package roulette;

import java.util.Arrays;

class ChanceSimple extends Mise {

	@Override
	public void setNumerosMises(String annonce) {
		int choix = Integer.parseInt(annonce);
		switch (choix) {
		case 1:
			// Manque
			numeros = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
			break;
		case 2:
			// Passe
			numeros = new int[] { 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };
			break;
		case 3:
			// Numéros rouges
			numeros = new int[] { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
			break;
		case 4:
			// Numéros noirs
			numeros = new int[] { 2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35 };
			break;
		case 5:
			// Pairs
			numeros = new int[] { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36 };
			break;
		case 6:
			// Impairs
			numeros = new int[] { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35 };
			break;
		default:
			numeros = new int[] {};
			break;
		}
	}

	@Override
	public void setMultiplicateur() {
		multiplicateurDeGain = 1;
	}

	@Override
	public void setExplication() {
		explication = "1: Manque (numéros entre 1 et 18)\n" + "2: Passe (numéros entre 19 et 36)\n"
				+ "3: Numéros rouges\n" + "4: Numéros noirs\n" + "5: Numéros pairs\n" + "6: Numéros impairs\n"
				+ "(entrez un chiffre entre 1 et 6)";
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
