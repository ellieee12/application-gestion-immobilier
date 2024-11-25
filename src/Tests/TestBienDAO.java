package Tests;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import modeleDAO.BienDAO;

public class TestBienDAO {
	BienDAO bDAO;

	@Before
	public void setUp() {
		this.bDAO= new BienDAO();
		
	}
	
	public void tearDown() {
		this.bDAO=null;	
	
	}
	
	@Test
	public void testGetBiens() {
		this.bDAO.ajouterBien(3, Date.valueOf("12/01/2004"), "testBien001", 4, 42.3f, "1", "L");
		
	}

}
