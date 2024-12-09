package classes;
import java.sql.Date;

public class Garage extends Bien {

	public Garage(Date date_acquisition, String id_bien,Float entretienPartieCommune) {
		super(null, date_acquisition, id_bien,entretienPartieCommune);
	}
	
}
