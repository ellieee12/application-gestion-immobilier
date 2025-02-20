package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Batiment;
import modele.BienDAO;
import modele.DAOException;
import modele.Immeuble;
import modele.ImmeubleDAO;
import modele.Logement;
import modele.Maison;

public class TestImmeubleDAO {
	private ImmeubleDAO imDAO;
	private Batiment bat;
	private Maison maison;
	private String idBat;
	private String idMaison;

	@Before
	public void setUp() throws DAOException {
		this.idBat = "testImmeuble0010";
		this.idMaison = "testImmeuble0020";
		this.imDAO = new ImmeubleDAO();
		this.bat = new Batiment(this.idBat, "Rue test", "99999", "TEST1", "1990-2000");
		this.maison = new Maison(this.idMaison, "Rue test1", "12345", "TEST2", "1920-1930");
		this.imDAO.ajouterImmeuble(bat);
		this.imDAO.ajouterImmeuble(maison);
	}
	
	@After
	public void tearDown() throws SQLException, DAOException {
		if (imDAO.immeubleExiste(idBat)) {
			this.imDAO.supprimerImmeuble(idBat);
		}
		if (imDAO.immeubleExiste(idMaison)) {
			this.imDAO.supprimerImmeuble(idMaison);
		}
		this.idMaison=null;
		this.maison = null;
		this.bat = null;
		this.idBat = null;
		this.imDAO=null;
	}
	
	@Test
	public void testGetAllImmeubles() throws DAOException {
		List<Immeuble> liste = this.imDAO.getAllImmeubles();
		assertTrue(liste.contains(this.bat));
		assertTrue(liste.contains(this.maison));
	}
	
	@Test
	public void testAddImmeuble() throws DAOException {
		assertTrue(this.imDAO.immeubleExiste(idBat));
	}
	
	@Test
	public void testDeleteImmeuble() throws DAOException {
		this.imDAO.supprimerImmeuble(idBat);
		assertFalse(this.imDAO.immeubleExiste(idBat));
	}
	
	@Test
	public void testGetImmeubleById() throws SQLException, DAOException {
		Immeuble b = this.imDAO.getInfoImmeuble(idBat);
		assertEquals(this.bat, b);
	}
	
	@Test
	public void testGetImmeublesPourAjouterBienMaisonRemplie() throws DAOException{
		Logement l = new Logement(Date.valueOf("2004-01-12"), "idBien", 3, 5,21.0f,200.0f);
		BienDAO bDAO = new BienDAO();
		bDAO.ajouterBien(l, this.idMaison);
		List<Immeuble> liste = this.imDAO.getImmeublesPourAjouterBien();
		assertTrue(liste.contains(this.bat));
		assertFalse(liste.contains(this.maison));
	}
	
	@Test
	public void testGetImmeublesPourAjouterBien() throws DAOException{
		List<Immeuble> liste = this.imDAO.getImmeublesPourAjouterBien();
		assertTrue(liste.contains(this.bat));
		assertTrue(liste.contains(this.maison));
	}
	
	@Test
	public void testGetTypeImmeubleFromIdBienBatiment() throws DAOException {
		Logement l = new Logement(Date.valueOf("2004-01-12"), "idBien", 3, 5,21.0f,200.0f);
		BienDAO bDAO = new BienDAO();
		bDAO.ajouterBien(l, idBat);
		assertEquals("B",this.imDAO.getTypeImmeubleFromIdBien("idBien"));
	}
	
	@Test
	public void testGetTypeImmeubleFromIdBienMaison() throws DAOException {
		Logement l = new Logement(Date.valueOf("2004-01-12"), "idBien", 3, 5,21.0f,200.0f);
		BienDAO bDAO = new BienDAO();
		bDAO.ajouterBien(l, idMaison);
		assertEquals("M",this.imDAO.getTypeImmeubleFromIdBien("idBien"));
	}
}
