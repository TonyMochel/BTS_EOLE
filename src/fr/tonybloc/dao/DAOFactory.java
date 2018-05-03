package fr.tonybloc.dao;

import java.sql.Connection;
import fr.tonybloc.dao.implement.*;

/**
 * La classe 'DAOFactory' gère l'instanciation des classes DAO
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
		try {
			return new VoilierDAO(connect);
		}catch(Exception exp) {
			return null;
		}		
	}
	
	/**
	 * Retourne un object Regate interagissant avec la bdd
	 * @return DAO
	 */
	public static RegateDAO getRegateDAO() {
		try {
			return new RegateDAO(connect);	
		}catch(Exception exp) {
			return null;
		}		
	}
	
	/**
	 * Retourne un object Categorie interagissant avec la bdd
	 * @return DAO
	 */
	public static CategorieDAO getCategorieDAO() {
		try {
			return new CategorieDAO(connect);	
		}catch(Exception exp) {
			return null;
		}		
	}
	
	/**
	 * Retourne un object Classement interagissant avec la bdd
	 * @return DAO
	 */
	public static ClassementDAO getClassementDAO() {
		try {
			return new ClassementDAO(connect);	
		}catch(Exception exp) {
			return null;
		}		
	}
}
