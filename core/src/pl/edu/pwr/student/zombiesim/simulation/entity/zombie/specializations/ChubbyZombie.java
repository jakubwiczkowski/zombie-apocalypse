package pl.edu.pwr.student.zombiesim.simulation.entity.zombie.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;

public class ChubbyZombie extends Zombie {
    public ChubbyZombie(Integer id) {
        super(id);
    }

    public ChubbyZombie(Integer id, double health, double strength, double agility, double infectionRate) {
        super(id, health, strength, agility, infectionRate);
    }

    @Override
    public void specialAbility() {

    }
}
