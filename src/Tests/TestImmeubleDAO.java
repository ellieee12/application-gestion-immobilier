package Tests;

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

import modele.Batiment;
import modele.BienDAO;
import modele.DAOException;
import modele.Immeuble;
import modele.ImmeubleDAO;
import modele.Logement;
import modele.Maison;

public class TestImmeubleDAO {
	ImmeubleDAO imDAO;
	Batiment bat;
	Maison maison;
	String idBat;
	String idMaison;

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
	public void testGetAllImmeubles() throws DAOException, SQLException {
		ResultSet rs = this.imDAO.getAllImmeubles();
		int size = 0;
		List<Immeuble> liste = new LinkedList<>();
		while (rs.next()) {
			if (rs.getString(6).equals("B")) {
				Batiment bat = new Batiment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				liste.add(bat);
			}else {
				Maison maison = new Maison(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				liste.add(maison);
			}
			
			size++;
		}
		assertEquals(2,size);
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
		ResultSet s = this.imDAO.getInfoImmeuble(idBat);
		int size = 0;
		Batiment b= null;
		while (s.next()) {
			b = new Batiment(idBat, s.getString(1), s.getString(2), s.getString(3), s.getString(4));
			size++;
		}
		assertEquals(1,size);
		assertEquals(this.bat, b);
	}
	
	@Test
	public void testGetImmeublesPourAjouterBienMaisonRemplie() throws DAOException, SQLException {
		Logement log = new Logement(Date.valueOf("2004-01-12"), "testBien001", 3, 5,21.0f,200.0f);
		BienDAO bDAO = new BienDAO();
		bDAO.ajouterBien(log, idMaison);
		ResultSet s = this.imDAO.getImmeublesPourAjouterBien();
		int size = 0;
		Immeuble i= null;
		while (s.next()) {
			if (s.getString(6).equals("B")) {
				i = new Batiment(s.getString(1),s.getString(2),s.getString(3),s.getString(4),s.getString(5));
			}else {
				i = new Maison(s.getString(1),s.getString(2),s.getString(3),s.getString(4),s.getString(5));
			}
			size++;
		}
		assertEquals(1,size);
		assertEquals(this.bat, i);
	}
	
	@Test
	public void testGetImmeublesPourAjouterBien() throws DAOException, SQLException {
		ResultSet rs = this.imDAO.getImmeublesPourAjouterBien();
		int size = 0;
		List<Immeuble> liste = new LinkedList<>();
		while (rs.next()) {
			if (rs.getString(6).equals("B")) {
				Batiment bat = new Batiment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				liste.add(bat);
			}else {
				Maison maison = new Maison(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				liste.add(maison);
			}
			
			size++;
		}
		assertEquals(2,size);
		assertEquals(this.bat,liste.get(0));
		assertEquals(this.maison,liste.get(1));
	}
}
