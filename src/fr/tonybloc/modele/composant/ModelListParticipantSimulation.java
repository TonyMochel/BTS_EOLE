package fr.tonybloc.modele.composant;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.ClassementDAO;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.dao.implement.VoilierDAO;
import fr.tonybloc.modele.Classement;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.Voilier;
/**
 * Model de la JTable : Liste des Participants (Simulation)
 * @author Tony
 *
 */
public class ModelListParticipantSimulation extends AbstractTableModel{

	/** Classement Handler */
	private ClassementDAO classementManager;
	/** Donnée de la JTable */
	private List<Classement> donnee;
	/** En-tête de la JTable */
	private final String[] entetes = {"ID", "VOILIER", "NOM", "PRENOM", "TEMPS REEL", "TEMPS COMPENSE"};
	

	private final static int CLASSEMENT = 0;
	private final static int VOILIER = 1;
	
	private final static int ID = 0;
	private final static int NOM_VOILIER = 1;
	private final static int NOM_SKIPPEUR = 2;
	private final static int PRENOM_SKIPPEUR = 3; 
	private final static int TEMPS_REEL = 4;
	private final static int TEMPS_COMPENSE = 5;
	
	
	/**
	 * Crée une instance de la class ModelListParticipantSimulation
	 */
	public ModelListParticipantSimulation() {
		super();
		this.classementManager = DAOFactory.getClassementDAO();
		this.donnee = new ArrayList<Classement>();
		
	}
	
	/**
	 * Met à jours les donnée de la JTable
	 * @param regate
	 */
	public void updateTable(Regate regate) {		
		if(regate == null) {
			this.donnee = new ArrayList<Classement>();
		}else {
			this.donnee = classementManager.findAllParticipant(regate);
		}	
		fireTableDataChanged();
		
	}
	
	/**
	 * Retourne le nombre de column
	 * @return int
	 */
	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}

	/**
	 * Retourne le nombre d'éléments dans la liste
	 * @return int
	 */
	@Override
	public int getRowCount() {
		return this.donnee.size();
	}

	/**
	 * Retourne le nom de la colonne
	 * @return String
	 */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	
	/**
	 * Retourn une donnée d'un participant selectionner
	 * @param rowIndex : indice de la ligne
	 * @param columnIndex : indice de la colonne
	 * @return Object
	 */
	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		
		Classement classement = this.donnee.get(rowIndex);
		Voilier voilier = classement.getVoilier();
		
		switch(colIndex) {
			case ID :
				return voilier.getId();
			case NOM_VOILIER:
				return voilier.getNomVoilier();
			case NOM_SKIPPEUR:
				return voilier.getNomSkippeur();
			case PRENOM_SKIPPEUR:
				return voilier.getPrenomSkippeur();
			case TEMPS_REEL:
				Time tempsArriver = classement.getTempsArrive();
				return tempsArriver;
			case TEMPS_COMPENSE:
				Time tempsCompense = classement.getTempsCompense();
				return tempsCompense;
			default: 
				return null;
		}
		
	}
	
	/**
	 * Met à jour le TempArriver
	 * @param rowIndex
	 * @param sTime
	 */
	public void updateTempArriver(int rowIndex, String sTime) {
		Time tempArriver;
		if(sTime == null) {
			tempArriver = null;
		}else {
			tempArriver = Time.valueOf(sTime);
		}
		
		Classement classement = donnee.get(rowIndex);
		classement.setTempsArrive( tempArriver );
		
		this.classementManager.update(classement);
		
		fireTableRowsUpdated(rowIndex, rowIndex);
	}
	
	/**
	 * Verifie, que tous les participants sont arrivés
	 * @return boolean
	 */
	public boolean tousLesParticipantSontArrive() {
		
		boolean estArrive = true;
		Iterator iter = donnee.listIterator();
		
		while(iter.hasNext()) {
			
			Classement classement = (Classement) iter.next();
			if(classement.getTempsArrive() == null) {
				estArrive = false;
			}
		}
		return estArrive;
	}
	public void videDonnee() {
		this.donnee = new ArrayList<Classement>();
	}
}
