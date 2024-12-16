package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocataireDAO {
	private MySQLCon mySQLCon;
	private static final Logger logger = Logger.getLogger(LocataireDAO.class.getName());
	
	public LocataireDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	/**
	 * Récupérer tous les locataires dans la base de donénes
	 * @return La liste de tous les locataires dans la base de données
	 * @throws DAOException
	 */
	public ResultSet getAllLocataires() throws DAOException {
		try {
			String req = "{CALL getAllLocataires}";
			Statement stmt = this.mySQLCon.getConnection().prepareCall(req);
			ResultSet rs = stmt.executeQuery(req);
			return rs;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de la liste des locataires",e);
			throw new DAOException("Erreurs lors de la récupération de la liste des locataires",e);
		}
	}
	/**
	 * Ajouter un locataire dans la base de données
	 * @param Locataire à ajouter
	 * @throws DAOException
	 */
	public void ajouterLocataire(Locataire loc) throws DAOException {
		try {
			String req = "{CALL insertLocataire(?,?,?,?,?,?)}";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, loc.getId_locataire());
			stmt.setString(2, loc.getNom());
			stmt.setString(3,loc.getPrenom());
			stmt.setString(4,loc.getTelephone());
			stmt.setString(5, loc.getMail());
			stmt.setDate(6,loc.getDate_naissance());
			stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e){
			logger.log(Level.SEVERE,"Erreurs lors de l'ajout d'un locataire",e);
			throw new DAOException("Erreurs lors de l'ajout d'un locataire",e);
		}
	}
	
	/**
	 * Récupérer le locataire à partir de son identifiant
	 * @param identifiant du locataire
	 * @return Le locataire concerné
	 * @throws DAOException
	 */
	public Locataire getLocataireById(String id_locataire) throws DAOException {
		try {
			String req = "{CALL getLocataireById(?)}";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_locataire);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Locataire(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(1),rs.getDate(6));
			}
			return null;
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération d'un locataire par son identifiant",e);
			throw new DAOException("Erreurs lors de la récupération d'un locataire par son identifiant",e);
		}
	}
	
	/**
	 * Vérifier si un locataire existe
	 * @param id_locataire
	 * @return boolean
	 * @throws DAOException
	 * @throws SQLException
	 */
	public boolean locataireExists(String id_locataire) throws DAOException, SQLException {
		try {
			Locataire loc = getLocataireById(id_locataire);
			return loc != null;
		} catch (DAOException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la vérification de l'existance d'un locataire",e);
			throw new DAOException("Erreurs lors de la vérification de l'existance d'un locataire",e);
		}
	}
	
	/**
	 * Supprimer un locataire à partir de son identifiant
	 * @param identifiant du locataire
	 * @throws DAOException
	 */
	public void supprimerLocataire(String idLocataire) throws DAOException {
		try {
			String req = "{CALL deleteLocataire(?)}";
			PreparedStatement stmt;
			stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, idLocataire);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la suppression de l'existance d'un locataire",e);
			throw new DAOException("Erreurs lors de la suppression de l'existance d'un locataire",e);
		}
		
	}
	
}
