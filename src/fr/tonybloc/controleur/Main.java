package fr.tonybloc.controleur;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.tonybloc.dao.*;
import fr.tonybloc.dao.implement.*;
import fr.tonybloc.modele.*;
import fr.tonybloc.vue.*;

public class Main {

	private JPanel panelCreationRegate;
	private JPanel panelInscription;
	private JPanel panelSimulation;
	private JPanel panelResultat;
	private JPanel panelAide;
	private JPanel panelInformation;
	
	
	
	public static void main(String[] args) {
		
		
		Menu menuPrincipal = new Menu();
		VueInscription vueInscription = new VueInscription();
		//VueCreationRegateFormulaire vueCreationRegateFormulaire = new VueCreationRegateFormulaire();
		//VueCreationRegateListe vueCreationRegateList = new VueCreationRegateListe();
		VueCreationRegate vueCreationRegate = new VueCreationRegate();
		
		
		/*
		JOptionPane.showMessageDialog(maFrame,
				"Course non créée \nLa distance n'est pas un décimal",
				"Avertissement",
		         JOptionPane.WARNING_MESSAGE);
		*/
		
		// Controleur : Creation Régate
		RegateControleur regateControleur = new RegateControleur
				(
						vueCreationRegate.getVueList().getModel(),
						vueCreationRegate.getVueFormulaire().getTfNomRegate(),
						vueCreationRegate.getVueFormulaire().getTfDistance(),
						vueCreationRegate.getVueFormulaire().getDatePicker(),
						vueCreationRegate.getVueFormulaire().getBtnCreation(),
						vueCreationRegate.getVueFormulaire().getBtnAnnuler(),
						vueCreationRegate.getVueList().getBtnSupprimer(),
						vueCreationRegate.getVueList().getBtnModifier(),
						vueCreationRegate.getVueList().getListRegate()
				);
		
		vueCreationRegate.getVueFormulaire().getBtnCreation().addActionListener(regateControleur);
		vueCreationRegate.getVueList().getBtnSupprimer().addActionListener(regateControleur);
		vueCreationRegate.getVueList().getBtnModifier().addActionListener(regateControleur);
		vueCreationRegate.getVueFormulaire().getBtnAnnuler().addActionListener(regateControleur);
		
		
		// Controleur : Inscription 
		InscriptionControle inscriptionControleur = new InscriptionControle 
				(
						vueInscription.getTfNomVoilier(),
						vueInscription.getTfNomSkippeur(),
						vueInscription.getCbCategorie(), 
						vueInscription.getBtnCreation() 
				);
		
		vueInscription.getBtnCreation().addActionListener(inscriptionControleur);
		
		// Controleur : Menu
		MenuControle menuControleur = new MenuControle
				(
						menuPrincipal.getItemCreationRegate(), 
						menuPrincipal.getItemInscription(),
						menuPrincipal.getItemSimulation(), 
						menuPrincipal.getItemResultatRegate(),
						menuPrincipal.getItemAide(), 
						menuPrincipal.getItemInformation()
				);
		
		menuPrincipal.getItemCreationRegate()	.addActionListener(menuControleur);
		menuPrincipal.getItemInscription()		.addActionListener(menuControleur);
		menuPrincipal.getItemSimulation()		.addActionListener(menuControleur);
		menuPrincipal.getItemResultatRegate()	.addActionListener(menuControleur);
		menuPrincipal.getItemAide()				.addActionListener(menuControleur);
		menuPrincipal.getItemInformation()		.addActionListener(menuControleur);
		
		// VueCreationVoilier view = new VueCreationVoilier();
		// Voilier voilier = null;
		// VoilierControle controllerVoilier = new VoilierControle(voilier, view.getTfNomVoilier(), view.getTfNomSkippeur(), view.getCbCategorie());
		// view.getBtnCreation().addActionListener(controllerVoilier);
		
		//Voilier v = new Voilier(id, 2, "voilier test", "skippeur test", "483"))
		/*
		DAO<Voilier> voilierManager = DAOFactory.getVoilierDAO();
		Voilier voilier = voilierManager.find(1);
		System.out.println(voilier.toString());
		
		
		DAO<Categorie> categorieManager = DAOFactory.getCategorieDAO();
		Categorie categorie = categorieManager.find(1);
		System.out.println(categorie.toString());
		
		
		DAO<Regate> regateManager = DAOFactory.getRegateDAO();
		Regate regate = regateManager.find(1);
		System.out.println(regate.toString());
		
		ClassementDAO classementManager = DAOFactory.getClassementDAO();
		Classement classement = classementManager.find(1,1);
		System.out.println(classement.toString());
		*/
		
		JFrame frame = new JFrame("Test de menu");
		frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setJMenuBar(menuPrincipal);
	    frame.getContentPane().add(vueCreationRegate.getContent(), BorderLayout.CENTER);
	    frame.setMinimumSize(new Dimension(1200, 700));
	    frame.pack();
	    frame.setVisible(true);
	}
	

}
