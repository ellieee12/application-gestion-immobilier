package classes;
import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("b5194946-6121-47b9-bc1d-a3b885a2e6d0")
public class Diagnostic {
    @objid ("f8fe33a8-ea0d-4435-90bc-74a74804afe0")
    private int id_diagnostic;

    @objid ("442f82bf-9a16-4896-94cf-4c4a2b7bdd65")
    private Date date_validite;

    @objid ("3faafe2f-37ff-4b84-bd76-544889479d1e")
    private String type_diagnostic;

	public Diagnostic(int id_diagnostic, Date date_validite, String type_diagnostic) {
		this.id_diagnostic = id_diagnostic;
		this.date_validite = date_validite;
		this.type_diagnostic = type_diagnostic;
	}
	
}
