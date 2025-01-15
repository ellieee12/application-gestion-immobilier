package modele;

import java.sql.Date;

public class SoldeDeToutCompte {
    private Date date_SDTC;

    public SoldeDeToutCompte(Date date_SDTC) {
    	this.date_SDTC = date_SDTC;
    }

	public Date getDate_SDTC() {
		return date_SDTC;
	}

	public void setDate_SDTC(Date date_SDTC) {
		this.date_SDTC = date_SDTC;
	}
}
