package classes;
import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("e3931666-7fc9-4eeb-858f-75a78d0c6911")
public class Locataire {
    @objid ("49dee57f-e834-4592-a53e-050a8f321a2a")
    private String Nom;

    @objid ("e92c581a-8e59-4e79-9d8b-2a64380abfec")
    private String Prenom;

    @objid ("030a52d9-fc0f-476a-896b-8d94e802705d")
    private String Telephone;

    @objid ("3cafeb40-1bd0-46a0-be95-f65d308a5a77")
    private String mail;

    @objid ("e86eee36-a513-41da-9c1d-779fcfbc4404")
    private String id_locataire;

    @objid ("5bd0c773-4941-44b3-bde8-72005abbab2d")
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
