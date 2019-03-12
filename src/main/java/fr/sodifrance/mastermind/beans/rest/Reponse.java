package fr.sodifrance.mastermind.beans.rest;

public class Reponse {
	
	private String etat;
	
	private Integer numProposition;

	public Reponse(String etat, Integer numProposition) {
		super();
		this.etat = etat;
		this.numProposition = numProposition;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Integer getNumProposition() {
		return numProposition;
	}

	public void setNumProposition(Integer numProposition) {
		this.numProposition = numProposition;
	}
}
