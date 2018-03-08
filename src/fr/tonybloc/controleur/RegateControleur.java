package fr.tonybloc.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.exceptions.ExceptionAucuneLigneSelectionne;
import fr.tonybloc.exceptions.ExceptionChampsVide;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.composant.ModelComboBoxRegates;
import fr.tonybloc.modele.composant.ModelListRegate;
import fr.tonybloc.outils.JDoubleField;
import fr.tonybloc.outils.Outils;

/**
 * 
 * @author Tony
 *
 */
public class RegateControleur implements ActionListener {

	JPanel panelCreationRegate;
	
	private JTextField tfNomRegate;
	private JDoubleField tfDistance;
	private JDatePickerImpl dpDate;
	private JButton btnCreation;
	private JButton btnAnnuler;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JTable listeRegates;
	
	ModelListRegate modelListRegate;
	
	private RegateDAO regateManager = DAOFactory.getRegateDAO();
	private final static Pattern DISTANCE_FORMAT = Pattern.compile("^[0-9]+.{0,1}[0-9]{0,2}$");
	
	private final static int ACTION_MODIF = 1;
	private final static int ACTION_CREE = 2;
	
	private int action;
	private int ligneAModifier;
	
	/**
	 * Instentiation du controleur
	 * @param modelListRegate
	 * @param tfNomRegate
	 * @param tfDistance
	 * @param dpDate
	 * @param btnCreation
	 * @param btnSupprimer
	 * @param btnModifier
	 * @param listRegates
	 */
	public RegateControleur ( 
			JPanel panelCreationRegate,
			ModelListRegate modelListRegate, 
			JTextField tfNomRegate, 
			JDoubleField tfDistance, 
			JDatePickerImpl dpDate, 
			JButton btnCreation,
			JButton btnAnnuler,
			JButton btnSupprimer,
			JButton btnModifier,
			JTable listRegates ) {
		
		panelCreationRegate = panelCreationRegate;
		this.modelListRegate = modelListRegate;
		this.tfNomRegate = tfNomRegate;
		this.tfDistance = tfDistance;
		this.dpDate = dpDate;
		this.btnCreation = btnCreation;
		this.btnAnnuler = btnAnnuler;
		this.btnModifier = btnModifier;
		this.btnSupprimer = btnSupprimer;
		this.listeRegates = listRegates;
		
		
		this.action = ACTION_CREE;
		this.ligneAModifier = -1;
	}
	
