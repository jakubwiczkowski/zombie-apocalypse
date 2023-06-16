package pl.edu.pwr.student.zombiesim.simulation.map;

import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.HumanManager;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.RegularHuman;
import pl.edu.pwr.student.zombiesim.simulation.entity.manager.AbstractEntityManager;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.ZombieManager;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.specializations.RegularZombie;
import pl.edu.pwr.student.zombiesim.simulation.map.noise.PerlinNoiseGenerator;

import java.util.*;

public class SimulationArea {

    private final Random random = new Random(System.currentTimeMillis());

    private final AbstractEntityManager<Human> humanManager = new HumanManager();
    private final AbstractEntityManager<Zombie> zombieManager = new ZombieManager();

    private final Ground[][] ground;

    private final int simulationSizeX;
    private final int simulationSizeY;

    public SimulationArea(int simulationSizeX, int simulationSizeY) {
        this.simulationSizeX = simulationSizeX;
        this.simulationSizeY = simulationSizeY;

        this.ground = new Ground[simulationSizeX][simulationSizeY];

        float[][] noise = PerlinNoiseGenerator.generatePerlinNoise(this.simulationSizeX, this.simulationSizeY, 4);

        for (int i = 0; i < this.simulationSizeX; i++) {
            for (int j = 0; j < this.simulationSizeY; j++) {
                ground[i][j] = noise[i][j] >= 0.4 ? Ground.GRASS : Ground.WATER;
            }
        }

        populate(20, 20);
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

    public Ground getGroundAt(Location location) {
        if ((location.x() < 0 || location.x() > this.simulationSizeX) ||
                (location.y() < 0 || location.y() > this.simulationSizeY))
            return Ground.GRASS;

        return this.ground[location.x()][location.y()];
    }

    public void populate(int humanCount, int zombieCount) {
        List<Location> groundLocations = new ArrayList<>();

        for (int i = 0; i < this.simulationSizeX; i++) {
            for (int j = 0; j < this.simulationSizeY; j++) {
                Location currLoc = new Location(i, j);

                if (getGroundAt(currLoc) != Ground.GRASS)
                    continue;

                groundLocations.add(currLoc);
            }
        }

        for (int i = 0; i < humanCount; i++) {
            int idx = random.nextInt(groundLocations.size());

            RegularHuman human = new RegularHuman(this.getHumanManager().getNextId(), ZombieSimulation.getInstance());
            this.getHumanManager().addEntity(human);
            human.setLocation(groundLocations.get(idx));

            groundLocations.remove(idx);
        }

        for (int i = 0; i < zombieCount; i++) {
            int idx = random.nextInt(groundLocations.size());

            RegularZombie zombie = new RegularZombie(this.getZombieManager().getNextId(), ZombieSimulation.getInstance());
            this.getZombieManager().addEntity(zombie);
            zombie.setLocation(groundLocations.get(idx));

            groundLocations.remove(idx);
        }
    }

    public void act() {

    }
}
