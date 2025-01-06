package modele;
import java.sql.Date;

public class Garage extends Bien {

	public Garage(Date date_acquisition, String id_bien,Float entretienPartieCommune) {
		super(null, date_acquisition, id_bien,entretienPartieCommune);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	
	
	
}
