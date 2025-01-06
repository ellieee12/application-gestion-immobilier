package modele;

import java.util.Objects;

public class Maison extends Immeuble {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(logement);
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
		Maison other = (Maison) obj;
		return Objects.equals(logement, other.logement);
	}

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
