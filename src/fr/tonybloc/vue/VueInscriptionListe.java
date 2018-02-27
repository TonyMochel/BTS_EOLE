package fr.tonybloc.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import fr.tonybloc.modele.composant.ModelListParticipant;
import fr.tonybloc.modele.composant.ModelListRegate;

public class VueInscriptionListe{

	private JPanel panelListParticipant;
	private JTable listParticipant;
	private JLabel lbInformationNbParticpants;
	private JButton btnAnnuler;
	private ModelListParticipant modelListParticipant;
	//private TableColumnModel columnModelListParticipant;

	private final Dimension dim = new Dimension(150, 30);
	private final Font police = new Font("Arial", Font.PLAIN, 14);
	
	
	public VueInscriptionListe() {
		
		this.panelListParticipant = new JPanel(new BorderLayout());
		this.panelListParticipant.setBorder( new EmptyBorder(20, 10, 20, 10));
		
		// COMPOSANT
		JPanel boutons = new JPanel();
		
		
		this.modelListParticipant = new ModelListParticipant();
		this.listParticipant = new JTable(this.modelListParticipant);
		this.listParticipant.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.btnAnnuler = new JButton("Annuler une particpation");
		this.btnAnnuler.setFont(this.police);
		boutons.add(btnAnnuler);
		
		this.lbInformationNbParticpants = new JLabel("Aucune régate selectionnée !");
		this.lbInformationNbParticpants.setFont(police);
		
		this.panelListParticipant.add(lbInformationNbParticpants, BorderLayout.NORTH);
		this.panelListParticipant.add(new JScrollPane(this.listParticipant), BorderLayout.CENTER);
		this.panelListParticipant.add(boutons, BorderLayout.SOUTH);
		
	}
	public JLabel getLabelInfo() {
		return this.lbInformationNbParticpants;
	}
	public JPanel getPanelListParticipant() {
		return this.panelListParticipant;
	}

	public JTable getListParticipant() {
		return this.listParticipant;
	}

	public JButton getBtnAnnuler() {
		return this.btnAnnuler;
	}

	public ModelListParticipant getModel() {
		return this.modelListParticipant;
	}
	
	
}
