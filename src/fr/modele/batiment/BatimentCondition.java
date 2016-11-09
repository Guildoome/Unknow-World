package fr.modele.batiment;

public class BatimentCondition {

	private String condition;
	private int niveau;
	
	public BatimentCondition(String condition, int niveau) {
		super();
		this.condition = condition;
		this.niveau = niveau;
	}

	public String getCondition() {
		return condition;
	}

	public int getNiveau() {
		return niveau;
	}	

}
