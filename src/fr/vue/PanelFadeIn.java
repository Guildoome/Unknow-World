package fr.vue;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelFadeIn extends JPanel {

	private static final long serialVersionUID = -196139312214391783L;
	
	private int fadeInDurationMs;
	private int currentFadeInPosMs;
	private int intervalMs;
	private float alpha;
	private float alphaCount;
	private Timer timer;
	
	public PanelFadeIn(int fadeInDurationMs, int intervalMs, float alpha) {
		super();		
		this.fadeInDurationMs = fadeInDurationMs;
		currentFadeInPosMs = 0;
		this.intervalMs = intervalMs;
		this.alpha = alpha;
		alphaCount = (1.0f - alpha) / (fadeInDurationMs / intervalMs);
		timer = new Timer(intervalMs, new FadeInActionTimerListener());
		timer.start();
		
	}

	class FadeInActionTimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == timer) redraw();
		}		
	}
	
	public void redraw() {
		if((currentFadeInPosMs += intervalMs) >= fadeInDurationMs) {
			timer.stop();
		}
		Graphics g = this.getGraphics();
		@SuppressWarnings("unused")
		Graphics2D g2d = (Graphics2D)g;		
		alpha += alphaCount;		
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = ((Graphics2D)g);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));		
		super.paint(g2d);
		
	}
}
