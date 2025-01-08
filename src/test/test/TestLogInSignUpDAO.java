package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.DAOException;
import modele.LogInSignUpDAO;

public class TestLogInSignUpDAO {
	
	private LogInSignUpDAO dao;
	
	@Before
	public void setUp() throws DAOException {
		this.dao = new LogInSignUpDAO();
		this.dao.addCompte("testCompte1", "testMDP1");
	}

	@After
	public void tearDown() throws DAOException {
		if (this.dao.compteExiste("testCompte1")) {
			this.dao.supprimerCompte("testCompte1");
		}
		if (this.dao.compteExiste("testCompte2")) {
			this.dao.supprimerCompte("testCompte2");
		}
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
