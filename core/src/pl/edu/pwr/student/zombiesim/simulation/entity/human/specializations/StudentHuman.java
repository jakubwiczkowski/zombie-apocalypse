package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import com.badlogic.gdx.math.MathUtils;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;

/**
 * {@link StudentHuman} is a type of {@link Human} that
 * has maximum of 95% agility, which makes them
 * invincible to most {@link Zombie} attacks.
 */
public class StudentHuman extends Human {

    public StudentHuman(Integer id) {
        super(id);

        setAgility(MathUtils.clamp(getAgility() * 1.2, 0, 0.95));
    }

    public StudentHuman(Integer id, Human parentOne, Human parentTwo) {
        super(id, parentOne, parentTwo);

        setAgility(MathUtils.clamp(getAgility() * 1.2, 0, 0.95));
    }

    @Override
    public String getName() {
        return "STU";
    }
}
