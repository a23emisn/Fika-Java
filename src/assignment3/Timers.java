package assignment3;

import java.util.Random;

// Time scaling class, adjust the values here to adjust
public class Timers {
	private static final double timeScale = 1.0; // Adjust this value to change the speed of the entire simulation.
	private static final int simulationTime = 30000; // Adjust this value to change the simulation duration. (ms)

	private static final int simulationSleep = 1000; // Simulation sleep time, don't touch!
	private static final int coffeeProduceTime = 2000; // Coffee production time, don't touch!
	private static final int coffeeServeTime = 1000; // Coffee serve time, don't touch!

	// Getter for simulationTime.
	public static int getSimulationTime() {
		return (int) (simulationTime / timeScale);
	}

	// Getter for simulationSleep.
	public static int getSimulationSleep() {
		return (int) (simulationSleep / timeScale);
	}

	// Getter for coffeeProduceTime.
	public static int getCoffeeProduceTime() {
		return (int) (coffeeProduceTime / timeScale);
	}

	// Getter for coffeeServeTime.
	public static int getCoffeeServeTime() {
		return (int) (coffeeServeTime / timeScale);
	}

	// Getter for energyDecreaseTimer.
	public static int getEnergyDecreaseTime() {
		return (int) (new Random().nextInt(1001) + 500 / timeScale);
	}
}
