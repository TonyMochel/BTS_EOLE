package fr.tonybloc.dao;

import java.sql.Connection;
import java.util.List;


/**
 * 
 * @author Tony
 *
 * @param <T>
 */
public abstract class DAO<T> {
	/**
	 * variable de connexion
	 */
	public Connection connect = null;
	
	/**
	 * Connection à une base de donnée
	 * @param con
	 */
	public DAO(Connection con) {
		this.connect = con;
	}
	
	/**
	 * Méthode de création
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean create(T obj);
	
	/**
	 * Méthode de suppression
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean delete(T obj);
	
	/**
	 * Méthode de mise à jour
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean update(T obj);
	
	/**
	 * Méthode de recherche spécifique
	 * @param id
	 * @return T
	 */
	public abstract T find(int id);
	
	/**
	 * Methode de recherce globale
	 * @return List<T>
	 */
	public abstract List<T> findAll();
}
