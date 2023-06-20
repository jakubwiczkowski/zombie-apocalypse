package pl.edu.pwr.student.zombiesim.simulation.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;

/**
 * The main class that represents an Entity. Extends
 * {@link Actor} and implements {@link Identifiable}, and its
 * unique identifier is an integer.
 */
public abstract class AbstractEntity extends Actor implements Identifiable<Integer> {

    private final int id;

    public AbstractEntity(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getIdentifier() {
        return this.id;
    }

    /**
     * Method that returns {@link AbstractEntity}'s
     * {@link Texture}.
     *
     * @return {@link AbstractEntity}'s {@link Texture}
     */
    public abstract Texture getTexture();

    /**
     * Method that returns the current lokation of
     * {@link AbstractEntity}.
     *
     * @return {@link AbstractEntity}'s {@link Location}
     */
    public abstract Location getLocation();

    /**
     * Takes care of {@link AbstractEntity}'s movement
     * logic.
     */
    public abstract void move();

    /**
     * Takes care of {@link AbstractEntity}'s interaction
     * logic.
     */
    public abstract void interact();

    /**
     * Method that returns the name of the
     * {@link AbstractEntity}. Used to differentiate
     * between entity types.
     *
     * @return name of the {@link AbstractEntity}
     */
    public abstract String getName();

}
