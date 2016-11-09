package fr.vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import fr.controller.ControleurVue;
import fr.controller.ControleurVueGenerale;
import fr.modele.message.Message;

public class VueGenerale extends Vue
{	
	private static final long serialVersionUID = 450340743297945746L;
	
	private JPanel panRessource;
	private JPanel panDetail;
	private JPanel panDetailHistorique;
	private JPanel panDetailScrollHistorique;
	private JPanel panDetailButtonHistorique;
	private JPanel panDetailImage;
	
	private JLabel lblAttaque;
	private JLabel lblDefense;
	private JLabel lblNourriture;
	private JLabel lblEau;
	private JLabel lblBois;
	private JLabel lblMinerai;
	private JLabel lblPierre;
	private JLabel lblUniteExploration;
	private JLabel lblUniteConstruction;
	private JLabel lblUniteDefense;
	
	private JListHistorique<Message> lstHistorique;
	
	private JButton cmdFinirTour;
	
	private BufferedImage imgFond;

	public VueGenerale(ControleurVue controleur)
	{		
			super(controleur);
			
	}

	@Override
	public void initControles() {
		this.setLayout(new FlowLayout()); // responssive
		this.setPreferredSize(new Dimension (1000,700));
		setRessourceControles();
		setDetailControles();
		
	}
	
	public void setRessourceControles() {
		panRessource = new JPanel();
		
		Border borderRessource = BorderFactory.createRaisedSoftBevelBorder();
		
		lblAttaque = new JLabel("attaque");
		lblAttaque.setIcon(new ImageIcon("data/img/attaque.png"));
		lblAttaque.setVerticalTextPosition(JLabel.BOTTOM);
		lblAttaque.setHorizontalTextPosition(JLabel.CENTER);
		lblAttaque.setToolTipText("Point d'attaque");
		panRessource.add(lblAttaque);
		
		lblDefense = new JLabel("defense");
		lblDefense.setIcon(new ImageIcon("data/img/defense2.png"));
		lblDefense.setVerticalTextPosition(JLabel.BOTTOM);
		lblDefense.setHorizontalTextPosition(JLabel.CENTER);
		lblDefense.setToolTipText("Point de défense");
		panRessource.add(lblDefense);
		
		lblNourriture = new JLabel("nourriture");
		lblNourriture.setIcon(new ImageIcon("data/img/nourriture.png"));
		lblNourriture.setVerticalTextPosition(JLabel.BOTTOM);
		lblNourriture.setHorizontalTextPosition(JLabel.CENTER);
		lblNourriture.setToolTipText("Nourriture");
		panRessource.add(lblNourriture);
		
		lblEau = new JLabel("eau");
		lblEau.setIcon(new ImageIcon("data/img/eau.png"));
		lblEau.setVerticalTextPosition(JLabel.BOTTOM);
		lblEau.setHorizontalTextPosition(JLabel.CENTER);
		lblEau.setToolTipText("Eau");
		panRessource.add(lblEau);
		
		lblBois = new JLabel("bois");
		lblBois.setIcon(new ImageIcon("data/img/bois.png"));
		lblBois.setVerticalTextPosition(JLabel.BOTTOM);
		lblBois.setHorizontalTextPosition(JLabel.CENTER);
		lblBois.setToolTipText("Bois");
		panRessource.add(lblBois);
		
		lblMinerai = new JLabel("minerai");
		lblMinerai.setIcon(new ImageIcon("data/img/minerai.png"));
		lblMinerai.setVerticalTextPosition(JLabel.BOTTOM);
		lblMinerai.setHorizontalTextPosition(JLabel.CENTER);
		lblMinerai.setToolTipText("Minerai");
		panRessource.add(lblMinerai);
		
		lblPierre = new JLabel("pierre");
		lblPierre.setIcon(new ImageIcon("data/img/pierre.png"));
		lblPierre.setVerticalTextPosition(JLabel.BOTTOM);
		lblPierre.setHorizontalTextPosition(JLabel.CENTER);
		lblPierre.setToolTipText("Pierre");
		panRessource.add(lblPierre);
				
		lblUniteExploration = new JLabel("exploration");
		lblUniteExploration.setIcon(new ImageIcon("data/img/unite.png"));
		lblUniteExploration.setVerticalTextPosition(JLabel.BOTTOM);
		lblUniteExploration.setHorizontalTextPosition(JLabel.CENTER);
		lblUniteExploration.setToolTipText("Unité en exploration");
		panRessource.add(lblUniteExploration);
		
		lblUniteConstruction = new JLabel("construction");
		lblUniteConstruction.setIcon(new ImageIcon("data/img/construction.png"));
		lblUniteConstruction.setVerticalTextPosition(JLabel.BOTTOM);
		lblUniteConstruction.setHorizontalTextPosition(JLabel.CENTER);
		lblUniteConstruction.setToolTipText("Bâtisseurs");
		panRessource.add(lblUniteConstruction);
		
		lblUniteDefense = new JLabel("defense");
		lblUniteDefense.setIcon(new ImageIcon("data/img/defense.png"));
		lblUniteDefense.setVerticalTextPosition(JLabel.BOTTOM);
		lblUniteDefense.setHorizontalTextPosition(JLabel.CENTER);
		lblUniteDefense.setToolTipText("Unité en défense");
		panRessource.add(lblUniteDefense);
		
		lblNourriture.setBorder(borderRessource);
		lblBois.setBorder(borderRessource);
		lblEau.setBorder(borderRessource);
		lblPierre.setBorder(borderRessource);
		lblMinerai.setBorder(borderRessource);
		lblAttaque.setBorder(borderRessource);
		lblUniteExploration.setBorder(borderRessource);
		lblDefense.setBorder(borderRessource);
		lblUniteDefense.setBorder(borderRessource);
		lblUniteConstruction.setBorder(borderRessource);
		
		this.add(panRessource);
	}
	
