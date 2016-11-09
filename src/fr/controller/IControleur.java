package fr.controller;

import fr.modele.ModeleBase;
import fr.vue.Ecran;

/**
 * 
 * @author JGC
 *
 */
public interface IControleur {
	public ModeleBase getModele();
	public void changerEcran(Ecran ecran);
}
