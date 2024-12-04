package classes;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("79931338-1b81-4175-966c-6c9255a1c683")
public class Compteur {
    @objid ("c9c03a92-3319-4649-b998-804d3305f4e7")
    private String id_compteur;

    @objid ("7490c329-be7b-4e0b-805f-dec75168ab2f")
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

    @objid ("f69f058c-6594-4f59-85e5-dd986e29dfb6")
    private float prix_abonnement;

	public Compteur(String id_compteur, typeCompteur typecomp, float prix_abonnement) {
		this.id_compteur = id_compteur;
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
