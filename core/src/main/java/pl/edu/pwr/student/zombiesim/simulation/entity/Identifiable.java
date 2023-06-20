package pl.edu.pwr.student.zombiesim.simulation.entity;

import java.io.Serializable;

/**
 * Interface that represents an object that has their own unique identifier.
 *
 * @param <I> a key type that could be used as a unique identifier for an object
 */
public interface Identifiable<I extends Serializable> {

    /**
     * Method that returns the identifier.
     *
     * @return unique identifier
     */
    I getIdentifier();

}
