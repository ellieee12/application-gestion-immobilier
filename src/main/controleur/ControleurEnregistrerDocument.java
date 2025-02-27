package controleur;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueAjouterDocuments;
import ihm.VueEnregistrerDocumentsLocation;
import modele.DocumentLocation;

public class ControleurEnregistrerDocument implements ActionListener{
	
	private VueEnregistrerDocumentsLocation vue;
	private String pathName;
	private File file;
	private ControleurAjouterDocuments controleurAjouterDocuments;
	/**
	 * Constructeur ControleurEnregistrerDocuments
	 * @param vue
	 * @param vueDoc
	 * @param controleurAjouterDocuments
	 */
	public ControleurEnregistrerDocument(VueEnregistrerDocumentsLocation vue, VueAjouterDocuments vueDoc,
			 ControleurAjouterDocuments controleurAjouterDocuments) {
		this.vue=vue;
		this.pathName="";
		this.controleurAjouterDocuments=controleurAjouterDocuments;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String b = ((JButton)e.getSource()).getText();
		if (b.equals("Choisir un fichier")){
			System.out.println("3");
			File fichier = this.selectionnerUnFichier();
			this.file = fichier;
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
				setDocumentLocation();
				this.vue.dispose();
			}
		}
		
	}
	/**
	 * Modifier la location du document
	 */
	private void setDocumentLocation() {
		DocumentLocation doc = getSelectedDocument();
		String etat = this.controleurAjouterDocuments.getDocumentEnCours();
		switch(etat) {
		case "ETAT_LIEU":
			this.controleurAjouterDocuments.setDocEtat(doc);

			break;
		case "CAUTION" : 
			this.controleurAjouterDocuments.setDocCaution(doc);
			break;
		}
	}
	/**
	 * Retourner le document séléctionné
	 * @return
	 */
	private DocumentLocation getSelectedDocument() {
		DocumentLocation doc = new DocumentLocation(this.file, this.vue.getDescription(), 
				new Date(Calendar.getInstance().getTime().getTime()));
		return doc;
	}
	/**
	 * Séléctionner un fichier
	 * @return
	 */
	private File selectionnerUnFichier() {
		FileDialog fileChooser = new FileDialog(this.vue);
		fileChooser.setVisible(true);
		if (fileChooser.getFile()!=null) {
			String path = fileChooser.getDirectory()+fileChooser.getFile();
			return new File(path);
		}
		return null;
	}
	/**
	 * Retourner le nom du path
	 * @return
	 */
	public String getPathName() {
		return pathName;
	}
	
	

}
