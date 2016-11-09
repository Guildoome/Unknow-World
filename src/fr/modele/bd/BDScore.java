package fr.modele.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author JGC
 *
 */
public class BDScore {
	
	private final String scoreId = "id_scr";
	private final String scoreIdPseudo = "scr_id_pseudo";
	private final String scoreEau = "scr_point_eau";
	private final String scoreNourriture = "scr_point_nourriture";
	private final String scoreBois = "scr_point_bois";
	private final String scoreMinerai = "scr_point_minerai";
	private final String scorePierre = "scr_point_pierre";
	private final String scoreBatiment = "scr_point_bat";
	private final String scoreTour = "scr_point_tour";
	private final String scoreUnite = "scr_point_unite";

	private Connection connection;
	
	public BDScore(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * 
	 * @param pseudo
	 * 			The player id
	 * 
	 * @param eau
	 * 			The water score
	 * 
	 * @param nourriture
	 * 			The food score
	 * 
	 * @param bois
	 * 			The wood score
	 * 
	 * @param minerai
	 * 			The ore score
	 * 
	 * @param pierre
	 * 			The stone score
	 * 
	 * @param batiment
	 * 			The building score
	 * 
	 * @param tour
	 * 			The turn score
	 * 
	 * @param unite
	 * 			The unit score
	 * 
	 * @return The score id
	 */
	public int create(int pseudo, int eau, int nourriture,
			int bois, int minerai, int pierre,
			int batiment, int tour, int unite) {
		boolean resultat = true;
		
		String query = "INSERT INTO unknow_world.score (" + scoreIdPseudo + "," + scoreEau + "," + scoreNourriture + "," + scoreBois + "," + scoreMinerai + "," + scorePierre + "," + scoreBatiment + "," + scoreTour + "," +scoreUnite + ") VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, pseudo);
			statement.setInt(2, eau);
			statement.setInt(3, nourriture);
			statement.setInt(4, bois);
			statement.setInt(5, minerai);
			statement.setInt(6, pierre);
			statement.setInt(7, batiment);
			statement.setInt(8, tour);
			statement.setInt(9, unite);
			
			statement.execute();
			
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			resultat = false;
		}
		finally {
			try {
				statement.close();
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
	/**
	 * Gets score data from an id
	 * 
	 * @param id
	 * 			The id of the score
	 * 
	 * @return The score data
	 */
	public String read(int id) {
		return "";
	}
	
	
	/**
	 * Gets all the score data from the score table
	 * 
	 * @return The score data
	 */
	public ArrayList<String> readAll() {
		
		ArrayList<String> listeScore = new ArrayList<>();
		
		String query = "SELECT * FROM unknow_world.score";
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			
			statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				BDUtilisateur utilisateur = new BDUtilisateur(connection);
				String pseudo = utilisateur.read(resultSet.getInt(scoreIdPseudo));
				int eau = resultSet.getInt(scoreEau);
				int nourriture = resultSet.getInt(scoreNourriture);
				int bois = resultSet.getInt(scoreBois);
				int minerai = resultSet.getInt(scoreMinerai);
				int pierre = resultSet.getInt(scorePierre);
				int batiment = resultSet.getInt(scoreBatiment);
				int tour = resultSet.getInt(scoreTour);
				int unite = resultSet.getInt(scoreUnite);
				int total = eau + nourriture + bois + minerai + pierre + batiment + tour + unite;
				
				String score = pseudo + ";" + total;
				listeScore.add(score);
			}			
			
			resultSet.close();
			
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listeScore;
	}

}
