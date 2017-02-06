package roulette;

public abstract class Mise {
	// classe qui représente une mise d'un joueur.
	// C'est une classe abstraite, en effet certaines parties de la logique
	// d'une mise
	// (comme la validation de l'annonce) sont traitées par des classes
	// concrètes.

	protected int[] numeros;
	protected String explication;
	protected int multiplicateurDeGain;
	protected int montant;
	protected boolean estValide;
	protected String messageErreur;
	protected String nomJoueur;

	public Mise() {
		// instantie une mise, définit le multiplicateur de gain et
		// l'explication apportée au joueur
		setMultiplicateur();
		setExplication();
	}

	abstract public void setExplication();

	public String getExplication() {
		return this.explication;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getMontant() {
		return this.montant;
	}

	public void setJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public String getNomJoueur() {
		return this.nomJoueur;
	}

	public int[] getNumeros() {
		return this.numeros;
	}

	abstract public void setNumerosMises(String annonce);

	abstract public void validerNumerosMises(TableDeJeu tableDeJeu);

	abstract public void setMultiplicateur();

	abstract public void validerAnnonce(String annonce);

	public void estValide(String annonce, TableDeJeu tableDeJeu) {
		// fonction qui valide une annonce du joueur

		try {
			validerAnnonce(annonce);
		} catch (IllegalArgumentException e) {
			estValide = false;
			messageErreur = "Annonce au mauvais format";
			return;
		}
		setNumerosMises(annonce);

		try {
			validerNumerosMises(tableDeJeu);

		} catch (IllegalArgumentException e) {
			estValide = false;
			messageErreur = "Numéros entrés non conformes";
			return;

		} catch (IndexOutOfBoundsException e) {
			estValide = false;
			messageErreur = "Numéros entrés non conformes";
			return;
		}

		estValide = true;
	}

	public int calculerGain(int numeroGagnant) {
		// calcule les gains d'une mise en fonction du numéro gagnant
		boolean estGagnant = Outils.contains(numeros, numeroGagnant);
		return (estGagnant) ? multiplicateurDeGain * montant : 0;
	}

	public String getTypeMise() {
		return this.getClass().getSimpleName();
	}
}
