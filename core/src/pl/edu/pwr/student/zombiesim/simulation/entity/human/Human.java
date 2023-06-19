package pl.edu.pwr.student.zombiesim.simulation.entity.human;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import org.apache.commons.math3.distribution.NormalDistribution;
import pl.edu.pwr.student.zombiesim.ZombieSimulation;
import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;
import pl.edu.pwr.student.zombiesim.simulation.entity.EntityInputListener;
import pl.edu.pwr.student.zombiesim.simulation.entity.Gender;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.RegularHuman;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.ShooterHuman;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.StudentHuman;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.specializations.WarriorHuman;
import pl.edu.pwr.student.zombiesim.simulation.entity.zombie.Zombie;
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
 * subtypes of {@link Human} extend this class.
 */
public abstract class Human extends AbstractEntity {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static final NormalDistribution HEALTH_DISTRIBUTION = new NormalDistribution(100, 20);
    private static final NormalDistribution STRENGTH_DISTRIBUTION = new NormalDistribution(30, 3);

    private final Gender gender = RANDOM.nextBoolean() ? Gender.MALE : Gender.FEMALE;
    private double maxHealth;
    private double health;
    private double strength;
    private double agility;

    private boolean isInfected = false;

    private final Texture texture = Textures.HUMAN_TEXTURE;

    private Location location = new Location(0, 0);

    private boolean hasInteracted = false;
    private int getMatedRound = 0;

    /**
     * Default constructor, that randomizes all
     * statistics upon creation.
     *
     * @param id unique id for this {@link Human}
     */
    public Human(Integer id) {
        super(id);

        this.health = MathUtils.clamp(HEALTH_DISTRIBUTION.sample(), 10, 200);
        this.maxHealth = this.health;

        this.strength = MathUtils.clamp(STRENGTH_DISTRIBUTION.sample(), 1, 100);
        this.agility = RANDOM.nextDouble(0.8);

        addListener(new EntityInputListener(this));

        ZombieSimulation.getInstance()
                .getDataCollector()
                .addHumanData(this);
    }

    /**
     * Constructor used when a {@link Human} is created
     * by being born from two other {@link Human}s.
     * Derived statistics are an average from both parents.
     *
     * @param id        unique id for this {@link Human}
     * @param parentOne first {@link Human} parent
     * @param parentTwo second {@link Human} parent
     */
    public Human(Integer id, Human parentOne, Human parentTwo) {
        super(id);

        this.health = (parentOne.getMaxHealth() + parentTwo.getMaxHealth()) / 2;
        this.maxHealth = this.health;

        this.strength = (parentOne.getStrength() + parentTwo.getStrength()) / 2;
        this.agility = (parentOne.getAgility() + parentTwo.getAgility()) / 2;

        addListener(new EntityInputListener(this));

        ZombieSimulation.getInstance()
                .getDataCollector()
                .addHumanData(this);
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

        batch.draw(getTexture(),
                this.location.x() * getTexture().getWidth(),
                this.location.y() * getTexture().getHeight());
    }

    /**
     * Method that returns the locations that this
     * {@link Human} can potentially attack.
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
     * {@link Human} can potentially move to.
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
    }

    @Override
    public void interact() {
        List<Location> nearbyLocations = getAttackLocations();

        SimulationArea simulationArea = ZombieSimulation.getInstance().getSimulationArea();

        List<Zombie> possibleTargets = nearbyLocations.stream()
                .map(location -> simulationArea.getZombieManager().getAtLocation(location))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        if (possibleTargets.isEmpty()) {
            if (ZombieSimulation.getInstance().getRound() - this.getMatedRound < 100)
                return;

            if (RANDOM.nextDouble() >= 0.01) // 1% szansy na rozmnozenie
                return;

            Optional<Human> possibleMate = getMoveLocations().stream()
                    .map(location -> simulationArea.getHumanManager().getAtLocation(location))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(human -> ZombieSimulation.getInstance().getRound() - human.getGetMatedRound() > 100)
                    .filter(human -> human.getGender() != this.gender)
                    .findFirst();

            if (possibleMate.isEmpty()) {
                return;
            }

            Human mate = possibleMate.get();

            List<Location> availableMoves = getMoveLocations().stream()
                    .filter(possibleLocation -> simulationArea.getGroundAt(possibleLocation).isPresent() &&
                            simulationArea.getGroundAt(possibleLocation).get() == Ground.GRASS)
                    .filter(possibleLocation -> simulationArea.getZombieManager().getAtLocation(possibleLocation).isEmpty())
                    .filter(possibleLocation -> simulationArea.getHumanManager().getAtLocation(possibleLocation).isEmpty())
                    .toList();

            if (availableMoves.isEmpty()) {
                return;
            }

            Location move = availableMoves.get(simulationArea.getRandom().nextInt(availableMoves.size()));

            Human newHuman;

            int humanType = RANDOM.nextInt(4);

            if (humanType == 0)
                newHuman = new RegularHuman(ZombieSimulation.getInstance().getSimulationArea().getHumanManager().getNextId(), this, mate);
            else if (humanType == 1)
                newHuman = new ShooterHuman(ZombieSimulation.getInstance().getSimulationArea().getHumanManager().getNextId(), this, mate);
            else if (humanType == 2)
                newHuman = new StudentHuman(ZombieSimulation.getInstance().getSimulationArea().getHumanManager().getNextId(), this, mate);
            else
                newHuman = new WarriorHuman(ZombieSimulation.getInstance().getSimulationArea().getHumanManager().getNextId(), this, mate);

            mate.setGetMatedRound(ZombieSimulation.getInstance().getRound());
            this.setGetMatedRound(ZombieSimulation.getInstance().getRound());
            newHuman.setGetMatedRound(ZombieSimulation.getInstance().getRound());

            ZombieSimulation.getInstance().getSimulationArea().getHumanManager().addEntity(newHuman);
            ZombieSimulation.getInstance().getGameStage().getEntityGroup().addActor(newHuman);

            newHuman.setLocation(move);

            ZombieSimulation.getInstance()
                    .getDataCollector()
                    .addHumansBorn(1);

            this.hasInteracted = true;

        } else {
            this.hasInteracted = true;

            Zombie target = possibleTargets.get(RANDOM.nextInt(possibleTargets.size()));

            if (RANDOM.nextDouble() < target.getAgility())
                return;

            target.setHealth(target.getHealth() - this.strength);
        }
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isInfected() {
        return isInfected;
    }

    public void setInfected(boolean infected) {
        isInfected = infected;
    }

    public double getAgility() {
        return agility;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
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

    public int getGetMatedRound() {
        return getMatedRound;
    }

    public void setGetMatedRound(int getMatedRound) {
        this.getMatedRound = getMatedRound;
    }
}
