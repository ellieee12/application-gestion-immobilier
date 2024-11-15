package classes;
import java.util.Date;
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

    @objid ("e01ca968-e22f-4f09-be7c-c79b58a05e19")
    private String mode_paiement;

    @objid ("04658c26-e9f5-40bb-9c8f-7b162d422cc2")
    private String numero_devis;

    @objid ("11cbc849-8b81-4a91-9443-9c1adaf71cf5")
    private float montant_reel_paye;

    @objid ("4d80e5bb-456e-45e4-a2a8-44de6b0443c2")
    private boolean imputable_locataire;
    
    public Facture(Date date_emission, Date date_paiement, String numero, String designation, float montant, String mode_paiement, String numero_devis, float montant_reel_paye, boolean imputable_locataire) {
    	this.date_emission = date_emission;
    	this.date_paiement = date_paiement;
    	this.numero = numero;
    	this.designation = designation;
    	this.montant = montant;
    	this.mode_paiement = mode_paiement;
    	this.numero_devis = numero_devis;
    	this.montant_reel_paye = montant_reel_paye;
    	this.imputable_locataire = imputable_locataire;
    }
    
}
