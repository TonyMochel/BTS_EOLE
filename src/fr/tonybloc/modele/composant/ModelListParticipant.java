package fr.tonybloc.modele.composant;

import java.util.ArrayList;
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
 * Model de la JTable : Liste des Participants
 * @author Tony
 *
 */
public class ModelListParticipant extends AbstractTableModel {

	/** Regate Handler */
	private RegateDAO regateManager;
	/** Voilier Handler */
	private VoilierDAO participantManager;
	/** Classement Handler */
	private ClassementDAO classementManager;
	
	/** Données de la JTable */
	private List<Voilier> participants;
	/** En-tête de la JTable */
	private final String[] entetes = { "ID_VOILIER","NOM_SKIPPEUR","PRENOM_SKIPPEUR","NOM_VOILIER","CATEGORIE","RATING"};
	
	public final static int ID_VOILIER = 0;
	public final static int NOM_SKIPPEUR = 1;
	public final static int PRENOM_SKIPPEUR = 2;
	public final static int NOM_VOILIER = 3;
	public final static int CATEGORIE = 4;
	public final static int RATING = 5;
	
	/**
	 * Crée une instance de la classe 'ModelListParticipant'
	 */
	public ModelListParticipant() {
		super();
		regateManager = DAOFactory.getRegateDAO();
		participantManager = DAOFactory.getVoilierDAO();
		classementManager = DAOFactory.getClassementDAO();
		
		participants = new ArrayList<Voilier>();	
	}
	
	/**
	 * Cherche le nombre de colonne dans la Jtable
	 * @return int
	 */
	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}

	/**
	 * Cherche le nombre d'éléments dans la liste
	 * @return int
	 */
	@Override
	public int getRowCount() {
		return this.participants.size();
	}
	
	/**
	 * Cherche le nom de la colonne
	 * @param columnIndex : index de la colonne
	 * @return String
	 */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	
	/**
	 * Cherche une donnée d'un participant selectionné
	 * @param rowIndex : indice de la ligne
	 * @param columnIndex : indice de la colonne
	 * @return Object
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Voilier voilier = participants.get(rowIndex);
		
		switch (columnIndex) 
		{
			case ID_VOILIER:
				return voilier.getId();
			case NOM_SKIPPEUR:
				return voilier.getNomSkippeur();
			case PRENOM_SKIPPEUR:
				return voilier.getPrenomSkippeur();
			case NOM_VOILIER:
				return voilier.getNomVoilier();
			case CATEGORIE:
				return voilier.getCategorie();
			case RATING:
				return voilier.getRating();
			default:
				return null;
		}
	}
	
	/**
	 * Ajoute un participant à la JTable
	 * @param participant : voilier cible
	 * @param regate : regate cible
	 */
	public void addParticipant(Voilier participant, Regate regate) {
		
		participantManager.create(participant);
		
		Voilier lastParticipant = participantManager.findLastVoilierInserted();
		this.participants.add(lastParticipant);
		
		this.classementManager.create( new Classement( lastParticipant, regate ));
		
		fireTableRowsInserted(participants.size()-1, participants.size() -1);
	}
	/**
	 * Supprime un participant de la JTable
	 * @param rowIndex : index de la ligne
	 * @param regate : regate cible
	 */
	public void removeParticpant(int rowIndex, Regate regate) {
		
		this.classementManager.delete( new Classement(participants.get(rowIndex), regate) );
		this.participants.remove(rowIndex);
		
		fireTableRowsDeleted(rowIndex, rowIndex);	
	}
	
	/**
	 * Met à jour les données contenu dans la JTable
	 * @param regate : regate cible
	 */
	public void updateTables(Regate regate) {
		if(regate == null) {
			this.participants = new ArrayList<Voilier>();
		}else {
			this.participants = regateManager.getAllParticipant(regate);
		}		
		fireTableDataChanged();
	}
}
