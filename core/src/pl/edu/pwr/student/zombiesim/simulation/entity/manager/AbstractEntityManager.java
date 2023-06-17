package pl.edu.pwr.student.zombiesim.simulation.entity.manager;

import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;
import pl.edu.pwr.student.zombiesim.simulation.entity.human.Human;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractEntityManager<E extends AbstractEntity> {

    private final Map<Integer, E> entityMap = new HashMap<>();
    private AtomicInteger currentId = new AtomicInteger(0);

    public Integer getNextId() {
        return this.currentId.getAndIncrement();
    }

    public Optional<E> getEntity(Integer id) {
        return Optional.ofNullable(this.entityMap.getOrDefault(id, null));
    }

    public Optional<E> getAtLocation(Location location) {
        return getEntities().stream()
                .filter(entity ->
                        entity.getLocation().x() == location.x() && entity.getLocation().y() == location.y())
                .findFirst();
    }

    public List<E> getEntities() {
        return List.copyOf(this.entityMap.values());
    }

    public void addEntity(E entity) {
        if (entityMap.containsKey(entity.getIdentifier()))
            return;

        this.entityMap.put(entity.getIdentifier(), entity);
    }

    public void removeEntity(Integer id) {
        this.entityMap.remove(id);
    }

    public void removeEntity(E entity) {
        removeEntity(entity.getIdentifier());
    }

}
