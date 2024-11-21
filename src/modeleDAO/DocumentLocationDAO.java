package modeleDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Calendar;

public class DocumentLocationDAO {
	private MySQLCon mySQLCon;
	
	public DocumentLocationDAO() {
		this.mySQLCon=MySQLCon.getInstance();
	}
	
	public boolean ajouterDocument(String filepath, String description,String id_bien, String id_locataire, Date date_debut) {
		try {
			String req = "insert into document_location(filepath, description, date_enregistrement, id_bien,id_locataire, date_debut) "
					+ "values(?,?,?,?,?,?)";
			PreparedStatement stmt = this.mySQLCon.getConnection().prepareStatement(req);
			stmt.setString(1, filepath);
			stmt.setString(2, description);
			stmt.setDate(3,Date.valueOf(LocalDate.now()));
			stmt.setString(4, id_bien);
			stmt.setString(5, id_locataire);
			stmt.setDate(6, date_debut);
			int i = stmt.executeUpdate();
			return i!=0;
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
