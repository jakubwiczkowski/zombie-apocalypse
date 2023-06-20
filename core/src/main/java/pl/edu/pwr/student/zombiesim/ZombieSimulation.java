package pl.edu.pwr.student.zombiesim;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.edu.pwr.student.zombiesim.simulation.data.DataCollector;
import pl.edu.pwr.student.zombiesim.simulation.map.Ground;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;
import pl.edu.pwr.student.zombiesim.simulation.map.SimulationArea;
import pl.edu.pwr.student.zombiesim.simulation.ui.Fonts;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;
import pl.edu.pwr.student.zombiesim.simulation.ui.entityLookup.EntityHoverStage;
import pl.edu.pwr.student.zombiesim.simulation.ui.game.GameStage;

import javax.xml.crypto.Data;
import java.io.IOException;

/**
 * Main class of the entire program. Stores all essential object for the simulation.
 * Extends {@link Game} from LibGDX library.
 * <p>
 * Is a singleton.
 */
public class ZombieSimulation extends Game {

    private static ZombieSimulation INSTANCE;

    private final DataCollector dataCollector = new DataCollector();

    private GameStage gameStage;
    private EntityHoverStage entityHoverStage;

    private SimulationArea simulationArea;

    private InputMultiplexer mainInputMultiplexer;

    private boolean fastForward = false;
    private int round = 0;

    @Override
    public void create() {
        INSTANCE = this;

        this.simulationArea = new SimulationArea(Settings.MAP_SIZE_WIDTH, Settings.MAP_SIZE_HEIGHT);
        this.simulationArea.populate(Settings.HUMAN_COUNT, Settings.ZOMBIE_COUNT, this.simulationArea.getGroundLocations());

        this.gameStage = new GameStage();
        this.gameStage.getMainInputProcessor().updateCamera();

        this.entityHoverStage = new EntityHoverStage();

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
        try {
            dataCollector.writeData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.gameStage.dispose();

        this.entityHoverStage.dispose();

        Textures.HUMAN_MALE_TEXTURE.dispose();
        Textures.HUMAN_FEMALE_TEXTURE.dispose();
        Textures.HUMAN_SHOOTER_TEXTURE.dispose();
        Textures.HUMAN_WARRIOR_TEXTURE.dispose();

        Textures.WATER_TEXTURE.dispose();
        Textures.GRASS_TEXTURE.dispose();
        Textures.GRASS_ALT_TEXTURE.dispose();

        Textures.PLACEHOLDER_TEXTURE.dispose();

        Fonts.MAIN_FONT.dispose();
    }

    /**
     * Method that returns a {@link SimulationArea} object used to
     * store all information about the map - entities,
     * {@link Ground} data, etc.
     *
     * @return {@link SimulationArea} object
     */
    public SimulationArea getSimulationArea() {
        return simulationArea;
    }

    /**
     * Method that returns a {@link GameStage} object - main
     * object of the simulation that takes care of rendering the map
     * to the screen.
     *
     * @return {@link GameStage} object
     */
    public GameStage getGameStage() {
        return gameStage;
    }

    /**
     * Method used to advance to the next round of the simulation.
     *
     * @see SimulationArea
     */
    public void nextRound() {
        if (this.simulationArea.getHumanManager().getEntities().isEmpty() ||
                this.simulationArea.getZombieManager().getEntities().isEmpty()) {
            dataCollector.setFinalHumanCount(
                    this.simulationArea.getHumanManager().getEntities().size());

            dataCollector.setFinalZombieCount(
                    this.simulationArea.getZombieManager().getEntities().size());

            int waterCount = 0;
            int groundCount = 0;

            for (int x = 0; x < this.simulationArea.getSimulationSizeX(); x++) {
                for (int y = 0; y < this.simulationArea.getSimulationSizeY(); y++) {
                    Ground ground = this.simulationArea.getGroundAt(new Location(x, y)).get();

                    if (ground == Ground.GRASS) groundCount++;
                    if (ground == Ground.WATER) waterCount++;
                }
            }

            dataCollector.setGroundCount(groundCount);
            dataCollector.setWaterCount(waterCount);

            try {
                dataCollector.writeData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return;
        }

        round++;

        this.simulationArea.act();

        dataCollector.setRoundsSimulated(round);
    }

    /**
     * Method that returns the current round of the simulation
     * (or how many rounds passed since simulation's initialization).
     *
     * @return current round
     */
    public int getRound() {
        return round;
    }

    /**
     * Method used to enable and disable the fast-forward option for skipping rounds.
     *
     * @param fastForward true - turns on fast-forward, false - turns of fast-forward
     */
    public void setFastForward(boolean fastForward) {
        this.fastForward = fastForward;
    }

    /**
     * Method used to check if fast-forward is enabled.
     *
     * @return true if enabled, false if disabled
     */
    public boolean isFastForward() {
        return fastForward;
    }

    /**
     * Method that returns simulation's {@link DataCollector} object.
     *
     * @return {@link DataCollector} instance
     */
    public DataCollector getDataCollector() {
        return dataCollector;
    }

    /**
     * Method that returns the instance of {@link ZombieSimulation} singleton.
     *
     * @return instance of the {@link ZombieSimulation} singleton
     */
    public static ZombieSimulation getInstance() {
        return INSTANCE;
    }
}
