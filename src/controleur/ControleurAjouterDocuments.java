package controleur;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;

import classes.DocumentLocation;
import ihm.VueAjouterDocuments;
import ihm.VueEnregistrerDocumentsLocation;

public class ControleurAjouterDocuments implements ActionListener {
	
	private VueAjouterDocuments vue;
	private DocumentLocation docEtat, docCaution, docEau, docElec,docPlaceHolder;
	
	public ControleurAjouterDocuments(VueAjouterDocuments vue) {
		this.vue=vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();	
		WindowAdapter listener = new WindowAdapter() {
			public void saveDoc (WindowEvent evt) {
				Frame frame = (Frame) evt.getSource();
				ControleurAjouterDocuments.docEtat=this.docPlaceHolder;
				this.vue.getbEtatDesLieux().setText(this.docEtat.getFileName());
			}
		};
		if (b.equals(this.vue.getbEtatDesLieux())) {
			try {
				VueEnregistrerDocumentsLocation frame = new VueEnregistrerDocumentsLocation(
						this.vue.getLoc().getDate_debut(),this.vue.getIdBien(),this.vue.getIdLocataire());
				frame.setVisible(true);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (b.equals(vue.getbCaution())) {
			try {
				VueEnregistrerDocumentsLocation frame = new VueEnregistrerDocumentsLocation(
						this.vue.getLoc().getDate_debut(),this.vue.getIdBien(),this.vue.getIdLocataire());
				frame.setVisible(true);
				this.docCaution=this.docPlaceHolder;
				this.vue.getbCaution().setText(this.docCaution.getFileName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (b.equals(vue.getbEau())) {
			try {
				VueEnregistrerDocumentsLocation frame = new VueEnregistrerDocumentsLocation(
						this.vue.getLoc().getDate_debut(),this.vue.getIdBien(),this.vue.getIdLocataire());
				frame.setVisible(true);
				this.docEau=this.docPlaceHolder;
				this.vue.getbEau().setText(this.docEau.getFileName());
			} catch (Exception e1) { 
				
			}
		} else if (b.equals(vue.getbElectricite())) {
			try {
				VueEnregistrerDocumentsLocation frame = new VueEnregistrerDocumentsLocation(
						this.vue.getLoc().getDate_debut(),this.vue.getIdBien(),this.vue.getIdLocataire());
				frame.setVisible(true);
				this.docElec=this.docPlaceHolder;
				this.vue.getbElectricite().setText(this.docElec.getFileName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		// à la fin
		//dao.ajouterDocument(this.pathName, this.vue.getDescription(), this.idBien,
		//this.idLocataire, this.dateDebut);
	}

	public void setDocEtat(DocumentLocation doc) {
		this.docEtat = doc;
	}

	public void setDocCaution(DocumentLocation docCaution) {
		this.docCaution = docCaution;
	}

	public void setDocEau(DocumentLocation docEau) {
		this.docEau = docEau;
	}

	public void setDocElec(DocumentLocation docElec) {
		this.docElec = docElec;
	}
	
	public void setDoc(DocumentLocation doc ) {
		this.docPlaceHolder=doc;
	}
	

	
}
