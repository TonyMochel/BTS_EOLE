package fr.tonybloc.controleur;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.CategorieDAO;
import fr.tonybloc.dao.implement.ClassementDAO;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.dao.implement.VoilierDAO;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.composant.ModelComboBoxRegates;
import fr.tonybloc.modele.composant.ModelListResultat;
import fr.tonybloc.outils.Outils;

public class ResultatControleur implements ActionListener{
	
	private final RegateDAO regateManager = DAOFactory.getRegateDAO();
	private final VoilierDAO voilierManager = DAOFactory.getVoilierDAO();
	private final ClassementDAO classementManager = DAOFactory.getClassementDAO();
	private final CategorieDAO categorieManager = DAOFactory.getCategorieDAO();
	
	private JPanel panelPrintClassement;
	
	private JPanel panelResultat;
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
			JPanel panelResultat,
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
			JButton btnImprimer	,
			JPanel panelPrintClassement
			) {
		this.panelResultat = panelResultat;
		
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
		this.panelPrintClassement = panelPrintClassement;
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
	
	/**
	 * Téléchage le fichier de résultat en .csv
	 */
	private void ActionTelechargerCsv() {
		
		if(this.regateSelectionner == null) {
			JOptionPane.showMessageDialog(this.panelResultat, "Aucun régate sélectionnée", "Avertissement", JOptionPane.WARNING_MESSAGE);
		}else {
			String cheminFichier = "./classement/" + this.regateSelectionner.getId() + "" + this.regateSelectionner.getIntituler().replaceAll(" ", "_") + "" + this.regateSelectionner.getDate_depart_string() + ".csv";
			File fichierCSV = new File(cheminFichier);
			
			Outils.toExcel(this.listResultat1, this.listResultat2, this.listResultat3, this.listResultat4, this.regateSelectionner, fichierCSV);
			JOptionPane.showMessageDialog(this.panelResultat, "Téléchargement éffectué", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/**
	 * Imprime la fiche de résultat
	 */
	private void ActionImprimer() {
		if(this.regateSelectionner == null) {
			JOptionPane.showMessageDialog(this.panelResultat, "Aucun régate sélectionnée", "Avertissement", JOptionPane.WARNING_MESSAGE);
		}else {			
		
			PrinterJob pj = PrinterJob.getPrinterJob();
			pj.setJobName(" Print Component ");
			pj.setPrintable (new Printable() {    
				public int print(Graphics pg, PageFormat pf, int pageNum){
					if (pageNum > 0){
						return Printable.NO_SUCH_PAGE;
					}
	
					Graphics2D g2 = (Graphics2D) pg;
					g2.translate(pf.getImageableX(), pf.getImageableY());
					panelPrintClassement.paint(g2);
					return Printable.PAGE_EXISTS;
			    }
			});
			if (pj.printDialog() == false) {
				return;
			}
			try {
				pj.print();
			} catch (PrinterException ex) {
				if(this.regateSelectionner == null) {
					JOptionPane.showMessageDialog(this.panelResultat, "Aucune imprimantes trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**
	 * ActionListener
	 * @param event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if(source.equals(cbChoixRegateCloturer)) {
			ActionSelectionnerRegate();
		}else if(source.equals(btnImprimer)) {
			ActionImprimer();
		}else if(source.equals(btnTelechargerCSV)) {
			ActionTelechargerCsv();
		}
	}
	
	/**
	 * Mes à jour les composants
	 */
	public void updateComponent() {
		this.cbChoixRegateCloturer.setModel(new ModelComboBoxRegates(ModelComboBoxRegates.REGATE_CLOSURE));
		this.regateSelectionner = null;
		this.modelListResultat1.updateTable();
		this.modelListResultat2.updateTable();
		this.modelListResultat3.updateTable();
		this.modelListResultat4.updateTable();
		
	}
}
