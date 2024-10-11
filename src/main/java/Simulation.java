
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Single Responsibility Principle (SRP): Each class has a specific responsibility:
 * Environment manages environmental conditions and resources.
 * Simulation orchestrates the overall simulation process.
 * Open/Closed Principle (OCP): The system is designed to allow extension (e.g.,
 * adding new environmental effects without modifying existing code.
 * Dependency Inversion Principle (DIP): The Simulation class should not depend directly on the Bunny or Environment classes.
 * Instead, it should depend on their interfaces, allowing new types of bunnies or environmental
 * management strategies to be added without modifying the existing application.
 */
public class Simulation {
    private Environment environment;
    private List<Bunny> bunnies;
    private double reproductionThreshold = 0.5;
    private static final Logger log = LoggerFactory.getLogger(Simulation.class);

    /**
     * Initializes a new simulation with a specified number of initial bunnies and a carrying capacity for the environment.
     *
     * <p>This constructor creates a new {@code Environment} with the given carrying capacity and populates it
     * with the specified number of initial bunnies. Each bunny is initialized as part of generation 0.
     * The environment's population count is also increased accordingly to reflect the number of bunnies added.</p>
     *
     * @param initialBunnies the number of bunnies to start the simulation with
     * @param carryingCapacity the maximum number of bunnies the environment can support
     */
    public Simulation(int initialBunnies, int carryingCapacity) {
        environment = new Environment(carryingCapacity);
        bunnies = new ArrayList<>();

        for (int i = 0; i < initialBunnies; i++) {
            bunnies.add(new Bunny(0));
            environment.increasePopulation();
        }
    }

    /**
     * Runs the simulation for a specified number of years, processing events and updating the state of bunnies and the environment each year.
     *
     * <p>This method simulates the life cycle of bunnies over a given number of years. For each year, it introduces
     * a random event, allows bunnies to age and reproduce based on resource availability, updates the environment's
     * resource state, and adjusts the health of the bunnies. Dead bunnies are removed from the population at the
     * end of each year, and statistics for the year are printed.</p>
     *
     * @param years the number of years to run the simulation
     */

    public void runSimulation(int years) {
        for (int year = 0; year < years; year++) {
            log.info("Year: " + year);
            environment.introduceRandomEvent();

            List<Bunny> newBunnies = new ArrayList<>();

            for (Bunny bunny : bunnies) {
                bunny.ageOneYear();
                if (bunny.isAlive()) {
                    environment.increasePopulation();

                    if (bunny.canReproduce() && environment.getResourceAvailability() > 10) {
                        Bunny offspring = bunny.reproduce();
                        if (offspring != null) {
                            newBunnies.add(offspring);
                        }
                    }
                }
            }

            bunnies.addAll(newBunnies);
            environment.updateResources(bunnies.size());
            environment.adjustHealth(bunnies.toArray(new Bunny[0]));
            removeDeadBunnies();
            printYearStats(year);
        }
    }

    /**
     * Removes dead bunnies from the population based on their age.
     *
     * <p>This method iterates through the list of bunnies and removes any bunny that is
     * 10 years old or older, as it is assumed that bunnies die at or beyond this age.</p>
     */
    public void removeDeadBunnies() {
        bunnies.removeIf(bunny -> bunny.getAge() >= 10);
    }

    private void printYearStats(int year) {
        log.info("Year: " + year);
        log.info("Population: " + bunnies.size());
        log.info("Current Resource Level: " + environment.getResourceAvailability());
        log.info("-------------");
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(10, 50);
        simulation.runSimulation(20);
    }

    public List<Bunny> getBunnies() {
        return bunnies;
    }

    /**
     * Simulates natural selection by allowing bunnies to reproduce based on their fitness levels.
     *
     * <p>This method iterates through the current population of bunnies and calculates
     * each bunny's fitness. If a bunny's fitness exceeds a specified threshold, it has a chance
     * to reproduce, with the likelihood of reproduction being proportional to its fitness.
     * Offspring are then added to the population.</p>
     */
    public void naturalSelection() {
        List<Bunny> newBunnies = new ArrayList<>();

        for (Bunny bunny : bunnies) {
            double fitness = bunny.calculateFitness(environment);
            if (fitness > reproductionThreshold && new Random().nextDouble() < fitness) {
                Bunny offspring = bunny.reproduce();
                if (offspring != null) newBunnies.add(offspring);
            }
        }
        bunnies.addAll(newBunnies);
    }

}
