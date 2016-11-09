package fr.modele.job;

import java.util.ArrayList;

import fr.modele.DonneeJoueurBase;
import fr.modele.batiment.Batiment;
import fr.modele.message.EMessageType;
import fr.modele.message.Message;

public class ConstructionBatimentJobItem extends JobItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7179952450203197657L;
	
	protected Batiment batiment;

	public ConstructionBatimentJobItem(Batiment batiment, ArrayList<Message> messageHistorique, DonneeJoueurBase donneeJoueur, int duration) {
		super(messageHistorique, donneeJoueur, duration);
		this.batiment = batiment;
		this.start();
	}

	public Batiment getBatiment() {
		return batiment;
	}

	/**
	 * Starts the construction of a building and takes the resources necessary
	 */
	@Override
	public void start() {
		donneeJoueur.consommerNourriture(batiment.getCoutNourriture());
		donneeJoueur.consommerEau(batiment.getCoutEau());
		donneeJoueur.consommerBois(batiment.getCoutBois());
		donneeJoueur.consommerMinerai(batiment.getCoutMinerai());
		donneeJoueur.consommerPierre(batiment.getCoutPierre());		
		donneeJoueur.envoyerUniteEnConstruction(batiment.getCoutUnite());
		donneeJoueur.ajouterPointBatiment(batiment.getPoint());
		ajouterMessage("La construction du batiment " + batiment.getTypeBatiment() + " a été lancée!", EMessageType.MESSAGE_CONSTRUCTION);
		state = EJobState.JOB_STARTED;
	}

	/**
	 * Finishes the construction of a building and returns the units used
	 */
	@Override
	public void stop() {
		if(state == EJobState.JOB_STARTED) {
			donneeJoueur.restaurerUniteEnConstruction(batiment.getCoutUnite());
			donneeJoueur.ajouterBatiment(batiment);
			ajouterMessage("La construction du batiment " + batiment.getTypeBatiment() + " est terminée!", EMessageType.MESSAGE_CONSTRUCTION);
			state = EJobState.JOB_FINISHED;
		}		
	}

	/**
	 * Unused
	 */
	@Override
	public void cancel() {
		if(state == EJobState.JOB_STARTED) {
			donneeJoueur.ajouterNourriture(batiment.getCoutNourriture());
			donneeJoueur.ajouterEau(batiment.getCoutEau());
			donneeJoueur.ajouterBois(batiment.getCoutBois());
			donneeJoueur.ajouterMinerai(batiment.getCoutMinerai());
			donneeJoueur.ajouterPierre(batiment.getCoutPierre());
			donneeJoueur.restaurerUniteEnConstruction(batiment.getCoutUnite());
			donneeJoueur.retirerPointBatiment(batiment.getPoint());
			state = EJobState.JOB_FINISHED;
		}
	}

}
