package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

public class RegularHuman extends Human {
    public RegularHuman(Integer id) {
        super(id);
    }

    public RegularHuman(Integer id, double health, double strength, double agility) {
        super(id, health, strength, agility);
    }

    @Override
    public void specialAbility() {

    }
}
