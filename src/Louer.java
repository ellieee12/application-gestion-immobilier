import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("48f8f55c-05cf-4051-8991-3878e7155b6a")
public class Louer {
    @objid ("31ca14d9-9889-4d81-8b50-04fb2870dbc2")
    private Date Date_debut;

    @objid ("bb01d782-39bb-4323-bd19-fe9bb4151d45")
    private String Type;

    @objid ("00988291-cf19-449a-beb2-d44abfff94ab")
    private int nb_mois;

    @objid ("e334a957-dc9d-4c44-ac42-cbcfa87dbff8")
    private float loyer_TTC;

    @objid ("8aa55c8c-cf4c-4a5a-ad56-f9d1e27e35d0")
    private int provision_chargement_TTC;

    @objid ("85039373-d4a4-48a2-9566-6400ef522a3a")
    private float caution_TTC;

    @objid ("673f9bea-a6a1-4391-989c-c6fba3e2a3f7")
    private String bail;

    @objid ("6e77ee39-435d-4483-829f-978f06898e78")
    private String etat_lieux;

    @objid ("0722a716-ccae-4173-bd3d-0cca8f62598d")
    private Date date_derniere_reg;

    @objid ("0f2f30df-8cfa-4d88-a265-1a9e7024388b")
    private boolean loyer_paye;

    @objid ("42682611-f9a6-4b58-82c4-2bce41321c5f")
    private String annee;

    @objid ("53db5251-c1c5-4a66-a092-f27c24efa711")
    private char trimestre;

    @objid ("af4e756c-2789-43c7-ba2f-2f22246cccfe")
    private float montant_reel_paye;
    
    private Locataire locataire;
    
    private ICC icc;

	public Louer(Date date_debut, String type, int nb_mois, float loyer_TTC, int provision_chargement_TTC,
			float caution_TTC, String bail, String etat_lieux, Date date_derniere_reg, boolean loyer_paye, float montant_reel_paye, Locataire locataire, ICC icc) {
		super();
		Date_debut = date_debut;
		Type = type;
		this.nb_mois = nb_mois;
		this.loyer_TTC = loyer_TTC;
		this.provision_chargement_TTC = provision_chargement_TTC;
		this.caution_TTC = caution_TTC;
		this.bail = bail;
		this.etat_lieux = etat_lieux;
		this.date_derniere_reg = date_derniere_reg;
		this.loyer_paye = loyer_paye;
		this.montant_reel_paye = montant_reel_paye;
		this.locataire = locataire;
	}

	public Date getDate_debut() {
		return Date_debut;
	}

	public String getType() {
		return Type;
	}

	public int getNb_mois() {
		return nb_mois;
	}

	public float getLoyer_TTC() {
		return loyer_TTC;
	}

	public int getProvision_chargement_TTC() {
		return provision_chargement_TTC;
	}

	public float getCaution_TTC() {
		return caution_TTC;
	}

	public String getBail() {
		return bail;
	}

	public String getEtat_lieux() {
		return etat_lieux;
	}

	public Date getDate_derniere_reg() {
		return date_derniere_reg;
	}

	public boolean isLoyer_paye() {
		return loyer_paye;
	}

	public String getAnnee() {
		return annee;
	}

	public char getTrimestre() {
		return trimestre;
	}

	public float getMontant_reel_paye() {
		return montant_reel_paye;
	}

	public Locataire getLocataire() {
		return locataire;
	}

	public ICC getIcc() {
		return icc;
	}
    
    

}
