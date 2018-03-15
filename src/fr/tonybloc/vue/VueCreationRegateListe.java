package fr.tonybloc.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import fr.tonybloc.modele.composant.ModelListRegate;

/**
 * Vue Tableau : Liste de régate
 * @author Tony
 *
 */
public class VueCreationRegateListe {

	private JPanel panelListRegate;
	private JTable listRegate;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private ModelListRegate modelListRegate;
	//private TableColumnModel columnModelListRegate;
	
	private final Dimension dim = new Dimension(150, 30);
	private final Font police = new Font("Arial", Font.PLAIN, 14);
	
	
	public VueCreationRegateListe() {
		
		this.panelListRegate = new JPanel(new BorderLayout());
		this.panelListRegate.setBorder(new EmptyBorder(20, 10, 20, 10));
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
