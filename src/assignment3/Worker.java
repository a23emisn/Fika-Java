package assignment3;

public class Worker extends Thread {
	private String name; // Worker name
	private int energyLevel; // Worker energy level.
	private final int energyDecreaseTimer = Timers.getEnergyDecreaseTime(); // Worker energy drain constant.
	private CoffeeMachine coffeeMachine; // Coffee Machine assigned to the worker.
	private boolean inQueue = false; // Flag to indicates a Worker is in the coffee queue.

	// Constructor for Worker.
	public Worker(String name, int energy, CoffeeMachine coffeeMachine) {
		this.name = name;
		this.energyLevel = energy;
		this.coffeeMachine = coffeeMachine;
	}

	// Enter the coffeeQueue.
	private void enterQueue() {
		coffeeMachine.addWorkerToQueue(this);
		inQueue = true;
	}

	// Exit the coffeeQueue.
	private void exitQueue() {
		coffeeMachine.removeWorkerFromQueue(this);
		inQueue = false;
	}

	// Check if there's a coffee to drink.
	private void checkForCoffee() {
		if (coffeeMachine.hasCoffee()) {
			drinkCoffeeAndExitQueue(); // Drink the coffee and exit the queue.
		} else {
			return; // If no coffee exists, abort.
		}
	}

	// Drink a coffee and leave the queue.
	private void drinkCoffeeAndExitQueue() {
		Coffee coffee = coffeeMachine.serveCoffee();
		int energyBoost = coffee.getEnergyLevel();
		energyLevel += energyBoost;
		System.out.println(name + " enjoyed a " + coffee.getDrinkName() + " with energy level " + energyBoost);
		exitQueue();
	}

	// Check if worker is first in the coffeeQueue return true/false.
	private boolean firstInQueue() {
		boolean firstInQueue;
		if (coffeeMachine.whoIsFirst() == this) {
			firstInQueue = true;
		} else {
			firstInQueue = false;
		}
		return firstInQueue;
	}

	public void run() {
		do {
			try {
				Thread.sleep(energyDecreaseTimer); // Sleep to simulate gradual energy decrease.
			} catch (InterruptedException e) {
				break;
			}

			synchronized (this) {
				// Code runs if worker has lower than 30 energy and is not in the queue.
				if (energyLevel < 30 && !inQueue) {
					enterQueue(); // If true, enters queue.
				}

				// Runs if worker is in queue.
				if (inQueue) {
					System.out.println(name + " is taking a break with energy level " + energyLevel);
					// If first in queue, the following snippet runs.
					if (firstInQueue()) {
						checkForCoffee(); // Check if coffee is available, if not, returns here and continues below,

						// Runs if Workers energy didn't reach 100, and not in queue.
						if (energyLevel < 100 && !inQueue) {
							enterQueue(); // Reenter the queue.
						}

						// Runs if Worker had their energy replenished to 100+.
						else if (energyLevel >= 100) {
							System.out.println(name + " goes back to work with energy level " + energyLevel);
						}
					}
				} else {
					// Otherwise the worker is working.
					System.out.println(name + " is working with energy level " + energyLevel);
				}
				energyLevel--; // Decrease workers energy level.
			}

		} while (energyLevel > 0);

		// If a Workers energy goes to 0, they leave work.
		if (energyLevel <= 0) {
			System.out.println(name + " feels too tired and leaves work.");
		}
	}
}