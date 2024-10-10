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

    public Environment(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
        this.currentPopulation = 0;
        this.resourceAvailability = 100.0; // Initial resource level
        this.random = new Random();
    }

    public void updateResources(int populationSize) {
        if (populationSize > carryingCapacity) {
            resourceAvailability -= (populationSize - carryingCapacity) * 0.5; // Resources decrease
        } else {
            resourceAvailability += (carryingCapacity - populationSize) * 0.2; // Resources increase
        }

        resourceAvailability = Math.max(resourceAvailability, 0); // Resource cannot be negative
    }

    public void adjustHealth(Bunny[] bunnies) {
        if (currentPopulation > carryingCapacity) {
            double declineRate = (double) currentPopulation / carryingCapacity;
            for (Bunny bunny : bunnies) {
                if (bunny.isAlive()) {
                    bunny.setHealth(bunny.getHealth() - declineRate * 10); // Decline health based on population
                }
            }
        }
    }

    public void introduceRandomEvent() {
        // Randomly choose an environmental event
        int eventType = random.nextInt(5); // Assuming we have 5 types of events
        switch (eventType) {
            case 0:
                // Drought: Decrease carrying capacity
                System.out.println("A drought has occurred! Decreasing carrying capacity.");
                carryingCapacity = (int) (carryingCapacity * 0.8); // Reduce by 20%
                break;
            case 1:
                // Resource Abundance: Increase resources
                System.out.println("A year of abundance! Increasing resource availability.");
                resourceAvailability += 30; // Increase resources
                break;
            case 2:
                // Disease Outbreak: Decrease health of all bunnies
                System.out.println("A disease outbreak has occurred! Decreasing health of all bunnies.");
                // Health adjustment logic can be added here
                break;
            case 3:
                // Good Weather: Increase carrying capacity
                System.out.println("Good weather! Increasing carrying capacity.");
                carryingCapacity = (int) (carryingCapacity * 1.2); // Increase by 20%
                break;
            case 4:
                // Natural Disaster: Catastrophic event that affects health
                System.out.println("A natural disaster has struck! Health of all bunnies is severely impacted.");
                // Health adjustment logic can be added here
                break;
            default:
                break;
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
