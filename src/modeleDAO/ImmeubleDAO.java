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
	
	public void supprimerImmeuble(String id) {
		try {
			String reqDeleteLouer = "delete from Louer where id_bien IN (select id_bien from Location where id_bien IN (select id_bien from bien where id_immeuble=?))";
			PreparedStatement stmtDeleteLouer = this.mySQLCon.getConnection().prepareStatement(reqDeleteLouer);
			stmtDeleteLouer.setString(1, id);
			int nbLignesSupprimeesLouer = stmtDeleteLouer.executeUpdate();
			stmtDeleteLouer.close();
			System.out.println(nbLignesSupprimeesLouer+" lignes supprimées dans le table (Louer)");
			
			String reqDeleteLocation = "delete from location where id_bien IN (select id_bien from bien where id_immeuble=?)";
			PreparedStatement stmtDeleteLocation = this.mySQLCon.getConnection().prepareStatement(reqDeleteLocation);
			stmtDeleteLocation.setString(1, id);
			int nbLignesSupprimeesLocation = stmtDeleteLocation.executeUpdate();
			stmtDeleteLocation.close();
			System.out.println(nbLignesSupprimeesLocation+" lignes supprimées dans le table (Location)");
			
			String reqDeleteBiens = "delete from bien where id_immeuble=?";
			PreparedStatement stmtDeleteBiens = this.mySQLCon.getConnection().prepareStatement(reqDeleteBiens);
			stmtDeleteBiens.setString(1, id);
			int nbLignesSupprimeesBiens = stmtDeleteBiens.executeUpdate();
			stmtDeleteBiens.close();
			System.out.println(nbLignesSupprimeesBiens+" lignes supprimées (Bien)");
			
			String reqDeleteImmeuble = "delete from immeuble where id_immeuble=?";
			PreparedStatement stmtDeleteImmeuble = this.mySQLCon.getConnection().prepareStatement(reqDeleteImmeuble);
			stmtDeleteImmeuble.setString(1, id);
			int nbLignesSupprimeesImmeuble = stmtDeleteImmeuble.executeUpdate();
			stmtDeleteImmeuble.close();
			
			
			System.out.println(nbLignesSupprimeesImmeuble+" lignes supprimées(Immeuble)");
		}catch(Exception e){
			System.out.println(e);
		}
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
	
	public ResultSet getImmeubles() throws SQLException {
		String req = "select * from immeuble "
				+ "where id_immeuble not in ("
				+ "select b.id_immeuble "
				+ "from bien b, immeuble i "
				+ "where b.id_immeuble=i.id_immeuble "
				+ "and i.type_immeuble='M' );";
		Statement stmt = this.mySQLCon.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(req);
		return rs;
	}
}
