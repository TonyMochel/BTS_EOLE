package fr.tonybloc.dao;

import java.sql.Connection;
import fr.tonybloc.dao.implement.*;

/**
 * La classe 'DAOFactory' gère l'instanciation des classes DAO
 * 
 * @author Tony
 *
 */
public class DAOFactory {
	
	/**
	 * Connexion à la base de donnée : eole
	 */
	public static final Connection connect = EoleConnect.getInstance();
	
	/**
	 * Retourne un object Voilier interagissant avec la bdd
	 * @return DAO
	 */
	public static VoilierDAO getVoilierDAO() {
		return new VoilierDAO(connect);
	}
	
	/**
	 * Retourne un object Regate interagissant avec la bdd
	 * @return DAO
	 */
	public static RegateDAO getRegateDAO() {
		return new RegateDAO(connect);
	}
	
	/**
	 * Retourne un object Categorie interagissant avec la bdd
	 * @return DAO
	 */
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAO(connect);
	}
	
	/**
	 * Retourne un object Classement interagissant avec la bdd
	 * @return DAO
	 */
	public static ClassementDAO getClassementDAO() {
		return new ClassementDAO(connect);
	}
}
