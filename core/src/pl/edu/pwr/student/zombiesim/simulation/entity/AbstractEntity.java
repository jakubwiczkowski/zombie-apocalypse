package pl.edu.pwr.student.zombiesim.simulation.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;

public abstract class AbstractEntity extends Actor implements Identifiable<Integer> {

    private final ZombieSimulation zombieSimulation;
    private final int id;

    public AbstractEntity(Integer id, ZombieSimulation zombieSimulation) {
        this.id = id;
        this.zombieSimulation = zombieSimulation;
    }

    @Override
    public Integer getIdentifier() {
        return this.id;
    }

    public abstract Texture getTexture();

    public abstract Location getLocation();

    public ZombieSimulation getZombieSimulation() {
        return zombieSimulation;
    }
}
