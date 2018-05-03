package fr.tonybloc.modele.composant;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.modele.Regate;
/**
 * Model de la JTable : Liste des régate
 * @author Tony
 *
 */
public class ModelListRegate extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/** Regate Handler */
	private RegateDAO regateManager;
	/** Données de la JTable */
	private List<Regate> regates;
	/** En-tête de la JTable */
	private final String[] entetes = { "ID_REGATE", "LIBELLE", "DISTANCE", "DATE_DEPART", "CLOTURE"};
	
	public final static int ID_REGATE = 0;
	public final static int LIBELLE = 1;
	public final static int DISTANCE = 2;
	public final static int DATE_DEPART = 3;
	public final static int CLOTURE = 4;
	
	
	/**
	 * Crée une instance de la classe ModelListRegate
	 */
	public ModelListRegate() {
		super();		
		this.regateManager = DAOFactory.getRegateDAO();
		this.regates = regateManager.findAll();
	}
	
	/**
	 * Cherche le nombre de colonne
	 * @return int
	 */
	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}
	/**
	 * Retourne le nombre d'élement dans la JTable
	 * @return int
	 */
	@Override
	public int getRowCount() {
		return this.regates.size();
	}
	
	/**
	 * Cherche le nom de la colonne
	 */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	/**
	 * Retourne une donnée d'une régate selectionner
	 * @param rowIndex
	 * @param columnIndex
	 * @return Object
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Regate regate = regates.get(rowIndex);
		
		switch(columnIndex) 
		{
			case ID_REGATE: 
				return regate.getId();
			case LIBELLE:
				return regate.getIntituler();
			case DISTANCE:
				return regate.getDistance();
			case DATE_DEPART:
				return regate.getDate_depart_string();
			case CLOTURE:
				return regate.isCloture();
			default:
				return null;
		}
	}
	
	
	/**
	 * Ajoute une Régate à la liste
	 * @param regate
	 */
	public void addRegate(Regate regate) {
		this.regateManager.create(regate);
		this.regates.add(this.regateManager.findLastRegateInserted());
		
		fireTableRowsInserted(regates.size()-1, regates.size() -1);
		
	}
	/**
	 * Supprime une Régate de la liste
	 * @param rowIndex
	 */
	public void removeRegate(int rowIndex) {
		
		this.regateManager.delete(regates.get(rowIndex));
		this.regates.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);		
	}
	
	/**
	 * Mise à jour d'une Régate de la liste
	 * @param regate
	 * @param rowIndex
	 */
	public void updateRegate(Regate regate, int rowIndex) {
		this.regateManager.update(regate);
		this.regates.set(rowIndex, regate);
		fireTableRowsUpdated(rowIndex, rowIndex);
	}
	
	/**
	 * Mes à jour toutes les données de la table
	 */
	public void updateTable() {
		this.regates = this.regateManager.findAll();
		fireTableDataChanged();
	}

}
