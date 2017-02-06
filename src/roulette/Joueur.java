package roulette;

public class Joueur {
	private int jetons;
	private String nom;

	public Joueur(String nom) {
		this.setJetons(100);
		this.setNom(nom);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getJetons() {
		return jetons;
	}

	public void setJetons(int jetons) {
		this.jetons = jetons;
	}

	public Mise choisirTypeMise() {
		int choix;
		int[] choixPossibles = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		choix = Outils.readInteger(choixPossibles);

		Mise mise = null;

		switch (choix) {
		case 1:
			mise = new Plein();
			break;
		case 2:
			mise = new ACheval();
			break;
		case 3:
			mise = new TransversalePleine();
			break;
		case 4:
			mise = new Carre();
			break;
		case 5:
			mise = new QuatrePremiers();
			break;
		case 6:
			mise = new TransversaleSimple();
			break;
		case 7:
			mise = new Colonne();
			break;
		case 8:
			mise = new Douzaine();
			break;
		case 9:
			mise = new PasseManque();
			break;
		case 10:
			mise = new ChanceSimple();
			break;
		case 11:
			System.out.println("La partie est terminée.");
			System.exit(0);
			break;
		default:
			break;
		}
		return mise;
	}

	public String choisirAnnonce() {
		String annonce = Outils.readString();
		return annonce;
	}

	public boolean veutContinuerDeMiser() {
		int[] choixPossibles = { 1, 2 };
		int choix = Outils.readInteger(choixPossibles);
		boolean choixBoolean = false;

		switch (choix) {
		case 1:
			choixBoolean = true;
			break;
		case 2:
			choixBoolean = false;
			break;
		default:
			break;
		}
		return choixBoolean;
	}

	public int choisirMontantMise() {
		int montantMax = jetons;
		int montant = Outils.readPositiveIntegerWithUpperBound(montantMax);
		return montant;
	}

	public void miser(int montant) {
		setJetons(jetons - montant);
	}
}
