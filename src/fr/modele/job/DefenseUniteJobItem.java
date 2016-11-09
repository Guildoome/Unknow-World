package fr.modele.job;

import java.util.ArrayList;

import fr.modele.DonneeJoueurBase;
import fr.modele.message.EMessageType;
import fr.modele.message.Message;

public class DefenseUniteJobItem extends JobItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -482725280029928125L;
	
	private int nbUnite;

	public DefenseUniteJobItem(int nbUnite, ArrayList<Message> messageHistorique, DonneeJoueurBase donneeJoueur, int duration) {
		super(messageHistorique, donneeJoueur, duration);
		this.nbUnite = nbUnite;
		this.start();
	}

	/**
	 * Sends a number of unit in defense
	 */
	@Override
	public void start() {
		donneeJoueur.envoyerUniteEnDefense(nbUnite);
		ajouterMessage(nbUnite + " unités sont partie en defense!", EMessageType.MESSAGE_DEFENSE);
		state = EJobState.JOB_STARTED;
	}

	/**
	 * Call back a number of unit from defense
	 */
	@Override
	public void stop() {
		if(state == EJobState.JOB_STARTED) {
			donneeJoueur.restaurerUniteEnDefense(nbUnite);
			state = EJobState.JOB_FINISHED;
		}
	}

	/**
	 * Unused
	 */
	@Override
	public void cancel() {
		if(state == EJobState.JOB_STARTED) {
			donneeJoueur.restaurerUniteEnDefense(nbUnite);
			state = EJobState.JOB_FINISHED;
		}
	}

}
