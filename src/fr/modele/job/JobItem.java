package fr.modele.job;

import java.io.Serializable;
import java.util.ArrayList;

import fr.modele.DonneeJoueurBase;
import fr.modele.message.EMessageType;
import fr.modele.message.Message;

/**
 * 
 * @author JGC
 *
 */
public abstract class JobItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6096693959292534385L;
	
	protected DonneeJoueurBase donneeJoueur;
	private transient ArrayList<Message> messageHistorique;
	protected int duration;
	protected EJobState state;
	
	public JobItem(ArrayList<Message> messageHistorique, DonneeJoueurBase donneeJoueur, int duration) {
		super();
		this.messageHistorique = messageHistorique;
		this.donneeJoueur = donneeJoueur;
		this.duration = duration;
	}
	
	public DonneeJoueurBase getDonneeJoueur() {
		return donneeJoueur;
	}

	public int getDuration() {
		return duration;
	}

	public EJobState getState() {
		return state;
	}

	/**
	 * Decrements the duration of the job and if the duration reaches 0 the job finishes
	 */
	public void execute() {
		duration--;
		
		if(duration <= 0) {
			this.stop();
		}
	}
	
	/**
	 * Adds a message to the history
	 * 
	 * @param message
	 * 			The message text
	 * 
	 * @param typeMessage
	 * 			The type of the message
	 */
	protected void ajouterMessage(String message, EMessageType typeMessage) {
		if(messageHistorique != null) {
			messageHistorique.add(0, new Message(message, typeMessage));
		}
	}
	
	public abstract void start();
	public abstract void stop();
	public abstract void cancel();
}
