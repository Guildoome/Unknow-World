package fr.vue;

import javax.swing.JPanel;

import fr.controller.ControleurVue;

public abstract class Vue extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4691531568715236842L;
	
	protected ControleurVue controleur;
	
	public Vue(ControleurVue controleur) {
		this.controleur = controleur;
		initControles();
		updateControles();
	}
	
	public abstract void initControles();
	public abstract void updateControles();

}
