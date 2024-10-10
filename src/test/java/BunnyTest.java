import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BunnyTest {
    private Bunny bunny;

    @BeforeEach
    void setUp() {
        bunny = new Bunny(1); // Initial generation 1
    }

    @Test
    void testInitialAttributes() {
        assertEquals(0, bunny.getAge(), "Initial age should be 0.");
        assertEquals(1, bunny.getGeneration(), "Initial generation should be 1.");
        assertTrue(bunny.isAlive(), "Bunny should be alive when created.");
        assertEquals(100.0, bunny.getHealth(), "Initial health should be 100.");
        assertTrue(bunny.getReproductionRate() >= 0.5 && bunny.getReproductionRate() <= 1.0,
                "Reproduction rate should be between 0.5 and 1.0.");
    }

    @Test
    void testAgeOneYear() {
        bunny.ageOneYear();
        assertEquals(1, bunny.getAge(), "Bunny should be 1 year old after aging.");
    }

    @Test
    void testReproduce() {
        bunny.ageOneYear(); // Age 1
        bunny.ageOneYear(); // Age 2 (reproduction age)

        Bunny offspring = bunny.reproduce();
        assertNotNull(offspring, "Bunny should produce an offspring at reproduction age.");
        assertEquals(2, offspring.getGeneration(), "Offspring should be from generation 2.");
    }

    @Test
    void testReproduceBeforeReproductionAge() {
        bunny.ageOneYear(); // Age 1
        Bunny offspring = bunny.reproduce();
        assertNull(offspring, "Bunny should not produce offspring before reproduction age.");
    }

    @Test
    void testDeathDueToMaxAge() {
        for (int i = 0; i < 7; i++) { // Age the bunny to the maximum age
            bunny.ageOneYear();
        }
        assertFalse(bunny.isAlive(), "Bunny should be dead after reaching maximum age.");
    }

    @Test
    void testDeathDueToLowHealth() {
        bunny.setHealth(10.0); // Set health below the threshold
        bunny.ageOneYear(); // Bunny ages, health check happens
        assertFalse(bunny.isAlive(), "Bunny should be dead due to low health.");
    }

    @Test
    void testHealthCheckAfterAging() {
        bunny.setHealth(50.0);
        bunny.ageOneYear();
        assertTrue(bunny.isAlive(), "Bunny should still be alive after aging with sufficient health.");
    }

    @Test
    void testMutationRateAfterReproduction() {
        bunny.ageOneYear(); // Age 1
        bunny.ageOneYear(); // Age 2 (reproduction age)

        Bunny offspring = bunny.reproduce();
        assertNotNull(offspring, "Bunny should produce an offspring.");

        // Check if the offspring has a mutated reproduction rate within the range
        double expectedMinMutationRate = Math.max(0, bunny.getMutationRate() - 0.05);
        double expectedMaxMutationRate = Math.min(1, bunny.getMutationRate() + 0.05);

        assertTrue(
                offspring.getMutationRate() >= expectedMinMutationRate &&
                        offspring.getMutationRate() <= expectedMaxMutationRate,
                "Offspring's mutation rate should be within ±0.05 of parent's mutation rate."
        );
    }

}
