package fr.modele.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author JGC
 *
 */
public class BDUtilisateur {
	
	private final String utilisateurId = "id_usr";
	private final String utilisateurPseudo = "usr_pseudo";
	private final String utilisateurPassword = "usr_password";
	private final String utilisateurMail = "usr_mail";

	private Connection connection;
	
	public BDUtilisateur(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Insert a player data into the database
	 * 
	 * @param pseudo
	 * 			The name of the player
	 * 
	 * @param password
	 * 			Unused
	 * 
	 * @param mail
	 * 			Unused
	 * 
	 * @return
	 * 			the id of the player
	 */
	public int create(String pseudo, String password, String mail) {
		int resultat = -1;
		
		String query = "INSERT INTO unknow_world.utilisateur (" + utilisateurPseudo + "," + utilisateurPassword + "," + utilisateurMail + ") VALUES (?,?,?)";
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, pseudo);
			statement.setString(2, password);
			statement.setString(3, mail);
			
			statement.execute();
			
			connection.commit();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next()) {
				resultat = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultat;
	}
	
	/**
	 * Gets player data from an id
	 * 
	 * @param id
	 * 			The id of the player
	 * 
	 * @return The player data
	 */
	public String read(int id) {
		String resultat = "";
		
		String query = "SELECT * FROM unknow_world.utilisateur WHERE " + utilisateurId + " = ?";
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			connection.commit();
			
			if(resultSet.next()) {
				resultat = resultSet.getString(utilisateurPseudo);
			}
			
			resultSet.close();
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
		
		return resultat;
	}
	
	/**
	 * Gets all the player data from the user table
	 * 
	 * @return The player data
	 */
	public ArrayList<String> readAll() {
		return new ArrayList<>();
	}

}
