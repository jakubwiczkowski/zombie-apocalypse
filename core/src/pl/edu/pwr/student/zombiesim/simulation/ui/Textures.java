package pl.edu.pwr.student.zombiesim.simulation.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

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
     * Texture used to represent water on the map
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.map.Ground
     */
    public static final Texture WATER_TEXTURE = new Texture(Gdx.files.internal("water.png"));
    /**
     * Texture used to represent grass on the map
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.map.Ground
     */
    public static final Texture GRASS_TEXTURE = new Texture(Gdx.files.internal("grass.png"));

}
