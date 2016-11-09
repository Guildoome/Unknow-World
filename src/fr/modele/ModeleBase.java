package fr.modele;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import fr.modele.batiment.Batiment;
import fr.modele.batiment.BatimentCondition;
import fr.modele.batiment.TypeBatiment;
import fr.modele.image.ImageManager;
import fr.modele.job.ConstructionBatimentJobItem;
import fr.modele.job.DefenseUniteJobItem;
import fr.modele.job.EvolutionBatimentJobItem;
import fr.modele.job.ExplorationUniteJobItem;
import fr.modele.message.EMessageType;
import fr.modele.message.Message;
import fr.modele.ressource.ERessourceJoueur;
import fr.modele.ressource.RessourceJoueur;

/**
 * 
 * @author JGC
 *
 */
public abstract class ModeleBase {
	
	protected String cheminDonnee;
	
	private String cheminCredit = "/credit.csv";
	
	protected EtatJeuBase etatJeu;
	
	protected ImageManager imgMgr;

	/**
	 * Constructor ModelBase
	 * @param cheminDonnee
	 * 			The path to the data on the disk
	 */
	public ModeleBase(String cheminDonnee) {
		this.cheminDonnee = cheminDonnee;
		imgMgr = new ImageManager(cheminDonnee);
	}
	
	/**
	 * Create a new game
	 */
	public void creerJeu() {
		etatJeu = new EtatJeuBase();
		if(etatJeu != null) {
			getDonneeTypeBatiment();
			getDonneeEvenementAttaque();
			getDonneeEvenementExploration();
			etatJeu.setDonneeJeu(new DonneeJeuBase(100));
			etatJeu.getDonneeJeu().setNbTourAvantAttaque();
			etatJeu.setDonneeJoueur(new DonneeJoueurBase("", 250, 250, 250, 0, 0, 100));
			construireBatiment("Vaisseau");
			etatJeu.getJobManager().getListeJobItem().get(0).stop();
			etatJeu.getDonneeJoueur().miseAJourRessource();
			
		}
	}
	
