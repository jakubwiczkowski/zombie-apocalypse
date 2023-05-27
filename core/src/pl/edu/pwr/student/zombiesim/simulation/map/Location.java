package pl.edu.pwr.student.zombiesim.simulation.map;

public record Location(int x, int y) {

    public int distanceSquared(Location to) {
        return (x - to.x)*(x - to.x) + (y - to.y)*(y - to.y);
    }

}
