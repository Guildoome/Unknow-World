package fr.vue;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class BBouton extends JButton implements MouseListener
{
	private static final long serialVersionUID = 1L;
	
@SuppressWarnings("unused")
private String nomBouton;
	
	private BufferedImage imageCourante;
	
	private String nomPremiereImage;
	private String nomSecondeImage;
	
	private BufferedImage premiereImage;
	private BufferedImage secondeImage;
	
	public BBouton(String nomBouton, String nomPremiereImage, String nomSecondeImage){
		super(nomBouton);
		this.nomBouton = nomBouton;
		this.nomPremiereImage = nomPremiereImage;
		this.nomSecondeImage = nomSecondeImage;
				
		try {
			premiereImage = ImageIO.read(new File(this.nomPremiereImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			secondeImage = ImageIO.read(new File(this.nomSecondeImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imageCourante = premiereImage;
		
		//Gr�ce � cette instruction, notre objet va s'�couter
		//D�s qu'un �v�nement de la souris sera intercept�, il en sera averti
		this.setOpaque(false);
		this.addMouseListener(this);
		this.setBorder(null);
	}

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
		g2d.setPaint(gp);
		g2d.drawImage(imageCourante, 0, 0, this.getWidth(), this.getHeight(), null);
		g2d.setColor(Color.black);
		
	}
  
	//M�thode appel�e lors du clic de souris
	public void mouseClicked(MouseEvent event) { 
		
	}

	//M�thode appel�e lors du survol de la souris
	public void mouseEntered(MouseEvent event) { 
		imageCourante = secondeImage;
	}

	//M�thode appel�e lorsque la souris sort de la zone du bouton
	public void mouseExited(MouseEvent event) { 
		imageCourante = premiereImage;
	}

	//M�thode appel�e lorsque l'on presse le bouton gauche de la souris
	public void mousePressed(MouseEvent event) { }

	//M�thode appel�e lorsque l'on rel�che le clic de souris
	public void mouseReleased(MouseEvent event) { }       
	
}
