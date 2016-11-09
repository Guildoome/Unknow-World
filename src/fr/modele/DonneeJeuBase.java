package fr.modele;

import java.io.Serializable;
import java.util.Random;

/**
 * 
 * @author JGC
 *
 */
public class DonneeJeuBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -972614621289559766L;
	
	private int nbTourJoue;
	private int nbTourAvantAttaque;
	private int nbTourTotal;

	public DonneeJeuBase(int nbTourTotal) {
		super();
		nbTourJoue = 1;
		this.nbTourTotal = nbTourTotal;
	}

	public int getNbTourTotal() {
		return nbTourTotal;
	}
	
	public void setNbTourTotal(int nbTourTotal) {
		this.nbTourTotal = nbTourTotal;
	}

	public int getNbTourJoue() {
		return nbTourJoue;
	}

	/**
	 * Increments the turn counter
	 */
	public void incrementerTourJoue() {
		nbTourJoue++;
	}
	
	public int getNbTourAvantAttaque() {
		return nbTourAvantAttaque;
	}
	
	/**
	 * Decrements the attack counter
	 */
	public void decrementerNbTourAvantAttaque() {
		nbTourAvantAttaque--;
	}
	
	
	/**
	 * Sets a counter for a attack
	 */
	public void setNbTourAvantAttaque() {
		Random random = new Random();
		nbTourAvantAttaque = random.nextInt(11) + 5;
	}
	
	/**
	 * Checks if the number of turn played has reached the total number of turns allowed
	 * 
	 * @return The result
	 */
	public boolean objectifAtteint() {
		boolean resultat = false;
		
		if(nbTourJoue >= nbTourTotal) {
			resultat = true;
		}
		
		return resultat;
	}

}
