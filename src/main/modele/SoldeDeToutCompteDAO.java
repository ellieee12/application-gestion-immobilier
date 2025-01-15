package modele;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoldeDeToutCompteDAO {
	private final MySQLCon mySQLCon;
	private static final Logger logger = Logger.getLogger(CompteurDAO.class.getName());
	
	/**
	 * Constructeur prenant une connexion à la base de données
	 */
	public SoldeDeToutCompteDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	/**
	 * Récupérer un sdtc à partir de l'identifiant d'un compteur
	 * @param id_compteur
	 * @param date_sdtc
	 * @return un sdtc
	 * @throws DAOException
	 */
	public int getSDTCFromLocation(String v_id_bien, Date v_date_debut) throws DAOException {
		try {
			String req = "{CALL getSDTCFromLocation(?,?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, v_id_bien);
			stmt.setDate(2,v_date_debut);
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
	 * Ajouter un sdtc dans la base de données
	 * @param date_sdtc
	 * @param index
	 * @param idCompteur
	 * @throws SQLException
	 */
	public void ajouterSDTC(SoldeDeToutCompte sdtc, String v_id_bien, Date v_date_debut) throws DAOException {
		try {
			CallableStatement stmt ;
			String req = "{CALL addSDTC(?,?,?)}";
			stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setDate(1,sdtc.getDate_SDTC());
			stmt.setString(2, v_id_bien);
			stmt.setDate(3, v_date_debut);
			stmt.executeUpdate();
			stmt.close();
		}catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de l'ajout d'un relevé dans la base de données",e);
			throw new DAOException("Erreurs lors de l'ajout d'un relevé dans la base de données",e);
		}
		
	}
	
	/**
	 * Supprimer un sdtc
	 * @param date_sdtc
	 * @param idCompteur
	 * @throws DAOException 
	 */
	public void supprimerSDTC(String v_id_bien, Date v_date_debut) throws DAOException {
		try {
			String req = "{call deleteSDTC(?,?)}";
			PreparedStatement st = this.mySQLCon.getConnection().prepareStatement(req);
			st.setString(1, v_id_bien);
			st.setDate(2, v_date_debut);
			st.executeUpdate();
			st.close();
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de l'effacement d'un sdtc dans la base de données",e);
			throw new DAOException("Erreurs lors de l'effacement d'un sdtc dans la base de données",e);
		}
	}
	
	/**
	 * Vérifier si un sdtc existe dans la base de données
	 * @param id_compteur
	 * @param date_sdtc
	 * @return
	 * @throws DAOException
	 */
	public boolean sdtcExists(String id_compteur, Date date_sdtc) throws DAOException {
		return this.getSDTCFromLocation(id_compteur, date_sdtc)!=0;
	}
}
