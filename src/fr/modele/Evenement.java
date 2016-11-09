package fr.modele;

/**
 * 
 * @author JGC
 *
 */
public class Evenement {	
	
	private int numero;
	private String message;
	private int perte;
	private int gainNouriture;
	private int gainEau;
	private int gainBois;
	private int gainMinerai;
	private int gainPierre;
	
	public Evenement(int numero, String message, int perte, int gainNouriture, int gainEau, int gainBois,
			int gainMinerai, int gainPierre) {
		super();
		this.numero = numero;
		this.message = message;
		this.perte = perte;
		this.gainNouriture = gainNouriture;
		this.gainEau = gainEau;
		this.gainBois = gainBois;
		this.gainMinerai = gainMinerai;
		this.gainPierre = gainPierre;
	}
    
	public int getNumero() {
		return numero;
	}
    
	public void setNumero(int numero) {
		this.numero = numero;
	}
    
	public String getMessage() {
		return message;
	}
    
	public void setMessage(String message) {
		this.message = message;
	}
    
	public int getPerte() {
		return perte;
	}
    
	public void setPerte(int perte) {
		this.perte = perte;
	}
    
	public int getGainNouriture() {
		return gainNouriture;
	}
    
	public void setGainNouriture(int gainNouriture) {
		this.gainNouriture = gainNouriture;
	}
    
	public int getGainEau() {
		return gainEau;
	}
    
	public void setGainEau(int gainEau) {
		this.gainEau = gainEau;
	}
    
	public int getGainBois() {
		return gainBois;
	}
    
	public void setGainBois(int gainBois) {
		this.gainBois = gainBois;
	}
    
	public int getGainMinerai() {
		return gainMinerai;
	}
    
	public void setGainMinerai(int gainMinerai) {
		this.gainMinerai = gainMinerai;
	}
    
	public int getGainPierre() {
		return gainPierre;
	}
    
	public void setGainPierre(int gainPierre) {
		this.gainPierre = gainPierre;
	}
	
	public String toString() {
		String result = "";
		result += this.getMessage();
		return result;
	}	
}