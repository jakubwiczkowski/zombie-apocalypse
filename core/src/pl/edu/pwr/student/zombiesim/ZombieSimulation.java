package pl.edu.pwr.student.zombiesim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import pl.edu.pwr.student.zombiesim.simulation.Textures;
import pl.edu.pwr.student.zombiesim.simulation.input.MainInputProcessor;
import pl.edu.pwr.student.zombiesim.simulation.map.Ground;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;
import pl.edu.pwr.student.zombiesim.simulation.map.SimulationArea;

public class ZombieSimulation extends ApplicationAdapter {

    private Stage mainStage;

    private SimulationArea simulationArea;

    private OrthographicCamera camera;
    private Batch batch;

    private float worldWidth;
    private float worldHeight;

    private final MainInputProcessor mainInputProcessor = new MainInputProcessor(this);
    private InputMultiplexer inputMultiplexer;

    @Override
    public void create() {
        this.simulationArea = new SimulationArea(100, 50);

        this.mainStage = new Stage(new ScreenViewport());
        this.inputMultiplexer = new InputMultiplexer(this.mainStage, this.mainInputProcessor);

        Gdx.input.setInputProcessor(this.inputMultiplexer);

        this.worldWidth = this.simulationArea.getSimulationSizeX() * Textures.GRASS_TEXTURE.getWidth();
        this.worldHeight = this.simulationArea.getSimulationSizeY() * Textures.GRASS_TEXTURE.getHeight();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        this.camera = (OrthographicCamera) this.mainStage.getCamera();
        this.camera.setToOrtho(false, worldWidth, worldWidth * (height / width));
        this.camera.position.set(width / 2, height / 2, 0);

        this.mainInputProcessor.updateCamera();

        System.out.println(this.camera.viewportWidth);
        System.out.println(this.camera.viewportHeight);

        this.batch = this.mainStage.getBatch();

        this.simulationArea.getHumanManager()
                .getEntities()
                .forEach(human -> {
                    this.mainStage.addActor(human);
                });
    }

    @Override
    public void render() {
        this.camera.update();
        float delta = Gdx.graphics.getDeltaTime();

        ScreenUtils.clear(1, 1, 1, 1);

        batch.setProjectionMatrix(camera.combined);

        this.mainStage.act(delta);

        for (int x = 0; x < this.simulationArea.getSimulationSizeX(); x++) {
            for (int y = 0; y < this.simulationArea.getSimulationSizeY(); y++) {
                Ground ground = this.simulationArea.getGroundAt(new Location(x, y));
                Texture texture = ground == Ground.GRASS ? Textures.GRASS_TEXTURE : Textures.WATER_TEXTURE;

                this.batch.begin();
                this.batch.draw(texture, x * texture.getWidth(), y * texture.getHeight());
                this.batch.end();
            }
        }

        this.mainStage.getActors().forEach(actor -> actor.draw(this.batch, 0.0f));
    }

    @Override
    public void dispose() {
        batch.dispose();
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

    public float getWorldHeight() {
        return worldHeight;
    }

    public float getWorldWidth() {
        return worldWidth;
    }

    public Stage getMainStage() {
        return mainStage;
    }
}
