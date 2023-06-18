package pl.edu.pwr.student.zombiesim.simulation.map;

import pl.edu.pwr.student.zombiesim.ZombieSimulation;
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

public class SimulationArea {

    private final Random random = new Random(System.currentTimeMillis());

    private final AbstractEntityManager<Human> humanManager = new HumanManager();
    private final AbstractEntityManager<Zombie> zombieManager = new ZombieManager();

    private List<Location> groundLocations;
    private final Ground[][] ground;

    private final int simulationSizeX;
    private final int simulationSizeY;

    public SimulationArea(int simulationSizeX, int simulationSizeY) {
        this.simulationSizeX = simulationSizeX;
        this.simulationSizeY = simulationSizeY;

        this.ground = new Ground[simulationSizeX][simulationSizeY];
        float[][] noise = PerlinNoiseGenerator.generatePerlinNoise(this.simulationSizeX, this.simulationSizeY, 4);

        this.groundLocations = new ArrayList<>();

        for (int i = 0; i < this.simulationSizeX; i++) {
            for (int j = 0; j < this.simulationSizeY; j++) {
                ground[i][j] = noise[i][j] >= 0.4 ? Ground.GRASS : Ground.WATER;

                if (ground[i][j] == Ground.GRASS)
                    this.groundLocations.add(new Location(i, j));
            }
        }

        populate(20, 20, this.groundLocations);
    }

    public AbstractEntityManager<Human> getHumanManager() {
        return this.humanManager;
    }

    public AbstractEntityManager<Zombie> getZombieManager() {
        return this.zombieManager;
    }

    public int getSimulationSizeX() {
        return this.simulationSizeX;
    }

    public int getSimulationSizeY() {
        return this.simulationSizeY;
    }
    public List<Location> getGroundLocation() {
        return this.groundLocations;
    }

    public Optional<Ground> getGroundAt(Location location) {
        if ((location.x() < 0 || location.x() >= this.simulationSizeX) ||
                (location.y() < 0 || location.y() >= this.simulationSizeY))
            return Optional.empty();

        return Optional.of(this.ground[location.x()][location.y()]);
    }

    private void populate(int humanCount, int zombieCount, List<Location> groundLocations) {
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
                zombie = new ChubbyZombie(this.getHumanManager().getNextId());
            else if (zombieType == 1)
                zombie = new KamikazeZombie(this.getHumanManager().getNextId());
            else
                zombie = new RegularZombie(this.getHumanManager().getNextId());

            this.getZombieManager().addEntity(zombie);
            zombie.setLocation(groundLocations.get(idx));
            groundLocations.remove(idx);
        }

    }

    public Random getRandom() {
        return random;
    }

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

        List<Human> humansToTurn = this.humanManager.getEntities().stream()
                .filter(human -> human.getHealth() <= 0 && human.isInfected())
                .toList();

        humansToDelete.forEach(this.humanManager::removeEntity);
        humansToTurn.forEach(this.humanManager::removeEntity);

        humansToTurn.stream().map(human -> {
            int zombieType = random.nextInt(3);

            if (zombieType == 0)
                return new ChubbyZombie(this.getHumanManager().getNextId(), human);
            else if (zombieType == 1)
                return new KamikazeZombie(this.getHumanManager().getNextId(), human);
            else
                return new RegularZombie(this.getHumanManager().getNextId(), human);
        }).forEach(zombie -> {
            this.zombieManager.addEntity(zombie);
            ZombieSimulation.getInstance().getGameStage().getEntityGroup().addActor(zombie);
        });

        List<Zombie> zombiesToDelete = this.zombieManager.getEntities().stream()
                .filter(zombie -> zombie.getHealth() <= 0)
                .toList();

        zombiesToDelete.forEach(this.zombieManager::removeEntity);
    }
}
