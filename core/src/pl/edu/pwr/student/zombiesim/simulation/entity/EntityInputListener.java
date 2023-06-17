package pl.edu.pwr.student.zombiesim.simulation.entity;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;

public class EntityInputListener extends InputListener {

    private final AbstractEntity entity;

    public EntityInputListener(AbstractEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        ZombieSimulation.getInstance().getGameStage().setSelectedEntity(entity);
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (ZombieSimulation.getInstance().getGameStage().getSelectedEntity() != this.entity)
            return;

        ZombieSimulation.getInstance().getGameStage().setSelectedEntity(null);
    }

}
