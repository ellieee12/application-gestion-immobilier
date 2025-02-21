package modele;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
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
	public Integer getCompteurFromOneBienSelonType(String idBien, typeCompteur type) throws DAOException {
		try {
			String req = "{CALL getCompteurByBienAndType(?,?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, idBien);
			stmt.setString(2, type.getDénomination());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
			return null;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de l'id compteur associé à un bien donné selon son type ",e);
			throw new DAOException("Erreurs lors de la récupération de l'id compteur associé à un bien donné selon son type",e);
		}
	}
	
	/**
	 * Ajouter un compteur associé à un bien dans la base de données
	 * @param compteur
	 * @param id_bien
	 */
	public void ajouterCompteur(Compteur compteur, String id_bien) {
		try {
			String req = "insert into compteur(type_compteur,prix_abonnement,id_bien) "
					+ "values (?,?,?)";
			PreparedStatement st = this.mySQLCon.getConnection().prepareStatement(req);
			st.setString(1, compteur.getTypecomp().getDénomination());
			st.setFloat(2, compteur.getPrix_abonnement());
			st.setString(3, id_bien);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Supprimer un compteur à partir de son type de l'identifiant du bien associé
	 * @param type
	 * @param id_bien
	 */
	public void supprimerCompteur(Compteur.typeCompteur type, String id_bien) {
		try {
			String req = "delete from compteur where id_bien = ? and type_compteur = ?";
			PreparedStatement st = this.mySQLCon.getConnection().prepareStatement(req);
			st.setString(1, id_bien);
			st.setString(2, type.getDénomination());
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Vérifier si un compteur associé à un bien existe dans la base de données
	 * @param type
	 * @param id_bien
	 * @return si le compteur existe
	 * @throws DAOException
	 */
	public boolean compteurExists(Compteur.typeCompteur type, String id_bien) throws DAOException {
		return this.getCompteurFromOneBienSelonType(id_bien, type)!=null;
	}
	
}
