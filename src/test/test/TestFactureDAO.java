package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Batiment;
import modele.BienDAO;
import modele.DAOException;
import modele.Facture;
import modele.FactureDAO;
import modele.ImmeubleDAO;
import modele.Logement;
import modele.MySQLCon;

public class TestFactureDAO {
	private ImmeubleDAO immeubleDAO;
	private BienDAO bienDAO;
	private FactureDAO facDAO;
	private Facture facture1;
	private Facture facture2;
	
	@Before
	public void setUp() throws DAOException {
		MySQLCon.getInstance().setAutocommit(false);
		MySQLCon.getInstance().rollback();
		this.bienDAO = new BienDAO();
		this.facDAO = new FactureDAO();
		this.immeubleDAO = new ImmeubleDAO();
		Batiment bat = new Batiment("testBat001", "Rue test1", "99999", "TEST1", "1990-2000");
		Logement log = new Logement(Date.valueOf("2004-01-12"), "testBien001", 3, 5,21.0f,200.0f);
		facture1 = new Facture("F12345", Date.valueOf("2020-03-15"), Date.valueOf("2020-03-18"), "D12345", "Monthly Rent", 500.0f, 1000.0f, 300.0f, "testBien001");
		facture2 = new Facture("F99999", Date.valueOf("2024-03-15"), Date.valueOf("2024-03-18"), "D99999", "Monthly Rent", 500.0f, 1000.0f, 300.0f, "testBien001");
		this.immeubleDAO.ajouterImmeuble(bat);
		this.bienDAO.ajouterBien(log, bat.getId_immeuble());
		this.facDAO.ajouterFacture(facture1);
		this.facDAO.ajouterFacture(facture2);
	}
	
	@After
	public void tearDown() throws DAOException{
		MySQLCon.getInstance().rollback();
		MySQLCon.getInstance().setAutocommit(true);
		
		this.bienDAO = null;
		this.immeubleDAO = null;
		this.facDAO = null;
	}
	
	@Test
	public void testAjouterFacture() throws DAOException {
		Facture facture = new Facture("F54321", Date.valueOf("2020-03-15"), Date.valueOf("2020-03-18"), "D12345", "Monthly Rent", 500.0f, 1000.0f, 300.0f, "testBien001");
		this.facDAO.ajouterFacture(facture);
		assertTrue(this.facDAO.FactureExiste("F54321"));
	}
	
	@Test
	public void testGetAllFacture() throws DAOException {
		List<Facture> liste = this.facDAO.getAllFactures();
		assertTrue(liste.contains(this.facture1));
		assertTrue(liste.contains(this.facture2));
	}
	
	@Test
	public void testSupprimerFacture() throws DAOException {
		this.facDAO.supprimerFacture(this.facture2.getNumero());
		assertFalse(this.facDAO.FactureExiste(this.facture2.getNumero()));
		assertTrue(this.facDAO.FactureExiste(this.facture1.getNumero()));
	}
	
	@Test
	public void testGetFactureByNumero() throws DAOException {
		assertEquals(this.facture1,this.facDAO.getFactureByNumero(this.facture1.getNumero()));
		assertEquals(this.facture2,this.facDAO.getFactureByNumero(this.facture2.getNumero()));
	}

	@Test
	public void testgetMontantTravaux()throws DAOException {
		List<Facture> liste = this.facDAO.getAllFactures();
		float total1 = 0;
		float total2 = 0;
		for (Facture f : liste) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(f.getDate_paiement());
			int year = calendar.get(Calendar.YEAR);
			System.out.println(year);
			if (year == 2020) {
				total1+=(f.getMontant_reel_paye()-f.getImputable_locataire());
			}else if(year==2024) {
				total2+=(f.getMontant_reel_paye()-f.getImputable_locataire());
			}
		}
		assertEquals(total1,this.facDAO.getMontantTravaux(2020),0.001);
		assertEquals(total2,this.facDAO.getMontantTravaux(2024),0.001);
	}
}
