import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d46244cf-338c-491f-9335-21b8b82b595f")
public class Bien {

    @objid ("3e715ebb-31aa-4e23-a548-37f88511d60d")
    private int num_etage;

    @objid ("47605313-fc13-40df-9b09-8fb5fbf85ff9")
    private Date date_acquisition;

    @objid ("d56b34c5-f27c-46b4-8270-24c7eebb90e5")
    private String id_bien;

	public Bien(int num_etage, Date date_acquisition, String id_bien) {
		this.num_etage = num_etage;
		this.date_acquisition = date_acquisition;
		this.id_bien = id_bien;
	}

	public String getId_bien() {
		return id_bien;
	}
    

}
