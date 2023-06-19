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
     * Number of {@link Human}s to spawn.
     */
    public static final int HUMAN_COUNT = 30;
    /**
     * Number of {@link Zombie}s to spawn.
     */
    public static final int ZOMBIE_COUNT = 30;

    private Settings() {
    }
}
