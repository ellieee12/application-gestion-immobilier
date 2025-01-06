package modele;
import java.sql.Date;
import java.util.Objects;

public class Logement extends Bien {
	
	private float surface_habitable;
	private int nb_pieces;
	
	public Logement(Date date_acquisition, String id_bien, int num_etage, int nb_pieces, float surface_habitable, Float entretienPartieCommune) {
		super(num_etage, date_acquisition, id_bien,entretienPartieCommune);
		this.nb_pieces = nb_pieces;
		this.surface_habitable = surface_habitable;
	}

	public float getSurface_habitable() {
		return surface_habitable;
	}

	public int getNb_pieces() {
		return nb_pieces;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nb_pieces, surface_habitable);
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
		Logement other = (Logement) obj;
		return nb_pieces == other.nb_pieces
				&& Float.floatToIntBits(surface_habitable) == Float.floatToIntBits(other.surface_habitable);
	}
	
	
}
