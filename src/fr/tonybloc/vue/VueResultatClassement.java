package fr.tonybloc.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.composant.ModelListRegate;
import fr.tonybloc.modele.composant.ModelListResultat;

public class VueResultatClassement {

	private JPanel content;
	private JTable listClassementCategorie1;
	private JTable listClassementCategorie2;
	private JTable listClassementCategorie3;
	private JTable listClassementCategorie4;
 
	private ModelListResultat modelListResultat1;
	private ModelListResultat modelListResultat2;
	private ModelListResultat modelListResultat3;
	private ModelListResultat modelListResultat4;
	
	private JComboBox<Regate> cbChoixRegateCloturer;
	private JButton btnTelechargerPDF;
	private JButton btnTelechargerCSV;
	private JButton btnImprimer;
	
	private JPanel panelClassement;
	
	private final Dimension dim = new Dimension(150, 30);
	private final Font police = new Font("Arial", Font.PLAIN, 12);
	
	/**
	 * Crée une instance de la class vueResultatClassement
	 */
	public VueResultatClassement() {
		
		this.content = new JPanel(new BorderLayout()); 
		this.content.setBorder(new EmptyBorder(20, 10, 20, 10));
		
		this.modelListResultat1 = new ModelListResultat();
		this.listClassementCategorie1 = new JTable(modelListResultat1); 
		this.modelListResultat2 = new ModelListResultat();
		this.listClassementCategorie2 = new JTable(modelListResultat2);
		this.modelListResultat3 = new ModelListResultat();
		this.listClassementCategorie3 = new JTable(modelListResultat3);
		this.modelListResultat4 = new ModelListResultat();
		this.listClassementCategorie4 = new JTable(modelListResultat4);
                  
		JLabel lbChoixRegate = new JLabel("Sélectionner une régate : ");
		JLabel lbCategorie1 = new JLabel("Classement : catégorie 1");
		JLabel lbCategorie2 = new JLabel("Classement : catégorie 2");
		JLabel lbCategorie3 = new JLabel("Classement : catégorie 3");
		JLabel lbCategorie4 = new JLabel("Classement : catégorie 4");
		
		this.cbChoixRegateCloturer = new JComboBox<Regate>();
		this.btnTelechargerPDF = new JButton("Télécharger PDF");
		this.btnTelechargerCSV = new JButton("Télécharger CSV");
		this.btnImprimer = new JButton("Imprimer");
		
		lbChoixRegate.setFont(police);
		lbCategorie1.setFont(police);
		lbCategorie2.setFont(police);
		lbCategorie3.setFont(police);
		lbCategorie4.setFont(police);
		this.cbChoixRegateCloturer.setFont(police);
		this.btnTelechargerPDF.setFont(police);
		this.btnTelechargerCSV.setFont(police);
		this.btnImprimer.setFont(police);
		
		JPanel panelGroupControle = new JPanel(new BorderLayout());
		panelGroupControle.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		JPanel panelChoixRegate = new JPanel(new FlowLayout());
		JPanel panelControle = new JPanel(new FlowLayout());
		
		this.panelClassement = new JPanel( new GridLayout(4,0) );
		
		JPanel panelCategorie1 = new JPanel(new BorderLayout());
		JPanel panelCategorie2 = new JPanel(new BorderLayout());
		JPanel panelCategorie3 = new JPanel(new BorderLayout());
		JPanel panelCategorie4 = new JPanel(new BorderLayout());
		
		
		panelCategorie1.add(lbCategorie1, BorderLayout.NORTH);
		panelCategorie1.add(new JScrollPane(listClassementCategorie1), BorderLayout.CENTER);
		panelCategorie2.add(lbCategorie2, BorderLayout.NORTH);
		panelCategorie2.add(new JScrollPane(listClassementCategorie2), BorderLayout.CENTER);
		panelCategorie3.add(lbCategorie3, BorderLayout.NORTH);
		panelCategorie3.add(new JScrollPane(listClassementCategorie3), BorderLayout.CENTER);
		panelCategorie4.add(lbCategorie4, BorderLayout.NORTH);
		panelCategorie4.add(new JScrollPane(listClassementCategorie4), BorderLayout.CENTER);
	
		
		panelClassement.add(panelCategorie1);
		panelClassement.add(panelCategorie2);
		panelClassement.add(panelCategorie3);
		panelClassement.add(panelCategorie4);
		
		panelControle.add(this.btnImprimer);
		panelControle.add(this.btnTelechargerCSV);
		//panelControle.add(this.btnTelechargerPDF);
		
		panelChoixRegate.add(lbChoixRegate);
		panelChoixRegate.add(this.cbChoixRegateCloturer);
		
		panelGroupControle.add(panelChoixRegate, BorderLayout.NORTH);
		panelGroupControle.add(panelControle, BorderLayout.CENTER);
		
		this.content.add(panelClassement, BorderLayout.CENTER);
		this.content.add(panelGroupControle, BorderLayout.EAST);
	}

	public JPanel getContent() {
		return content;
	}
	public JPanel getPanelClassement() {
		return panelClassement;
	}

	public JTable getListClassementCategorie1() {
		return listClassementCategorie1;
	}

	public JTable getListClassementCategorie2() {
		return listClassementCategorie2;
	}

	public JTable getListClassementCategorie3() {
		return listClassementCategorie3;
	}

	public JTable getListClassementCategorie4() {
		return listClassementCategorie4;
	}

	public ModelListResultat getModelListResultat1() {
		return modelListResultat1;
	}
	public ModelListResultat getModelListResultat2() {
		return modelListResultat2;
	}
	public ModelListResultat getModelListResultat3() {
		return modelListResultat3;
	}
	public ModelListResultat getModelListResultat4() {
		return modelListResultat4;
	}

	public JComboBox<Regate> getCbChoixRegateCloturer() {
		return cbChoixRegateCloturer;
	}

	public JButton getBtnTelechargerPDF() {
		return btnTelechargerPDF;
	}

	public JButton getBtnTelechargerCSV() {
		return btnTelechargerCSV;
	}

	public JButton getBtnImprimer() {
		return btnImprimer;
	}
	
}
