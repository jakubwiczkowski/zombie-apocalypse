package pl.edu.pwr.student.zombiesim.simulation.data;

import com.badlogic.gdx.Gdx;
import org.apache.commons.math3.util.Pair;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects data about the simulation.
 */
public class DataCollector {

    private boolean outputted = false;

    private int roundsSimulated = 0;

    private int humansBorn = 0;
    private int humansTurned = 0;
    private int humansDied = 0;

    private int zombiesDied = 0;

    private int finalZombieCount = 0;
    private int finalHumanCount = 0;

    private int groundCount = 0;
    private int waterCount = 0;

    private final List<Pair<String, Double>> humanAgility = new ArrayList<>();
    private final List<Pair<String, Double>> humanMaxHealth = new ArrayList<>();
    private final List<Pair<String, Double>> humanStrength = new ArrayList<>();

    private final List<Pair<String, Double>> zombieAgility = new ArrayList<>();
    private final List<Pair<String, Double>> zombieMaxHealth = new ArrayList<>();
    private final List<Pair<String, Double>> zombieStrength = new ArrayList<>();
    private final List<Pair<String, Double>> zombieInfectionRate = new ArrayList<>();

    public DataCollector() {
    }

    /**
     * Collects data about specified {@link Zombie}.
     *
     * @param zombie {@link Zombie} to collect data
     */
    public void addZombieData(Zombie zombie) {
        zombieAgility.add(new Pair<>(zombie.getName(), zombie.getAgility()));
        zombieMaxHealth.add(new Pair<>(zombie.getName(), zombie.getMaxHealth()));
        zombieStrength.add(new Pair<>(zombie.getName(), zombie.getStrength()));
        zombieInfectionRate.add(new Pair<>(zombie.getName(), zombie.getInfectionRate()));
    }

    /**
     * Collects data about specified {@link Human}.
     *
     * @param human {@link Human} to collect data
     */
    public void addHumanData(Human human) {
        humanAgility.add(new Pair<>(human.getName(), human.getAgility()));
        humanMaxHealth.add(new Pair<>(human.getName(), human.getMaxHealth()));
        humanStrength.add(new Pair<>(human.getName(), human.getStrength()));
    }

    /**
     * Method that returns amount of {@link Human}s born.
     *
     * @return amount of {@link Human}s born
     */
    public int getHumansBorn() {
        return humansBorn;
    }

    /**
     * Method that returns amount of {@link Human}s turned
     * to {@link Zombie}s.
     *
     * @return amount of {@link Human}s born
     */
    public int getHumansTurned() {
        return humansTurned;
    }

    /**
     * Method that returns amount of rounds
     * simulated.
     *
     * @return amount of rounds simulated
     */
    public int getRoundsSimulated() {
        return roundsSimulated;
    }

    /**
     * Method that returns amount of {@link Human}s that
     * died during simulation.
     *
     * @return amount of {@link Human}s that died
     */
    public int getHumansDied() {
        return humansDied;
    }

    /**
     * Method that returns amount of {@link Zombie}s that
     * died during simulation.
     *
     * @return amount of {@link Zombie}s that died
     */
    public int getZombiesDied() {
        return zombiesDied;
    }

    /**
     * Adds specified amount to humansDied counter.
     *
     * @param count amount to be added
     */
    public void addHumansDied(int count) {
        this.humansDied += count;
    }

    /**
     * Adds specified amount to humansTurned counter.
     *
     * @param count amount to be added
     */
    public void addHumansTurned(int count) {
        this.humansTurned += count;
    }

    /**
     * Adds specified amount to humansBorn counter.
     *
     * @param count amount to be added
     */
    public void addHumansBorn(int count) {
        this.humansBorn += count;
    }

    /**
     * Adds specified amount to zombiesDied counter.
     *
     * @param count amount to be added
     */
    public void addZombiesDied(int count) {
        this.zombiesDied += count;
    }

    /**
     * Sets specified amount to humansDied counter.
     *
     * @param humansBorn amount to be set
     */
    public void setHumansBorn(int humansBorn) {
        this.humansBorn = humansBorn;
    }

