package fr.tonybloc.vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class VueCreationRegate {

	VueCreationRegateFormulaire vueFormulaire;
	VueCreationRegateListe vueList;
	
	JPanel content;
	
	public VueCreationRegate() {
		
		// Vue JTable
		this.vueList 		= new VueCreationRegateListe();
		// Vue Formulaire
		this.vueFormulaire 	= new VueCreationRegateFormulaire();
			
		this.content = new JPanel(new BorderLayout());
		content.add(vueFormulaire.getPanelFormulaire(),BorderLayout.WEST);
		content.add(vueList.getPanelListRegate(), BorderLayout.CENTER);
		
	}
	public VueCreationRegateFormulaire getVueFormulaire() {
		return this.vueFormulaire;
	}
	public VueCreationRegateListe getVueList() {
		return this.vueList;
	}
	public JPanel getContent() {
		return this.content;
	}
}
