package pl.edu.pwr.student.zombiesim.simulation.map;

import pl.edu.pwr.student.zombiesim.Settings;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.HumanManager;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.RegularHuman;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.ShooterHuman;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.StudentHuman;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.WarriorHuman;
import pl.edu.pwr.student.zombiesim.simulation.entity.manager.AbstractEntityManager;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.ZombieManager;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.specializations.ChubbyZombie;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.specializations.KamikazeZombie;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.specializations.RegularZombie;
import pl.edu.pwr.student.zombiesim.simulation.map.noise.PerlinNoiseGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Class that takes care of everything map related.
 * Holds {@link HumanManager} and {@link ZombieManager} instances.
 * Generates the map based on Perlin noise.
 *
 * @see PerlinNoiseGenerator
 */
public class SimulationArea {

    private final Random random = new Random(System.currentTimeMillis());

    private final AbstractEntityManager<Human> humanManager = new HumanManager();
    private final AbstractEntityManager<Zombie> zombieManager = new ZombieManager();

    private final List<Location> groundLocations;
    private final Ground[][] ground;

    private final int simulationSizeX;
    private final int simulationSizeY;

    public SimulationArea(int simulationSizeX, int simulationSizeY) {
        this.simulationSizeX = simulationSizeX;
        this.simulationSizeY = simulationSizeY;

        this.ground = new Ground[simulationSizeX][simulationSizeY];

        float[][] noise = PerlinNoiseGenerator.generatePerlinNoise(this.simulationSizeX, this.simulationSizeY, Settings.MAP_OCTAVES);

        this.groundLocations = new ArrayList<>();

        for (int i = 0; i < this.simulationSizeX; i++) {
            for (int j = 0; j < this.simulationSizeY; j++) {
                ground[i][j] = noise[i][j] >= Settings.MAP_MIN_FOR_GROUND ? Ground.GRASS : Ground.WATER;

                if (ground[i][j] == Ground.GRASS)
                    this.groundLocations.add(new Location(i, j));
            }
        }
    }

    /**
     * Method that returns {@link HumanManager} instance.
     *
     * @see AbstractEntityManager
     *
     * @return {@link HumanManager} object
     */
    public AbstractEntityManager<Human> getHumanManager() {
        return this.humanManager;
    }

    /**
     * Method that returns {@link ZombieManager} instance.
     *
     * @see AbstractEntityManager
     *
     * @return {@link ZombieManager} object
     */
    public AbstractEntityManager<Zombie> getZombieManager() {
        return this.zombieManager;
    }

    /**
     * Method that returns width of the simulation
     * in tiles (one tile is 32x32 pixels).
     *
     * @return width of the simulation
     */
    public int getSimulationSizeX() {
        return this.simulationSizeX;
    }

    /**
     * Method that returns height of the simulation
     * in tiles (one tile is 32x32 pixels).
     *
     * @return height of the simulation
     */
    public int getSimulationSizeY() {
        return this.simulationSizeY;
    }

    /**
     * Returns the type of {@link Ground} at a
     * given {@link Location}.
     *
     * @param location {@link Location} on the map
     * @return         {@link Ground} if exists, null otherwise
     *                 (enclosed in {@link Optional})
     */
    public Optional<Ground> getGroundAt(Location location) {
        if ((location.x() < 0 || location.x() >= this.simulationSizeX) ||
                (location.y() < 0 || location.y() >= this.simulationSizeY))
            return Optional.empty();

        return Optional.of(this.ground[location.x()][location.y()]);
    }

    /**
     * Sets the type of ground at specified coordinates.
     *
     * @param location {@link Location} of the ground
     * @param ground   type of {@link Ground} to be set
     */
    public void setGroundAt(Location location, Ground ground) {
        if ((location.x() < 0 || location.x() >= this.simulationSizeX) ||
                (location.y() < 0 || location.y() >= this.simulationSizeY))
            return;

        this.ground[location.x()][location.y()] = ground;
    }

    /**
     * Method that returns {@link List} of locations that are not {@link Ground#WATER}
     *
     * @return {@link List} of locations that are not {@link Ground#WATER}
     */
    public List<Location> getGroundLocations() {
        return groundLocations;
    }

