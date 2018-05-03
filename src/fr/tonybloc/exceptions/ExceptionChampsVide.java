package fr.tonybloc.exceptions;

/**
 * Exception : Champs vide 
 * @author Tony
 *
 */
public class ExceptionChampsVide extends Exception{
	private String message;
	
	/**
	 * Crée une instance de la classe ExceptionCampsVide
	 */
	public ExceptionChampsVide() {
		this.message = "Un ou plusieurs champs de saisie sont vides !";
	}
	
	/**
	 * Retourne le message d'exception
	 */
	public String getMessage() {
		return message;
	}
	
}
