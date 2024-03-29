package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import com.badlogic.gdx.graphics.Texture;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link ShooterHuman} is a type of {@link Human} that has
 * much bigger attack range than all the other entities.
 */
public class ShooterHuman extends Human {
    public ShooterHuman(Integer id) {
        super(id);
    }

    public ShooterHuman(Integer id, Human parentOne, Human parentTwo) {
        super(id, parentOne, parentTwo);
    }

    @Override
    public List<Location> getAttackLocations() {
        List<Location> nearbyLocations = new ArrayList<>();

        nearbyLocations.add(getLocation().add(-1, 1));
        nearbyLocations.add(getLocation().add(0, 1));
        nearbyLocations.add(getLocation().add(1, 1));

        nearbyLocations.add(getLocation().add(-1, 0));
        nearbyLocations.add(getLocation().add(1, 0));

        nearbyLocations.add(getLocation().add(-1, -1));
        nearbyLocations.add(getLocation().add(0, -1));
        nearbyLocations.add(getLocation().add(1, -1));


        nearbyLocations.add(getLocation().add(-1, 2));
        nearbyLocations.add(getLocation().add(0, 2));
        nearbyLocations.add(getLocation().add(1, 2));

        nearbyLocations.add(getLocation().add(-2, 1));
        nearbyLocations.add(getLocation().add(-2, 0));
        nearbyLocations.add(getLocation().add(-2, 1));

        nearbyLocations.add(getLocation().add(2, 1));
        nearbyLocations.add(getLocation().add(2, 0));
        nearbyLocations.add(getLocation().add(2, 1));

        nearbyLocations.add(getLocation().add(-1, -2));
        nearbyLocations.add(getLocation().add(0, -2));
        nearbyLocations.add(getLocation().add(1, -2));

        return nearbyLocations;
    }

    @Override
    public String getName() {
        return "SHO";
    }

    @Override
    public Texture getTexture() {
        return Textures.HUMAN_SHOOTER_TEXTURE;
    }
}
