package pl.edu.pwr.student.zombiesim.simulation.entity.zombie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;
import pl.edu.pwr.student.zombiesim.simulation.entity.EntityInputListener;
import pl.edu.pwr.student.zombiesim.simulation.entity.Gender;
import pl.edu.pwr.student.zombiesim.simulation.map.Ground;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;
import pl.edu.pwr.student.zombiesim.simulation.map.SimulationArea;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Zombie extends AbstractEntity {

    private final Gender gender = Gender.MALE;

    private double health;
    private double strength;
    private double agility;
    private double infectionRate;

    private final Texture texture = Textures.HUMAN_TEXTURE;

    private Location location = new Location(0, 0);

    public Zombie(Integer id) {
        super(id);

        addListener(new EntityInputListener(this));
    }

    public Zombie(Integer id, double health, double strength, double agility, double infectionRate) {
        this(id);

        this.health = health;
        this.strength =  strength;
        this.agility = agility;
        this.infectionRate = infectionRate;
    }

    @Override
    public Texture getTexture() {
        return this.texture;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setBounds(this.location.x() * getTexture().getWidth(),
                this.location.y() * getTexture().getHeight(),
                texture.getWidth(),
                texture.getHeight());

        batch.draw(this.texture,
                this.location.x() * getTexture().getWidth(),
                this.location.y() * getTexture().getHeight());
    }

    @Override
    public void move() {
        List<Location> possibleMoves = new ArrayList<>();

        possibleMoves.add(location.add(-1, 1));
        possibleMoves.add(location.add(0, 1));
        possibleMoves.add(location.add(1, 1));

        possibleMoves.add(location.add(-1, 0));
        possibleMoves.add(location.add(1, 0));

        possibleMoves.add(location.add(-1, -1));
        possibleMoves.add(location.add(0, -1));
        possibleMoves.add(location.add(1, -1));

        SimulationArea simulationArea = ZombieSimulation.getInstance().getSimulationArea();

        List<Location> availableMoves = possibleMoves.stream()
                .filter(possibleLocation -> simulationArea.getGroundAt(possibleLocation).isPresent() &&
                        simulationArea.getGroundAt(possibleLocation).get() == Ground.GRASS)
                .filter(possibleLocation -> simulationArea.getZombieManager().getAtLocation(possibleLocation).isEmpty())
                .filter(possibleLocation -> simulationArea.getHumanManager().getAtLocation(possibleLocation).isEmpty())
                .toList();

        Location move = availableMoves.get(simulationArea.getRandom().nextInt(availableMoves.size()));

        setLocation(move);
    }

    public abstract void specialAbility();

    public double getStrength() {
        return strength;
    }

    public double getAgility() {
        return agility;
    }

    public double getHealth() {
        return health;
    }

    public double getInfectionRate() {
        return infectionRate;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
