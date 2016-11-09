package fr.modele.bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.modele.Evenement;
import fr.modele.ModeleBase;
import fr.modele.batiment.TypeBatiment;

/**
 * 
 * @author JGC
 *
 */
public class ModeleBaseBD extends ModeleBase {

	public ModeleBaseBD(String cheminDonnee) {
		super(cheminDonnee);
	}

	/**
	 * Gets building type data from the database
	 */
	@Override
	protected void getDonneeTypeBatiment() {
		BDConnection bdConnection = new BDConnection();
		Connection connection = bdConnection.getConnection();
				
		BDBatiment bdBatiment = new BDBatiment(connection);
		ArrayList<TypeBatiment> listeBatiment = bdBatiment.readAll();
		
		bdConnection.close();
				
		if(etatJeu != null) {
			etatJeu.setListeTypeBatiment(listeBatiment);
		}
	}

	/**
	 * Gets attack event type data from the database
	 */
	@Override
	protected void getDonneeEvenementAttaque() {
		BDConnection bdConnection = new BDConnection();
		Connection connection = bdConnection.getConnection();
		
		BDAttaqueEvenement bdAttaqueEvenement = new BDAttaqueEvenement(connection);
		ArrayList<Evenement> listeEvenementAttaque = bdAttaqueEvenement.getEvenementListe();
		
		bdConnection.close();
		
		if(etatJeu != null) {
			etatJeu.setListeEvenementAttaque(listeEvenementAttaque);
		}
	}

	/**
	 * Gets exploration event data from the database
	 */
	@Override
	protected void getDonneeEvenementExploration() {
		BDConnection bdConnection = new BDConnection();
		Connection connection = bdConnection.getConnection();
		
		BDExplorationEvenement bdExplorationEvenement = new BDExplorationEvenement(connection);
		ArrayList<Evenement> listeEvenementExploration = bdExplorationEvenement.readAll();
		
		bdConnection.close();
		
		if(etatJeu != null) {
			etatJeu.setListeEvenementExploration(listeEvenementExploration);
		}
	}

	/**
	 * Gets score data from the database
	 * 
	 * @return The score data
	 */
	@Override
	public ArrayList<String> getDonneeScore() {
		ArrayList<String> listeScore = new ArrayList<>();
		
		BDConnection bdConnection = new BDConnection();
		Connection connection = bdConnection.getConnection();
		
		BDScore score = new BDScore(connection);
		listeScore = score.readAll();
		
		bdConnection.close();
		
		return listeScore;
	}

	/**
	 * Check whether a connection is available
	 */
	@Override
	public boolean isConnected() {
		BDConnection bdConnection = new BDConnection();
		Connection connection = bdConnection.getConnection();
		
		if(connection != null) {
			bdConnection.close();
			return true;
		}
		
		return false;
	}

	/**
	 * Insert score data into the database
	 */
	@Override
	public void enregistrerScore() {
		BDConnection bdConnection = new BDConnection();
		Connection connection = bdConnection.getConnection();
				
		BDUtilisateur utilisateur = new BDUtilisateur(connection);
		int utl_id = utilisateur.create(getPseudoJoueur(), "", "");
		
		BDScore score = new BDScore(connection);
		score.create(utl_id, getScoreJoueur().getPtEau(), getScoreJoueur().getPtNourriture(),
				getScoreJoueur().getPtBois(), getScoreJoueur().getPtMinerai(), getScoreJoueur().getPtPierre(),
				getScoreJoueur().getPtBatiment(), getScoreJoueur().getPtTour(), getScoreJoueur().getPtUnite());
		
		bdConnection.close();
	}
	
	/*
	@Override
	public ArrayList<String> getDonneeSauvegarde() {
		return new ArrayList<>();
	}
	*/
}
