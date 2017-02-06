package roulette;

class QuatrePremiers extends Mise {

	@Override
	public void setNumerosMises(String annonce) {
		numeros = new int[] { 0, 1, 2, 3 };
	}

	@Override
	public void setMultiplicateur() {
		multiplicateurDeGain = 8;
	}

	@Override
	public void setExplication() {
		explication = "Vous allez miser sur les chiffres 0, 1, 2 et 3.\n" + "Appuyez sur une touche pour confirmer.";
	}

	@Override
	public void validerAnnonce(String annonce) {
	}

	@Override
	public void validerNumerosMises(TableDeJeu tableDeJeu) {
	}
}