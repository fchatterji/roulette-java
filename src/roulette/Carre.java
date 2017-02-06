package roulette;

class Carre extends Mise {
	// Représente une mise à cheval sur 4 numéros

	@Override
	public void setNumerosMises(String annonce) {
		// parse l'annonce pour définir les numéros misés par l'utilisateur
		String[] tokens = annonce.split("-");
		String debut = tokens[0];
		String fin = tokens[1];
		int numero1 = Integer.parseInt(debut);
		int numero4 = Integer.parseInt(fin);
		int numero2 = numero1 + 1;
		int numero3 = numero4 - 1;

		numeros = new int[] { numero1, numero2, numero3, numero4 };
	}

	@Override
	public void setMultiplicateur() {
		// définit le multiplicateur de la mise en cas de victoire du joueur
		multiplicateurDeGain = 8;
	}

	@Override
	public void setExplication() {
		// définit l'explication affichée avant le début de la partie
		explication = "Vous devez entrez deux numéros consécutifs séparés par un tiret, ex: '1-5'.\n"
				+ "Pour que les numéros entrés soient conformes, il faut commencer par le numéro le plus bas.\n"
				+ "Ainsi, 1-5 est correct, mais 5-1 ne l'est pas.\n\n"
				+ "Par exemple, si le premier numéro est 2, le deuxième peut être 6 ou 4.\n"
				+ "Vos quatres numéros doivent être adjacents sur le plateau de jeu, par\n"
				+ "exemple, 3-4 n'est pas conforme.";
	}

	@Override
	public void validerAnnonce(String annonce) {
		// valide l'annonce faite par l'utilisateur en utilisant un regex
		if (!annonce.matches("^[0-9]+-[0-9]+$")) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void validerNumerosMises(TableDeJeu tableDeJeu) {
		// valide les numéros misés par l'utilisateur
		int numeroDebut = numeros[0];
		int numeroFin = numeros[3];

		// on vérifie que les numéros sont dans les bornes du plateau de jeu
		if (numeroDebut < 1 || numeroDebut > 33) {
			throw new IllegalArgumentException();
		}
		if (numeroFin < 4 || numeroFin > 36) {
			throw new IllegalArgumentException();
		}

		// on vérifie que le numéro de fin est bien possible
		int numeroFinPossible1 = tableDeJeu.getOffsetValue(numeroDebut, -1, 1);
		int numeroFinPossible2 = tableDeJeu.getOffsetValue(numeroDebut, 1, 1);

		if (numeroFin != numeroFinPossible1 && numeroFin != numeroFinPossible2) {
			throw new IllegalArgumentException();
		}
	}
}
