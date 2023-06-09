package pl.edu.pwr.student.zombiesim.simulation.map;

import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.manager.AbstractEntityManager;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.HumanManager;
import pl.edu.pwr.student.zombiesim.simulation.map.noise.PerlinNoiseGenerator;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.ZombieManager;

public class SimulationArea {

    private final ZombieSimulation zombieSimulation;

    private final AbstractEntityManager<Human> humanManager = new HumanManager();
    private final AbstractEntityManager<Zombie> zombieManager = new ZombieManager();

    private final Ground[][] ground;

    private final int simulationSizeX;
    private final int simulationSizeY;

    public SimulationArea(int simulationSizeX, int simulationSizeY, ZombieSimulation zombieSimulation) {
        this.zombieSimulation = zombieSimulation;

        this.simulationSizeX = simulationSizeX;
        this.simulationSizeY = simulationSizeY;

        this.ground = new Ground[simulationSizeX][simulationSizeY];

        float[][] noise = PerlinNoiseGenerator.generatePerlinNoise(this.simulationSizeX, this.simulationSizeY, 4);

        for (int i = 0; i < this.simulationSizeX; i++) {
            for (int j = 0; j < this.simulationSizeY; j++) {
                ground[i][j] = noise[i][j] >= 0.4 ? Ground.GRASS : Ground.WATER;
            }
        }
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
}
