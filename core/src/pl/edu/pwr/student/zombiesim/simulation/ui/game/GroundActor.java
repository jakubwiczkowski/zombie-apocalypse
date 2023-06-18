package pl.edu.pwr.student.zombiesim.simulation.ui.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import pl.edu.pwr.student.zombiesim.simulation.map.Ground;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;

/**
 * Object used for rendering {@link Ground} on the screen.
 */
public class GroundActor extends Actor {

    private Ground ground;
    private Texture texture;

    private Location location;

    /**
     * Constructor for {@link GroundActor} object.
     *
     * @see GameStage
     *
     * @param ground   type of {@link Ground} to render
     * @param location {@link Ground}'s {@link Location}
     */
    public GroundActor(Ground ground, Location location) {
        this.ground = ground;

        this.location = location;

        this.texture = ground == Ground.GRASS ? Textures.GRASS_TEXTURE : Textures.WATER_TEXTURE;

        addListener(new GroundInputListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setBounds(location.x() * this.texture.getWidth(), location.y() * this.texture.getHeight(), texture.getWidth(), texture.getHeight());

        batch.draw(this.texture, location.x() * this.texture.getWidth(), location.y() * this.texture.getHeight());
    }

    public void setGround(Ground ground) {
        this.ground = ground;
        this.texture = ground == Ground.GRASS ? Textures.GRASS_TEXTURE : Textures.WATER_TEXTURE;
    }

    public Ground getGround() {
        return ground;
    }

    public Location getLocation() {
        return location;
    }
}
