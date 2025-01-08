package test;

import static org.junit.Assert.*;

import java.sql.Date;

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

public class TestFacture {
	private ImmeubleDAO immeubleDAO;
	private BienDAO bienDAO;
	private FactureDAO facDAO;
	
	@Before
	public void setUp() throws DAOException {
		this.bienDAO = new BienDAO();
		this.facDAO = new FactureDAO();
		this.immeubleDAO = new ImmeubleDAO();
		Facture facture1 = new Facture("F12345", Date.valueOf("2020-03-15"), Date.valueOf("2020-03-18"), "D12345", "Monthly Rent", 500.0f, 1000.0f, 300.0f, "testBien001");
		Facture facture2 = new Facture("F99999", Date.valueOf("2024-03-15"), Date.valueOf("2024-03-18"), "D99999", "Monthly Rent", 500.0f, 1000.0f, 300.0f, "testBien001");
		Batiment bat = new Batiment("testBat001", "Rue test1", "99999", "TEST1", "1990-2000");
		Logement log = new Logement(Date.valueOf("2004-01-12"), "testBien001", 3, 5,21.0f,200.0f);
		this.immeubleDAO.ajouterImmeuble(bat);
		this.bienDAO.ajouterBien(log, bat.getId_immeuble());
		this.facDAO.ajouterFacture(facture1);
		this.facDAO.ajouterFacture(facture2);
	}
	
	@After
	public void tearDown() throws DAOException{
		if (this.bienDAO.bienExiste("testBien001")) {
			this.bienDAO.supprimerBien("testBien001");
		}
		if (this.immeubleDAO.immeubleExiste("testBat001")) {
			this.immeubleDAO.supprimerImmeuble("testBat001");
		}
		if (this.facDAO.FactureExiste("F12345")) {
			this.facDAO.supprimerFacture("F12345");
		}
		if (this.facDAO.FactureExiste("F54321")) {
			this.facDAO.supprimerFacture("F54321");
		}
		if (this.facDAO.FactureExiste("F99999")) {
			this.facDAO.supprimerFacture("F99999");
		}
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
	public void testGetAllFacture() {
		
	}

}
