package fr.controller;

import fr.modele.ModeleBase;
import fr.vue.Vue;

public abstract class ControleurVue implements IControleur {

	protected Vue vue;
	protected ModeleBase modele;
	protected IControleur controleur;

	public ControleurVue(IControleur controleur, Vue vue) {
		this.controleur = controleur;
		this.modele = controleur.getModele();
		this.vue = vue;
	}
	
	public void setVue(Vue vue) {
		this.vue = vue;
	}
	
	public IControleur getControleur() {
		return controleur;
	}

}
