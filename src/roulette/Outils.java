package roulette;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Outils {
	/*
	 * Comporte quelques fonctions utilitaires pour lire l'input de
	 * l'utilisateur et travailler avec des arrays
	 */
	public static String readString() {
		// retourne un string entré par l'utilisateur,
		// message d'erreur s'il y a une exception (si l'utilisateur n'a pas
		// entré un string)
		while (true) {
			try {
				BufferedReader BufferLecture = new BufferedReader(new InputStreamReader(System.in));
				return BufferLecture.readLine();
			} catch (IOException e) {
				System.out.println("Vous devez entrer un texte au bon format.");
				System.out.print("> ");
				continue;
			}
		}
	}

	static int readInteger() {
		// retourne un int entré par l'utilisateur
		// message d'erreur si ce n'est pas un int
		while (true) {
			try {
				BufferedReader BufferLecture = new BufferedReader(new InputStreamReader(System.in));
				return Integer.parseInt(BufferLecture.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("Vous devez entrer un numéro au bon format.");
				System.out.print("> ");
				continue;
			}
		}
	}

	static int readInteger(int[] possibleValues) {
		// retourne un int entré par l'utilisateur
		// message d'erreur si ce n'est pas un int, et si l'int n'est pas dans
		// la liste des
		// valeurs possibles entrées en paramètre
		int result = 0;

		while (true) {
			try {
				BufferedReader BufferLecture = new BufferedReader(new InputStreamReader(System.in));
				result = Integer.parseInt(BufferLecture.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("Vous devez entrer un numéro au bon format.");
				System.out.print("> ");
				continue;
			}

			if (!contains(possibleValues, result)) {
				System.out.println("Ce numéro n'est pas autorisé.");
				System.out.print("> ");
				continue;
			}

			return result;
		}
	}

	static int readPositiveInteger() {
		// retourne un int entré par l'utilisateur
		// message d'erreur si ce n'est pas un int, et si l'int n'est pas
		// positif
		int result = 0;

		while (true) {
			try {
				BufferedReader BufferLecture = new BufferedReader(new InputStreamReader(System.in));
				result = Integer.parseInt(BufferLecture.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("Vous devez entrer un numéro au bon format.");
				System.out.print("> ");
				continue;
			}

			if (!(result > 0)) {
				System.out.println("Ce numéro n'est pas autorisé.");
				System.out.print("> ");
				continue;
			}
			return result;
		}
	}

	static int readPositiveIntegerWithUpperBound(int max) {
		// retourne un int entré par l'utilisateur
		// message d'erreur si ce n'est pas un int, si l'int n'est pas positif,
		// et si l'int est
		// supérieur à la valeur max entrée en paramètre
		int result = 0;

		while (true) {
			try {
				BufferedReader BufferLecture = new BufferedReader(new InputStreamReader(System.in));
				result = Integer.parseInt(BufferLecture.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("Vous devez entrer un numéro au bon format.");
				System.out.print("> ");
				continue;
			}

			if (!(result > 0)) {
				System.out.println("Ce numéro n'est pas autorisé.");
				System.out.print("> ");
				continue;
			}

			if (result > max) {
				System.out.println("Le numéro dépasse le montant maximum autorisés.");
				System.out.print("> ");
				continue;
			}
			return result;
		}
	}

	static boolean contains(int[] array, int numero) {
		// vérifie si un array d'int contient un int particulier
		return IntStream.of(array).anyMatch(x -> x == numero);
	}
}