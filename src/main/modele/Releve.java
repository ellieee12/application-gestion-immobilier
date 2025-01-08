package modele;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("fd88557e-8a54-4688-9b99-1462c5a16341")
public class Releve {
    private int date_releve;
    private int indexcomp;

    public Releve(int date_releve, int indexcomp) {
    	this.date_releve = date_releve;
    	this.indexcomp = indexcomp;
    }

	public int getDate_releve() {
		return date_releve;
	}

	public void setDate_releve(int date_releve) {
		this.date_releve = date_releve;
	}

	public int getIndexcomp() {
		return indexcomp;
	}

	public void setIndexcomp(int indexcomp) {
		this.indexcomp = indexcomp;
	}
    
    
    
}
