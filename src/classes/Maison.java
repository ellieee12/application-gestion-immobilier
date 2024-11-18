package classes;

import java.util.Date;

public class Maison extends Immeuble {

	private Logement logement;
	
	public Maison(String adresse, String cp, String ville, String id_immeuble, String periode_construction) {
		super(adresse, cp, ville, id_immeuble,periode_construction);
	}
	
	public void setLogement(Logement logement) {
		this.logement = logement;
	}

	public Bien getLogement() {
		return logement;
	}

}
