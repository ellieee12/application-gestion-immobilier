import java.util.HashMap;
import java.util.Map;

public class Biens {
	
	private Map<String,Bien> biens;
	
	public Biens() {
		this.biens = new HashMap<String, Bien>();
	}
	
	public void addBien(Bien bien) {
		this.biens.put(bien.getId_bien(), bien);
	}
	
	public Map<String,Bien> getBiens() {
		return this.biens;
	}
}
