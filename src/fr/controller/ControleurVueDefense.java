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

public class ControleurVueDefense extends ControleurVue {

	public ControleurVueDefense(IControleur controleur, Vue vue) {
		super(controleur, vue);
	}
	
	/**
	 * Updates the unit data
	 * 
	 * @param label
	 * 			The component representing the available units
	 * 
	 * @param label1
	 * 			The component representing the units already in defense
	 */
	public void updateUniteInfo(JLabel label, JLabel label1) {
		ArrayList<String> contructionHistorique = new ArrayList<>();
		if((label != null) && (label1 != null)) {
			RessourceJoueur resUniteDispo = modele.getRessource(ERessourceJoueur.RESSOURCE_UNITE);
			RessourceJoueur resUniteDefense = modele.getRessource(ERessourceJoueur.RESSOURCE_UNITE_DEFENSE);
		
			label.setText(""+resUniteDispo.getRessourcePremiere());
			label1.setText(""+resUniteDefense.getRessourcePremiere());
			
		}		
	}
	
	/**
	 * Launches a defense job with the number units requested
	 *  
	 * @param nbUnite
	 * 		The number of units to send in defense
	 */
	public void envoyerUniteEnDefense(String nbUnite) {
		if((nbUnite != null) && (!nbUnite.trim().equals(""))) {
			int nbUniteAEnvoyer = Integer.parseInt(nbUnite);
			modele.envoyerUniteEnDefense(nbUniteAEnvoyer);
		}
	}
	
	/**
	 * Updates the display of the defense events history
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
				if(message.getTypeMessage() == EMessageType.MESSAGE_DEFENSE) {
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
