package fr.modele.image;

import java.awt.image.BufferedImage;

public class ImageContainer {
	
	private String nomFichier;
	
	private BufferedImage image;

	public ImageContainer(String nomFichier, BufferedImage image) {
		super();
		this.nomFichier = nomFichier;
		this.image = image;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public BufferedImage getImage() {
		return image;
	}

}
