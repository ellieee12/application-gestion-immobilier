package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;

import classes.DocumentLocation;
import ihm.VueAjouterDocuments;
import ihm.VueEnregistrerDocumentsLocation;
import modeleDAO.DocumentLocationDAO;

public class ControleurAjouterDocuments implements ActionListener {
	
	private enum DocumentEnCours {
		CAUTION("CAUTION"),ETAT_LIEU("ETAT_LIEU");
		
		private final String description;
		
		DocumentEnCours(String description) {
			this.description=description;
		}
		
		String getDescription(){
			return this.description;
		}
		
	}
	private DocumentEnCours documentEnCours;
	private VueAjouterDocuments vue;
	private DocumentLocation docEtat, docCaution, docEau, docElec;
	private Date dateDebut;
	private String idBien;
	private String idLocataire;
	
	public ControleurAjouterDocuments(VueAjouterDocuments vue,Date dateDebut, String idBien, String idLocataire) {
		this.vue=vue;
		this.idBien=idBien;
		this.idLocataire=idLocataire;
		this.dateDebut=dateDebut;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();	
		try {
			if (b.equals(this.vue.getbEtatDesLieux())){
				this.documentEnCours=DocumentEnCours.ETAT_LIEU;
				 initialiserFenetreEnregistrerUnDocument();
			}else if (b.equals(vue.getbCaution())) {
				this.documentEnCours=DocumentEnCours.CAUTION;
				initialiserFenetreEnregistrerUnDocument();
			}
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		if (b.getText().equals("Enregistrer")) {
			System.out.println("entered");
			this.ajouterDocumentDAO(docCaution);
			this.ajouterDocumentDAO(docEau);
			this.ajouterDocumentDAO(docElec);
			this.ajouterDocumentDAO(docEtat);
			this.vue.dispose();
		}
	}

	private void ajouterDocumentDAO(DocumentLocation document) {
		if (document!=null) {
			DocumentLocationDAO dao = new DocumentLocationDAO();
			dao.ajouterDocument(document,this.idBien,this.idLocataire,this.dateDebut);
		}
	}
	
	private void initialiserFenetreEnregistrerUnDocument() {
		VueEnregistrerDocumentsLocation frame;
		frame = new VueEnregistrerDocumentsLocation(
				this.vue.getLoc().getDate_debut(),this.vue.getIdBien(),this.vue.getIdLocataire(),this.vue,this);
		frame.setVisible(true);
	}

	public void setDocEtat(DocumentLocation doc) {
		this.docEtat = doc;
		this.vue.afficherNomFichier(this.docEtat.getFileName(), this.vue.getEtatDesLieux());
	}

	public void setDocCaution(DocumentLocation docCaution) {
		this.docCaution = docCaution;
		this.vue.afficherNomFichier(this.docCaution.getFileName(), this.vue.getCaution());
	}
	
	public String getDocumentEnCours() {
		return this.documentEnCours.getDescription();
	}

	
}
