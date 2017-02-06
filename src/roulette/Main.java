package roulette;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		// mise en place de la partie: instantiation de la table de jeu et du
		// croupier
		TableDeJeu tableDeJeu = new TableDeJeu();
		Croupier croupier = new Croupier();

		// choix et validation du nombre de joueurs
		System.out.println("Avant de commencer la partie, veuillez entrer un nombre de joueurs.");
		System.out.print("> ");
		int nombreJoueurs = Outils.readPositiveInteger();

		// instantiation des objets joueurs
		tableDeJeu.ajouterJoueurs(nombreJoueurs);
		ArrayList<Joueur> joueurs = tableDeJeu.getJoueurs();

		// while qui continue tant que le jeu n'est pas terminé
		boolean jeuTermine = false;
		while (!jeuTermine) {

			croupier.faitesVosJeux();

			for (Joueur joueur : joueurs) {
				croupier.direAuJoueurDeMise(joueur);

				// Mises des joueurs
				boolean misesTerminees = false;
				while (!misesTerminees) {

					// le joueur choisit un type de mise
					croupier.expliquerChoixMise();
					Mise mise = joueur.choisirTypeMise();

					tableDeJeu.afficherPlateauDeJeu();
					croupier.expliquerReglesMise(mise);

					// le joueur choisit une annonce (soit les numéros qu'il
					// mise)
					boolean annonceEstChoisi = false;
					while (!annonceEstChoisi) {
						String annonce = joueur.choisirAnnonce();
						if (!croupier.annonceEstValide(mise, annonce, tableDeJeu)) {
							croupier.critiquerGentillementLeJoueur(mise);
							continue;
						}
						annonceEstChoisi = true;
					}

					// choix du montant de la mise
					croupier.expliquerChoixMontant(joueur);
					int montant = joueur.choisirMontantMise();

					// le joueur mise et "pose" ses jetons sur la table
					joueur.miser(montant);
					tableDeJeu.ajouterMise(mise, montant, joueur.getNom());

					// le croupier demande si le joueur veut continuer à miser
					croupier.demanderSiLeJoueurVeutContinuerDeMiser();
					misesTerminees = !joueur.veutContinuerDeMiser();
				}
			}
			// le croupier annonce les mises
			croupier.lesJeuxSontFaits();
			croupier.annoncerMise(tableDeJeu);
			croupier.rienNeVaPlus();

			// la bille est lancée
			Bille bille = new Bille();
			int numeroGagnant = bille.getNumeroGagnant();

			// le croupier annonce les résultats et les gains
			croupier.annoncerNumeroGagnant(numeroGagnant, tableDeJeu);
			croupier.calculerEtAnnoncerGains(tableDeJeu, numeroGagnant);

			// le croupier supprime l'historique des mises
			croupier.supprimerMises(tableDeJeu);

			// le croupier demande si le jeu doit continuer
			croupier.continuerAJouer();

			// l'utilisateur choisit s'il veut continuer
			int[] choixPossibles = { 1, 2 };
			int choixContinuer = Outils.readInteger(choixPossibles);
			if (choixContinuer == 2) {
				jeuTermine = true;
			}
		}
	}
}
