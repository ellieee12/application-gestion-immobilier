package modeleDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestImmeubleDAO {
	public static void main(String[] args) throws SQLException {
		ImmeubleDAO immeubleDAO = new ImmeubleDAO();
		ResultSet rs = immeubleDAO.getAllImmeubles();
		while(rs.next()) {
			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)
			+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)); 
		}
		immeubleDAO.supprimerImmeuble("99");
		immeubleDAO.ajouterImmeuble("99", "Doe Street", "99999", "Ohio", "9999-9999", "B");
		
		
	}
}
