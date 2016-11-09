package fr.modele.ressource;

public class RessourceJoueur {

	private int ressourceActuelle;
	private int ressourceTotale;
	
	public RessourceJoueur(int ressourceActuelle, int ressourceTotale) {
		super();
		this.ressourceActuelle = ressourceActuelle;
		this.ressourceTotale = ressourceTotale;
	}

	public int getRessourcePremiere() {
		return ressourceActuelle;
	}

	public int getRessourceSecondaire() {
		return ressourceTotale;
	}	
	
}
