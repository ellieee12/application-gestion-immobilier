package controleur;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import ihm.VueEnregistrerDocumentsLocation;
import modeleDAO.DocumentLocationDAO;

public class ControleurEnregistrerDocument implements ActionListener{
	
	private VueEnregistrerDocumentsLocation vue;
	private String id_location;
	private String pathName;
	
	public ControleurEnregistrerDocument(VueEnregistrerDocumentsLocation vue) {
		this.vue=vue;
		this.pathName="";
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
			if (this.pathName!="") {
				//TODO : add when the fichier is not selected
			}else {
				dao.ajouterDocument(this.pathName, this.vue.getDescription(), "1", "1", null);
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

}
