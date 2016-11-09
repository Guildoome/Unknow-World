package fr.vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

import fr.controller.ControleurVue;
import fr.controller.ControleurVueDefense;



public class VueDefense extends Vue 
{
	private static final long serialVersionUID = 2122368645514833426L;
	
	private JPanel panDefensePrincipal;
	private JPanel panDefenseDetail;
	
	private JPanel panDefense;	
	private JPanel panInfoUnite;
	private JTextArea txtInfoUnite;	
	private JPanel panSelectionUnite;
	private JLabel lblSelectionUnite;
	private JTextField txtNbUniteAEnvoyer;	
	private JPanel panOption;
	private JButton cmdOk;
	
	private JTextArea txtHistorique;
	private JScrollPane panScrollHistorique;
	
	private JLabel dispo;
	private JLabel def;
	
	public VueDefense(ControleurVue controleur)
	{
		super(controleur);
	}

	@Override
	public void initControles() {
		this.setLayout(new FlowLayout()); // responssive
		this.setPreferredSize(new Dimension (1000,700));
		
		//this.setBackground(Color.GRAY);
		//this.setOpaque(false); // rendre Jpanel invisible
		setDefensePrincipalControles();
		setDefenseDetailControles();
	}
	
	public void setDefensePrincipalControles() {
		panDefensePrincipal = new JPanel();
		
		Border borderDefense = BorderFactory.createTitledBorder("Defense");		
		panDefensePrincipal.setBorder(borderDefense);
	/*	txtInfoUnite = new JTextArea();
		txtInfoUnite.setPreferredSize(new Dimension(300, 40));
		txtInfoUnite.setRows(2);
		txtInfoUnite.setEditable(false);*/
		dispo = new JLabel();
		dispo.setIcon(new ImageIcon("data/img/unite.png"));
		dispo.setVerticalTextPosition(JLabel.BOTTOM);
		dispo.setHorizontalTextPosition(JLabel.CENTER);
		dispo.setToolTipText("unités disponible");
		
		def = new JLabel();
		def.setIcon(new ImageIcon("data/img/defense.png"));
		def.setVerticalTextPosition(JLabel.BOTTOM);
		def.setHorizontalTextPosition(JLabel.CENTER);
		def.setToolTipText("unités en défense");
		
		panInfoUnite = new JPanel();
		panInfoUnite.add(dispo);
		panInfoUnite.add(def);
		
		lblSelectionUnite = new JLabel("Choisir le nombre d'unité à envoyer en défense : ");
		
		txtNbUniteAEnvoyer = new JTextField("0");
		txtNbUniteAEnvoyer.setHorizontalAlignment(JTextField.RIGHT);
	
		//Controles OK et Retour
		cmdOk = new JButton("Ok");
		cmdOk.addActionListener(new DefenseButtonActionListener());
		
		panOption = new JPanel();
		panOption.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		panOption.add(cmdOk);
		
		
		panSelectionUnite = new JPanel();
		panSelectionUnite.setLayout(new BoxLayout(panSelectionUnite, BoxLayout.Y_AXIS));
		
		panSelectionUnite.add(lblSelectionUnite);
		panSelectionUnite.add(txtNbUniteAEnvoyer);
		panSelectionUnite.add(panOption);
		
		panDefense = new JPanel();
		panDefense.setLayout(new BoxLayout(panDefense, BoxLayout.X_AXIS));
		
		panDefense.add(panInfoUnite);
		panDefense.add(panSelectionUnite);
		String unite = "\tDisponible\tEn Defense\r\n";
		
		//	unite += "Unite\t" + controleur.getRessource().get(12) + "\t" + controleur.getRessource().get(16) + "\r\n";
			
		//txtInfoUnite.setText(unite);
				
		panDefensePrincipal.add(panDefense);
		this.add(panDefensePrincipal);
	}
	
	public void setDefenseDetailControles() {
		panDefenseDetail = new JPanel();
		Border borderHistorique = BorderFactory.createTitledBorder("Historique");		
		panDefenseDetail.setBorder(borderHistorique);
		
		txtHistorique = new JTextArea();
		txtHistorique.setColumns(85); // taille en largeur	
		txtHistorique.setLineWrap(true); // affichage automatique du slide gauche droite lorsque l'on arrive en bas du text area et active le retour a la ligne ou non
		txtHistorique.setRows(30); // taille en hauteur 
		txtHistorique.setEditable(false);
		DefaultCaret caret = (DefaultCaret)txtHistorique.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
	    panScrollHistorique = new JScrollPane(txtHistorique); // creation d'un Jscrollpane qui contien le textarea (placement non logique mais pas le choix !!)	    
	    panDefenseDetail.add(panScrollHistorique); // affichage du Jscrollpane
	    this.add(panDefenseDetail);
	}
	
	class DefenseButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == cmdOk) cmdOk_click();
		}
		
	}
	
	public void cmdOk_click() {
		ControleurVueDefense ctrl = (ControleurVueDefense)controleur;
		
		String nbUnite = txtNbUniteAEnvoyer.getText();
		
		ctrl.envoyerUniteEnDefense(nbUnite);
		ctrl.updateUniteInfo(dispo, def);

		ctrl.updateHistorique(txtHistorique);
	}

	@Override
	public void updateControles() {
		ControleurVueDefense ctrl = (ControleurVueDefense)controleur;
		
		ctrl.updateUniteInfo(dispo, def);
		ctrl.updateHistorique(txtHistorique);
	}
}

	