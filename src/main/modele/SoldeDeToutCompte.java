package modele;

import java.sql.Date;

public class SoldeDeToutCompte {
    private Date date_SDTC;
    private int indexcomp;

    public SoldeDeToutCompte(Date date_SDTC, int indexcomp) {
    	this.date_SDTC = date_SDTC;
    	this.indexcomp = indexcomp;
    }

	public Date getDate_SDTC() {
		return date_SDTC;
	}

	public void setDate_SDTC(Date date_SDTC) {
		this.date_SDTC = date_SDTC;
	}

	public int getIndexcomp() {
		return indexcomp;
	}

	public void setIndexcomp(int indexcomp) {
		this.indexcomp = indexcomp;
	}  
}
