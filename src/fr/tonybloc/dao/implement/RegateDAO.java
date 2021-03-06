package fr.tonybloc.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.tonybloc.dao.DAO;
import fr.tonybloc.modele.Categorie;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.Voilier;

/**
 * Handler Regate
 * @author Tony
 *
 */
public class RegateDAO extends DAO<Regate> {

	/**
	 * Crée une instance de la classe RegateDAO
	 * @param con : connexion à la BDD
	 */
	public RegateDAO(Connection con) {
		super(con);
	}
	
	@Override
	public boolean create(Regate obj) {
		boolean requeteExecuter = false;
		try 
		{
			Statement stat = this.connect.createStatement();
			stat.executeUpdate("INSERT INTO `regate`(`LIBELLE`, `DISTANCE`, `DATE_DEPART`, `CLOTURE`) VALUES ( '" + obj.getIntituler() +"', " + obj.getDistance() +", '" + obj.getDate_depart_string() +"', " + obj.isCloture() + ")");
			requeteExecuter = true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return requeteExecuter;
	}
	
	
	@Override
	public boolean delete(Regate obj) {
		boolean requeteExecuter = false;
		try 
		{
			Statement stat = this.connect.createStatement();
			stat.executeUpdate("DELETE FROM `regate` WHERE ID_REGATE = " + obj.getId());
			requeteExecuter = true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return requeteExecuter;
	}

	
	@Override
	public boolean update(Regate obj) {
		boolean requeteExecuter = false;
		try 
		{
			Statement stat = this.connect.createStatement();
			stat.executeUpdate("UPDATE `regate` SET `LIBELLE`= '"+obj.getIntituler()+"',`DISTANCE`="+obj.getDistance()+",`DATE_DEPART`= '"+obj.getDate_depart_string()+"',`CLOTURE`="+obj.isCloture()+" WHERE `ID_REGATE` = "+ obj.getId());
			requeteExecuter = true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return requeteExecuter;
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
				regate = new Regate (
						id, 
						result.getString("LIBELLE"),
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

	/**
	 * Cherche la dernière régate inserée
	 * @return Regate
	 */
	public Regate findLastRegateInserted() {
		Regate regate = new Regate();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM regate WHERE ID_REGATE = (SELECT Max(ID_REGATE) FROM regate)");
			if(result.first()) {
				regate = new Regate(
						result.getInt("ID_REGATE"),
						result.getString("LIBELLE"),
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
						result.getString("LIBELLE"),
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
	
	/**
	 * Cherche toutes les régates cloturées
	 * @return List<Regate>
	 */
	public List<Regate> findRegateClosure(){
		List<Regate> listRegate = new ArrayList<Regate>();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM regate WHERE CLOTURE = true");
			while(result.next()) {
				listRegate.add(new Regate( 
						result.getInt("ID_REGATE"), 
						result.getString("LIBELLE"),
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
	
	/**
	 * Cherche toutes les regates qui ne sont pas cloturées
	 * @return List<Regate>
	 */
	public List<Regate> findRegateNotClosure() {
		List<Regate> listRegate = new ArrayList<Regate>();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM regate WHERE CLOTURE = false");
			while(result.next()) {
				listRegate.add(new Regate( 
						result.getInt("ID_REGATE"), 
						result.getString("LIBELLE"),
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
	
	/**
	 * Cherche tout les particiants d'une régate
	 * @param regate : regate courrente
	 * @return List<Voilier>
	 */
	public List<Voilier> getAllParticipant(Regate regate){
		
		
		List<Voilier> listParticipant = new ArrayList<Voilier>();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT DISTINCT v.*, cat.* FROM classement c, voilier v, regate r, categorie cat WHERE v.ID_VOILIER = c.ID_VOILIER AND c.ID_REGATE = "+regate.getId()+" AND cat.ID_CATEGORIE = v.CATEGORIE");
			while(result.next()) {
				listParticipant.add(new Voilier(
						result.getInt("ID_Voilier"),
						new Categorie(result.getInt("ID_CATEGORIE"), result.getString("LIBELLER")),
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
		return listParticipant;
	}
	
	/**
	 * Cherche le nombre d'élèments de la table : Régate
	 * @return int
	 */
	public int getRowCount() {
		int rowCount = 0;
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT count(*) as ROWCOUNT FROM regate");
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
	 * Cloture une régate
	 * @param obj : regate courrente
	 * @return boolean
	 */
	public boolean clotureRegate(Regate obj) {
		boolean requeteExecuter = false;
		try 
		{
			Statement stat = this.connect.createStatement();
			stat.executeUpdate("UPDATE `regate` SET `CLOTURE`="+obj.isCloture()+" WHERE `ID_REGATE` = "+ obj.getId());
			requeteExecuter = true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return requeteExecuter;
	}

}
