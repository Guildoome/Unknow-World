package fr.modele.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class ImageManager {

	private String cheminDonneeImage;
	
	private ArrayList<ImageContainer> listeImage;
	
	public ImageManager(String cheminDonnee) {
		cheminDonneeImage = cheminDonnee + "/img/";
		listeImage = new ArrayList<>();
	}
	
	public ImageContainer getImage(String nomImage) {
		ImageContainer resultat = null;
		boolean imgTrouve = false;
		
		Iterator<ImageContainer> it = listeImage.iterator();
		while(it.hasNext()) {
			ImageContainer imgContainer = it.next();
			
			if(imgContainer.getNomFichier().equals(nomImage)) {
				resultat = imgContainer;
				imgTrouve = true;
				break;
			}
		}
		
		if(imgTrouve == false) {
			ImageContainer imgContainer = loadImage(nomImage);
			
			if(imgContainer != null) {
				listeImage.add(imgContainer);
				resultat = imgContainer;
			}
		}
		
		return resultat;
	}
	
	private ImageContainer loadImage(String nomImage) {
		ImageContainer resultat = null;
				
		File fichierImage = new File(cheminDonneeImage + nomImage);
		if(fichierImage.exists()) {
			BufferedImage image;
			try {
				image = ImageIO.read(fichierImage);
				resultat = new ImageContainer(nomImage, image);
			} catch (IOException e) {
				e.printStackTrace();
			}				
		}
		
		return resultat;		
	}
	
	public void clear() {
		listeImage.clear();
	}

}
