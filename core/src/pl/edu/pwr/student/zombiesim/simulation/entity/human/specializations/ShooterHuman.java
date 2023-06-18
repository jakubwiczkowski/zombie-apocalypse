package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

public class ShooterHuman extends Human {
    public ShooterHuman(Integer id) {
        super(id);
    }

    public ShooterHuman(Integer id, Human parentOne, Human parentTwo) {
        super(id, parentOne, parentTwo);
    }

    public ShooterHuman(Integer id, double health, double strength, double agility) {
        super(id, health, strength, agility);
    }

    @Override
    public void specialAbility() {

    }
}
