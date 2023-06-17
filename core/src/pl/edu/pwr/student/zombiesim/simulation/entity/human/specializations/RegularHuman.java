package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

public class RegularHuman extends Human {
    public RegularHuman(Integer id) {
        super(id);
    }

    public RegularHuman(Integer id, double health, double strength, double regeneration, double agility, double intelligence) {
        super(id, health, strength, regeneration, agility, intelligence);
    }

    @Override
    public void specialAbility() {

    }
}
