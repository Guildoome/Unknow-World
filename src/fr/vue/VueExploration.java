package fr.vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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
import fr.controller.ControleurVueExploration;

public class VueExploration extends Vue {

	private static final long serialVersionUID = -4154284972629941527L;
	
	private JPanel panExplorationPrincipal;
	private JPanel panExplorationDetail;
	
	private JPanel panExploration;	
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
	private JLabel explo;

	public VueExploration(ControleurVue controleur) {
		super(controleur);
		
	}

	@Override
	public void initControles() {
		this.setLayout(new FlowLayout()); // responssive
		this.setPreferredSize(new Dimension (1000,700));
		
		//this.setBackground(Color.GRAY);
		//this.setOpaque(false); // rendre Jpanel invisible
		setExplorationPrincipalControles();
		setExplorationDetailControles();
	}
	
	public void setExplorationPrincipalControles() {
		panExplorationPrincipal = new JPanel();
		
		Border borderDefense = BorderFactory.createTitledBorder("Exploration");		
		panExplorationPrincipal.setBorder(borderDefense);
		//txtInfoUnite = new JTextArea();
		//txtInfoUnite.setPreferredSize(new Dimension(300, 40));
		//txtInfoUnite.setRows(2);
		//txtInfoUnite.setEditable(false);
		dispo = new JLabel();
		dispo.setIcon(new ImageIcon("data/img/unite.png"));
		dispo.setVerticalTextPosition(JLabel.BOTTOM);
		dispo.setHorizontalTextPosition(JLabel.CENTER);
		dispo.setToolTipText("unités disponible");
		
		explo = new JLabel();
		explo.setIcon(new ImageIcon("data/img/explo.jpg"));
		explo.setVerticalTextPosition(JLabel.BOTTOM);
		explo.setHorizontalTextPosition(JLabel.CENTER);
		explo.setToolTipText("unités en exploration");
		
		panInfoUnite = new JPanel();
		
		panInfoUnite.add(dispo);
		panInfoUnite.add(explo);
		
		lblSelectionUnite = new JLabel("Choisir le nombre d'unité à envoyer en exploration : ");
		
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
		
		panExploration = new JPanel();
		panExploration.setLayout(new BoxLayout(panExploration, BoxLayout.X_AXIS));
		
		panExploration.add(panInfoUnite);
		panExploration.add(panSelectionUnite);
		//String unite = "\tDisponible\tEn Exploration\r\n";
		
		//	unite += "Unite\t" + controleur.getRessource().get(12) + "\t" + controleur.getRessource().get(16) + "\r\n";
			
		//txtInfoUnite.setText(unite);
				
		panExplorationPrincipal.add(panExploration);
		this.add(panExplorationPrincipal);
	}
	
	public void setExplorationDetailControles() {
		panExplorationDetail = new JPanel();
		Border borderHistorique = BorderFactory.createTitledBorder("Historique");		
		panExplorationDetail.setBorder(borderHistorique);
		
		txtHistorique = new JTextArea();
		txtHistorique.setColumns(85); // taille en largeur	
		txtHistorique.setWrapStyleWord(true);
		txtHistorique.setLineWrap(true); // affichage automatique du slide gauche droite lorsque l'on arrive en bas du text area et active le retour a la ligne ou non
		txtHistorique.setRows(30); // taille en hauteur 
		txtHistorique.setEditable(false);
		DefaultCaret caret = (DefaultCaret)txtHistorique.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
	    panScrollHistorique = new JScrollPane(txtHistorique); // creation d'un Jscrollpane qui contien le textarea (placement non logique mais pas le choix !!)	 
	    

	    panExplorationDetail.add(panScrollHistorique); // affichage du Jscrollpane
	    this.add(panExplorationDetail);
	}
	
	class DefenseButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == cmdOk) cmdOk_click();
		}
		
	}
	
	public void cmdOk_click() {
		ControleurVueExploration ctrl = (ControleurVueExploration)controleur;
		
		String nbUnite = txtNbUniteAEnvoyer.getText();
		
		ctrl.envoyerUniteEnExploration(nbUnite);
		ctrl.updateUniteInfo(dispo, explo);
		ctrl.updateHistorique(txtHistorique);
	}

	@Override
	public void updateControles() {
		ControleurVueExploration ctrl = (ControleurVueExploration)controleur;
		
		ctrl.updateUniteInfo(dispo, explo);
		ctrl.updateHistorique(txtHistorique);
	}
}

