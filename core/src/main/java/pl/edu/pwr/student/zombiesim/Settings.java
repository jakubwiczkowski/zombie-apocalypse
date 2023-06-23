package pl.edu.pwr.student.zombiesim;

import com.badlogic.gdx.Gdx;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Class that stores global variables used as Settings.
 */
public class Settings {

    private static final Properties SETTINGS;

    static {
        File file = Gdx.files.internal("simulation.properties").file();

        SETTINGS = new Properties();
        try {
            SETTINGS.load(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Width of the simulation's map.
     */
    public static final int MAP_SIZE_WIDTH = Integer.parseInt(SETTINGS.getProperty("mapSizeWidth"));
    /**
     * Height of the simulation's map.
     */
    public static final int MAP_SIZE_HEIGHT = Integer.parseInt(SETTINGS.getProperty("mapSizeHeight"));
    /**
     * Minimum value from Perlin noise to be considered ground
     */
    public static final double MAP_MIN_FOR_GROUND = Double.parseDouble(SETTINGS.getProperty("mapMinForGround"));
    /**
     * Octave count for Perlin noise generation
     */
    public static final int MAP_OCTAVES = Integer.parseInt(SETTINGS.getProperty("mapOctaves"));

    /**
     * Number of {@link Human}s to spawn.
     */
    public static final int HUMAN_COUNT = Integer.parseInt(SETTINGS.getProperty("humanCount"));
    /**
     * Number of {@link Zombie}s to spawn.
     */
    public static final int ZOMBIE_COUNT = Integer.parseInt(SETTINGS.getProperty("zombieCount"));

    /**
     * Normal distribution mean for health
     */
    public static final double HUMAN_HEALTH_MEAN = Double.parseDouble(SETTINGS.getProperty("humanHealthMean"));
    /**
     * Normal distribution variance for health
     */
    public static final double HUMAN_HEALTH_VARIANCE = Double.parseDouble(SETTINGS.getProperty("humanHealthVariance"));
    /**
     * Minimal health value
     */
    public static final double HUMAN_HEALTH_MIN = Double.parseDouble(SETTINGS.getProperty("humanHealthMin"));
    /**
     * Maximum health value
     */
    public static final double HUMAN_HEALTH_MAX = Double.parseDouble(SETTINGS.getProperty("humanHealthMax"));
    /**
     * Minimal strength value
     */
    public static final double HUMAN_STRENGTH_MIN = Double.parseDouble(SETTINGS.getProperty("humanStrengthMin"));
    /**
     * Maximum strength value
     */
    public static final double HUMAN_STRENGTH_MAX = Double.parseDouble(SETTINGS.getProperty("humanStrengthMax"));
    /**
     * Maximum agility value
     */
    public static final double HUMAN_AGILITY_MAX = Double.parseDouble(SETTINGS.getProperty("humanAgilityMax"));
    /**
     * Percentage for a Human to get pregnant
     */
    public static final double HUMAN_PREGNANCY_CHANCE = Double.parseDouble(SETTINGS.getProperty("humanPregnancyChance"));
    /**
     * Cooldown for Human pregnancy (in rounds)
     */
    public static final int HUMAN_PREGNANCY_ROUNDS = Integer.parseInt(SETTINGS.getProperty("humanPregnancyRounds"));
    /**
     * Multiplier for statistics for specialized classes
     */
    public static final double HUMAN_EXTRA_MULTIPLIER = Double.parseDouble(SETTINGS.getProperty("humanExtraMultiplier"));

    /**
     * Minimal agility for a StudentHuman
     */
    public static final double STUDENT_HUMAN_AGILITY_MIN = Double.parseDouble(SETTINGS.getProperty("studentAgilityMin"));
    /**
     * Maximum agility for a StudentHuman
     */
    public static final double STUDENT_HUMAN_AGILITY_MAX = Double.parseDouble(SETTINGS.getProperty("studentAgilityMax"));

    /**
     * Normal distribution mean for strength
     */
    public static final double HUMAN_STRENGTH_MEAN = Double.parseDouble(SETTINGS.getProperty("humanStrengthMean"));
    /**
     * Normal distribution variance for strength
     */
    public static final double HUMAN_STRENGTH_VARIANCE = Double.parseDouble(SETTINGS.getProperty("humanStrengthVariance"));


    /**
     * Normal distribution mean for health
     */
    public static final double ZOMBIE_HEALTH_MEAN = Double.parseDouble(SETTINGS.getProperty("zombieHealthMean"));
    /**
     * Normal distribution variance for health
     */
    public static final double ZOMBIE_HEALTH_VARIANCE = Double.parseDouble(SETTINGS.getProperty("zombieHealthVariance"));
    /**
     * Normal distribution mean for strength
     */
    public static final double ZOMBIE_STRENGTH_MEAN = Double.parseDouble(SETTINGS.getProperty("zombieStrengthMean"));
    /**
     * Normal distribution variance for strength
     */
    public static final double ZOMBIE_STRENGTH_VARIANCE = Double.parseDouble(SETTINGS.getProperty("zombieStrengthVariance"));
    /**
     * Minimal health value
     */
    public static final double ZOMBIE_HEALTH_MIN = Double.parseDouble(SETTINGS.getProperty("zombieHealthMin"));
    /**
     * Maximum health value
     */
    public static final double ZOMBIE_HEALTH_MAX = Double.parseDouble(SETTINGS.getProperty("zombieHealthMax"));
    /**
     * Minimal strength value
     */
    public static final double ZOMBIE_STRENGTH_MIN = Double.parseDouble(SETTINGS.getProperty("zombieStrengthMin"));
    /**
     * Maximum strength value
     */
    public static final double ZOMBIE_STRENGTH_MAX = Double.parseDouble(SETTINGS.getProperty("zombieStrengthMax"));
    /**
     * Maximum agility value
     */
    public static final double ZOMBIE_AGILITY_MAX = Double.parseDouble(SETTINGS.getProperty("zombieAgilityMax"));
    /**
     * Minimum infection rate value
     */
    public static final double ZOMBIE_INFECTIONRATE_MIN = Double.parseDouble(SETTINGS.getProperty("zombieInfectionRateMin"));
    /**
     * Maximum infection rate value
     */
    public static final double ZOMBIE_INFECTIONRATE_MAX = Double.parseDouble(SETTINGS.getProperty("zombieInfectionRateMax"));
    /**
     * Multiplier for statistics for specialized classes
     */
    public static final double ZOMBIE_EXTRA_MULTIPLIER = Double.parseDouble(SETTINGS.getProperty("zombieExtraMultiplier"));

    private Settings() {
    }
}
