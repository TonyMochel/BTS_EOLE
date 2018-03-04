package fr.tonybloc.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.ClassementDAO;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.dao.implement.VoilierDAO;
import fr.tonybloc.modele.Classement;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.composant.Chronometre;
import fr.tonybloc.modele.composant.ModelComboBoxRegates;
import fr.tonybloc.modele.composant.ModelListParticipantSimulation;

public class ClassementControleur implements ActionListener{
	
	private JPanel panelSimulation;
	
	private JButton btnChronoStop;
	private JButton btnChronoReset;
	private JButton btnChronoStart;
	private JComboBox<Regate> cbChoixRegate;
	private JTable listParticipants;
	private JLabel lbChrono;
	private Chronometre chrono;
	
	private JButton btnCloture;
	
	private JButton btnArrive;
	private JButton btnModifier;
	private JButton btnAbandon;
	
	private final RegateDAO regateManager = DAOFactory.getRegateDAO();
	private final VoilierDAO voilierManager = DAOFactory.getVoilierDAO();
	private final ClassementDAO ClassementManager = DAOFactory.getClassementDAO();
	
	private ModelListParticipantSimulation modelListParticipantSimulation;
	
	private Regate regateSelectionner;
	private Classement classementSelected;
	private boolean isStarted;
	
	private final static int AUCUNE_LIGNE_SELECTIONNER = -1;
	
	public ClassementControleur(
			JPanel panelSimulation,
			ModelListParticipantSimulation modelListParticipant,
			JButton btnCloture,
			JButton btnArreter,
			JButton btnModifier,
			JButton btnAbandon,
			JButton btnChronoStop,
			JButton btnChronoReset,
			JButton btnChronoStart,
			JComboBox<Regate> cbChoixRegate,
			JTable listParticipants,
			JLabel lbChrono,
			Chronometre chrono
			) {
		
		this.panelSimulation = panelSimulation;
		
		this.modelListParticipantSimulation = modelListParticipant;
		this.btnArrive = btnArreter;
		this.btnModifier = btnModifier;
		this.btnAbandon = btnAbandon;		
		
		this.btnChronoStop = btnChronoStop;
		this.btnChronoReset = btnChronoReset;
		this.btnChronoStart = btnChronoStart;
		this.cbChoixRegate = cbChoixRegate;
		this.listParticipants = listParticipants;
		this.lbChrono = lbChrono;
		
		this.btnCloture = btnCloture;
		
		this.chrono = chrono;
		init();
	}
	
	private void init() {
		this.classementSelected = null;
		this.regateSelectionner = null;	
		this.isStarted = false;
		
		this.btnArrive.setEnabled(false);
		this.btnAbandon.setEnabled(false);
		this.btnModifier.setEnabled(false);
		
	}
	/**
	 * Cloture la régate séléctionner
	 */
	private void ActionCloturerRegate() {
		this.regateSelectionner.setCloture(true);
		this.regateManager.clotureRegate(this.regateSelectionner);
		// Message
		this.cbChoixRegate.setModel(new ModelComboBoxRegates());
		
	}
	/**
	 * Modifier la JTables (affiche les participant à une regate)
	 */
	private void ActionSelectionnerRegate() {
		this.regateSelectionner = (Regate) cbChoixRegate.getSelectedItem();
		modelListParticipantSimulation.updateTable(regateSelectionner);		
	}
	
	/**
	 * Arrivé d'un participant
	 */
	private void ActionParticipantArriver() {
		
		if(isStarted) {
			int ligneSelectionner = this.listParticipants.getSelectedRow();
			if(ligneSelectionner != AUCUNE_LIGNE_SELECTIONNER) {
				this.modelListParticipantSimulation.updateTempArriver(ligneSelectionner, this.lbChrono.getText());
			}else {
				System.out.println("Aucune ligne séléctionner !");
			}			
		}else {
			System.out.println("La régate n'a pas commencer !");
		}
	}
	
	/**
	 * Abandon du participant
	 */
	private void ActionParticipantAbandon() {
		if(isStarted) {
			int ligneSelectionner = this.listParticipants.getSelectedRow();
			if(ligneSelectionner != AUCUNE_LIGNE_SELECTIONNER) {
				this.modelListParticipantSimulation.updateTempArriver(ligneSelectionner, "00:00:00");
			}else {
				System.out.println("Aucune ligne séléctionner !");
			}	
		}else {
			System.out.println("La régate n'a pas commencer !");
		}
	}
	
	/**
	 * Modifie un participants
	 */
	private void ActionParticipantModifier() {	
		if(isStarted) {
			int ligneSelectionner = this.listParticipants.getSelectedRow();
			if(ligneSelectionner != AUCUNE_LIGNE_SELECTIONNER) {
				this.modelListParticipantSimulation.updateTempArriver(ligneSelectionner, null);
			}else {
				System.out.println("Aucune ligne séléctionner !");
			}
		}else {
			System.out.println("La régate n'a pas commencer !");
		}
		
	}
	
	/**
	 * Démarre le Chronometre
	 */
	private void ActionStartChrono() {
		if(this.regateSelectionner == null) {
			System.out.println("Aucune régate selectionner");
			//JOptionPane.showMessageDialog(this.panelSimulation, "Aucune régate selectionner", "Titre", JOptionPane.WARNING_MESSAGE);
			//System.out.println("Valeur : "+ test);
		}else {
			this.chrono.StartTimer();
			this.isStarted = true;
			this.cbChoixRegate.setEnabled(false);
			this.btnChronoReset.setEnabled(false);
			this.btnChronoStart.setEnabled(false);
			

			this.btnArrive.setEnabled(true);
			this.btnAbandon.setEnabled(true);
			this.btnModifier.setEnabled(true);
			
		}
	}
	
	/**
	 * Stop le chronometre
	 */
	private void ActionStopChrono() {
		if(this.modelListParticipantSimulation.tousLesParticipantSontArrive()) {
			this.chrono.StopTimer();
			this.isStarted = false;
			this.btnChronoReset.setEnabled(true);
			this.btnChronoStart.setEnabled(true);

			this.btnArrive.setEnabled(false);
			this.btnAbandon.setEnabled(false);
			this.btnModifier.setEnabled(false);
			
		}else {
			System.out.println("Tous les participant ne sont pas encore arrivée");
		}
		
		
		
	}
	
	/**
	 * Réinitialise le Chronometre
	 */
	private void ActionResetChrono() {
		this.chrono.ResetTimer();
		this.cbChoixRegate.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if(source.equals(cbChoixRegate)) {
			ActionSelectionnerRegate();			
		}else if(source.equals(btnArrive)) {
			ActionParticipantArriver();
		}else if(source.equals(btnAbandon)) {
			ActionParticipantAbandon();
		}else if(source.equals(btnModifier)) {
			ActionParticipantModifier();
		}else if(source.equals(btnChronoStart)) {
			ActionStartChrono();
		}else if(source.equals(btnChronoReset)) {
			ActionResetChrono();
		}else if(source.equals(btnChronoStop)) {
			ActionStopChrono();
		}else if(source.equals(btnCloture)) {
			if(this.modelListParticipantSimulation.tousLesParticipantSontArrive()) {
				ActionCloturerRegate();
			}else {
				// Message
				System.out.println("Tous les participants ne sont pas encore arrivée !");
			}
			
		}
		
	}
	
}