    /**
     * Sets specified amount to humansTurned counter.
     *
     * @param humansTurned amount to be set
     */
    public void setHumansTurned(int humansTurned) {
        this.humansTurned = humansTurned;
    }

    /**
     * Sets specified amount to humansDied counter.
     *
     * @param humansDied amount to be set
     */
    public void setHumansDied(int humansDied) {
        this.humansDied = humansDied;
    }

    /**
     * Sets specified amount to zombiesDied counter.
     *
     * @param zombiesDied amount to be set
     */
    public void setZombiesDied(int zombiesDied) {
        this.zombiesDied = zombiesDied;
    }

    /**
     * Sets specified amount to roundsSimulated counter.
     *
     * @param roundsSimulated amount to be added
     */
    public void setRoundsSimulated(int roundsSimulated) {
        this.roundsSimulated = roundsSimulated;
    }

    /**
     * Sets specified amount to finalZombieCount counter.
     *
     * @param finalZombieCount amount to be added
     */
    public void setFinalZombieCount(int finalZombieCount) {
        this.finalZombieCount = finalZombieCount;
    }

    /**
     * Sets specified amount to finalHumanCount counter.
     *
     * @param finalHumanCount amount to be added
     */
    public void setFinalHumanCount(int finalHumanCount) {
        this.finalHumanCount = finalHumanCount;
    }

    /**
     * Sets specified amount to groundCount counter.
     *
     * @param groundCount amount to be added
     */
    public void setGroundCount(int groundCount) {
        this.groundCount = groundCount;
    }

    /**
     * Sets specified amount to waterCount counter.
     *
     * @param waterCount amount to be added
     */
    public void setWaterCount(int waterCount) {
        this.waterCount = waterCount;
    }

    /**
     * Writes collected data to .csv files in
     * assets/ folder.
     */
    public void writeData() throws IOException {
        if (outputted)
            return;

        BufferedWriter humanWriter = new BufferedWriter(new FileWriter("humans.csv", true));

        if (Gdx.files.internal("humans.csv").readString().isEmpty())
            humanWriter.write("type,agility,maxhealth,strength\n");

        int hCount = humanAgility.size();

        for (int idx = 0; idx < hCount; idx++) {
            humanWriter.append(
                    humanAgility.get(idx).getFirst() + "," +
                    humanAgility.get(idx).getSecond() + "," +
                    humanMaxHealth.get(idx).getSecond() + "," +
                    humanStrength.get(idx).getSecond() + "\n");
        }

        humanWriter.close();

        BufferedWriter zombieWriter = new BufferedWriter(new FileWriter("zombies.csv", true));

        if (Gdx.files.internal("zombies.csv").readString().isEmpty())
            zombieWriter.write("type,agility,maxhealth,strength,infectionRate\n");

        int zCount = zombieAgility.size();

        for (int idx = 0; idx < zCount; idx++) {
            zombieWriter.append(
                    zombieAgility.get(idx).getFirst() + "," +
                            zombieAgility.get(idx).getSecond() + "," +
                            zombieMaxHealth.get(idx).getSecond() + "," +
                            zombieStrength.get(idx).getSecond() + "," +
                            zombieInfectionRate.get(idx).getSecond() + "\n");
        }

        zombieWriter.close();

        BufferedWriter overallWriter = new BufferedWriter(new FileWriter("overall.csv", true));

        if (Gdx.files.internal("overall.csv").readString().isEmpty())
            overallWriter.write("roundsSimulated,humansBorn,humansTurned,humansDied,zombiesDied,finalHumanCount,finalZombieCount,groundCount,waterCount\n");

        overallWriter.append(roundsSimulated + "," +
                humansBorn + "," +
                humansTurned + "," +
                humansDied + "," +
                zombiesDied + "," +
                finalHumanCount + "," +
                finalZombieCount + "," +
                groundCount + "," +
                waterCount + "\n");

        overallWriter.close();

        outputted = true;
    }
}
