package pl.edu.pwr.student.zombiesim.simulation.entity.zombie.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;

public class RegularZombie extends Zombie {
    public RegularZombie(Integer id) {
        super(id);
    }

    public RegularZombie(Integer id, Human fromHuman) {
        super(id, fromHuman);
    }

    public RegularZombie(Integer id, double health, double strength, double agility, double infectionRate) {
        super(id, health, strength,agility, infectionRate);
    }

    @Override
    public void specialAbility() {

    }
}
