package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import modele.MySQLCon;

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
		MySQLCon.getInstance().setAutocommit(false);
		MySQLCon.getInstance().rollback();
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
		MySQLCon.getInstance().rollback();
		MySQLCon.getInstance().setAutocommit(true);
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
		this.locationDAO.setDateRegularisation(this.location3.getIdBien(),this.location3.getDate_debut(), Date.valueOf("2024-12-25"));
		List<Location> liste = this.locationDAO.getAllLocations();
		float loyer = 0.0f;
		for (Location l : liste) {
			Calendar calendar2 = new GregorianCalendar();
			calendar2.setTime(l.getDate_debut());
			int year2 = calendar2.get(Calendar.YEAR);
			if (l.getDate_fin()==null) {
				if (year2<2024) {
					loyer+=l.getLoyer_TTC();
				}
			}else {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(l.getDate_fin());
				int year = calendar.get(Calendar.YEAR);
				if (year>2024 && year2<2024) {
					loyer+=l.getLoyer_TTC();
				}
			}
		}
		assertEquals(loyer*12,this.locationDAO.getSommeLoyers12Mois(2024),0.001);
	}
	
	@Test
	public void getLoyersTermine() throws DAOException {
	    List<List<Object>> l = this.locationDAO.getLoyersTermine(2024);
	    List<Location> liste = this.locationDAO.getAllLocations();

	    for (Location loc : liste) {
	        if (loc.getDate_fin() != null) {
	            Calendar calendarFin = new GregorianCalendar();
	            calendarFin.setTime(loc.getDate_fin());
	            int yearFin = calendarFin.get(Calendar.YEAR);
	            int monthFin = calendarFin.get(Calendar.MONTH) + 1;

	            if (yearFin == 2024) {
	                float expectedLoyer = loc.getLoyer_TTC();
	                Calendar calendarDebut = new GregorianCalendar();
	                calendarDebut.setTime(loc.getDate_debut());
	                int yearDebut = calendarDebut.get(Calendar.YEAR);
	                int monthDebut = calendarDebut.get(Calendar.MONTH) + 1;

	                // Expected row
	                List<Object> expectedList = new ArrayList<>();
	                expectedList.add(expectedLoyer);
	                expectedList.add(yearDebut);
	                expectedList.add(monthDebut);
	                expectedList.add(monthFin);

	                // Verify if expected list is in result
	                boolean found = false;
	                for (List<Object> actual : l) {
	                    float actualLoyer = (float) actual.get(0);
	                    int actualYearDebut = (int) actual.get(1);
	                    int actualMonthDebut = (int) actual.get(2);
	                    int actualMonthFin = (int) actual.get(3);

	                    if (Math.abs(actualLoyer - expectedLoyer) < 0.01 &&
	                        actualYearDebut == yearDebut &&
	                        actualMonthDebut == monthDebut &&
	                        actualMonthFin == monthFin) {
	                        found = true;
	                        break;
	                    }
	                }

	                System.out.println("Expected: " + expectedList);
	                System.out.println("Actual: " + l);
	                assertTrue("Expected list not found in result", found);
	            }
	        }
	    }
	}
	
	@Test
	public void getLoyersCommence() throws DAOException {
	    List<List<Object>> l = this.locationDAO.getLoyersCommence(2024);
	    List<Location> liste = this.locationDAO.getAllLocations(); 

	    for (Location loc : liste) {
	        Calendar calendarDebut = new GregorianCalendar();
	        calendarDebut.setTime(loc.getDate_debut());
	        int yearDebut = calendarDebut.get(Calendar.YEAR);
	        int monthDebut = calendarDebut.get(Calendar.MONTH) + 1;

	        if (yearDebut == 2024) {
	            int yearFin;
	            if (loc.getDate_fin() == null) {
	                yearFin = 0;
	            } else {
	                Calendar calendarFin = new GregorianCalendar();
	                calendarFin.setTime(loc.getDate_fin()); // FIXED: Now using correct date
	                yearFin = calendarFin.get(Calendar.YEAR);
	            }

	            if (yearFin == 0 || yearFin > 2024) {
	                List<Object> expectedList = new ArrayList<>();
	                expectedList.add(loc.getLoyer_TTC());
	                expectedList.add(monthDebut);

	                boolean found = false;
	                for (List<Object> actual : l) {
	                    float actualLoyer = (float) actual.get(0);
	                    int actualMonth = (int) actual.get(1);

	                    if (Math.abs(actualLoyer - loc.getLoyer_TTC()) < 0.01 && actualMonth == monthDebut) {
	                        found = true;
	                        break;
	                    }
	                }

	                System.out.println("Expected: " + expectedList);
	                System.out.println("Actual: " + l);
	                assertTrue("Expected list not found in result", found);
	            }
	        }
	    }
	}
//		float resFinal = 0.0F;
//		for (int i=0;i<l.size();i++) {
//			float res = 0.0F;
//			float loyer = (float) l.get(i).get(0);
//			int moisDebut = (int) l.get(i).get(1);
//			res = loyer*(12-moisDebut+1);
//			resFinal+=res;
//		}
//		assertEquals(location3.getLoyer_TTC()*10, resFinal,0.001);
	
	
}
