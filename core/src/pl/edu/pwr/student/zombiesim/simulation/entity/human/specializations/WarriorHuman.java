package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

public class WarriorHuman extends Human {

    public WarriorHuman(Integer id) {
        super(id);
    }

    public WarriorHuman(Integer id, double health, double strength, double agility) {
        super(id, health, strength, agility);
    }

    @Override
    public void specialAbility() {

    }
}
