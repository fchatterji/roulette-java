package roulette;

import java.util.ArrayList;

public class TableDeJeu {
	// Classe qui représente une table de jeu de la roulette.
	// Cette classe contient des informations sur les joueurs présents à la
	// table
	// et sur les mises en cours.

	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

	private ArrayList<Mise> mises = new ArrayList<Mise>();

	private String[][] plateauDeJeu = {
			{ "", "3", "6", "9", "12", "|", "15", "18", "21", "24", "|", "27", "30", "33", "36", "|", "3eC" },
			{ "0", "2", "5", "8", "11", "|", "14", "17", "20", "23", "|", "26", "29", "32", "35", "|", "2eC" },
			{ "", "1", "4", "7", "10", "|", "13", "16", "19", "22", "|", "25", "28", "31", "34", "|", "1eC" },
			{ "1 douzaine", "2 douzaines", "3 douzaines" },
			{ "1 à 18", "pair", "noir", "rouge", "impair", "19 à 36" } };

	private int[][] nombres = { { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36 },
			{ 2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35 }, { 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34 } };

	public int[] passe = { 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };
	public int[] manque = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };

	public int[] rouge = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
	public int[] noir = { 2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35 };

	public int[] pairs = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36 };
	public int[] impairs = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35 };

	public int[] colonne1 = { 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34 };
	public int[] colonne2 = { 2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35 };
	public int[] colonne3 = { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36 };

	public ArrayList<Joueur> getJoueurs() {
		return this.joueurs;
	}

	public ArrayList<Mise> getMises() {
		return this.mises;
	}

	public void supprimerMises() {
		this.mises.clear();
	}

	public void ajouterJoueur(Joueur joueur) {
		// ajouter un joueur à la table de jeu
		joueurs.add(joueur);
	}

	public void ajouterJoueurs(int nombreJoueurs) {
		// méthode pour ajouter rapidement un nombre de joueurs avec un nom
		// correspondant à leur numéro
		for (int i = 0; i < nombreJoueurs; i++) {
			Joueur joueur = new Joueur("Joueur n°" + String.valueOf(i + 1));
			joueurs.add(joueur);
		}
	}

	public void afficherPlateauDeJeu() {
		// afficher le plateau de jeu sur la console
		System.out.println("---------------------------------------------------------------------");
		afficherNombres();
		afficherDouzaines();
		afficherChancesSimples();
		System.out.println("---------------------------------------------------------------------");
	}

	public void afficherNombres() {
		// afficher les lignes avec les numéros
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < plateauDeJeu[i].length; j++) {
				System.out.print(String.format("%4s", plateauDeJeu[i][j]));
			}
			System.out.print("\n");
		}
	}

	public void afficherDouzaines() {
		// afficher les douzaines
		System.out.format("%4s%16s%4s%16s%4s%16s\n", "", plateauDeJeu[3][0], "", plateauDeJeu[3][1], "",
				plateauDeJeu[3][2]);
	}

	public void afficherChancesSimples() {
		// afficher les chances simples
		System.out.format("%4s%8s%8s%4s%8s%8s%4s%8s%8s\n", "", plateauDeJeu[4][0], plateauDeJeu[4][1], "",
				plateauDeJeu[4][2], plateauDeJeu[4][3], "", plateauDeJeu[4][4], plateauDeJeu[4][5]);
	}

	public int getOffsetValue(int number, int xOffset, int yOffset) {
		// donne la valeur d'une case à partir de la valeur d'une autre
		// case et d'un décalage (offset) sur l'axe des abscisses ou
		// des ordonnées
		int offsetValue;

		try {
			// on récupère les coordonnées du numéro initial
			int[] initialCoordinate = getCoordinate(number);

			// on récupère les coordonnées du numéro recherché
			int xOffsetCoordinate = initialCoordinate[0] + xOffset;
			int yOffsetCoordinate = initialCoordinate[1] + yOffset;
			int[] offsetCoordinate = new int[] { xOffsetCoordinate, yOffsetCoordinate };

			// on récupère la valeur correspondante aux coordonnées du numéro
			// recherché
			offsetValue = getValue(offsetCoordinate);
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
		return offsetValue;
	}

	private int[] getCoordinate(int value) {
		// retourne les coordonnées d'une case à partir de sa valeur
		int rows = nombres.length;
		int columns = nombres[0].length;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (nombres[i][j] == value) {
					return new int[] { i, j };
				}
			}
		}
		return null;
	}

	private int getValue(int[] coordinate) {
		// retourne la valeur d'une case à partir de ses coordonnées
		int xCoordinate = coordinate[0];
		int yCoordinate = coordinate[1];
		return nombres[xCoordinate][yCoordinate];
	}

	public Mise getDerniereMise() {
		// retourne la dernière mise réalisée
		return mises.get(mises.size() - 1);
	}

	public void ajouterMise(Mise mise, int montant, String joueur) {
		mise.setMontant(montant);
		mise.setJoueur(joueur);
		mises.add(mise);
	}
}
