package modele;

import java.io.File;
import java.sql.Date;

public class DocumentLocation {
	
	private File file;
	private String description;
	private Date date;
	private String idBien;
	private String idLocataire;
	private Date dateDebut;
	
	public DocumentLocation(File file, String description, Date date) {
		this.file = file;
		this.description = description;
		this.date = date;
	}

	public File getFile() {
		return file;
	}
	
	public String getFilePath() {
		return this.file.getAbsolutePath();
	}
	
	public String getFileName() {
		return this.file.getName();
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}

	public String getIdBien() {
		return idBien;
	}

	public String getIdLocataire() {
		return idLocataire;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setIdBien(String idBien) {
		this.idBien = idBien;
	}

	public void setIdLocataire(String idLocataire) {
		this.idLocataire = idLocataire;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	

}
