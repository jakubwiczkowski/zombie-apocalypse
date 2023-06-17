package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

public class StudentHuman extends Human {

    public StudentHuman(Integer id) {
        super(id);
    }

    public StudentHuman(Integer id, double health, double strength, double regeneration, double agility, double intelligence) {
        super(id, health, strength, regeneration, agility, intelligence);
    }

    @Override
    public void specialAbility() {

    }
}
