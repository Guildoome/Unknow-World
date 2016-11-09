package fr.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import fr.modele.ModeleBase;
import fr.modele.message.EMessageType;
import fr.modele.message.Message;
import fr.modele.ressource.ERessourceJoueur;
import fr.modele.ressource.RessourceJoueur;
import fr.vue.Ecran;
import fr.vue.JListHistorique;
import fr.vue.Vue;

public class ControleurVueGenerale extends ControleurVue {
	
	public ControleurVueGenerale(IControleur controleur, Vue vue) {
		super(controleur, vue);
	}
	
	/**
	 * Updates the display of the plyer resources
	 * 
	 * @param ressource
	 * 			An array of the components to update
	 */
	public void updateRessource(JLabel[] ressource) {
		if(ressource != null) {
			RessourceJoueur res = modele.getRessource(ERessourceJoueur.RESSOURCE_BASE);
			ressource[0].setText("" + res.getRessourcePremiere());
			ressource[1].setText("" + res.getRessourceSecondaire());
			
			res = modele.getRessource(ERessourceJoueur.RESSOURCE_NOURRITURE);			
			ressource[2].setText(res.getRessourcePremiere() + "/" + res.getRessourceSecondaire());
			
			res = modele.getRessource(ERessourceJoueur.RESSOURCE_EAU);			
			ressource[3].setText(res.getRessourcePremiere() + "/" + res.getRessourceSecondaire());
			
			res = modele.getRessource(ERessourceJoueur.RESSOURCE_BOIS);			
			ressource[4].setText(res.getRessourcePremiere() + "/" + res.getRessourceSecondaire());
			
			res = modele.getRessource(ERessourceJoueur.RESSOURCE_MINERAI);			
			ressource[5].setText(res.getRessourcePremiere() + "/" + res.getRessourceSecondaire());
			
			res = modele.getRessource(ERessourceJoueur.RESSOURCE_PIERRE);			
			ressource[6].setText(res.getRessourcePremiere() + "/" + res.getRessourceSecondaire());
			
			res = modele.getRessource(ERessourceJoueur.RESSOURCE_UNITE_EXPLORATION);			
			ressource[7].setText(res.getRessourcePremiere() + "/" + res.getRessourceSecondaire());
			
			res = modele.getRessource(ERessourceJoueur.RESSOURCE_UNITE_CONSTRUCTION);			
			ressource[8].setText(res.getRessourcePremiere() + "/" + res.getRessourceSecondaire());
			
			res = modele.getRessource(ERessourceJoueur.RESSOURCE_UNITE_DEFENSE);			
			ressource[9].setText(res.getRessourcePremiere() + "/" + res.getRessourceSecondaire());			
		}		
	}
	
	/**
	 * Updates the display of the events history
	 * 
	 * @param lstHistorique
	 * 			The component to update
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateHistorique(JListHistorique lstHistorique) {
		
		ArrayList<Message> historique = new ArrayList<>();
		
		if(lstHistorique != null) {
			Iterator<Message> it = modele.getHistorique().iterator();
			while(it.hasNext()) {
				Message message = it.next();
				EMessageType type = message.getTypeMessage();
				if((type != EMessageType.MESSAGE_EXPLORATION) && (type != EMessageType.MESSAGE_CONSTRUCTION) && (type != EMessageType.MESSAGE_DEFENSE)) {
					historique.add(message);
				}
			}
			
			lstHistorique.setListData(historique.toArray());
		}		
	}
	
	/**
	 * 
	 */
	public void updateCarte() {
		
	}
	
	/**
	 * Ends the turn and executes remaining jobs and other events
	 */
	public void finirTour() {
		ControleurPrincipal ctrl = (ControleurPrincipal)controleur;
		modele.effectuerTourJeu();
		
		if(modele.objectifAtteint()) {
			JOptionPane.showMessageDialog(vue, "Bravo "+ modele.getPseudoJoueur()+ " Vous avez gagné avec un total de "+ modele.getScoreJoueur().getPtTotal()+" points");
			modele.enregistrerScore();
			
			ctrl.afficherCredit();
			
		}
		
		if(!modele.joueurEstEnVie()) {
			JOptionPane.showMessageDialog(vue, "Vous avez perdu");
			ctrl.afficherMenuPrincipal();
		}
	}

	@Override
	public ModeleBase getModele() {
		return modele;
	}

	@Override
	public void changerEcran(Ecran ecran) {
		
	}

}
