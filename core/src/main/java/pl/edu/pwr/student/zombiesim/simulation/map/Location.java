package pl.edu.pwr.student.zombiesim.simulation.map;

/**
 * Object that represents location in 2D space
 *
 * @param x coordinate on the X axis
 * @param y coordinate on the Y axis
 */
public record Location(int x, int y) {

    /**
     * Method used to add values relative to the {@link Location} object.
     *
     * @param x value to add to the X axis
     * @param y value to add to the Y axis
     * @return  new {@link Location} object with modified coordinates
     */
    public Location add(int x, int y) {
        return new Location(this.x() + x, this.y() + y);
    }

}
