package java;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("79931338-1b81-4175-966c-6c9255a1c683")
public class Compteur {
    @objid ("c9c03a92-3319-4649-b998-804d3305f4e7")
    private String id_compteur;

    @objid ("7490c329-be7b-4e0b-805f-dec75168ab2f")
    private String typecomp;

    @objid ("f69f058c-6594-4f59-85e5-dd986e29dfb6")
    private float prix_abonnement;

    @objid ("fe528b22-b535-4e73-92b4-27872a046602")
    public List<Bien> bien = new ArrayList<Bien> ();

    @objid ("bd3d853e-9e11-43e8-8c03-de5e0da2eee4")
    public List<Relevé> date_relevé  = new ArrayList<Relevé> ();

	public Compteur(String id_compteur, String typecomp, float prix_abonnement, List<Bien> bien,
			List<Relevé> date_relevé) {
		this.id_compteur = id_compteur;
		this.typecomp = typecomp;
		this.prix_abonnement = prix_abonnement;
		this.bien = bien;
		this.date_relevé = date_relevé;
	}

}
