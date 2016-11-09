package fr.controller;

import javax.swing.JLabel;

import fr.modele.DonneeJoueurScore;
import fr.modele.ModeleBase;
import fr.vue.Ecran;
import fr.vue.Vue;

public class ControleurVueScore extends ControleurVue {

	public ControleurVueScore(IControleur controleur, Vue vue) {
		super(controleur, vue);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Updates the display of the player score
	 * 
	 * @param scoreTotal
	 * 			The component to update that displays the player's total score
	 * 
	 * @param scoreUnite
	 * 			The component to update that displays the player's unit score
	 * 
	 * @param scoreTour
	 * 			The component to update that displays the player's turn score
	 * 
	 * @param scoreBatiment
	 * 			The component to update that displays the player's building score
	 * 
	 * @param scorePierre
	 * 			The component to update that displays the player's stone score
	 * 
	 * @param scoreEau
	 * 			The component to update that displays the player's water score
	 * 
	 * @param scoreNourriture
	 * 			The component to update that displays the player's food score
	 * 
	 * @param scoreBois
	 * 			The component to update that displays the player's wood score
	 * 
	 * @param scoreMinerai
	 * 			The component to update that displays the player's ore score
	 */
	public void updateScore(JLabel scoreTotal, JLabel scoreUnite, JLabel scoreTour,
			JLabel scoreBatiment, JLabel scorePierre, JLabel scoreEau,
			JLabel scoreNourriture, JLabel scoreBois, JLabel scoreMinerai) {
		DonneeJoueurScore score = modele.getScoreJoueur();
		
		if((scoreTotal != null) && (scoreUnite != null) && (scoreTour != null) &&
			(scoreBatiment != null) && (scorePierre != null) && (scoreEau != null) &&
			(scoreNourriture != null) && (scoreBois != null) && (scoreMinerai != null)) {
			scoreTotal.setText("Score Total :" + score.getPtTotal());
			scoreUnite.setText("Point Unite :" + score.getPtUnite());
			scoreTour.setText("Point Tour :" + score.getPtTour());
			scoreBatiment.setText("Point Batiment : " + score.getPtBatiment());
			scorePierre.setText("Point Pierre : " + score.getPtPierre());
			scoreEau.setText("Point Eau :" + score.getPtEau());
			scoreNourriture.setText("Point Nourriture : " + score.getPtNourriture());
			scoreBois.setText("Point Bois : " + score.getPtBois());
			scoreMinerai.setText("Point Minerai : " + score.getPtMinerai());
		}
	}

	@Override
	public ModeleBase getModele() {
		return modele;
	}

	@Override
	public void changerEcran(Ecran ecran) {
		// TODO Auto-generated method stub

	}

}
