package fr.tonybloc.modele;

import java.sql.Time;
/**
 * Objet Classement
 * @author Tony
 */
public class Classement {
	
	/**
	 * Identifiant du voilier
	 */
	private int idVoilier;
	/**
	 * Identifiant de la régate
	 */
	private int idRegate;
	/**
	 * Temps arrivé
	 */
	private Time tempsArrive;
	/**
	 * temps compensé
	 */
	private Time tempsCompense;
	
	
	/**
	 * Crée une instance de la classe 'Classement' : vide
	 */
	public Classement() {}
	/**
	 * Crée une instance de la classe 'Classement'
	 * @param idVoilier
	 * @param idRegate
	 * @param tempsArriver
	 * @param tempsCompense
	 */
	public Classement(int idVoilier, int idRegate, Time tempsArrive, Time tempsCompense) {
		this.idVoilier = idVoilier;
		this.idRegate = idRegate;
		this.tempsArrive = tempsArrive;
		this.tempsCompense = tempsCompense;
	}
	/**
	 * Crée une instance de la classe Classement
	 * @param idVoilier
	 * @param idRegate
	 */
	public Classement(int idVoilier, int idRegate) {
		this.idVoilier = idVoilier;
		this.idRegate = idRegate;
		this.tempsArrive = null;
		this.tempsCompense = null;
	}
	
	public int getIdVoilier() {
		return idVoilier;
	}
	public void setIdVoilier(int idVoilier) {
		this.idVoilier = idVoilier;
	}
	public int getIdRegate() {
		return idRegate;
	}
	public void setIdRegate(int idRegate) {
		this.idRegate = idRegate;
	}
	public Time getTempsArrive() {
		return tempsArrive;
	}
	public void setTempsArrive(Time tempsArrive) {
		this.tempsArrive = tempsArrive;
	}
	public Time getTempsCompense() {
		return tempsCompense;
	}
	public void setTempsCompense(Time tempsCompense) {
		this.tempsCompense = tempsCompense;
	}
	
	
	/**
	 * Description de l'objet Classement
	 */
	@Override
	public String toString() {
		return "{ "
				+ "'id Voilier' => " + this.idVoilier + ", "
				+ "'id Regate' => " + this.idRegate + ", "
				+ "'temps arrivé' => " + this.tempsArrive + ", "
				+ "'temps compensé' => " + this.tempsCompense + ", "
				+ "}";
		
	}
}
