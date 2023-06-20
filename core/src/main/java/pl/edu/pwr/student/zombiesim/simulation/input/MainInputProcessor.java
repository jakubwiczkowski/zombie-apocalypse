package pl.edu.pwr.student.zombiesim.simulation.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.ui.game.GameStage;

/**
 * Class that takes care of all input management
 * fo the main screen.
 */
public class MainInputProcessor implements InputProcessor {

    private final GameStage gameStage;

    private boolean isHoldingAlt = false;

    private boolean isDragging = false;
    private int dragStartX = 0;
    private int dragStartY = 0;

    public MainInputProcessor(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    /**
     * Manages the start of fast-forward when the space bar
     * is let go and single round skipping.
     *
     * @see ZombieSimulation
     */
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ALT_LEFT)
            isHoldingAlt = true;

        if (keycode == Input.Keys.SPACE)
            ZombieSimulation.getInstance().setFastForward(true);

        if (keycode == Input.Keys.ENTER)
            ZombieSimulation.getInstance().setFastForward(!ZombieSimulation.getInstance().isFastForward());

        if (keycode == Input.Keys.TAB)
            ZombieSimulation.getInstance().nextRound();

        return true;
    }

    /**
     * Manages the end of fast-forward when the space bar
     * is let go.
     *
     * @see ZombieSimulation
     */
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ALT_LEFT)
            isHoldingAlt = false;

        if (keycode == Input.Keys.SPACE)
            ZombieSimulation.getInstance().setFastForward(false);

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Specifies when the drag move of the map starts and
     * saves the initial cursor position.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.isDragging = true;
        this.dragStartX = screenX;
        this.dragStartY = screenY;
        return false;
    }

    /**
     * Specifies when the drag move of the map ends.
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        this.isDragging = false;
        return false;
    }

    /**
     * Moves the map around by holding left-click and dragging.
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (!this.isDragging)
            return true;

        int dx = this.dragStartX - screenX;
        int dy = this.dragStartY - screenY;

        OrthographicCamera camera = (OrthographicCamera) this.gameStage.getCamera();

        camera.translate(dx, -dy, 0);

        this.dragStartX = screenX;
        this.dragStartY = screenY;

        updateCamera();

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Manages the zoom that changes while scrolling.
     */
    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (!(this.gameStage.getCamera() instanceof OrthographicCamera camera))
            return false;

        if (amountY == 1.0)
            camera.zoom += 0.02;
        else
            camera.zoom -= 0.02;

        float max = (this.gameStage.isHorizontal() ?
                this.gameStage.getWorldHeight() / camera.viewportHeight :
                this.gameStage.getWorldWidth() / camera.viewportWidth);

        camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, max);

        updateCamera();
        return true;
    }

    /**
     * Method that updates the main camera.
     * Takes care of zoom and positioning.
     */
    public void updateCamera() {
        if (!(this.gameStage.getCamera() instanceof OrthographicCamera camera))
            return;

        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        camera.position.x = MathUtils.clamp(camera.position.x,
                effectiveViewportWidth / 2f,
                this.gameStage.getWorldWidth() - effectiveViewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y,
                effectiveViewportHeight / 2f,
                this.gameStage.getWorldHeight() - effectiveViewportHeight / 2f);
    }

    public boolean isHoldingAlt() {
        return isHoldingAlt;
    }
}
