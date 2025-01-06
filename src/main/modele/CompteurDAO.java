package modele;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import modele.Compteur.typeCompteur;

/**
 * DAO pour la gestion des opérations CRUD sur les objets Compteur.
 */
public class CompteurDAO{
	
	private final MySQLCon mySQLCon;
	private static final Logger logger = Logger.getLogger(CompteurDAO.class.getName());
	
	/**
	 * Constructeur prenant une connexion à la base de données
	 */
	public CompteurDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}

	/**
	 * Récupère l'id compteur associé à un bien donné selon son type
	 * @throws SQLException
	 */
	public String getCompteurFromOneBienSelonType(String idBien, typeCompteur type) throws SQLException {
		String req = "{CALL getCompteurByBienAndType(?,?)}";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, idBien);
		stmt.setString(2, type.getDénomination());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
	
	public ResultSet getTypeImmeubleFromIdBien(String id_bien) throws SQLException {
		String req = "{CALL getTypeImmeubleFromIdBien(?)}";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, id_bien);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs;
		}
		return null;
	}
	
	public int getReleveFromIdCompteur(String id_compteur, int annee) throws SQLException {
		String req = "{CALL getReleveFromIdCompteur(?,?)}";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, id_compteur);
		stmt.setInt(2,annee);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
	
	public ResultSet getEntretienFromIdBien(String id_bien) throws SQLException {
		String req = "{CALL getEntretienFromIdBien(?)}";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, id_bien);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs;
		}
		return null;
	}
	
	public Float getProvisionFromLocation(String id_bien, Date date_debut) throws SQLException {
		String req = "{CALL getProvisionFromLocation(?,?)}";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, id_bien);
		stmt.setDate(2, date_debut);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getFloat(1);
		}
		return null;
	}
	
	public void setNouvelleProvision(String id_bien, Date date_debut, Float provision) throws SQLException {
		if (provision == null) {
			
		}
		String req = "{CALL setNouvelleProvision(?,?,?)}";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, id_bien);
		stmt.setDate(2, date_debut);
		stmt.setFloat(3, provision);
		stmt.executeQuery();
	}
	
	public void ajouterReleve(int annee, int index, String idCompteur) throws SQLException {
		CallableStatement stmt ;
		String req = "{CALL addReleve(?,?,?)}";
		stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setInt(1,annee);
		stmt.setInt(2, index);
		stmt.setString(3, idCompteur);
		stmt.executeUpdate();
	}
}
