package fr.tonybloc.dao;

import java.sql.Connection;
import java.util.List;


/**
 * Classe DAO : Definie la signature des méthodes (CRUD)
 * @author Tony
 * @param <T> : un type d'objet
 */
public abstract class DAO<T> {
	/**
	 * variable de connexion
	 */
	public Connection connect = null;
	
	/**
	 * Connection à une base de données
	 * @param con : connexion à la BDD
	 */
	public DAO(Connection con) {
		this.connect = con;
	}
	
	/**
	 * Méthode de création
	 * @param obj : un objet
	 * @return boolean
	 */
	public abstract boolean create(T obj);
	
	/**
	 * Méthode de suppression
	 * @param obj : un objet
	 * @return boolean
	 */
	public abstract boolean delete(T obj);
	
	/**
	 * Méthode de mise à jour
	 * @param obj : un objet
	 * @return boolean
	 */
	public abstract boolean update(T obj);
	
	/**
	 * Méthode de recherche spécifique
	 * @param id : identifien d'un objet
	 * @return T
	 */
	public abstract T find(int id);
	
	/**
	 * Méthode de recherche globale
	 * @return List<T>
	 */
	public abstract List<T> findAll();
}
