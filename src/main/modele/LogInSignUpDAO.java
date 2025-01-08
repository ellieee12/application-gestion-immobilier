package modele;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogInSignUpDAO {

    private MySQLCon mySQLCon;
    private static final Logger logger = Logger.getLogger(ImmeubleDAO.class.getName());
    
    public LogInSignUpDAO() {
        this.mySQLCon = MySQLCon.getInstance();
    }

    public boolean compteExiste(String username) throws DAOException{
        try {
            String req = "{CALL getCompteByUsernameMdp(?)}";
            CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
        	logger.log(Level.SEVERE,"Erreurs lors de la vérification de l'existence du compte",e);
			throw new DAOException("Erreurs lors de la vérification de l'existence du compte",e);
        }
    }

    public void addCompte(String username, String mdp) throws DAOException {
        try {

            String hashedPassword = HashUtil.hashMD5(mdp);
            String req = "{CALL insertCompte(?,?)}"; 
            CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req); 
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
        	logger.log(Level.SEVERE,"Erreurs lors de l'ajoute d'un compte",e);
			throw new DAOException("Erreurs lors de l'ajoute d'un compte",e);
        }
    }

    public boolean mdpCorrect(String username, String mdp) throws DAOException {
        try {
            String req = "{CALL getMdpByUsername(?)}";
            CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString(1);
                 return HashUtil.hashMD5(mdp).equals(hashedPassword);
            }
        }catch (SQLException e) {
        	logger.log(Level.SEVERE,"Erreurs lors de la vérification du compte",e);
			throw new DAOException("Erreurs lors de la vérification du compte",e);
        }
		return false;
    }
    
    public void supprimerCompte(String username) throws DAOException {
    	try {
    		String req = "delete from signup where username = ?";
    		PreparedStatement st = this.mySQLCon.getConnection().prepareStatement(req);
    		st.setString(1, username);
    		st.executeUpdate();
    		st.close(); 
    	}catch (SQLException e) {
        	logger.log(Level.SEVERE,"Erreurs lors de l'effacement du compte",e);
			throw new DAOException("Erreurs lors de l'effacement du compte",e);
        }
    }
}