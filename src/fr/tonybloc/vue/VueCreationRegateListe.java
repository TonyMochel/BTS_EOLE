package fr.tonybloc.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import fr.tonybloc.modele.ModelListRegate;

public class VueCreationRegateListe {

	JPanel panelListRegate;
	JTable listRegate;
	JButton btnModifier;
	JButton btnSupprimer;
	ModelListRegate modelListRegate;
	TableColumnModel columnModelListRegate;
	
	Dimension dim;
	Font police;
	
	public VueCreationRegateListe() {
		
		this.dim 		= new Dimension(150, 30);
		this.police 	= new Font("Arial", Font.PLAIN, 14);
		
		this.panelListRegate = new JPanel(new BorderLayout());
		JPanel boutons = new JPanel();
		
		this.modelListRegate = new ModelListRegate();
		this.listRegate = new JTable(this.modelListRegate);
		this.listRegate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.btnModifier = new JButton("Modifier");
		this.btnSupprimer = new JButton("Supprimer");
		
		this.btnModifier.setFont(police);
		this.btnSupprimer.setFont(police);
		
		boutons.add(btnModifier);
		boutons.add(btnSupprimer);
		
		this.panelListRegate.add(new JScrollPane(this.listRegate),  BorderLayout.CENTER);
		this.panelListRegate.add(boutons, BorderLayout.SOUTH);
	}
	
	public ModelListRegate getModel() {
		return this.modelListRegate;
	}
	public JPanel getPanelListRegate() {
		return this.panelListRegate;
	}
	public JButton getBtnSupprimer() {
		return this.btnSupprimer;
	}
	public JButton getBtnModifier() {
		return this.btnModifier;
	}
	public JTable getListRegate() {
		return this.listRegate;
	}
}
