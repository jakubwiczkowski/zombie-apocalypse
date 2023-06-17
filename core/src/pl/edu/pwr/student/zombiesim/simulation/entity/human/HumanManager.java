package pl.edu.pwr.student.zombiesim.simulation.entity.human;

import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.manager.AbstractEntityManager;

public class HumanManager extends AbstractEntityManager<Human> {

    @Override
    public void removeEntity(Human human) {
        super.removeEntity(human);

        ZombieSimulation.getInstance().getGameStage().getEntityGroup().removeActor(human);
    }
}
