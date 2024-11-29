package classes;
import java.sql.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("48f8f55c-05cf-4051-8991-3878e7155b6a")
public class Location {
	
	private String idBien;
	
    @objid ("31ca14d9-9889-4d81-8b50-04fb2870dbc2")
    private Date date_debut;
    
    @objid ("bb01d782-39bb-4323-bd19-fe9bb4151d45")
    private int colocation;

    @objid ("00988291-cf19-449a-beb2-d44abfff94ab")
    private int nb_mois;

    @objid ("e334a957-dc9d-4c44-ac42-cbcfa87dbff8")
    private float loyer_TTC;

    @objid ("8aa55c8c-cf4c-4a5a-ad56-f9d1e27e35d0")
    private float provision_chargement_TTC;

    @objid ("85039373-d4a4-48a2-9566-6400ef522a3a")
    private float caution_TTC;

    @objid ("0722a716-ccae-4173-bd3d-0cca8f62598d")
    private Date date_derniere_reg;

    @objid ("0f2f30df-8cfa-4d88-a265-1a9e7024388b")
    private int loyer_paye;

    @objid ("af4e756c-2789-43c7-ba2f-2f22246cccfe")
    private float montant_reel_paye;
 
	public Location(Date date_debut, int colocation, int nb_mois, float loyer_TTC, float provision_chargement_TTC,
			float caution_TTC, Date date_derniere_reg, int loyer_paye, float montant_reel_paye) {
		this.date_debut = date_debut;
		this.colocation = colocation;
		this.nb_mois = nb_mois;
		this.loyer_TTC = loyer_TTC;
		this.provision_chargement_TTC = provision_chargement_TTC;
		this.caution_TTC = caution_TTC;
		this.date_derniere_reg = date_derniere_reg;
		this.loyer_paye = loyer_paye;
		this.montant_reel_paye = montant_reel_paye;
	}
	
	public String getIdBien () {
		return idBien;
	}

	public Date getDate_debut() {
		return date_debut;
	}


	public int getNb_mois() {
		return nb_mois;
	}

	public float getLoyer_TTC() {
		return loyer_TTC;
	}
	
	public boolean getColocation() {
		return this.colocation == 1;
	}

	public float getProvision_chargement_TTC() {
		return provision_chargement_TTC;
	}

	public float getCaution_TTC() {
		return caution_TTC;
	}

	public Date getDate_derniere_reg() {
		return date_derniere_reg;
	}

	public boolean isLoyer_paye() {
		return this.loyer_paye == 1;
	}

	public float getMontant_reel_paye() {
		return montant_reel_paye;
	}


}
