package fr.tonybloc.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import fr.tonybloc.modele.Categorie;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.outils.*;

/**
 * Vue formulaire : Inscription des participants 
 * @author Tony
 *
 */
public class VueInscriptionFormulaire {

	private JPanel formulaire;
	
	private JTextField tfNomSkippeur;
	private JTextField tfPrenomSkippeur;
	private JTextField tfNomVoilier;
	private JComboBox<Categorie> cbCategorie;
	private JNumberField tfRating;
	
	private JComboBox<Regate> cbChoixRegate;
	private JButton btnInscription;
	
	private final Dimension dim = new Dimension(170, 30);
	private final Font police = new Font("Arial", Font.PLAIN, 14);
	
	/**
	 * Instanciation de la vue
	 */
	public VueInscriptionFormulaire() {
		
		// JPanel
		this.formulaire = new JPanel(new BorderLayout());
		this.formulaire.setBorder(new EmptyBorder(20, 10, 20, 10));
		
		//JLabel
		JLabel lbChoixRegate = new JLabel("Choix Régate");
		JLabel lbNomSkippeur = new JLabel("Nom Skippeur");
		JLabel lbPrenomSkippeur = new JLabel("Prénom Skippeur");
		JLabel lbNomVoilier = new JLabel("Nom voilier");
		JLabel lbCategorie = new JLabel("Catégorie");
		JLabel lbRating = new JLabel("Rating");
		
		
		// JComponents
//		this.tfChercheParticipants = new JTextField();
		this.cbChoixRegate = new JComboBox<Regate>();
//		this.btnRechercheParticipants = new JButton();
		this.btnInscription = new JButton();
		
		this.tfNomSkippeur = new JTextField();
		this.tfPrenomSkippeur = new JTextField();
		this.tfNomVoilier = new JTextField();
		this.cbCategorie = new JComboBox<Categorie>();
		this.tfRating = new JNumberField();
		
		this.btnInscription = new JButton("Inscription");
		
		DocumentFilter uppercaseFilter = new CapitaliseDocumentFilter();
		((AbstractDocument) this.tfNomSkippeur.getDocument()).setDocumentFilter(uppercaseFilter);
		((AbstractDocument) this.tfPrenomSkippeur.getDocument()).setDocumentFilter(uppercaseFilter);
		
		
//		this.tfChercheParticipants.setFont(this.police);
		this.cbChoixRegate.setFont(this.police);
//		this.btnRechercheParticipants.setFont(this.police);
		this.btnInscription.setFont(this.police);
		this.tfNomSkippeur.setFont(this.police);
		this.tfPrenomSkippeur.setFont(this.police);
		this.tfNomVoilier.setFont(this.police);
		this.cbCategorie.setFont(this.police);
		this.tfRating.setFont(this.police);
		
		
		// PERSONALISATION DES CHAMPS
		lbCategorie.setPreferredSize(this.dim);
		lbChoixRegate.setPreferredSize(this.dim);
		lbNomVoilier.setPreferredSize(this.dim);
		lbNomSkippeur.setPreferredSize(this.dim);
		lbPrenomSkippeur.setPreferredSize(this.dim);
		lbRating.setPreferredSize(this.dim);
		this.tfNomSkippeur.setPreferredSize(this.dim);
		this.cbChoixRegate.setPreferredSize(this.dim);
		
		lbCategorie.setFont(this.police);
		lbChoixRegate.setFont(this.police);
		lbNomVoilier.setFont(this.police);
		lbNomSkippeur.setFont(this.police);
		lbPrenomSkippeur.setFont(this.police);
		lbRating.setFont(this.police);
		this.tfNomSkippeur.setFont(this.police);
		this.tfNomVoilier.setFont(this.police);
		this.tfRating.setFont(this.police);
		this.cbCategorie.setFont(this.police);
		this.cbChoixRegate.setFont(this.police);
		this.btnInscription.setFont(this.police);
		
		// PANEL CHOIX REGATE
		JPanel panelChoixRegateLabel = new JPanel(new GridLayout(1,0));
		JPanel panelChoixRegateTextField = new JPanel(new GridLayout(1,0));
		
		panelChoixRegateLabel.add(lbChoixRegate);
		panelChoixRegateTextField.add(this.cbChoixRegate);
		
		// PANEL INSCRIPTION
		JPanel panelInscriptionLabel = new JPanel(new GridLayout(5,0));
		JPanel panelInscriptionTextField = new JPanel(new GridLayout(5,0));
		JPanel panelInscriptionSouth = new JPanel(new BorderLayout());
		JPanel panelInscriptionButtons = new JPanel(new FlowLayout());
		//JPanel panelSkippeur = new JPanel(new GridLayout(0,2));
		
		panelInscriptionLabel.add(lbNomVoilier);
		panelInscriptionLabel.add(lbNomSkippeur);
		panelInscriptionLabel.add(lbPrenomSkippeur);
		panelInscriptionLabel.add(lbCategorie);
		panelInscriptionLabel.add(lbRating);
		
//		panelSkippeur.add(this.tfNomSkippeur);
//		panelSkippeur.add(this.tfPrenomSkippeur);
		
		panelInscriptionTextField.add(this.tfNomVoilier);
		panelInscriptionTextField.add(this.tfNomSkippeur);
		panelInscriptionTextField.add(this.tfPrenomSkippeur);
		panelInscriptionTextField.add(this.cbCategorie);
		panelInscriptionTextField.add(this.tfRating);
		
		panelInscriptionButtons.add(this.btnInscription);
		panelInscriptionSouth.add(panelInscriptionButtons, BorderLayout.EAST);
		
		
		JPanel panelInscription = new JPanel(new BorderLayout());
		panelInscription.add(panelInscriptionLabel, BorderLayout.WEST);
		panelInscription.add(panelInscriptionTextField, BorderLayout.CENTER);
		panelInscription.add(panelInscriptionSouth, BorderLayout.SOUTH);
		
		
		panelInscription.setBorder(BorderFactory.createTitledBorder("Inscription des participants"));
		
		
		JPanel panelChoixRegate = new JPanel(new BorderLayout());
		panelChoixRegate.add(panelChoixRegateLabel, BorderLayout.WEST);
		panelChoixRegate.add(panelChoixRegateTextField, BorderLayout.CENTER);
		
		panelChoixRegate.setBorder(BorderFactory.createTitledBorder("Choix de la Régate"));
		
		JPanel panelContent = new JPanel(new BorderLayout());
		panelContent.add(panelChoixRegate, BorderLayout.NORTH);
		panelContent.add(panelInscription, BorderLayout.CENTER);
		
		
		this.formulaire.add(panelContent, BorderLayout.NORTH);
		
		
		
	}

	public JPanel getPanelFormulaire() {
		return formulaire;
	}

//	public JTextField getTfChercheParticipants() {
//		return tfChercheParticipants;
//	}

	public JComboBox<Regate> getCbChoixRegate() {
		return cbChoixRegate;
	}

//	public JButton getBtnRechercheParticipants() {
//		return btnRechercheParticipants;
//	}

	public JButton getBtnInscription() {
		return btnInscription;
	}

	public JTextField getTfNomSkippeur() {
		return tfNomSkippeur;
	}
	
	public JTextField getTfPrenomSkippeur() {
		return tfPrenomSkippeur;
	}

	public JTextField getTfNomVoilier() {
		return tfNomVoilier;
	}

	public JComboBox getCbCategorie() {
		return cbCategorie;
	}

	public JNumberField getTfRating() {
		return tfRating;
	}
}
