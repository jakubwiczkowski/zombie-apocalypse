package pl.edu.pwr.student.zombiesim.simulation.entity.zombie.specializations;

import com.badlogic.gdx.graphics.Texture;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;
import pl.edu.pwr.student.zombiesim.simulation.map.SimulationArea;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;

import java.util.List;
import java.util.Optional;

/**
 * {@link KamikazeZombie} is a {@link Zombie} that
 * sacrifices itself to have a 100% chance of {@link Human}
 * infection.
 */
public class KamikazeZombie extends Zombie {
    public KamikazeZombie(Integer id) {
        super(id);
    }

    public KamikazeZombie(Integer id, Human fromHuman) {
        super(id, fromHuman);
    }

    @Override
    public void interact() {
        List<Location> nearbyLocations = getAttackLocations();

        SimulationArea simulationArea = ZombieSimulation.getInstance().getSimulationArea();

        List<Human> possibleTargets = nearbyLocations.stream()
                .map(location -> simulationArea.getHumanManager().getAtLocation(location))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        if (possibleTargets.isEmpty())
            return;

        setHasInteracted(true);

        Human target = possibleTargets.get(RANDOM.nextInt(possibleTargets.size()));

        if (RANDOM.nextDouble() < target.getAgility())
            return;

        target.setHealth(0);
        setHealth(0);

        target.setInfected(true);
    }

    @Override
    public String getName() {
        return "KAM";
    }
    @Override
    public Texture getTexture() {
        return Textures.ZOMBIE_KAMIKAZE_TEXTURE;
    }
}
