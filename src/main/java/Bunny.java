import java.util.Random;
/**
 * Single Responsibility Principle (SRP): Each class has a specific responsibility:
 * Bunny handles bunny attributes and behaviors.
 * Liskov Substitution Principle (LSP): The Bunny class can be extended to create specialized bunny types if needed,
 * ensuring that the system remains consistent.
 * Interface Segregation Principle (ISP): The classes are designed with specific behaviors in mind,
 * avoiding the need for unnecessary interfaces.

 */
public class Bunny {
    private int age;
    private int generation;
    private double reproductionRate;
    private double mutationRate;
    private boolean alive;
    private double health;

    private static final int REPRODUCTION_AGE = 2;
    private static final int MAX_AGE = 7;
    private static final double HEALTH_THRESHOLD = 20.0;

    public Bunny(int generation) {
        this.age = 0;
        this.generation = generation;
        this.reproductionRate = 0.5 + new Random().nextDouble() * 0.5; // between 0.5 and 1.0
        this.mutationRate = 0.05;
        this.alive = true;
        this.health = 100.0;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void ageOneYear() {
        if (alive) {
            age++;
            if (age >= MAX_AGE || health < HEALTH_THRESHOLD) {
                alive = false;
            }
        }
    }

    public boolean canReproduce() {
        return alive && age >= REPRODUCTION_AGE;
    }

    /**
     * Simulates the reproduction process of a Bunny.
     *
     * <p>This method checks if the bunny is able to reproduce by invoking the {@code canReproduce()} method.
     * If eligible, it creates a new Bunny instance representing the offspring.
     * The offspring inherits certain characteristics from the parent, including the generation number, which is
     * incremented by 1.</p>
     *
     * <p>The method also introduces random genetic mutations. If a randomly generated value is less than the parent's
     * mutation rate, the offspring's mutation rate is adjusted by a small random value between -0.1 and +0.1.
     * The mutation rate is kept within the valid range of 0.0 to 1.0.</p>
     *
     * @return the new Bunny offspring if reproduction occurs, or {@code null} if the bunny cannot reproduce
     */
    public Bunny reproduce() {
        if (canReproduce()) {
            Bunny offspring = new Bunny(generation + 1);
            if (new Random().nextDouble() < mutationRate) {
                offspring.mutationRate += (new Random().nextDouble() * 0.2) - 0.1; // mutate between -0.1 and +0.1
                offspring.mutationRate = Math.max(0, Math.min(offspring.mutationRate, 1.0)); // keep mutation rate in range
            }
            return offspring;
        }
        return null;
    }

    /**
     * Calculates the fitness of the bunny based on environmental factors and its traits.
     *
     * <p>The fitness value is determined by considering three factors:
     * the availability of resources in the environment, the carrying capacity of the environment,
     * and the bunny's internal traits such as reproduction rate and mutation rate.
     * A higher fitness score indicates better adaptability to the environment.</p>
     *
     * <p>The formula for fitness is:</p>
     * <ul>
     * <li><strong>Resource Fitness</strong>: The ratio of resource availability to the environment's carrying capacity.</li>
     * <li><strong>Trait Fitness</strong>: A value favoring balanced traits between the reproduction rate and mutation rate,
     * calculated as the inverse of the absolute difference between these two traits.</li>
     * <li>The final fitness is a product of the bunny's health, resource fitness, and trait fitness.</li>
     * </ul>
     *
     * @param env the environment in which the bunny exists, providing resource availability and carrying capacity
     * @return the calculated fitness value for the bunny based on its health, environmental conditions, and traits
     */
    public double calculateFitness(Environment env) {
        double resourceFitness = (double)env.getResourceAvailability() / env.getCarryingCapacity();
        double traitFitness = (1.0 / Math.abs(reproductionRate - mutationRate)); // Favor balanced traits
        return health * resourceFitness * traitFitness;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getGeneration() {
        return generation;
    }

    public double getHealth() {
        return health;
    }

    public double getReproductionRate() {
        return reproductionRate;
    }
}
