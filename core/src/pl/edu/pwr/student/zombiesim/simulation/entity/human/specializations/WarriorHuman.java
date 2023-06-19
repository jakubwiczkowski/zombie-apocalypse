package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import com.badlogic.gdx.graphics.Texture;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;

/**
 * {@link WarriorHuman} is a type of {@link Human} that
 * is slightly stronger than others.
 */
public class WarriorHuman extends Human {

    public WarriorHuman(Integer id) {
        super(id);

        setStrength(getStrength() * 1.2);
    }

    public WarriorHuman(Integer id, Human parentOne, Human parentTwo) {
        super(id, parentOne, parentTwo);

        setStrength(getStrength() * 1.2);
    }

    @Override
    public String getName() {
        return "WAR";
    }

    @Override
    public Texture getTexture() {
        return Textures.HUMAN_WARRIOR_TEXTURE;
    }
}
