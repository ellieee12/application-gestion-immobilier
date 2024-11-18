package modeleDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImmeubleDAO {
	private MySQLCon mySQLCon;
	
	public ImmeubleDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	public ResultSet getAllImmeubles() throws SQLException {
		String req = "select * from immeuble";
		Statement stmt = this.mySQLCon.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(req);
		return rs;
	}
	
	public ResultSet getInfoImmeuble(String idImmeuble) throws SQLException {
		String req = "select adresse,code_postale,ville from immeuble where id_immeuble = ?";
		PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
		stmt.setString(1, idImmeuble);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	
	public int supprimerImmeuble(String id) {
		try {
			String req = "delete from immeuble where id_immeuble=?";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id);
			int nbLignesSupprimees = stmt.executeUpdate();
			stmt.close();
			return nbLignesSupprimees;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	
	public int ajouterImmeuble(String id_immeuble,String adresse, String cp, String ville, String periode_construction,String type_immeuble) {
		try {
			String req = "insert into immeuble (id_immeuble,adresse,code_postale,ville,periode_construction,type_immeuble) values (?,?,?,?,?,?)";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id_immeuble);
			stmt.setString(2, adresse);
			stmt.setString(3, cp);
			stmt.setString(4,ville);
			stmt.setString(5, periode_construction);
			stmt.setString(6, type_immeuble);
			int i = stmt.executeUpdate();
			stmt.close();
			return i;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	
	public boolean immeubleExiste(String id_immeuble) {
		try {
			String req = "select * from immeuble where id_immeuble=?";
			PreparedStatement stmt =  this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id_immeuble);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		}catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}
}
