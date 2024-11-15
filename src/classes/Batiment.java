package classes;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

public class Batiment extends Immeuble {
	
    private Map<String,Bien> biens;

	public Batiment(String adresse, String cp, String ville, String id_immeuble) {
		super(adresse, cp, ville, id_immeuble);
		this.biens = new HashMap<String, Bien>();
	}
	
	public void addBien(Bien bien) throws IllegalArgumentException {
		for (String key : this.biens.keySet()) {
			if (key==bien.getId_bien()) {
				throw new IllegalArgumentException("Cet identifiant est déjà utilisé");
			}
		}
		this.biens.put(bien.getId_bien(), bien);
	}
	
	public Map<String,Bien> getBiens() {
		return this.biens;
	}
	
	public Bien getBien(String id_bien) {
		return this.biens.get(id_bien);
	}
	
	public void setNumEtage(String IdBien, int numEtage) {
		this.biens.get(IdBien).setNum_etage(OptionalInt.of(numEtage));
	}
	
}
