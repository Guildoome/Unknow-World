package fr.modele;

public class DonneeJoueurScore {

	private int ptTotal;
	private int ptUnite;
	private int ptTour;
	private int ptBatiment;
	private int ptPierre;
	private int ptEau;
	private int ptNourriture;
	private int ptBois;
	private int ptMinerai;
	
	public DonneeJoueurScore() {
		ptTotal = 0;
		ptUnite = 0;
		ptTour = 0;
		ptBatiment = 0;
		ptPierre = 0;
		ptEau = 0;
		ptNourriture = 0;
		ptBois = 0;
		ptMinerai = 0;
	}

	public int getPtTour() {
		return ptTour;
	}

	public void setPtTour(int ptTour) {
		this.ptTour = ptTour;
	}

	public int getPtTotal() {
		return ptTotal;
	}

	public void setPtTotal(int ptTotal) {
		this.ptTotal = ptTotal;
	}

	public int getPtUnite() {
		return ptUnite;
	}

	public void setPtUnite(int ptUnite) {
		this.ptUnite = ptUnite;
	}

	public int getPtBatiment() {
		return ptBatiment;
	}

	public void setPtBatiment(int ptBatiment) {
		this.ptBatiment = ptBatiment;
	}

	public int getPtPierre() {
		return ptPierre;
	}

	public void setPtPierre(int ptPierre) {
		this.ptPierre = ptPierre;
	}

	public int getPtEau() {
		return ptEau;
	}

	public void setPtEau(int ptEau) {
		this.ptEau = ptEau;
	}

	public int getPtNourriture() {
		return ptNourriture;
	}

	public void setPtNourriture(int ptNourriture) {
		this.ptNourriture = ptNourriture;
	}

	public int getPtBois() {
		return ptBois;
	}

	public void setPtBois(int ptBois) {
		this.ptBois = ptBois;
	}

	public int getPtMinerai() {
		return ptMinerai;
	}

	public void setPtMinerai(int ptMinerai) {
		this.ptMinerai = ptMinerai;
	}
	
	public void calculerTotal() {
		ptTotal = ptUnite + ptTour + ptBatiment +	ptPierre +
				ptEau + ptNourriture + ptBois +	ptMinerai;
	}

}
