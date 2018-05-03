package fr.tonybloc.exceptions;

/**
 * Exception : Aucune ligne selectionné dans une JTable
 * @author Tony
 *
 */
public class ExceptionAucuneLigneSelectionne extends Exception{
	private String message;
	
	/**
	 * Crée une instance de la classe ExceptionAucuneLigneSelectionne
	 */
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
