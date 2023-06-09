package pl.edu.pwr.student.zombiesim.simulation.entity.zombie.specializations;

import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;

public class ChubbyZombie extends Zombie {
    public ChubbyZombie(Integer id, ZombieSimulation zombieSimulation) {
        super(id, zombieSimulation);
    }

    public ChubbyZombie(Integer id, ZombieSimulation zombieSimulation, double health, double strength, double regeneration, double agility, double infectionRate) {
        super(id, zombieSimulation, health, strength, regeneration, agility, infectionRate);
    }

    @Override
    public void specialAbility() {

    }
}
