package roulette;

class TransversalePleine extends Mise {

	@Override
	public void setNumerosMises(String annonce) {
		String[] tokens = annonce.split("-");
		String debut = tokens[0];
		String fin = tokens[1];
		int numero1 = Integer.parseInt(debut);
		int numero2 = numero1 + 1;
		int numero3 = Integer.parseInt(fin);
		numeros = new int[] { numero1, numero2, numero3 };
	}

	@Override
	public void setMultiplicateur() {
		multiplicateurDeGain = 11;
	}

	@Override
	public void setExplication() {
		explication = "Vous devez entrez deux numéros consécutifs séparés par un tiret, ex: '4-6'.\n"
				+ "Pour que les numéros entrés soient conformes, il faut commencer par le numéro le plus bas.\n"
				+ "Ainsi, 7-9 est correct, mais 9-7 ne l'est pas.\n\n"
				+ "Par exemple, si vous voulez miser sur la 1ère colonne, indiquez 1-3.\n"
				+ "Vous devez bien choisir deux numéros correspondants au début et à la fin de la colonne, \n"
				+ "par exemple, 5-6 n'est pas conforme.";
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
		int numeroFin = numeros[2];

		if (numeroDebut < 1 || numeroDebut > 34) {
			throw new IllegalArgumentException();
		}
		if (numeroFin < 3 || numeroFin > 36) {
			throw new IllegalArgumentException();
		}
		if (numeroFin - numeroDebut != 2) {
			throw new IllegalArgumentException();
		}

		int numeroFinPossible = tableDeJeu.getOffsetValue(numeroDebut, -2, 0);

		if (numeroFin != numeroFinPossible) {
			throw new IllegalArgumentException();
		}
	}
}
