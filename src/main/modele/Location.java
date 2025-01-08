package modele;
import java.sql.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("48f8f55c-05cf-4051-8991-3878e7155b6a")
public class Location {
	
	private String idBien;
	
    @objid ("31ca14d9-9889-4d81-8b50-04fb2870dbc2")
    private Date date_debut;
    
    @objid ("bb01d782-39bb-4323-bd19-fe9bb4151d45")
    private String colocation;

    @objid ("00988291-cf19-449a-beb2-d44abfff94ab")
    private int nb_mois;

    @objid ("e334a957-dc9d-4c44-ac42-cbcfa87dbff8")
    private float loyer_TTC;

    @objid ("8aa55c8c-cf4c-4a5a-ad56-f9d1e27e35d0")
    private float provision_chargement_TTC;

    @objid ("85039373-d4a4-48a2-9566-6400ef522a3a")
    private float caution_TTC;   
    
//	@Override
//	public int hashCode() {
//		return Objects.hash(caution_TTC, colocation, date_debut, idBien, loyer_TTC, nb_mois, provision_chargement_TTC);
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return caution_TTC == other.caution_TTC
				&& colocation.equals(other.colocation) && date_debut.equals(other.date_debut)
				&& idBien.equals(other.idBien)
				&& loyer_TTC == other.loyer_TTC && nb_mois == other.nb_mois
				&& provision_chargement_TTC == other.provision_chargement_TTC;
	}

	public Location(Date date_debut, String colocation, int nb_mois, float loyer_TTC, float provision_chargement_TTC,
			float caution_TTC, String idBien) {
		this.date_debut = date_debut;
		this.colocation = colocation;
		this.nb_mois = nb_mois;
		this.loyer_TTC = loyer_TTC;
		this.provision_chargement_TTC = provision_chargement_TTC;
		this.caution_TTC = caution_TTC;
		this.idBien = idBien;
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
	
	public static String isColocationByString(String i) {
		if (i.equals("Oui")) {
			return "Oui";
		}
		return "Non";
	}
	
	public String isColocation() {
		return this.colocation;
	}

	public float getProvision_chargement_TTC() {
		return provision_chargement_TTC;
	}

	public float getCaution_TTC() {
		return caution_TTC;
	}
}
