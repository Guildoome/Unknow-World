package fr.controller;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import fr.modele.ModeleBase;
import fr.modele.image.ImageContainer;
import fr.vue.Ecran;
import fr.vue.EcranAcceuil;
import fr.vue.EcranCredit;
import fr.vue.EcranJoueur;
import fr.vue.EcranScore;
import fr.vue.View;

/**
 * 
 * @author JGC
 *
 */
public class ControleurPrincipal implements IControleur {
	
	private ModeleBase modele;
	private View view;
	
	public ControleurPrincipal(ModeleBase modele, View view) {
		this.modele = modele;
		this.view = view;
		view.setControleur(this);
		afficherMenuPrincipal();
	}
	
	/**
	 * Creates a new game
	 */
	public void creerNouvellePartie() {
		modele.creerJeu();
		changerEcran(new EcranJoueur(this));
		view.setPseudo(view);
	}
	
	/**
	 * Loads a game
	 * 
	 * @param fichierSauvegarde
	 * 			The save filename
	 */
	public void chargerPartie(String fichierSauvegarde) {		
		if(modele.chargerJeu(fichierSauvegarde)) {
			changerEcran(new EcranJoueur(this));
		}
	}
	
	/**
	 * Saves a game
	 * 
	 * @param fichierSauvegarde
	 * 			The save filename
	 */
	public void sauvegarderPartie(String fichierSauvegarde) {		
		modele.sauvegarderJeu(fichierSauvegarde);
	}
	
	/**
	 * Sets the name of the player
	 * 
	 * @param pseudo
	 * 			The player's name
	 */
	public void setPseudoJoueur(String pseudo) {
		modele.setPseudoJoueur(pseudo);
	}
	
	/**
	 * Gets the name of the player
	 * 
	 * @return The player's name
	 */
	public String getPseudoJoueur() {
		return modele.getPseudoJoueur();
	}
	
	/**
	 * Enable or disable saving from the menu bar
	 * 
	 * @param estActif
	 * 			Choice
	 */
	public void setMenuSauvegardeActive(boolean estActif) {
		view.setMenuSauvegardeActive(estActif);
	}
	
	/**
	 * Enable or disable quitting a game from the menu bar
	 * 
	 * @param estActif
	 * 			Choice
	 */
	public void setPartieActive(boolean estActif) {
		view.setPartieActive(estActif);
	}
	
	/**
	 * Opens a PDF that contains the game's instructions
	 */
	public void ouvrirAide() {
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File("data/manuel.pdf");
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException ex) {
		       System.err.println("aucune application pour ouvrir PDF");
		    }
		}
	}
	
	/**
	 * Displays the game's credits
	 */
	public void afficherCredit() {		
		changerEcran(new EcranCredit(this));		
	}
	
	/**
	 * 
	 * @return The handle to the credit file
	 */
	public BufferedReader getHandleCredit() {
		return modele.getHandleCredit();
	}
	
	/**
	 * Displays the game's main menu
	 */
	public void afficherMenuPrincipal() {		
		changerEcran(new EcranAcceuil(this));		
	}
	
	/**
	 * Displays a list of player score
	 * 
	 */
	public void afficherScore() {
		changerEcran(new EcranScore(this));
	}
	
	/**
	 * Unused
	 * @param nomImage unused
	 * 
	 * @return unused
	 */
	public BufferedImage getImage(String nomImage) {
		BufferedImage resultat = null;
		
		ImageContainer imgContainer = modele.getImageManager().getImage(nomImage);
		if(imgContainer != null) {
			resultat = imgContainer.getImage();
		}
		
		return resultat;
	}

	/**
	 * Changes screen
	 */
	@Override
	public void changerEcran(Ecran ecran) {
		view.getContentPane().removeAll();
		view.setContentPane(ecran);
		view.validate();
	}

	/**
	 * Gets the saved score data
	 * 
	 * @return The score data
	 */
	public ArrayList<String> getScore() {
		
		// TODO Auto-generated method stub
		return modele.getDonneeScore();
	}

	/**
	 * Returns the model
	 */
	@Override
	public ModeleBase getModele() {
		return modele;
	}

	
}
