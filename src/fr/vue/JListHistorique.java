package fr.vue;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import fr.modele.message.Message;

public class JListHistorique<E> extends JList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265606070574129236L;
	
	public JListHistorique() {
		super();
		this.setCellRenderer(new HistoriqueListCellRenderer());
	}
	
	
	class HistoriqueListCellRenderer extends DefaultListCellRenderer {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 3569778166134960186L;

		public HistoriqueListCellRenderer() {
			super();
		}
		
		@SuppressWarnings("rawtypes")
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			Component resultat = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			if(value instanceof Message) {
				switch(((Message) value).getTypeMessage()) {
					case MESSAGE_JEU: {
						setForeground(Color.BLACK);
						setText(((Message) value).getTexte());						
					}break;
					
					case MESSAGE_SYSTEME: {
						setForeground(Color.RED);
						setText(((Message) value).getTexte());
					}break;
					
					case MESSAGE_CONSTRUCTION: {
						setForeground(Color.BLUE);
						setText(((Message) value).getTexte());
					}break;
					
					case MESSAGE_DEFENSE: {
						setForeground(Color.ORANGE);
						setText(((Message) value).getTexte());
					}break;
					
					case MESSAGE_EXPLORATION: {
						setForeground(Color.MAGENTA);
						setText(((Message) value).getTexte());
					}break;
					
					default: {
						setForeground(Color.BLACK);
						setText(((Message) value).getTexte());
					}break;
				}
			}
			else {
				setText("Panic!!!");
			}
			
			return resultat;
		}
	}
}
