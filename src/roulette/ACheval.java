package roulette;

class ACheval extends Mise {
	// Représente une mise à cheval sur deux numéros

	@Override
	public void setNumerosMises(String annonce) {
		// parse l'annonce pour définir les numéros misés par l'utilisateur
		String[] tokens = annonce.split("-");
		String annonce1 = tokens[0];
		String annonce2 = tokens[1];
		int numero1 = Integer.parseInt(annonce1);
		int numero2 = Integer.parseInt(annonce2);
		numeros = new int[] { numero1, numero2 };
	}

	@Override
	public void setMultiplicateur() {
		// définit le multiplicateur de la mise en cas de victoire du joueur
		multiplicateurDeGain = 17;
	}

	@Override
	public void setExplication() {
		// définit l'explication affichée avant le début de la partie
		explication = "Vous devez entrez deux numéros consécutifs séparés par un tiret, ex: '4-5'.\n"
				+ "Pour que les numéros entrés soient conformes, il faut commencer par le numéro le plus bas.\n"
				+ "Ainsi, 2-3 est correct, mais 3-2 ne l'est pas.\n\n"
				+ "Les deux numéros doivent également être adjacents sur le tapis de jeux. Par exemple, 3-1 n'est\n"
				+ "pas conforme.";
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

		// récupération des numéros misés
		int numeroDebut = numeros[0];
		int numeroFin = numeros[1];

		// exception si les numéros misés ne sont pas sur le plateau
		if (numeroDebut < 1 || numeroDebut > 35) {
			throw new IllegalArgumentException();
		}
		if (numeroFin < 2 || numeroFin > 36) {
			throw new IllegalArgumentException();
		}

		if (numeroDebut < 34) {
			// si le premier numéro misé est inférieur à
			// 34, deux deuxièmes numéros sont possibles: celui en "haut" et
			// celui à "droite"
			int numeroFinPossible1 = tableDeJeu.getOffsetValue(numeroDebut, 0, 1);
			int numeroFinPossible2 = tableDeJeu.getOffsetValue(numeroDebut, -1, 0);

			if (numeroFin != numeroFinPossible1 && numeroFin != numeroFinPossible2) {
				throw new IllegalArgumentException();
			}
		} else {
			// en revanche, si le premier numéro misé est 35 ou 36, un seul
			// deuxième
			// numéro est possible: celui juste "en haut"
			int numeroFinPossible1 = tableDeJeu.getOffsetValue(numeroDebut, -1, 0);

			if (numeroFin != numeroFinPossible1) {
				throw new IllegalArgumentException();
			}
		}
	}
}