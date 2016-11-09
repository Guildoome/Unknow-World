package fr.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JPanel;

import fr.controller.ControleurPrincipal;
import fr.controller.IControleur;



public class EcranCredit extends Ecran {

	private static final long serialVersionUID = 8743925890696996428L;

	private JPanel panCredit;
	
	public EcranCredit(IControleur controleur) {
		super(controleur);
	}
	
	public void texteDefilant(
		int typeDefilement,
		int cadenceEnMs, int incrementEnPx, 
		int espaceGauche, int espaceHaut,
		Color coulAvantPlan, Color coulArrPlan,
		Component conteneur,
		BufferedReader fichierTexte) throws IOException {
	        String ligneCourante = null;
	        while(true){
	            ligneCourante = fichierTexte.readLine();
	            if(ligneCourante == null) break;
	            this._lignesDeTexte.add(ligneCourante);
	        }
	        this.graphicInitialization(espaceGauche, espaceHaut,
	                coulAvantPlan, coulArrPlan);
	        this.dataInitialization(typeDefilement, cadenceEnMs, incrementEnPx, conteneur);
	}
		
	private void graphicInitialization(int espaceGauche, int espaceHaut, Color coulAvantPlan, Color coulArrPlan) {
		this._espaceGauche = espaceGauche;
		this._espaceHaut = espaceHaut;
		this._coulAvantPlan = coulAvantPlan;
		this._coulArrPlan = coulArrPlan;
	}
	
	private void dataInitialization(int typeDefilement, final int cadenceEnMs, final int incrementEnPx, Component conteneur) {
	        
		this._conteneur = conteneur;
		
		this._actionPourTempo_montUniq_period = new TimerTask_Personnalise(){
		
		    {
		        this._hauteurConteneur = _conteneur.getHeight();
		        this._largeurConteneur = _conteneur.getWidth();
		        
		        _yDebut = this._hauteurConteneur - _espaceHaut;
		        
		    }
		    
		    @Override
		    public void run() {
		        _yDebut -= incrementEnPx;
		        if(_yDebut <= -1 * this._hauteurFonte * _lignesDeTexte.size()){
		            _yDebut = this._hauteurConteneur;
		        }
		        repaint();
		    }
		    
		};
		
		this._actionPourTempo_vaEtVient_period = new TimerTask_Personnalise(){
		
		    {
		        this._hauteurConteneur = _conteneur.getHeight();
		        this._largeurConteneur = _conteneur.getWidth();
		        
		        _yDebut = this._hauteurConteneur - _espaceHaut;
		        
		    }
		    
		    @Override
		    public void run() {
		        
		        _yDebut -= this._sens * incrementEnPx;
		        int nbrLignes = _lignesDeTexte.size();
		        boolean conditionChangement = 
		            this._hauteurFonte > 0 &&
		            /*cette premiere condition obligatoire :
		 * au 1er lancement _hauteurFonte non initialisee !
		 * (Sinon aurait immediatement change de sens : hauteurFonte valant alors 0) .
		 */
		            ( ( ((this._sens > 0) && _yDebut<= -1 * this._hauteurFonte * nbrLignes)
		            || ( (this._sens < 0) && _yDebut >= this._hauteurConteneur)) );
		        
		        if(conditionChangement){
		            this._sens *= -1;
		        }
		        repaint();
		    }
		    
		};
		
		this._actionPourTempo_monteeUnique = new TimerTask_Personnalise() {
		
		    {
		        this._hauteurConteneur = _conteneur.getHeight();
		        this._largeurConteneur = _conteneur.getWidth();
		        
		        _yDebut = this._hauteurConteneur - _espaceHaut;		        
		    }
		    
		    @Override
		    public void run() {
		        
		        _yDebut -= this._sens * incrementEnPx;
		        int nbrLignes = _lignesDeTexte.size();
		        boolean conditionChangement = _yDebut <= -1 * this._hauteurFonte * nbrLignes;
		        
		        if(conditionChangement) {
		            this._sens = 0;
		        }
		        repaint();
		    }
		    
		};
		
		switch(typeDefilement) {
		    case 1 :
		        this._actionPourTempo = this._actionPourTempo_monteeUnique;
		        break;
		    case 2 :
		        this._actionPourTempo = this._actionPourTempo_montUniq_period;
		        break;
		    case 3 :
		        this._actionPourTempo = this._actionPourTempo_vaEtVient_period;
		        break;
		    default :
		        this._actionPourTempo = this._actionPourTempo_monteeUnique;
		}
		
		this._tempo = new Timer();
		this._tempo.scheduleAtFixedRate(this._actionPourTempo, cadenceEnMs, cadenceEnMs);
	}
	
