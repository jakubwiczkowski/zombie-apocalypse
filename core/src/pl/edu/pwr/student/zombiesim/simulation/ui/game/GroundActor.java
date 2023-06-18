package pl.edu.pwr.student.zombiesim.simulation.ui.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import pl.edu.pwr.student.zombiesim.simulation.map.Ground;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;

/**
 * Object used for rendering {@link Ground} on the screen.
 */
public class GroundActor extends Actor {

    private final Ground ground;
    private final Texture texture;

    /**
     * Constructor for {@link GroundActor} object.
     *
     * @see GameStage
     *
     * @param ground type of {@link Ground} to render
     * @param x      coordinate on the X axis
     * @param y      coordinate on the Y axis
     */
    public GroundActor(Ground ground, float x, float y) {
        super();

        this.ground = ground;

        this.texture = ground == Ground.GRASS ? Textures.GRASS_TEXTURE : Textures.WATER_TEXTURE;

        setX(x);
        setY(y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(this.texture, getX() * this.texture.getWidth(), getY() * this.texture.getHeight());
    }
}
