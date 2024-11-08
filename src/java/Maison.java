package java;

public class Maison extends Immeuble {

	private Logement logement;
	
	public Maison(String adresse, String cp, String ville, String id_immeuble, Logement logement) {
		super(adresse, cp, ville, id_immeuble);
		this.logement = logement;
	}

	public Bien getLogement() {
		return logement;
	}

}
