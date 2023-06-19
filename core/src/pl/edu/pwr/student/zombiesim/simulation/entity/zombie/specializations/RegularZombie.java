package pl.edu.pwr.student.zombiesim.simulation.entity.zombie.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;

/**
 * {@link RegularZombie} is a {@link Zombie} that has no
 * special attributes.
 */
public class RegularZombie extends Zombie {
    public RegularZombie(Integer id) {
        super(id);
    }

    public RegularZombie(Integer id, Human fromHuman) {
        super(id, fromHuman);
    }

    @Override
    public String getName() {
        return "REG";
    }
}
