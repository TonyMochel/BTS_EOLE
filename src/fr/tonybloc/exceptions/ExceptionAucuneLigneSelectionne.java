package fr.tonybloc.exceptions;

public class ExceptionAucuneLigneSelectionne extends Exception{
	private String message;
	
	public ExceptionAucuneLigneSelectionne() {
		this.message = "Aucune ligne n'a été séléctionné";
	}
	
	/**
	 * Retourne le message d'exception
	 */
	public String getMessage() {
		return message;
	}
}
