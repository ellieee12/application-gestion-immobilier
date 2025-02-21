package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Batiment;
import modele.BienDAO;
import modele.Compteur;
import modele.CompteurDAO;
import modele.DAOException;
import modele.Garage;
import modele.ImmeubleDAO;
import modele.Logement;
import modele.MySQLCon;

public class TestCompteurDAO {
	private ImmeubleDAO immeubleDAO;
	private BienDAO bienDAO;
	private CompteurDAO compteurDAO;
	private Compteur compteur1;
	private Compteur compteur2;
	
	@Before
	public void setUp() throws DAOException {
		MySQLCon.getInstance().setAutocommit(false);
		MySQLCon.getInstance().rollback();
		this.compteurDAO = new CompteurDAO();
		this.bienDAO = new BienDAO();
		this.immeubleDAO = new ImmeubleDAO();
		compteur1 = new Compteur(Compteur.typeCompteur.EAU, 0.99f);
		compteur2 = new Compteur(Compteur.typeCompteur.ELECTRICITE, 0.88f);
		Batiment bat = new Batiment("testBat001", "Rue test1", "99999", "TEST1", "1990-2000");
		Logement log = new Logement(Date.valueOf("2004-01-12"), "testBien001", 3, 5,21.0f,200.0f);
		this.immeubleDAO.ajouterImmeuble(bat);
		this.bienDAO.ajouterBien(log, bat.getId_immeuble());
		this.compteurDAO.ajouterCompteur(compteur1, "testBien001");
		this.compteurDAO.ajouterCompteur(compteur2, "testBien001");
	}
	
	@After
	public void tearDown() throws DAOException {
		MySQLCon.getInstance().rollback();
		MySQLCon.getInstance().setAutocommit(true);
		this.compteurDAO=null;
		this.bienDAO=null;
		this.immeubleDAO=null;
	}

	@Test
	public void testAjouterCompteur() throws DAOException {
		assertTrue(this.compteurDAO.compteurExists(this.compteur1.getTypecomp(), "testBien001"));
		assertTrue(this.compteurDAO.compteurExists(this.compteur2.getTypecomp(), "testBien001"));
	}
	
	@Test
	public void testGetCompteurFromOneBienSelonType() throws DAOException {
		assertNotNull(this.compteurDAO.getCompteurFromOneBienSelonType("testBien001", this.compteur1.getTypecomp()));
		assertNotNull(this.compteurDAO.getCompteurFromOneBienSelonType("testBien001", this.compteur2.getTypecomp()));
	}
	
	@Test 
	public void testSupprimerCompteur() throws DAOException {
		this.compteurDAO.supprimerCompteur(this.compteur2.getTypecomp(), "testBien001");
		assertFalse(this.compteurDAO.compteurExists(this.compteur2.getTypecomp(), "testBien001"));
		assertTrue(this.compteurDAO.compteurExists(this.compteur1.getTypecomp(), "testBien001"));
	}
	
	@Test
	public void testAjouterBienEtCompteurs() throws DAOException {
		MySQLCon.getInstance().setAutocommit(false);
		MySQLCon.getInstance().rollback();
		String idBatCompt = "testImmeubleCompt";
		String idBienGarageCompt = "testBienCompt";
		Batiment batCompt = new Batiment(idBatCompt, "Rue test", "99999", "TEST", "1990-2000");
		Garage g = new Garage(Date.valueOf("2024-01-12"), idBienGarageCompt,100.0f);
		this.immeubleDAO.ajouterImmeuble(batCompt);
		CompteurDAO cDAO = new CompteurDAO();
		Compteur compteur1 = new Compteur(Compteur.typeCompteur.EAU, 0.99f);
		Compteur compteur2 = new Compteur(Compteur.typeCompteur.ELECTRICITE, 0.88f);
		List<Compteur> liste = new LinkedList<>(); 
		liste.add(compteur1); liste.add(compteur2);
		this.bienDAO.ajouterBienEtCompteurs(g, idBatCompt, liste);
		assertTrue(this.bienDAO.bienExiste(idBienGarageCompt));
		assertTrue(cDAO.compteurExists(Compteur.typeCompteur.EAU, idBienGarageCompt));
		assertTrue(cDAO.compteurExists(Compteur.typeCompteur.ELECTRICITE, idBienGarageCompt));
		MySQLCon.getInstance().rollback();
		MySQLCon.getInstance().setAutocommit(true);
	}

}
