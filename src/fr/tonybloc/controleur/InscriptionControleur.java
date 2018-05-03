package fr.tonybloc.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.ClassementDAO;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.dao.implement.VoilierDAO;
import fr.tonybloc.exceptions.ExceptionChampsVide;
import fr.tonybloc.exceptions.ExceptionTropDeParticipants;
import fr.tonybloc.modele.Categorie;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.Voilier;
import fr.tonybloc.modele.composant.ModelComboBoxCategories;
import fr.tonybloc.modele.composant.ModelComboBoxRegates;
import fr.tonybloc.modele.composant.ModelListParticipant;
import fr.tonybloc.outils.JNumberField;
import fr.tonybloc.outils.Outils;

/**
 * Controleur page inscription
 * @author Tony
 *
 */
public class InscriptionControleur implements ActionListener {

	JPanel panelInscription;
	
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
	
	private final RegateDAO regateManager = DAOFactory.getRegateDAO();
	private final VoilierDAO voilierManager = DAOFactory.getVoilierDAO();
	private final ClassementDAO ClassementManager = DAOFactory.getClassementDAO();
	
	private ModelListParticipant modelListParticipant;
	
	private Regate regateSelectionner;
	private Categorie categorieSelectionner;
	
	private final static String PLACEHOLDER_NOM_SKIPPEUR = "Nom";
	private final static String PLACEHOLDER_PRENOM_SKIPPEUR = "Prenom";
	
	
	/**
	 * Crée une instance de la classe 'InscriptionControleur'
	 * @param panelInscription : JPanel page inscription
	 * @param modelListParticipant : Model de la JTable liste des participants
	 * @param tfNomVoilier : TextField nom du voilier
	 * @param tfNomSkippeur : TextField nom du skippeur
	 * @param tfPrenomSkippeur : TextField prénom du skippeur
	 * @param tfRating : TextField valeur de rating
	 * @param cbCategorie : ComboBox des categorie de voilier
	 * @param cbChoixRegate : ComboBox liste des régates
	 * @param btnInscription : JButton Inscription d'un participant
	 * @param btnAnnuler : JBUtton annulation d'une inscription
	 * @param listParticipants : JTable liste des participants
	 * @param lbInfoJTable : Libeler nombre de participant à la course
	 */
	public InscriptionControleur(
			JPanel panelInscription,
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
		this.panelInscription = panelInscription;
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
	
	/** Intialise les composants graphiques*/
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
			if(regateSelectionner == null) {
				JOptionPane.showMessageDialog(this.panelInscription, "Aucune régate sélectionnée", "Avertissement", JOptionPane.WARNING_MESSAGE);
			}else {
				if(!Outils.estVide(tfNomSkippeur) && !Outils.estVide(tfPrenomSkippeur) && 
						!Outils.estVide(tfNomVoilier) && !Outils.estVide(tfRating) &&
							categorieSelectionner != null ) {
								
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
		if(ligneSelectionne == -1) {
			JOptionPane.showMessageDialog(this.panelInscription, "Aucune ligne sélectionnée", "Avertissement", JOptionPane.WARNING_MESSAGE);
		}else {
			int confirm = JOptionPane.showConfirmDialog(this.panelInscription, "Voulez-vous désinscrire le participant?", "Avertissement", JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				modelListParticipant.removeParticpant(ligneSelectionne, this.regateSelectionner);
				ActionActualiseLeLabelInformations();
			}
		}
	}
	
	/**
	 * Modifie la JTables (affiche les participants à une regate)
	 */
	private void ActionSelectionnerRegate() {
		this.regateSelectionner = (Regate) cbChoixRegate.getSelectedItem();
		modelListParticipant.updateTables(this.regateSelectionner);
		
		ActionActualiseLeLabelInformations();
		
	}
	
	/**
	 * Actualise le label : nombre de participants
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
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		Object source = event.getSource();
		
		if(source.equals(cbChoixRegate)) {
			ActionSelectionnerRegate();
		}
		else if(source.equals(cbCategorie)) {
			this.categorieSelectionner = (Categorie) cbCategorie.getSelectedItem();
		}
		else if(source.equals(btnInscription)) {
			try {
				ActionInscrire();
			} catch (ExceptionChampsVide e) {
				JOptionPane.showMessageDialog(this.panelInscription, "Certain champs de saisies sont vide", "Avertissement", JOptionPane.WARNING_MESSAGE);
			} catch (ExceptionTropDeParticipants e) {
				JOptionPane.showMessageDialog(this.panelInscription, "Il y a trop de participant (max 20)", "Avertissement", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(source.equals(btnAnnuler)) {
			ActionAnnulerParticipation();
		}
		
		System.out.println("Click sur le bouton");

	}
	
	/**
	 * Met à jour les composants
	 */
	public void updateComponent(){
		this.cbChoixRegate.setModel(new ModelComboBoxRegates(ModelComboBoxRegates.REGATE_NOT_CLOSURE));
		this.regateSelectionner = null;
		this.lbInfoJTable.setText("Aucune régate sélectionné");
		this.modelListParticipant.updateTables(null);
	}
	

}
