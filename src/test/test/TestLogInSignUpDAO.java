package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.DAOException;
import modele.LogInSignUpDAO;
import modele.MySQLCon;

public class TestLogInSignUpDAO {
	
	private LogInSignUpDAO dao;
	
	@Before
	public void setUp() throws DAOException {
		MySQLCon.getInstance().setAutocommit(false);
		MySQLCon.getInstance().rollback();
		this.dao = new LogInSignUpDAO();
		this.dao.addCompte("testCompte1", "testMDP1");
	}

	@After
	public void tearDown() throws DAOException {
		MySQLCon.getInstance().rollback();
		MySQLCon.getInstance().setAutocommit(true);
		this.dao=null;
	}
	
	@Test
	public void testCompteExists() throws DAOException {
		assertTrue(this.dao.compteExiste("testCompte1"));
	}
	
	@Test
	public void testAddCompte() throws DAOException {
		this.dao.addCompte("testCompte2", "testMDP2");
		assertTrue(this.dao.compteExiste("testCompte2"));
	}
	
	@Test
	public void testSupprimercompte() throws DAOException {
		this.dao.supprimerCompte("testCompte1");
		assertFalse(this.dao.compteExiste("testCompte1"));
	}

}
