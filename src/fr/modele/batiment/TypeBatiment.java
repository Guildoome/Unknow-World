package fr.modele.batiment;

import java.util.ArrayList;

public class TypeBatiment {

	private String typeBatiment;
	
	private String description;
	
	private int defense;
	private int attaque;
	
	private int coutNourriture;
	private int coutEau;
	private int coutBois;
	private int coutMinerai;
	private int coutPierre;
	private int coutUnite;
	
	private int stockageNourriture;
	private int stockageEau;
	private int stockageBois;
	private int stockageMinerai;
	private int stockagePierre;
	
	private ArrayList<BatimentCondition> listeCondition;
	
	private float augmentationAttaque;
	private float augmentationDefense;
	private float augmentationCout;
	private float augmentationStockage;
	private int augmentationTour;
	
	private int niveauMax;
	
	private int point;
	private int nbTour;
	
	public TypeBatiment(String typeBatiment, String description, int defense, int attaque, int coutNourriture, int coutEau, int coutBois,
			int coutMinerai, int coutPierre, int coutUnite, int stockageNourriture, int stockageEau, int stockageBois,
			int stockageMinerai, int stockagePierre, ArrayList<BatimentCondition> listeCondition, float augmentationAttaque,
			float augmentationDefense, float augmentationCout, float augmentationStockage, int augmentationTour,
			int niveauMax, int point, int nbTour) {
		super();
		this.typeBatiment = typeBatiment;
		this.description = description;
		this.defense = defense;
		this.attaque = attaque;
		this.coutNourriture = coutNourriture;
		this.coutEau = coutEau;
		this.coutBois = coutBois;
		this.coutMinerai = coutMinerai;
		this.coutPierre = coutPierre;
		this.coutUnite = coutUnite;
		this.stockageNourriture = stockageNourriture;
		this.stockageEau = stockageEau;
		this.stockageBois = stockageBois;
		this.stockageMinerai = stockageMinerai;
		this.stockagePierre = stockagePierre;
		this.listeCondition = listeCondition;
		this.augmentationAttaque = augmentationAttaque;
		this.augmentationDefense = augmentationDefense;
		this.augmentationCout = augmentationCout;
		this.augmentationStockage = augmentationStockage;
		this.augmentationTour = augmentationTour;
		this.niveauMax = niveauMax;
		this.point = point;
		this.nbTour = nbTour;
	}

	public String getTypeBatiment() {
		return typeBatiment;
	}
	
	public void setTypeBatiment(String typeBatiment) {
		this.typeBatiment = typeBatiment;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDefense() {
		return defense;
	}
	
	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public int getAttaque() {
		return attaque;
	}
	
	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}
	
	public int getCoutNourriture() {
		return coutNourriture;
	}
	
	public void setCoutNourriture(int coutNourriture) {
		this.coutNourriture = coutNourriture;
	}
	
	public int getCoutEau() {
		return coutEau;
	}
	
	public void setCoutEau(int coutEau) {
		this.coutEau = coutEau;
	}
	
	public int getCoutBois() {
		return coutBois;
	}
	
	public void setCoutBois(int coutBois) {
		this.coutBois = coutBois;
	}
	
	public int getCoutMinerai() {
		return coutMinerai;
	}
	
	public void setCoutMinerai(int coutMinerai) {
		this.coutMinerai = coutMinerai;
	}
	
	public int getCoutPierre() {
		return coutPierre;
	}
	
	public void setCoutPierre(int coutPierre) {
		this.coutPierre = coutPierre;
	}
	
	public int getCoutUnite() {
		return coutUnite;
	}
	
	public void setCoutUnite(int coutUnite) {
		this.coutUnite = coutUnite;
	}
	
	public int getStockageNourriture() {
		return stockageNourriture;
	}
	
	public void setStockageNourriture(int stockageNourriture) {
		this.stockageNourriture = stockageNourriture;
	}
	
	public int getStockageEau() {
		return stockageEau;
	}
	
	public void setStockageEau(int stockageEau) {
		this.stockageEau = stockageEau;
	}
	
	public int getStockageBois() {
		return stockageBois;
	}
	
	public void setStockageBois(int stockageBois) {
		this.stockageBois = stockageBois;
	}
	
	public int getStockageMinerai() {
		return stockageMinerai;
	}
	
	public void setStockageMinerai(int stockageMinerai) {
		this.stockageMinerai = stockageMinerai;
	}
	
	public int getStockagePierre() {
		return stockagePierre;
	}
	
	public void setStockagePierre(int stockagePierre) {
		this.stockagePierre = stockagePierre;
	}

	public ArrayList<BatimentCondition> getCondition() {
		return listeCondition;
	}

	public void setCondition(ArrayList<BatimentCondition> listeCondition) {
		this.listeCondition = listeCondition;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}

	public float getAugmentationAttaque() {
		return augmentationAttaque;
	}

	public void setAugmentationAttaque(float augmentationAttaque) {
		this.augmentationAttaque = augmentationAttaque;
	}

	public float getAugmentationDefense() {
		return augmentationDefense;
	}

	public void setAugmentationDefense(float augmentationDefense) {
		this.augmentationDefense = augmentationDefense;
	}

	public float getAugmentationCout() {
		return augmentationCout;
	}

	public void setAugmentationCout(float augmentationCout) {
		this.augmentationCout = augmentationCout;
	}

	public float getAugmentationStockage() {
		return augmentationStockage;
	}

	public void setAugmentationStockage(float augmentationStockage) {
		this.augmentationStockage = augmentationStockage;
	}

	public int getAugmentationTour() {
		return augmentationTour;
	}

	public void setAugmentationTour(int augmentationTour) {
		this.augmentationTour = augmentationTour;
	}

	public int getNiveauMax() {
		return niveauMax;
	}

	public void setNiveauMax(int niveauMax) {
		this.niveauMax = niveauMax;
	}
	
}
