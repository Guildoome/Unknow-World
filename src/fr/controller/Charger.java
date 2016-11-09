package fr.controller;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class Charger {

	static class MonFiltre extends FileFilter {

		String [] lesSuffixes;
		String  laDescription;
		
		public MonFiltre(String []lesSuffixes, String laDescription){
			this.lesSuffixes = lesSuffixes;
			this.laDescription = laDescription;
		}
		
		boolean appartient( String suffixe ){
			for( int i = 0; i<lesSuffixes.length; ++i)
		    if(suffixe.equals(lesSuffixes[i]))
		    return true;
		    return false;
		}
		
		public boolean accept(File f) {
		  if (f.isDirectory())  return true;
		      String suffixe = null;
		      String s = f.getName();
		      int i = s.lastIndexOf('.');
		      if(i > 0 &&  i < s.length() - 1)
		         suffixe=s.substring(i+1).toLowerCase();
		      return suffixe!=null&&appartient(suffixe);
		}
		 
	   public String getDescription() {
	      return laDescription;
	   }
		   
	   static MonFiltre mft = new MonFiltre(new String[]{"sav"},"fichiers sauvegarde (*.sav)");
	}
	
	public static String charge(String chemin, Component comp){
		
		String resultat = null;
		
		JFileChooser chooser = new JFileChooser(chemin);//création dun nouveau filechooser
		chooser.setApproveButtonText("Choix du fichier..."); //intitulé du bouton
		chooser.addChoosableFileFilter(MonFiltre.mft);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = chooser.showOpenDialog(comp);
		if(result == JFileChooser.APPROVE_OPTION) {
			resultat = chooser.getSelectedFile().getName();
		}
		 
		return resultat;
	}
	
	public static String save(String chemin, Component comp,String pseudo){
		String resultat = null;
		  	
		JFileChooser chooser = new JFileChooser(chemin);//création dun nouveau filechosser
		
		chooser.setApproveButtonText("Choix du fichier..."); //intitulé du bouton
		chooser.addChoosableFileFilter(MonFiltre.mft);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setSelectedFile( new File(pseudo+".sav") );
		
		int result = chooser.showSaveDialog(comp);
		
		if(result == JFileChooser.APPROVE_OPTION) {
			
			String fileName = chooser.getSelectedFile().getName();
			
			if(!fileName.endsWith(".sav")) {
				resultat = fileName+".sav";
			}
			else {
				resultat = fileName;
			}
			
		if (chooser.getSelectedFile().exists()) {
			int n = JOptionPane.showConfirmDialog(
                    comp,
                    "Souhaitez vous écraser le fichier ?",
                    "Unknow World",
                    JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION)
                chooser.approveSelection();
		}
        else
            chooser.approveSelection();
		}
		
		return resultat;
	}

}
