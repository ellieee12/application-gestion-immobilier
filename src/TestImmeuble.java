import static org.junit.Assert.assertTrue;

import java.Batiment;
import java.Bien;
import java.Maison;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestImmeuble {
	
	Maison m;
	Batiment b;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		m = new Maison("10 rue des champs", "31000", "Toulouse", "1", new Bien(0, new Date(120,5,1), "1"));
		b = new Batiment("10 rue des champs", "31000", "Toulouse", "1");
	}

	@Test
	public void testNature() {
		
	}

}
