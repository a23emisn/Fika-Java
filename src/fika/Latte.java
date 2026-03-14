package fika;

import java.util.Random;

public class Latte extends Coffee {
	private int drinkEnergy;
	private String drinkName = "Latte";

	// Constructor for Latte.
	public Latte() {
		// Randomize a energy between 25 and 35
		drinkEnergy = new Random().nextInt(11) + 25;
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
