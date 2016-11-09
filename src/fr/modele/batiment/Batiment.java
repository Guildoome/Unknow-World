package fr.modele.batiment;

import java.io.Serializable;

/**
 * 
 * @author JGC
 *
 */
public class Batiment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4934280323244142582L;

	public static int nombreBatimentCree = 0;
	
	private String typeBatiment;
	
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
	
	private int niveau;
	
	private int point;
	
	public Batiment(String typeBatiment, int defense, int attaque, int coutNourriture, int coutEau, int coutBois,
			int coutMinerai, int coutPierre, int coutUnite, int stockageNourriture, int stockageEau, int stockageBois,
			int stockageMinerai, int stockagePierre, int point) {
		super();
		this.typeBatiment = typeBatiment;
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
		this.point = point;
		this.niveau = 1;
		
		nombreBatimentCree++;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getTypeBatiment() {
		return typeBatiment;
	}

	public void setTypeBatiment(String typeBatiment) {
		this.typeBatiment = typeBatiment;
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

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public void setStockagePierre(int stockagePierre) {
		this.stockagePierre = stockagePierre;
	}

}
