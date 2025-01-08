package modele;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReleveDAO {
	private final MySQLCon mySQLCon;
	private static final Logger logger = Logger.getLogger(CompteurDAO.class.getName());
	
	/**
	 * Constructeur prenant une connexion à la base de données
	 */
	public ReleveDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	/**
	 * Récupérer un rélevé à partir de l'identifiant d'un compteur
	 * @param id_compteur
	 * @param annee
	 * @return un relevé
	 * @throws DAOException
	 */
	public int getReleveFromIdCompteur(String id_compteur, int annee) throws DAOException {
		try {
			String req = "{CALL getReleveFromIdCompteur(?,?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_compteur);
			stmt.setInt(2,annee);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de l'id compteur associé à un bien donné selon son type ",e);
			throw new DAOException("Erreurs lors de la récupération de l'id compteur associé à un bien donné selon son type",e);
		}
	}
	
	/**
	 * Ajouter un relevé dans la base de données
	 * @param annee
	 * @param index
	 * @param idCompteur
	 * @throws SQLException
	 */
	public void ajouterReleve(Releve releve, String idCompteur) throws DAOException {
		try {
			CallableStatement stmt ;
			String req = "{CALL addReleve(?,?,?)}";
			stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setInt(1,releve.getDate_releve());
			stmt.setInt(2, releve.getIndexcomp());
			stmt.setString(3, idCompteur);
			stmt.executeUpdate();
			stmt.close();
		}catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de l'ajout d'un relevé dans la base de données",e);
			throw new DAOException("Erreurs lors de l'ajout d'un relevé dans la base de données",e);
		}
		
	}
	
	/**
	 * Supprimer un relevé
	 * @param annee
	 * @param idCompteur
	 * @throws DAOException 
	 */
	public void supprimerRelever(int annee, String idCompteur) throws DAOException {
		try {
			String req = "delete from releve where annee=? and id_compteur=?";
			PreparedStatement st = this.mySQLCon.getConnection().prepareStatement(req);
			st.setInt(1, annee);
			st.setString(2, idCompteur);
			st.executeUpdate();
			st.close();
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de l'effacement d'un relevé dans la base de données",e);
			throw new DAOException("Erreurs lors de l'effacement d'un relevé dans la base de données",e);
		}
	}
	
	/**
	 * Vérifier si un relevé existe dans la base de données
	 * @param id_compteur
	 * @param annee
	 * @return
	 * @throws DAOException
	 */
	public boolean releveExists(String id_compteur, int annee) throws DAOException {
		return this.getReleveFromIdCompteur(id_compteur, annee)!=0;
	}
}
