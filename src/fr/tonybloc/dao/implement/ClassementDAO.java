package fr.tonybloc.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.tonybloc.dao.DAO;
import fr.tonybloc.modele.Classement;

public class ClassementDAO extends DAO<Classement> {

	public ClassementDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Classement obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Classement obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Classement obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Classement find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Recherche un objet classement dans la base de donnée : eole
	 * @param idVoilier
	 * @param idRegate
	 * @return
	 */
	public Classement find(int idVoilier, int idRegate) {
		Classement classement = new Classement();
		
		try
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM classement WHERE ID_VOILIER = " + idVoilier + " AND ID_REGATE = " + idRegate);
			
			if(result.first()) {
				classement = new Classement(
						idVoilier, 
						idRegate, 
						result.getTime("TEMPS_ARRIVER"),
						result.getTime("TEMPS_COMPENSE")
						);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return classement;
	}

	@Override
	public List<Classement> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Recherche tout les participant d'une regate
	 * @param idVoilier
	 * @param idRegate
	 * @return
	 */
	public List<Classement> findAll(int idRegate) {
		List<Classement> listClassement = new ArrayList<Classement>();
		
		try
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM classement WHERE ID_REGATE = " + idRegate);
			
			while(result.next()) {
				listClassement.add(new Classement(
						result.getInt("ID_VOILIER"), 
						result.getInt("ID_REGATE"), 
						result.getTime("TEMPS_ARRIVER"),
						result.getTime("TEMPS_COMPENSE")
						));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return listClassement;
	}

}
