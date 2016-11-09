package fr.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import fr.modele.ModeleBase;
import fr.modele.batiment.Batiment;
import fr.modele.batiment.TypeBatiment;
import fr.modele.message.EMessageType;
import fr.modele.message.Message;
import fr.vue.Ecran;
import fr.vue.Vue;
import fr.vue.VueConstruction;

/**
 * 
 * @author JGC
 *
 */
public class ControleurVueConstruction extends ControleurVue {

	public ControleurVueConstruction(IControleur controleur, Vue vue) {
		super(controleur, vue);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Updates the display of the list of buildings that can be constructed
	 * 
	 * @param box
	 * 			The component to update
	 */
	public void updateConstruction(JComboBox box) {
		box.removeAllItems();
		
		Iterator<TypeBatiment> it = modele.getListeTypeBatiment().iterator();		
		while(it.hasNext()) {
			TypeBatiment typeBatiment = it.next();
			if(!typeBatiment.getTypeBatiment().trim().equals("Vaisseau")) {
				box.addItem(typeBatiment.getTypeBatiment());
			}				
		}		
	}
	
	/**
	 * Updates the display of the building data to construct
	 * 
	 * @param item
	 * 			The name of the building
	 * 
	 * @param textArea
	 * 			The component to update
	 */
	public void updateConstructionText(String item, JTextArea textArea) {
		String donnneeBatiment = "";
		
		if(item != null) {
			Iterator<TypeBatiment> it = modele.getListeTypeBatiment().iterator();		
			while(it.hasNext()) {
				TypeBatiment typeBatiment = it.next();
				if(typeBatiment.getTypeBatiment().equals(item)) {
					
					donnneeBatiment += typeBatiment.getDescription() + "\r\n";
					donnneeBatiment += "\tAtt\tDef\tC.Nou\tC.Eau\tC.Bois\tC.Min\tC.Pie\tC.Uni" + "\r\n";
					donnneeBatiment += "\t" + typeBatiment.getAttaque() + "\t" + typeBatiment.getDefense() + "\t" + typeBatiment.getCoutNourriture() + "\t" + typeBatiment.getCoutEau() + "\t" + typeBatiment.getCoutBois() + "\t" + typeBatiment.getCoutMinerai() + "\t" + typeBatiment.getCoutPierre() + "\t" + typeBatiment.getCoutUnite();//
					break;
				}
			}
		}	
		
		textArea.setText(donnneeBatiment);
	}
	
	/**
	 * Updates the display of the list of buildings that evolve
	 * 
	 * @param box
	 * 			The component to update
	 */
	public void updateEvolution(JComboBox box) {
		box.removeAllItems();
		
		Iterator<Batiment> it = modele.getBatimentConstruit().iterator();
		while(it.hasNext()) {
			Batiment batiment = it.next();
			if(!batiment.getTypeBatiment().trim().equals("Vaisseau")) {
				box.addItem(batiment.getTypeBatiment());
			}	
		}
	}
	
	/**
	 * Updates the display of the building data to make evolve
	 * 
	 * @param index
	 * 			The index in the list of the building to make evolve
	 * 
	 * @param textArea
	 * 			The component to update
	 */
	public void updateEvolutionText(int index, JTextArea textArea) {
		String donnneeBatiment = "";
		
		if(index >= 0) {
			index++;
			Batiment batiment = modele.getBatimentConstruit().get(index);
			if(batiment != null) {
				donnneeBatiment += batiment.getTypeBatiment();
			}
		}
		
		textArea.setText(donnneeBatiment);
	}

	/**
	 * Updates the display of the construction events history
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
				if(message.getTypeMessage() == EMessageType.MESSAGE_CONSTRUCTION) {
					historique += message.getTexte() + "\r\n";
				}
			}
			
			txtArea.setText(historique);
		}		
	}
	
	/**
	 * Launches a construction job
	 * 
	 * @param nomBatiment
	 * 			The name of the building
	 */
	public void constructionBatiment(String nomBatiment) {
		modele.construireBatiment(nomBatiment);
	}
	
	/**
	 * Launches an evolution job
	 * 
	 * @param batimentIndex
	 * 			The index in the list of building constructed by the player
	 */
	public void evolutionBatiment(int batimentIndex) {
		batimentIndex++;
		Batiment batiment = modele.getBatimentConstruit().get(batimentIndex);
		modele.evoluerBatiment(batiment);
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
