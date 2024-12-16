package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import modele.Locataire;

public class LocataireDAO {
	private MySQLCon mySQLCon;
	private static final Logger logger = Logger.getLogger(LocataireDAO.class.getName());
	
	public LocataireDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
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
	
	public int ajouterLocataire(Locataire loc) throws DAOException {
		try {
			String req = "{CALL insertLocataire(?,?,?,?,?,?)}";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, loc.getId_locataire());
			stmt.setString(2, loc.getNom());
			stmt.setString(3,loc.getPrenom());
			stmt.setString(4,loc.getTelephone());
			stmt.setString(5, loc.getMail());
			stmt.setDate(6,loc.getDate_naissance());
			int i = stmt.executeUpdate();
			stmt.close();
			return i;
		}catch(SQLException e){
			logger.log(Level.SEVERE,"Erreurs lors de l'ajout d'un locataire",e);
			throw new DAOException("Erreurs lors de l'ajout d'un locataire",e);
		}
	}
	
	
	public ResultSet getLocataireById(String id_locataire) throws DAOException {
		try {
			String req = "{CALL getLocataireById(?)}";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_locataire);
			ResultSet rs = stmt.executeQuery();
			return rs;
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération d'un locataire par son identifiant",e);
			throw new DAOException("Erreurs lors de la récupération d'un locataire par son identifiant",e);
		}
	}
	
	public boolean locataireExists(String id_locataire) throws DAOException, SQLException {
		try {
			ResultSet rs = getLocataireById(id_locataire);
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (DAOException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la vérification de l'existance d'un locataire",e);
			throw new DAOException("Erreurs lors de la vérification de l'existance d'un locataire",e);
		}
	}
	
}
