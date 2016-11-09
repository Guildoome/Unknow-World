package fr.modele;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import fr.modele.batiment.BatimentCondition;
import fr.modele.batiment.TypeBatiment;

/**
 * 
 * @author JGC
 *
 */
public class ModeleBaseFichier extends ModeleBase {

	private String cheminScore = "/score.csv";
	private String cheminBatiment = "/batiment.csv";
	private String cheminCondition = "/condition.csv";
	private String cheminEvenementAttaque = "/evenement_attaque.csv";
	private String cheminEvenementExploration = "/evenement_exploration.csv";
	@SuppressWarnings("unused")
	private String cheminSauvegarde = "/save/";

	public ModeleBaseFichier(String cheminDonnee) {
		super(cheminDonnee);
	}

	/**
	 * Gets building type data from the disk
	 */
	@Override
	protected void getDonneeTypeBatiment() {
		etatJeu.setListeTypeBatiment(new ArrayList<>());
		
		ArrayList<String> donneeTypeBatiment = lireFichier(cheminDonnee + cheminBatiment);		
		
		Iterator<String> it = donneeTypeBatiment.iterator();
		while(it.hasNext()) {
			String typeBatiment = it.next();
			
			String[] donnee = typeBatiment.split(";");
			if(!donnee[0].trim().equals("nom")) {
				String type = donnee[0];
				String description = donnee[1];
				
				int defense = Integer.parseInt(donnee[2]);
				int attaque = Integer.parseInt(donnee[3]);
				
				int coutNourriture = Integer.parseInt(donnee[5]);
				int coutEau = Integer.parseInt(donnee[4]);
				int coutBois = Integer.parseInt(donnee[6]);
				int coutMinerai = Integer.parseInt(donnee[7]);
				int coutPierre = Integer.parseInt(donnee[8]);
				int coutUnite = Integer.parseInt(donnee[9]);
				
				int stockageNourriture = Integer.parseInt(donnee[10]);
				int stockageEau = Integer.parseInt(donnee[11]);
				int stockageBois = Integer.parseInt(donnee[12]);
				int stockageMinerai = Integer.parseInt(donnee[13]);
				int stockagePierre = Integer.parseInt(donnee[14]);
				
				ArrayList<BatimentCondition> listeCondition = new ArrayList<>();
				
				String condition = donnee[15];
				if(!condition.equals("0")) {
					ArrayList<String> donneeCondition = lireFichier(cheminDonnee + cheminCondition);		
					
					Iterator<String> itCondition = donneeCondition.iterator();
					while(itCondition.hasNext()) {
						String evenement = itCondition.next();
						
						String[] donnee_condition = evenement.split(";");
						if(donnee_condition[0].trim().equals(type)) {
							listeCondition.add(new BatimentCondition(donnee_condition[1], Integer.parseInt(donnee_condition[2])));
						}			
					}
				}
				
				int point = Integer.parseInt(donnee[16]);
				int nbTour = Integer.parseInt(donnee[17]);
				
				float augmentationAttaque = Float.parseFloat(donnee[18]);
				float augmentationDefense = Float.parseFloat(donnee[19]);
				float augmentationCout = Float.parseFloat(donnee[20]);
				float augmentationStockage = Float.parseFloat(donnee[21]);
				int augmentationTour = Integer.parseInt(donnee[22]);
				int niveauMax = Integer.parseInt(donnee[23]);
				
				etatJeu.getListeTypeBatiment().add(new TypeBatiment(type, description, defense, attaque,
						coutNourriture, coutEau, coutBois,
						coutMinerai, coutPierre, coutUnite,
						stockageNourriture, stockageEau, stockageBois,
						stockageMinerai, stockagePierre, listeCondition,
						augmentationCout, augmentationStockage, augmentationAttaque,
						augmentationDefense, augmentationTour, niveauMax,
						point, nbTour));
			}			
		}
	}

	/**
	 * Gets attack event type data from the disk
	 */
	@Override
	protected void getDonneeEvenementAttaque() {
		etatJeu.setListeEvenementAttaque(new ArrayList<>());
		
		ArrayList<String> donneeEvenement = lireFichier(cheminDonnee + cheminEvenementAttaque);		
		
		Iterator<String> it = donneeEvenement.iterator();
		while(it.hasNext()) {
			String evenement = it.next();
			
			String[] donnee = evenement.split(";");
			if(!donnee[0].trim().equals("nom")) {
				int numero = Integer.parseInt(donnee[0]);
				String message = donnee[1];				
				int perte = Integer.parseInt(donnee[2]);
				
				etatJeu.getListeEvenementAttaque().add(new Evenement(numero, message, perte, 0, 0, 0, 0, 0));
			}			
		}
	}

	/**
	 * Gets exploration event data from the disk
	 */
	@Override
	protected void getDonneeEvenementExploration() {
		etatJeu.setListeEvenementExploration(new ArrayList<>());
		
		ArrayList<String> donneeEvenement = lireFichier(cheminDonnee + cheminEvenementExploration);		
		
		Iterator<String> it = donneeEvenement.iterator();
		while(it.hasNext()) {
			String evenement = it.next();
			
			String[] donnee = evenement.split(";");
			if(!donnee[0].trim().equals("nom")) {
				int numero = Integer.parseInt(donnee[0]);
				String message = donnee[1];				
				int perte = Integer.parseInt(donnee[2]);				
				int gainNourriture = Integer.parseInt(donnee[3]);
				int gainEau = Integer.parseInt(donnee[4]);
				int gainBois = Integer.parseInt(donnee[5]);
				int gainMinerai = Integer.parseInt(donnee[6]);
				int gainPierre = Integer.parseInt(donnee[7]);
				
				
				etatJeu.getListeEvenementExploration().add(new Evenement(numero, message, perte, gainNourriture, gainEau, gainBois, gainMinerai, gainPierre));
			}			
		}
	}

	/**
	 * Gets score data from the disk
	 * 
	 * @return The score data
	 */
	@Override
	public ArrayList<String> getDonneeScore() {
		return lireFichier(cheminDonnee + cheminScore);
	}

	@Override
	public boolean isConnected() {
		File fichier = new File(cheminDonnee + cheminBatiment);
		
		if(fichier.exists()) {
			return true;
		}
		
		return false;
	}

	/**
	 * Saves score data on the disk
	 */
	@Override
	public void enregistrerScore() {
	
		ArrayList<String> score = lireFichier(cheminDonnee + cheminScore);
		
		String scores = getPseudoJoueur() + ";" + getScoreJoueur().getPtTotal();
		
		score.add(scores);
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(cheminDonnee + cheminScore));
			Iterator<String> it = score.iterator();
			while(it.hasNext()) {
				String donnee = it.next();
				writer.write(donnee + "\r\n");
			}
			
			writer.flush();
			
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
		
	}	
	
}
