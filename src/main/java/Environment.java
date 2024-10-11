
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;
/**
 * Single Responsibility Principle (SRP): Each class has a specific responsibility:
 * Environment manages environmental conditions and resources.

 */

public class Environment {
    private int carryingCapacity;
    private int currentPopulation;
    private double resourceAvailability;
    private Random random;
    private static final Logger log = LoggerFactory.getLogger(Environment.class);

    public Environment(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
        this.currentPopulation = 0;
        this.resourceAvailability = 100.0;
        this.random = new Random();
    }

    /**
     * Updates the resource availability in the environment based on the current population size.
     *
     * <p>This method adjusts the available resources depending on whether the population exceeds or
     * is below the environment's carrying capacity. If the population size exceeds the carrying capacity,
     * resources decrease. If the population is below the carrying capacity, resources increase.
     * The resource availability is also capped to ensure it doesn't become negative.</p>
     *
     * <p>The resource update logic is as follows:</p>
     * <ul>
     * <li>If the population exceeds the carrying capacity, resources decrease at a rate of 0.5 per extra individual.</li>
     * <li>If the population is below the carrying capacity, resources increase at a rate of 0.2 per individual below the capacity.</li>
     * </ul>
     *
     * <p>The method ensures that resource availability does not fall below zero.</p>
     *
     * @param populationSize the current population size of the environment
     */
    public void updateResources(int populationSize) {
        if (populationSize > carryingCapacity) {
            resourceAvailability -= (populationSize - carryingCapacity) * 0.5; // Resources decrease
        } else {
            resourceAvailability += (carryingCapacity - populationSize) * 0.2;
        }

        resourceAvailability = Math.max(resourceAvailability, 0);
    }
    /**
     * Adjusts the health of all living bunnies in the population based on the current population density.
     *
     * <p>This method calculates a health decline rate based on the ratio of the current population size
     * to the environment's carrying capacity. If the population exceeds the carrying capacity, the health
     * of each bunny will decline more rapidly. The health of each living bunny is reduced proportionally
     * to this decline rate. Bunnies that are not alive are not affected by this method.</p>
     *
     * <p>The decline rate is determined as follows:</p>
     * <ul>
     * <li>If the population size is equal to or less than the carrying capacity, the decline rate is 1.0.</li>
     * <li>If the population exceeds the carrying capacity, the decline rate increases proportionally to the ratio of population size to carrying capacity.</li>
     * </ul>
     *
     * @param bunnies an array of {@code Bunny} objects representing the population of bunnies
     */
    public void adjustHealth(Bunny[] bunnies) {
        double declineRate = Math.max((double) currentPopulation / carryingCapacity, 1.0);
        for (Bunny bunny : bunnies) {
            if (bunny.isAlive()) {
                bunny.setHealth(bunny.getHealth() - (declineRate * 5));
            }
        }
    }

    /**
     * Introduces a random environmental event that affects the carrying capacity,
     * resource availability, or the health of the bunnies in the simulation.
     *
     * The method randomly selects an event type based on predefined probabilities:
     * <ul>
     *     <li>If an event of type 0-4 occurs, a drought reduces the carrying capacity by 20%.</li>
     *     <li>If an event of type 5-14 occurs, a year of abundance increases resource availability by 30.</li>
     *     <li>If an event of type 15-24 occurs, a disease outbreak decreases the health of all bunnies by 20.</li>
     * </ul>
     *
     */
    public void introduceRandomEvent() {

        int eventType = random.nextInt(100);
        if (eventType < 5) {
            log.info("A drought has occurred! Decreasing carrying capacity.");
            carryingCapacity = (int) (carryingCapacity * 0.8);
        } else if (eventType < 15) {
            log.info("A year of abundance! Increasing resource availability.");
            resourceAvailability += 30;
        } else {
            log.info("A disease outbreak has occurred! Decreasing health of all bunnies.");

        }
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public void increasePopulation() {
        currentPopulation++;
    }

    public void decreasePopulation() {
        currentPopulation--;
    }

    public double getResourceAvailability() {
        return resourceAvailability;
    }
}
