package modele;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactureDAO {

	private final MySQLCon mySQLCon;
	private static final Logger logger = Logger.getLogger(BienDAO.class.getName());
	
	/**
	 * Constructeur prenant une connexion à la base de données
	 * 
	 */
	public FactureDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	/**
	 * Récupère tous les biens immobiliers de la base de données
	 * @return Un result set de tous les objets Bien
	 * @throws DAOException 
	 * @throws SQLException
	 */
	public List<Facture> getAllFactures() throws DAOException {
		try {
			String req = "{call getAllFactures()}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			ResultSet rs = stmt.executeQuery(req);
			List<Facture> liste = new LinkedList<>();
			while(rs.next()) {
				liste.add(new Facture(rs.getString("numero_facture"),rs.getDate("date_paiement"),rs.getDate("date_emission"),rs.getString(4),rs.getString(5),rs.getFloat(6),rs.getFloat(7),rs.getFloat(8),rs.getString(9)));
			}
			return liste;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de toutes les factures.",e);
			throw new DAOException("Erreurs lors de la récupération de toutes les factures.",e);
		}
	}
	
	/**
	 * Supprimer une facture à partir de son numéro 
	 * @param numero_factures
	 * @throws DAOException
	 */
	public void supprimerFacture(String numero_factures) throws DAOException {
		try {
			String req = "{CALL deleteFacture(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, numero_factures);
			int i = stmt.executeUpdate();
			System.out.println(i+" lignes supprimées");
			stmt.close();
		}catch(SQLException e){
			logger.log(Level.SEVERE,"Erreurs lors de la suppression d'une facture",e);
			throw new DAOException("Erreurs lors de la suppression d'une facture",e);
		}
	}
	
	/**
	 * Ajouter une facture dans la base de données
	 * @param facture
	 * @throws DAOException
	 */
	public void ajouterFacture(Facture f) throws DAOException {
		try {
			CallableStatement stmt ;
			String req = "{CALL insertFacture(?,?,?,?,?,?,?,?,?)}";
			stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, f.getNumero());
			stmt.setDate(2, f.getDate_paiement());
			stmt.setDate(3, f.getDate_emission());
			stmt.setString(4, f.getNumero_devis());
			stmt.setString(5, f.getDesignation());
			stmt.setFloat(6, f.getMontant_reel_paye());
			stmt.setFloat(7, f.getMontant());
			stmt.setFloat(8, f.getImputable_locataire());
			stmt.setString(9, f.getId_bien());
			int i = stmt.executeUpdate();
			stmt.close();
			System.out.println(i+" lignes ajoutées");
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la création d'une facture",e);
			throw new DAOException("Erreurs lors de la création d'une facture",e);
		}
	}
	
	/**
	 * Récupérer une facture à partir de son numéro
	 * @param numero_factures
	 * @return
	 * @throws DAOException
	 */
	public Facture getFactureByNumero(String numero_factures) throws DAOException {
		try {
			String req = "{CALL getFactureByNumero(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, numero_factures);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return new Facture(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getFloat(7), rs.getFloat(8), rs.getString(9));
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de la facture par son numéro.",e);
			throw new DAOException("Erreurs lors de la récupération de la facture par son numéro.",e);
		}
		return null;
	}
	
	/**
	 * Vérifier si une facture existe dans la base de donnée à partir de son numéro
	 * @param numero_factures
	 * @return
	 * @throws DAOException
	 */
	public boolean FactureExiste(String numero_factures) throws DAOException {
		return this.getFactureByNumero(numero_factures) != null;
	}
	
	/**
	 * 
	 * @param int annee
	 * @return float : le montant des travaux moins la partie imputable au locataire
	 * @throws DAOException 
	 */
	public float getMontantTravaux(int annee) throws DAOException {
		try {
			String req = "{CALL getMontantTravaux(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setInt(1, annee);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getFloat(1);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération des travaux.",e);
			throw new DAOException("Erreurs lors de la récupération des travaux.",e);
		}
		return -1.0F;
	}
	
}
