package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Compteur;
import modele.DAOException;

public class TestCompteur {
	
	private Compteur compteurEau;
	private Compteur compteurElec;
	
	@Before
	public void setUp() throws DAOException {
		this.compteurEau = new Compteur(Compteur.typeCompteur.EAU, 1.5f);
		this.compteurElec= new Compteur(Compteur.typeCompteur.ELECTRICITE, 1.5f);

	}
	
	@After
	public void tearDown() throws DAOException {
		this.compteurEau=null;
		this.compteurElec=null;
	}
	
	@Test
	public void testMontantEauBatiment() {
		assertEquals(311.02f,this.compteurEau.calculerMontantEauBatiment(200),0.001f);
	}
	
	@Test
	public void testMontantEauMaison() {
		assertEquals(476.16f,this.compteurEau.calculerMontantEauMaison(200),0.001f);
	}
	
	@Test
	public void testMontantElec() {
		assertEquals(300f,this.compteurElec.calculerMontantElec(200),0.001f);
	}
	
	@Test (expected = IllegalStateException.class)
	public void testExceptionMontantElecTypeError() {
		this.compteurEau.calculerMontantElec(0);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testExceptionMontantEauBatimentException() {
		this.compteurElec.calculerMontantEauBatiment(0);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testExceptionMontantEauMaisonException() {
		this.compteurElec.calculerMontantEauMaison(0);
	}

}
