package pl.edu.pwr.student.zombiesim.simulation.entity;

import java.io.Serializable;

public interface Identifiable<I extends Serializable> {

    I getIdentifier();

}
