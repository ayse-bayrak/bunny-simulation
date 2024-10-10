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
        this.mutationRate = 0.05; // default mutation rate
        this.alive = true;
        this.health = 100.0; // initial health
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
            if (age >= MAX_AGE) {
                alive = false; // Bunny dies after max age
            } else if (health < HEALTH_THRESHOLD) {
                alive = false; // Bunny dies if health falls below threshold
            }
        }
    }

    public boolean canReproduce() {
        return alive && age >= REPRODUCTION_AGE;
    }

    public Bunny reproduce() {
        if (canReproduce()) {
            Bunny offspring = new Bunny(generation + 1);
            // Apply mutations
            if (new Random().nextDouble() < mutationRate) {
                offspring.mutationRate += (new Random().nextDouble() * 0.1) - 0.05; // mutate between -0.05 and +0.05
            }
            return offspring;
        }
        return null; // Can't reproduce
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
