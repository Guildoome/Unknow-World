package fr.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import fr.modele.batiment.Batiment;

/**
 * 
 * @author JGC
 *
 */
public class DonneeJoueurBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6378217780849820680L;
	
	private String nomJoueur;
	
	private ArrayList<Batiment> listeBatiment;
	
	private int defense;
	private int attaque;
	
	private int nourriture;
	private int nourritureMax;
	
	private int eau;
	private int eauMax;

	private int bois;
	private int boisMax;
	
	private int minerai;
	private int mineraiMax;
	
	private int pierre;
	private int pierreMax;
	
	private int nbUniteDisponible;
	private int nbUniteEnExploration;
	private int nbUniteEnConstruction;
	private int nbUniteEnDefense;
	private int nbUniteMax;
	
	private DonneeJoueurScore score;
	
	public DonneeJoueurBase() {
		listeBatiment = new ArrayList<>();
		score = new DonneeJoueurScore();
	}
	
	public DonneeJoueurBase(String nomJoueur, int nourriture, int eau, int bois, int minerai, int pierre, int nbUniteMax) {
		super();
		this.nomJoueur = nomJoueur;
		this.nourriture = nourriture;
		this.eau = eau;
		this.bois = bois;
		this.minerai = mineraiMax;
		this.pierre = pierre;
		this.nbUniteMax = nbUniteMax;
		this.nbUniteDisponible = nbUniteMax;
		this.nbUniteEnExploration = 0;
		this.nbUniteEnConstruction = 0;
		this.nbUniteEnDefense = 0;
		score = new DonneeJoueurScore();
		
		listeBatiment = new ArrayList<>();
	}
	
	public String getNomJoueur() {
		return nomJoueur;
	}
	
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}
	
	//Gestion des batiments
	public ArrayList<Batiment> getListeBatiment() {
		return listeBatiment;
	}
	
	/**
	 * Calculates the damages taken by the buildings
	 * 
	 * @param dommage
	 * 			The total of damages that the base receives
	 */
	public void prendreDommage(int dommage) {
		int nbBatiment = listeBatiment.size();
		
		boolean vaisseauVulnerable = (nbBatiment > 1) ? false : true;
		
		if(!vaisseauVulnerable) {
			nbBatiment--;
		}
		
		int dommageParBatiment = dommage / nbBatiment;

		Iterator<Batiment> it = listeBatiment.iterator();
		while(it.hasNext()) {
			Batiment batiment = it.next();
			if(batiment.getTypeBatiment().equals("vaisseau") && vaisseauVulnerable) {
				batiment.setDefense(batiment.getDefense() - dommageParBatiment);
				
				if(isBatimentDetruit(batiment) == true) {
					enleverBatiment(batiment);
				}
			}
			else {
				batiment.setDefense(batiment.getDefense() - dommageParBatiment);
				
				if(isBatimentDetruit(batiment) == true) {
					enleverBatiment(batiment);
				}
			}
		}
	}
	
	/**
	 * Checks whether a building should be destroyed
	 * 
	 * @param batiment
	 * 			The building to check
	 * 
	 * @return Whether the building was destroyed
	 */
	public boolean isBatimentDetruit(Batiment batiment) {
		boolean resultat = false;
		
		if(batiment != null) {
			if(batiment.getDefense() <= 0) {
				resultat = true;
			}
		}
		
		return resultat;
	}
	
	/**
	 * Checks whether a building is available
	 * 
	 * @param typeBatiment
	 * 			The name of the building
	 * 
	 * @return Whether the building is available
	 */
	public boolean hasBatiment(String typeBatiment) {
		boolean resultat = false;
		
		Iterator<Batiment> it = listeBatiment.iterator();		
		while(it.hasNext()) {
			Batiment batiment = it.next();
			if(batiment.getTypeBatiment().equals(typeBatiment)) {
				resultat = true;
				break;
			}
		}
		
		return resultat;
	}
	
	/**
	 * Checks whether a building is available and of the required level
	 * 
	 * @param typeBatiment
	 * 			The name of the building
	 * 
	 * @param niveau
	 * 			The level of the required building
	 * 
	 * @return Whether the building is available
	 */
	public boolean hasBatiment(String typeBatiment, int niveau) {
		boolean resultat = false;
		
		Iterator<Batiment> it = listeBatiment.iterator();		
		while(it.hasNext()) {
			Batiment batiment = it.next();
			if(batiment.getTypeBatiment().equals(typeBatiment) && (batiment.getNiveau() == niveau)) {
				resultat = true;
				break;
			}
		}
		
		return resultat;
	}
	
	/**
	 * Adds a building to the list
	 * 
	 * @param batiment
	 * 			The building to add
	 */
	public void ajouterBatiment(Batiment batiment) {
		if(batiment != null) {
			listeBatiment.add(batiment);
		}
	}
	
	/**
	 * Removes a building from the list
	 * 
	 * @param batiment
	 * 			The building to remove
	 */
	public void enleverBatiment(Batiment batiment) {
		if(batiment != null) {
			listeBatiment.remove(batiment);
		}
	}
	
	/**
	 * Adds food to the stock
	 * 
	 * @param nbRessource
	 * 			The amount of food to add
	 * 
	 * @return The amount of food added
	 */
	public int ajouterNourriture(int nbRessource) {
		int resultat = nbRessource;
		int nbRessourceApresAjout = nbRessource + nourriture;
		
		if(nbRessourceApresAjout > nourritureMax) {
			resultat -= nbRessourceApresAjout - nourritureMax;
		}
		
		nourriture += resultat;
		
		return resultat;
	}
	
	/**
	 * Removes food from the stock
	 * 
	 * @param nbRessource
	 * 			The amount of food to remove
	 */
	public void consommerNourriture(int nbRessource) {
		nourriture -= nbRessource;
	}
	
	/**
	 * Adds water to the stock
	 * 
	 * @param nbRessource
	 * 			The amount of water to add
	 * 
	 * @return The amount of water added
	 */
	public int ajouterEau(int nbRessource) {
		int resultat = nbRessource;
		int nbRessourceApresAjout = nbRessource + eau;
		
		if(nbRessourceApresAjout > eauMax) {
			resultat -= nbRessourceApresAjout - eauMax;
		}
		
		eau += resultat;
		
		return resultat;
	}
	
	/**
	 * Removes water from the stock
	 * 
	 * @param nbRessource
	 * 			The amount of water to remove
	 */
	public void consommerEau(int nbRessource) {
		eau -= nbRessource;
	}
	
	/**
	 * Adds wood to the stock
	 * 
	 * @param nbRessource
	 * 			The amount of wood to add
	 * 
	 * @return The amount of wood added
	 */
	public int ajouterBois(int nbRessource) {
		int resultat = nbRessource;
		int nbRessourceApresAjout = nbRessource + bois;
		
		if(nbRessourceApresAjout > boisMax) {
			resultat -= nbRessourceApresAjout - boisMax;
		}
		
		bois += resultat;
		
		return resultat;
	}
	
	/**
	 * Removes wood from the stock
	 * 
	 * @param nbRessource
	 * 			The amount of wood to remove
	 */
	public void consommerBois(int nbRessource) {
		bois -= nbRessource;
	}
	
	/**
	 * Adds ore to the stock
	 * 
	 * @param nbRessource
	 * 			The amount of ore to add
	 * 
	 * @return The amount of ore added
	 */
	public int ajouterMinerai(int nbRessource) {
		int resultat = nbRessource;
		int nbRessourceApresAjout = nbRessource + minerai;
		
		if(nbRessourceApresAjout > mineraiMax) {
			resultat -= nbRessourceApresAjout - mineraiMax;
		}
		
		minerai += resultat;
		
		return resultat;
	}
	
	/**
	 * Removes ore from the stock
	 * 
	 * @param nbRessource
	 * 			The amount of ore to remove
	 */
	public void consommerMinerai(int nbRessource) {
		minerai -= nbRessource;
	}
	
	/**
	 * Adds stone to the stock
	 * 
	 * @param nbRessource
	 * 			The amount of stone to add
	 * 
	 * @return The amount of stone added
	 */
	public int ajouterPierre(int nbRessource) {
		int resultat = nbRessource;
		int nbRessourceApresAjout = nbRessource + pierre;
		
		if(nbRessourceApresAjout > pierreMax) {
			resultat -= nbRessourceApresAjout - pierreMax;
		}
		
		pierre += resultat;
		
		return resultat;
	}
	
	/**
	 * Removes stone from the stock
	 * 
	 * @param nbRessource
	 * 			The amount of stone to remove
	 */
	public void consommerPierre(int nbRessource) {
		pierre -= nbRessource;
	}

	public int getDefense() {
		return defense;
	}

	public int getAttaque() {
		return attaque;
	}

	public int getNourriture() {
		return nourriture;
	}

	public int getNourritureMax() {
		return nourritureMax;
	}

	public int getEau() {
		return eau;
	}

	public int getEauMax() {
		return eauMax;
	}

	public int getBois() {
		return bois;
	}

	public int getBoisMax() {
		return boisMax;
	}

	public int getMinerai() {
		return minerai;
	}

	public int getMineraiMax() {
		return mineraiMax;
	}

	public int getPierre() {
		return pierre;
	}

	public int getPierreMax() {
		return pierreMax;
	}

	public int getNbUniteDisponible() {
		return nbUniteDisponible;
	}

	public int getNbUniteEnExploration() {
		return nbUniteEnExploration;
	}

	public int getNbUniteEnConstruction() {
		return nbUniteEnConstruction;
	}

	public int getNbUniteEnDefense() {
		return nbUniteEnDefense;
	}

	public int getNbUniteMax() {
		return nbUniteMax;
	}

	/**
	 * Removes units form the game
	 * 
	 * @param nbUnite
	 * 			The number of units to remove
	 */
	public void detruireUnite(int nbUnite) {
		if(nbUnite <= nbUniteMax) {
			nbUniteMax -= nbUnite;
		}
	}
	
	/**
	 * Removes units form the game
	 * 
	 * @param nbUnite
	 * 			The number of units to remove
	 */
	public void detruireUniteEnExploration(int nbUnite) {
		if(nbUnite <= nbUniteMax) {
			nbUniteMax -= nbUnite;
			nbUniteEnExploration -= nbUnite;
		}
	}
	
	/**
	 * Checks whether the player has enough units available to do something
	 * 
	 * @param nbUnite
	 * 			The number of units requested
	 */
	private boolean peutUtiliserLesUnite(int nbUnite) {
		boolean resultat = true;
		
		if((nbUnite > nbUniteMax) && (nbUnite > nbUniteDisponible)) {
			resultat = false; 
		}
		
		return resultat;
	}
	
	/**
	 * Sends units in exploration
	 * 
	 * @param nbUnite
	 * 			The number of units to send
	 * 
	 * @return Whether the units were sent in exploration
	 */
	public boolean envoyerUniteEnExploration(int nbUnite) {
		boolean instructionValide = peutUtiliserLesUnite(nbUnite);
		
		if(instructionValide) {
			nbUniteDisponible -= nbUnite;
			nbUniteEnExploration += nbUnite;
		}
		
		return instructionValide;
	}
	
	/**
	 * Sends units in construction
	 * 
	 * @param nbUnite
	 * 			The number of units to send
	 * 
	 * @return Whether the units were sent in construction
	 */
	public boolean envoyerUniteEnConstruction(int nbUnite) {
		boolean instructionValide = peutUtiliserLesUnite(nbUnite);
		
		if(instructionValide) {
			nbUniteDisponible -= nbUnite;
			nbUniteEnConstruction += nbUnite;
		}
		
		return instructionValide;
	}
	
	/**
	 * Sends units in defense
	 * 
	 * @param nbUnite
	 * 			The number of units to send
	 * 
	 * @return Whether the units were sent in defense
	 */
	public boolean envoyerUniteEnDefense(int nbUnite) {
		boolean instructionValide = peutUtiliserLesUnite(nbUnite);
		
		if(instructionValide) {
			nbUniteDisponible -= nbUnite;
			nbUniteEnDefense += nbUnite;
		}
		
		return instructionValide;
	}
	
	/**
	 * Removes units from exploration
	 * 
	 * @param nbUnite
	 * 		The number of units to remove
	 */
	public void restaurerUniteEnExploration(int nbUnite) {
				
		if(nbUnite <= nbUniteEnExploration) {
			nbUniteDisponible += nbUnite;
			nbUniteEnExploration -= nbUnite;
		}
		
	}
	
	/**
	 * Removes units from defense
	 * 
	 * @param nbUnite
	 * 		The number of units to remove
	 */
	public void restaurerUniteEnDefense(int nbUnite) {
		
		if(nbUnite <= nbUniteEnDefense) {
			nbUniteDisponible += nbUnite;
			nbUniteEnDefense -= nbUnite;
		}
		
	}
	
	/**
	 * Removes units from construction
	 * 
	 * @param nbUnite
	 * 		The number of units to remove
	 */
	public void restaurerUniteEnConstruction(int nbUnite) {
		
		if(nbUnite <= nbUniteEnConstruction) {
			nbUniteDisponible += nbUnite;
			nbUniteEnConstruction -= nbUnite;
		}
		
	}
	
	/**
	 * Updates the player score
	 */
	private void updateScore() {
		int ptRessource = 2;
		int ptRessourceRare = 3;
		
		score.setPtUnite(nbUniteMax * ptRessource);
		score.setPtBois(bois * ptRessource);
		score.setPtEau(eau * ptRessource);
		score.setPtMinerai(minerai * ptRessourceRare);
		score.setPtPierre(pierre * ptRessourceRare);
		score.setPtNourriture(nourriture * ptRessource);		
	}
	
	/**
	 * Adds point when a building is constructed
	 * 
	 * @param point
	 * 			The number of points to add
	 */
	public void ajouterPointBatiment(int point) {
		score.setPtBatiment(score.getPtBatiment() + point);
	}
	
	/**
	 * Unused
	 * 
	 * @param point unused
	 */
	public void retirerPointBatiment(int point) {
		score.setPtBatiment(score.getPtBatiment() - point);
	}
	
	public DonneeJoueurScore getScore() {
		return score;
	}
	
	/**
	 * Calculates the turn food consumption
	 * 
	 * @return The amount of food to eat
	 */
	public int calculerConsommationNourriture() {
		return (int)(Math.round((float)nbUniteDisponible * 0.15f)  + ((float)nbUniteEnExploration * 0.2f) + ((float)nbUniteEnConstruction * 0.3f) + ((float)nbUniteEnDefense * 0.4f));
	}
	
	/**
	 * Calculates the turn water consumption
	 * 
	 * @return The amount of water to drink
	 */
	public int calculerConsommationEau() {
		return (int)(Math.round((float)nbUniteDisponible * 0.15f)  + ((float)nbUniteEnExploration * 0.2f) + ((float)nbUniteEnConstruction * 0.3f) + ((float)nbUniteEnDefense * 0.4f));
	}
	
	/**
	 * Updates the player's data
	 */
	public void miseAJourRessource() {
		int defenseTemp = 0;
		int attaqueTemp = 0;		
		int nourritureTemp = 0;
		int eauTemp = 0;
		int boisTemp = 0;
		int mineraiTemp = 0;
		int pierreTemp = 0;
		
		Iterator<Batiment> it = listeBatiment.iterator();
		while(it.hasNext()) {
			Batiment batiment = it.next();
			
			defenseTemp += batiment.getDefense();
			attaqueTemp += batiment.getAttaque();			
			nourritureTemp += batiment.getStockageNourriture();
			eauTemp += batiment.getStockageEau();
			boisTemp += batiment.getStockageBois();
			mineraiTemp += batiment.getStockageMinerai();
			pierreTemp += batiment.getStockagePierre();
		}
		
		defense = defenseTemp;
		attaque = attaqueTemp;
		nourritureMax = nourritureTemp;
		eauMax = eauTemp;
		boisMax = boisTemp;
		mineraiMax = mineraiTemp;
		pierreMax = pierreTemp;
		
		updateScore();
	}
	
	/**
	 * Checks whether the player is still alive
	 * 
	 * @return The state of the player's life
	 */
	public boolean joueurEstEnVie() {
		boolean resultat = ((defense > 0) && (nbUniteMax > 0) && (nourriture > 0) && (eau > 0)) ? true : false;
		
		return resultat;
	}

}
