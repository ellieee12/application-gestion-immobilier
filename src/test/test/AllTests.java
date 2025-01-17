package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBienDAO.class, 
	TestImmeubleDAO.class, 
	TestLocataireDAO.class, 
	TestLocationDAO.class, 
	TestDocumentLocationDAO.class,
	TestFactureDAO.class,
	TestLogInSignUpDAO.class,
	TestCompteurDAO.class,
	TestCompteur.class})
public class AllTests {

}
