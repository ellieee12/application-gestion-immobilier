import static org.junit.Assert.assertTrue;

import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class TestBien {
	
	Bien b,b2;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		b = new Bien(TypeBien.LOGEMENT, 2,new Date(120,12,14), "1");
		b2 = new Bien(TypeBien.GARAGE, 0, new Date(120,12,14), "2");
		
	}

	@Test
	public void testType() {
			assertTrue(b.getType_bien() instanceof TypeBien);
	}
	
	@Test
	public void testLogementSurfaceNbPiece () {
		assertNotNull(b.getSurface_habitable());
		assertNotNull(b.getNb_pieces());
		
	}

}
