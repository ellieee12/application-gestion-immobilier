package modeleDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestBienDAO {
	public static void main(String[] args) throws SQLException {
		BienDAO bien = new BienDAO();
		ResultSet rs = bien.getAllBiens();
		while(rs.next())  
			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)
			+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)); 
	}
}
