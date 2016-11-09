package fr.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
//import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
//import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

import fr.controller.ControleurPrincipal;
import fr.controller.ControleurVue;
import fr.controller.ControleurVueConstruction;
import fr.controller.ControleurVueDefense;
import fr.controller.ControleurVueExploration;
import fr.controller.ControleurVueGenerale;
import fr.controller.ControleurVueScore;
import fr.controller.IControleur;

public class EcranJoueur extends Ecran
{
	private static final long serialVersionUID = 1L;
	//private JTabbedPane tabPrincipal = new JTabbedPane();
	
	
	private		JTabbedPane tabbedPane;
	private		Vue		vueGenerale;
	private		Vue		vueConstruction;
	private		Vue		vueExploration;
	private		Vue		vueDefense;
	private		Vue		vueDetailScore;
		
	public EcranJoueur(IControleur controleur)
	{			
		super(controleur);
	}


	public void createPage1()
	{	//vueGenerale = new JPanel();			
		//this.add(new VueGenerale(), BorderLayout.CENTER);
		//this.add(new VueGenerale());
		//JLabel label1 = new JLabel( "Ressource" );				
		//	vueGenerale.add( label1 );
		ControleurVue ctrlVueGeneral = new ControleurVueGenerale(controleur, null);
		vueGenerale = new VueGenerale(ctrlVueGeneral);	
		ctrlVueGeneral.setVue(vueGenerale);		
	}
	
	public void createPage2()
	{
		ControleurVue ctrlVueConstruction = new ControleurVueConstruction(controleur, null);
		vueConstruction = new VueConstruction(ctrlVueConstruction);
		ctrlVueConstruction.setVue(vueConstruction);		
	}

	public void createPage3()
	{	
		ControleurVue ctrlVueExploration = new ControleurVueExploration(controleur, null);
		vueExploration = new VueExploration(ctrlVueExploration);
		ctrlVueExploration.setVue(vueExploration);		
	}
	public void createPage4()
	{	
		ControleurVue ctrlVueDefense = new ControleurVueDefense(controleur, null);
		vueDefense = new VueDefense(ctrlVueDefense);
		ctrlVueDefense.setVue(vueGenerale);	
	}	
	public void createPage5()
	{	
		ControleurVue ctrlVueScore = new ControleurVueScore(controleur, null);
		vueDetailScore = new VueDetailScore(ctrlVueScore);
		ctrlVueScore.setVue(vueDetailScore);		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		JPanel panVueJoueur = new JPanel();	
		
		panVueJoueur.setLayout( new BorderLayout() );
		this.add( panVueJoueur );

		// Create the tab pages		
		createPage1();
		createPage2();
		createPage3();
		createPage4();
		createPage5();

		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(1024, 760) );
		tabbedPane.addTab( "Vue Generale", vueGenerale );
		tabbedPane.addTab( "Batiments", vueConstruction );
		tabbedPane.addTab( "Exploration", vueExploration ); // ok
		tabbedPane.addTab( "Defense", vueDefense ); // ok
		tabbedPane.addTab( "Score", vueDetailScore );
		panVueJoueur.add( tabbedPane, BorderLayout.CENTER );		
		panVueJoueur.add( tabbedPane);	
		
		tabbedPane.addChangeListener(new TabPaneChangeListener());
		
		((ControleurPrincipal)controleur).setMenuSauvegardeActive(true);
		((ControleurPrincipal)controleur).setPartieActive(true);
	}
	
	class TabPaneChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			Object source = e.getSource();
			if(source == tabbedPane) tabPaneChanged();
		}		
	}
	
	private void tabPaneChanged() {
		Vue vue = (Vue)tabbedPane.getSelectedComponent();
		
		if(vue != null) {
			vue.updateControles();
		}
	}
	
}
