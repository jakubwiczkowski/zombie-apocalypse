package pl.edu.pwr.student.zombiesim.simulation.entity.zombie;

import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.manager.AbstractEntityManager;

public class ZombieManager extends AbstractEntityManager<Zombie> {

    @Override
    public void removeEntity(Zombie zombie) {
        super.removeEntity(zombie);

        ZombieSimulation.getInstance().getGameStage().getEntityGroup().removeActor(zombie);
    }
}
