package pl.edu.pwr.student.zombiesim.simulation.entity.human;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.EntityInputListener;
import pl.edu.pwr.student.zombiesim.simulation.ui.Fonts;
import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;
import pl.edu.pwr.student.zombiesim.simulation.entity.Gender;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;

public abstract class Human extends AbstractEntity {

    private final Gender gender = Gender.MALE;

    private double health;
    private double strength;
    private double regeneration;
    private double agility;
    private double intelligence;

    private Texture texture = Textures.HUMAN_TEXTURE;

    private Location location = new Location(0, 0);

    public Human(Integer id, ZombieSimulation zombieSimulation) {
        super(id, zombieSimulation);

        addListener(new EntityInputListener(this));
    }

    public Human(Integer id, ZombieSimulation zombieSimulation, double health, double strength, double regeneration, double agility, double intelligence) {
        super(id, zombieSimulation);

        this.health = health;
        this.strength =  strength;
        this.regeneration = regeneration;
        this.agility = agility;
        this.intelligence = intelligence;

        addListener(new EntityInputListener(this));
    }

    @Override
    public Texture getTexture() {
        return this.texture;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setBounds(this.location.x() * getTexture().getWidth(),
                this.location.y() * getTexture().getHeight(),
                texture.getWidth(),
                texture.getHeight());

        batch.draw(this.texture,
                this.location.x() * getTexture().getWidth(),
                this.location.y() * getTexture().getHeight());
    }

    public abstract void specialAbility();
}
