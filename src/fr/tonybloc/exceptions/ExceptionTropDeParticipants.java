package fr.tonybloc.exceptions;

public class ExceptionTropDeParticipants extends Exception{
	private String message;
	
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