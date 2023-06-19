package pl.edu.pwr.student.zombiesim.simulation.data;

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
     * Writes collected data to .csv files in
     * assets/ folder.
     */
    public void writeData() throws IOException {
        if (outputted)
            return;

        BufferedWriter humAgWriter = new BufferedWriter(new FileWriter("h_ag.csv"));
        BufferedWriter humMhWriter = new BufferedWriter(new FileWriter("h_mh.csv"));
        BufferedWriter humStWriter = new BufferedWriter(new FileWriter("h_st.csv"));

        humAgWriter.write("type,agility\n");
        humMhWriter.write("type,maxhealth\n");
        humStWriter.write("type,strength\n");

        humanAgility.forEach(pair -> {
            try {
                humAgWriter.append(pair.getFirst())
                        .append(",")
                        .append(String.valueOf(pair.getSecond()))
                        .append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        humanMaxHealth.forEach(pair -> {
            try {
                humMhWriter.append(pair.getFirst())
                        .append(",")
                        .append(String.valueOf(pair.getSecond()))
                        .append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        humanStrength.forEach(pair -> {
            try {
                humStWriter.append(pair.getFirst())
                        .append(",")
                        .append(String.valueOf(pair.getSecond()))
                        .append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        humAgWriter.close();
        humMhWriter.close();
        humStWriter.close();

        BufferedWriter zomAgWriter = new BufferedWriter(new FileWriter("z_ag.csv"));
        BufferedWriter zomMhWriter = new BufferedWriter(new FileWriter("z_mh.csv"));
        BufferedWriter zomStWriter = new BufferedWriter(new FileWriter("z_st.csv"));
        BufferedWriter zomIrWriter = new BufferedWriter(new FileWriter("z_st.csv"));

        zomAgWriter.write("type,agility\n");
        zomMhWriter.write("type,maxhealth\n");
        zomStWriter.write("type,strength\n");
        zomIrWriter.write("type,infectionRate\n");

        zombieAgility.forEach(pair -> {
            try {
                zomAgWriter.append(pair.getFirst())
                        .append(",")
                        .append(String.valueOf(pair.getSecond()))
                        .append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        zombieMaxHealth.forEach(pair -> {
            try {
                zomMhWriter.append(pair.getFirst())
                        .append(",")
                        .append(String.valueOf(pair.getSecond()))
                        .append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        zombieStrength.forEach(pair -> {
            try {
                zomStWriter.append(pair.getFirst())
                        .append(",")
                        .append(String.valueOf(pair.getSecond()))
                        .append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        zombieInfectionRate.forEach(pair -> {
            try {
                zomIrWriter.append(pair.getFirst())
                        .append(",")
                        .append(String.valueOf(pair.getSecond()))
                        .append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        zomAgWriter.close();
        zomMhWriter.close();
        zomStWriter.close();
        zomIrWriter.close();

        BufferedWriter overallWriter = new BufferedWriter(new FileWriter("overall.csv"));

        overallWriter.write("roundsSimulated,humansBorn,humansTurned,humansDied,zombiesDied\n");
        overallWriter.append(roundsSimulated + "," + humansBorn + "," + humansTurned + "," + humansDied + "," + zombiesDied);

        overallWriter.close();

        outputted = true;
    }
}
