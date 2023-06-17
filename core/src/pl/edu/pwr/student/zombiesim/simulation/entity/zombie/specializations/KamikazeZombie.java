package pl.edu.pwr.student.zombiesim.simulation.entity.zombie.specializations;

import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;

public class KamikazeZombie extends Zombie {
    public KamikazeZombie(Integer id) {
        super(id);
    }

    public KamikazeZombie(Integer id, double health, double strength, double regeneration, double agility, double infectionRate) {
        super(id, health, strength, regeneration, agility, infectionRate);
    }

    @Override
    public void specialAbility() {

    }
}
