package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

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
}
