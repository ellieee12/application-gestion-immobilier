package modele;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("ca4d9a25-0978-4504-b29f-12df14fd70de")
public class Impot {
    @objid ("1c112f33-c50e-4915-99bf-ee884e1b8acd")
    private int id_impot;

    @objid ("815128bf-bd04-426b-a74d-bd2a7d7a6e27")
    private String nom;

    @objid ("400349b5-1ef6-45e3-a005-75915e720cea")
    private float montant;

    @objid ("69f61a19-76c8-4cf2-9123-780441461695")
    private String annee;
    
    public Impot(int id_impot, String nom, float montant, String annee) {
    	this.id_impot = id_impot;
    	this.nom = nom;
    	this.montant = montant;
    	this.annee = annee;
    }

}
