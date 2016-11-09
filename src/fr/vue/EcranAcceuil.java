package fr.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.controller.Charger;
import fr.controller.ControleurPrincipal;
import fr.controller.IControleur;

public class EcranAcceuil  extends Ecran implements MouseListener
{
	private static final long serialVersionUID = 1L;
	private BufferedImage imgFond;
	private BufferedImage imgLogo;
	private BufferedImage imgMenu;
	
	private PanelFadeIn panLogo;
	private JPanel panMenu;
	private JPanel panBoutonMenu;
	
	private BBouton cmdNouvellePartie; // initialisation du boutton
	private BBouton cmdChargerPartie; //
	private BBouton cmdAfficherScore; //
	private BBouton cmdAfficherCredit; //
	private BBouton cmdQuitter; //	

	public EcranAcceuil(IControleur controleur)
	{		
		super(controleur);
	}
	
	@Override
	public void paintComponent(Graphics g) // utilisation de l'objet Graphics 2D
	{
		g.drawImage(imgFond, 0, 0, this.getWidth(), this.getHeight(), this); // adapte l'image a la taille de la fenetre
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
	}
		
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initControles() {
		this.setBackground(Color.BLUE);// ajout d'un couleur de fond pour faire la difference		
		this.addMouseListener(this);
		this.setLayout(new FlowLayout()); // Responsive		
		try {
			imgFond = ImageIO.read(new File("data/img/348387-admin.jpg"));		      
		} catch (IOException e) {
		      e.printStackTrace();
	    }
		
		panBoutonMenu = new JPanel();
		
		panBoutonMenu.setOpaque(false); // rendre Jpanel invisible
		panBoutonMenu.setLayout(new GridLayout(5, 1, 5, 5)); // Responsive
		
		panBoutonMenu.setBorder(new EmptyBorder(90, 45, 90, 45)); // ajoute un espace vide en haut gauche bas droite
		
		cmdNouvellePartie = new BBouton("Nouvelle Partie", "data/img/nouvelle-partie.png", "data/img/nouvelle-partie2.png");
		panBoutonMenu.add(cmdNouvellePartie); // ajout de la zone ecriture
		cmdNouvellePartie.addActionListener(new MenuActionListener());
		
				
		cmdChargerPartie = new BBouton("Charger Partie", "data/img/charger-partie.png", "data/img/charger-partie2.png");
		panBoutonMenu.add(cmdChargerPartie);
		cmdChargerPartie.addActionListener(new MenuActionListener());
		
		cmdAfficherScore = new BBouton("Afficher Score", "data/img/afficher-score.png", "data/img/afficher-score2.png");
		panBoutonMenu.add(cmdAfficherScore); // ajout de la zone ecriture
		cmdAfficherScore.addActionListener(new MenuActionListener());
				
		cmdAfficherCredit = new BBouton("Afficher Credit", "data/img/afficher-credit.png", "data/img/afficher-credit2.png");
		panBoutonMenu.add(cmdAfficherCredit);
		cmdAfficherCredit.addActionListener(new MenuActionListener());
		
		cmdQuitter = new BBouton("Quitter Jeu", "data/img/quitter-jeu.png", "data/img/quitter-jeu2.png");
		panBoutonMenu.add(cmdQuitter);
		cmdQuitter.addActionListener(new MenuActionListener());
		
		panMenu = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2600558076154162381L;

			@Override
			public void paintComponent(Graphics g) // utilisation de l'objet Graphics 2D
			{
				g.drawImage(imgMenu, 0, 0, panMenu.getWidth(), panMenu.getHeight(), panMenu); // adapte l'image a la taille de la fenetre
				panMenu.setBounds(80, 180, 364, 544);			}
		};
		
		panMenu.setOpaque(false); // rendre Jpanel invisible
		panMenu.setPreferredSize(new Dimension(364,544)); // taille du Jpanel
		
		panMenu.setLayout(new BorderLayout()); // responssive
		panMenu.add(panBoutonMenu, BorderLayout.CENTER); // ajout class Green	
		
		try {
			imgMenu = ImageIO.read(new File("data/img/interface.png"));
		      
	    } catch (IOException e) {
	    	e.printStackTrace();

	    }
		
		/**/
		panLogo = new PanelFadeIn(5000, 100, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2600558076154162381L;

			@Override
			public void paintComponent(Graphics g) // utilisation de l'objet Graphics 2D
			{
				g.drawImage(imgLogo, 0, 0, panLogo.getWidth(), panLogo.getHeight(), panLogo); // adapte l'image a la taille de la fenetre			
			}
		};
		
		panLogo.setOpaque(false); // rendre Jpanel invisible
		panLogo.setPreferredSize(new Dimension(960,140)); // taille du Jpanel
			
		try {
			imgLogo = ImageIO.read(new File("data/img/titre.png"));
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		((ControleurPrincipal)controleur).setMenuSauvegardeActive(false);
		((ControleurPrincipal)controleur).setPartieActive(false);
		
		this.add(panLogo); 
		this.add(panMenu);
		this.validate();
						
		}
	
	class MenuActionListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();
			if(source == cmdNouvellePartie)
			{
				creerNouvellePartie();
			}
			else if (source == cmdChargerPartie)
			{
				chargerPartie();
			}
			else if (source == cmdAfficherScore)
			{
				afficherScore();
			}
			else if (source == cmdAfficherCredit)
			{
				afficherCredit();
			}
			else if (source == cmdQuitter)
			{
				quitterJeu();
			}
		}
		
	}
// fin 
// affichage de la nouvelle vue
	private void creerNouvellePartie()
	{
		if(controleur != null) {
			((ControleurPrincipal)controleur).creerNouvellePartie();
		}
	}
// fin
	private void chargerPartie()
	{
		String repertoireData = System.getProperty("user.dir") + "/data";
		
		ControleurPrincipal ctrlPrincipal = (ControleurPrincipal)controleur;
		
		String save = Charger.charge(repertoireData + "/save", this);
		if((save != null) && (!save.trim().equals(""))) {
			ctrlPrincipal.chargerPartie(repertoireData + "/save/" + save);
		}
	}
	
	private void afficherScore()
	{
		((ControleurPrincipal)controleur).afficherScore();
	}
	
	private void afficherCredit()
	{
		((ControleurPrincipal)controleur).afficherCredit();
	}
	
	private void quitterJeu()
	{
		int QUITTER_PARTIE = JOptionPane.showConfirmDialog(this, "Voulez-vous Quitter le jeu ?", "QUITTER",JOptionPane.YES_NO_OPTION);
		
		if(QUITTER_PARTIE == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}

}
