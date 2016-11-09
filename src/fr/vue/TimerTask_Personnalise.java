package fr.vue;
import java.util.TimerTask;

/**
 * TimerTask personnalise, doit etre associe a un JPanelTexteDefilant
 * @author laurent
 *
 */
public abstract class TimerTask_Personnalise extends TimerTask {

    /**
     * Fixe la hauteur de fonte .
     * Cree pour des raisons pratiques .
     * @param hauteur - int - hauteur de la fonte
     */
    public void setHauteurFonte(int hauteur){
        this._hauteurFonte = hauteur;
    }
    
    /**
     * Fixe les dimensions du conteneur de JPanelTexteDefilant
     * auquel ce TimerTask est associe
     * @param largeur - int - largeur du conteneur
     * @param hauteur - int - hauteur du conteneur
     */
    public void setDimensionsConteneur(int largeur, int hauteur){
        this._largeurConteneur = largeur;
        this._hauteurConteneur = hauteur;
    }
    
    /**
     * Suspend le defilement ou le reprend
     *
     */
    public void suspendreOuReprendre(){
        if(this._sens != 0){
            this._sauvegardeSens = this._sens;
            this._sens = 0;
        }
        else {
            this._sens = this._sauvegardeSens;
        }
    }
    
    /**
     * hauteur de fonte du JPannel .
     */
    protected int _hauteurFonte;
    
    /**
     * hauteur du conteneur
     */
    protected int _hauteurConteneur;
    
    /**
     *  largeur du conteneur
     */
    protected int _largeurConteneur;
    
    /*
     * Sens de defilement : 1 ou -1 .
     * Si > 0 => defilement vers le haut .
     * Si < 0 => defilement vers le bas .
     * Si == 0 => arret defilement (temporaire : jusqu'Ã  valeur != 0)
     */
    protected int _sens = 1;
    
    /*
     * Sauvegarde de la valeur de _sens lors d'un arret temporaire
     * demande par l'utilisateur .
     */
    private int _sauvegardeSens = 0;

}

