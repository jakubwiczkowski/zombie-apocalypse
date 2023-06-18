package pl.edu.pwr.student.zombiesim.simulation.ui.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.map.Ground;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;

public class GroundInputListener extends InputListener {

    private final GroundActor groundActor;

    public GroundInputListener(GroundActor groundActor) {
        this.groundActor = groundActor;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (!ZombieSimulation.getInstance().getGameStage().getMainInputProcessor().isHoldingAlt())
            return false;

        groundActor.setGround(groundActor.getGround() == Ground.GRASS ? Ground.WATER : Ground.GRASS);
        ZombieSimulation.getInstance()
                .getSimulationArea()
                .setGroundAt(new Location(groundActor.getLocation().x(), groundActor.getLocation().y()), groundActor.getGround());
        return false;
    }
}
