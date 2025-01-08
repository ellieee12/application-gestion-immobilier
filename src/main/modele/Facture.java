package modele;
import java.sql.Date;
import java.util.Objects;

public class Facture {
    private Date date_emission;
    private Date date_paiement;
    private String numero;
    private String designation;
    private float montant;
    private String numero_devis;
	private float montant_reel_paye;
    private float imputable_locataire; 
    private String id_bien;
    
    public Facture(String numero, Date date_paiement, Date date_emission, String numero_devis, String designation, float montant_reel_paye, float montant, float imputable_locataire, String id_bien) {
    	this.date_emission = date_emission;
    	this.date_paiement = date_paiement;
    	this.numero = numero;
    	this.designation = designation;
    	this.montant = montant;
    	this.numero_devis = numero_devis;
    	this.montant_reel_paye = montant_reel_paye;
    	this.imputable_locataire = imputable_locataire;
    	this.id_bien = id_bien;
    }

    public Date getDate_emission() {
		return date_emission;
	}

	public Date getDate_paiement() {
		return date_paiement;
	}

	public String getNumero() {
		return numero;
	}

	public String getDesignation() {
		return designation;
	}

	public float getMontant() {
		return montant;
	}

	public String getNumero_devis() {
		return numero_devis;
	}

	public float getMontant_reel_paye() {
		return montant_reel_paye;
	}

	public float getImputable_locataire() {
		return imputable_locataire;
	}

	public String getId_bien() {
		return id_bien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date_emission, date_paiement, designation, id_bien, imputable_locataire, montant,
				montant_reel_paye, numero, numero_devis);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Facture other = (Facture) obj;
		return this.date_emission.equals(other.date_emission) && this.date_paiement.equals(other.date_paiement)
				&& this.designation.equals(other.designation) && this.id_bien.equals(other.id_bien)
				&& imputable_locataire == other.imputable_locataire
				&& montant == other.montant
				&& montant_reel_paye == other.montant_reel_paye
				&& this.numero.equals(other.numero)
				&& this.numero_devis.equals(other.numero_devis);
	}

}
