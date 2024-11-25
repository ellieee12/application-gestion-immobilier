package modeleDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Location;

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
			String reqDeleteLocation = "delete from Location where id_bien = ? and date_debut = ?";
			PreparedStatement stmtDeleteLocation = this.mySQLCon.getConnection().prepareStatement(reqDeleteLocation);
			stmtDeleteLocation.setString(1, id);
			stmtDeleteLocation.setDate(2, date_debut);
			int nbLignesSupprimeesLocation = stmtDeleteLocation.executeUpdate();
			stmtDeleteLocation.close();
			System.out.println(nbLignesSupprimeesLocation+" lignes supprimées dans le table (Louer)");			
			
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public int ajouterLocation(
		String id_bien,
		String id_locataire,
		Location location) throws SQLException {
		try {
			String reqInsertLocation = "insert into location (id_bien,date_debut,nb_mois,provision_charges_ttc,loyer_ttc,caution_ttc,etat_lieux,"
					+ "date_derniere_reg,montant_reel_paye,annee,trimestre) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmtInsertLocation = this.mySQLCon.getConnection().prepareStatement(reqInsertLocation);
			stmtInsertLocation.setString(1, id_bien);
			stmtInsertLocation.setDate(2, location.getDate_debut());
			stmtInsertLocation.setInt(3, location.getNb_mois());
			stmtInsertLocation.setFloat(4, location.getProvision_chargement_TTC());
			stmtInsertLocation.setFloat(5, location.getLoyer_TTC());
			stmtInsertLocation.setFloat(6, location.getCaution_TTC());
			stmtInsertLocation.setString(7,location.getEtat_lieux());
			stmtInsertLocation.setDate(8, location.getDate_derniere_reg());
			stmtInsertLocation.setFloat(9, location.getMontant_reel_paye());
			stmtInsertLocation.setString(10,"2024-01-01");
			stmtInsertLocation.setInt(11, 1);
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
