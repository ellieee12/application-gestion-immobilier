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
	
	public float calculerMontantEauMaison(int conso) {
		if (this.typecomp != typeCompteur.EAU) {
	        throw new IllegalStateException("Calcul de montant eau non applicable pour un compteur de type: " + this.typecomp.getDénomination());
	    }
		return (float) (conso*this.getPrix_abonnement() + 176.16);
	}
	
	public float calculerMontantEauBatiment(int conso) {
		if (this.typecomp != typeCompteur.EAU) {
	        throw new IllegalStateException("Calcul de montant eau non applicable pour un compteur de type: " + this.typecomp.getDénomination());
	    }
		return (float) (conso*this.getPrix_abonnement() + 11.02);
	}
	
	public float calculerMontantElec(int conso) {
		if (this.typecomp != typeCompteur.ELECTRICITE) {
	        throw new IllegalStateException("Calcul de montant électrique non applicable pour un compteur de type: " + this.typecomp.getDénomination());
	    }
		return (float) (conso*this.getPrix_abonnement());
	}
	
}
