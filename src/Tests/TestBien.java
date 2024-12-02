package Tests;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import org.junit.Before;
import org.junit.Test;

import classes.Bien;
import classes.Garage;
import classes.Logement;

import static org.junit.Assert.assertNotNull;

public class TestBien {
	
	Bien b,b2;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		b = new Logement(new Date(120,12,14), "1", 2, 3, 4.5f);
		b2 = new Garage(new Date(120,12,14), "2");
		
	}

	@Test
	public void testType() {
			assertTrue(b instanceof Logement);
			assertTrue(b2 instanceof Garage);
	}
	
	@Test
	public void testLogementSurfaceNbPiece () {
		assertNotNull(((Logement) b).getSurface_habitable());
		assertNotNull(((Logement) b).getNb_pieces());
		
	}

}
