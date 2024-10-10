import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnvironmentTest {
    private Environment environment;

    @BeforeEach
    public void setUp() {
        // Create a new Environment instance before each test
        environment = new Environment(100); // Setting carrying capacity to 100
    }

    @Test
    public void testUpdateResourcesPopulationExceedsCarryingCapacity() {
        environment.increasePopulation(); // Simulate population increase
        environment.increasePopulation();
        environment.updateResources(102); // Exceed carrying capacity
        
        assertEquals(99.0, environment.getResourceAvailability(), 0.01);
    }

    @Test
    public void testUpdateResourcesPopulationUnderCarryingCapacity() {
        environment.updateResources(80); // Below carrying capacity
        
        assertEquals(104.0, environment.getResourceAvailability(), 0.01); // Expecting 104 after increase
    }

    @Test
    public void testIntroduceRandomEvent() {
        // You may want to test various events here
        environment.introduceRandomEvent();
        // Depending on the random event, you may need assertions to check changes
        // For example, checking if carrying capacity or resource availability changes
    }

    // Additional tests can be added for other methods
}
