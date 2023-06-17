package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

public class StudentHuman extends Human {

    public StudentHuman(Integer id) {
        super(id);
    }

    public StudentHuman(Integer id, double health, double strength, double agility) {
        super(id, health, strength, agility);
    }

    @Override
    public void specialAbility() {

    }
}
