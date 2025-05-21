package assignment3;

import java.util.Random;

public class Fika {
	private final int simulationDuration = Timers.getSimulationTime(); // Duration of the simulation, this and speed
																		// setting can be adjusted in "Timers.java".
	private final int simulationSleep = Timers.getSimulationSleep();

	public void startSimulation() {
		// Create a coffeeMachine and worker objects. Add a worker by declaring name and
		// adding constructor parameters.
		CoffeeMachine coffeeMachine = new CoffeeMachine();
		Worker Sara = new Worker("Sara", new Random().nextInt(61) + 30, coffeeMachine);
		Worker Holger = new Worker("Holger", new Random().nextInt(61) + 30, coffeeMachine);
		Worker Billy = new Worker("Billy", new Random().nextInt(61) + 30, coffeeMachine);
		Worker Kristina = new Worker("Kristina", new Random().nextInt(61) + 30, coffeeMachine);

		// Create threads for the created objects. Add any newly created workers as new
		// threads in the same pattern.
		Thread coffeeMachineThread = new Thread(coffeeMachine);
		Thread worker1 = new Thread(Sara);
		Thread worker2 = new Thread(Holger);
		Thread worker3 = new Thread(Billy);
		Thread worker4 = new Thread(Kristina);

		// Start each of the created threads. Include (If any) newly created workers.
		coffeeMachineThread.start();
		worker1.start();
		worker2.start();
		worker3.start();
		worker4.start();

		// Set a start time for the simulation.
		long simulationStartTime = System.currentTimeMillis();

		// Run the simulation until the elapsed time exceeds the specified duration.
		while (System.currentTimeMillis() - simulationStartTime < simulationDuration) {
			try {
				Thread.sleep(simulationSleep);
			} catch (InterruptedException e) {
			}
		}

		// Interrupt all threads to stop the simulation. Include (If any) newly created
		// workers.
		coffeeMachineThread.interrupt();
		worker1.interrupt();
		worker2.interrupt();
		worker3.interrupt();
		worker4.interrupt();
	}

	// Main entry point of application.
	public static void main(String args[]) {
		Fika fika = new Fika();
		fika.startSimulation();
	}
}