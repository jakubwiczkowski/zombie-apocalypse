package pl.edu.pwr.student.zombiesim.simulation.ui.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import pl.edu.pwr.student.zombiesim.simulation.map.Ground;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;

public class GroundActor extends Actor {

    private final Ground ground;
    private final Texture texture;

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
