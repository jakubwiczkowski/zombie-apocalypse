package pl.edu.pwr.student.zombiesim.simulation.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.ShooterHuman;

/**
 * Class used to store all textures that are used in the simulation
 */
public class Textures {

    /**
     * Texture used to represent a human entity on the map
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.entity.human.Human
     */
    public static final Texture HUMAN_TEXTURE = new Texture(Gdx.files.internal("test.png"));
    /**
     * Texture used to represent {@link ShooterHuman}
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.entity.human.Human
     */
    public static final Texture HUMAN_SHOOTER_TEXTURE = new Texture(Gdx.files.internal("human/shooter.png"));

    /**
     * Texture used to represent water on the map
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.map.Ground
     */
    public static final Texture WATER_TEXTURE = new Texture(Gdx.files.internal("water.png"));
    /**
     * Texture used to represent ground on the map
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.map.Ground
     */
    public static final Texture GRASS_TEXTURE = new Texture(Gdx.files.internal("grass.png"));
    /**
     * Texture used to represent alternative ground on the map
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.map.Ground
     */
    public static final Texture GRASS_ALT_TEXTURE = new Texture(Gdx.files.internal("ground_alternative.png"));

}
