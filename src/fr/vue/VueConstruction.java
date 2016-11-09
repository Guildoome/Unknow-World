package fr.vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import fr.controller.ControleurVue;
import fr.controller.ControleurVueConstruction;

public class VueConstruction extends Vue {

	private static final long serialVersionUID = 4694715513725058296L;
	
	private JPanel panConstruction;
	private JTextArea txtConstructionDetail;
	private JComboBox<?> selConstruction;
	private JScrollPane panScrollConstruction;
	private JButton cmdOKConstruction;
	
	private JPanel panEvolution;
	private JTextArea txtEvolutionDetail;
	private JComboBox<?> selEvolution;
	private JScrollPane panScrollEvolution;
	private JButton cmdOKEvolution;
	
	private JPanel panConstructionHistorique;
	private JTextArea txtConstructionHistorique;
	private JScrollPane panScrollConstructionHistorique;

	public VueConstruction(ControleurVue controleur) 
	{
		super(controleur);
	}

	@Override
	public void initControles() {
		this.setLayout(new FlowLayout()); // responssive
		this.setPreferredSize(new Dimension (1000,700));
		setConstructionControles();
		setEvolutionControles();
		setHistoriqueControles();
	}
	
	public void setConstructionControles() {
		panConstruction = new JPanel();
		
		Border borderRessource = BorderFactory.createTitledBorder("Construction Batiment");		
		panConstruction.setBorder(borderRessource);	
		txtConstructionDetail = new JTextArea();		
		txtConstructionDetail.setColumns(70); // taille en largeur	
		txtConstructionDetail.setLineWrap(true); // affichage automatique du slide gauche droite lorsque l'on arrive en bas du text area et active le retour a la ligne ou non
		txtConstructionDetail.setRows(4); // taille en hauteur 		
		
		selConstruction = new JComboBox<Object>();
		selConstruction.addActionListener(new BatimentListActionListener());
		
		panConstruction.add(selConstruction);
		selConstruction.setEditable(false);	
		txtConstructionDetail.setEditable(false);
		panScrollConstruction = new JScrollPane(txtConstructionDetail); // creation d'un Jscrollpane qui contien le textarea (placement non logique mais pas le choix !!)	    
	    panConstruction.add(panScrollConstruction); // affichage du Jscrollpane
	    cmdOKConstruction = new JButton("Ok");
	    cmdOKConstruction.addActionListener(new BatimentButtonActionListener());
	    panConstruction.add(cmdOKConstruction);
	    this.add(panConstruction);
	}
	
	public void setEvolutionControles() {
		panEvolution = new JPanel();
		
		Border borderRessource = BorderFactory.createTitledBorder("Evolution Batiment");		
		panEvolution.setBorder(borderRessource);	
		txtEvolutionDetail = new JTextArea();		
		txtEvolutionDetail.setColumns(70); // taille en largeur	
		txtEvolutionDetail.setLineWrap(true); // affichage automatique du slide gauche droite lorsque l'on arrive en bas du text area et active le retour a la ligne ou non
		txtEvolutionDetail.setRows(4); // taille en hauteur 		
		selEvolution = new JComboBox<Object>();
		selEvolution.addActionListener(new BatimentListActionListener());
		
		panEvolution.add(selEvolution);
		selEvolution.setEditable(false);	
		txtEvolutionDetail.setEditable(false);
		panScrollEvolution = new JScrollPane(txtEvolutionDetail); // creation d'un Jscrollpane qui contien le textarea (placement non logique mais pas le choix !!)	    
		panEvolution.add(panScrollEvolution); // affichage du Jscrollpane
		cmdOKEvolution = new JButton("Ok");
		cmdOKEvolution.addActionListener(new BatimentButtonActionListener());
		panEvolution.add(cmdOKEvolution);
	    this.add(panEvolution);
	}
	
	public void setHistoriqueControles() {
		panConstructionHistorique = new JPanel();
		
		Border borderHistorique = BorderFactory.createTitledBorder("Historique");		
		panConstructionHistorique.setBorder(borderHistorique);	
		txtConstructionHistorique = new JTextArea();
		txtConstructionHistorique.setColumns(88); // taille en largeur	
		txtConstructionHistorique.setLineWrap(true); // affichage automatique du slide gauche droite lorsque l'on arrive en bas du text area et active le retour a la ligne ou non
		txtConstructionHistorique.setWrapStyleWord(true);
		txtConstructionHistorique.setRows(24); // taille en hauteur 
		txtConstructionHistorique.setEditable(false);
	    panScrollConstructionHistorique = new JScrollPane(txtConstructionHistorique); // creation d'un Jscrollpane qui contien le textarea (placement non logique mais pas le choix !!)	    
	    panConstructionHistorique.add(panScrollConstructionHistorique); // affichage du Jscrollpane	   
	    this.add(panConstructionHistorique);
	}

	@Override
	public void updateControles() {
		ControleurVueConstruction ctrl = (ControleurVueConstruction)controleur;
		ctrl.updateConstruction(selConstruction);
		ctrl.updateEvolution(selEvolution);
		ctrl.updateHistorique(txtConstructionHistorique);
	}
	
	class BatimentListActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == selConstruction) selConstruction_click();
			if(source == selEvolution) selEvolution_click();
		}
		
	}
	
	private void selConstruction_click() {
		ControleurVueConstruction ctrl = (ControleurVueConstruction)controleur;
		
		String item = (String)selConstruction.getSelectedItem();
		
		ctrl.updateConstructionText(item, txtConstructionDetail);
	}

	private void selEvolution_click() {
		ControleurVueConstruction ctrl = (ControleurVueConstruction)controleur;
		
		int index = selEvolution.getSelectedIndex();
		
		ctrl.updateEvolutionText(index, txtEvolutionDetail);
	}
	
	class BatimentButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == cmdOKConstruction) cmdOkConstruction_click();
			if(source == cmdOKEvolution) cmdOkEvolution_click();
		}
		
	}
	
	private void cmdOkConstruction_click() {
		ControleurVueConstruction ctrl = (ControleurVueConstruction)controleur;
		String item = (String)selConstruction.getSelectedItem();
		
		if(item != null) {
			((ControleurVueConstruction)controleur).constructionBatiment(item);
		}
		
		ctrl.updateHistorique(txtConstructionHistorique);
	}
	
	private void cmdOkEvolution_click() {
		ControleurVueConstruction ctrl = (ControleurVueConstruction)controleur;
		
		int index = selEvolution.getSelectedIndex();
		
		if(index >= 0) {
			ctrl.evolutionBatiment(index);
		}
		
		ctrl.updateHistorique(txtConstructionHistorique);
	}
}
