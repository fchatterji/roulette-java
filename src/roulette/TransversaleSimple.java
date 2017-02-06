package roulette;

class TransversaleSimple extends Mise {

	@Override
	public void setNumerosMises(String annonce) {
		String[] tokens = annonce.split("-");
		String debut = tokens[0];
		String fin = tokens[1];
		int numero1 = Integer.parseInt(debut);
		int numero2 = numero1 + 1;
		int numero3 = numero1 + 2;
		int numero4 = numero1 + 3;
		int numero5 = numero1 + 4;
		int numero6 = Integer.parseInt(fin);

		numeros = new int[] { numero1, numero2, numero3, numero4, numero5, numero6 };
	}

	@Override
	public void setMultiplicateur() {
		multiplicateurDeGain = 5;
	}

	@Override
	public void setExplication() {
		explication = "Vous devez entrez deux numéros consécutifs séparés par un tiret, ex: '1-6'.\n"
				+ "Pour que les numéros entrés soient conformes, il faut commencer par le numéro le plus bas.\n"
				+ "Ainsi, 7-12 est correct, mais 12-7 ne l'est pas.\n\n"
				+ "Par exemple, si vous voulez miser sur les 2 premières colonnes, indiquez 1-6.\n"
				+ "Vous devez bien choisir deux numéros correspondants au début et à la fin de votre choix, \n"
				+ "par exemple, 4-8 n'est pas conforme.";
	}

	@Override
	public void validerAnnonce(String annonce) {
		if (!annonce.matches("^[0-9]+-[0-9]+$")) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void validerNumerosMises(TableDeJeu tableDeJeu) {
		int numeroDebut = numeros[0];
		int numeroFin = numeros[5];

		if (!Outils.contains(new int[] { 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31 }, numeroDebut)) {
			throw new IllegalArgumentException();
		}

		if (!Outils.contains(new int[] { 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36 }, numeroFin)) {
			throw new IllegalArgumentException();
		}

		if (numeroFin - numeroDebut != 5) {
			throw new IllegalArgumentException();
		}

		int numeroFinPossible = tableDeJeu.getOffsetValue(numeroDebut, -2, 1);

		if (numeroFin != numeroFinPossible) {
			throw new IllegalArgumentException();
		}
	}
}