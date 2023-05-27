package pl.edu.pwr.student.zombiesim.simulation.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;

public class MainInputProcessor implements InputProcessor {

    private final ZombieSimulation zombieSimulation;

    public MainInputProcessor(ZombieSimulation zombieSimulation) {
        this.zombieSimulation = zombieSimulation;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT)
            this.zombieSimulation.getMainStage().getCamera().translate(-60, 0, 0);

        if (keycode == Input.Keys.RIGHT)
            this.zombieSimulation.getMainStage().getCamera().translate(60, 0, 0);

        if (keycode == Input.Keys.DOWN)
            this.zombieSimulation.getMainStage().getCamera().translate(0, -60, 0);

        if (keycode == Input.Keys.UP)
            this.zombieSimulation.getMainStage().getCamera().translate(0, 60, 0);

        updateCamera();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (!(this.zombieSimulation.getMainStage().getCamera() instanceof OrthographicCamera camera))
            return false;

        if (amountY == 1.0)
            camera.zoom += 0.02;
        else
            camera.zoom -= 0.02;

        camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, this.zombieSimulation.getWorldHeight() / camera.viewportHeight);

        updateCamera();
        return true;
    }

    public void updateCamera() {
        if (!(this.zombieSimulation.getMainStage().getCamera() instanceof OrthographicCamera camera))
            return;

        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        camera.position.x = MathUtils.clamp(camera.position.x,
                effectiveViewportWidth / 2f,
                this.zombieSimulation.getWorldWidth() - effectiveViewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y,
                effectiveViewportHeight / 2f,
                this.zombieSimulation.getWorldHeight() - effectiveViewportHeight / 2f);
    }
}