	public void setDetailControles() {
		panDetail = new JPanel();
		
		panDetailHistorique = new JPanel();
		panDetailHistorique.setLayout(new BoxLayout(panDetailHistorique, BoxLayout.Y_AXIS));
		
		cmdFinirTour = new JButton("Finir Tour");
		cmdFinirTour.addActionListener(new EndTurnButtonActionListener());
		
		Border borderJText = BorderFactory.createTitledBorder("Evenement");
		
		lstHistorique = new JListHistorique<>();
		lstHistorique.setLayoutOrientation(JListHistorique.VERTICAL);
		lstHistorique.setVisibleRowCount(33);
		
	//	lstHistorique.setAlignmentX(TOP_ALIGNMENT);
		//lstHistorique.setAlignmentY(LEFT_ALIGNMENT);

		JScrollPane panScrollHistorique = new JScrollPane(lstHistorique);
		panScrollHistorique.setPreferredSize(new Dimension(260, 530));
		
		panDetailScrollHistorique = new JPanel();
		panDetailButtonHistorique = new JPanel();
				
		panDetailScrollHistorique.add(panScrollHistorique);
		panDetailButtonHistorique.add(cmdFinirTour);
		panDetailHistorique.setBorder(borderJText);
		panDetailHistorique.add(panDetailScrollHistorique);
		panDetailHistorique.add(panDetailButtonHistorique);
		
		panDetailImage = new JPanel(){
			/**
			 * @return 
			 * 
			 */
			
			public void paintComponent(Graphics g) // utilisation de l'objet Graphics 2D
			{
				g.drawImage(imgFond, 0, 0, panDetailImage.getWidth(), panDetailImage.getHeight(), panDetailImage); // adapte l'image a la taille de la fenetre			
			}
		};

		panDetailImage.setPreferredSize(new Dimension (700,580));
		
		try {
			imgFond = ImageIO.read(new File("data/img/jeu.jpg"));
		      
	    } catch (IOException e) {
	    	e.printStackTrace();

	    }

/*
		panDetailImage.setBackground(Color.BLACK);
		panDetailImage.setPreferredSize(new Dimension (700,580));
	*/	
		panDetail.add(panDetailHistorique);
		panDetail.add(panDetailImage);
		
		this.add(panDetail);
	}

	@Override
	public void updateControles() {
		ControleurVueGenerale ctrl = (ControleurVueGenerale)controleur;
		
		JLabel[] ressource = { lblAttaque, lblDefense, lblNourriture, lblEau, lblBois, lblMinerai, lblPierre, lblUniteExploration, lblUniteConstruction, lblUniteDefense };
		
		ctrl.updateRessource(ressource);
		ctrl.updateHistorique(lstHistorique);
		ctrl.updateCarte();
	}
	
	class EndTurnButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == cmdFinirTour) cmdFinirTour_click();
		}
		
	}
	
	private void cmdFinirTour_click() {
		ControleurVueGenerale ctrl = (ControleurVueGenerale)controleur;
		ctrl.finirTour();
		updateControles();
	}
}
