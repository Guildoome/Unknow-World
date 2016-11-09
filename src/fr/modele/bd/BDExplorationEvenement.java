package fr.modele.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.modele.Evenement;

/**
 * 
 * @author JGC
 *
 */
public class BDExplorationEvenement {
	private final String explorationTable = "unknow_world.exploration";
	
	private final String explorationId = "id_exp";
	private final String explorationMessage = "exp_mess_explo";
	private final String explorationPerte = "exp_perte_humaine";
	private final String explorationNourriture = "exp_nourriture";
	private final String explorationEau = "exp_eau";
	private final String explorationBois = "exp_bois";
	private final String explorationMinerai = "exp_minerai";
	private final String explorationPierre = "exp_pierre";

	private Connection connection;
	
	public BDExplorationEvenement(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Gets all the event data from the exploration table
	 * 
	 * @return The event data
	 */
	public ArrayList<Evenement> readAll() {
		ArrayList<Evenement> resultat = new ArrayList<>();

		Statement state = null;
		ResultSet resultSet = null;
		try {
			state = connection.createStatement();
			resultSet = state.executeQuery("SELECT * FROM "+ explorationTable);
			
			while(resultSet.next()) {
				int numero = resultSet.getInt(explorationId);
				String message = resultSet.getString(explorationMessage);
				int perte = resultSet.getInt(explorationPerte);
				int gainNouriture = resultSet.getInt(explorationNourriture);
				int gainEau = resultSet.getInt(explorationEau);
				int gainBois = resultSet.getInt(explorationBois);
				int gainMinerai = resultSet.getInt(explorationMinerai);
				int gainPierre = resultSet.getInt(explorationPierre);
				
				resultat.add(new Evenement(numero, message, perte,
						gainNouriture, gainEau, gainBois,
						gainMinerai, gainPierre));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
				state.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
		return resultat;
	}
}
