package modeleDAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	 * Récupère le compteur associés à un bien donné selon son type
	 * @param identifiant d'un immeuble donné
	 * @return Un ResultSet de tous les objets Bien associés à un immeuble donné
	 * @throws SQLException
	 */
	public ResultSet getCompteurFromOneBienSelonType(String idBien, typeCompteur type) throws SQLException {
		String req = "{CALL getCompteurByBienAndType(?,?)};";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, idBien);
		stmt.setString(2, type.getDénomination());
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	
	public ResultSet getTypeImmeubleFromIdBien(String id_bien) throws SQLException {
		String req = "{CALL getTypeImmeubleFromBien(?)}";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, id_bien);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	
	public ResultSet getReleveFromIdCompteur(String id_compteur) throws SQLException {
		String req = "{CALL getReleveFromIdCompteur(?)}";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, id_compteur);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
}