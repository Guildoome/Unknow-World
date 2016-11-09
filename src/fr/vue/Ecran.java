package fr.vue;

import java.awt.event.MouseListener;

import javax.swing.JPanel;

import fr.controller.IControleur;

public abstract class Ecran extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1890069422076950129L;
	
	protected IControleur controleur;
		
	public Ecran(IControleur controleur) {
		this.controleur = controleur;
		initControles();
	}
	
	public abstract void initControles();
}
