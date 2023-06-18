package pl.edu.pwr.student.zombiesim.simulation.entity;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.ui.entityLookup.EntityHoverStage;

/**
 * Object that takes care of setting the selected {@link AbstractEntity}
 * for {@link EntityHoverStage}.
 */
public class EntityInputListener extends InputListener {

    private final AbstractEntity entity;

    public EntityInputListener(AbstractEntity entity) {
        this.entity = entity;
    }

    /**
     * Sets the {@link AbstractEntity} as selected
     */
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        ZombieSimulation.getInstance().getGameStage().setSelectedEntity(entity);
        return true;
    }

    /**
     * Deselects the {@link AbstractEntity}
     */
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (ZombieSimulation.getInstance().getGameStage().getSelectedEntity() != this.entity)
            return;

        ZombieSimulation.getInstance().getGameStage().setSelectedEntity(null);
    }

}
