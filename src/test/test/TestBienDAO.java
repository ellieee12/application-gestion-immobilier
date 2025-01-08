package test;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Batiment;
import modele.Bien;
import modele.Garage;
import modele.BienDAO;
import modele.Compteur;
import modele.CompteurDAO;
import modele.DAOException;
import modele.ImmeubleDAO;
import modele.Logement;

public class TestBienDAO {
	private BienDAO bDAO;
	private ImmeubleDAO imDAO;
	private Batiment bat;
	private Garage b;
	private Logement l;
	private String idBat;
	private String idBienGarage;
	private String idBienLogement;

	@Before
	public void setUp() throws DAOException {
		this.idBat = "testImmeuble0010";
		this.idBienGarage = "testBien001";
		this.idBienLogement = "testBien002";
		
		this.bDAO= new BienDAO();
		this.imDAO = new ImmeubleDAO();
		
		this.bat = new Batiment(this.idBat, "Rue test", "99999", "TEST", "1990-2000");
		this.l = new Logement(Date.valueOf("2004-01-12"), this.idBienLogement, 3, 5,21.0f,200.0f);
		this.b = new Garage(Date.valueOf("2004-01-12"), this.idBienGarage,100.0f);
		
		this.imDAO.ajouterImmeuble(bat);
		this.bDAO.ajouterBien(l, this.idBat);
		this.bDAO.ajouterBien(b,this.idBat);
	}
	
	@After
	public void tearDown() throws DAOException {
		CompteurDAO cDAO = new CompteurDAO();
		if (cDAO.compteurExists(Compteur.typeCompteur.EAU, this.idBienGarage)) {
			cDAO.supprimerCompteur(Compteur.typeCompteur.EAU, this.idBienGarage);
		}
		if (cDAO.compteurExists(Compteur.typeCompteur.ELECTRICITE, this.idBienGarage)) {
			cDAO.supprimerCompteur(Compteur.typeCompteur.ELECTRICITE, this.idBienGarage);
		}
		if (bDAO.bienExiste(idBienGarage)) {
			this.bDAO.supprimerBien(idBienGarage);
		}
		if (bDAO.bienExiste(idBienLogement)) {
			this.bDAO.supprimerBien(idBienLogement);
		}
		if (imDAO.immeubleExiste(idBat)) {
			this.imDAO.supprimerImmeuble(idBat);
		}
		this.bat = null;
		this.b = null;
		this.idBat = null;
		this.idBienGarage=null;
		this.imDAO=null;
		this.bDAO=null;	
	}
	
	@Test
	public void testGetBien() throws DAOException {
		assertTrue(this.bDAO.bienExiste(this.idBienGarage));
		assertTrue(this.bDAO.bienExiste(idBienLogement));
	}
	
	@Test
	public void testGetAllBien() throws DAOException {
		List<Bien> liste = this.bDAO.getAllBiens();
		assertTrue(liste.contains(this.b));
		assertTrue(liste.contains(this.l));
	}
	
	@Test
	public void testDeleteBien() throws DAOException {
		this.bDAO.supprimerBien(idBienGarage);
		assertFalse(this.bDAO.bienExiste(idBienGarage));
		assertTrue(this.bDAO.bienExiste(idBienLogement));
	}
	
	@Test
	public void testGetBienById() throws DAOException {
		Bien bienGarage = this.bDAO.getBienById(idBienGarage);
		Bien bienLogement= this.bDAO.getBienById(idBienLogement);
		assertEquals(this.b,bienGarage);
		assertEquals(this.l,bienLogement);
	}
	
	@Test
	public void testGetBienByIdImmeuble() throws DAOException{
		List<Bien> liste = this.bDAO.getBiensFromOneImmeuble(idBat);
		assertEquals(2,liste.size());
		assertEquals(this.b,liste.get(0));
		assertEquals(this.l,liste.get(1));
	}

	@Test
	public void testGetEntretienFromIdBien() throws DAOException {
		assertEquals(this.b.getEntretienPartieCommune(),this.bDAO.getEntretienFromIdBien(this.idBienGarage));
		assertEquals(this.l.getEntretienPartieCommune(),this.bDAO.getEntretienFromIdBien(this.idBienLogement));
		assertNull(this.bDAO.getEntretienFromIdBien("test"));
	}
	
	@Test
	public void testAjouterBienEtCompteurs() throws DAOException {
		this.bDAO.supprimerBien(idBienGarage);
		CompteurDAO cDAO = new CompteurDAO();
		Compteur compteur1 = new Compteur(Compteur.typeCompteur.EAU, 0.99f);
		Compteur compteur2 = new Compteur(Compteur.typeCompteur.ELECTRICITE, 0.88f);
		List<Compteur> liste = new LinkedList<>(); 
		liste.add(compteur1); liste.add(compteur2);
		this.bDAO.ajouterBienEtCompteurs(b, idBat, liste);
		assertTrue(this.bDAO.bienExiste(idBienGarage));
		assertTrue(cDAO.compteurExists(Compteur.typeCompteur.EAU, this.idBienGarage));
		assertTrue(cDAO.compteurExists(Compteur.typeCompteur.ELECTRICITE, this.idBienGarage));
	}
}
