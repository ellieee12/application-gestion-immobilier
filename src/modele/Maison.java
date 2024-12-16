package modele;

public class Maison extends Immeuble {

	private Logement logement;
	
	public Maison( String id_immeuble, String adresse, String cp, String ville, String periode_construction) {
		super(adresse, cp, ville, id_immeuble,periode_construction);
	}
	
	public void setLogement(Logement logement) {
		this.logement = logement;
	}

	public Bien getLogement() {
		return logement;
	}

}
