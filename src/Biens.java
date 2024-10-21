import java.util.HashMap;
import java.util.Map;

public class Biens extends IllegalArgumentException {
	
	private Map<String,Bien> biens;
	
	public Biens() {
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
	
	
}
