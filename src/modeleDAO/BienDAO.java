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
	
	public int ajouterBien(Integer num_etage, Date date_acquisition, String id_bien, Integer nb_pieces, 
            Float surface_habitable, String id_immeuble, String type_bien) {
		try {
			String req = "INSERT INTO bien (id_bien, nb_pieces, numero_etage, surface_habitable, date_acquisition, type_bien, id_immeuble) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
		
			stmt.setString(1, id_bien);
		
			if (nb_pieces != null) {
				stmt.setInt(2, nb_pieces);
			} else {
				stmt.setNull(2, java.sql.Types.INTEGER);
			}
			
			if (num_etage != null) {
				stmt.setInt(3, num_etage);
			} else {
				stmt.setNull(3, java.sql.Types.INTEGER);
			}
			
			if (surface_habitable != null) {
				stmt.setFloat(4, surface_habitable);
			} else {
				stmt.setNull(4, java.sql.Types.FLOAT);
			}
			
			if (date_acquisition != null) {
				stmt.setDate(5, date_acquisition);
			} else {
				stmt.setNull(5, java.sql.Types.DATE);
			}
			
			stmt.setString(6, type_bien);
			stmt.setString(7, id_immeuble);
			
			int i = stmt.executeUpdate();
			stmt.close();
			return i;
		} catch (Exception e) {
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
