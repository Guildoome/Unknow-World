package fr.jeu;

import fr.controller.ControleurPrincipal;
import fr.modele.ModeleBase;
import fr.modele.ModeleBaseFichier;
import fr.modele.Sons;
import fr.modele.bd.ModeleBaseBD;
import fr.vue.View;

public class LanceurJeu {

	public static void main(String[] args) {
		String repertoireData = System.getProperty("user.dir") + "/data";	
		
		//Switch to local files if the database is not available
		ModeleBase modele = new ModeleBaseBD(repertoireData);		
		if(!modele.isConnected()) {
			modele = new ModeleBaseFichier(repertoireData);
		}
				
		View view = new View();
			
		@SuppressWarnings("unused")
		ControleurPrincipal controleur = new ControleurPrincipal(modele, view);
		Sons.playSound();
	}

}
