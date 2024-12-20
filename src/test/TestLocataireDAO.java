package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.DAOException;
import modele.Locataire;
import modele.LocataireDAO;

public class TestLocataireDAO {

	private Locataire loc1;
	private Locataire loc2;
	private LocataireDAO locDAO;
	
	@Before
	public void setUp() throws DAOException {
		this.locDAO = new LocataireDAO();
		this.loc1 = new Locataire("Doe", "John", "+33123456789", "johndoe@gmail.com", "johnnyboy", Date.valueOf("2004-01-12"));
		this.loc2 = new Locataire("Jane", "Mary", "+33987654321", "maryjane@gmail.com", "mary123", Date.valueOf("2002-08-27"));
		this.locDAO.ajouterLocataire(loc1);
		this.locDAO.ajouterLocataire(loc2);
	}
	
	@After
	public void tearDown() throws DAOException, SQLException {
		if (locDAO.locataireExists(loc1.getId_locataire())) {
			this.locDAO.supprimerLocataire(loc1.getId_locataire());
		}
		if (locDAO.locataireExists(loc2.getId_locataire())) {
			this.locDAO.supprimerLocataire(loc2.getId_locataire());
		}
		this.locDAO=null;
		this.loc1 = null;
		this.loc2 = null;
	}

	@Test
	public void testGetAllLocataire() throws DAOException, SQLException {
		ResultSet rs = this.locDAO.getAllLocataires();
		List<Locataire> liste = new LinkedList<>();
		while (rs.next()) {
			liste.add(new Locataire(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(1),rs.getDate(6)));
		}
		assertEquals(this.loc1,liste.get(0));
		assertEquals(this.loc2,liste.get(1));
	}
	
	@Test
	public void testDeleteLocataire() throws DAOException, SQLException {
		this.locDAO.supprimerLocataire(this.loc1.getId_locataire());
		assertTrue(this.locDAO.locataireExists(this.loc2.getId_locataire()));
		assertFalse(this.locDAO.locataireExists(this.loc1.getId_locataire()));
	}

	@Test
	public void testAjouterLocataire() throws DAOException, SQLException {
		this.locDAO.ajouterLocataire(new Locataire("Doe", "Jim", "+33123456789", "jimmydoe@gmail.com", "jimmyboy", Date.valueOf("2000-05-12")));
		assertTrue(this.locDAO.locataireExists("jimmyboy"));
		this.locDAO.supprimerLocataire("jimmyboy");
	}
	
	@Test 
	public void testGetLocataireById() throws DAOException {
		assertEquals(this.loc1,this.locDAO.getLocataireById(this.loc1.getId_locataire()));
		assertEquals(this.loc2,this.locDAO.getLocataireById(this.loc2.getId_locataire()));
	}
}
