package fr.tonybloc.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.modele.composant.ModelComboBoxRegates;
/**
 * Controleur du menu
 * @author Tony
 *
 */
public class MenuControle implements ActionListener {

	JMenuItem itemCreationRegate;
	JMenuItem itemInscription;
	
	JMenuItem itemSimulation;
	JMenuItem itemResultatRegate;
	
	JMenuItem itemAide;
	JMenuItem itemInformation;
	
	
	JPanel contenuCreationRegate;
	JPanel contenuInscription;
	JPanel contenuSimulation;
	JPanel contenuResultat;
	JPanel contenuAccueil;
	
	RegateControleur regateControleur;
	InscriptionControleur inscriptionControleur;
	ClassementControleur classementControleur;
	ResultatControleur resultatControleur;
//	JComboBox cbChoixRegateInscription;
//	JComboBox cbChoixRegateClassement;
//	JComboBox cbChoixRegateResult;
//	
	JFrame frame;
	
	private RegateDAO regateManager;
	
	/**
	 * Instancie le Controleur
	 * @param itemCreationRegate
	 * @param itemInscription
	 * @param itemSimulation
	 * @param itemResultatRegate
	 * @param itemAide
	 * @param itemInformation
	 */
	public MenuControle(
			JFrame frame,
			JPanel contenuAccueil,
			JPanel contenuCreationRegate,
			JPanel contenuInscription,
			JPanel contenuSimulation,
			JPanel contenuResultat,
			RegateControleur regateControleur,
			InscriptionControleur inscriptionControleur, 
			ClassementControleur classementControleur,
			ResultatControleur resultatControleur,
//			JComboBox cbChoixRegateInscription,
//			JComboBox cbChoixRegateClassement, 
//			JComboBox cbChoixRegateResult,
			JMenuItem itemCreationRegate, 
			JMenuItem itemInscription,
			JMenuItem itemSimulation,
			JMenuItem itemResultatRegate,
			JMenuItem itemAide,
			JMenuItem itemInformation) {
		
		this.frame = frame;
		
		this.contenuAccueil = contenuAccueil;
		this.contenuCreationRegate = contenuCreationRegate;
		this.contenuInscription = contenuInscription;
		this.contenuSimulation = contenuSimulation;
		this.contenuResultat = contenuResultat;
		
		this.itemCreationRegate = itemCreationRegate;
		this.itemInscription = itemInscription;
		this.itemSimulation = itemSimulation;
		this.itemResultatRegate = itemResultatRegate;
		this.itemAide = itemAide;
		this.itemInformation = itemInformation;
		
		this.regateControleur = regateControleur;
		this.inscriptionControleur = inscriptionControleur;
		this.classementControleur = classementControleur;
		this.resultatControleur = resultatControleur;
//		this.cbChoixRegateInscription = cbChoixRegateInscription;
//		this.cbChoixRegateClassement = cbChoixRegateClassement;
//		this.cbChoixRegateResult = cbChoixRegateResult;
	}
	/**
	 * Evenement
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source.equals(itemCreationRegate) ) {
			regateControleur.updateComponent();
			
			this.frame.setContentPane(this.contenuCreationRegate);
			this.frame.setVisible(true);
		
		}else if(source.equals(itemInscription)) {
			inscriptionControleur.updateComponent();
			
			this.frame.setContentPane(this.contenuInscription);			
			this.frame.setVisible(true);
			
		}else if(source.equals(this.itemResultatRegate)) {
			this.resultatControleur.updateComponent();
//			this.cbChoixRegateResult.setModel(new ModelComboBoxRegates(ModelComboBoxRegates.REGATE_CLOSURE));
			
			this.frame.setContentPane(this.contenuResultat);
			this.frame.setVisible(true);
			
		}else if(source.equals(this.itemSimulation)) {
			this.classementControleur.updateComponent();
//			this.cbChoixRegateClassement.setModel(new ModelComboBoxRegates(ModelComboBoxRegates.REGATE_NOT_CLOSURE));
			
			this.frame.setContentPane(this.contenuSimulation);
			this.frame.setVisible(true);
		}else if(source.equals(this.itemAide)) {
			
		}else if(source.equals(this.itemInformation)) {
			
		}else {
			System.out.println("Elément de menu [" + e.getActionCommand()
	         + "] utilisé.");	
		}
		 		
	}
	
	public void AfficheCreateionRegatePanel() {
		
	}

}
