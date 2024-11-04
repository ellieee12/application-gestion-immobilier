import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("b64baee4-47a6-44c9-bf10-62aba347f346")
public class ICC {
    @objid ("422e0901-aa19-44c1-960b-7f4e6b29796e")
    private String annee;

    @objid ("bfaa15a3-e8d9-4ec2-920c-87fbdcb061d4")
    private String trimestre;

    @objid ("358c5528-6984-4540-9ce6-1bca17a61ee5")
    private float indice;

	public ICC(String annee, String trimestre, float indice) {
		this.annee = annee;
		this.trimestre = trimestre;
		this.indice = indice;
	}

	public String getAnnee() {
		return annee;
	}

	public String getTrimestre() {
		return trimestre;
	}

	public float getIndice() {
		return indice;
	}

}
