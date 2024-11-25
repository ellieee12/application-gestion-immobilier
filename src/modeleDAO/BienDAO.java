package modeleDAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Bien;
import classes.Garage;
import classes.Logement;
/**
 * DAO pour la gestion des opérations CRUD sur les objets Bien.
 */
public class BienDAO{
	
	private final MySQLCon mySQLCon;
	
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
	 * @throws SQLException
	 */
	public ResultSet getAllBiens() throws SQLException {
//		String req = "select id_bien,type_bien,numero_etage,surface_habitable,nb_pieces,date_acquisition from bien";
		String req = "{call getAllBiens()}";
		CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
		return stmt.executeQuery(req);
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
	 */
	public void supprimerBien(String id_bien) {
		try {
			String req = "{CALL deleteBien(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_bien);
			int i = stmt.executeUpdate();
			System.out.println(i+" lignes supprimées");
			stmt.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	/**
	 * Ajouter un bien enregistré dans la base de données
	 * @param Object Bien contenant toutes les informations concernées
	 * @param L'identifiant de l'immeuble auquel le bien est associé
	 * @return Le nombre des biens ajouté dans la base de données
	 */
	public int ajouterBien(Bien b, String id_immeuble) {
		try {
			CallableStatement stmt ;
			if (b instanceof Garage) {
				String req = "{CALL insertGarage(?,?,?)}";
				stmt = this.mySQLCon.getConnection().prepareCall(req);
				stmt.setString(1, b.getId_bien());
				stmt.setDate(2, b.getDate_acquisition());
				stmt.setString(3, id_immeuble);
			}else {
				String req = "{CALL insertLogement(?,?,?,?,?,?)}";
				stmt = this.mySQLCon.getConnection().prepareCall(req);
				Logement l = (Logement) b;
				stmt.setString(1, l.getId_bien());
				stmt.setInt(2, l.getNb_pieces());
				stmt.setInt(3, l.getNum_etage().getAsInt());
				stmt.setFloat(4, l.getSurface_habitable());
				stmt.setDate(5, l.getDate_acquisition());
				stmt.setString(6, id_immeuble);
			}
			int i = stmt.executeUpdate();
			stmt.close();
			return i;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	/**
	 * Récupère tous les biens associés à partir d'un identifiant bien donné
	 * @param L'identifiant de l'immeuble auquel le bien est associé
	 * @return Object Bien obtenu à partir de l'identifiant bien donné
	 */
	public Bien getBienById(String id_bien) {
		try {
			String req = "{CALL getBienById(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_bien);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				if (rs.getString(6).equals("G")) {
					return new Garage(rs.getDate(5),rs.getString(1));
				}else {
					return new Logement(rs.getDate(5),rs.getString(1),rs.getInt(3),rs.getInt(2),rs.getFloat(4));
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * 
	 * @param id_bien
	 * @return
	 */
	public boolean bienExiste(String id_bien) {
		return this.getBienById(id_bien)!=null;
	}
	
	public void setAutoCommit(boolean b) throws SQLException {
		this.mySQLCon.getConnection().setAutoCommit(b);
	}
	
	public void rollback() throws SQLException {
		this.mySQLCon.getConnection().rollback();
	}
}
