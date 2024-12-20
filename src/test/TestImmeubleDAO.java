package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Batiment;
import modele.DAOException;
import modele.Immeuble;
import modele.ImmeubleDAO;
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
		assertEquals(2,liste.size());
		assertEquals(this.bat,liste.get(0));
		assertEquals(this.maison,liste.get(1));
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
	public void testGetImmeublesPourAjouterBienMaisonRemplie() throws DAOException, SQLException {
		List<Immeuble> liste = this.imDAO.getImmeublesPourAjouterBien();
		assertEquals(2,liste.size());
		assertEquals(this.bat, liste.get(0));
	}
	
	@Test
	public void testGetImmeublesPourAjouterBien() throws DAOException, SQLException {
		List<Immeuble> liste = this.imDAO.getImmeublesPourAjouterBien();
		assertEquals(2,liste.size());
		assertEquals(this.bat,liste.get(0));
		assertEquals(this.maison,liste.get(1));
	}
}
