package modeleDAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import classes.Compteur.typeCompteur;

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
	 * Récupère le compteur associé à un bien donné selon son type
	 * @param identifiant d'un immeuble donné
	 * @return Un ResultSet de tous les objets Bien associés à un immeuble donné
	 * @throws SQLException
	 */
	public String getCompteurFromOneBienSelonType(String idBien, typeCompteur type) throws SQLException {
		String req = "{CALL getCompteurByBienAndType(?,?)};";
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
	
	public List<Integer> getReleveFromIdCompteur(String id_compteur) throws SQLException {
		String req = "{CALL getReleveFromIdCompteur(?)}";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, id_compteur);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			List<Integer> l = new LinkedList<Integer>();
			l.add((Integer) rs.getInt(1));
			l.add((Integer) rs.getInt(2));
			return l;
		}
		return null;
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
		String req = "{CALL setNouvelleProvision(?,?,?)}";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, id_bien);
		stmt.setDate(2, date_debut);
		stmt.setFloat(3, provision);
		stmt.executeQuery();
	}
}