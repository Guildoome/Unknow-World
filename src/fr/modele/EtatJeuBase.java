package fr.modele;

import java.io.Serializable;
import java.util.ArrayList;

import fr.modele.batiment.TypeBatiment;
import fr.modele.job.JobManager;
import fr.modele.message.EMessageType;
import fr.modele.message.Message;

/**
 * 
 * @author JGC
 *
 */
public class EtatJeuBase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1065665552514814882L;
	
	private transient ArrayList<TypeBatiment> listeTypeBatiment;
	private transient ArrayList<Evenement> listeEvenementAttaque;
	private transient ArrayList<Evenement> listeEvenementExploration;
	private transient ArrayList<Message> messageHistorique;
	
	private DonneeJoueurBase donneeJoueur;
	private DonneeJeuBase donneeJeu;
	
	private JobManager jobManager;

	public EtatJeuBase() {
		listeTypeBatiment = new ArrayList<>();
		listeEvenementAttaque = new ArrayList<>();
		listeEvenementExploration =  new ArrayList<>();
		messageHistorique = new ArrayList<>();
		jobManager = new JobManager();
		
	}

	public ArrayList<TypeBatiment> getListeTypeBatiment() {
		return listeTypeBatiment;
	}

	public void setListeTypeBatiment(ArrayList<TypeBatiment> listeTypeBatiment) {
		this.listeTypeBatiment = listeTypeBatiment;
	}

	public ArrayList<Evenement> getListeEvenementAttaque() {
		return listeEvenementAttaque;
	}

	public void setListeEvenementAttaque(ArrayList<Evenement> listeEvenementAttaque) {
		this.listeEvenementAttaque = listeEvenementAttaque;
	}

	public ArrayList<Evenement> getListeEvenementExploration() {
		return listeEvenementExploration;
	}

	public void setListeEvenementExploration(ArrayList<Evenement> listeEvenementExploration) {
		this.listeEvenementExploration = listeEvenementExploration;
	}

	public ArrayList<Message> getMessageHistorique() {
		return messageHistorique;
	}

	public void setMessageHistorique(ArrayList<Message> messageHistorique) {
		this.messageHistorique = messageHistorique;
	}
	
	public void ajouterMessage(String message, EMessageType typeMessage) {
		if(messageHistorique != null) {
			messageHistorique.add(0, new Message(message, typeMessage));
		}
	}

	public DonneeJoueurBase getDonneeJoueur() {
		return donneeJoueur;
	}

	public void setDonneeJoueur(DonneeJoueurBase donneeJoueur) {
		this.donneeJoueur = donneeJoueur;
	}

	public DonneeJeuBase getDonneeJeu() {
		return donneeJeu;
	}

	public void setDonneeJeu(DonneeJeuBase donneeJeu) {
		this.donneeJeu = donneeJeu;
	}

	public JobManager getJobManager() {
		return jobManager;
	}

	public void setJobManager(JobManager jobManager) {
		this.jobManager = jobManager;
	}

}
