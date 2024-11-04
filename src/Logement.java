import java.util.Date;

public class Logement extends Bien {
	
	private float surface_habitable;
	private int nb_pieces;
	
	public Logement(Date date_acquisition, String id_bien,int nb_pieces, float surface_habitable) {
		super(date_acquisition, id_bien);
		this.nb_pieces= nb_pieces;
		this.surface_habitable = surface_habitable;
	}

	public float getSurface_habitable() {
		return surface_habitable;
	}

	public int getNb_pieces() {
		return nb_pieces;
	}
	
}
