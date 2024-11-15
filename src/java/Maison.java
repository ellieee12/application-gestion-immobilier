package java;

public class Maison extends Immeuble {

	private Logement logement;
	
	public Maison(String adresse, String cp, String ville, String id_immeuble) {
		super(adresse, cp, ville, id_immeuble);
	}
	
	public void setLogement(Logement logement) {
		this.logement = logement;
	}

	public Bien getLogement() {
		return logement;
	}

}