	 public void paint(Graphics g) {
		final int hauteurFonte = g.getFontMetrics().getHeight();
		
		//On ne peut initaliser la hauteur de la fonte pour les actions tempo  qu'ici
		this._actionPourTempo_montUniq_period.setHauteurFonte(hauteurFonte);
		this._actionPourTempo_vaEtVient_period.setHauteurFonte(hauteurFonte);
		this._actionPourTempo_monteeUnique.setHauteurFonte(hauteurFonte);
		
		//Mise a jour reguliere des dimensions du conteneur : c'est pourquoi cette methode appelee ici .
		this._actionPourTempo_montUniq_period.setDimensionsConteneur(this._conteneur.getWidth(), this._conteneur.getHeight());
		this._actionPourTempo_vaEtVient_period.setDimensionsConteneur(this._conteneur.getWidth(), this._conteneur.getHeight());
		this._actionPourTempo_monteeUnique.setDimensionsConteneur(this._conteneur.getWidth(), this._conteneur.getHeight());
		
		//le fond
		g.setColor(this._coulArrPlan);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//le texte
		g.setColor(this._coulAvantPlan);
		for(byte i = 0; i < this._lignesDeTexte.size(); i++){
		    g.drawString(this._lignesDeTexte.get(i), this._espaceGauche,
		            this._espaceHaut + this._yDebut + i * hauteurFonte);
		}
		
		//les bordures
		g.setColor(this._coulArrPlan);
		g.fillRect(0, 0, this.getWidth(), this._espaceHaut);
		g.fillRect(0, this.getHeight()-this._espaceHaut, this.getWidth(), this.getHeight());
		g.fillRect(0, 0, this._espaceGauche, this.getHeight());
		g.fillRect(this.getWidth()-this._espaceGauche, 0, this.getWidth(), this.getHeight());
	}
	 
	public void setVueLayout() {
		//ArrayList<String> credit = controleur.getCredit();
		//BufferedReader reader = controleur.getHandleCredit();
		
		
	/*	
		try {
		//	texteDefilant(0, 40, 1, 300, 250, Color.YELLOW, Color.BLACK, panCredit, reader);
			// texte defilant (0 ?, vitesse de feilement, defilement actif, marge gauche, endroit de depart (marge sombre sur telle hauteur)
			panCredit.setLayout(new BorderLayout());
			panCredit.setVisible(true);
			this.add(_conteneur);
		}
		catch (IOException e) {
			e.printStackTrace();
		}*/
	
	//	this.addMouseListener(this);
	}

	public void updateLayout() {		
		
	}

	public void actionPerformed(ActionEvent e) {		
		
	}

	public void mouseClicked(MouseEvent e) {
		((ControleurPrincipal)controleur).afficherMenuPrincipal();
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	/**
     * couleur texte
     */
    private Color _coulAvantPlan;
    
    /**
     * couleur fond
     */
    private Color _coulArrPlan;
    
    /**
     * Ordonee de la 1ere ligne de texte 
     */
    private int _yDebut;
    
    /**
     * Espace du bord gauche
     */
    private int _espaceGauche;
    
    /**
     * Espace du bord haut
     */
    private int _espaceHaut;
    
    /**
     * Objet conteneur du JPanel
     */
    private Component _conteneur;
    
    /**
     * La temporisation associee
     */
    private Timer _tempo;
    
    /**
     *  Action a effectuer regulierement par la tempo .
     */
    private TimerTask_Personnalise _actionPourTempo;
    
    /**
     * Action a effectuer regulierement par la tempo : mode montee unique periodique.
     */
    private TimerTask_Personnalise _actionPourTempo_montUniq_period;
    
    /**
     * Action a effectuer regulierement par la tempo : mode va-et-vient periodique .
     */
    private TimerTask_Personnalise _actionPourTempo_vaEtVient_period;
    
    /**
     * Action a effectuer regulierement par la tempo : mode montee (1 seule fois) .
     */
    private TimerTask_Personnalise _actionPourTempo_monteeUnique;
    
    
    /**
     * Les lignes de texte a afficher .
     */
    private ArrayList<String> _lignesDeTexte;

	@Override
	public void initControles() {
		BufferedReader reader = ((ControleurPrincipal)controleur).getHandleCredit();
		
		panCredit = new JPanel();
		panCredit.setSize(500, 400);
		
		_lignesDeTexte = new ArrayList<String>();
		
		try {
			texteDefilant(0, 25, 1, 350, 5, Color.white, Color.black, panCredit, reader);
			// texte defilant (0 ?, vitesse de feilement, defilement actif, marge gauche, endroit de depart (marge sombre sur telle hauteur)
			panCredit.setLayout(new BorderLayout());
			panCredit.setVisible(true);
			this.add(_conteneur);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
		this.addMouseListener(this);	
	}
}
