package modele;
import java.sql.Date;
import java.util.Objects;

public class Location {
	
	private String idBien;
    private Date date_debut;
    private String colocation;
    private int nb_mois;
    private float loyer_TTC;
    private float provision_chargement_TTC;
    private float caution_TTC; 
    private String idLocataire;
    private Date date_fin;
    private Date date_regularisation;
    
	@Override
	public int hashCode() {
		return Objects.hash(caution_TTC, colocation, date_debut, idBien, loyer_TTC, nb_mois, provision_chargement_TTC);
	}

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
			float caution_TTC, String idBien, String idLocataire, Date date_fin, Date date_regularisation) {
		this.date_debut = date_debut;
		this.colocation = colocation;
		this.nb_mois = nb_mois;
		this.loyer_TTC = loyer_TTC;
		this.provision_chargement_TTC = provision_chargement_TTC;
		this.caution_TTC = caution_TTC;
		this.idBien = idBien;
		this.idLocataire = idLocataire;
		this.date_fin = date_fin;
		this.date_regularisation = date_regularisation;
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
		if (this.colocation.equals(null)){
			return null;
		}
		return this.colocation;
	}

	public Float getProvision_chargement_TTC() {
		return provision_chargement_TTC;
	}

	public Float getCaution_TTC() {
		return caution_TTC;
	}
	
	public String getIdLocataire() {
		return this.idLocataire;
	}
	
	public Date getDate_fin() {
		return this.date_fin;
	}
	
	public Date getDate_regularisation() {
		return this.date_regularisation;
	}
}
