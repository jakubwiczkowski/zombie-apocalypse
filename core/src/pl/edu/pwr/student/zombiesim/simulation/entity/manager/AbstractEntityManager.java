package pl.edu.pwr.student.zombiesim.simulation.entity.manager;

import pl.edu.pwr.student.zombiesim.simulation.entity.AbstractEntity;

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
