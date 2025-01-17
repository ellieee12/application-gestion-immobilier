package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
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
	public void setUp() throws DAOException {
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
		this.location1 = new Location(Date.valueOf("2023-06-23"),"Non",36,400.0f,100.0f,200.0f,"testBien001","mary123",null,null);
		this.location2 = new Location(Date.valueOf("2023-06-23"),"Non",36,100.0f,20.0f,50.0f,"testBien002","mary123",Date.valueOf("2024-06-23"),null);
		this.location3 = new Location(Date.valueOf("2024-03-25"),"Non",40,500.0f,150.0f,200.0f,"testBien003","johnnyboy",null,null);
		
		immeubleDAO.ajouterImmeuble(bat);
		immeubleDAO.ajouterImmeuble(maison);
		locataireDAO.ajouterLocataire(locataire);
		locataireDAO.ajouterLocataire(locataireM);
		bienDAO.ajouterBien(garage, bat.getId_immeuble());
		bienDAO.ajouterBien(log, bat.getId_immeuble());
		bienDAO.ajouterBien(logM, maison.getId_immeuble());
		this.locationDAO.ajouterLocation(log.getId_bien(), location1);
		this.locationDAO.ajouterLocation(garage.getId_bien(), location2);
		this.locationDAO.ajouterLocation(logM.getId_bien(),location3);
	}
	
	@After
	public void tearDown() throws DAOException {
		if (this.locationDAO.locationExists("testBien001", "mary123",Date.valueOf("2023-06-23"))) {
			this.locationDAO.supprimerLocation("testBien001", Date.valueOf("2023-06-23"));
		}
		if(this.locationDAO.locationExists("testBien002", "mary123",Date.valueOf("2023-06-23"))){
			this.locationDAO.supprimerLocation("testBien002", Date.valueOf("2023-06-23"));
		}
		if(this.locationDAO.locationExists("testBien003", "johnnyboy", Date.valueOf("2024-03-25"))) {
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
	public void testAddLocation() throws DAOException {
		assertTrue(this.locationDAO.locationExists("testBien002","mary123", Date.valueOf("2023-06-23")));
		assertTrue(this.locationDAO.locationExists("testBien003", "johnnyboy", Date.valueOf("2024-03-25")));
		assertTrue(this.locationDAO.locationExists("testBien001","mary123",Date.valueOf("2023-06-23")));
	}
	
	@Test
	public void testGetAllLocation() throws DAOException {
		List<Location> listeAll = this.locationDAO.getAllLocations();
		assertTrue(listeAll.contains(this.location1));
		assertTrue(listeAll.contains(this.location2));
		assertTrue(listeAll.contains(this.location3));
	}
	
	@Test
	public void testDeleteLocation() throws DAOException {
		this.locationDAO.supprimerLocation("testBien002",Date.valueOf("2023-06-23"));
		assertFalse(this.locationDAO.locationExists("testBien002","mary123", Date.valueOf("2023-06-23")));
		assertTrue(this.locationDAO.locationExists("testBien003", "johnnyboy", Date.valueOf("2024-03-25")));
		assertTrue(this.locationDAO.locationExists("testBien001","mary123",Date.valueOf("2023-06-23")));
	}
	
	//TODO Test getLocationById_Bien????
	
	@Test
	public void testGetProvision() throws DAOException {
		assertEquals(this.location1.getProvision_chargement_TTC(),
				this.locationDAO.getProvisionFromLocation(this.location1.getIdBien(), this.location1.getDate_debut()));
		assertEquals(this.location2.getProvision_chargement_TTC(),
				this.locationDAO.getProvisionFromLocation(this.location2.getIdBien(), this.location2.getDate_debut()));
		assertEquals(this.location3.getProvision_chargement_TTC(),
				this.locationDAO.getProvisionFromLocation(this.location3.getIdBien(), this.location3.getDate_debut()));
	}
	
	@Test
	public void testSetNouvelleProvision() throws DAOException{
		this.locationDAO.setNouvelleProvision(this.location1.getIdBien(), 
				this.location1.getDate_debut(), 989f);
		assertEquals(Float.valueOf(989),this.locationDAO.getProvisionFromLocation(this.location1.getIdBien(), this.location1.getDate_debut()));
		assertEquals(this.location2.getProvision_chargement_TTC(),
				this.locationDAO.getProvisionFromLocation(this.location2.getIdBien(), this.location2.getDate_debut()));
		assertEquals(this.location3.getProvision_chargement_TTC(),
				this.locationDAO.getProvisionFromLocation(this.location3.getIdBien(), this.location3.getDate_debut()));
	}
	
	@Test
	public void getSommeLoyers12Mois() throws DAOException{
		assertEquals(this.locationDAO.getSommeLoyers12Mois(2024), location1.getLoyer_TTC()*12,0.001);
	}
	
	@Test
	public void getLoyersTermine() throws DAOException{
		List<List<Object>> l = this.locationDAO.getLoyersTermine(2024);
		float resFinal = 0.0F;
		for (int i=0;i<l.size();i++) {
			float res = 0.0F;
			float loyer = (float) l.get(i).get(0);
			int anneeDebut = (int) l.get(i).get(1);
			int moisDebut = (int) l.get(i).get(2);
			int moisFin = (int) l.get(i).get(3);
			if (anneeDebut < 2024) {
				res = loyer*moisFin;
			} else {
				res = loyer*(moisFin-moisDebut+1);
			}
			resFinal+=res;
		}
		assertEquals(location2.getLoyer_TTC()*6, resFinal,0.001);	
	}
	
	@Test
	public void getLoyersCommence() throws DAOException{
		List<List<Object>> l = this.locationDAO.getLoyersCommence(2024);
		float resFinal = 0.0F;
		for (int i=0;i<l.size();i++) {
			float res = 0.0F;
			float loyer = (float) l.get(i).get(0);
			int moisDebut = (int) l.get(i).get(1);
			res = loyer*(12-moisDebut+1);
			resFinal+=res;
		}
		assertEquals(location3.getLoyer_TTC()*10, resFinal,0.001);
	}
	
}
