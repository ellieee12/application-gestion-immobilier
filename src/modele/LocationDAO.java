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
	
}
