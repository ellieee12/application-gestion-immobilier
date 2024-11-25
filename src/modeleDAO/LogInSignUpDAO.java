package modeleDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogInSignUpDAO {
	
	private MySQLCon mySQLCon;
	
	public LogInSignUpDAO() {
		this.mySQLCon = MySQLCon.getInstance();
	}
	
	public boolean compteExiste(String username, String mdp){
		try {
			String req = "Select * from signup where username = ? and mdp = ? ";
			PreparedStatement stmt =  this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, username);
			stmt.setString(2, mdp);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public 

}
