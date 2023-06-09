package pl.edu.pwr.student.zombiesim.simulation.entity.zombie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;
import pl.edu.pwr.student.zombiesim.simulation.entity.EntityInputListener;
import pl.edu.pwr.student.zombiesim.simulation.entity.Gender;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;

public abstract class Zombie extends AbstractEntity {

    private final Gender gender = Gender.MALE;

    private double health;
    private double strength;
    private double regeneration;
    private double agility;
    private double infectionRate;

    private Texture texture = Textures.HUMAN_TEXTURE;

    private Location location = new Location(69, 69);

    public Zombie(Integer id, ZombieSimulation zombieSimulation) {
        super(id, zombieSimulation);
    }

    public Zombie(Integer id, ZombieSimulation zombieSimulation, double health, double strength, double regeneration, double agility, double infectionRate) {
        super(id, zombieSimulation);

        this.health = health;
        this.strength =  strength;
        this.regeneration = regeneration;
        this.agility = agility;
        this.infectionRate = infectionRate;

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
        super.draw(batch, parentAlpha);
    }

    public abstract void specialAbility();

}
