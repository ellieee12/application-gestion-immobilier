package modeleDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocataireDAO {
	private MySQLCon mySQLCon;
	
	public LocataireDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	public ResultSet getAllLocataires() throws SQLException {
		String req = "select * from locataire";
		Statement stmt = this.mySQLCon.getConnection().createStatement();
		return stmt.executeQuery(req);
	}
	
	public int ajouterLocataire(String id_locataire, String nom, String prenom, String mail, String telephone, Date date_naissance ) {
		try {
			String req = "insert into locataire(id_locataire, nom, prenom, mail, telephone, date_naissance) values (?,?,?,?,?,?)";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id_locataire);
			stmt.setString(2, nom);
			stmt.setString(3,prenom);
			stmt.setString(4,mail);
			stmt.setString(5,telephone);
			stmt.setDate(6,date_naissance);
			
			int i = stmt.executeUpdate();
			stmt.close();
			return i;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	
	public boolean locataireExists(String id_locataire) {
		try {
			String req = "select * from locataire where id_locataire=?";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id_locataire);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		}catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}
	
}
