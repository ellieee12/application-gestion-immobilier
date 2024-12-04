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
			// Supprimer dans la table Louer
	        String reqDeleteLouer = "DELETE FROM louer WHERE id_bien = ? AND date_debut = ?";
	        PreparedStatement stmtDeleteLouer = this.mySQLCon.getConnection().prepareStatement(reqDeleteLouer);
	        stmtDeleteLouer.setString(1, id);
	        stmtDeleteLouer.setDate(2, date_debut);
	        stmtDeleteLouer.executeUpdate();
	        stmtDeleteLouer.close();
	        
	        //Supprimer dans la table Documents location
	        String reqdeleteDocumentLocation  = "DELETE FROM Document_Location WHERE id_bien = ? AND date_debut = ?";
	        PreparedStatement stmtDeleteDocumentLocation = this.mySQLCon.getConnection().prepareStatement(reqdeleteDocumentLocation);
	        stmtDeleteDocumentLocation.setString(1, id);
	        stmtDeleteDocumentLocation.setDate(2, date_debut);
	        stmtDeleteDocumentLocation.executeUpdate();
	        stmtDeleteDocumentLocation.close();
	        
			
	        //Supprimer dans la table Location
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
			String reqInsertLocation = "insert into location (id_bien,date_debut,nb_mois,provision_charges_ttc,loyer_ttc,caution_ttc,"
					+ "date_derniere_reg,annee,trimestre) "
					+ "values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmtInsertLocation = this.mySQLCon.getConnection().prepareStatement(reqInsertLocation);
			stmtInsertLocation.setString(1, id_bien);
			stmtInsertLocation.setDate(2, location.getDate_debut());
			stmtInsertLocation.setInt(3, location.getNb_mois());
			stmtInsertLocation.setFloat(4, location.getProvision_chargement_TTC());
			stmtInsertLocation.setFloat(5, location.getLoyer_TTC());
			stmtInsertLocation.setFloat(6, location.getCaution_TTC());
			stmtInsertLocation.setDate(7, location.getDate_derniere_reg());
			stmtInsertLocation.setString(8,"2024-01-01");
			stmtInsertLocation.setInt(9, 1);
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
