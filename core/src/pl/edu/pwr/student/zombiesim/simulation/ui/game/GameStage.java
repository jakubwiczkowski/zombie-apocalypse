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

public class GameStage extends Stage {

    private final ZombieSimulation game;

    private final InputMultiplexer gameAreaInputMultiplexer;
    private final MainInputProcessor mainInputProcessor;

    private final float worldWidth;
    private final float worldHeight;

    private final boolean isHorizontal;

    private final OrthographicCamera camera = (OrthographicCamera) this.getCamera();

    private final Group mapTilesGroup = new Group();
    private final Group entityGroup = new Group();

    private AbstractEntity selectedEntity = null;

    public GameStage(ZombieSimulation game) {
        super(new ScreenViewport());

        this.game = game;

        this.mainInputProcessor = new MainInputProcessor(this);

        this.gameAreaInputMultiplexer = new InputMultiplexer(this, this.mainInputProcessor);

        this.worldWidth = this.game.getSimulationArea().getSimulationSizeX() * Textures.GRASS_TEXTURE.getWidth();
        this.worldHeight = this.game.getSimulationArea().getSimulationSizeY() * Textures.GRASS_TEXTURE.getHeight();

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

        for (int x = 0; x < this.game.getSimulationArea().getSimulationSizeX(); x++) {
            for (int y = 0; y < this.game.getSimulationArea().getSimulationSizeY(); y++) {
                Ground ground = this.game.getSimulationArea().getGroundAt(new Location(x, y)).get();

                this.mapTilesGroup.addActor(new GroundActor(ground, x, y));
            }
        }

        this.game.getSimulationArea()
                .getHumanManager()
                .getEntities()
                .forEach(this.entityGroup::addActor);

        this.game.getSimulationArea()
                .getZombieManager()
                .getEntities()
                .forEach(this.entityGroup::addActor);
    }

    public InputMultiplexer getGameAreaInputMultiplexer() {
        return gameAreaInputMultiplexer;
    }

    public MainInputProcessor getMainInputProcessor() {
        return mainInputProcessor;
    }

    public float getWorldWidth() {
        return worldWidth;
    }

    public float getWorldHeight() {
        return worldHeight;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public AbstractEntity getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(AbstractEntity selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

}
