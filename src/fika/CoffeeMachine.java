package fika;

import java.util.Random;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

// CoffeeMachine class, represents a Coffee machine, extends Thread.
public class CoffeeMachine extends Thread {
	private LinkedList<Coffee> coffeeReserve; // List to represent coffees in the reserve.
	private ConcurrentLinkedQueue<Worker> coffeeQueue; // A queue representing the coffeemachine's queue.
	private final int coffeeProduceTime = Timers.getCoffeeProduceTime(); // Get coffee production time.
	private final int coffeeServeTime = Timers.getCoffeeServeTime();

	// Constructor for coffeeMachine.
	public CoffeeMachine() {
		this.coffeeReserve = new LinkedList<>();
		this.coffeeQueue = new ConcurrentLinkedQueue<>();
	}

	// Add a worker to the queue.
	public void addWorkerToQueue(Worker worker) {
		coffeeQueue.add(worker);
	}

	// Remove a worker from the queue.
	public void removeWorkerFromQueue(Worker worker) {
		coffeeQueue.remove(worker);
	}

	// Check if there is a coffee available to drink.
	public boolean hasCoffee() {
		return !coffeeReserve.isEmpty();
	}

	// Return and remove the first Coffee in the coffeeReserve.
	public Coffee serveCoffee() {
		try {
			Thread.sleep(coffeeServeTime);
		} catch (InterruptedException e) {
		}
		return coffeeReserve.poll();
	}

	// Return the first worker in the coffeeQueue.
	public Worker whoIsFirst() {
		return coffeeQueue.peek();
	}

	// Creates a coffee of random type and adds it to coffeeReserve.
	private void createCoffee() {
		int coffeeType = new Random().nextInt(3); // Randomize number for type of coffee.
		switch (coffeeType) {
		case 0:
			coffeeReserve.offerLast(new BlackCoffee()); // 0 for Black Coffee.
			break;
		case 1:
			coffeeReserve.offerLast(new Latte()); // 1 for Latte.
			break;
		case 2:
			coffeeReserve.offerLast(new Capuccino()); // 2 for Capuccino.
			break;
		}
	}

	public void run() {
		// Simulate coffee production time.
		while (!Thread.interrupted()) {
			try {
				Thread.sleep(coffeeProduceTime);
			} catch (InterruptedException e) {
				break;
			}

			synchronized (this) {
				// Adds a coffee to the coffee reserve if it's not full.
				if (coffeeReserve.size() < 20) {
					createCoffee();
					System.out.println(
							"Drink created. Coffee Machine has " + coffeeReserve.size() + " drinks in reserve.");
				}
			}
		}
	}
}