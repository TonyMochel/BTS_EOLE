package fr.tonybloc.exceptions;

public class ExceptionChampsVide extends Exception{
	private String message;
	
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
