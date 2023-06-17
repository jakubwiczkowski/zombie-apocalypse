package pl.edu.pwr.student.zombiesim.simulation.entity.human;

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

public abstract class Human extends AbstractEntity {

    private final Gender gender = Gender.MALE;

    private double health;
    private double strength;
    private double regeneration;
    private double agility;
    private double intelligence;

    private final Texture texture = Textures.HUMAN_TEXTURE;

    private Location location = new Location(0, 0);

    public Human(Integer id) {
        super(id);

        addListener(new EntityInputListener(this));
    }

    public Human(Integer id, double health, double strength, double regeneration, double agility, double intelligence) {
        this(id);

        this.health = health;
        this.strength = strength;
        this.regeneration = regeneration;
        this.agility = agility;
        this.intelligence = intelligence;
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

    public double getHealth() {
        return health;
    }

    public double getAgility() {
        return agility;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public double getRegeneration() {
        return regeneration;
    }

    public double getStrength() {
        return strength;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public abstract void specialAbility();
}
