package fr.tonybloc.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.io.File;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.CategorieDAO;
import fr.tonybloc.dao.implement.ClassementDAO;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.dao.implement.VoilierDAO;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.composant.ModelListResultat;
import fr.tonybloc.outils.Outils;

public class ResultatControleur implements ActionListener{
	
	private final RegateDAO regateManager = DAOFactory.getRegateDAO();
	private final VoilierDAO voilierManager = DAOFactory.getVoilierDAO();
	private final ClassementDAO classementManager = DAOFactory.getClassementDAO();
	private final CategorieDAO categorieManager = DAOFactory.getCategorieDAO();
	
	private JTable listResultat1;
	private JTable listResultat2;
	private JTable listResultat3;
	private JTable listResultat4;
	
	private ModelListResultat modelListResultat1;
	private ModelListResultat modelListResultat2;
	private ModelListResultat modelListResultat3;
	private ModelListResultat modelListResultat4;
	
	private JComboBox<Regate> cbChoixRegateCloturer;
	private JButton btnTelechargerPDF;
	private JButton btnTelechargerCSV;
	private JButton btnImprimer;
	
	private Regate regateSelectionner;
	
	/**
	 * Crée une instance de la classe
	 * @param cbChoixRegateCloturer
	 * @param listResultat1
	 * @param listResultat2
	 * @param listResultat3
	 * @param listResultat4
	 * @param modelListResultat
	 * @param btnTelechargerPDF
	 * @param btnTelechargerCSV
	 * @param btnImprimer
	 */
	public ResultatControleur(
			JComboBox<Regate> cbChoixRegateCloturer,
			JTable listResultat1,
			JTable listResultat2,
			JTable listResultat3,
			JTable listResultat4,
			ModelListResultat modelListResultat1,
			ModelListResultat modelListResultat2,
			ModelListResultat modelListResultat3,
			ModelListResultat modelListResultat4,
			JButton btnTelechargerPDF,
			JButton btnTelechargerCSV,
			JButton btnImprimer			
			) {
		
		this.cbChoixRegateCloturer = cbChoixRegateCloturer;
		this.listResultat1 = listResultat1;
		this.listResultat2 = listResultat2;
		this.listResultat3 = listResultat3;
		this.listResultat4 = listResultat4;
		this.modelListResultat1 = modelListResultat1;
		this.modelListResultat2 = modelListResultat2;
		this.modelListResultat3 = modelListResultat3;
		this.modelListResultat4 = modelListResultat4;
		this.btnTelechargerPDF = btnTelechargerPDF;
		this.btnTelechargerCSV = btnTelechargerCSV;
		this.btnImprimer = btnImprimer;	
	}
	
	/**
	 * Modifier la JTables (affiche les participant à une regate)
	 */
	private void ActionSelectionnerRegate() {
		this.regateSelectionner = (Regate) cbChoixRegateCloturer.getSelectedItem();		
		this.modelListResultat1.updateTable(this.regateSelectionner, categorieManager.find(1));
		this.modelListResultat2.updateTable(this.regateSelectionner, categorieManager.find(2));
		this.modelListResultat3.updateTable(this.regateSelectionner, categorieManager.find(3));
		this.modelListResultat4.updateTable(this.regateSelectionner, categorieManager.find(4));
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if(source.equals(cbChoixRegateCloturer)) {
			ActionSelectionnerRegate();
		}
		else if(source.equals(btnImprimer)) {
			MessageFormat header = new MessageFormat("Classement des participants");
			MessageFormat footer = new MessageFormat("test");
			try {
				this.listResultat1.print(JTable.PrintMode.NORMAL,header, footer );
				
			}catch(java.awt.print.PrinterException e) {
				System.out.println("Erreur d'impression");
			}
		}else if(source.equals(btnTelechargerCSV)) {
			
			String cheminFichier = "./" + this.regateSelectionner.getId() + "" + this.regateSelectionner.getIntituler().replaceAll(" ", "_") + "" + this.regateSelectionner.getDate_depart_string() + ".csv";
			File fichierCSV = new File(cheminFichier);
			
			Outils.toExcel(this.listResultat1, this.listResultat2, this.listResultat3, this.listResultat4, this.regateSelectionner, fichierCSV);
		}
	}
}
