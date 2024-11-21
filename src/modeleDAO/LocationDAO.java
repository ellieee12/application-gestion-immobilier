package modeleDAO;

import java.sql.Date;

import classes.Location;

public class LocationDAO {
private MySQLCon mySQLCon;
	
	public LocationDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	public int ajouterLocation(
			String id_bien,
			String id_locataire,
			Location location) {
			String req = "insert into location (id_bien,id_locataire,date_debut";
		return 0;
	}
	//ajouter location
	// verifier location
	
}
