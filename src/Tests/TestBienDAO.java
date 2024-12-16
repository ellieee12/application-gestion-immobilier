	package Tests;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Bien;

public class TestBienDAO {
	private BienDAO bienDAO;
	private ImmeubleDAO immeubleDAO;
//	public static void main(String[] args) throws SQLException {
//		BienDAO bien = new BienDAO();
//		ResultSet rs = bien.getBiensFromOneImmeuble("1");
//		while(rs.next())  
//			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)
//			+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)); 
//		bien.supprimerBien("999");
//		bien.ajouterBien(99, null, "999", 99, 99.9f, "1", "G");
//	}
	
	@Before
	public void setUp() throws SQLException {
		this.bienDAO=new BienDAO();
		this.immeubleDAO=new ImmeubleDAO();
		this.bienDAO.setAutoCommit(false);
	}
	
	@After
	public void tearDown() {
		this.bienDAO = null;
		this.immeubleDAO=null;
	}
	
	@Test
	public void testGetBiensByIdImmeuble() throws SQLException {
		
		this.immeubleDAO.ajouterImmeuble("testImmeuble", "testAdresse", "12345", "Toulouse","", "B");
		this.bienDAO.ajouterBien(1, Date.valueOf("01/01/20040"), "Bien1", 99, 99f, "testImmeuble", "L");
		
		this.bienDAO.rollback();
	}
}
