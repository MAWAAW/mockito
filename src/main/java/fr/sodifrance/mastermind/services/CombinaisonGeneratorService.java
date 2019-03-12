package fr.sodifrance.mastermind.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import fr.sodifrance.mastermind.beans.plateau.Combinaison;
import fr.sodifrance.mastermind.beans.plateau.Couleur;

@Service
public class CombinaisonGeneratorService {

	private static final int VALEUR_MIN = 0;
	private static final int VALEUR_MAX = 7;

	private ArrayList<String> choix = new ArrayList<>();

	public CombinaisonGeneratorService() {
		choix.add("bleu");
		choix.add("jaune");
		choix.add("rouge");
		choix.add("vert");
		choix.add("orange");
		choix.add("violet");
		choix.add("noir");
		choix.add("blanc");
	}

	public Combinaison genererCombinaison() throws Exception {
		ArrayList<Couleur> couleurs = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			Couleur couleur = createRandomCouleur();
			if(choix.contains(couleur.getNom()))
				couleurs.add(couleur);
			else
				throw new Exception();
		}

		return new Combinaison(couleurs);
	}

	public Couleur createRandomCouleur() throws Exception {
		Random r = new Random();
		int index = VALEUR_MIN + r.nextInt(VALEUR_MAX - VALEUR_MIN);

		return new Couleur(choix.get(index));
	}
}
