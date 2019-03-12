package fr.sodifrance.mastermind.beans.plateau;

import java.util.ArrayList;
import java.util.List;

public class Combinaison {

	private List<Couleur> couleurs;

	public Combinaison(List<Couleur> couleurs) {
		this.couleurs = couleurs;
	}
	
	public Combinaison() {
		this.couleurs = new ArrayList<Couleur>();
	}

	public List<Couleur> getCouleurs() {
		return couleurs;
	}

	public void setCouleurs(List<Couleur> couleurs) {
		this.couleurs = couleurs;
	}
}
