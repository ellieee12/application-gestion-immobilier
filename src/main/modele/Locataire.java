package modele;
import java.sql.Date;
import java.util.Objects;

public class Locataire {
    @Override
	public int hashCode() {
		return Objects.hash(Nom, Prenom, Telephone, date_naissance, id_locataire, mail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locataire other = (Locataire) obj;
		return Objects.equals(Nom, other.Nom) && Objects.equals(Prenom, other.Prenom)
				&& Objects.equals(Telephone, other.Telephone) && Objects.equals(date_naissance, other.date_naissance)
				&& Objects.equals(id_locataire, other.id_locataire) && Objects.equals(mail, other.mail);
	}

    private String Nom;
    private String Prenom;
    private String Telephone;
    private String mail;
    private String id_locataire;
    private Date date_naissance;

	public Locataire(String nom, String prenom, String telephone, String mail, String id_locataire,
			Date date_naissance) {
		this.Nom = nom;
		this.Prenom = prenom;
		this.Telephone = telephone;
		this.mail = mail;
		this.id_locataire = id_locataire;
		this.date_naissance = date_naissance;
	}

	public String getNom() {
		return Nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public String getTelephone() {
		return Telephone;
	}

	public String getMail() {
		return mail;
	}

	public String getId_locataire() {
		return id_locataire;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

}
