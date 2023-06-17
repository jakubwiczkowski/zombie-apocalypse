package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

public class ShooterHuman extends Human {
    public ShooterHuman(Integer id) {
        super(id);
    }

    public ShooterHuman(Integer id, Human parentOne, Human parentTwo) {
        super(id, parentOne, parentTwo);
    }

    public ShooterHuman(Integer id, double health, double strength, double regeneration, double agility, double intelligence) {
        super(id, health, strength, regeneration, agility, intelligence);
    }

    @Override
    public void specialAbility() {

    }
}
