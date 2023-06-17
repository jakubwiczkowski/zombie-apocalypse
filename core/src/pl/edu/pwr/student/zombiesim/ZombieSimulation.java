package pl.edu.pwr.student.zombiesim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.edu.pwr.student.zombiesim.simulation.map.SimulationArea;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;
import pl.edu.pwr.student.zombiesim.simulation.ui.entityLookup.EntityHoverStage;
import pl.edu.pwr.student.zombiesim.simulation.ui.game.GameStage;

public class ZombieSimulation extends Game {

    private static ZombieSimulation INSTANCE;

    private GameStage gameStage;
    private EntityHoverStage entityHoverStage;

    private SimulationArea simulationArea;

    private InputMultiplexer mainInputMultiplexer;

    private boolean fastForward = false;
    private int round = 0;

    @Override
    public void create() {
        INSTANCE = this;

        this.simulationArea = new SimulationArea(50, 50);

        this.gameStage = new GameStage(this);
        this.gameStage.getMainInputProcessor().updateCamera();

        this.entityHoverStage = new EntityHoverStage(this);

        this.mainInputMultiplexer = new InputMultiplexer(
                this.gameStage.getGameAreaInputMultiplexer(),
                this.entityHoverStage.getInputMultiplexer());

        Gdx.input.setInputProcessor(this.mainInputMultiplexer);
    }

    @Override
    public void render() {
        this.gameStage.getCamera().update();
        this.entityHoverStage.getCamera().update();
        float delta = Gdx.graphics.getDeltaTime();

        ScreenUtils.clear(1, 1, 1, 1);

        if (fastForward)
            nextRound();

        this.gameStage.act(delta);
        this.gameStage.draw();

        this.entityHoverStage.act(delta);
        this.entityHoverStage.draw();
    }

    @Override
    public void dispose() {
        this.gameStage.getBatch().dispose();
        this.gameStage.dispose();

        this.entityHoverStage.getBatch().dispose();
        this.entityHoverStage.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public SimulationArea getSimulationArea() {
        return simulationArea;
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public EntityHoverStage getEntityHoverStage() {
        return entityHoverStage;
    }

    public void nextRound() {
        round++;

        this.simulationArea.act();
    }

    public int getRound() {
        return round;
    }

    public boolean isFastForward() {
        return fastForward;
    }

    public void setFastForward(boolean fastForward) {
        this.fastForward = fastForward;
    }

    public static ZombieSimulation getInstance() {
        return INSTANCE;
    }
}
