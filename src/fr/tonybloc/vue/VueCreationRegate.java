package fr.tonybloc.vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;
/**
 * Vue de la page : création regate
 * @author Tony
 *
 */
public class VueCreationRegate {

	private VueCreationRegateFormulaire vueFormulaire;
	private VueCreationRegateListe vueListe;
	
	private JPanel content;
	
	public VueCreationRegate() {
		
		// Vue JTable
		this.vueListe 		= new VueCreationRegateListe();
		// Vue Formulaire
		this.vueFormulaire 	= new VueCreationRegateFormulaire();
			
		this.content = new JPanel(new BorderLayout());
		content.add(vueFormulaire.getPanelFormulaire(),BorderLayout.WEST);
		content.add(vueListe.getPanelListRegate(), BorderLayout.CENTER);
		
	}
	public VueCreationRegateFormulaire getVueFormulaire() {
		return this.vueFormulaire;
	}
	public VueCreationRegateListe getVueListe() {
		return this.vueListe;
	}
	public JPanel getContent() {
		return this.content;
	}
}
