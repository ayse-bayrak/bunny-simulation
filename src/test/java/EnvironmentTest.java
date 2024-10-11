import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnvironmentTest {
    private Environment environment;

    @BeforeEach
    public void setUp() {
        environment = new Environment(100);
    }

    @Test
    public void testUpdateResourcesPopulationExceedsCarryingCapacity() {
        environment.increasePopulation();
        environment.increasePopulation();
        environment.updateResources(102);
        
        assertEquals(99.0, environment.getResourceAvailability(), 0.01);
    }

    @Test
    public void testUpdateResourcesPopulationUnderCarryingCapacity() {
        environment.updateResources(80);
        
        assertEquals(104.0, environment.getResourceAvailability(), 0.01);
    }

    @Test
    public void testIntroduceRandomEvent() {
        environment.introduceRandomEvent();
    }

}
