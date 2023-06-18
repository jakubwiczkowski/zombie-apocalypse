package pl.edu.pwr.student.zombiesim.simulation.entity.manager;

import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Base for an object that stores entities which unique key is an integer
 *
 * @param <E> entity class extending AbstractEntity
 */
public abstract class AbstractEntityManager<E extends AbstractEntity> {

    private final Map<Integer, E> entityMap = new HashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    /**
     * Method that returns and then increments stored id integer.
     * Used during creation of a new Entity.
     *
     * @return next available id
     */
    public Integer getNextId() {
        return this.currentId.getAndIncrement();
    }

    /**
     * Method that returns {@link AbstractEntity} object if stored.
     *
     * @param id entity's identifier
     * @return   entity object if exists, otherwise null (enclosed in an Optional)
     */
    public Optional<E> getEntity(Integer id) {
        return Optional.ofNullable(this.entityMap.getOrDefault(id, null));
    }

    /**
     * Method that returns {@link AbstractEntity} that is on specific location on the map.
     *
     * @param location location given with {@link Location} object
     * @return         {@link AbstractEntity} if exits, otherwise null (enclosed in {@link Optional<E>})
     */
    public Optional<E> getAtLocation(Location location) {
        return getEntities().stream()
                .filter(entity ->
                        entity.getLocation().x() == location.x() && entity.getLocation().y() == location.y())
                .findFirst();
    }

    /**
     * Method that returns a copy of {@link HashMap} values with {@link AbstractEntity} objects stored.
     *
     * @return {@link List<E>} of all stored {@link AbstractEntity} objects
     */
    public List<E> getEntities() {
        return List.copyOf(this.entityMap.values());
    }

    /**
     * Adds given {@link AbstractEntity} to manager's storage.
     *
     * @param entity {@link AbstractEntity} object to add
     */
    public void addEntity(E entity) {
        if (entityMap.containsKey(entity.getIdentifier()))
            return;

        this.entityMap.put(entity.getIdentifier(), entity);
    }

    /**
     * Removes {@link AbstractEntity} object from manager's storage
     * by providing it's unique identifier.
     *
     * @param id unique identifier of {@link AbstractEntity}
     */
    public void removeEntity(Integer id) {
        this.entityMap.remove(id);
    }

    /**
     * Removes {@link AbstractEntity} object from manager's storage.
     *
     * @param entity {@link AbstractEntity} object
     */
    public void removeEntity(E entity) {
        removeEntity(entity.getIdentifier());
    }

}
