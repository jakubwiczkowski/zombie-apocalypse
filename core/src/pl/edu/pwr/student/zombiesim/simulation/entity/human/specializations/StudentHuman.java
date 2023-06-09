package pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations;

import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;

public class StudentHuman extends Human {

    public StudentHuman(Integer id, ZombieSimulation zombieSimulation) { super(id, zombieSimulation); }

    public StudentHuman(Integer id, ZombieSimulation zombieSimulation, double health, double strength, double regeneration, double agility, double intelligence) {
        super(id, zombieSimulation, health, strength, regeneration, agility, intelligence);
    }

    @Override
    public void specialAbility() {

    }
}
