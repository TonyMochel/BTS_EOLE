package fr.tonybloc.dao;

import java.sql.DriverManager;

import javax.swing.JOptionPane;

import java.sql.Connection;

/**
 * Classe de connexion
 * @author : Anthony Mochel
 */
public class EoleConnect {
	
	/**
	 * Url de connexion
	 */
	private String url = "jdbc:mysql://localhost/eole?zeroDateTimeBehavior=convertToNull";
	/**
	 * Login de connexion
	 */
	private String username = "root";
	/**
	 * Mots de passe de connexion
	 */
	private String password = "";
	/**
	 * Objet Connexion
	 */
	private static Connection connect = null;
	
	
	/**
	 * Initialise une connexion à la database : eole
	 */
	private EoleConnect(){
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(this.url, this.username, this.password);
		} 
		catch (Exception  e) 
		{
			JOptionPane.showMessageDialog(null, "Aucune Connexion à la base de donnée");
		}
	}
	
	/**
	 * Retourne l'unique instance de la class de connexion
	 */
	public static Connection getInstance() {
		
		if(connect == null) 
		{
			new EoleConnect();
			System.out.println("CREATION DE LA CONNEXION");
		}

		System.out.println("RETOURN LA CONNEXION");
		return connect;
	}
	
}
