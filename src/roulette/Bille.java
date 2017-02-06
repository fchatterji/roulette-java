package roulette;

public class Bille {
	// Classe qui représente la bille du jeu de la roulette, avec le numéro
	// gagnant
	// sur lequel elle s'arrête
	private int numeroGagnant;
	private int min;
	private int max;

	public Bille(int min, int max) {
		// instantie une bille avec des bornes maximum et minimum
		this.min = min;
		this.max = max;
		this.numeroGagnant = (int) (Math.random() * ((this.max - this.min) + 1)) + this.min;
	}

	public Bille() {
		// instantie une bille de roulette avec des bornes maximum et minimum
		// prédéfinies
		this.min = 1;
		this.max = 35;
		this.numeroGagnant = (int) (Math.random() * ((this.max - this.min) + 1)) + this.min;
	}

	public int getNumeroGagnant() {
		// retourne le numéro gagnant
		return numeroGagnant;
	}
}
