package pl.edu.pwr.student.zombiesim.simulation.entity.zombie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import org.apache.commons.math3.distribution.NormalDistribution;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;
import pl.edu.pwr.student.zombiesim.simulation.entity.EntityInputListener;
import pl.edu.pwr.student.zombiesim.simulation.entity.Gender;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.map.Ground;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;
import pl.edu.pwr.student.zombiesim.simulation.map.SimulationArea;
import pl.edu.pwr.student.zombiesim.simulation.ui.Textures;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * One of 2 base entities of the simulation. All
 * subtypes of {@link Zombie} extend this class.
 */
public abstract class Zombie extends AbstractEntity {

    protected static final Random RANDOM = new Random(System.currentTimeMillis());

    private static final NormalDistribution HEALTH_DISTRIBUTION = new NormalDistribution(100, 20);
    private static final NormalDistribution STRENGTH_DISTRIBUTION = new NormalDistribution(30, 3);

    private final Gender gender = RANDOM.nextBoolean() ? Gender.MALE : Gender.FEMALE;

    private double maxHealth;
    private double health;
    private double strength;
    private double agility;
    private double infectionRate;

    private final Texture texture = Textures.HUMAN_TEXTURE;

    private Location location = new Location(0, 0);

    private boolean hasInteracted = false;

    /**
     * Default constructor, that randomizes all
     * statistics upon creation.
     *
     * @param id unique id for this {@link Zombie}
     */
    public Zombie(Integer id) {
        super(id);

        this.health = MathUtils.clamp(HEALTH_DISTRIBUTION.sample(), 10, 200);
        this.maxHealth = this.health;

        this.strength = MathUtils.clamp(STRENGTH_DISTRIBUTION.sample(), 1, 100);
        this.agility = RANDOM.nextDouble(0.8);
        this.infectionRate = RANDOM.nextDouble(0.9, 1.0);

        addListener(new EntityInputListener(this));

        ZombieSimulation.getInstance()
                .getDataCollector()
                .addZombieData(this);
    }

    /**
     * Constructor that creates a {@link Zombie} from
     * infected {@link Human}. All {@link Human} statistics
     * are inherited.
     *
     * @param id        unique id for this {@link Zombie}
     * @param fromHuman {@link Human} object that statistics are being taken from
     */
    public Zombie(Integer id, Human fromHuman) {
        super(id);

        this.health = fromHuman.getMaxHealth();
        this.maxHealth = this.health;

        this.strength = fromHuman.getStrength();
        this.agility = fromHuman.getAgility();
        this.infectionRate = RANDOM.nextDouble();

        this.location = fromHuman.getLocation();

        addListener(new EntityInputListener(this));

        ZombieSimulation.getInstance()
                .getDataCollector()
                .addZombieData(this);
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

    /**
     * Method that returns the locations that this
     * {@link Zombie} can potentially attack.
     *
     * @return all available attack locations
     */
    public List<Location> getAttackLocations() {
        List<Location> nearbyLocations = new ArrayList<>();

        nearbyLocations.add(location.add(-1, 1));
        nearbyLocations.add(location.add(0, 1));
        nearbyLocations.add(location.add(1, 1));

        nearbyLocations.add(location.add(-1, 0));
        nearbyLocations.add(location.add(1, 0));

        nearbyLocations.add(location.add(-1, -1));
        nearbyLocations.add(location.add(0, -1));
        nearbyLocations.add(location.add(1, -1));

        return nearbyLocations;
    }

    /**
     * Method that returns the locations that this
     * {@link Zombie} can potentially move to.
     *
     * @return all available move locations
     */
    public List<Location> getMoveLocations() {
        List<Location> nearbyLocations = new ArrayList<>();

        nearbyLocations.add(location.add(-1, 1));
        nearbyLocations.add(location.add(0, 1));
        nearbyLocations.add(location.add(1, 1));

        nearbyLocations.add(location.add(-1, 0));
        nearbyLocations.add(location.add(1, 0));

        nearbyLocations.add(location.add(-1, -1));
        nearbyLocations.add(location.add(0, -1));
        nearbyLocations.add(location.add(1, -1));

        return nearbyLocations;
    }

    @Override
    public void move() {
        if (this.hasInteracted)
            return;

        List<Location> possibleMoves = getMoveLocations();

        SimulationArea simulationArea = ZombieSimulation.getInstance().getSimulationArea();

        List<Location> availableMoves = possibleMoves.stream()
                .filter(possibleLocation -> simulationArea.getGroundAt(possibleLocation).isPresent() &&
                        simulationArea.getGroundAt(possibleLocation).get() == Ground.GRASS)
                .filter(possibleLocation -> simulationArea.getZombieManager().getAtLocation(possibleLocation).isEmpty())
                .filter(possibleLocation -> simulationArea.getHumanManager().getAtLocation(possibleLocation).isEmpty())
                .toList();

        if (availableMoves.isEmpty())
            return;

        Location move = availableMoves.get(simulationArea.getRandom().nextInt(availableMoves.size()));

        setLocation(move);

        this.hasInteracted = false;
    }

    @Override
    public void interact() {
        if (this.health < this.maxHealth * 0.1)
            return;

        List<Location> nearbyLocations = getAttackLocations();

        SimulationArea simulationArea = ZombieSimulation.getInstance().getSimulationArea();

        List<Human> possibleTargets = nearbyLocations.stream()
                .map(location -> simulationArea.getHumanManager().getAtLocation(location))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        if (possibleTargets.isEmpty())
            return;

        this.hasInteracted = true;

        Human target = possibleTargets.get(RANDOM.nextInt(possibleTargets.size()));

        if (RANDOM.nextDouble() < target.getAgility())
            return;

        target.setHealth(target.getHealth() - this.strength);

        if (target.getHealth() > 0)
            return;

        target.setInfected(RANDOM.nextDouble() < this.infectionRate);
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getAgility() {
        return agility;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public Gender getGender() {
        return gender;
    }

    public double getInfectionRate() {
        return infectionRate;
    }

    public boolean hasInteracted() {
        return hasInteracted;
    }

    public void setHasInteracted(boolean hasInteracted) {
        this.hasInteracted = hasInteracted;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
