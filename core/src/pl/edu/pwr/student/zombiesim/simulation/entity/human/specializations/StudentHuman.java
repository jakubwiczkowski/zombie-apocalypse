package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import com.badlogic.gdx.math.MathUtils;
import pl.edu.pwr.student.zombiesim.Settings;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;

/**
 * {@link StudentHuman} is a type of {@link Human} that
 * has maximum of (defined in {@link Settings#STUDENT_HUMAN_AGILITY_MAX}) agility, which makes them
 * invincible to most {@link Zombie} attacks.
 */
public class StudentHuman extends Human {

    public StudentHuman(Integer id) {
        super(id);

        setAgility(MathUtils.clamp(
                getAgility() * Settings.HUMAN_EXTRA_MULTIPLIER,
                Settings.STUDENT_HUMAN_AGILITY_MIN,
                Settings.STUDENT_HUMAN_AGILITY_MAX));
    }

    public StudentHuman(Integer id, Human parentOne, Human parentTwo) {
        super(id, parentOne, parentTwo);

        setAgility(MathUtils.clamp(
                getAgility() * Settings.HUMAN_EXTRA_MULTIPLIER,
                Settings.STUDENT_HUMAN_AGILITY_MIN,
                Settings.STUDENT_HUMAN_AGILITY_MAX));
    }

    @Override
    public String getName() {
        return "STU";
    }
}
