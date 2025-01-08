package modele;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class LogInSignUpDAO {

    private MySQLCon mySQLCon;

    public LogInSignUpDAO() {
        this.mySQLCon = MySQLCon.getInstance();
    }

    public boolean compteExiste(String username){
        try {
            String req = "{CALL getCompteByUsernameMdp(?)}";
            CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public void addCompte(String username, String mdp) {
        try {

            String hashedPassword = HashUtil.hashMD5(mdp);
            String req = "{CALL insertCompte(?,?)}"; 
            CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req); 
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean mdpCorrect(String username, String mdp) {
        try {
            String req = "{CALL getMdpByUsername(?)}";
            CallableStatement stmt = this.mySQLCon.getConnection().prepareCall(req);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString(1);
                 return HashUtil.hashMD5(mdp).equals(hashedPassword);
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}