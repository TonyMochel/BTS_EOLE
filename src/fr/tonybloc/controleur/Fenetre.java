package fr.tonybloc.controleur;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.tonybloc.dao.*;
import fr.tonybloc.dao.implement.*;
import fr.tonybloc.modele.*;
import fr.tonybloc.outils.JNumberField;
import fr.tonybloc.vue.*;

public class Fenetre extends JFrame {

	private JFrame eoleApplication;
	
	private JPanel panelCreationRegate;
	private JPanel panelInscription;
	private JPanel panelSimulation;
	private JPanel panelResultat;
	private JPanel panelAide;
	private JPanel panelInformation;
	
	Menu menuPrincipal;
	VueCreationRegate vueCreationRegate;
	VueInscription vueInscription;
	
	
	public Fenetre() {
		
		this.eoleApplication = new JFrame("Test de menu");
		
		this.menuPrincipal = new Menu();
		this.vueCreationRegate = new VueCreationRegate();
		this.vueInscription = new VueInscription();
		
		this.panelCreationRegate = vueCreationRegate.getContent();
		this.panelInscription = vueInscription.getContent();
		
		
		// Controleur Regate
		RegateControleur regateControleur = new RegateControleur
				(
						this.vueCreationRegate.getVueListe().getModel(),
						this.vueCreationRegate.getVueFormulaire().getTfNomRegate(),
						this.vueCreationRegate.getVueFormulaire().getTfDistance(),
						this.vueCreationRegate.getVueFormulaire().getDatePicker(),
						this.vueCreationRegate.getVueFormulaire().getBtnCreation(),
						this.vueCreationRegate.getVueFormulaire().getBtnAnnuler(),
						this.vueCreationRegate.getVueListe().getBtnSupprimer(),
						this.vueCreationRegate.getVueListe().getBtnModifier(),
						this.vueCreationRegate.getVueListe().getListRegate()
				);
		
		this.vueCreationRegate.getVueFormulaire().getBtnCreation().addActionListener(regateControleur);
		this.vueCreationRegate.getVueListe().getBtnSupprimer().addActionListener(regateControleur);
		this.vueCreationRegate.getVueListe().getBtnModifier().addActionListener(regateControleur);
		this.vueCreationRegate.getVueFormulaire().getBtnAnnuler().addActionListener(regateControleur);
		
		InscriptionControleur inscriptionControleur = new InscriptionControleur
				(
						this.vueInscription.getVueListe().getModel(),
						this.vueInscription.getVueFormulaire().getTfNomVoilier(), 
						this.vueInscription.getVueFormulaire().getTfNomSkippeur(),
						this.vueInscription.getVueFormulaire().getTfPrenomSkippeur(),
						this.vueInscription.getVueFormulaire().getTfRating(),
						this.vueInscription.getVueFormulaire().getCbCategorie(),
						this.vueInscription.getVueFormulaire().getCbChoixRegate(),
						this.vueInscription.getVueFormulaire().getBtnInscription(),
						this.vueInscription.getVueListe().getBtnAnnuler(),
						this.vueInscription.getVueListe().getListParticipant(),
						this.vueInscription.getVueListe().getLabelInfo()
				);
		
		this.vueInscription.getVueFormulaire().getBtnInscription().addActionListener(inscriptionControleur);
		this.vueInscription.getVueFormulaire().getCbChoixRegate().addActionListener(inscriptionControleur);
		this.vueInscription.getVueFormulaire().getCbCategorie().addActionListener(inscriptionControleur);
		this.vueInscription.getVueListe().getBtnAnnuler().addActionListener(inscriptionControleur);
		
		// Controleur : Menu
		MenuControle menuControleur = new MenuControle
				(
						this.eoleApplication,
						this.panelCreationRegate,
						this.panelInscription,
						this.vueInscription.getVueFormulaire().getCbChoixRegate(),
						this.menuPrincipal.getItemCreationRegate(), 
						this.menuPrincipal.getItemInscription(),
						this.menuPrincipal.getItemSimulation(), 
						this.menuPrincipal.getItemResultatRegate(),
						this.menuPrincipal.getItemAide(), 
						this.menuPrincipal.getItemInformation()
				);
		
		this.menuPrincipal.getItemCreationRegate().addActionListener(menuControleur);
		this.menuPrincipal.getItemInscription().addActionListener(menuControleur);
		this.menuPrincipal.getItemSimulation().addActionListener(menuControleur);
		this.menuPrincipal.getItemResultatRegate().addActionListener(menuControleur);
		this.menuPrincipal.getItemAide().addActionListener(menuControleur);
		this.menuPrincipal.getItemInformation().addActionListener(menuControleur);
		
		
		// Parametrage de la fenetre
		this.eoleApplication.setResizable(false);
		this.eoleApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.eoleApplication.setContentPane(this.vueCreationRegate.getContent());
		this.eoleApplication.setJMenuBar(menuPrincipal);
		this.eoleApplication.setMinimumSize(new Dimension(1200, 700));
	    this.eoleApplication.pack();
	    this.eoleApplication.setVisible(true);
		
		
	}
	
	
	public static void main(String[] args) {
		new Fenetre();
		
		String str1 = "h";
		
		
		System.out.println(str1.substring(0, 1).toUpperCase()+str1.substring(1));
		/*
		JOptionPane.showMessageDialog(maFrame,
				"Course non créée \nLa distance n'est pas un décimal",
				"Avertissement",
		         JOptionPane.WARNING_MESSAGE);
		*/
		
		// Controleur : Creation Régate
		
		
		
	}
	

}
