package modele;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalInt;

public class Batiment extends Immeuble {
	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(biens);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Batiment other = (Batiment) obj;
		return Objects.equals(biens, other.biens);
	}

	private Map<String,Bien> biens;

	public Batiment(String id_immeuble, String adresse, String cp, String ville, String periode_construction) {
		super(adresse, cp, ville, id_immeuble, periode_construction);
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
