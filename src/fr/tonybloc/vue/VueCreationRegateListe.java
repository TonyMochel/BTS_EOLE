package fr.tonybloc.vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import fr.tonybloc.modele.ModelListRegate;

public class VueCreationRegateListe {

	JPanel panelListRegate;
	JTable listRegate;
	TableModel modelListRegate;
	TableColumnModel columnModelListRegate;
	
	
	public VueCreationRegateListe() {
		this.modelListRegate = new ModelListRegate();
		
		this.panelListRegate = new JPanel(new BorderLayout());
		
		this.listRegate = new JTable(this.modelListRegate);
		this.panelListRegate.add(new JScrollPane(this.listRegate),  BorderLayout.NORTH);

	}
	
	
	public JPanel getPanelListRegate() {
		return this.panelListRegate;
	}
}
