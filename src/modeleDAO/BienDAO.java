package modeleDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BienDAO{
	
	private MySQLCon mySQLCon;
	
	public BienDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	public ResultSet getAllBiens() throws SQLException {
		String req = "select id_bien, nb_pieces, numero_etage,surface_habitable,date_acquisition,type_bien from bien";
		Statement stmt = this.mySQLCon.getConnection().createStatement();
		return stmt.executeQuery(req);
	}
	
}
