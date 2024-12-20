package modele;

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
		String req = "select l1.id_bien, l1.date_debut, l1.nb_mois, l1.colocation, "
				+ "l1.provision_charges_ttc, l1.loyer_ttc, l1.caution_ttc, l1.annee, "
				+ "l1.trimestre, l2.id_locataire "
				+ "from location l1, louer l2 "
				+ "where l2.id_bien=l1.id_bien "
				+ "and l2.date_debut=l2.date_debut";
		Statement stmt;
		List<Location> liste = new LinkedList<>();
		try {
			stmt = this.mySQLCon.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(req);
			while(rs.next()) {
				liste.add(new Location(null, false, 0, 0, 0, 0, req));
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
		String id_locataire,
		Location location) throws DAOException {
		try {
			String reqInsertLocation = "{CALL insertLocation(?,?,?,?,?,?,?,?,?,?)}";
			PreparedStatement stmtInsertLocation = this.mySQLCon.getConnection().prepareStatement(reqInsertLocation);
			stmtInsertLocation.setString(1, id_locataire);
			stmtInsertLocation.setString(2, id_bien);
			stmtInsertLocation.setDate(3, location.getDate_debut());
			stmtInsertLocation.setInt(4,location.getNb_mois());
			if (location.isColocation()) {
				stmtInsertLocation.setInt(5, 1);
			}else {
				stmtInsertLocation.setInt(5, 0);
			}
			stmtInsertLocation.setFloat(6, location.getProvision_chargement_TTC());
			stmtInsertLocation.setFloat(7, location.getLoyer_TTC());
			stmtInsertLocation.setFloat(8, location.getCaution_TTC());
			stmtInsertLocation.setDate(9,Date.valueOf("2024-01-01"));
			stmtInsertLocation.setInt(10,3);
			int i = stmtInsertLocation.executeUpdate();
			stmtInsertLocation.close();
			
			String reqInsertLouer = "insert into louer (id_bien,date_debut,id_locataire) values (?,?,?)";
			PreparedStatement stmtInsertLouer = this.mySQLCon.getConnection().prepareStatement(reqInsertLouer);
			stmtInsertLouer.setString(1, id_bien);
			stmtInsertLouer.setDate(2, location.getDate_debut());
			stmtInsertLouer.setString(3, id_locataire);
			i+=stmtInsertLouer.executeUpdate();
			stmtInsertLouer.close();
			return i;
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de l'ajout d'une location",e);
			throw new DAOException("Erreurs lors de l'ajout d'une location",e);
		}
	}
	
	public boolean locationExists(String id_bien, String id_locataire,Date date_debut) {
		try {
			String req="select * from louer where id_bien=? and id_locataire=? and date_debut=?";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id_bien);
			stmt.setDate(3, date_debut);
			stmt.setString(2, id_locataire);
			System.out.println("yes");
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		}catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}
	
}
