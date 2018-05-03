package fr.tonybloc.exceptions;

/**
 * Exception : Il y a trop de participants dans une régate
 * @author Tony
 *
 */
public class ExceptionTropDeParticipants extends Exception{
	private String message;
	
	/**
	 * Crée une instance de la classe ExceptionTropDeParticipants
	 */
	public ExceptionTropDeParticipants() {
		this.message = "Trop de personnes participent à la régate ! (max 20)";
	}
	
	/**
	 * Retourne le message d'exception
	 */
	public String getMessage() {
		return message;
	}
	
}