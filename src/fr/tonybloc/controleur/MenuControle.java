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
	
	JMenuItem itemInformation;
	
	JPanel contenuCreationRegate;
	JPanel contenuInscription;
	JPanel contenuSimulation;
	JPanel contenuResultat;
	JPanel contenuAccueil;
	JPanel contenuInformation;
	
	RegateControleur regateControleur;
	InscriptionControleur inscriptionControleur;
	ClassementControleur classementControleur;
	ResultatControleur resultatControleur;

	JFrame frame;
	
	private RegateDAO regateManager;
	
	/**
	 * Crée une instance de la classe 'MenuControle'
	 * @param frame : Fenetre principale de l'application
	 * @param contenuAccueil : JPanel de la page d'acceuil
	 * @param contenuCreationRegate : JPanel de la page creation de régate
	 * @param contenuInscription : JPanel de la page inscription des participants
	 * @param contenuSimulation : JPanel de la page simulation de la régate
	 * @param contenuResultat : JPanel de la page de résultat de la régate
	 * @param contenuInformation : JPanel de la page d'informations
	 * @param regateControleur : Controleur régate
	 * @param inscriptionControleur : Controleur inscription
	 * @param classementControleur : Controleur classement
	 * @param resultatControleur : Controleur résultat
	 * @param itemCreationRegate : Onglet du menu (page régate)
	 * @param itemInscription : Onglet du menu (page inscription)
	 * @param itemSimulation : Onglet du menu (page simulation)
	 * @param itemResultatRegate : Onglet du menu (page résultats)
	 * @param itemInformation : Onglet du menu (page information)
	 */
	public MenuControle(
			JFrame frame,
			JPanel contenuAccueil,
			JPanel contenuCreationRegate,
			JPanel contenuInscription,
			JPanel contenuSimulation,
			JPanel contenuResultat,
			JPanel contenuInformation,
			RegateControleur regateControleur,
			InscriptionControleur inscriptionControleur, 
			ClassementControleur classementControleur,
			ResultatControleur resultatControleur,
			JMenuItem itemCreationRegate, 
			JMenuItem itemInscription,
			JMenuItem itemSimulation,
			JMenuItem itemResultatRegate,
			JMenuItem itemInformation) {
		
		this.frame = frame;
		
		this.contenuAccueil = contenuAccueil;
		this.contenuCreationRegate = contenuCreationRegate;
		this.contenuInscription = contenuInscription;
		this.contenuSimulation = contenuSimulation;
		this.contenuResultat = contenuResultat;
		this.contenuInformation = contenuInformation;
		this.itemCreationRegate = itemCreationRegate;
		this.itemInscription = itemInscription;
		this.itemSimulation = itemSimulation;
		this.itemResultatRegate = itemResultatRegate;
		this.itemInformation = itemInformation;
		
		this.regateControleur = regateControleur;
		this.inscriptionControleur = inscriptionControleur;
		this.classementControleur = classementControleur;
		this.resultatControleur = resultatControleur;
	}
	
	
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

			this.frame.setContentPane(this.contenuResultat);
			this.frame.setVisible(true);
			
		}else if(source.equals(this.itemSimulation)) {
			this.classementControleur.updateComponent();
			
			this.frame.setContentPane(this.contenuSimulation);
			this.frame.setVisible(true);
			
		}else if(source.equals(this.itemInformation)) {
			this.frame.setContentPane(this.contenuInformation);
			this.frame.setVisible(true);
		}else {
			System.out.println("Elément de menu [" + e.getActionCommand()
	         + "] utilisé.");	
		}	 		
	}
}
