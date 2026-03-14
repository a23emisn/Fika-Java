package fika;

import java.util.Random;

public class Capuccino extends Coffee {
	private int drinkEnergy;
	private String drinkName = "Capuccino";

	// Constructor for Capuccino.
	public Capuccino() {
		// Randomize a energy between 20 and 30
		drinkEnergy = new Random().nextInt(11) + 20;
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