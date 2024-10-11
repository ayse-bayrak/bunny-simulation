import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {

    private Simulation simulation;

    @BeforeEach
    public void setup() {
        simulation = new Simulation(2, 20);
    }

    @Test
    public void testInitialBunnyCount() {
        List<Bunny> bunnies = simulation.getBunnies();
        assertEquals(2, bunnies.size(), "There should be 2 initial bunnies");
    }

    @Test
    public void testRunSimulationAddsBunnies() {
        simulation.runSimulation(5);
        List<Bunny> bunnies = simulation.getBunnies();
        assertTrue(bunnies.size() > 2, "There should be more than 2 bunnies after running the simulation"); // New bunnies should be born
    }

    @Test
    public void testRemoveDeadBunnies() {
        List<Bunny> bunnies = simulation.getBunnies();

        // Age all bunnies to make them die
        for (Bunny bunny : bunnies) {
            bunny.setAge(15);
        }

        simulation.removeDeadBunnies();

        bunnies = simulation.getBunnies();
        assertEquals(0, bunnies.size(), "All bunnies should be dead, and the list should be empty");
    }

    @Test
    public void testResourceUpdateAfterBunnyRemoval() {
        simulation.runSimulation(1);
        int initialResources = simulation.getBunnies().size();
        simulation.removeDeadBunnies();
        assertTrue(simulation.getBunnies().size() <= initialResources, "Population should decrease after removing dead bunnies");
    }
}
