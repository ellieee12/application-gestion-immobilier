package modele;

public class Compteur {
    
    public enum typeCompteur {
    	EAU("eau"),ELECTRICITE("electricite");
    	private final String dénomination;
    	private typeCompteur(String dénomination) {
    		this.dénomination = dénomination;
    	}
    	public String getDénomination() {
    		return this.dénomination;
    	}
    }
    
    private typeCompteur typecomp;
    private float prix_abonnement;

	public Compteur(typeCompteur typecomp, float prix_abonnement) {
		this.typecomp = typecomp;
		this.prix_abonnement = prix_abonnement;
	}

	public float getPrix_abonnement() {
		return prix_abonnement;
	}

	public typeCompteur getTypecomp() {
		return typecomp;
	}
	
}
