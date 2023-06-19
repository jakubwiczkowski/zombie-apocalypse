package pl.edu.pwr.student.zombiesim.simulation.entity.zombie.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;

/**
 * {@link ChubbyZombie} is a {@link Zombie} that has a slightly
 * more health than the other {@link Zombie}s.
 */
public class ChubbyZombie extends Zombie {
    public ChubbyZombie(Integer id) {
        super(id);

        setMaxHealth(getMaxHealth() * 1.2);
        setHealth(getMaxHealth());
    }

    public ChubbyZombie(Integer id, Human fromHuman) {
        super(id, fromHuman);

        setMaxHealth(getMaxHealth() * 1.2);
        setHealth(getMaxHealth());
    }

    @Override
    public String getName() {
        return "CHU";
    }
}
