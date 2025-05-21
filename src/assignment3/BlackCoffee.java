package assignment3;

import java.util.Random;

public class BlackCoffee extends Coffee {
	private int drinkEnergy;
	private String drinkName = "Black Coffee";

	// Constructor for BlackCoffee.
	public BlackCoffee() {
		// Randomize a energy between 15 and 20
		drinkEnergy = new Random().nextInt(6) + 15;
	}

	// Getter for DrinkName
	public String getDrinkName() {
		return drinkName;
	}

	// Getter for EnergyLevel
	public int getEnergyLevel() {
		return this.drinkEnergy;
	}
}