	/**
	 * Gestion des Actions
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		
		// Définition des Actions
		if (source.equals(this.btnCreation)) {
			
			System.out.println("Création");
			try {
				this.ActionCreer();
			} catch (ExceptionChampsVide e1) {
				JOptionPane.showMessageDialog(this.panelCreationRegate, "Des champs de saisies sont vides", "Avertissement", JOptionPane.WARNING_MESSAGE);
			}
			
		}else if(source.equals(this.btnModifier)) {
			
			System.out.println("Modification");
			try {
				ActionModificationActiver();
			} catch (ExceptionAucuneLigneSelectionne e1) {
				JOptionPane.showMessageDialog(this.panelCreationRegate, "Aucune ligne sélectionnée", "Avertissement", JOptionPane.WARNING_MESSAGE);
			}
			
		}else if(source.equals(this.btnSupprimer)) {
			
			System.out.println("Suppression");
			try {
				this.ActionSupprimer();
			} catch (ExceptionAucuneLigneSelectionne e1) {
				JOptionPane.showMessageDialog(this.panelCreationRegate, "Aucune ligne sélectionnée", "Avertissement", JOptionPane.WARNING_MESSAGE);
			}
			
		}else if(source.equals(this.btnAnnuler)) {
			
			this.ActionAnnuler();
		
		}
		else {
		
			System.out.println("source non trouvé !");
		
		}
	}
	/**
	 * Action du suppression : Supprime une régate
	 * @throws ExceptionAucuneLigneSelectionne
	 */
	private void ActionSupprimer() throws ExceptionAucuneLigneSelectionne {
		
		int ligneSelectionne = this.listeRegates.getSelectedRow();
		
		if( ligneSelectionne != -1) {
			int confirm = JOptionPane.showConfirmDialog(this.panelCreationRegate, "Voulez-vous supprimer la régate ?", "Avertissement", JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				this.modelListRegate.removeRegate(ligneSelectionne);
			}
		}else {
			throw new ExceptionAucuneLigneSelectionne();
		}
		
	}
	/**
	 * Action de modification : Modifie une régate
	 * @throws ExceptionAucuneLigneSelectionne 
	 */
	private void ActionModificationActiver() throws ExceptionAucuneLigneSelectionne {
		this.ligneAModifier = this.listeRegates.getSelectedRow();
		
		if( this.ligneAModifier != -1) {
			
			DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.FRANCE);
			
			this.btnAnnuler.setVisible(true);
			this.tfNomRegate.setText( (String) this.modelListRegate.getValueAt( ligneAModifier, ModelListRegate.LIBELLE ) );
			this.tfDistance.setText( "" + this.modelListRegate.getValueAt( ligneAModifier, ModelListRegate.DISTANCE ) );
			this.btnCreation.setText("Confirmer la modification");
			LocalDate date = LocalDate.parse( (String) this.modelListRegate.getValueAt( ligneAModifier, ModelListRegate.DATE_DEPART ), DATE_FORMAT);
			
//			System.out.println("DAY :" +date.getDayOfMonth() + "MONTH :" +date.getMonthValue() + "YEAR" + date.getYear());

			this.dpDate.getModel().setSelected(true);
			this.dpDate.getModel().setDate(date.getYear(), date.getMonthValue()-1, date.getDayOfMonth());
			
			this.action = ACTION_MODIF;
			
		}else {
			throw new ExceptionAucuneLigneSelectionne();
		}
	}
	
	/**
	 * Action:  Crée une régate;
	 * @throws ExceptionChampsVide
	 */
	private void ActionCreer() throws ExceptionChampsVide {
		
		// Vérifie la saisie du champ : distance
		Matcher m = DISTANCE_FORMAT.matcher(this.tfDistance.getText());
		Boolean verifDistance = m.find();
		
		// Création / Modification d'une régate.
		if(verifDistance && !Outils.estVide(this.tfNomRegate) && !Outils.estVide(this.tfDistance) && !Outils.estVide(dpDate) ) {
			
			String libelle = this.tfNomRegate.getText();
			Double distance = Double.parseDouble(this.tfDistance.getText());
			Date date_depart = (Date) this.dpDate.getModel().getValue();
			
			switch (this.action) {
				case ACTION_CREE:
				
					this.modelListRegate.addRegate(new Regate(libelle, distance, date_depart, false));
					viderChampsFormulaire();
					
					break;
				case ACTION_MODIF:
				
					int idRegateAModifier = (int) this.modelListRegate.getValueAt(this.ligneAModifier, ModelListRegate.ID_REGATE);
					Regate regate = new Regate(idRegateAModifier, libelle, distance, date_depart, false);
					this.modelListRegate.updateRegate(regate, this.ligneAModifier);
					viderChampsFormulaire();				
					
					break;
			}
			
		}else {
			throw new ExceptionChampsVide();
		}
	}
	/**
	 * Action : Annule une modification
	 */
	private void ActionAnnuler() {
		viderChampsFormulaire();
	}
	/**
	 * Netoye les zone de saisies
	 */
	private void viderChampsFormulaire() {
		this.btnAnnuler.setVisible(false);
		this.tfNomRegate.setText("");
		this.tfDistance.setText("");
		this.dpDate.getJFormattedTextField().setText("");
		this.btnCreation.setText("Créer");
		this.action = ACTION_CREE;
	}
	/**
	 * Mes à jour les composants
	 */
	public void updateComponent(){		
		this.modelListRegate.updateTable();
	}
}
