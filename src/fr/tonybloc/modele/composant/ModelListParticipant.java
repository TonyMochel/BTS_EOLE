package fr.tonybloc.modele.composant;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.ClassementDAO;
import fr.tonybloc.dao.implement.VoilierDAO;
import fr.tonybloc.modele.Classement;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.Voilier;

public class ModelListParticipant extends AbstractTableModel {

	
	
	private VoilierDAO participantManager;
	private ClassementDAO classementManager;
	
	private List<Voilier> participants;
	private final String[] entetes = { "ID_VOILIER","NOM_SKIPPEUR","PRENOM_SKIPPEUR","NOM_VOILIER","CATEGORIE","RATING"};
	
	public final static int ID_VOILIER = 0;
	public final static int NOM_SKIPPEUR = 1;
	public final static int PRENOM_SKIPPEUR = 2;
	public final static int NOM_VOILIER = 3;
	public final static int CATEGORIE = 4;
	public final static int RATING = 5;
	
	/**
	 * Crée une instance de la classe ModelListParticipant
	 */
	public ModelListParticipant() {
		super();
		participantManager = DAOFactory.getVoilierDAO();
		classementManager = DAOFactory.getClassementDAO();
		
		participants = new ArrayList<Voilier>();	
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
		return this.participants.size();
	}
	
	/**
	 * Retourn le nom de la colonne
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
	 * Ajoute un Participant à la liste
	 * @param participant
	 * @param regate
	 */
	public void addParticipant(Voilier participant, Regate regate) {
		
		participantManager.create(participant);
		
		Voilier lastParticipant = participantManager.findLastVoilierInserted();
		this.participants.add(lastParticipant);
		
		this.classementManager.create( new Classement( lastParticipant.getId(), regate.getId() ));
		
		fireTableRowsInserted(participants.size()-1, participants.size() -1);
	}
	/**
	 * Supprime un Participant de la liste
	 * @param rowIndex
	 */
	public void removeParticpant(int rowIndex, Regate regate) {
		
		this.classementManager.delete( new Classement(participants.get(rowIndex).getId(), regate.getId()) );
		this.participants.remove(rowIndex);
		
		fireTableRowsDeleted(rowIndex, rowIndex);	
	}
	
	public void updateTables(List<Voilier> participants) {
		this.participants = participants;
		fireTableDataChanged();
	}
	
	
}
