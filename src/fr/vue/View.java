package fr.vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import fr.controller.Charger;
import fr.controller.ControleurPrincipal;
import fr.controller.IControleur;
import fr.modele.Sons;

public class View extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JMenuBar mbPrincipale;
	private JMenu mnuFichier;
	private JMenu mnuAide;
	private JMenuItem mnuFichierNouveau;
	private JMenuItem mnuFichierCharger;
	private JMenuItem mnuFichierSauvegarder;
	private JMenuItem mnuFichierSons;
	private JMenuItem mnuFichierQuitter;
	private JMenuItem mnuAidePropos;
	private JMenuItem mnuAideManuel;
	private JMenuItem mnuFichierScreen;
	private JMenuItem mnuFichierQuitterPartie;
	
	private IControleur controleur;
	
	private String version = "Version 3.0.1";
	
	public View()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Look and feel not set.");
		}
		//fenetre principale		
	    this.setTitle("Unknow World");
	    this.setSize(1280, 850);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);	
	    this.setVisible(true);
	 
	   
	    initMenu(); // appel Jmenubar
	    
//  debut insertion jMenuBar
	}
	
	public void setControleur(IControleur controleur) {
		this.controleur = controleur;
	}
		
	public void initMenu() {
		
		mbPrincipale = new JMenuBar();
		mnuFichier = new JMenu ("Fichier");
		mnuAide = new JMenu ("Aide");
		
		mnuFichierNouveau = new JMenuItem("Nouvelle partie", new ImageIcon("data/img/nouvellePartie.png"));
		mnuFichierCharger = new JMenuItem("Charger partie", new ImageIcon("data/img/charge.png"));
		mnuFichierSauvegarder = new JMenuItem("Sauvegarder partie", new ImageIcon("data/img/sauvegarde.png"));
		mnuFichierSons = new JMenuItem("Sons On/Off", new ImageIcon("data/img/sons.png"));
		mnuFichierScreen = new JMenuItem("Capture ecran", new ImageIcon("data/img/screen.png"));
		mnuFichierQuitterPartie = new JMenuItem("Quitter Partie", new ImageIcon("data/img/quitterPartie.png"));
		mnuFichierQuitter = new JMenuItem("Quitter Jeu", new ImageIcon("data/img/quitterJeu.png"));
		mnuAidePropos = new JMenuItem("A propos", new ImageIcon("data/img/aPropos.png"));
		mnuAideManuel = new JMenuItem("Manuel", new ImageIcon("data/img/manuel.png"));
		
		mnuFichier.setMnemonic('f');
		mnuAide.setMnemonic ('?');
				
		mnuFichierNouveau.setAccelerator(KeyStroke.getKeyStroke("alt F1"));
		mnuFichierNouveau.addActionListener(new MenuActionListener()); 
		mnuFichierNouveau.setMnemonic('N');
		mnuFichier.add (mnuFichierNouveau);
		
		mnuFichierSauvegarder.setAccelerator(KeyStroke.getKeyStroke("alt F2"));
		mnuFichierSauvegarder.addActionListener(new MenuActionListener()); 
		mnuFichierSauvegarder.setMnemonic('S');
		mnuFichier.add (mnuFichierSauvegarder);
		
		mnuFichierCharger.setAccelerator(KeyStroke.getKeyStroke("alt F3"));
		mnuFichierCharger.addActionListener(new MenuActionListener()); 
		mnuFichierCharger.setMnemonic('C');
		mnuFichier.add (mnuFichierCharger);
		
		mnuFichier.addSeparator();
		
		mnuFichierSons.setAccelerator(KeyStroke.getKeyStroke("alt F9"));
		mnuFichierSons.addActionListener(new MenuActionListener()); 
		mnuFichierSons.setMnemonic('O');
		mnuFichier.add (mnuFichierSons);
		
		mnuFichierScreen.setAccelerator(KeyStroke.getKeyStroke("alt F10"));
		mnuFichierScreen.addActionListener(new MenuActionListener()); 
		mnuFichierScreen.setMnemonic('P');
		mnuFichier.add (mnuFichierScreen);
		
		mnuFichier.addSeparator();
		
		mnuFichierQuitterPartie.setAccelerator(KeyStroke.getKeyStroke("alt F5"));
		mnuFichierQuitterPartie.addActionListener(new MenuActionListener()); 
		mnuFichierQuitterPartie.setMnemonic('U');
		mnuFichier.add(mnuFichierQuitterPartie);
		
		mnuFichierQuitter.setAccelerator(KeyStroke.getKeyStroke("alt F4"));
		mnuFichierQuitter.addActionListener(new MenuActionListener()); 
		mnuFichierQuitter.setMnemonic('Q');
		mnuFichier.add(mnuFichierQuitter);
		
		mnuAidePropos.setAccelerator(KeyStroke.getKeyStroke("alt F12"));
		mnuAidePropos.addActionListener(new MenuActionListener()); 
		mnuAidePropos.setMnemonic('A');
		mnuAide.add(mnuAidePropos);
		
		mnuAideManuel.setAccelerator(KeyStroke.getKeyStroke("alt F11"));
		mnuAideManuel.addActionListener(new MenuActionListener()); 
		mnuAideManuel.setMnemonic('M');
		mnuAide.add(mnuAideManuel);
		
		mbPrincipale.add(mnuFichier);
		mbPrincipale.add(mnuAide);
		
		this.setJMenuBar(mbPrincipale);
		//this.add(mbPrincipale, BorderLayout.WEST);
	}

	class MenuActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();
			if (source == mnuFichierNouveau) mnuFichierNouveau_click();
			if (source == mnuFichierSauvegarder) mnuFichierSauvegarder_click();
			if (source == mnuFichierCharger) mnuFichierCharger_click();
			if (source == mnuFichierScreen) mnuFichierScreen_click();
			if (source == mnuFichierSons ) mnuFichierSons_click();
			if (source == mnuFichierQuitter) mnuFichierQuitter_click();
			if (source == mnuAidePropos) mnuAideAPropos_click();
			if (source == mnuAideManuel) mnuAideManuel_click();
			if (source == mnuFichierQuitterPartie) mnuFichierQuitterPartie_click();
		}
	}

	private void mnuFichierQuitterPartie_click() {
		((ControleurPrincipal)controleur).afficherMenuPrincipal();
	}
	
	private void mnuAideManuel_click() {
		((ControleurPrincipal)controleur).ouvrirAide();
	}
	
	private void mnuAideAPropos_click()
	{
		APropos dlg = new APropos(this, "Unknow World ");
		dlg.setDescription("Le jeu de l'annee");
		dlg.setCopyright("G. Delbar, J. Inimod, C. Ollier");
		dlg.setVersion(version);
		dlg.setVisible(true);
	}
	
	private void mnuFichierQuitter_click()
	{
		int QUITTER_PARTIE = JOptionPane.showConfirmDialog(this, "Voulez-vous Quitter le jeu ?", "QUITTER",JOptionPane.YES_NO_OPTION);
		
		if(QUITTER_PARTIE == JOptionPane.OK_OPTION) {
			this.dispose();
			System.exit(0);
		}
	}
	
	private void mnuFichierSons_click()
	{
		if (!Sons.isPaused)
		Sons.pauseSound();
		else
		Sons.resumeSound();
	}
	
	public void mnuFichierScreen_click() {
		String repertoireData = System.getProperty("user.dir") + "/data";
		Rectangle rect = this.getContentPane().getBounds();
		
	    try {
	        String format = "png";
	        int nbrCapture = 0;
	        while((new File(repertoireData + "/screen/" + "screen" + nbrCapture + "." + format)).exists()) {
	        	nbrCapture++;
	        }
	        
	        String fileName = "screen" + nbrCapture + "." + format;
	        
	        BufferedImage captureImage =
	                new BufferedImage(rect.width, rect.height,
	                                    BufferedImage.TYPE_INT_ARGB);
	        this.getContentPane().paint(captureImage.getGraphics());
	 
	        ImageIO.write(captureImage, format, new File(repertoireData + "/screen/" +fileName));
	 
	        
	    } catch (IOException ex) {
	        System.err.println(ex);
	    }
	}
	
	private void mnuFichierNouveau_click()
	{
		if(controleur != null) {
			((ControleurPrincipal)controleur).creerNouvellePartie();
		}
	}
	
	private void mnuFichierCharger_click() {
		String repertoireData = System.getProperty("user.dir") + "/data";
		
		ControleurPrincipal ctrlPrincipal = (ControleurPrincipal)controleur;
		
		String save = Charger.charge(repertoireData + "/save", this);
		if((save != null) && (!save.trim().equals(""))) {
			ctrlPrincipal.chargerPartie(repertoireData + "/save/" + save);
		}
	
	}
		

	private void mnuFichierSauvegarder_click() {
		String repertoireData = System.getProperty("user.dir") + "/data";
		
		ControleurPrincipal ctrlPrincipal = (ControleurPrincipal)controleur;
		
		String save = Charger.save(repertoireData + "/save/", this, ctrlPrincipal.getPseudoJoueur());
		if((save != null) && (!save.trim().equals(""))) {
			ctrlPrincipal.sauvegarderPartie(repertoireData + "/save/" + save);
		}		
	}
	
	public void setPseudo(Component comp) {
		String[] options = {"OK"};
		JPanel panel = new JPanel();
		JLabel lbl = new JLabel("Entrer votre pseudo: ");
		JTextField txt = new JTextField(10);
		
		panel.add(lbl);
		panel.add(txt);
		
		int selectedOption = JOptionPane.showOptionDialog(null, panel, "Unknow World", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
			
		if(selectedOption == 0)
		{
		    String nom = txt.getText();
		    if((nom != null) && (!nom.trim().equals(""))) {
		    	JOptionPane.showMessageDialog(comp, "Bienvenue "+nom, "Unknow World", JOptionPane.INFORMATION_MESSAGE);
		    	((ControleurPrincipal)controleur).setPseudoJoueur(nom);	
				}
				else {
					JOptionPane.showMessageDialog(comp, "Veuillez entrer un pseudo !", "Erreur", JOptionPane.ERROR_MESSAGE);
					setPseudo(comp);
				}	
		    
		}
		
		 if(selectedOption == JOptionPane.CLOSED_OPTION) {
			 ((ControleurPrincipal)controleur).changerEcran(new EcranAcceuil(controleur));
		 }
	}
	
	public void setMenuSauvegardeActive(boolean estActif) {
		this.mnuFichierSauvegarder.setEnabled(estActif);
	}
	
	public void setPartieActive(boolean estActif) {
		this.mnuFichierQuitterPartie.setEnabled(estActif);
	}
	
	public class APropos extends JDialog implements ActionListener
	{
		
		private static final long serialVersionUID = 1L;
		private JLabel lblDescription = new JLabel ();
		private JLabel lblCopyright = new JLabel ();
		private JLabel lblVersion = new JLabel () ;
		private JButton cmdOK = new JButton ("OK");
		
		public APropos(JFrame parent, String titre)
		{
			super(parent, "A Propos de " + titre, true);
			Point loc = parent.getLocation();
			this.setLocation((int) loc.getX()+50,(int)loc.getY()+100);
			this.setResizable(false);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
			initControles();
		}
		
		private void initControles()
		{
			JPanel zoneClient = (JPanel) this.getContentPane();
			zoneClient.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			JPanel panDroite = new JPanel (new GridLayout(3,1,10,10));
			panDroite.add (lblDescription);
			panDroite.add (lblCopyright);
			panDroite.add (lblVersion);
			JPanel panHaut = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
			panHaut.add (panDroite);
			JPanel panBas = new JPanel (); // FlowLayout par défaut
			cmdOK.addActionListener(this); // Voir plus bas
			panBas.add(cmdOK);
			zoneClient.add (panHaut, BorderLayout.NORTH);
			zoneClient.add (panBas, BorderLayout.SOUTH);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == cmdOK) this.dispose();
		}
		
		public void setDescription (String texte)
		{
			lblDescription.setText(texte);
			this.pack();
		}
		
		public void setVersion (String texte)
		{
			lblVersion.setText(texte);
			this.pack();
		}
		
		public void setCopyright (String texte)
		{
			lblCopyright.setText(texte);
			this.pack();
		}
		
	}
// fin JmenuBar
}