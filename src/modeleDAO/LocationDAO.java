package modeleDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.Location;

public class LocationDAO {
private MySQLCon mySQLCon;
	
	public LocationDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	public int ajouterLocation(
			String id_bien,
			String id_locataire,
			Location location) throws SQLException {
			String req = "insert into location (id_bien,id_locataire,date_debut,nb_mois,provision_charges_ttc,loyer_ttc,caution_ttc,bail,etat_lieux,"
					+ "date_derniere_reg,montant_reel_paye,annee,trimestre) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id_bien);
			stmt.setString(2, id_locataire);
			stmt.setDate(3, location.getDate_debut());
			stmt.setInt(4, location.getNb_mois());
			stmt.setFloat(5, location.getProvision_chargement_TTC());
			stmt.setFloat(6, location.getLoyer_TTC());
			
		return 0;
	}
	//ajouter location
	// verifier location
	
}
