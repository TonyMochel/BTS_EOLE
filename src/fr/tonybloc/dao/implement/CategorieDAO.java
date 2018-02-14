package fr.tonybloc.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.tonybloc.dao.DAO;
import fr.tonybloc.modele.Categorie;

public class CategorieDAO extends DAO<Categorie>{

	public CategorieDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Categorie obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Categorie obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Categorie obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Categorie find(int id) {
		Categorie categorie = new Categorie();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM categorie WHERE ID_CATEGORIE = " + id);
			
			if(result.first()) {
				categorie = new Categorie(
						id,
						result.getString("LIBELLER")
						);
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return categorie;
	}

	@Override
	public List<Categorie> findAll() {
		List<Categorie> listCategorie = new ArrayList<Categorie>();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM categorie");
			
			while(result.next()) {
				listCategorie.add(new Categorie(
						result.getInt("ID_CATEGORIE"),
						result.getString("LIBELLER")
						));
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return listCategorie;
	}
	
	/**
	 * Retourne le nombre d'élèment de la table : categorie
	 * @return int
	 */
	public int getRowCount() {
		int rowCount = 0;
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT count(*) as ROWCOUNT FROM categorie");
			if(result.first()) {
				rowCount = result.getInt("ROWCOUNT");
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return rowCount;
	}

}
