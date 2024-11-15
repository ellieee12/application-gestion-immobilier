package modeleDAO;

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
	
	public int supprimerBien(int id) {
		try {
			String req = "delete from bien where id_bien=?";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setInt(1, id);
			int nbLignesSupprimees = stmt.executeUpdate();
			stmt.close();
			return nbLignesSupprimees;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	
	public int ajouterBien(int num_etage,) {
		try {
			//String req =
		}
		return 0;
	}
	
}
