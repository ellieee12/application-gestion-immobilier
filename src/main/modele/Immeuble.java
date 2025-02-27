package modele;
import java.util.Objects;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("53320ca9-96f2-4360-8985-3549ba7ec855")
public class Immeuble {
    @Override
	public int hashCode() {
		return Objects.hash(adresse, cp, id_immeuble, periode_construction, ville);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Immeuble other = (Immeuble) obj;
		return Objects.equals(adresse, other.adresse) && Objects.equals(cp, other.cp)
				&& Objects.equals(id_immeuble, other.id_immeuble)
				&& Objects.equals(periode_construction, other.periode_construction)
				&& Objects.equals(ville, other.ville);
	}

	@objid ("6c2d8138-7645-4d73-b8b3-976fa038dcfb")
    private String adresse;

    @objid ("eac8a24f-2847-4105-965a-0b8b59b17bff")
    private String cp;

    @objid ("11984bf7-22be-4fc9-834a-a393ee804c8b")
    private String ville;

    @objid ("8e2af955-5e2d-4e0d-9326-7b01561a7055")
    private String id_immeuble;

    @objid ("1722c1eb-dd52-4379-9fe8-56481b664377")
    private String periode_construction;
    
	public Immeuble(String adresse, String cp, String ville, String id_immeuble, String periode_construction) {
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.id_immeuble = id_immeuble;
		this.periode_construction = periode_construction;
	}

	public void setPeriode_construction(String periode_construction) {
		this.periode_construction = periode_construction;
	}

	public String getId_immeuble() {
		return this.id_immeuble;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getCp() {
		return cp;
	}

	public String getVille() {
		return ville;
	}

	public String getPeriode_construction() {
		return periode_construction;
	}
	

}
