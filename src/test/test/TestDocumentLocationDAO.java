package test;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Batiment;
import modele.BienDAO;
import modele.DAOException;
import modele.DocumentLocation;
import modele.DocumentLocationDAO;
import modele.ImmeubleDAO;
import modele.Locataire;
import modele.LocataireDAO;
import modele.Location;
import modele.LocationDAO;
import modele.Logement;

public class TestDocumentLocationDAO {
	
	private DocumentLocationDAO doclocDAO;
	private LocationDAO locationDAO;
	private ImmeubleDAO immeubleDAO;
	private BienDAO bienDAO;
	private LocataireDAO locataireDAO;
	
	@Before
	public void setUp() throws DAOException {
		this.doclocDAO = new DocumentLocationDAO();
		this.locationDAO=new LocationDAO();
		this.immeubleDAO= new ImmeubleDAO();
		this.bienDAO = new BienDAO();
		this.locataireDAO = new LocataireDAO();
		Locataire locataire = new Locataire("Jane", "Mary", "+33987654321", "maryjane@gmail.com", "mary123", Date.valueOf("2002-08-27"));
		Batiment bat = new Batiment("testBat001", "Rue test1", "99999", "TEST1", "1990-2000");
		Location location1 = new Location(Date.valueOf("2023-06-23"),"Non",36,400.0f,100.0f,200.0f,"testBien001");
		Logement log = new Logement(Date.valueOf("2004-01-12"), "testBien001", 3, 5,21.0f,200.0f);
		
		
		this.immeubleDAO.ajouterImmeuble(bat);
		this.locataireDAO.ajouterLocataire(locataire);
		this.bienDAO.ajouterBien(log, bat.getId_immeuble());
		this.locationDAO.ajouterLocation(log.getId_bien(), locataire.getId_locataire(), location1);
		
	}
	
	@After
	public void tearDown() throws DAOException {
		if (this.locationDAO.locationExists("testBien001", "mary123",Date.valueOf("2023-06-23"))) {
			this.locationDAO.supprimerLocation("testBien001", Date.valueOf("2023-06-23"));
		}
		if (this.bienDAO.bienExiste("testBien001")) {
			this.bienDAO.supprimerBien("testBien001");
		}
		if (this.immeubleDAO.immeubleExiste("testBat001")) {
			this.immeubleDAO.supprimerImmeuble("testBat001");
		}
		if (this.locataireDAO.locataireExists("mary123")) {
			this.locataireDAO.supprimerLocataire("mary123");
		}
		if (this.doclocDAO.documentExists((new File("./file/path/name")).getAbsolutePath(), "testBien001", Date.valueOf("2023-06-23"), "mary123")){
			this.doclocDAO.supprimerDocument((new File("./file/path/name")).getAbsolutePath(), "testBien001", Date.valueOf("2023-06-23"), "mary123");
		}
	}
	
	@Test
	public void testAjouterDocument() throws DAOException {
		DocumentLocation doc = new DocumentLocation(new File("./file/path/name"), null, Date.valueOf("2025-01-01"));
		this.doclocDAO.ajouterDocument(doc, "testBien001", "mary123", Date.valueOf("2023-06-23"));
		assertTrue(this.doclocDAO.documentExists((new File("./file/path/name")).getAbsolutePath(), "testBien001", Date.valueOf("2023-06-23"), "mary123"));
	}

}
