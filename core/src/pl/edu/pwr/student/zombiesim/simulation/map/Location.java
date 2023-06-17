package pl.edu.pwr.student.zombiesim.simulation.map;

public record Location(int x, int y) {

    public Location add(int x, int y) {
        return new Location(this.x() + x, this.y() + y);
    }

    public int distanceSquared(Location to) {
        return (x - to.x)*(x - to.x) + (y - to.y)*(y - to.y);
    }

}
