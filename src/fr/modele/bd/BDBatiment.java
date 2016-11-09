package fr.modele.bd;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.modele.batiment.BatimentCondition;
import fr.modele.batiment.TypeBatiment;

/**
 * 
 * @author JGC
 *
 */
public class BDBatiment {
	
	private final String batimentTable = "unknow_world.batiment";
	
	private final String batimentId = "id_btm";
	private final String batimentNom = "btm_nom";
	private final String batimentDescription = "btm_description";
	
	private final String batimentDefense = "btm_def";
	private final String batimentAttaque = "btm_att";
	
	private final String batimentCoutNourriture = "btm_nourriture";
	private final String batimentCoutEau = "btm_eau";
	private final String batimentCoutBois = "btm_bois";
	private final String batimentCoutMinerai = "btm_minerai";
	private final String batimentCoutPierre = "btm_pierre";
	private final String batimentCoutUnite = "btm_unite_utile";
	
	private final String batimentStockNourriture = "btm_stock_nourriture";
	private final String batimentStockEau = "btm_stock_eau";
	private final String batimentStockBois = "btm_stock_bois";
	private final String batimentStockMinerai = "btm_stock_minerai";
	private final String batimentStockPierre = "btm_stock_pierre";
	
	private final String batimentAugAttaque = "btm_aug_att";
	private final String batimentAugDefense = "btm_aug_def";
	private final String batimentAugCout = "btm_aug_cout";
	private final String batimentAugStock = "btm_aug_stock";
	private final String batimentAugTour = "btm_aug_tour";
	
	private final String batimentNiveauMax = "btm_lvl_max";
	private final String batimentPoint = "btm_point";
	private final String batimentTour = "btm_tour_construc";
	
	private final String batimentACondition = "btm_condition_active";
	
	private Connection connection;
	
	public BDBatiment(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Gets all the building type data from the event table
	 * 
	 * @return The building type data
	 */
	public ArrayList<TypeBatiment> readAll() {
		ArrayList<TypeBatiment> resultat = new ArrayList<>();
		
		Statement state = null;
		ResultSet resultSet = null;		
		try {
			state = connection.createStatement();
			resultSet = state.executeQuery("SELECT * FROM " + batimentTable);
						
			while(resultSet.next()) {
				int batiment_id = resultSet.getInt(batimentId);
				String typeBatiment = resultSet.getString(batimentNom);
				Blob descriptionBlob = resultSet.getBlob(batimentDescription);
				
				int length = (int)descriptionBlob.length();
				String description = new String(descriptionBlob.getBytes(1, length));
				
				int defense = resultSet.getInt(batimentDefense);
				int attaque = resultSet.getInt(batimentAttaque);
				
				int coutNourriture = resultSet.getInt(batimentCoutNourriture);
				int coutEau = resultSet.getInt(batimentCoutEau);
				int coutBois = resultSet.getInt(batimentCoutBois);
				int coutMinerai = resultSet.getInt(batimentCoutMinerai);
				int coutPierre = resultSet.getInt(batimentCoutPierre);
				int coutUnite = resultSet.getInt(batimentCoutUnite);
				
				int stockageNourriture = resultSet.getInt(batimentStockNourriture);
				int stockageEau = resultSet.getInt(batimentStockEau);
				int stockageBois = resultSet.getInt(batimentStockBois);
				int stockageMinerai = resultSet.getInt(batimentStockMinerai);
				int stockagePierre = resultSet.getInt(batimentStockPierre);
				
				ArrayList<BatimentCondition> listeCondition = new ArrayList<>();
				int condition = resultSet.getInt(batimentACondition);
				if(condition != 0) {
					BDBatimentCondition bdConditionBatiment = new BDBatimentCondition(connection);
					listeCondition = bdConditionBatiment.read(batiment_id);					
				}
				
				float augmentationAttaque = resultSet.getFloat(batimentAugAttaque);
				float augmentationDefense = resultSet.getFloat(batimentAugDefense);
				float augmentationCout = resultSet.getFloat(batimentAugCout);
				float augmentationStockage = resultSet.getFloat(batimentAugStock);
				int augmentationTour = resultSet.getInt(batimentAugTour);
				
				int niveauMax = resultSet.getInt(batimentNiveauMax);
				
				int point = resultSet.getInt(batimentPoint);
				int nbTour = resultSet.getInt(batimentTour);
				
				resultat.add(new TypeBatiment(typeBatiment, description, defense, attaque, coutNourriture, coutEau, coutBois,
						coutMinerai, coutPierre, coutUnite, stockageNourriture, stockageEau, stockageBois,
						stockageMinerai, stockagePierre, listeCondition, augmentationAttaque,
						augmentationDefense, augmentationCout, augmentationStockage, augmentationTour,
						niveauMax, point, nbTour));
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
				state.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
		return resultat;
	}

}
