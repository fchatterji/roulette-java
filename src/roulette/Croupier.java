package roulette;

import java.util.ArrayList;
import java.util.Arrays;

public class Croupier {

	public void direBonjourAuxJoueurs() {
		System.out.println("Bonjour à tous les joueurs et bienvenue à la roulette!");
	}

	public void faitesVosJeux() {
		System.out.println("Mesdames et Messieurs, faites vos jeux!");
	}

	public void direAuJoueurDeMise(Joueur joueur) {
		System.out.println(joueur.getNom() + ", à votre tour!");
	}

	public void expliquerChoixMise() {
		System.out.println("Vous avez le choix entre ces différentes options:");
		System.out.println("1: Plein (miser sur un chiffre)");
		System.out.println("2: A cheval (miser sur deux chiffres)");
		System.out.println("3: Transversale pleine (miser sur trois chiffres)");
		System.out.println("4: Carré (miser sur quatre chiffres)");
		System.out.println("5: Quatre premiers (miser sur les quatre premiers chiffres)");
		System.out.println("6: Transversale simple (miser sur six chiffres)");
		System.out.println("7: Colonne (miser sur douze chiffres)");
		System.out.println("8: Douzaines (miser sur douze chiffres)");
		System.out.println("9: Passe/Manque (miser sur la moitié des chiffres)");
		System.out.println("10: Chances simples (miser sur une couleur ou pair impair)");
		System.out.println("11: Quitter le jeu");
		System.out.print("> ");
	}

	public void expliquerReglesMise(Mise mise) {
		// afficher les règles d'une mise
		System.out.println(mise.getExplication());
		System.out.print("> ");
	}

	public boolean annonceEstValide(Mise mise, String annonce, TableDeJeu tableDeJeu) {
		// vérifier si une annonce est valide
		mise.estValide(annonce, tableDeJeu);
		return mise.estValide;

	}

	public void critiquerGentillementLeJoueur(Mise mise) {
		// le croupier est toujours de bonne humeur
		System.out.println(mise.messageErreur);
		System.out.println(
				"Le système d'annonce n'est pas très intuitif, je vous l'accorde. Ces numéros ne sont pas valides. Essayez encore :-)");
		System.out.print("> ");
	}

	public void demanderSiLeJoueurVeutContinuerDeMiser() {
		System.out.println("Encore une mise?");
		System.out.println("1: oui");
		System.out.println("2: non");
		System.out.print("> ");
	}

	public void lesJeuxSontFaits() {
		System.out.println("Les jeux sont faits.");
	}

	public void rienNeVaPlus() {
		System.out.println("Rien ne va plus.");
	}

	public void expliquerChoixMontant(Joueur joueur) {
		System.out.println(joueur.getNom() + ", vous devez maintenant choisir le montant que vous allez miser.");
		System.out.println("Pour rappel, il vous reste " + joueur.getJetons() + ".");
		System.out.print("> ");
	}

	public boolean montantEstValide(Joueur joueur, int montant) {
		// vérifier si le montant entré par le joueur est bien inférieur au
		// nombre de jetons qu'il a
		return montant <= joueur.getJetons();
	}

	public void leMontantEstTropEleve() {
		System.out.println("Désolé, mais la maison ne fait pas crédit.");
	}

	public void annoncerNumeroGagnant(int numeroGagnant, TableDeJeu tableDeJeu) {
		// à partir d'un numéro gagnant, annoncer sa parité, sa couleur, s'il
		// est dans
		// les numéros passe ou manque
		String parite = "";
		if (Outils.contains(tableDeJeu.pairs, numeroGagnant)) {
			parite = "pair";
		} else if (Outils.contains(tableDeJeu.impairs, numeroGagnant)) {
			parite = "impair";
		}

		String couleur = "";
		if (Outils.contains(tableDeJeu.rouge, numeroGagnant)) {
			couleur = "rouge";
		} else if (Outils.contains(tableDeJeu.noir, numeroGagnant)) {
			couleur = "noir";
		}

		String passeManque = "";
		if (Outils.contains(tableDeJeu.passe, numeroGagnant)) {
			passeManque = "passe";
		} else if (Outils.contains(tableDeJeu.manque, numeroGagnant)) {
			passeManque = "manque";
		}

		System.out.println("Le numéro gagnant est le " + numeroGagnant + ", " + parite + ", " + couleur + ", "
				+ passeManque + ".");
	}

	public void calculerEtAnnoncerGains(TableDeJeu tableDeJeu, int numeroGagnant) {
		// pour chaque joueur, calculer les gains, puis les annoncer
		ArrayList<Mise> mises = tableDeJeu.getMises();
		ArrayList<Joueur> joueurs = tableDeJeu.getJoueurs();

		for (Joueur joueur : joueurs) {
			int gainTotal = 0;
			int miseTotale = 0;
			for (Mise mise : mises) {
				if (mise.getNomJoueur() == joueur.getNom()) {
					gainTotal += mise.calculerGain(numeroGagnant);
					miseTotale += mise.getMontant();
				}
			}
			int jetons = joueur.getJetons();
			joueur.setJetons(jetons + gainTotal);
			System.out.println(joueur.getNom() + ", a misé " + miseTotale + ". Il a gagné " + gainTotal
					+ ". Il a maintenant un total de " + joueur.getJetons() + " jetons restants.");
		}
	}

	public void annoncerMise(TableDeJeu tableDeJeu) {
		// annoncer toutes les mises en cours
		ArrayList<Mise> mises = tableDeJeu.getMises();
		for (Mise mise : mises) {
			System.out.println(mise.getNomJoueur() + " a misé " + mise.getMontant() + " sur " + mise.getTypeMise()
					+ " soit le(s) numéro(s) " + Arrays.toString(mise.getNumeros()));
		}
	}

	public boolean typeMiseEstValide(int typeMise) {
		return (typeMise < 1 || typeMise > 12) ? false : true;
	}

	public void continuerAJouer() {
		System.out.println("Voulez-vous continuer à jouer?");
		System.out.println("1: oui");
		System.out.println("2: non");
		System.out.print("> ");
	}

	public void supprimerMises(TableDeJeu tableDeJeu) {
		tableDeJeu.supprimerMises();
	}
}
