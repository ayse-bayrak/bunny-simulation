# Bunny Population Simulation

## Overview

This application simulates the evolution of a bunny population based on environmental factors such as age, reproduction rate, and mutations. The simulation tracks the population dynamics over multiple generations, observing how environmental constraints impact growth, reproduction, and health.

## Features

- **Bunny Class**: Models individual bunnies with attributes such as age, generation, reproduction rate, mutation rate, health status, and living condition.
- **Environment Class**: Represents the habitat with a defined carrying capacity, introducing constraints like resource scarcity and random environmental events.
- **Simulation Class**: Manages the lifecycle of the simulation, executing yearly updates to the population and environment.
- **Mutation and Evolution**: Models genetic mutations that occur during reproduction, affecting the bunnies' survival and adaptability.

# Java Coding Standards
## 1. Code Formatting

- Follow standard Java code formatting guidelines.
- Use an IDE like IntelliJ IDEA or Eclipse, which can automatically format code.
- Maintain a consistent indentation style (typically 4 spaces per indent).

## 2. Naming Conventions

- Class names should be nouns in PascalCase (e.g., `Bunny`, `Environment`, `Simulation`).
- Method names should be verbs in camelCase (e.g., `ageBunnies`, `introduceRandomEvent`).
- Variable names should be descriptive and in camelCase (e.g., `populationSize`, `carryingCapacity`).

## 3. Comments

- Use Javadoc for public methods and classes to describe their purpose and usage.
- Inline comments should be used sparingly to clarify complex logic.
- Ensure comments are updated as code changes to avoid misinformation.

## 4. Java Maven Project Specifications

- The project is structured as a Maven project.
- Source code is located in the `src/main/java` directory.
- Test code should be placed in the `src/test/java` directory.
- The `pom.xml` file contains project dependencies and configurations.

## 5. General Guidelines

- Keep methods short and focused on a single task.
- Prefer composition over inheritance where applicable.
- Ensure that classes have a single responsibility.
- Regularly commit changes to version control with meaningful commit messages.

## 6. Dependencies

The following dependencies are included in the `pom.xml`:

- **Java**: 1.8 or higher
- **Maven**: For project management and build automation
- **JUnit**: For testing 

Example of `pom.xml` dependencies section:
```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    <!-- Add other dependencies as needed -->
</dependencies>
