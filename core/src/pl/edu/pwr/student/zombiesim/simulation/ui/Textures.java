package pl.edu.pwr.student.zombiesim.simulation.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.ShooterHuman;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.WarriorHuman;

import java.util.Random;

/**
 * Class used to store all textures that are used in the simulation
 */
public class Textures {

    /**
     * Texture used to represent a placeholder entity on the map
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.entity.human.Human
     */
    public static final Texture PLACEHOLDER_TEXTURE = new Texture(Gdx.files.internal("test.png"));
    /**
     * Texture used to represent a male human entity on the map
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.entity.human.Human
     */
    public static final Texture HUMAN_MALE_TEXTURE = new Texture(Gdx.files.internal("human/man.png"));
    /**
     * Texture used to represent a female human entity on the map
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.entity.human.Human
     */
    public static final Texture HUMAN_FEMALE_TEXTURE = new Texture(Gdx.files.internal("human/woman.png"));
    /**
     * Texture used to represent {@link ShooterHuman}
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.entity.human.Human
     */
    public static final Texture HUMAN_SHOOTER_TEXTURE = new Texture(Gdx.files.internal("human/shooter.png"));
    /**
     * Texture used to represent {@link WarriorHuman}
     *
     * @see pl.edu.pwr.student.zombiesim.simulation.entity.human.Human
     */
    public static final Texture HUMAN_WARRIOR_TEXTURE = new Texture(Gdx.files.internal("human/warrior.png"));

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
