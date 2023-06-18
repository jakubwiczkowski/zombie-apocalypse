package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

/**
 * {@link RegularHuman} is a {@link Human} that has no
 * special attributes.
 */
public class RegularHuman extends Human {
    public RegularHuman(Integer id) {
        super(id);
    }

    public RegularHuman(Integer id, double health, double strength, double agility) {
        super(id, health, strength, agility);
    }

    public RegularHuman(Integer id, Human parentOne, Human parentTwo) {
        super(id, parentOne, parentTwo);
    }

    @Override
    public String getName() {
        return "REG";
    }
}
