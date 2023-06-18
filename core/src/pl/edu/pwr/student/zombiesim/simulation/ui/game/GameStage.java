package pl.edu.pwr.student.zombiesim.simulation.ui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;
import pl.edu.pwr.student.zombiesim.simulation.input.MainInputProcessor;
import pl.edu.pwr.student.zombiesim.simulation.map.Ground;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;
import pl.edu.pwr.student.zombiesim.simulation.ui.entityLookup.EntityHoverStage;

/**
 * Object that represent a screen with a view of the map.
 * <p>
 * Handles all the input processing via {@link InputMultiplexer}.
 * Manages displayed entities.
 */
public class GameStage extends Stage {

    private final InputMultiplexer gameAreaInputMultiplexer;
    private final MainInputProcessor mainInputProcessor;

    private final float worldWidth;
    private final float worldHeight;

    private final boolean isHorizontal;

    private final OrthographicCamera camera = (OrthographicCamera) this.getCamera();

    private final Group mapTilesGroup = new Group();
    private final Group entityGroup = new Group();

    private AbstractEntity selectedEntity = null;

    public GameStage() {
        super(new ScreenViewport());

        ZombieSimulation game = ZombieSimulation.getInstance();

        this.mainInputProcessor = new MainInputProcessor(this);

        this.gameAreaInputMultiplexer = new InputMultiplexer(this, this.mainInputProcessor);

        this.worldWidth = game.getSimulationArea().getSimulationSizeX() * Textures.GRASS_TEXTURE.getWidth();
        this.worldHeight = game.getSimulationArea().getSimulationSizeY() * Textures.GRASS_TEXTURE.getHeight();

        this.isHorizontal = this.worldHeight < this.worldWidth;

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        if (this.isHorizontal) {
            this.camera.setToOrtho(false, worldWidth, worldWidth * (height / width));
            this.camera.zoom = this.worldHeight / this.camera.viewportHeight;
        } else {
            this.camera.setToOrtho(false, worldHeight, worldHeight * (height / width));
            this.camera.zoom = this.worldWidth / this.camera.viewportWidth;
        }

        this.camera.position.set(this.worldWidth / 2, this.worldHeight / 2, 0);

        getBatch().setProjectionMatrix(this.camera.combined);

        getRoot().addActor(this.mapTilesGroup);
        getRoot().addActor(this.entityGroup);

        for (int x = 0; x < game.getSimulationArea().getSimulationSizeX(); x++) {
            for (int y = 0; y < game.getSimulationArea().getSimulationSizeY(); y++) {
                Ground ground = game.getSimulationArea().getGroundAt(new Location(x, y)).get();

                this.mapTilesGroup.addActor(new GroundActor(ground, x, y));
            }
        }

        game.getSimulationArea()
                .getHumanManager()
                .getEntities()
                .forEach(this.entityGroup::addActor);

        game.getSimulationArea()
                .getZombieManager()
                .getEntities()
                .forEach(this.entityGroup::addActor);
    }

    /**
     * Method that returns a {@link InputMultiplexer} responsible for
     * managing all input processors for the main screen.
     *
     * @return {@link InputMultiplexer} managing all processors for {@link GameStage}
     */
    public InputMultiplexer getGameAreaInputMultiplexer() {
        return gameAreaInputMultiplexer;
    }

    /**
     * Method that returns {@link MainInputProcessor} responsible for
     * movement of the camera, keyboard input and mouse input.
     *
     * @return {@link MainInputProcessor} responsible for camera / keyboard input
     */
    public MainInputProcessor getMainInputProcessor() {
        return mainInputProcessor;
    }

    /**
     * Returns the world width in pixels
     *
     * @return world width
     */
    public float getWorldWidth() {
        return worldWidth;
    }

    /**
     * Returns the world height in pixels
     *
     * @return world height
     */
    public float getWorldHeight() {
        return worldHeight;
    }

    /**
     * Method that checks if maps dimensions make the map vertical
     * or horizontal.
     *
     * @return true if world is horizontal, false otherwise
     */
    public boolean isHorizontal() {
        return isHorizontal;
    }

    /**
     * Returns the currently selected {@link AbstractEntity}
     * by the user.
     *
     * @see EntityHoverStage
     *
     * @return selected {@link AbstractEntity}
     */
    public AbstractEntity getSelectedEntity() {
        return selectedEntity;
    }

    /**
     * Method that returns a {@link Group} of {@link AbstractEntity}
     * that are to be rendered on screen.
     *
     * @return {@link Group} consisting of {@link AbstractEntity}
     */
    public Group getEntityGroup() {
        return entityGroup;
    }

    /**
     * Sets an {@link AbstractEntity} to currently selected.
     *
     * @param selectedEntity {@link AbstractEntity} to select
     */
    public void setSelectedEntity(AbstractEntity selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

}
