package modele;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocationDAO {
private MySQLCon mySQLCon;
private static final Logger logger = Logger.getLogger(ImmeubleDAO.class.getName());
	
	public LocationDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	public List<Location> getAllLocations() throws DAOException {
		String req = "select distinct l1.id_bien, l1.date_debut, l1.nb_mois, l1.colocation, "
				+ "l1.provision_charges_ttc, l1.loyer_ttc, l1.caution_ttc, l1.annee, "
				+ "l1.trimestre, l2.id_locataire, l1.active "
				+ "from location l1, louer l2 "
				+ "where l2.id_bien=l1.id_bien "
				+ "and l1.date_debut = l2.date_debut";
		Statement stmt;
		List<Location> liste = new LinkedList<>();
		try {
			stmt = this.mySQLCon.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(req);
			while(rs.next()) {
				liste.add(new Location(rs.getDate(2), rs.getString(4), rs.getInt(3), 
						rs.getFloat(6), rs.getFloat(5), rs.getFloat(7), rs.getString(1), rs.getString(10), rs.getBoolean(11)));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de la liste des locations",e);
			throw new DAOException("Erreurs lors de la récupération de la liste des locations",e);
		}
		
		return liste;
	}
	
	public void supprimerLocation(String id, Date date_debut) throws DAOException {
		try {
	        String reqDeleteLouer = "{CALL deleteLocation (?,?)}";
	        PreparedStatement stmtDeleteLouer = this.mySQLCon.getConnection().prepareCall(reqDeleteLouer);
	        stmtDeleteLouer.setString(1, id);
	        stmtDeleteLouer.setDate(2, date_debut);
	        int i = stmtDeleteLouer.executeUpdate();
	        System.out.println(i +"lignes supprimées");
	        stmtDeleteLouer.close();
		}catch(SQLException e){
			logger.log(Level.SEVERE,"Erreurs lors de la suppression de la liste des locations",e);
			throw new DAOException("Erreurs lors de la suppression de la liste des locations",e);
		}
	}
	
	public int ajouterLocation(
		String id_bien,
		Location location) throws DAOException {
		try {
			String reqInsertLocation = "{CALL insertLocation(?,?,?,?,?,?,?,?,?,?)}";
			PreparedStatement stmtInsertLocation = this.mySQLCon.getConnection().prepareStatement(reqInsertLocation);
			stmtInsertLocation.setString(1, id_bien);
			stmtInsertLocation.setDate(2, location.getDate_debut());
			stmtInsertLocation.setInt(3,location.getNb_mois());
			stmtInsertLocation.setString(4, location.isColocation());
			stmtInsertLocation.setFloat(5, location.getProvision_chargement_TTC());
			stmtInsertLocation.setFloat(6, location.getLoyer_TTC());
			stmtInsertLocation.setFloat(7, location.getCaution_TTC());
			stmtInsertLocation.setDate(8,Date.valueOf("2025-01-01"));
			stmtInsertLocation.setInt(9,1);
			stmtInsertLocation.setBoolean(10,true);
			int i = stmtInsertLocation.executeUpdate();
			stmtInsertLocation.close();
			
			String reqInsertLouer = "insert into louer (id_bien,date_debut,id_locataire) values (?,?,?)";
			PreparedStatement stmtInsertLouer = this.mySQLCon.getConnection().prepareStatement(reqInsertLouer);
			stmtInsertLouer.setString(1, id_bien);
			stmtInsertLouer.setDate(2, location.getDate_debut());
			stmtInsertLouer.setString(3, location.getIdLocataire());
			i+=stmtInsertLouer.executeUpdate();
			stmtInsertLouer.close();
			return i;
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de l'ajout d'une location",e);
			throw new DAOException("Erreurs lors de l'ajout d'une location",e);
		}
	}
	
	public boolean locationExists(String id_bien, String id_locataire,Date date_debut) throws DAOException {
		try {
			String req="select * from louer where id_bien=? and id_locataire=? and date_debut=?";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id_bien);
			stmt.setDate(3, date_debut);
			stmt.setString(2, id_locataire);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de l'ajout d'une location",e);
			throw new DAOException("Erreurs lors de l'ajout d'une location",e);
		}
	}
	
	/**
	 * Récupérer une location à partir de l'identifiant d'un bien
	 * @param id_bien
	 * @return
	 * @throws DAOException 
	 */
	public Location getLocationById_Bien(String id_bien) throws DAOException {
		try {
			String req = "{CALL getColocationByIdBien(?)}";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id_bien);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Location(rs.getDate(2), rs.getString(4), rs.getInt(3), rs.getFloat(6), rs.getFloat(5), rs.getFloat(7), rs.getString(1), rs.getString(10), rs.getBoolean(11));
			}
			return null;
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération d'une location à partir de l'identifiant d'un bien",e);
			throw new DAOException("Erreurs lors de la récupération d'une location à partir de l'identifiant d'un bien",e);
		}
		
	}	
	
	/**
	 * Récupérer la provision d'une location
	 * @param id_bien
	 * @param date_debut
	 * @return
	 * @throws SQLException
	 */
	public Float getProvisionFromLocation(String id_bien, Date date_debut) throws DAOException {
		try {
			String req = "{CALL getProvisionFromLocation(?,?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_bien);
			stmt.setDate(2, date_debut);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getFloat(1);
			}
			return null;
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de la provision d'une location",e);
			throw new DAOException("Erreurs lors de la récupération de la provision d'une location",e);
		}
	}
	
	/**
	 * Mettre à jour la provision d'une location
	 * @param id_bien
	 * @param date_debut
	 * @param provision
	 * @throws SQLException
	 */
	public void setNouvelleProvision(String id_bien, Date date_debut, Float provision) throws DAOException {
		try {
			String req = "{CALL setNouvelleProvision(?,?,?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_bien);
			stmt.setDate(2, date_debut);
			stmt.setFloat(3, provision);
			stmt.executeQuery();
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la mise à jour la provision d'une location",e);
			throw new DAOException("Erreurs lors de la mise à jour la provision d'une location",e);
		}
	}
}
