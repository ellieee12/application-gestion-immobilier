package classes;
import java.sql.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("2d5ad251-126f-43fe-a942-0997bcc1174c")
public class Facture {
    @objid ("8781fc6d-f8f0-4004-9630-7bd23cb2947d")
    private Date date_emission;

    @objid ("7bcf76c9-2ac7-4bed-9566-680636e622aa")
    private Date date_paiement;

    @objid ("f2e64be0-ae41-4b90-98c1-d435e71421fd")
    private String numero;

    @objid ("550731bc-af2b-4465-a289-e5f62769c495")
    private String designation;

    @objid ("4be1b203-05af-49fe-a2ed-cbf94b0aaf3a")
    private float montant;

    @objid ("04658c26-e9f5-40bb-9c8f-7b162d422cc2")
    private String numero_devis;

    @objid ("11cbc849-8b81-4a91-9443-9c1adaf71cf5")
    private float montant_reel_paye;

    @objid ("4d80e5bb-456e-45e4-a2a8-44de6b0443c2")
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

    
}
