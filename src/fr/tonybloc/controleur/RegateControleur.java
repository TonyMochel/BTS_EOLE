package fr.tonybloc.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.exceptions.ExceptionChampsVide;
import fr.tonybloc.exceptions.ExceptionAucuneLigneSelectionne;
import fr.tonybloc.modele.ModelListRegate;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.outils.*;

/**
 * 
 * @author Tony
 *
 */
public class RegateControleur implements ActionListener {

	//JPanel panelCreationRegate;
	JTextField tfNomRegate;
	JNumberField tfDistance;
	JDatePickerImpl dpDate;
	JButton btnCreation;
	JButton btnAnnuler;
	JButton btnModifier;
	JButton btnSupprimer;
	JTable listeRegates;
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
			ModelListRegate modelListRegate, 
			JTextField tfNomRegate, 
			JNumberField tfDistance, 
			JDatePickerImpl dpDate, 
			JButton btnCreation,
			JButton btnAnnuler,
			JButton btnSupprimer,
			JButton btnModifier,
			JTable listRegates) {
		
		//panelCreationRegate = new JPanel();
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
		if (source == this.btnCreation) {
			System.out.println("Création");
			try {
				this.ActionCreer();
			} catch (ExceptionChampsVide e1) {
				e1.printStackTrace();
			}
		}else if(source == this.btnModifier) {
			System.out.println("Modification");
			try {
				ActionModificationActiver();
			} catch (ExceptionAucuneLigneSelectionne e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(source == this.btnSupprimer) {
			System.out.println("Suppression");
			try {
				this.ActionSupprimer();
			} catch (ExceptionAucuneLigneSelectionne e1) {
				e1.printStackTrace();
			}
			
		}else if(source == this.btnAnnuler) {
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
			this.modelListRegate.removeRegate(ligneSelectionne);
		}else {
			throw new ExceptionAucuneLigneSelectionne();
		}
		
	}
	/**
	 * Action de modification : Modifie une régate
	 * @throws ExceptionAucuneLigneSelectionne 
	 */
	@SuppressWarnings("deprecation")
	private void ActionModificationActiver() throws ExceptionAucuneLigneSelectionne {
		this.ligneAModifier = this.listeRegates.getSelectedRow();
		
		if( this.ligneAModifier != -1) {
			
			DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			this.btnAnnuler.setVisible(true);
			this.tfNomRegate.setText( (String) this.modelListRegate.getValueAt( ligneAModifier, ModelListRegate.LIBELLE ) );
			this.tfDistance.setText( "" + this.modelListRegate.getValueAt( ligneAModifier, ModelListRegate.DISTANCE ) );
			
			LocalDate date = LocalDate.parse( (String) this.modelListRegate.getValueAt( ligneAModifier, ModelListRegate.DATE_DEPART ), DATE_FORMAT);
			
			
			this.dpDate.getModel().setDay(date.getDayOfMonth());
			this.dpDate.getModel().setMonth(date.getMonthValue()-1);
			this.dpDate.getModel().setYear(date.getYear());

			this.dpDate.getModel().setSelected(true);
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
		this.action = ACTION_CREE;
	}
}