    /**
     * Populates the simulation map with {@link Human}s
     * and {@link Zombie}s.
     *
     * @param humanCount      number of {@link Human}s to spawn
     * @param zombieCount     number of {@link Zombie}s to spawn
     * @param groundLocations available spawn locations
     */
    public void populate(int humanCount, int zombieCount, List<Location> groundLocations) {
        if (humanCount + zombieCount > groundLocations.size())
            return;

        for (int i = 0; i < humanCount; i++) {
            int idx = random.nextInt(groundLocations.size());

            Human human;

            int humanType = random.nextInt(4);
            if (humanType == 0)
                human = new RegularHuman(this.getHumanManager().getNextId());
            else if (humanType == 1)
                human = new ShooterHuman(this.getHumanManager().getNextId());
            else if (humanType == 2)
                human = new StudentHuman(this.getHumanManager().getNextId());
            else
                human = new WarriorHuman(this.getHumanManager().getNextId());

            this.getHumanManager().addEntity(human);
            human.setLocation(groundLocations.get(idx));
            groundLocations.remove(idx);
        }

        for (int i = 0; i < zombieCount; i++) {
            int idx = random.nextInt(groundLocations.size());

            Zombie zombie;

            int zombieType = random.nextInt(3);

            if (zombieType == 0)
                zombie = new ChubbyZombie(this.getZombieManager().getNextId());
            else if (zombieType == 1)
                zombie = new KamikazeZombie(this.getZombieManager().getNextId());
            else
                zombie = new RegularZombie(this.getZombieManager().getNextId());

            this.getZombieManager().addEntity(zombie);
            zombie.setLocation(groundLocations.get(idx));
            groundLocations.remove(idx);
        }

    }

    /**
     * Returns this object's instance of {@link Random} class.
     *
     * @return an instance of {@link Random}
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Method called at the start of every round.
     * Takes care of {@link AbstractEntity} interaction, movement, death
     * and turning infected {@link Human}s into {@link Zombie}s.
     *
     * @see ZombieSimulation
     */
    public void act() {
        this.humanManager.getEntities().forEach(Human::interact);
        this.humanManager.getEntities().forEach(Human::move);
        this.zombieManager.getEntities().forEach(Zombie::interact);
        this.zombieManager.getEntities().forEach(Zombie::move);

        this.humanManager.getEntities().forEach(human -> human.setHasInteracted(false));
        this.zombieManager.getEntities().forEach(zombie -> zombie.setHasInteracted(false));

        List<Human> humansToDelete = this.humanManager.getEntities().stream()
                .filter(human -> human.getHealth() <= 0 && !human.isInfected())
                .toList();

        ZombieSimulation.getInstance()
                .getDataCollector()
                .addHumansDied(humansToDelete.size());

        List<Human> humansToTurn = this.humanManager.getEntities().stream()
                .filter(human -> human.getHealth() <= 0 && human.isInfected())
                .toList();

        ZombieSimulation.getInstance()
                .getDataCollector()
                .addHumansTurned(humansToTurn.size());

        humansToDelete.forEach(this.humanManager::removeEntity);
        humansToTurn.forEach(this.humanManager::removeEntity);

        humansToTurn.stream().map(human -> {
            int zombieType = random.nextInt(3);

            if (zombieType == 0)
                return new ChubbyZombie(this.getZombieManager().getNextId(), human);
            else if (zombieType == 1)
                return new KamikazeZombie(this.getZombieManager().getNextId(), human);
            else
                return new RegularZombie(this.getZombieManager().getNextId(), human);
        }).forEach(zombie -> {
            this.zombieManager.addEntity(zombie);
            ZombieSimulation.getInstance().getGameStage().getEntityGroup().addActor(zombie);
        });

        List<Zombie> zombiesToDelete = this.zombieManager.getEntities().stream()
                .filter(zombie -> zombie.getHealth() <= 0)
                .toList();

        ZombieSimulation.getInstance()
                .getDataCollector()
                .addZombiesDied(zombiesToDelete.size());

        zombiesToDelete.forEach(this.zombieManager::removeEntity);
    }
}
