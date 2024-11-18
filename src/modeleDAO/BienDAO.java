package modeleDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BienDAO{
	
	private MySQLCon mySQLCon;
	
	public BienDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	public ResultSet getAllBiens() throws SQLException {
		String req = "select id_bien,type_bien,numero_etage,surface_habitable,nb_pieces,date_acquisition from bien";
		Statement stmt = this.mySQLCon.getConnection().createStatement();
		return stmt.executeQuery(req);
	}
	
	public ResultSet getBiensFromOneImmeuble(String idImmeuble) throws SQLException {
		String req = "select id_bien,type_bien,numero_etage,surface_habitable,nb_pieces,date_acquisition from bien where id_immeuble = ?;";
		PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
		stmt.setString(1, idImmeuble);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	
	public int supprimerBien(String id) {
		try {
			String req = "delete from bien where id_bien=?";
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
	
	public int ajouterBien(int num_etage, Date date_acquisition,String id_bien,int nb_pieces, float surface_habitable, String id_immeuble,String type_bien) {
		try {
			String req = "insert into bien (id_bien,nb_pieces,numero_etage,surface_habitable,date_acquisition,type_bien,id_immeuble) values (?,?,?,?,?,?,?)";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id_bien);
			stmt.setInt(2, nb_pieces);
			stmt.setInt(3, nb_pieces);
			stmt.setFloat(4,surface_habitable);
			stmt.setDate(5, date_acquisition);
			stmt.setString(6, type_bien);
			stmt.setString(7,id_immeuble);
			int i = stmt.executeUpdate();
			stmt.close();
			return i;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	
	public boolean bienExiste(String id_bien) {
		try {
			String req = "select * from bien where id_bien=?";
			PreparedStatement stmt =  this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, id_bien);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		}catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}
}
