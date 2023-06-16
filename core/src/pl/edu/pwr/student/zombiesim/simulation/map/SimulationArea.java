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

    ArrayList<ArrayList<Integer>> groundCoordinates;

    private final int simulationSizeX;
    private final int simulationSizeY;

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    public SimulationArea(int simulationSizeX, int simulationSizeY) {
        this.simulationSizeX = simulationSizeX;
        this.simulationSizeY = simulationSizeY;

        this.ground = new Ground[simulationSizeX][simulationSizeY];
        this.groundCoordinates = new ArrayList<>(simulationSizeX);

        float[][] noise = PerlinNoiseGenerator.generatePerlinNoise(this.simulationSizeX, this.simulationSizeY, 4);

        groundCoordinates = getGroundCoordinates(simulationSizeX, simulationSizeY, noise);

        List<Location> groundLocation = new ArrayList<>();

        for (int x = 0; x < getSimulationSizeX(); x++) {
            for (int y = 0; y < simulationSizeY; y++) {
                if (groundCoordinates.get(x).contains(y))
                    groundLocation.add(new Location(x, y));
            }
        }

        for (int i = 0; i < this.simulationSizeX; i++) {
            for (int j = 0; j < this.simulationSizeY; j++) {
                ground[i][j] = noise[i][j] >= 0.4 ? Ground.GRASS : Ground.WATER;
            }
        }

        populate(20, 20, groundLocation);
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

    private void populate(int humanCount, int zombieCount, List<Location> groundLocation) {

        for (int i = 0; i < humanCount; i++) {

            int random = getRandomNumber(groundLocation);

            RegularHuman human = new RegularHuman(this.getHumanManager().getNextId(), ZombieSimulation.getInstance());
            this.getHumanManager().addEntity(human);
            human.setLocation(groundLocation.get(random));
            groundLocation.remove(random);
        }

        for (int i = 0; i < zombieCount; i++) {

            int random = getRandomNumber(groundLocation);

            RegularZombie zombie = new RegularZombie(this.getHumanManager().getNextId(), ZombieSimulation.getInstance());
            this.getZombieManager().addEntity(zombie);
            zombie.setLocation(groundLocation.get(random));
            groundLocation.remove(random);
        }

    }

    private ArrayList<ArrayList<Integer>> getGroundCoordinates(int simulationSizeX, int simulationSizeY, float[][] noise) {

        ArrayList<ArrayList<Integer>> groundCoordinates = new ArrayList<>(simulationSizeX);

        for (int x = 0; x < simulationSizeX; x++) {
            groundCoordinates.add(new ArrayList<>());
        }
        for (int x = 0; x < simulationSizeX; x++) {
            for (int y = 0; y < simulationSizeY; y++) {
                if (noise[x][y] >= 0.4) {
                    groundCoordinates.get(x).add(y);
                }
            }
        }
        return groundCoordinates;
    }
    private int getRandomNumber(List<Location> groundLocation) {

        int min = 0;
        int max = groundLocation.size();

        int randomForX = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return randomForX;
    }

    public void act() {

    }
}
