package fr.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import fr.modele.ModeleBase;
import fr.modele.message.EMessageType;
import fr.modele.message.Message;
import fr.modele.ressource.ERessourceJoueur;
import fr.modele.ressource.RessourceJoueur;
import fr.vue.Ecran;
import fr.vue.Vue;

public class ControleurVueExploration extends ControleurVue {

	public ControleurVueExploration(IControleur controleur, Vue vue) {
		super(controleur, vue);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Updates the unit data
	 * 
	 * @param label
	 * 			The component representing the available units
	 * 
	 * @param label1
	 * 			The component representing the units already in exploration
	 */
	public void updateUniteInfo(JLabel label, JLabel label1) {
		ArrayList<String> contructionHistorique = new ArrayList<>();
		

		if((label != null) && (label1 != null)) {
			RessourceJoueur resUniteDispo = modele.getRessource(ERessourceJoueur.RESSOURCE_UNITE);
			RessourceJoueur resUniteExploration = modele.getRessource(ERessourceJoueur.RESSOURCE_UNITE_EXPLORATION);
		
			label.setText(""+resUniteDispo.getRessourcePremiere());
			label1.setText(""+resUniteExploration.getRessourcePremiere());
			

		}		
	}
	
	/**
	 * Launches a exploration job with the number units requested
	 *  
	 * @param nbUnite
	 * 		The number of units to send in exploration
	 */
	public void envoyerUniteEnExploration(String nbUnite) {
		if((nbUnite != null) && (!nbUnite.trim().equals(""))) {
			int nbUniteAEnvoyer = Integer.parseInt(nbUnite);
			modele.envoyerUniteEnExploration(nbUniteAEnvoyer);
		}
	}
	
	/**
	 * Updates the display of the exploration events history
	 * 
	 * @param txtArea
	 * 			The component to update
	 */
	public void updateHistorique(JTextArea txtArea) {		
		String historique = "";
		if(txtArea != null) {
			Iterator<Message> it = modele.getHistorique().iterator();
			while(it.hasNext()) {
				Message message = it.next();
				if(message.getTypeMessage() == EMessageType.MESSAGE_EXPLORATION) {
					historique += message.getTexte() + "\r\n";
				}
			}
			
			txtArea.setText(historique);
		}		
	}

	@Override
	public ModeleBase getModele() {
		return modele;
	}

	@Override
	public void changerEcran(Ecran ecran) {
		// TODO Auto-generated method stub

	}

}
