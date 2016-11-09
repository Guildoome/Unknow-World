package fr.modele.job;

import java.util.ArrayList;

import fr.modele.DonneeJoueurBase;
import fr.modele.batiment.Batiment;
import fr.modele.batiment.TypeBatiment;
import fr.modele.message.EMessageType;
import fr.modele.message.Message;

/**
 * 
 * @author JGC
 *
 */
public class EvolutionBatimentJobItem extends JobItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1453793043622829506L;
	
	private TypeBatiment typeBatiment;
	private Batiment batiment;
	
	private float attaque;
	private float defense;
	private float consommation;
	private float stockage;
	
	public EvolutionBatimentJobItem(TypeBatiment typeBatiment, Batiment batiment, ArrayList<Message> messageHistorique, DonneeJoueurBase donneeJoueur, int duration) {
		super(messageHistorique, donneeJoueur, duration);
		this.batiment = batiment;
		this.typeBatiment = typeBatiment;
		this.start();
	}

	/**
	 * Starts the evolution of a building and takes the resources necessary
	 */
	@Override
	public void start() {
		calculerConsommation();
		
		donneeJoueur.consommerNourriture((int)(typeBatiment.getCoutNourriture() * consommation));
		donneeJoueur.consommerEau((int)(batiment.getCoutEau() * consommation));
		donneeJoueur.consommerBois((int)(batiment.getCoutBois() * consommation));
		donneeJoueur.consommerMinerai((int)(batiment.getCoutMinerai() * consommation));
		donneeJoueur.consommerPierre((int)(batiment.getCoutPierre() * consommation));		
		donneeJoueur.envoyerUniteEnConstruction((int)(batiment.getCoutUnite() * consommation));
		donneeJoueur.ajouterPointBatiment((int)(batiment.getPoint() * consommation));
		ajouterMessage("L'evolution du batiment " + batiment.getTypeBatiment() + " de niveau" + (batiment.getNiveau() + 1) + " a été lancée!", EMessageType.MESSAGE_CONSTRUCTION);
		state = EJobState.JOB_STARTED;
	}

	/**
	 * Finishes the evolution of a building and returns the units used
	 */
	@Override
	public void stop() {
		if(state == EJobState.JOB_STARTED) {
			calculerAttaque();
			calculerDefense();
			calculerStockage();
			
			donneeJoueur.restaurerUniteEnConstruction((int)(batiment.getCoutUnite() * consommation));
			
			batiment.setCoutNourriture((int)(typeBatiment.getCoutNourriture() * consommation));
			batiment.setCoutEau((int)(batiment.getCoutEau() * consommation));
			batiment.setCoutBois((int)(batiment.getCoutBois() * consommation));
			batiment.setCoutMinerai((int)(batiment.getCoutMinerai() * consommation));
			batiment.setCoutPierre((int)(batiment.getCoutPierre() * consommation));
			batiment.setCoutUnite((int)(batiment.getCoutUnite() * consommation));
			
			batiment.setStockageNourriture((int)(batiment.getStockageNourriture() * stockage));
			batiment.setStockageEau((int)(batiment.getStockageEau() * stockage));
			batiment.setStockageBois((int)(batiment.getStockageBois() * stockage));
			batiment.setStockageMinerai((int)(batiment.getStockageMinerai() * stockage));
			batiment.setStockagePierre((int)(batiment.getStockagePierre() * stockage));
			
			batiment.setAttaque((int)(batiment.getAttaque() * attaque));
			batiment.setDefense((int)(batiment.getDefense() * defense));
			batiment.setNiveau(batiment.getNiveau() + 1);
						
			ajouterMessage("L'evolution du batiment " + batiment.getTypeBatiment() +  " de niveau" + batiment.getNiveau() + " est terminée!", EMessageType.MESSAGE_CONSTRUCTION);
			state = EJobState.JOB_FINISHED;
		}		
	}

	/**
	 * Unused
	 */
	@Override
	public void cancel() {
		if(state == EJobState.JOB_STARTED) {
			donneeJoueur.ajouterNourriture((int)(batiment.getCoutNourriture() * consommation));
			donneeJoueur.ajouterEau((int)(batiment.getCoutEau() * consommation));
			donneeJoueur.ajouterBois((int)(batiment.getCoutBois() * consommation));
			donneeJoueur.ajouterMinerai((int)(batiment.getCoutMinerai() * consommation));
			donneeJoueur.ajouterPierre((int)(batiment.getCoutPierre() * consommation));
			donneeJoueur.restaurerUniteEnConstruction((int)(batiment.getCoutUnite() * consommation));
			donneeJoueur.retirerPointBatiment((int)(batiment.getPoint() * consommation));
			state = EJobState.JOB_FINISHED;
		}
	}
	
	/**
	 * Calculate the rate of consumption
	 */
	private void calculerConsommation() {
		float fConsommation = 1;
		
		for(int i = 1; i < batiment.getNiveau() + 1; i++) {
			fConsommation += fConsommation * ((float)typeBatiment.getAugmentationCout()) / 100.0f;
		}
		
		consommation = fConsommation;
	}
	
	/**
	 * Calculate the rate of attack
	 */
	private void calculerAttaque() {
		float fConsommation = 1;
		
		for(int i = 1; i < batiment.getNiveau() + 1; i++) {
			fConsommation += fConsommation * ((float)typeBatiment.getAugmentationAttaque()) / 100.0f;
		}
		
		attaque = fConsommation;
	}
	
	/**
	 * Calculate the rate of defense
	 */
	private void calculerDefense() {
		float fConsommation = 1;
		
		for(int i = 1; i < batiment.getNiveau() + 1; i++) {
			fConsommation += fConsommation * ((float)typeBatiment.getAugmentationDefense()) / 100.0f;
		}
		
		defense = fConsommation;
	}
	
	/**
	 * Calculate the rate of stock
	 */
	private void calculerStockage() {
		float fConsommation = 1;
		
		for(int i = 1; i < batiment.getNiveau() + 1; i++) {
			fConsommation += fConsommation * ((float)typeBatiment.getAugmentationStockage()) / 100.0f;
		}
		
		stockage = fConsommation;
	}

}
