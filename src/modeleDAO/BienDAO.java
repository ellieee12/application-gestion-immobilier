package modeleDAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import classes.Bien;
import classes.Garage;
import classes.Logement;
/**
 * DAO pour la gestion des opérations CRUD sur les objets Bien.
 */
public class BienDAO{
	
	private final MySQLCon mySQLCon;
	private static final Logger logger = Logger.getLogger(BienDAO.class.getName());
	
	/**
	 * Constructeur prenant une connexion à la base de données
	 * 
	 */
	public BienDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	/**
	 * Récupère tous les biens immobiliers de la base de données
	 * @return Un result set de tous les objets Bien
	 * @throws DAOException 
	 * @throws SQLException
	 */
	public ResultSet getAllBiens() throws DAOException {
		try {
			String req = "{call getAllBiens()}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			return stmt.executeQuery(req);
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de tous les bien.",e);
			throw new DAOException("Erreurs lors de la récupération de tous les bien.",e);
		}
	}
	
	/**
	 * Récupère tous les biens associés à un immeuble donné
	 * @param identifiant d'un immeuble donné
	 * @return Un ResultSet de tous les objets Bien associés à un immeuble donné
	 * @throws SQLException
	 */
	public ResultSet getBiensFromOneImmeuble(String idImmeuble) throws SQLException {
		String req = "{CALL getBiensByImmeuble(?)};";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		stmt.setString(1, idImmeuble);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	
	/**
	 * Supprimer tous les biens ayant un identifiant donné
	 * @param identifiant d'un bien donné
	 * @throws DAOException 
	 */
	public void supprimerBien(String id_bien) throws DAOException {
		try {
			String req = "{CALL deleteBien(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_bien);
			int i = stmt.executeUpdate();
			System.out.println(i+" lignes supprimées");
			stmt.close();
		}catch(SQLException e){
			logger.log(Level.SEVERE,"Erreurs lors de la suppression d'un bien",e);
			throw new DAOException("Erreurs lors de la suppression d'un bien",e);
		}
	}
	
	/**
	 * Ajouter un bien enregistré dans la base de données
	 * @param Object Bien contenant toutes les informations concernées
	 * @param L'identifiant de l'immeuble auquel le bien est associé
	 * @return Le nombre des biens ajouté dans la base de données
	 * @throws DAOException 
	 */
	public void ajouterBien(Bien b, String id_immeuble) throws DAOException {
		try {
			CallableStatement stmt ;
			if (b instanceof Garage) {
				String req = "{CALL insertGarage(?,?,?,?)}";
				stmt = this.mySQLCon.getConnection().prepareCall(req);
				stmt.setString(1, b.getId_bien());
				stmt.setDate(2, b.getDate_acquisition());
				stmt.setString(3, id_immeuble);
				stmt.setFloat(4, b.getEntretienPartieCommune());
			}else {
				String req = "{CALL insertLogement(?,?,?,?,?,?,?)}";
				stmt = this.mySQLCon.getConnection().prepareCall(req);
				Logement l = (Logement) b;
				stmt.setString(1, l.getId_bien());
				stmt.setInt(2, l.getNb_pieces());
				stmt.setInt(3, l.getNum_etage().getAsInt());
				stmt.setFloat(4, l.getSurface_habitable());
				stmt.setDate(5, l.getDate_acquisition());
				stmt.setString(6, id_immeuble);
				stmt.setFloat(4, b.getEntretienPartieCommune());
			}
			int i = stmt.executeUpdate();
			stmt.close();
			System.out.println(i+" lignes ajoutées");
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la création d'un bien",e);
			throw new DAOException("Erreurs lors de la création d'un bien",e);
		}
	}
	
	/**
	 * Récupère tous les biens associés à partir d'un identifiant bien donné
	 * @param L'identifiant de l'immeuble auquel le bien est associé
	 * @return Object Bien obtenu à partir de l'identifiant bien donné
	 * @throws DAOException 
	 */
	public Bien getBienById(String id_bien) throws DAOException {
		try {
			String req = "{CALL getBienById(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_bien);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				if (rs.getString(6).equals("G")) {
					return new Garage(rs.getDate(5),rs.getString(1),rs.getFloat(7));
				}else {
					return new Logement(rs.getDate(5),rs.getString(1),rs.getInt(3),rs.getInt(2),rs.getFloat(4),rs.getFloat(7));
				}
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération du bien par son identifiant.",e);
			throw new DAOException("Erreurs lors de la récupération du bien par son identifiant.",e);
		}
		return null;
	}

	/**
	 * 
	 * @param id_bien
	 * @return
	 * @throws DAOException 
	 */
	public boolean bienExiste(String id_bien) throws DAOException {
		return this.getBienById(id_bien)!=null;
	}
	
	public void setAutoCommit(boolean b) throws SQLException {
		this.mySQLCon.getConnection().setAutoCommit(b);
	}
	
	public void rollback() throws SQLException {
		this.mySQLCon.getConnection().rollback();
	}
}
