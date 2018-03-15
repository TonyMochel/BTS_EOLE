package fr.tonybloc.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.composant.ModelListParticipantSimulation;

/**
 * Vue Table : Liste des participant avec temps d'arrivée
 * @author Tony
 *
 */
public class VueSimulationTableau {

	private JPanel content;
	private JComboBox<Regate> cbChoixRegate; 
	private JTable listParticipant;
	private ModelListParticipantSimulation ModelListParticipant;
	
	private JButton btnArriver;
	private JButton btnAbandonner;
	private JButton btnModifier;
	
	private final Dimension dim = new Dimension(150, 30);
	private final Font police = new Font("Arial", Font.PLAIN, 14);
	
	/**
	 * Crée une instance de la class VueSimulationTableau
	 */
	public VueSimulationTableau() {
		this.content = new JPanel(new BorderLayout());
		this.content.setBorder(new EmptyBorder(20, 10, 20, 10));
		
		this.btnArriver = new JButton("Arriver");
		this.btnAbandonner = new JButton("Abandon");
		this.btnModifier = new JButton("Modifier");
		this.cbChoixRegate = new JComboBox<Regate>();
		
		JLabel lbChoixRegate = new JLabel("Choisir une régate : ");
		
		this.cbChoixRegate.setPreferredSize(new Dimension(200, 30));
		
		this.btnArriver.setFont(police);
		this.btnAbandonner.setFont(police);
		this.btnModifier.setFont(police);
		this.cbChoixRegate.setFont(police);
		lbChoixRegate.setFont(police);
		
		this.ModelListParticipant = new ModelListParticipantSimulation();
		this.listParticipant = new JTable(this.ModelListParticipant);
		this.listParticipant.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panelGroupControl = new JPanel(new GridLayout(0,2));
		JPanel panelControlSimulation = new JPanel(new FlowLayout());
		JPanel panelControlRegate = new JPanel(new FlowLayout());
		
		panelControlSimulation.add(btnArriver);
		panelControlSimulation.add(btnAbandonner);
		panelControlSimulation.add(btnModifier);
		
		panelControlRegate.add(lbChoixRegate);
		panelControlRegate.add(cbChoixRegate);
		
		panelGroupControl.add(panelControlRegate);
		panelGroupControl.add(panelControlSimulation);
		
		content.add(panelGroupControl, BorderLayout.NORTH);
		content.add(new JScrollPane(this.listParticipant), BorderLayout.CENTER);
	}

	public JPanel getContent() {
		return content;
	}

	public JComboBox<Regate> getCbChoixRegate() {
		return cbChoixRegate;
	}

	public JTable getListParticipant() {
		return listParticipant;
	}

	public ModelListParticipantSimulation getModelListParticipant() {
		return ModelListParticipant;
	}

	public JButton getBtnArriver() {
		return btnArriver;
	}

	public JButton getBtnAbandonner() {
		return btnAbandonner;
	}

	public JButton getBtnModifier() {
		return btnModifier;
	}
	
	
	
}
