package modele;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("a009827b-e449-4a80-939e-2fdc1b038bf8")
public class Assurance {
    @objid ("3b8de39a-5af1-4a00-ab46-1074605fe8ee")
    private String num_police;

    @objid ("6405a2ff-8ccd-435d-9849-65078924c626")
    private int montant;

	public Assurance(String num_police, int montant) {
		this.num_police = num_police;
		this.montant = montant;
	}
    
}
