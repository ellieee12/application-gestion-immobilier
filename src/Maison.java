
public class Maison extends Immeuble {

	private Bien bien;
	
	public Maison(String adresse, String cp, String ville, String id_immeuble, Bien bien) {
		super(adresse, cp, ville, id_immeuble);
		this.bien = bien;
	}

	public Bien getBien() {
		return bien;
	}

}
