	package modele;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modele.Location;

public class LocationDAO {
private MySQLCon mySQLCon;
	
	public LocationDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	public ResultSet getAllLocations() throws SQLException {
		String req = "select * from location";
		Statement stmt = this.mySQLCon.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(req);
		return rs;
	}
	
	public void supprimerLocation(String id, Date date_debut) {
		try {
	        String reqDeleteLouer = "{CALL deleteLocation (?,?)}";
	        PreparedStatement stmtDeleteLouer = this.mySQLCon.getConnection().prepareCall(reqDeleteLouer);
	        stmtDeleteLouer.setString(1, id);
	        stmtDeleteLouer.setDate(2, date_debut);
	        int i = stmtDeleteLouer.executeUpdate();
	        System.out.println(i +"lignes supprimées");
	        stmtDeleteLouer.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public int ajouterLocation(
		String id_bien,
		String id_locataire,
		Location location) throws SQLException {
		try {
			String reqInsertLocation = "insert into location (id_bien,date_debut,nb_mois,colocation,provision_charges_ttc,loyer_ttc,caution_ttc,"
					+ "annee,trimestre) "
					+ "values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmtInsertLocation = this.mySQLCon.getConnection().prepareStatement(reqInsertLocation);
			stmtInsertLocation.setString(1, id_bien);
			stmtInsertLocation.setDate(2, location.getDate_debut());
			stmtInsertLocation.setInt(3, location.getNb_mois());
			stmtInsertLocation.setString(4, location.isColocation());
			stmtInsertLocation.setFloat(4, location.getProvision_chargement_TTC());
			stmtInsertLocation.setFloat(5, location.getLoyer_TTC());
			stmtInsertLocation.setFloat(6, location.getCaution_TTC());
			stmtInsertLocation.setString(7,"2024-01-01");
			stmtInsertLocation.setInt(8, 1);
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
		}catch(Exception e) {
			System.out.println(e);
		}
		return 0;
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
	
	public String isSelectBienColoc(String id_bien) {
		try {
			String req = "{call getColocationByIdBien(?)}";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id_bien);
			ResultSet rs = stmt.executeQuery();
			return rs.getString(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
}
