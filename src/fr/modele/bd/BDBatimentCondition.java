package fr.modele.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.modele.batiment.BatimentCondition;

/**
 * 
 * @author JGC
 *
 */
public class BDBatimentCondition {
	
	private final String conditionTable = "unknow_world.condition";
	
	@SuppressWarnings("unused")
	private final String conditionId = "id";
	private final String conditionIdBatimentConcerne = "bat_id_concerner";
	private final String conditionIdBatimentCondition = "bat_id_util_pour_condi";
	private final String conditionIdBatimentConditionNiveau = "lvl_attendu";

	private Connection connection;
	
	public BDBatimentCondition(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Gets condition data from an id
	 * 
	 * @param batimentId
	 * 			The id of the condition
	 * 
	 * @return The condition data
	 */
	public ArrayList<BatimentCondition> read(int batimentId) {
		ArrayList<BatimentCondition> resultat = new ArrayList<>();

		Statement conditionStatement = null;
		ResultSet resultConditionSet = null;
		try {
			conditionStatement = connection.createStatement();
			resultConditionSet = conditionStatement.executeQuery("SELECT * FROM " + conditionTable + " WHERE " + conditionIdBatimentConcerne + " = " + batimentId);
		
			while(resultConditionSet.next()) {
				int batiment_id_condition = resultConditionSet.getInt(conditionIdBatimentCondition);
				
				String nom_condition = null;
				int niveau_condition = resultConditionSet.getInt(conditionIdBatimentConditionNiveau);
				
				Statement conditionBatimentStatement = connection.createStatement();
				ResultSet resultBatimentConditionSet = conditionBatimentStatement.executeQuery("SELECT * FROM unknow_world.batiment WHERE batiment.id_btm = " + batiment_id_condition);
				if(resultBatimentConditionSet.next()) {
					nom_condition = resultBatimentConditionSet.getString("btm_nom");
				}
				
				resultat.add(new BatimentCondition(nom_condition, niveau_condition));
				
				resultBatimentConditionSet.close();
				conditionBatimentStatement.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				resultConditionSet.close();
				conditionStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}					
		
		return resultat;
	}

}
