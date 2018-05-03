package fr.tonybloc.vue;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Vue de la page d'information
 * @author Tony
 *
 */
public class VueInformation {
	
	private JPanel content;
	
	/**
	 * Constructeur de la vue
	 */
	public VueInformation() {
		
		JLabel lbInformation = new JLabel(""
				+ "Cette application a été développé par Mochel Anthony ");
		this.content = new JPanel();
		this.content.add(lbInformation, BorderLayout.CENTER);
	}
	
	public JPanel getContent() {
		return content;
	}
}
