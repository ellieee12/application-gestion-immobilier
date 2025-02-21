package test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Batiment;
import modele.BienDAO;
import modele.Compteur;
import modele.CompteurDAO;
import modele.DAOException;
import modele.ImmeubleDAO;
import modele.Logement;
import modele.MySQLCon;
import modele.Releve;
import modele.ReleveDAO;

public class TestReleveDAO {
	
	private ImmeubleDAO immeubleDAO;
	private BienDAO bienDAO;
	private CompteurDAO compteurDAO;
	private ReleveDAO releveDAO;
	
	private Releve releve1;
	private Compteur compteur1;
	private Compteur compteur2;
	
	private int idcomp1;
	private int idcomp2;
	private Releve releve2;
	
	@Before
	public void setUp() throws DAOException {
		MySQLCon.getInstance().setAutocommit(false);
		MySQLCon.getInstance().rollback();
		this.compteurDAO = new CompteurDAO();
		this.bienDAO = new BienDAO();
		this.immeubleDAO = new ImmeubleDAO();
		this.releveDAO = new ReleveDAO();
		compteur1 = new Compteur(Compteur.typeCompteur.EAU, 0.99f);
		compteur2 = new Compteur(Compteur.typeCompteur.ELECTRICITE, 0.88f);
		Batiment bat = new Batiment("testBat001", "Rue test1", "99999", "TEST1", "1990-2000");
		Logement log = new Logement(Date.valueOf("2004-01-12"), "testBien001", 3, 5,21.0f,200.0f);
		this.immeubleDAO.ajouterImmeuble(bat);
		this.bienDAO.ajouterBien(log, bat.getId_immeuble());
		this.compteurDAO.ajouterCompteur(compteur1, "testBien001");
		this.compteurDAO.ajouterCompteur(compteur2, "testBien001");
		this.releve1 = new Releve(2025, 123);
		this.releve2 = new Releve(2025, 321);
		this.idcomp1 = this.compteurDAO.getCompteurFromOneBienSelonType("testBien001", this.compteur1.getTypecomp());
		this.idcomp2 = this.compteurDAO.getCompteurFromOneBienSelonType("testBien001", this.compteur2.getTypecomp());
		this.releveDAO.ajouterReleve(this.releve1, this.idcomp1);
		this.releveDAO.ajouterReleve(this.releve2, this.idcomp2);
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
	public void testAjouterReleve() throws DAOException {
		assertTrue(this.releveDAO.releveExists(idcomp1, this.releve1.getDate_releve()));
		assertTrue(this.releveDAO.releveExists(idcomp2, this.releve2.getDate_releve()));
	}
	
	@Test
	public void testSupprimerReleve() throws DAOException {
		this.releveDAO.supprimerRelever(this.releve1.getDate_releve(), this.idcomp1);
		assertFalse(this.releveDAO.releveExists(idcomp1, this.releve1.getDate_releve()));
		assertTrue(this.releveDAO.releveExists(idcomp2, this.releve2.getDate_releve()));
	}
	
	@Test
	public void testGetReleveFromIdCompteur() throws DAOException {
		assertEquals(123, this.releveDAO.getReleveFromIdCompteur(idcomp1, this.releve1.getDate_releve()));
		assertEquals(321, this.releveDAO.getReleveFromIdCompteur(idcomp2, this.releve2.getDate_releve()));
	}

	@Test
	public void testGetReleveFromIdCompteurNonExistant() throws DAOException {
		assertEquals(0, this.releveDAO.getReleveFromIdCompteur(-1, 2000));
	}
}
