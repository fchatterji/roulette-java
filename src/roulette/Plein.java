package roulette;

class Plein extends Mise {

	@Override
	public void setNumerosMises(String annonce) {
		numeros = new int[] { Integer.parseInt(annonce) };
	}

	@Override
	public void setMultiplicateur() {
		multiplicateurDeGain = 35;
	}

	@Override
	public void setExplication() {
		explication = "Entrez un seul numéro.";
	}

	@Override
	public void validerAnnonce(String annonce) {
		if (!annonce.matches("^[0-9]+$")) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void validerNumerosMises(TableDeJeu tableDeJeu) {
		if (numeros[0] < 1 || numeros[0] > 36) {
			throw new IllegalArgumentException();
		}
	}
}