	/**
	 * Saves the game
	 * 
	 * @param nomFichier
	 * 			The name of the file to save
	 * 
	 * @return the result of the saving action
	 */
	public boolean sauvegarderJeu(String nomFichier) {
		boolean resultat = false;
				
		ObjectOutputStream writer = null;
		
		try {
			writer = new ObjectOutputStream(new FileOutputStream(nomFichier));
			writer.writeObject(etatJeu);
			writer.flush();
			ajouterMessage("Le fichier " + nomFichier + " a été sauvegardé!", EMessageType.MESSAGE_SYSTEME);
			resultat = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return resultat;
	}
	
	/**
	 * Loads a previously saved game
	 * 
	 * @param nomFichier
	 * 			The name of the file to load
	 * 
	 * @return the result of the loading action
	 */
	public boolean chargerJeu(String nomFichier) {
		boolean resultat = false;
		
		EtatJeuBase temp = null;
		
		ObjectInputStream reader = null;
		
		try {
			reader = new ObjectInputStream(new FileInputStream(nomFichier));
			try {
				temp = (EtatJeuBase)reader.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(temp != null) {
			etatJeu = temp;
			getDonneeTypeBatiment();
			getDonneeEvenementAttaque();
			getDonneeEvenementExploration();
			
			etatJeu.setMessageHistorique(new ArrayList<>());
			
			ajouterMessage("Le fichier " + nomFichier + " a été chargé!", EMessageType.MESSAGE_SYSTEME);
			
			resultat = true;
		}
		
		return resultat;
	}
	
	/**
	 * Processes all the actions chosen by the player and validate them
	 */
	public void effectuerTourJeu() {
		if(etatJeu != null) {
			
			DonneeJoueurBase donneeJoueur = etatJeu.getDonneeJoueur();
			DonneeJeuBase donneeJeu = etatJeu.getDonneeJeu();
			
			if(donneeJeu.getNbTourAvantAttaque() <= 0) {
				lancerEvementAttaque();
				donneeJeu.setNbTourAvantAttaque();
			}
						
			etatJeu.getJobManager().processJobs();
			
			int consommationNourriture = donneeJoueur.calculerConsommationNourriture();
			int consommationEau = donneeJoueur.calculerConsommationEau();
			
			ajouterMessage("Fin du tour " + donneeJeu.getNbTourJoue() + " \r\n Vous avez consomme " + consommationEau +" unites d'eau et "+ consommationNourriture + " unites de nourriture", EMessageType.MESSAGE_JEU);
						
			donneeJoueur.consommerNourriture(consommationNourriture);
			donneeJoueur.consommerEau(consommationEau);
			
			donneeJoueur.miseAJourRessource();
			donneeJeu.decrementerNbTourAvantAttaque();
			donneeJeu.incrementerTourJoue();
			
			donneeJoueur.getScore().setPtTour((donneeJeu.getNbTourJoue() - 1) * 100);
			donneeJoueur.getScore().calculerTotal();
		}
	}
	
	/**
	 * Controls whether the player has reached the end of the game
	 * 
	 * @return The state of the game
	 */
	public boolean objectifAtteint() {
		return etatJeu.getDonneeJeu().objectifAtteint();
	}
	
	/**
	 * Controls whether the player is still alive
	 * 
	 * @return The state of the player's life
	 */
	public boolean joueurEstEnVie() {
		return etatJeu.getDonneeJoueur().joueurEstEnVie();
	}	
	
	/**
	 * Launches an attack on the player's base
	 */
	public void lancerEvementAttaque() {
		Random random = new Random();
		
		int numeroEvenement = random.nextInt(etatJeu.getListeEvenementAttaque().size());
		Evenement evenement = etatJeu.getListeEvenementAttaque().get(numeroEvenement);
		
		int pourcentageDegat = random.nextInt(11) + 10;
		int degatTotal = etatJeu.getDonneeJoueur().getDefense() * pourcentageDegat / 100;
		int degatParBatiment = degatTotal / etatJeu.getDonneeJoueur().getListeBatiment().size();
		
		ajouterMessage(evenement.getMessage(), EMessageType.MESSAGE_JEU);
		ajouterMessage("Vous avez subit un total de " + degatTotal + " points de degats.", EMessageType.MESSAGE_JEU);
		
		Iterator<Batiment> it = etatJeu.getDonneeJoueur().getListeBatiment().iterator();
		while(it.hasNext()) {
			Batiment batiment = it.next();
			batiment.setDefense(batiment.getDefense() - degatParBatiment);
			
			ajouterMessage("Le batiment " + batiment.getTypeBatiment() +  " a recu " + degatParBatiment + " points de degats.", EMessageType.MESSAGE_JEU);
			
			if(batiment.getDefense() <= 0) {
				ajouterMessage("Le batiment " + batiment.getTypeBatiment() + " a été detruit.", EMessageType.MESSAGE_JEU);
				etatJeu.getDonneeJoueur().getListeBatiment().remove(batiment);				
			}
		}
	}

	protected abstract void getDonneeTypeBatiment();
	protected abstract void getDonneeEvenementAttaque();
	protected abstract void getDonneeEvenementExploration();
	public abstract boolean isConnected();
	
	/**
	 * Read a CSV file that returns the credit data
	 * 
	 * @return The contents of the file
	 */
	public ArrayList<String> getDonneeCredit() {
		return lireFichier(cheminDonnee + cheminCredit);
	}
	
	/**
	 * Gets the player's score
	 * 
	 * @return The score of the player
	 */
	public DonneeJoueurScore getScoreJoueur() {
		return etatJeu.getDonneeJoueur().getScore();
	}
	
	public abstract ArrayList<String> getDonneeScore();

	/**
	 * Gets a handle on the file that contains credit data
	 * 
	 * @return The handle of the file
	 */
	public BufferedReader getHandleCredit() {
		BufferedReader reader = null;
		
		String fileName = cheminDonnee + cheminCredit;
		
		try {
			reader = new BufferedReader(new FileReader(fileName));			
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier " + fileName + " n'a pas été trouvé.");
		}
		
		return reader;
	}
	
	//public abstract ArrayList<String> getDonneeSauvegarde();
	
	/**
	 * Gets the game's event history
	 * 
	 * @return The history data
	 */
	public ArrayList<Message> getHistorique() {
		return etatJeu.getMessageHistorique();
	}
	
	/**
	 * Add a message to the history
	 * 
	 * @param message
	 * 			The message text
	 * 
	 * @param typeMessage
	 * 			The message type
	 */
	public void ajouterMessage(String message, EMessageType typeMessage) {
		etatJeu.ajouterMessage(message, typeMessage);
	}
	
	/**
	 * Returns a building type data from a building type name
	 * 
	 * @param type
	 * 			The name of the building
	 * 
	 * @return The building type data
	 */
	public TypeBatiment getTypeBatiment(String type) {
		TypeBatiment resultat = null;
		
		if(etatJeu.getListeTypeBatiment() != null) {
			Iterator<TypeBatiment> it = etatJeu.getListeTypeBatiment().iterator();
			
			while(it.hasNext()) {
				TypeBatiment typeBatiment = it.next();
				if(typeBatiment.getTypeBatiment().equals(type)) {
					resultat = typeBatiment;
					break;
				}
			}
		}		
		
		return resultat;
	}
	
	/**
	 * Returns the list of all the building type
	 * 
	 * @return The list of building type
	 */
	public ArrayList<TypeBatiment> getListeTypeBatiment() {
		return etatJeu.getListeTypeBatiment();
	}
	
	/**
	 * Returns the list of all the buildings built by the player
	 * 
	 * @return the list of buildings
	 */
	public ArrayList<Batiment> getBatimentConstruit() {
		return etatJeu.getDonneeJoueur().getListeBatiment();
	}
	
	/**
	 * Launches a contruction job from a building name and verifies that the player has the resources available and the conditions fulfilled
	 * 
	 * @param nomBatiment
	 * 			The building name
	 */
	public void construireBatiment(String nomBatiment) {		
		if(etatJeu.getListeTypeBatiment() != null) {
			Iterator<TypeBatiment> it = etatJeu.getListeTypeBatiment().iterator();
			
			while(it.hasNext()) {
				TypeBatiment typeBatiment = it.next();
				if(typeBatiment.getTypeBatiment().equals(nomBatiment)) {
					boolean hasAssezRessource = verifieValiditeConstructionBatiment(etatJeu.getDonneeJoueur(), typeBatiment);
					
					if(hasAssezRessource) {
						Batiment batiment = new Batiment(typeBatiment.getTypeBatiment(), typeBatiment.getDefense(), typeBatiment.getAttaque(),
								typeBatiment.getCoutNourriture(), typeBatiment.getCoutEau(), typeBatiment.getCoutBois(),
								typeBatiment.getCoutMinerai(), typeBatiment.getCoutPierre(), typeBatiment.getCoutUnite(),
								typeBatiment.getStockageNourriture(), typeBatiment.getStockageEau(), typeBatiment.getStockageBois(),
								typeBatiment.getStockageMinerai(), typeBatiment.getStockagePierre(), typeBatiment.getPoint());

						if(typeBatiment.getCondition().size() == 0) {
							etatJeu.getJobManager().addJob(new ConstructionBatimentJobItem(batiment, etatJeu.getMessageHistorique(), etatJeu.getDonneeJoueur(), typeBatiment.getNbTour()));
						}
						else {
							if(verifieValiditeCondition(etatJeu.getDonneeJoueur(), typeBatiment)) {
								etatJeu.getJobManager().addJob(new ConstructionBatimentJobItem(batiment, etatJeu.getMessageHistorique(), etatJeu.getDonneeJoueur(), typeBatiment.getNbTour()));
							}
							else {
								ajouterMessage("La construction du batiment " + typeBatiment.getTypeBatiment() + " n'a pas pu etre lancé du à une condition non remplie!", EMessageType.MESSAGE_CONSTRUCTION);
							}
						}
					}
					else {
						ajouterMessage("La construction du batiment " + typeBatiment.getTypeBatiment() + " n'a pas pu etre lancé du à un manque de ressources!", EMessageType.MESSAGE_CONSTRUCTION);
					}
				}
			}
		}
		
	}
	
	/**
	 * Launches a evolution job from a building data and verifies that the player has the resources available and the conditions fulfilled
	 * 
	 * @param batiment
	 * 			The building data
	 */
	public void evoluerBatiment(Batiment batiment) {
		if(etatJeu.getListeTypeBatiment() != null) {
			Iterator<TypeBatiment> it = etatJeu.getListeTypeBatiment().iterator();
			
			while(it.hasNext()) {
				TypeBatiment typeBatiment = it.next();
				if(typeBatiment.getTypeBatiment().equals(batiment.getTypeBatiment())) {
					if(batiment.getNiveau() < typeBatiment.getNiveauMax()) {
						boolean hasAssezRessource = verifieValiditeEvolutionBatiment(etatJeu.getDonneeJoueur(), typeBatiment, batiment);
						
						if(hasAssezRessource) {
							int tour = typeBatiment.getNbTour();
							
							for(int i = 1; i < batiment.getNiveau() + 1; i++) {
								tour += typeBatiment.getNbTour();
							}
							
							etatJeu.getJobManager().addJob(new EvolutionBatimentJobItem(typeBatiment, batiment, etatJeu.getMessageHistorique(), etatJeu.getDonneeJoueur(), tour));
						}
						else {
							ajouterMessage("La construction du batiment " + typeBatiment.getTypeBatiment() + " de niveau " + (batiment.getNiveau() + 1) + " n'a pas pu etre lancé du à un manque de ressources!", EMessageType.MESSAGE_CONSTRUCTION);
						}
					}
					else {
						ajouterMessage("Le batiment " + batiment.getTypeBatiment() + " a atteint son niveau maximal et ne peut plus evoluer!", EMessageType.MESSAGE_CONSTRUCTION);
					}
				}
			}
		}
		
	}
	
	/**
	 * Checks whether the player has the resources necessary to construct a building
	 * 
	 * @param donneeJoueur
	 * 			The player's data
	 * 
	 * @param typeBatiment
	 * 			The type data of the building to construct 
	 * 
	 * @return The validity of the action
	 */
	public boolean verifieValiditeConstructionBatiment(DonneeJoueurBase donneeJoueur, TypeBatiment typeBatiment) {
		boolean resultat = (donneeJoueur.getNourriture() >= typeBatiment.getCoutNourriture())
				&& (donneeJoueur.getEau() >= typeBatiment.getCoutEau())
				&& (donneeJoueur.getBois() >= typeBatiment.getCoutBois())
				&& (donneeJoueur.getMinerai() >= typeBatiment.getCoutMinerai())
				&& (donneeJoueur.getPierre() >= typeBatiment.getCoutPierre())	
				&& (donneeJoueur.getNbUniteDisponible() >= typeBatiment.getCoutUnite());		
		
		return resultat;
	}
	
	/**
	 * Checks whether the player has fulfilled the conditions necessary to construct a building
	 * 
	 * @param donneeJoueur
	 * 			The player's data
	 * 
	 * @param typeBatiment
	 * 			The type data of the building to construct 
	 * 
	 * @return The validity of the action
	 */
	public boolean verifieValiditeCondition(DonneeJoueurBase donneeJoueur, TypeBatiment typeBatiment) {
		boolean resultat = true;
		
		Iterator<BatimentCondition> it = typeBatiment.getCondition().iterator();
		while(it.hasNext()) {
			BatimentCondition condition = it.next();
			if(!donneeJoueur.hasBatiment(condition.getCondition(), condition.getNiveau())) {
				resultat = false;
			}
		}
		
		return resultat;
	}
	
	/**
	 * Checks whether the player has the resources necessary to make a building evolve
	 * 
	 * @param donneeJoueur
	 * 			The player's data
	 * 
	 * @param typeBatiment
	 * 			The type data of the building to make evolve
	 * 
	 * @param batiment
	 * 			The data of the building to make evolve
	 * 
	 * @return The validity of the action
	 */
	public boolean verifieValiditeEvolutionBatiment(DonneeJoueurBase donneeJoueur, TypeBatiment typeBatiment, Batiment batiment) {
		float fConsommation = 1;
		
		for(int i = 1; i < batiment.getNiveau() + 1; i++) {
			fConsommation += fConsommation * ((float)typeBatiment.getAugmentationCout()) / 100.0f;
		}
		
		int consommation = (int)fConsommation;
	
		boolean resultat = (donneeJoueur.getNourriture() >= typeBatiment.getCoutNourriture() * consommation)
				&& (donneeJoueur.getEau() >= typeBatiment.getCoutEau() * consommation)
				&& (donneeJoueur.getBois() >= typeBatiment.getCoutBois() * consommation)
				&& (donneeJoueur.getMinerai() >= typeBatiment.getCoutMinerai() * consommation)
				&& (donneeJoueur.getPierre() >= typeBatiment.getCoutPierre() * consommation)
				&& (donneeJoueur.getNbUniteDisponible() >= typeBatiment.getCoutUnite() * consommation);		
		
		return resultat;
	}
	
	/**
	 * Launches a exploration job from a number and verifies that the player has enough units available
	 * 
	 * @param nbAAjouter
	 * 			The number of units to send on exploration
	 */
	public void envoyerUniteEnExploration(int nbAAjouter) {
		if(verifierDisponibiliteUnite(nbAAjouter)) {
			etatJeu.getJobManager().addJob(new ExplorationUniteJobItem(nbAAjouter, etatJeu.getListeEvenementExploration(), etatJeu.getMessageHistorique(), etatJeu.getDonneeJoueur(), 1));
		}
		
		else {
			etatJeu.ajouterMessage("il est impossible d'envoyer "+ nbAAjouter +" unités en exploration.", EMessageType.MESSAGE_EXPLORATION);
		}

	}
	
	/**
	 * Launches a defense job from a number and verifies that the player has enough units available
	 * 
	 * @param nbAAjouter
	 * 			The number of units to send on defense
	 */
	public void envoyerUniteEnDefense(int nbAAjouter) {
		if(verifierDisponibiliteUnite(nbAAjouter)) {
			etatJeu.getJobManager().addJob(new DefenseUniteJobItem(nbAAjouter, etatJeu.getMessageHistorique(), etatJeu.getDonneeJoueur(), 1));
		}
		else {
			etatJeu.ajouterMessage("il est impossible d'envoyer "+ nbAAjouter +" unités en defense.", EMessageType.MESSAGE_DEFENSE);
		}
	}
	
	/**
	 * Verifies that the player has enough units available
	 * 
	 * @param nbAAjouter
	 * 			The number of units requested
	 * 
	 * @return The vaidity of the request
	 */
	private boolean verifierDisponibiliteUnite(int nbAAjouter) {
		boolean resultat = false;
		
		if((nbAAjouter > 0) && (nbAAjouter <= etatJeu.getDonneeJoueur().getNbUniteDisponible())) {
			resultat = true;
		}
		
		return resultat;
	}
	
	/**
	 * Returns resource data from a resource type
	 * 
	 * @param typeRessource
	 * 			The type of the resource to return
	 * 
	 * @return The resource
	 */
	public RessourceJoueur getRessource(ERessourceJoueur typeRessource) {
		RessourceJoueur resultat = null;
		
		DonneeJoueurBase donneeJoueur = etatJeu.getDonneeJoueur();
		
		switch(typeRessource) {
			case RESSOURCE_BASE: {
				resultat = new RessourceJoueur(donneeJoueur.getAttaque(), donneeJoueur.getDefense());
			}break;
		
			case RESSOURCE_NOURRITURE: {
				resultat = new RessourceJoueur(donneeJoueur.getNourriture(), donneeJoueur.getNourritureMax());
			}break;
			
			case RESSOURCE_EAU: {
				resultat = new RessourceJoueur(donneeJoueur.getEau(), donneeJoueur.getEauMax());
			}break;
			
			case RESSOURCE_BOIS: {
				resultat = new RessourceJoueur(donneeJoueur.getBois(), donneeJoueur.getBoisMax());
			}break;
			
			case RESSOURCE_MINERAI: {
				resultat = new RessourceJoueur(donneeJoueur.getMinerai(), donneeJoueur.getMineraiMax());
			}break;
			
			case RESSOURCE_PIERRE: {
				resultat = new RessourceJoueur(donneeJoueur.getPierre(), donneeJoueur.getPierreMax());
			}break;
			
			case RESSOURCE_UNITE: {
				resultat = new RessourceJoueur(donneeJoueur.getNbUniteDisponible(), donneeJoueur.getNbUniteMax());
			}break;
			
			case RESSOURCE_UNITE_EXPLORATION: {
				resultat = new RessourceJoueur(donneeJoueur.getNbUniteEnExploration(), donneeJoueur.getNbUniteMax());
			}break;
			
			case RESSOURCE_UNITE_CONSTRUCTION: {
				resultat = new RessourceJoueur(donneeJoueur.getNbUniteEnConstruction(), donneeJoueur.getNbUniteMax());
			}break;
			
			case RESSOURCE_UNITE_DEFENSE: {
				resultat = new RessourceJoueur(donneeJoueur.getNbUniteEnDefense(), donneeJoueur.getNbUniteMax());
			}break;
		}
		
		return resultat;
	}
	
	/**
	 * Sets the name of the player
	 * 
	 * @param nom
	 * 			The player's name
	 */
	public void setPseudoJoueur(String nom) {
		etatJeu.getDonneeJoueur().setNomJoueur(nom);
	}
	
	/**
	 * Gets the name of the player
	 * 
	 * @return The player's name
	 */
	public String getPseudoJoueur() {
		return etatJeu.getDonneeJoueur().getNomJoueur();
		
	}
	
	public ImageManager getImageManager() {
		return imgMgr;
	}
	
	/**
	 * Reads the entire contents of a file
	 * 
	 * @param emplacement
	 * 			The file path
	 * 
	 * @return The contents of the file
	 */
	public ArrayList<String> lireFichier(String emplacement) {
		ArrayList<String> resultat = new ArrayList<>();		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(emplacement));
			String donnee = null;
			
			try {
				while((donnee = reader.readLine()) != null) {
					
					resultat.add(donnee);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier " + emplacement + " n'a pas été trouvé.");
		}
		finally {
			try {
				reader.close();
			} 
			catch(NullPointerException e) {
				System.err.println("Le flux est null et le fichier n'a pas besoin d'etre fermé.");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return resultat;
	}
	
	public abstract void enregistrerScore();
	
}
