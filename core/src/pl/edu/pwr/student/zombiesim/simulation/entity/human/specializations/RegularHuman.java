package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

public class RegularHuman extends Human {
    public RegularHuman(Integer id) {
        super(id);
    }

    public RegularHuman(Integer id, double health, double strength, double regeneration, double agility, double intelligence) {
        super(id, health, strength, regeneration, agility, intelligence);
    }

    public RegularHuman(Integer id, Human parentOne, Human parentTwo) {
        super(id, parentOne, parentTwo);
    }

    @Override
    public void specialAbility() {

    }
}
