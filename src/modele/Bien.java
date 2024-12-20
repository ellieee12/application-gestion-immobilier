package modele;
import java.sql.Date;
import java.util.Objects;
import java.util.OptionalInt;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d46244cf-338c-491f-9335-21b8b82b595f")
public class Bien {

    @objid ("3e715ebb-31aa-4e23-a548-37f88511d60d")
    private OptionalInt num_etage;

    @objid ("47605313-fc13-40df-9b09-8fb5fbf85ff9")
    private Date date_acquisition;

    @objid ("d56b34c5-f27c-46b4-8270-24c7eebb90e5")
    private String id_bien;
    
    private Float entretienPartieCommune;

	public Bien(Integer num_etage, Date date_acquisition, String id_bien, Float entretienPartieCommune) {
		if (num_etage == null) {
			this.num_etage = OptionalInt.empty();
		} else {
			this.num_etage = OptionalInt.of(num_etage);
		}
		this.date_acquisition = date_acquisition;
		this.id_bien = id_bien;
		this.entretienPartieCommune=entretienPartieCommune; 
	}

	public String getId_bien() {
		return id_bien;
	}

	public OptionalInt getNum_etage() {
		return num_etage;
	}

	public Date getDate_acquisition() {
		return date_acquisition;
	}
	
	public Boolean isLogement() {
		return this.getClass().equals(Logement.class);
	}

	public void setNum_etage(OptionalInt num_etage) {
		this.num_etage = num_etage;
	}
    
	public Float getEntretienPartieCommune() {
		return this.entretienPartieCommune;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date_acquisition, entretienPartieCommune, id_bien, num_etage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bien other = (Bien) obj;
		return Objects.equals(date_acquisition, other.date_acquisition)
				&& Objects.equals(entretienPartieCommune, other.entretienPartieCommune)
				&& Objects.equals(id_bien, other.id_bien) && Objects.equals(num_etage, other.num_etage);
	}

	
}
