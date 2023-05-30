package pl.edu.pwr.student.zombiesim.simulation.ui.entityLookup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;
import pl.edu.pwr.student.zombiesim.simulation.ui.Fonts;

public class EntityHoverStage extends Stage {

    private static final float SIDEBAR_WIDTH = 200;
    private static final float SIDEBAR_HEIGHT = 320;

    private final ZombieSimulation zombieSimulation;

    private ShapeRenderer renderer = new ShapeRenderer();

    private InputMultiplexer inputMultiplexer = new InputMultiplexer(this);

    public EntityHoverStage(ZombieSimulation zombieSimulation) {
        super(new ScreenViewport());

        this.zombieSimulation = zombieSimulation;

        OrthographicCamera camera = (OrthographicCamera) getCamera();

        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        camera.position.set(screenWidth / 2, (screenHeight / 2) - (screenHeight - SIDEBAR_HEIGHT), 0);

        getViewport().setScreenSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        getViewport().setScreenPosition(0, Gdx.graphics.getHeight());
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    @Override
    public void draw() {
        super.draw();

        if (this.zombieSimulation.getGameStage().getSelectedEntity() == null)
            return;

        renderer.setProjectionMatrix(getBatch().getProjectionMatrix());
        renderer.setTransformMatrix(getBatch().getTransformMatrix());

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);
        renderer.rect(0, 0, SIDEBAR_WIDTH, SIDEBAR_HEIGHT);
        renderer.end();

        AbstractEntity abstractEntity = this.zombieSimulation.getGameStage().getSelectedEntity();

        getBatch().begin();

        getBatch().draw(abstractEntity.getTexture(), 84, 280);

        Fonts.MAIN_FONT.draw(getBatch(), "Ludzik ID: " + abstractEntity.getIdentifier().toString(), 40, 280);

        getBatch().end();
    }
}
