import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.edu.pwr.student.zombiesim.simulation.map.Location;
import pl.edu.pwr.student.zombiesim.simulation.map.SimulationArea;

public class SimulationAreaTests {

    public static SimulationArea simulationArea;

    @BeforeAll
    public static void setUp() {
        simulationArea = new SimulationArea(30, 30);
    }

    @Test
    @DisplayName("Makes sure that map bounds are set correctly")
    public void mapBoundsTest() {
        Assertions.assertTrue(simulationArea.getGroundAt(new Location(5, 5)).isPresent());
        Assertions.assertTrue(simulationArea.getGroundAt(new Location(10, 15)).isPresent());
        Assertions.assertTrue(simulationArea.getGroundAt(new Location(29, 29)).isPresent());
        Assertions.assertTrue(simulationArea.getGroundAt(new Location(8, 7)).isPresent());
        Assertions.assertTrue(simulationArea.getGroundAt(new Location(2, 4)).isPresent());

        Assertions.assertTrue(simulationArea.getGroundAt(new Location(-1, -1)).isEmpty());
        Assertions.assertTrue(simulationArea.getGroundAt(new Location(30, 30)).isEmpty());
        Assertions.assertTrue(simulationArea.getGroundAt(new Location(-10, -15)).isEmpty());
        Assertions.assertTrue(simulationArea.getGroundAt(new Location(100, 100)).isEmpty());
        Assertions.assertTrue(simulationArea.getGroundAt(new Location(-5, 40)).isEmpty());
    }

}
