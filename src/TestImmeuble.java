import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestImmeuble {
	
	Immeuble i;
	
	@Before
	public void setUp() {
		i = new Immeuble("10 rue des champs", "31000", "Toulouse", "1");
	}

	@Test
	public void testType() {
		i.setType_immeuble(TypeImmeuble.BATIMENT);
		assertTrue(i.getType_immeuble() instanceof TypeImmeuble);	
	}
	
	@Test
	public void testType2() {
		i.setType_immeuble(TypeImmeuble.BIEN);
		assertTrue(i.getType_immeuble() instanceof TypeImmeuble);
	}

}
