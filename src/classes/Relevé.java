package classes;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("fd88557e-8a54-4688-9b99-1462c5a16341")
public class Relevé {
    @objid ("e26867d9-288a-496b-b146-cfc2a8a3439f")
    private int date_relevé;

    @objid ("79758f35-1f69-47df-935f-95086ca9a7b9")
    private int indexcomp;

    public Relevé(int date_relevé, int indexcomp) {
    	this.date_relevé = date_relevé;
    	this.indexcomp = indexcomp;
    }

	public int getDate_relevé() {
		return date_relevé;
	}

	public void setDate_relevé(int date_relevé) {
		this.date_relevé = date_relevé;
	}

	public int getIndexcomp() {
		return indexcomp;
	}

	public void setIndexcomp(int indexcomp) {
		this.indexcomp = indexcomp;
	}
    
    
    
}
