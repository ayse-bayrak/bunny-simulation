import java.util.ArrayList;
import java.util.List;
/**
 * Single Responsibility Principle (SRP): Each class has a specific responsibility:
 * Environment manages environmental conditions and resources.
 * Simulation orchestrates the overall simulation process.
 * Open/Closed Principle (OCP): The system is designed to allow extension (e.g.,
 * adding new environmental effects without modifying existing code.
 * Dependency Inversion Principle (DIP): The simulation can interact with various
 * implementations of bunnies or environments if necessary, making it flexible.

 */
public class Simulation {
    private Environment environment;
    private List<Bunny> bunnies;

    public Simulation(int initialBunnies, int carryingCapacity) {
        environment = new Environment(carryingCapacity);
        bunnies = new ArrayList<>();

        for (int i = 0; i < initialBunnies; i++) {
            bunnies.add(new Bunny(0));
            environment.increasePopulation();
        }
    }

    public void runSimulation(int years) {
        for (int year = 0; year < years; year++) {
            System.out.println("Year: " + year);
            environment.introduceRandomEvent(); // Introduce a random event

            List<Bunny> newBunnies = new ArrayList<>();

            for (Bunny bunny : bunnies) {
                bunny.ageOneYear();
                if (bunny.isAlive()) {
                    environment.increasePopulation(); // Update environment population

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


    public void removeDeadBunnies() {
        bunnies.removeIf(bunny -> bunny.getAge() >= 10); // assuming a bunny dies at age 10 or older
    }

    private void printYearStats(int year) {
        System.out.println("Year: " + year);
        System.out.println("Population: " + bunnies.size());
        System.out.println("Current Resource Level: " + environment.getResourceAvailability());
        System.out.println("-------------");
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(10, 50); // 10 initial bunnies, carrying capacity of 50
        simulation.runSimulation(20); // Simulate for 20 years
    }

    // to get the list of bunnies
    public List<Bunny> getBunnies() {
        return bunnies; //Return the list of bunnies
    }
}
