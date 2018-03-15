package fr.tonybloc.modele.composant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.ClassementDAO;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.dao.implement.VoilierDAO;
import fr.tonybloc.modele.Categorie;
import fr.tonybloc.modele.Classement;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.Voilier;
/**
 * Model de la JTable : Liste des résultat
 * @author Tony
 *
 */
public class ModelListResultat extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	/** Classement Handler */
	private ClassementDAO classementManager;
	/** Donnée de la JTable */
	private List<Classement> donnee;
	/** En-tête de la JTable */
	private String [] entetes = {"RANG", "VOILIER", "NOM", "PRENOM", "TEMPS REEL", "TEMPS COMPENSE"};
	
	private final static int RANG = 0; 
	private final static int VOILIER = 1;
	private final static int NOM = 2;
	private final static int PRENOM = 3;
	private final static int TEMPS_REEL = 4;
	private final static int TEMPS_COMPENSE = 5;
	
	/**
	 * Crée une instance de la class ModelListClassement
	 * 
	 * @param regate
	 * @param categorie
	 */
	public ModelListResultat() {
		super();
		
		this.classementManager = DAOFactory.getClassementDAO();
		
		this.donnee = new ArrayList<Classement>();
	}

	/**
	 * Retourne le nombre de column
	 * @return int
	 */
	@Override
	public int getColumnCount() {
		return entetes.length;
	}
	
	/**
	 * Retourne le nombre d'element dans la liste
	 * @return int
	 */
	@Override
	public int getRowCount() {
		return donnee.size();
	}

	/**
	 * Retourne une donnée du classement selectionner
	 * @param rowIndex
	 * @param columnIndex
	 * @return Object
	 */
	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		Classement classement = donnee.get(rowIndex);
		Voilier voilier = classement.getVoilier();
		switch (colIndex) 
		{
			case RANG:
				return classement.getRang();
			case VOILIER:
				return voilier.getNomVoilier();
			case NOM:
				return voilier.getNomSkippeur();
			case PRENOM:
				return voilier.getPrenomSkippeur();
			case TEMPS_REEL:
				return classement.getTempsArrive();
			case TEMPS_COMPENSE:
				return classement.getTempsCompense();
			default:
				return null;
		}
	}
	/**
	 * Retourn le nom de la colonne
	 * @return String
	 */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	/**
	 * Met à jour les donnée de la JTable
	 * @param regate
	 * @param categorie
	 */
	public void updateTable(Regate regate, Categorie categorie) {
		List<Classement> listClassement = classementManager.findClassementAt(regate, categorie);
		this.donnee = listClassement;
		
		Iterator iter = this.donnee.listIterator();
		int rang = 0;
		while(iter.hasNext()) {
			
			rang++;
			Classement classement = (Classement) iter.next();
			classement.setRang(rang);
		}
		
		fireTableDataChanged();
	}
	
	/**
	 * Mes à jour les donnée de la table
	 */
	public void updateTable() {
		this.donnee = new ArrayList<Classement>();
		fireTableDataChanged();
	}
}
