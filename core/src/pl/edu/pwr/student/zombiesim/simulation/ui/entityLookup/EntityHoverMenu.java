package pl.edu.pwr.student.zombiesim.simulation.ui.entityLookup;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;

public class EntityHoverMenu extends Group {

    private final AbstractEntity entity;

    public EntityHoverMenu(AbstractEntity entity) {
        this.entity = entity;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

}
