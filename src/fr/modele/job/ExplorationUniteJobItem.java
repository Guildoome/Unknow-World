package fr.modele.job;

import java.util.ArrayList;
import java.util.Random;

import fr.modele.DonneeJoueurBase;
import fr.modele.Evenement;
import fr.modele.message.EMessageType;
import fr.modele.message.Message;

public class ExplorationUniteJobItem extends JobItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2785796950803836885L;
	
	private int nbUnite;
	
	private ArrayList<Evenement> listeEvenement;

	public ExplorationUniteJobItem(int nbUnite, ArrayList<Evenement> listeEvenement, ArrayList<Message> messageHistorique, DonneeJoueurBase donneeJoueur, int duration) {
		super(messageHistorique, donneeJoueur, duration);
		this.nbUnite = nbUnite;
		this.listeEvenement = listeEvenement;
		this.start();
	}

	/**
	 * Sends a number of unit in exploration
	 */
	@Override
	public void start() {
		donneeJoueur.envoyerUniteEnExploration(nbUnite);
		ajouterMessage(nbUnite + " unités sont partie en exploration!", EMessageType.MESSAGE_EXPLORATION);
		state = EJobState.JOB_STARTED;
	}

	/**
	 * Call back a number of unit from exploration
	 */
	@Override
	public void stop() {
		if(state == EJobState.JOB_STARTED) {
			lancerEvementExploration();
			donneeJoueur.restaurerUniteEnExploration(nbUnite);
			state = EJobState.JOB_FINISHED;
		}
	}

	/**
	 * Unused
	 */
	@Override
	public void cancel() {
		if(state == EJobState.JOB_STARTED) {
			donneeJoueur.restaurerUniteEnExploration(nbUnite);
			state = EJobState.JOB_FINISHED;
		}
	}
	
	/**
	 * Launches a exploration job
	 */
	private void lancerEvementExploration() {
		Random random = new Random();
		
		int numeroEvenement = random.nextInt(listeEvenement.size());
		Evenement evenement = listeEvenement.get(numeroEvenement);
		
		ajouterMessage(evenement.getMessage(), EMessageType.MESSAGE_EXPLORATION);
		
		if(evenement.getPerte() > 0) {
			int perte = nbUnite * (random.nextInt(46) + 25) / 100;
			nbUnite -= perte;
			donneeJoueur.detruireUniteEnExploration(perte);
			ajouterMessage("Ils y a eu " + perte + " pertes.", EMessageType.MESSAGE_EXPLORATION);
		}
		
		if(evenement.getGainNouriture() > 0) {
			int gain = (nbUnite + donneeJoueur.getNourritureMax() * (random.nextInt(16) + 15) / 100);
			donneeJoueur.ajouterNourriture(gain);
			ajouterMessage("Ils rapportent " + gain + " unités de nourriture.", EMessageType.MESSAGE_EXPLORATION);
		}
		
		if(evenement.getGainEau() > 0) {
			int gain = (nbUnite + donneeJoueur.getEauMax() * (random.nextInt(16) + 15) / 100);
			donneeJoueur.ajouterEau(gain);
			ajouterMessage("Ils rapportent " + gain + " unités d'eau.", EMessageType.MESSAGE_EXPLORATION);
		}

		if(evenement.getGainBois() > 0) {
			int gain = (nbUnite + donneeJoueur.getBoisMax() * (random.nextInt(16) + 15) / 100);
			donneeJoueur.ajouterBois(gain);
			ajouterMessage("Ils rapportent " + gain + " unités de bois.", EMessageType.MESSAGE_EXPLORATION);
		}

		if(evenement.getGainMinerai() > 0) {
			int gain = (nbUnite + donneeJoueur.getMineraiMax() * (random.nextInt(16) + 15) / 100);
			donneeJoueur.ajouterMinerai(gain);
			ajouterMessage("Ils rapportent " + gain + " unités de minerai.", EMessageType.MESSAGE_EXPLORATION);
		}

		if(evenement.getGainPierre() > 0) {
			int gain = (nbUnite + donneeJoueur.getPierreMax() * (random.nextInt(16) + 15) / 100);
			donneeJoueur.ajouterPierre(gain);
			ajouterMessage("Ils rapportent " + gain + " unités de pierre.", EMessageType.MESSAGE_EXPLORATION);
		}
	}
}
