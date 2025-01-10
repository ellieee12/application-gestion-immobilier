package modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	public List<Bien> getAllBiens() throws DAOException {
		try {
			String req = "{call getAllBiens()}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			ResultSet s = stmt.executeQuery(req);
			List<Bien> liste = new LinkedList<>();
			while(s.next()) {
				if (s.getString(6).equals("G")) {
					Garage g = new Garage(s.getDate(5),s.getString(1),s.getFloat(8));
					liste.add(g);
				}else {
					Logement l = new Logement(s.getDate(5),s.getString(1),s.getInt(3),s.getInt(2),s.getFloat(4),s.getFloat(8));
					liste.add(l);
				}
			}
			return liste;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération de tous les bien.",e);
			throw new DAOException("Erreurs lors de la récupération de tous les bien.",e);
		}
	}
	
	public void ajouterCompteur(String id_bien, Float prix_abonnement, String type_compteur) throws DAOException {
		try {
			String req = "{CALL insertCompteur(?,?,?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, id_bien);
			stmt.setFloat(2, prix_abonnement);
			stmt.setString(3, type_compteur);
			int i = stmt.executeUpdate();
			System.out.println(i+" lignes ajoutées");
			stmt.close();
		}catch(SQLException e){
			logger.log(Level.SEVERE,"Erreurs lors de l'ajout d'un compteur",e);
			throw new DAOException("Erreurs lors de l'ajout d'un compteur",e);
		}
	}	
	
	/**
	 * Récupère tous les biens associés à un immeuble donné
	 * @param identifiant d'un immeuble donné
	 * @return Un ResultSet de tous les objets Bien associés à un immeuble donné
	 * @throws DAOException
	 */
	public List<Bien> getBiensFromOneImmeuble(String idImmeuble) throws DAOException {
		try {
			String req = "{CALL getBiensByImmeuble(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setString(1, idImmeuble);
			ResultSet s = stmt.executeQuery();
			List<Bien> liste = new LinkedList<>();
			while(s.next()) {
				if (s.getString(2).equals("G")) {
					Garage g = new Garage(s.getDate(7),s.getString(1),s.getFloat(6));
					liste.add(g);
				}else {
					Logement l = new Logement(s.getDate(7),s.getString(1),s.getInt(3),s.getInt(5),s.getFloat(4),s.getFloat(6));
					liste.add(l);
				}
			}
			s.close();
			return liste;
		}catch(SQLException e){
			logger.log(Level.SEVERE,"Erreurs lors de la récupération des biens à partir d'un bien",e);
			throw new DAOException("Erreurs lors de la récupération des biens à partir d'un bien",e);
		}
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
			int i = 0;
			if (b instanceof Garage) {
				String req = "{CALL insertGarage(?,?,?,?)}";
				stmt = this.mySQLCon.getConnection().prepareCall(req);
				stmt.setString(1, b.getId_bien());
				stmt.setDate(2, b.getDate_acquisition());
				stmt.setString(3, id_immeuble);
				stmt.setFloat(4, b.getEntretienPartieCommune());
				i = stmt.executeUpdate();
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
				stmt.setFloat(7, b.getEntretienPartieCommune());
				i = stmt.executeUpdate();
			}
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
					return new Garage(rs.getDate(5),rs.getString(1),rs.getFloat(8));
				}else {
					return new Logement(rs.getDate(5),rs.getString(1),rs.getInt(3),rs.getInt(2),rs.getFloat(4),rs.getFloat(8));
				}
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération du bien par son identifiant.",e);
			throw new DAOException("Erreurs lors de la récupération du bien par son identifiant.",e);
		}
		return null;
	}

	public void ajouterBienEtCompteurs(Bien b, String id_immeuble, List<Compteur> compteurs) throws DAOException {
	    try {
	        // Désactiver l'auto-commit
	        this.mySQLCon.getConnection().setAutoCommit(false);

	        // Ajouter le bien
	        ajouterBien(b, id_immeuble);

	        // Ajouter les compteurs
	        for (Compteur compteur : compteurs) {
	            ajouterCompteur(b.getId_bien(), compteur.getPrix_abonnement(), compteur.getTypecomp().getDénomination());
	        }
	        
	        // Valider la transaction
	        this.mySQLCon.getConnection().commit();
	    } catch (SQLException e) {
	        try {
	            // Rollback en cas d'erreur
	            this.mySQLCon.getConnection().rollback();
	        } catch (SQLException rollbackEx) {
	            logger.log(Level.SEVERE, "Erreur lors du rollback", rollbackEx);
	        }
	        logger.log(Level.SEVERE, "Erreur lors de l'ajout du bien et des compteurs", e);
	        throw new DAOException("Erreur lors de l'ajout du bien et des compteurs", e);
	    } finally {
	        try {
	            // Réactiver l'auto-commit
	            this.mySQLCon.getConnection().setAutoCommit(true);
	        } catch (SQLException autoCommitEx) {
	            logger.log(Level.SEVERE, "Erreur lors de la réactivation de l'auto-commit", autoCommitEx);
	        }
	    }
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
	
	
	/**
	 * 
	 * @param annee
	 * @return 
	 * @throws DAOException 
	 */
	public float getSommeLoyers12Mois(int annee) throws DAOException {
		try {
			String req = "{CALL getSommeLoyers12Mois(?)}";
			CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
			stmt.setInt(1, annee);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getFloat(1);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE,"Erreurs lors de la récupération des loyers.",e);
			throw new DAOException("Erreurs lors de la récupération des loyers.",e);
		}
		return 0;
	}

}