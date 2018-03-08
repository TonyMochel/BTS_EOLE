package fr.tonybloc.vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Menu de l'application
 * @author Tony
 *
 */
public class Menu extends JMenuBar{
	
	JMenuItem itemCreationRegate;
	JMenuItem itemInscription;
	
	JMenuItem itemSimulation;
	JMenuItem itemResultatRegate;
	
	JMenuItem itemAide;
	JMenuItem itemInformation;
	
	private final Font police = new Font("Arial", Font.PLAIN, 14);
	private final Font policeSubMenu = new Font("Arial", Font.PLAIN, 12);
	
	/**
	 * Menu de l'application
	 */
	public Menu() {
		
		/*
		 * Menu Création  
		 */
		JMenu menuCreation = new JMenu("Création");
		this.itemCreationRegate = new JMenuItem("Création Régate", 'R');
		this.itemInscription 	= new JMenuItem("Inscription", 'I');
		
		menuCreation.setFont(police);
		this.itemCreationRegate.setFont(policeSubMenu);
		this.itemInscription.setFont(policeSubMenu);
		
		this.itemCreationRegate.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit()
		        .getMenuShortcutKeyMask(), false));
		this.itemInscription.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit()
		        .getMenuShortcutKeyMask(), false));
		
	
		menuCreation.add(this.itemCreationRegate);
		menuCreation.add(this.itemInscription);
		
		
		/*
		 * Menu Simulation  
		 */
		JMenu menuSimulation = new JMenu("Simulation");
		this.itemSimulation = new JMenuItem("Simulation", 'S');
		this.itemResultatRegate = new JMenuItem("Résultat", 'R');
		
		menuSimulation.setFont(police);
		this.itemSimulation.setFont(policeSubMenu);
		this.itemResultatRegate.setFont(policeSubMenu);
		
		this.itemSimulation.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit()
		        .getMenuShortcutKeyMask(), false));
		this.itemResultatRegate.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit()
		        .getMenuShortcutKeyMask(), false));
		
		menuSimulation.add(this.itemSimulation);
		menuSimulation.add(this.itemResultatRegate);
		
		
		/*
		 * Menu A Propos  
		 */
		JMenu menuAPropos = new JMenu("A Propos");
		this.itemAide = new JMenuItem("Aide", 'H');
		this.itemInformation = new JMenuItem("Information", 'I');
		
		menuAPropos.setFont(police);
		this.itemAide.setFont(policeSubMenu);
		this.itemInformation.setFont(policeSubMenu);
		
		this.itemAide.setAccelerator(KeyStroke.getKeyStroke('H', Toolkit.getDefaultToolkit()
		        .getMenuShortcutKeyMask(), false));
		this.itemInformation.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit()
		        .getMenuShortcutKeyMask(), false));				
		menuAPropos.add(this.itemAide);
		menuAPropos.add(this.itemInformation);
		
		/*
		 * Ajoute au JMenuBar les éléments du menu  
		 */
		add(menuCreation);
		add(menuSimulation);
		add(menuAPropos);
		
	}
	
	public JMenuItem getItemCreationRegate() {
		return itemCreationRegate;
	}
	public JMenuItem getItemInscription() {
		return itemInscription;
	}
	public JMenuItem getItemSimulation() {
		return itemSimulation;
	}
	public JMenuItem getItemResultatRegate() {
		return itemResultatRegate;
	}
	public JMenuItem getItemAide() {
		return itemAide;
	}
	public JMenuItem getItemInformation() {
		return itemInformation;
	}
}
