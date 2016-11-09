package fr.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import fr.controller.ControleurPrincipal;
import fr.controller.IControleur;

public class EcranScore extends Ecran {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2218705751742494286L;
	
	private JPanel panScore;
	private BBouton cmdRetour;
	private BorderFactory bordure;
	private BufferedImage imgFond;
	private GridBagConstraints gbc;
	
	
	public EcranScore(IControleur controleur) {
		super(controleur);
	}
	
	public void paintComponent(Graphics g) // utilisation de l'objet Graphics 2D
	{
		g.drawImage(imgFond, 0, 0, this.getWidth(), this.getHeight(), this); // adapte l'image a la taille de la fenetre
	}
	
	@Override
	public void initControles() {
		ControleurPrincipal ctrlScore = ((ControleurPrincipal)controleur);
		ArrayList<String> score = ctrlScore.getScore();
		
		try {
			imgFond = ImageIO.read(new File("data/img/348387-admin.jpg"));		      
		} catch (IOException e) {
		      e.printStackTrace();
	    }
		
		panScore = new JPanel();
		panScore.setLayout(new BoxLayout(panScore, BoxLayout.PAGE_AXIS));
		//panScore.setBorder(bordure.createLoweredBevelBorder());
		panScore.setOpaque(false);
		
		
		
		if(score.size() != 0) {
			Iterator<String> it = score.iterator();
			JTextPane titre = new JTextPane();
			titre.setText("Pseudo	Scores \r\n");
			titre.setOpaque(false);
			panScore.add(titre);
			while(it.hasNext()) {
				String texteScore = it.next();
				texteScore = texteScore.replaceAll(";", "	");
				JTextPane panelScore = new JTextPane();
				panelScore.setText(texteScore);
				panelScore.setVisible(true);
				panelScore.setOpaque(false);
				panScore.add(panelScore);
				panelScore.setForeground(Color.WHITE);
				panelScore.setFont(new Font("arial", Font.ITALIC, 30));
				titre.setFont(new Font("arial", Font.BOLD, 30));
				titre.setForeground(Color.YELLOW);
				

			}
			
		}
		else {
			String texteScore = "Aucun Score";
			JTextPane panelScore = new JTextPane();
			panelScore.setText(texteScore);
			panelScore.setVisible(true);
			panScore.add(panelScore);
		}
		
		JPanel tout = new JPanel();
		cmdRetour = new BBouton("Retour", "data/img/retour.png", "data/img/retour2.png");
		//cmdRetour.setAlignmentX(CENTER_ALIGNMENT);
		cmdRetour.setVisible(true);
		
		
		cmdRetour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlScore.afficherMenuPrincipal();
				
			}
		});
		
		gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0; 
		gbc.gridy = 3; 
		gbc.gridx = 0; 
		gbc.gridwidth = 2;
		gbc.gridheight = 1; 
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 15, 10, 10);
		
		tout.setLayout(new BoxLayout(tout, BoxLayout.Y_AXIS));
		this.setLayout(new GridBagLayout());
		tout.add(panScore);
		this.add(cmdRetour, gbc);
		tout.setOpaque(false);
		cmdRetour.setPreferredSize(new Dimension(250,70));
		this.add(tout);

			
		
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
