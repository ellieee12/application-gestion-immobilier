package controleur;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueEnregistrerDocumentsLocation;
import modeleDAO.DocumentLocationDAO;

public class ControleurEnregistrerDocument implements ActionListener{
	
	private VueEnregistrerDocumentsLocation vue;
	private Date dateDebut;
	private String idBien;
	private String idLocataire;
	private String pathName;
	
	public ControleurEnregistrerDocument(VueEnregistrerDocumentsLocation vue, 
			Date dateDebut, String idBien, String idLocataire) {
		this.vue=vue;
		this.pathName="";
		this.dateDebut=dateDebut;
		this.idBien=idBien;
		this.idLocataire=idLocataire;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DocumentLocationDAO dao = new DocumentLocationDAO();
		String b = ((JButton)e.getSource()).getText();
		if (b.equals("Choisir un fichier")){
			File fichier = this.selectionnerUnFichier();
//			System.out.println(fichier.getAbsolutePath());
			if (!(fichier==null)) {
				this.vue.afficherNomFichier(fichier.getName());
				this.pathName=fichier.getAbsolutePath();
			}
		}else if (b.equals("Annuler")) {
			this.vue.dispose();
		}else if(b.equals("Enregistrer")) {
			if (this.pathName=="") {
				JOptionPane.showMessageDialog(this.vue, 
						"Fichier non valide","Attention", JOptionPane.WARNING_MESSAGE);
			}else {
				dao.ajouterDocument(this.pathName, this.vue.getDescription(), this.idBien,
						this.idLocataire, this.dateDebut);
				this.vue.dispose();
			}
		}
		
	}
	
	private File selectionnerUnFichier() {
		FileDialog fileChooser = new FileDialog(this.vue);
		fileChooser.setVisible(true);
		if (fileChooser.getFile()!=null) {
			String path = fileChooser.getDirectory()+fileChooser.getFile();
			return new File(path);
		}
		return null;
	}

	public String getPathName() {
		return pathName;
	}
	
	

}
