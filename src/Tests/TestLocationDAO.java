package Tests;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Batiment;
import modele.BienDAO;
import modele.DAOException;
import modele.Garage;
import modele.ImmeubleDAO;
import modele.Locataire;
import modele.LocataireDAO;
import modele.Location;
import modele.LocationDAO;
import modele.Logement;
import modele.Maison;

public class TestLocationDAO {
	
	private Location location1;
	private Location location2;
	private Location location3;
	private LocationDAO locationDAO;
	private BienDAO bienDAO;
	private ImmeubleDAO immeubleDAO;
	private LocataireDAO locataireDAO;
	
	@Before
	public void setUp() throws DAOException, SQLException {
		this.locationDAO = new LocationDAO();
		bienDAO = new BienDAO();
		immeubleDAO = new ImmeubleDAO();
		locataireDAO = new LocataireDAO();
		
		Locataire locataire = new Locataire("Jane", "Mary", "+33987654321", "maryjane@gmail.com", "mary123", Date.valueOf("2002-08-27"));
		Locataire locataireM = new Locataire("Doe", "John", "+33123456789", "johndoe@gmail.com", "johnnyboy", Date.valueOf("2004-01-12"));
		Batiment bat = new Batiment("testBat001", "Rue test1", "99999", "TEST1", "1990-2000");
		Maison maison = new Maison("testBat002",  "Rue test2", "12345", "TEST2", "1990-2000");
		Logement log = new Logement(Date.valueOf("2004-01-12"), "testBien001", 3, 5,21.0f,200.0f);
		Garage garage = new Garage(Date.valueOf("2004-02-12"), "testBien002", 100.0f);
		Logement logM = new Logement(Date.valueOf("2004-03-12"), "testBien003", 3,5,21.0f,200.0f);
		this.location1 = new Location(Date.valueOf("2023-06-23"),false,36,400.0f,100.0f,200.0f,"testBien001");
		this.location2 = new Location(Date.valueOf("2023-06-23"),false,36,100.0f,20.0f,50.0f,"testBien002");
		this.location3 = new Location(Date.valueOf("2024-01-25"),false,40,500.0f,150.0f,200.0f,"testBien003");
		
		immeubleDAO.ajouterImmeuble(bat);
		immeubleDAO.ajouterImmeuble(maison);
		locataireDAO.ajouterLocataire(locataire);
		locataireDAO.ajouterLocataire(locataireM);
		bienDAO.ajouterBien(garage, bat.getId_immeuble());
		bienDAO.ajouterBien(log, bat.getId_immeuble());
		bienDAO.ajouterBien(logM, maison.getId_immeuble());
		this.locationDAO.ajouterLocation(log.getId_bien(), locataire.getId_locataire(), location1);
		this.locationDAO.ajouterLocation(garage.getId_bien(), locataire.getId_locataire(), location2);
		this.locationDAO.ajouterLocation(logM.getId_bien(),locataireM.getId_locataire(),location3);
	}
	
	@After
	public void tearDown() throws DAOException, SQLException {
		if (this.locationDAO.locationExists("testBien001", "mary123",Date.valueOf("2023-06-23"))) {
			this.locationDAO.supprimerLocation("testBien001", Date.valueOf("2023-06-23"));
		}
		if(this.locationDAO.locationExists("testBien002", "mary123",Date.valueOf("2023-06-23"))){
			this.locationDAO.supprimerLocation("testBien002", Date.valueOf("2023-06-23"));
		}
		if(this.locationDAO.locationExists("testBien002", "johnnyboy", Date.valueOf("2024-01-25"))) {
			this.locationDAO.supprimerLocation("testBien003", Date.valueOf("2024-01-25"));
		}
		if (this.bienDAO.bienExiste("testBien001")) {
			this.bienDAO.supprimerBien("testBien001");
		}
		if (this.bienDAO.bienExiste("testBien002")) {
			this.bienDAO.supprimerBien("testBien002");
		}
		if (this.bienDAO.bienExiste("testBien003")) {
			this.bienDAO.supprimerBien("testBien003");
		}
		if (this.immeubleDAO.immeubleExiste("testBat001")) {
			this.immeubleDAO.supprimerImmeuble("testBat001");
		}
		if (this.immeubleDAO.immeubleExiste("testBat002")) {
			this.immeubleDAO.supprimerImmeuble("testBat002");
		}
		if (this.locataireDAO.locataireExists("johnnyboy")) {
			this.locataireDAO.supprimerLocataire("johnnyboy");
		}
		if (this.locataireDAO.locataireExists("mary123")) {
			this.locataireDAO.supprimerLocataire("mary123");
		}
		this.location1=null;
		this.location2=null;
		this.location3=null;
		this.locationDAO=null;
		this.locataireDAO=null;
		this.bienDAO=null;
		this.immeubleDAO=null;
	}
	
	@Test
	public void testGetAllLocation() throws DAOException {
		List<Location> listeAll = this.locationDAO.getAllLocations();
		assertEquals(location1, listeAll.get(0));
		assertEquals(location2, listeAll.get(1));
		assertEquals(location3, listeAll.get(2));
	}

}
