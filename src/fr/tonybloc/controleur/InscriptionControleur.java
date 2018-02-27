package fr.tonybloc.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.ClassementDAO;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.dao.implement.VoilierDAO;
import fr.tonybloc.exceptions.ExceptionChampsVide;
import fr.tonybloc.exceptions.ExceptionTropDeParticipants;
import fr.tonybloc.modele.Categorie;
import fr.tonybloc.modele.Classement;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.Voilier;
import fr.tonybloc.modele.composant.ModelComboBoxCategories;
import fr.tonybloc.modele.composant.ModelComboBoxRegates;
import fr.tonybloc.modele.composant.ModelListParticipant;
import fr.tonybloc.outils.JNumberField;
import fr.tonybloc.outils.Outils;

public class InscriptionControleur implements ActionListener {

	// Modèle
	//Voilier voilier;
	//Categorie categorie;
	
	// Composants : Swing
	private JTextField tfNomVoilier; 
	private JTextField tfNomSkippeur;
	private JTextField tfPrenomSkippeur;
	private JNumberField tfRating;
	private JComboBox<Categorie> cbCategorie;
	private JComboBox<Regate> cbChoixRegate;
	private JButton btnInscription;
	private JButton btnAnnuler;
	private JTable listParticipants;
	private JLabel lbInfoJTable;
	
	private RegateDAO regateManager = DAOFactory.getRegateDAO();
	private VoilierDAO voilierManager = DAOFactory.getVoilierDAO();
	private ClassementDAO ClassementManager = DAOFactory.getClassementDAO();
	
	private ModelListParticipant modelListParticipant;
	
	private Regate regateSelectionner;
	private Categorie categorieSelectionner;
	
	private final static String PLACEHOLDER_NOM_SKIPPEUR = "Nom";
	private final static String PLACEHOLDER_PRENOM_SKIPPEUR = "Prenom";
	
	
	/**
	 * Instancie le controleur
	 * @param voilier
	 * @param tfNomVoilier
	 * @param tfNomSkippeur
	 * @param cbCategorie
	 */
	public InscriptionControleur(
			ModelListParticipant modelListParticipant,
			JTextField tfNomVoilier, 
			JTextField tfNomSkippeur,
			JTextField tfPrenomSkippeur,
			JNumberField tfRating,
			JComboBox<Categorie> cbCategorie,
			JComboBox<Regate> cbChoixRegate,
			JButton btnInscription,
			JButton btnAnnuler,
			JTable listParticipants,
			JLabel lbInfoJTable) {
		
		this.modelListParticipant = modelListParticipant;
		
		this.tfNomVoilier = tfNomVoilier; 
		this.tfNomSkippeur = tfNomSkippeur;
		this.tfPrenomSkippeur = tfPrenomSkippeur;
		this.tfRating = tfRating;
		this.cbCategorie = cbCategorie;
		this.cbChoixRegate = cbChoixRegate;
		this.btnInscription = btnInscription;
		this.btnAnnuler = btnAnnuler;
		this.listParticipants = listParticipants;
		this.lbInfoJTable = lbInfoJTable;
		
		init();
	}
	
	
	private void init() {
		this.regateSelectionner = null;
		this.categorieSelectionner = null;
//		Outils.placeholder(this.tfNomSkippeur, PLACEHOLDER_NOM_SKIPPEUR);
//		Outils.placeholder(this.tfPrenomSkippeur, PLACEHOLDER_PRENOM_SKIPPEUR);
		
//		this.cbChoixRegate.setModel(new ModelComboBoxRegates());
		this.cbCategorie.setModel(new ModelComboBoxCategories());
		
	}
	
	/**
	 * Inscrit un utilisateur à une régate
	 * @throws ExceptionChampsVide
	 * @throws ExceptionTropDeParticipants 
	 */
	private void ActionInscrire() throws ExceptionChampsVide, ExceptionTropDeParticipants {
		
		if(this.modelListParticipant.getRowCount() < 20) {
			if(!Outils.estVide(tfNomSkippeur) && !Outils.estVide(tfPrenomSkippeur) && 
					!Outils.estVide(tfNomVoilier) && !Outils.estVide(tfRating) &&
						categorieSelectionner != null && regateSelectionner != null) {
				
				String nomSkippeur = tfNomSkippeur.getText();
				String prenomSkippeur = tfPrenomSkippeur.getText();
				String nomVoilier = tfNomVoilier.getText();
				int rating = Integer.parseInt(tfRating.getText());
				
				this.modelListParticipant.addParticipant(new Voilier(categorieSelectionner, nomVoilier, nomSkippeur, prenomSkippeur, rating), regateSelectionner ); 
						
				ActionActualiseLeLabelInformations();
				viderChampsFormulaire();
			}else {
				throw new ExceptionChampsVide();
			}		
		}else {
			throw new ExceptionTropDeParticipants();
		}		
	}
	
	/**
	 * Supprime la participation d'un skippeur
	 */
	private void ActionAnnulerParticipation() {
		int ligneSelectionne = this.listParticipants.getSelectedRow();
		modelListParticipant.removeParticpant(ligneSelectionne, this.regateSelectionner);
		
		ActionActualiseLeLabelInformations();
	}
	
	/**
	 * Modifier la JTables (affiche les participant à une regate)
	 */
	private void ActionSelectionnerRegate() {
		this.regateSelectionner = (Regate) cbChoixRegate.getSelectedItem();
		System.out.println(regateSelectionner.getId() + " - " + regateSelectionner.getIntituler());
		List<Voilier> participants = regateManager.getAllParticipant(regateSelectionner.getId());
		modelListParticipant.updateTables(participants);
		
		ActionActualiseLeLabelInformations();
		
	}
	
	/**
	 * Actualise le JLable décrivant la JTable
	 */
	private void ActionActualiseLeLabelInformations(){
		this.lbInfoJTable.setText("Régate : " + regateSelectionner.getIntituler() + " - Nombre de participants : " + modelListParticipant.getRowCount()+ "/20");

	}
	
	/**
	 * Nettoie les champs de formulaires
	 */
	private void viderChampsFormulaire() {
		this.tfNomSkippeur.setText("");
		this.tfNomVoilier.setText("");
		this.tfPrenomSkippeur.setText("");
		this.tfRating.setText("");
	}
	
	/**
	 * ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		Object source = event.getSource();
		
		if(source == cbChoixRegate) {
			ActionSelectionnerRegate();
		}
		else if(source == cbCategorie) {
			this.categorieSelectionner = (Categorie) cbCategorie.getSelectedItem();
		}
		else if(source == btnInscription) {
			try {
				ActionInscrire();
			} catch (ExceptionChampsVide e) {
				e.printStackTrace();
			} catch (ExceptionTropDeParticipants e) {
				e.printStackTrace();
			}
		}
		else if(source == btnAnnuler) {
			ActionAnnulerParticipation();
		}
		
		System.out.println("Click sur le bouton");

	}

}
