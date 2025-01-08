package modele;

public class ICC {
	
    private String annee;

    private String trimestre;

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
