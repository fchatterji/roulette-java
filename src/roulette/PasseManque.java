package roulette;

import java.util.Arrays;

class PasseManque extends Mise {

	@Override
	public void setNumerosMises(String annonce) {
		int choix = Integer.parseInt(annonce);
		switch (choix) {
		case 1:
			numeros = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
			break;
		case 2:
			numeros = new int[] { 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };
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
				+ "(entrez un chiffre entre 1 et 2)";
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
