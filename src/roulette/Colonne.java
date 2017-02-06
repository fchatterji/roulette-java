package roulette;

import java.util.Arrays;

class Colonne extends Mise {

	@Override
	public void setNumerosMises(String annonce) {
		int choix = Integer.parseInt(annonce);
		switch (choix) {
		case 1:
			numeros = new int[] { 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34 };
			break;
		case 2:
			numeros = new int[] { 2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35 };
			break;
		case 3:
			numeros = new int[] { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36 };
			break;
		default:
			numeros = new int[] {};
			break;
		}
	}

	@Override
	public void setMultiplicateur() {
		multiplicateurDeGain = 2;
	}

	@Override
	public void setExplication() {
		explication = "1: Première colonne\n" + "2: Deuxième colonne\n" + "3: Troisième colonne\n"
				+ "(entrez un chiffre entre 1 et 3)";
	}

	@Override
	public void validerAnnonce(String annonce) {
		if (!annonce.matches("^[0-9]$")) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void validerNumerosMises(TableDeJeu tableDeJeu) {
		if (Arrays.equals(new int[] {}, numeros)) {
			throw new IllegalArgumentException();
		}
	}
}