package fr.tonybloc.modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Object Regate
 * @author Tony
 *
 */
public class Regate {

	/**
	 * Identifiant d'une régate
	 */
	private int id;
	/**
	 * Distance de la régate
	 */
	private double distance;
	
	/**
	 * Intituler de la régate
	 */
	private String intituler;
	
	/**
	 * Date de depart de la régate
	 */
	private String date_depart;
	
	/**
	 * Est-elle coloturé ?
	 */
	private boolean cloture = false;
	
	
	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Crée une instance de la classe 'Regate' : vide
	 */
	public Regate() {}
	
	/**
	 * Crée une instance de la classe 'Regate'
	 * @param intituler
	 * @param distance
	 * @param date_depart
	 * @param cloture
	 */
	public Regate(String intituler, double distance, Date date_depart, boolean cloture) {
		this.id = 0;
		this.intituler = intituler;
		this.distance = distance;
		this.date_depart = DATE_FORMAT.format(date_depart);
		this.cloture = cloture;
	}
	/**
	 * Crée une instance de la classe 'Regate'
	 * @param id
	 * @param intituler
	 * @param distance
	 * @param date_de_depart
	 * @param cloture
	 */
	public Regate(int id, String intituler, double distance, Date date_depart, boolean cloture) {
		this.id = id;
		this.intituler = intituler;
		this.distance = distance;
		this.date_depart = DATE_FORMAT.format(date_depart);
		this.cloture = cloture;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getIntituler() {
		return intituler;
	}

	public void setIntituler(String intituler) {
		this.intituler = intituler;
	}

	public Date getDate_depart_date() {
		try {
			return (Date) DATE_FORMAT.parse(date_depart);
		} catch (ParseException e) {
			return null;
		}
	}
	public String getDate_depart_string() {
		return this.date_depart;
	}

	public void setDate_depart(Date date_de_depart) {
		this.date_depart = DATE_FORMAT.format(date_depart);
	}
	public void setDate_depart(String date_de_depart) {
		this.date_depart = DATE_FORMAT.format(date_depart);
	}

	public boolean isCloture() {
		return cloture;
	}

	public void setCloture(boolean cloture) {
		this.cloture = cloture;
	}

	/**
	 * Description de l'objet Regate
	 */
	@Override
	public String toString() {
		return this.intituler + " du " + this.date_depart;
		
	}
}
