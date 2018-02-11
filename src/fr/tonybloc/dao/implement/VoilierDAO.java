package fr.tonybloc.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.tonybloc.dao.DAO;
import fr.tonybloc.modele.Voilier;

public class VoilierDAO extends DAO<Voilier> {

	public VoilierDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Voilier obj) {
		boolean requestExecuted = false;
		try {
			
			Statement stat = this.connect.createStatement();
			stat.executeUpdate(""
					+ "INSERT INTO `voilier` "
					+ "(`ID_VOILIER`, `CATEGORIE`, `NOM_VOILIER`, `NOM_SKIPPEUR`, `RATING`) "
					+ "VALUES ( "+obj.getId()+", "+obj.getCategorie()+", "+obj.getNomVoilier()+", "+obj.getNomSkippeur()+", "+obj.getRating()+")"
					);
			stat.close();
			requestExecuted = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return requestExecuted;
	}

	@Override
	public boolean delete(Voilier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Voilier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Voilier find(int id) {
		Voilier voilier = new Voilier();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM voilier WHERE ID_VOILIER = " + id);
			if(result.first()) {
				System.out.println(result.getInt("CATEGORIE") + " - " +
						result.getString("NOM_VOILIER") + " - " +
						result.getString("NOM_SKIPPEUR") + " - " +
						result.getInt("RATING"));
				
				voilier = new Voilier( 
						id, 
						result.getInt("CATEGORIE"),
						result.getString("NOM_VOILIER"),
						result.getString("NOM_SKIPPEUR"),
						result.getInt("RATING")
						);
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return voilier;
	}

	@Override
	public List<Voilier> findAll() {
		List<Voilier> listVoilier = new ArrayList<Voilier>();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM voilier");
			while(result.next()) {
				
				listVoilier.add(new Voilier(
						result.getInt("ID_VOILIER"), 
						result.getInt("CATEGORIE"),
						result.getString("NOM_VOILIER"),
						result.getString("NOM_SKIPPEUR"),
						result.getInt("RATING")
						));
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	
		return listVoilier;
	}

}
