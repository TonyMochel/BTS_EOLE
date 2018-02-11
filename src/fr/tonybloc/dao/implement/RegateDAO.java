package fr.tonybloc.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.tonybloc.dao.DAO;
import fr.tonybloc.modele.Regate;

public class RegateDAO extends DAO<Regate> {

	public RegateDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Regate obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Regate obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Regate obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Regate find(int id) {
		Regate regate = new Regate();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM regate WHERE ID_REGATE = " + id);
			if(result.first()) {
				regate = new Regate( 
						id, 
						result.getString("LIBELLER"),
						result.getDouble("DISTANCE"),
						result.getDate("DATE_DEPART"),
						result.getBoolean("CLOTURE")
						);
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return regate;
	}

	@Override
	public List<Regate> findAll() {
		List<Regate> listRegate = new ArrayList<Regate>();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM regate");
			while(result.next()) {
				listRegate.add(new Regate( 
						result.getInt("ID_REGATE"), 
						result.getString("LIBELLER"),
						result.getDouble("DISTANCE"),
						result.getDate("DATE_DEPART"),
						result.getBoolean("CLOTURE")
						));
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return listRegate;
	}

}
