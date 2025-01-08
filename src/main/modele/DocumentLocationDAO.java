package modele;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentLocationDAO {
	private MySQLCon mySQLCon;
	private static final Logger logger = Logger.getLogger(ImmeubleDAO.class.getName());
	
	public DocumentLocationDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	/**
	 * Ajouter un document lié à une location donnée
	 * @param doc
	 * @param id_bien
	 * @param id_locataire
	 * @param date_debut
	 * @return si le document est ajouté
	 * @throws DAOException
	 */
	public boolean ajouterDocument(DocumentLocation doc, String id_bien, String id_locataire, Date date_debut) throws DAOException {
		try {
			String req = "insert into document_location(filepath, description, date_enregistrement, id_bien,id_locataire, date_debut) "
					+ "values(?,?,?,?,?,?)";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, doc.getFilePath());
			stmt.setString(2, doc.getDescription());
			stmt.setDate(3,Date.valueOf(LocalDate.now()));
			stmt.setString(4, id_bien);
			stmt.setString(5, id_locataire);
			stmt.setDate(6, date_debut);
			int i = stmt.executeUpdate();
			stmt.close();
			return i!=0;
			
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de l'ajout d'un document",e);
			throw new DAOException("Erreurs lors de l'ajout d'un document",e);
		}
	}
	
	/**
	 * Supprimer un document liée à une location donnée
	 * @param filepath
	 * @param id_bien
	 * @param date_debut
	 * @param id_locataire
	 * @return si le document est supprimé
	 * @throws DAOException
	 */
	public boolean supprimerDocument (String filepath,String id_bien, Date date_debut, String id_locataire) throws DAOException {
		try {
			String req = "delete from document_location where filepath = ? and id_bien = ? and date_debut = ? and id_locataire = ?";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, filepath);
			stmt.setString(2, id_bien);
			stmt.setDate(3, date_debut);
			stmt.setString(4, id_locataire);
			int i = stmt.executeUpdate();
			stmt.close();
			return i!=0;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de l'effacement d'un document",e);
			throw new DAOException("Erreurs lors de l'effacement d'un document",e);
		}
	}
	
	/**
	 * Vérifier qu'un document lié à une location 
	 * @param filepath
	 * @param id_bien
	 * @param date_debut
	 * @param id_locataire
	 * @return
	 * @throws DAOException
	 */
	public boolean documentExists(String filepath,String id_bien, Date date_debut, String id_locataire) throws DAOException {
		try {
			String req = "select * from document_location where filepath = ? and id_bien = ? and date_debut = ? and id_locataire = ?";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, filepath);
			stmt.setString(2, id_bien);
			stmt.setDate(3, date_debut);
			stmt.setString(4, id_locataire);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		}catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de l'effacement d'un document",e);
			throw new DAOException("Erreurs lors de l'effacement d'un document",e);
		}
	}
}
