package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

public class WarriorHuman extends Human {

    public WarriorHuman(Integer id) {
        super(id);
    }

    public WarriorHuman(Integer id, Human parentOne, Human parentTwo) {
        super(id, parentOne, parentTwo);
    }

    public WarriorHuman(Integer id, double health, double strength, double regeneration, double agility, double intelligence) {
        super(id, health, strength, regeneration, agility, intelligence);
    }

    @Override
    public void specialAbility() {

    }
}
