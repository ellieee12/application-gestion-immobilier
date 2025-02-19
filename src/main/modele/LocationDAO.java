package modele;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		try {
			String req = "{call getAllLocations()}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			ResultSet rs = stmt.executeQuery(req);
			List<Location> liste = new LinkedList<>();
			while(rs.next()) {
				liste.add(new Location(rs.getDate(2), rs.getString(4), rs.getInt(3), 
						rs.getFloat(6), rs.getFloat(5), rs.getFloat(7), rs.getString(1), rs.getString(8), rs.getDate(9), rs.getDate(10)));
			}
			System.out.println(liste);
			rs.close();
			return liste;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de la liste des locations",e);
			throw new DAOException("Erreurs lors de la récupération de la liste des locations",e);
		}
	}
	
	public void supprimerLocation(String id_bien, Date date_debut) throws DAOException {
		try {
	        String reqDeleteLouer = "{CALL deleteLocation (?,?)}";
	        PreparedStatement stmtDeleteLouer = this.mySQLCon.getConnection().prepareCall(reqDeleteLouer);
	        stmtDeleteLouer.setString(1, id_bien);
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
			String reqInsertLocation = "{CALL insertLocation(?,?,?,?,?,?,?,?,?)}";
			PreparedStatement stmtInsertLocation = this.mySQLCon.getConnection().prepareStatement(reqInsertLocation);
			stmtInsertLocation.setString(1, id_bien);
			stmtInsertLocation.setDate(2, location.getDate_debut());
			stmtInsertLocation.setInt(3,location.getNb_mois());
			stmtInsertLocation.setString(4, location.isColocation());
			stmtInsertLocation.setFloat(5, location.getProvision_chargement_TTC());
			stmtInsertLocation.setFloat(6, location.getLoyer_TTC());
			stmtInsertLocation.setFloat(7, location.getCaution_TTC());
			stmtInsertLocation.setDate(8,location.getDate_regularisation());
			stmtInsertLocation.setDate(9,location.getDate_fin());
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
			String req="select louer.id_bien, louer.id_locataire, louer.date_debut from louer, location "
					+ "where louer.id_bien = location.id_bien "
					+ "and louer.date_debut = location.date_debut "
					+ "and louer.id_bien=? "
					+ "and louer.id_locataire=? "
					+ "and louer.date_debut=? "
					+ "and date_fin is null;";
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
				return new Location(rs.getDate(2), rs.getString(4), rs.getInt(3), rs.getFloat(6),
						rs.getFloat(5), rs.getFloat(7), rs.getString(1), rs.getString(8), rs.getDate(9), rs.getDate(10));
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
	
	/**
	 * Mettre en place la date de la dernière régularisation d'une location
	 * @param id_bien
	 * @param date_debut
	 * @param provision
	 * @throws SQLException
	 */
	public void setDateRegularisation(String id_bien, Date date_debut, Date date_fin) throws DAOException {
		try {
			String req = "{CALL setDateRegularisation(?,?,?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_bien);
			stmt.setDate(2, date_debut);
			stmt.setDate(3, date_fin);
			stmt.executeQuery();
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la mise à jour la provision d'une location",e);
			throw new DAOException("Erreurs lors de la mise à jour la provision d'une location",e);
		}
	}
	
	public Date getDateRegularisationFromLocation(String id_bien, Date date_debut) throws DAOException {
		try {
			String req = "{CALL getDateRegularisationFromLocation(?,?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_bien);
			stmt.setDate(2, date_debut);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getDate(1);
			}
			return null;
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de la provision d'une location",e);
			throw new DAOException("Erreurs lors de la récupération de la provision d'une location",e);
		}
	}
	
	/**
	 * Mettre en place la date de fin d'une location
	 * @param id_bien
	 * @param date_debut
	 * @param provision
	 * @throws SQLException
	 */
	public void setDateFin(String id_bien, Date date_debut, Date date_fin) throws DAOException {
		try {
			String req = "{CALL setDateFin(?,?,?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_bien);
			stmt.setDate(2, date_debut);
			stmt.setDate(3, date_fin);
			stmt.executeQuery();
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la mise à jour la provision d'une location",e);
			throw new DAOException("Erreurs lors de la mise à jour la provision d'une location",e);
		}
	}
	
	public Date getDateFinFromLocation(String id_bien, Date date_debut) throws DAOException {
		try {
			String req = "{CALL getDateFinFromLocation(?,?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_bien);
			stmt.setDate(2, date_debut);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getDate(1);
			}
			return null;
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de la provision d'une location",e);
			throw new DAOException("Erreurs lors de la récupération de la provision d'une location",e);
		}
	}
	
	/**
	 * @param annee L'année de la déclaration
	 * @return la somme des loyers qui ont été payés pendant toute l'année
	 * @throws DAOException 
	 */
	public float getSommeLoyers12Mois(int annee) throws DAOException {
		try {
			String req = "{CALL getSommeLoyers12Mois(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setInt(1, annee);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getFloat(1)*12;	//on multiplie la somme des loyers par le nombre de mois
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération des loyers.",e);
			throw new DAOException("Erreurs lors de la récupération des loyers.",e);
		}
		return -1.0F;
	}
	
	/**
	 * 
	 * @param annee L'année de la déclaration
	 * @return la liste des loyers des locations qui se sont terminées pendant
	 * l'année entrée, leur mois de debut, et leur mois de fin
	 * @throws DAOException 
	 */
	public List<List<Object>> getLoyersTermine(int annee) throws DAOException {
		List<List<Object>> resultList = new ArrayList<>();
		try {
			String req = "{CALL getLoyersTermine(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setInt(1, annee);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				float loyer = rs.getFloat(1);	//montant du loyer
                int anneeDebut = rs.getInt(2);	// Numéro de l'année de début
                int moisDebut = rs.getInt(3);	// Numéro du mois de début
                int moisFin = rs.getInt(4); // Numéro du mois de fin

                // Stocker la ligne de résultat dans une liste
                List<Object> row = new ArrayList<>();
                row.add(loyer);
                row.add(anneeDebut);
                row.add(moisDebut);
                row.add(moisFin);

                resultList.add(row);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération des loyers.",e);
			throw new DAOException("Erreurs lors de la récupération des loyers.",e);
		}
		return resultList;
	}
	
	/**
	 * 
	 * @param annee L'année de la déclaration
	 * @return la liste des loyers des locations ayant commencé pendant l'année entrée, et leur date de début
	 * @throws DAOException 
	 */
	public List<List<Object>> getLoyersCommence(int annee) throws DAOException {
		List<List<Object>> resultList = new ArrayList<>();
		try {
			String req = "{CALL getLoyersCommence(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setInt(1, annee);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				float loyer = rs.getFloat(1);
                int moisDebut = rs.getInt(2);

                // Stocker la ligne de résultat dans une liste
                List<Object> row = new ArrayList<>();
                row.add(loyer);
                row.add(moisDebut);

                resultList.add(row);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération des loyers.",e);
			throw new DAOException("Erreurs lors de la récupération des loyers.",e);
		}
		return resultList;
	}
}
