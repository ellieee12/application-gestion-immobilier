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

public class TestCompteurDAO {
	private ImmeubleDAO immeubleDAO;
	private BienDAO bienDAO;
	private CompteurDAO compteurDAO;
	private Compteur compteur1;
	private Compteur compteur2;
	
	@Before
	public void setUp() throws DAOException {
		this.compteurDAO = new CompteurDAO();
		this.bienDAO = new BienDAO();
		this.immeubleDAO = new ImmeubleDAO();
		compteur1 = new Compteur(Compteur.typeCompteur.EAU, 0.99f);
		compteur2 = new Compteur(Compteur.typeCompteur.ELECTRICITE, 0.88f);
		Batiment bat = new Batiment("testBat001", "Rue test1", "99999", "TEST1", "1990-2000");
		Logement log = new Logement(Date.valueOf("2004-01-12"), "testBien001", 3, 5,21.0f,200.0f);
		this.immeubleDAO.ajouterImmeuble(bat);
		this.bienDAO.ajouterBien(log, bat.getId_immeuble());
		this.compteurDAO.ajouterCompteur(compteur1, "testBat001");
		this.compteurDAO.ajouterCompteur(compteur2, "testBat001");
	}
	
	@After
	public void tearDown() throws DAOException {
		if (this.compteurDAO.compteurExists(this.compteur1.getTypecomp(), "testBien001" ));
		if (this.bienDAO.bienExiste("testBien001")) {
			this.bienDAO.supprimerBien("testBien001");
		}
		if (this.immeubleDAO.immeubleExiste("testBat001")) {
			this.immeubleDAO.supprimerImmeuble("testBat001");
		}
		this.compteurDAO=null;
		this.bienDAO=null;
		this.immeubleDAO=null;
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
