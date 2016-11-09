package fr.vue;

//import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.controller.ControleurVue;
import fr.controller.ControleurVueScore;



public class VueDetailScore extends Vue 
{
	private static final long serialVersionUID = 1L;
	
	private JPanel panScorePrincipal;
	private JLabel scoreTotal;
	
	private JPanel panScoreDetail;
	private JLabel scoreUnite;
	private JLabel scoreEau;
	private JLabel scoreTour;
	private JLabel scoreNourriture;
	private JLabel scoreBatiment;
	private JLabel scoreBois;
	private JLabel scoreMinerai;
	private JLabel scorePierre;
	
	public VueDetailScore(ControleurVue controleur)
	{
		super(controleur);
		
	}

	@Override
	public void initControles() {
		this.setLayout(new FlowLayout()); // responssive
		this.setPreferredSize(new Dimension (1000,700));
		
		//this.setBackground(Color.GRAY);
		//this.setOpaque(false); // rendre Jpanel invisible
		setScorePrincipalControles();		
		setScoreDetailControles();		
	}
	
	public void setScorePrincipalControles() {
		panScorePrincipal = new JPanel();
		panScorePrincipal.setPreferredSize(new Dimension(1005,250));
		panScorePrincipal.setOpaque(false); // rendre Jpanel invisible
		//GridLayout blocScore = new GridLayout(3,3);
		panScorePrincipal.setBorder(new EmptyBorder(150, 150, 200, 200)); // ajoute un espace vide en haut gauche bas droite
		//setLayout(new GridLayout(1,2, 15,15));//grille de 5 ligne et 2 colonne avec 25px d'intervalle entre 2
		
		//this.setSize(1000,1000);
		scoreTotal = new JLabel(); // ligne 1 gauche
		
		scoreTotal.setPreferredSize(new Dimension(400,80));
		scoreTotal.setBorder(BorderFactory.createRaisedBevelBorder());
		panScorePrincipal.add(scoreTotal);
		
		this.add(panScorePrincipal);
	}
	
	public void setScoreDetailControles() {
		panScoreDetail = new JPanel();
		panScoreDetail.setPreferredSize(new Dimension(1005,650));
		
		panScoreDetail.setOpaque(false); // rendre Jpanel invisible
		panScoreDetail.setBorder(new EmptyBorder(50, 150, 250, 190)); // ajoute un espace vide en haut gauche bas droite
		panScoreDetail.setLayout(new GridLayout(5,2, 15,15));//grille de 5 ligne et 2 colonne avec 25px d'intervalle entre 2
		
		scoreUnite = new JLabel();	// ligne 2 gauche	
		scoreUnite.setBorder(BorderFactory.createRaisedBevelBorder());
		scoreEau = new JLabel();// ligne 2 droite
		scoreEau.setBorder(BorderFactory.createRaisedBevelBorder());
		scoreTour = new JLabel();
		scoreTour.setBorder(BorderFactory.createRaisedBevelBorder());
		scoreNourriture = new JLabel();
		scoreNourriture.setBorder(BorderFactory.createRaisedBevelBorder());
		scoreBatiment = new JLabel();
		scoreBatiment.setBorder(BorderFactory.createRaisedBevelBorder());
		scoreBois = new JLabel();
		scoreBois.setBorder(BorderFactory.createRaisedBevelBorder());
		scoreMinerai = new JLabel();
		scoreMinerai.setBorder(BorderFactory.createRaisedBevelBorder());
		scorePierre = new JLabel();
		scorePierre.setBorder(BorderFactory.createRaisedBevelBorder());		
		panScoreDetail.add(scoreUnite);
		panScoreDetail.add(scoreEau);
		panScoreDetail.add(scoreTour);
		panScoreDetail.add(scoreNourriture);
		panScoreDetail.add(scoreBatiment);
		panScoreDetail.add(scoreBois);		
		panScoreDetail.add(scorePierre);			
		panScoreDetail.add(scoreMinerai);
		
		this.add(panScoreDetail);
	}

	@Override
	public void updateControles() {
		ControleurVueScore ctrl = (ControleurVueScore)controleur;
		ctrl.updateScore(scoreTotal, scoreUnite, scoreTour,
				scoreBatiment, scorePierre, scoreEau,
				scoreNourriture, scoreBois, scoreMinerai);
	}
}
