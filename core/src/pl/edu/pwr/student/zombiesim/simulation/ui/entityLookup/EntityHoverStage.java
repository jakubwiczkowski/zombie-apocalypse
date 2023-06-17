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
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;
import pl.edu.pwr.student.zombiesim.simulation.ui.Fonts;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class EntityHoverStage extends Stage {

    private static final float SIDEBAR_WIDTH = 200;
    private static final float SIDEBAR_HEIGHT = 320;

    private static final float ICON_OFFSET_TOP = 32 * 3 + 10;
    private static final float TEXT_HEIGHT = 20;

    private final ZombieSimulation zombieSimulation;

    private final ShapeRenderer renderer = new ShapeRenderer();

    private final InputMultiplexer inputMultiplexer = new InputMultiplexer(this);

    private final NumberFormat oneDecimal = new DecimalFormat("#0.0");
    private final NumberFormat percentage = new DecimalFormat("#0.0");

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

        getBatch().draw(abstractEntity.getTexture(),
                (SIDEBAR_WIDTH / 2) - (abstractEntity.getTexture().getWidth() * 3) / 2,
                SIDEBAR_HEIGHT - ICON_OFFSET_TOP,
                abstractEntity.getTexture().getWidth() * 3,
                abstractEntity.getTexture().getHeight() * 3);

        if (abstractEntity instanceof Zombie zombie) {
            Fonts.MAIN_FONT.draw(getBatch(), "ZOM: " + zombie.getIdentifier().toString(), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP);

            Fonts.MAIN_FONT.draw(getBatch(), "HP:  " + oneDecimal.format(zombie.getHealth()), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP - TEXT_HEIGHT);
            Fonts.MAIN_FONT.draw(getBatch(), "STR: " + oneDecimal.format(zombie.getStrength()), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP - TEXT_HEIGHT * 2);
            Fonts.MAIN_FONT.draw(getBatch(), "AG:  " + percentage.format(zombie.getAgility() * 100), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP - TEXT_HEIGHT * 3);
            Fonts.MAIN_FONT.draw(getBatch(), "IR:  " + percentage.format(zombie.getInfectionRate() * 100), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP - TEXT_HEIGHT * 4);
            Fonts.MAIN_FONT.draw(getBatch(), "GEN: " + zombie.getGender().toString(), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP - TEXT_HEIGHT * 5);
        } else if (abstractEntity instanceof Human human) {
            Fonts.MAIN_FONT.draw(getBatch(), "HUM: " + human.getIdentifier().toString(), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP);

            Fonts.MAIN_FONT.draw(getBatch(), "HP:  " + oneDecimal.format(human.getHealth()), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP - TEXT_HEIGHT);
            Fonts.MAIN_FONT.draw(getBatch(), "STR: " + oneDecimal.format(human.getStrength()), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP - TEXT_HEIGHT * 2);
            Fonts.MAIN_FONT.draw(getBatch(), "AG:  " + percentage.format(human.getAgility() * 100), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP - TEXT_HEIGHT * 3);
            Fonts.MAIN_FONT.draw(getBatch(), "GEN: " + human.getGender().toString(), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP - TEXT_HEIGHT * 4);
        }

        Fonts.MAIN_FONT.draw(getBatch(), "RND: " + ZombieSimulation.getInstance().getRound(), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP - TEXT_HEIGHT * 8);
        Fonts.MAIN_FONT.draw(getBatch(), "Z/H: " +
                ZombieSimulation.getInstance().getSimulationArea().getZombieManager().getEntities().size()
                + " / " +
                ZombieSimulation.getInstance().getSimulationArea().getHumanManager().getEntities().size(), 5, SIDEBAR_HEIGHT - ICON_OFFSET_TOP - TEXT_HEIGHT * 9);

        getBatch().end();
    }
}
