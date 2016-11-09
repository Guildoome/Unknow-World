package fr.modele;

import java.io.File;

import javax.sound.sampled.*;

/**
 * 
 * @author JGC
 *
 */
public class Sons 
{
	public static boolean isPlaying = true;
	public static boolean isPaused = false;
	public static int framePos = 0;
	static Clip clip = null;
	
	/**
	 * Plays a sound
	 */
	public static void playSound()
	{
	    try {
	     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("data/sound/0733.wav"));
	     clip = AudioSystem.getClip();
	     clip.open(audioInputStream);
	     clip.start();
	     clip.loop(Clip.LOOP_CONTINUOUSLY);
	     isPlaying = true;
	    }
	    catch(Exception ex)
	    {
	      System.err.println("Error with playing sound.");
	      ex.printStackTrace( );
	    }
	}
	
	/**
	 * Stop the playing sound
	 */
	public static void stopSound()
	{
		clip.stop();
		isPlaying = false;
	}
	
	/**
	 * Pauses the playing sound
	 */
	public static void pauseSound() {
		if(!isPaused) {
			framePos = clip.getFramePosition();
			clip.stop();
			isPaused = true;
		}
	}
	
	/**
	 * Resumes the playing sound
	 */
	public static void resumeSound() {
		if(isPaused) {
			clip.setFramePosition(framePos);
			clip.start();
			isPaused = false;
		}
	}
}
	