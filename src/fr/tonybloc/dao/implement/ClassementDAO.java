package fr.tonybloc.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fr.tonybloc.dao.DAO;
import fr.tonybloc.modele.Categorie;
import fr.tonybloc.modele.Classement;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.modele.Voilier;

public class ClassementDAO extends DAO<Classement> {

	public ClassementDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Classement obj) {
		boolean requeteExecuter = false;
		try 
		{
			Statement stat = this.connect.createStatement();
			
			if(obj.getTempsArrive() == null && obj.getTempsCompense() == null) {
				stat.executeUpdate("INSERT INTO `classement`(`ID_VOILIER`, `ID_REGATE`, `TEMPS_ARRIVER`, `TEMPS_COMPENSE`) "
						+ "VALUES ( "+obj.getVoilier().getId()+", "+obj.getRegate().getId()+", "+obj.getTempsArrive()+","+obj.getTempsCompense()+" )");
				requeteExecuter = true;
			}else {
				stat.executeUpdate("INSERT INTO `classement`(`ID_VOILIER`, `ID_REGATE`, `TEMPS_ARRIVER`, `TEMPS_COMPENSE`) "
						+ "VALUES ( "+obj.getVoilier().getId()+", "+obj.getRegate().getId()+", '"+obj.getTempsArrive()+"', '"+obj.getTempsCompense()+"' )");
				requeteExecuter = true;
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return requeteExecuter;
	}

	@Override
	public boolean delete(Classement obj) {
		boolean requeteExecuter = false;
		try 
		{
			Statement stat = this.connect.createStatement();
			stat.executeUpdate("DELETE FROM `classement` WHERE ID_REGATE = " + obj.getRegate().getId() + " AND ID_VOILIER = " + obj.getVoilier().getId() );
			requeteExecuter = true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return requeteExecuter;
	}

	@Override
	public boolean update(Classement obj) {	
		boolean requeteExecuter = false;
		try 
		{
			Statement stat = this.connect.createStatement();
			if(obj.getTempsArrive() == null) {
				stat.executeUpdate("UPDATE classement SET TEMPS_ARRIVER= "+obj.getTempsArrive()+", TEMPS_COMPENSE = "+obj.getTempsCompense()+" WHERE ID_VOILIER = "+obj.getVoilier().getId()+" AND ID_REGATE = "+obj.getRegate().getId());
			}else {
				stat.executeUpdate("UPDATE classement SET TEMPS_ARRIVER= '"+obj.getTempsArrive()+"', TEMPS_COMPENSE = '"+obj.getTempsCompense()+"' WHERE ID_VOILIER = "+obj.getVoilier().getId()+" AND ID_REGATE = "+obj.getRegate().getId());
			}
			requeteExecuter = true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return requeteExecuter;
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
	public Classement find(Voilier voilier, Regate regate) {
		Classement classement = new Classement();
		
		try
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT * FROM classement c WHERE ID_VOILIER = " + voilier.getId() + " AND ID_REGATE = " + regate.getId());
			
			if(result.first()) {
				classement = new Classement(
						voilier, 
						regate, 
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
	 * Recherche tout les classement d'une regate
	 * @param idVoilier
	 * @param idRegate
	 * @return
	 */
	public List<Classement> findAllParticipant(Regate regate) {
		List<Classement> listClassement = new ArrayList<Classement>();
		
		try
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT *  FROM classement c, categorie cat, voilier v WHERE c.ID_VOILIER = v.ID_VOILIER AND cat.ID_CATEGORIE = v.CATEGORIE AND c.ID_REGATE = " + regate.getId());
			while(result.next()) {
				listClassement.add(new Classement(
						new Voilier(
								result.getInt("ID_VOILIER"),
								new Categorie(result.getInt("ID_CATEGORIE"), result.getString("LIBELLER")),
								result.getString("NOM_VOILIER"),
								result.getString("NOM_SKIPPEUR"),
								result.getString("PRENOM_SKIPPEUR"),
								result.getInt("RATING")
								), 
						regate, 
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
	
	/**
	 * Retourne le nombre d'élèment de la table : classement, pour une Régate
	 * @return int
	 */
	public int getRowCount(int idRegate) {
		int rowCount = 0;
		try 
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT count(*) as ROWCOUNT FROM classement WHERE ID_REGATE = " + idRegate);
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
	 * Recherche tout les classement d'une regate
	 * @param idVoilier
	 * @param idRegate
	 * @return
	 */
	public List<Classement> findClassementAt(Regate regate, Categorie categorie) {
		List<Classement> listClassement = new ArrayList<Classement>();
		try
		{
			ResultSet result;
			Statement stat = this.connect.createStatement();
			result = stat.executeQuery("SELECT v.*, c.*, cat.* from classement c, regate r, categorie cat, voilier v where c.ID_VOILIER = v.ID_VOILIER AND cat.ID_CATEGORIE = v.CATEGORIE AND c.ID_REGATE = r.ID_REGATE AND c.ID_REGATE = "+regate.getId()+" AND v.CATEGORIE = "+categorie.getId()+" ORDER BY c.TEMPS_COMPENSE ASC");
			while(result.next()) {
				listClassement.add(new Classement(
						new Voilier(
								result.getInt("ID_VOILIER"),
								new Categorie(result.getInt("ID_CATEGORIE"), result.getString("LIBELLER")),
								result.getString("NOM_VOILIER"),
								result.getString("NOM_SKIPPEUR"),
								result.getString("PRENOM_SKIPPEUR"),
								result.getInt("RATING")
								), 
						regate, 
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
