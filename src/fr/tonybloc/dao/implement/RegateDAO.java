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

public class RegateDAO extends DAO<Regate> {

	/**
	 * Crée une instance de la classe RegateDAO
	 * @param con
	 */
	public RegateDAO(Connection con) {
		super(con);
	}
	/**
	 * Ajoute une nouvelle 'Régate' dans la base de donnée
	 */
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
	/**
	 * Supprime une 'Régate' dans la base de donnée
	 */
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

	/**
	 * Met à jour une 'Régate' dans la base de donnée
	 */
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

	/**
	 * Retourne un element de la table : Régate 
	 * @return Regate
	 */
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
	/**
	 * Retourn tous les élément de la table : Régate
	 * @return List<Regate>
	 */
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
	 * Retourn tout les regate qui ne sont pas cloturées
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
	
	public List<Voilier> getAllParticipant(int id){
		
		
		List<Voilier> listParticipant = new ArrayList<Voilier>();
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT DISTINCT v.*, cat.* FROM classement c, voilier v, regate r, categorie cat WHERE v.ID_VOILIER = c.ID_VOILIER AND c.ID_REGATE = "+id+" AND cat.ID_CATEGORIE = v.CATEGORIE");
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
	 * Retourne le nombre d'élèment de la table : Régate
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

}
