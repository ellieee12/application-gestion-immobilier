package modele;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImmeubleDAO {
	private MySQLCon mySQLCon;
	private static final Logger logger = Logger.getLogger(ImmeubleDAO.class.getName());
	
	public ImmeubleDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	public ResultSet getAllImmeubles() throws DAOException {
		try {
			String req = "{CALL getAllImmeubles()}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			return stmt.executeQuery(req);
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de la liste des immeubles",e);
			throw new DAOException("Erreurs lors de la récupération de la liste des immeubles",e);
		}
	}
	
	public ResultSet getInfoImmeuble(String idImmeuble) throws DAOException{
		try {
			String req = "{CALL getImmeubleById(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, idImmeuble);
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération d'un immeuble à partir de son identifiant",e);
			throw new DAOException("Erreurs lors de la récupération d'un immeuble à partir de son identifiant",e);
		}
	}
	
	public void supprimerImmeuble(String idImmeuble) throws DAOException {
		try {
			String req = "{CALL deleteImmeuble(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, idImmeuble);
			int i = stmt.executeUpdate();
			System.out.println(i+" lignes supprimées");
			stmt.close();
		}catch(SQLException e){
			logger.log(Level.SEVERE,"Erreurs lors de la suppression d'un immeuble",e);
			throw new DAOException("Erreurs lors de la suppression d'un immeuble",e);
		}
	}
	
	public void ajouterImmeuble(Immeuble immeuble) throws DAOException {
		try {
			String req = "{CALL addImmeuble(?,?,?,?,?,?)}";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, immeuble.getId_immeuble());
			stmt.setString(2, immeuble.getAdresse());
			stmt.setString(3, immeuble.getCp());
			stmt.setString(4, immeuble.getVille());
			stmt.setString(5, immeuble.getPeriode_construction());
			if (immeuble instanceof Batiment) {
				stmt.setString(6,"B");
			}else {
				stmt.setString(6, "M");
			}
			int i = stmt.executeUpdate();
			stmt.close();
			System.out.println(i+" lignes ajoutées");
		}catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la création d'un immeuble",e);
			throw new DAOException("Erreurs lors de la création d'un immeuble",e);
		}
	}
	
	public boolean immeubleExiste(String id_immeuble) throws DAOException {
		try {
			return this.getInfoImmeuble(id_immeuble).next();
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la vérification de l'existance d'un immeuble",e);
			throw new DAOException("Erreurs lors de la vérification de l'existance d'un immeuble",e);
		}
	}
	
	public ResultSet getImmeublesPourAjouterBien() throws DAOException {
		try {
			String req = "{CALL getImmeublesDisponibles()}";
			Statement stmt = this.mySQLCon.getConnection().prepareCall(req);
			ResultSet rs = stmt.executeQuery(req);
			return rs;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération des immeubles disponibles",e);
			throw new DAOException("Erreurs lors de la récupération des immeubles disponibles",e);
		}
	}
}
