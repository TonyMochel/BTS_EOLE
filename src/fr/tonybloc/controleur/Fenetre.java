package fr.tonybloc.controleur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Time;
import java.time.LocalTime;

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
	VueSimulation vueSimulation;
	
	public Fenetre() {
		
		this.eoleApplication = new JFrame("Test de menu");
		
		this.menuPrincipal = new Menu();
		this.vueCreationRegate = new VueCreationRegate();
		this.vueInscription = new VueInscription();
		this.vueSimulation = new VueSimulation();
		
		this.panelCreationRegate = vueCreationRegate.getContent();
		this.panelInscription = vueInscription.getContent();
		this.panelSimulation = vueSimulation.getContent();
		
		// Controleur : Création de Regate
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
		
		// Controleur : Inscription des participants
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
		
		// Controleur : Classement des participants
		ClassementControleur classementControleur = new ClassementControleur(
				this.panelSimulation,
				this.vueSimulation.getVueTableau().getModelListParticipant(),
				this.vueSimulation.getVueCommande().getBtnCloture(),
				this.vueSimulation.getVueTableau().getBtnArriver(),
				this.vueSimulation.getVueTableau().getBtnModifier(),
				this.vueSimulation.getVueTableau().getBtnAbandonner(),
				this.vueSimulation.getVueCommande().getBtnArreteChrono(),
				this.vueSimulation.getVueCommande().getBtnRecommencer(),
				this.vueSimulation.getVueCommande().getBtnDemarreChrono(),
				this.vueSimulation.getVueTableau().getCbChoixRegate(),
				this.vueSimulation.getVueTableau().getListParticipant(),
				this.vueSimulation.getVueCommande().getLbChrono(),
				this.vueSimulation.getVueCommande().getChrono()
				);
		this.vueSimulation.getVueCommande().getBtnCloture().addActionListener(classementControleur);
		this.vueSimulation.getVueTableau().getBtnArriver().addActionListener(classementControleur);
		this.vueSimulation.getVueTableau().getBtnModifier().addActionListener(classementControleur);
		this.vueSimulation.getVueTableau().getBtnAbandonner().addActionListener(classementControleur);
		this.vueSimulation.getVueTableau().getCbChoixRegate().addActionListener(classementControleur);
		this.vueSimulation.getVueCommande().getBtnArreteChrono().addActionListener(classementControleur);
		this.vueSimulation.getVueCommande().getBtnRecommencer().addActionListener(classementControleur);
		this.vueSimulation.getVueCommande().getBtnDemarreChrono().addActionListener(classementControleur);
		
		// Controleur : Menu
		MenuControle menuControleur = new MenuControle
				(
						this.eoleApplication,
						this.panelCreationRegate,
						this.panelInscription,
						this.panelSimulation,
						this.vueInscription.getVueFormulaire().getCbChoixRegate(),
						this.vueSimulation.getVueTableau().getCbChoixRegate(),
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
		
		
		/*
		JOptionPane.showMessageDialog(maFrame,
				"Course non créée \nLa distance n'est pas un décimal",
				"Avertissement",
		         JOptionPane.WARNING_MESSAGE);
		*/
		
		// Controleur : Creation Régate
		
		
		
	}
	

}