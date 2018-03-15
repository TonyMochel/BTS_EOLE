package fr.tonybloc.vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Vue page simulation de la régate
 * @author Tony
 *
 */
public class VueSimulation {

	private VueSimulationTableau vueTableau;
	private VueSimulationCommande vueCommande;
	
	private JPanel content;
	
	/**
	 * Crée une instance de la classe vueSimulation
	 */
	public VueSimulation() {
		this.vueCommande = new VueSimulationCommande();
		this.vueTableau = new VueSimulationTableau();
		
		this.content = new JPanel(new BorderLayout());

		this.content.add(vueCommande.getContent(), BorderLayout.NORTH);
		this.content.add(vueTableau.getContent(), BorderLayout.CENTER);
	}

	
	public VueSimulationCommande getVueCommande() {
		return vueCommande;
	}

	public VueSimulationTableau getVueTableau() {
		return vueTableau;
	}

	public JPanel getContent() {
		return content;
	}
	
	
}
