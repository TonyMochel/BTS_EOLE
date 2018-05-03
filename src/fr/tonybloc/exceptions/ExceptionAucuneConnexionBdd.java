package fr.tonybloc.exceptions;

/**
 * Exception : Aucune connexion à une base de données trouvé
 * @author Tony
 *
 */
public class ExceptionAucuneConnexionBdd extends Exception{

	private String message;
	
	/**
	 * Crée une instance de la classe ExceptionAucuneConnexionBdd
	 */
	public ExceptionAucuneConnexionBdd() {
		this.message = "Aucune connexion à la base de donnée";
	}
	
	/**
	 * Retourne le message d'exception
	 */
	public String getMessage() {
		return message;
	}
}
