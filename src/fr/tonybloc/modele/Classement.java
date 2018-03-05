package fr.tonybloc.modele;

import java.sql.Time;
import java.util.Date;

import fr.tonybloc.outils.Outils;
/**
 * Objet Classement
 * @author Tony
 */
public class Classement {
	
	/**
	 * Identifiant du voilier
	 */
	private Voilier voilier;
	/**
	 * Identifiant de la régate
	 */
	private Regate regate;
	/**
	 * Temps arrivé
	 */
	private Time tempsArrive;
	/** Temps compensé */
	private Time tempsCompense;
	/** Rang du participant */
	private int rang;
	
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
	public Classement(Voilier voilier, Regate regate, Time tempsArrive, Time tempsCompense) {
		this.voilier = voilier;
		this.regate = regate;
		this.tempsArrive = tempsArrive;
		this.tempsCompense = tempsCompense;
		this.rang = -1;
	}
	/**
	 * Crée une instance de la classe Classement
	 * @param idVoilier
	 * @param idRegate
	 */
	public Classement(Voilier voilier, Regate idRegate) {
		this.voilier = voilier;
		this.regate = idRegate;
		this.tempsArrive = null;
		this.tempsCompense = null;
		this.rang = -1;
	}
	
	public Voilier getVoilier() {
		return voilier;
	}	
	public Regate getRegate() {
		return regate;
	}
	public int getRang() {
		return rang;
	}
	public void setRang(int rang) {
		this.rang = rang;
	}
	public Time getTempsArrive() {
		return tempsArrive;
	}
	public void setTempsArrive(Time tempsArrive) {
		this.tempsArrive = tempsArrive;
		
		if(tempsArrive == null) {
			this.tempsCompense = null;
		}else if(tempsArrive.equals( Time.valueOf("00:00:00") )) {
			this.tempsCompense = tempsArrive;
		}else {
			this.tempsCompense = calculeTempCompense();
		}
		
	}
	public Time getTempsCompense() {
		return tempsCompense;
	}
	
	/**
	 * Calcule le temps d'arriver avec l'handicap
	 * @return
	 */
	private Time calculeTempCompense() {
		Date dt = getTempsArrive();
		
		int secondTempArriver = Outils.convertHTStoS(dt);
		System.out.println("Seconde :" + secondTempArriver);
		int handicap = (int) Math.round( (5143 / (Math.sqrt(this.getVoilier().getRating() + 3.5)) * this.getRegate().getDistance()));
		System.out.println("handicap :" + handicap);
		
		Time tempCompense = Time.valueOf(Outils.convertHMS(secondTempArriver + handicap));
		System.out.println("Temp : " + tempCompense);

		return tempCompense;
	}
	
	
	/**
	 * Description de l'objet Classement
	 */
	@Override
	public String toString() {
		return "{ "
				+ "'id Voilier' => " + this.voilier.getId() + ", "
				+ "'id Regate' => " + this.regate.getId() + ", "
				+ "'temps arrivé' => " + this.tempsArrive + ", "
				+ "'temps compensé' => " + this.tempsCompense + ", "
				+ "}";
		
	}
}
