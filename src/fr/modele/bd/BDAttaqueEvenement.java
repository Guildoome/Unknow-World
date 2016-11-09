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
public class BDAttaqueEvenement {

	private final String attaqueTable = "unknow_world.attaque";
	
	private final String attaqueID = "id_att";
	private final String attaqueMessage = "att_message";
	private final String attaquePerte = "att_perte_humaine";

	private Connection connection;
	
	public BDAttaqueEvenement(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Gets all the event data from the attack table
	 * 
	 * @return The event data
	 */
	public ArrayList<Evenement> getEvenementListe() {
		ArrayList<Evenement> resultat = new ArrayList<>();

		Statement state = null;
		ResultSet resultSet = null;
		try {
			state = connection.createStatement();
			resultSet = state.executeQuery("SELECT * FROM "+ attaqueTable);
			
			while(resultSet.next()) {
				int numero = resultSet.getInt(attaqueID);
				String message = resultSet.getString(attaqueMessage);
				int perte = resultSet.getInt(attaquePerte);
				int gainNouriture = 0;
				int gainEau = 0;
				int gainBois = 0;
				int gainMinerai = 0;
				int gainPierre = 0;
				
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
