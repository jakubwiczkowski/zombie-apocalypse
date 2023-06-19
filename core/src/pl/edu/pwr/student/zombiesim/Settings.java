package pl.edu.pwr.student.zombiesim;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;

/**
 * Class that stores global variables used as Settings.
 */
public class Settings {

    /**
     * Width of the simulation's map.
     */
    public static final int MAP_SIZE_WIDTH = 30;
    /**
     * Height of the simulation's map.
     */
    public static final int MAP_SIZE_HEIGHT = 30;
    /**
     * Minimum value from Perlin noise to be considered ground
     */
    public static final double MAP_MIN_FOR_GROUND = 0.4;
    /**
     * Octave count for Perlin noise generation
     */
    public static final int MAP_OCTAVES = 4;

    /**
     * Number of {@link Human}s to spawn.
     */
    public static final int HUMAN_COUNT = 30;
    /**
     * Number of {@link Zombie}s to spawn.
     */
    public static final int ZOMBIE_COUNT = 30;

    /**
     * Normal distribution mean for health
     */
    public static final double HUMAN_HEALTH_MEAN = 100;
    /**
     * Normal distribution variance for health
     */
    public static final double HUMAN_HEALTH_VARIANCE = 20;
    /**
     * Minimal health value
     */
    public static final double HUMAN_HEALTH_MIN = 10;
    /**
     * Maximum health value
     */
    public static final double HUMAN_HEALTH_MAX = 200;
    /**
     * Minimal strength value
     */
    public static final double HUMAN_STRENGTH_MIN = 1;
    /**
     * Maximum strength value
     */
    public static final double HUMAN_STRENGTH_MAX = 100;
    /**
     * Maximum agility value
     */
    public static final double HUMAN_AGILITY_MAX = 0.9;
    /**
     * Percentage for a Human to get pregnant
     */
    public static final double HUMAN_PREGNANCY_CHANCE = 0.01;
    /**
     * Cooldown for Human pregnancy (in rounds)
     */
    public static final double HUMAN_PREGNANCY_ROUNDS = 100;
    /**
     * Multiplier for statistics for specialized classes
     */
    public static final double HUMAN_EXTRA_MULTIPLIER = 1.2;

    /**
     * Minimal agility for a StudentHuman
     */
    public static final double STUDENT_HUMAN_AGILITY_MIN = 0;
    /**
     * Maximum agility for a StudentHuman
     */
    public static final double STUDENT_HUMAN_AGILITY_MAX = 0.95;

    /**
     * Normal distribution mean for strength
     */
    public static final double HUMAN_STRENGTH_MEAN = 30;
    /**
     * Normal distribution variance for strength
     */
    public static final double HUMAN_STRENGTH_VARIANCE = 3;


    /**
     * Normal distribution mean for health
     */
    public static final double ZOMBIE_HEALTH_MEAN = 100;
    /**
     * Normal distribution variance for health
     */
    public static final double ZOMBIE_HEALTH_VARIANCE = 20;
    /**
     * Normal distribution mean for strength
     */
    public static final double ZOMBIE_STRENGTH_MEAN = 30;
    /**
     * Normal distribution variance for strength
     */
    public static final double ZOMBIE_STRENGTH_VARIANCE = 3;
    /**
     * Minimal health value
     */
    public static final double ZOMBIE_HEALTH_MIN = 10;
    /**
     * Maximum health value
     */
    public static final double ZOMBIE_HEALTH_MAX = 200;
    /**
     * Minimal strength value
     */
    public static final double ZOMBIE_STRENGTH_MIN = 1;
    /**
     * Maximum strength value
     */
    public static final double ZOMBIE_STRENGTH_MAX = 100;
    /**
     * Maximum agility value
     */
    public static final double ZOMBIE_AGILITY_MAX = 0.8;
    /**
     * Minimum infection rate value
     */
    public static final double ZOMBIE_INFECTIONRATE_MIN = 0.4;
    /**
     * Maximum infection rate value
     */
    public static final double ZOMBIE_INFECTIONRATE_MAX = 0.6;
    /**
     * Multiplier for statistics for specialized classes
     */
    public static final double ZOMBIE_EXTRA_MULTIPLIER = 1.2;

    private Settings() {
    }
}
