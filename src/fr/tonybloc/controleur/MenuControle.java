package fr.tonybloc.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.tonybloc.dao.DAOFactory;
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
	
	JComboBox cbChoixRegate;
	
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
			JPanel contenuCreationRegate,
			JPanel contenuInscription,
			JComboBox cbChoixRegate,
			JMenuItem itemCreationRegate, 
			JMenuItem itemInscription,
			JMenuItem itemSimulation,
			JMenuItem itemResultatRegate,
			JMenuItem itemAide,
			JMenuItem itemInformation) {
		
		this.frame = frame;
		this.contenuCreationRegate = contenuCreationRegate;
		this.contenuInscription = contenuInscription;
		
		this.itemCreationRegate = itemCreationRegate;
		this.itemInscription = itemInscription;
		this.itemSimulation = itemSimulation;
		this.itemResultatRegate = itemResultatRegate;
		this.itemAide = itemAide;
		this.itemInformation = itemInformation;
		
		this.cbChoixRegate = cbChoixRegate;
		
	}
	/**
	 * Evenement
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == this.itemCreationRegate) {
			
			this.frame.setContentPane(this.contenuCreationRegate);
			this.frame.setVisible(true);
		
		}else if(source == this.itemInscription) {
			
			this.cbChoixRegate.setModel(new ModelComboBoxRegates());
			this.frame.setContentPane(this.contenuInscription);			
			this.frame.setVisible(true);
			
		}else if(source == this.itemResultatRegate) {
			
		}else if(source == this.itemSimulation) {
			
		}else if(source == this.itemAide) {
			
		}else if(source == this.itemInformation) {
			
		}else {
			System.out.println("Elément de menu [" + e.getActionCommand()
	         + "] utilisé.");	
		}
		 		
	}
	
	public void AfficheCreateionRegatePanel() {
		
	}

}
