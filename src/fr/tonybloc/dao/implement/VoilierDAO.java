package fr.tonybloc.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.tonybloc.dao.DAO;
import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.modele.Categorie;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.Voilier;

/**
 * Handler Voilier
 * @author Tony
 *
 */
public class VoilierDAO extends DAO<Voilier> {

	/**
	 * Crée une instance de la classe VoilierDOA
	 * @param con : connexion à la BDD
	 */
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
					+ "INSERT INTO `voilier` (`CATEGORIE`, `NOM_VOILIER`, `NOM_SKIPPEUR`, `PRENOM_SKIPPEUR`, `RATING`) "
					+ "VALUES ( "+obj.getCategorie().getId()+", '"+obj.getNomVoilier()+"', '"+obj.getNomSkippeur()+"', '"+ obj.getPrenomSkippeur() +"', "+ obj.getRating()+")"
					);
//			stat.close();
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
				voilier = new Voilier( 
						id, 
						DAOFactory.getCategorieDAO().find(result.getInt("CATEGORIE")),
						result.getString("NOM_VOILIER"),
						result.getString("NOM_SKIPPEUR"),
						result.getString("PRENOM_SKIPPEUR"),
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
						DAOFactory.getCategorieDAO().find(result.getInt("CATEGORIE")),
						result.getString("NOM_VOILIER"),
						result.getString("NOM_SKIPPEUR"),
						result.getString("PRENOM_SKIPPEUR"),
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
	
	
	
	/**
	 * Cherche le nombre d'élèments de la table : voilier
	 * @return int
	 */
	public int getRowCount() {
		int rowCount = 0;
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT count(*) as ROWCOUNT FROM voilier");
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

	/**
	 * Cherche le dernier voilier inséré
	 * @return Voilier
	 */
	public Voilier findLastVoilierInserted() {
		Voilier voilier = new Voilier();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM voilier v, categorie c WHERE v.CATEGORIE = c.ID_CATEGORIE AND ID_VOILIER = (SELECT Max(ID_VOILIER) FROM voilier)");
			if(result.first()) {
				voilier = new Voilier(
						result.getInt("ID_Voilier"),
						new Categorie(result.getInt("ID_CATEGORIE"), result.getString("LIBELLER")),
						result.getString("NOM_VOILIER"),
						result.getString("NOM_SKIPPEUR"),
						result.getString("PRENOM_SKIPPEUR"),
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

}
