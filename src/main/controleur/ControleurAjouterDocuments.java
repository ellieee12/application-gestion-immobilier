package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;

import ihm.VueAjouterDocuments;
import ihm.VueEnregistrerDocumentsLocation;
import ihm.VueMesLocations;
import modele.DAOException;
import modele.DocumentLocation;
import modele.DocumentLocationDAO;

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
	private VueMesLocations vueMesLocations;
	private DocumentEnCours documentEnCours;
	private VueAjouterDocuments vue;
	private DocumentLocation docEtat, docCaution, docEau, docElec;
	private Date dateDebut;
	private String idBien;
	private String idLocataire;
	/**
	 * Constructeur de ControleurAjouterDocuments
	 * @param vue
	 * @param dateDebut
	 * @param idBien
	 * @param idLocataire
	 * @param vueMesLocations
	 */
	public ControleurAjouterDocuments(VueAjouterDocuments vue,Date dateDebut, String idBien, String idLocataire, VueMesLocations vueMesLocations) {
		this.vue=vue;
		this.vueMesLocations = vueMesLocations;
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
			try {
				this.ajouterDocumentDAO(docCaution);
				this.ajouterDocumentDAO(docEau);
				this.ajouterDocumentDAO(docElec);
				this.ajouterDocumentDAO(docEtat);
				valider();
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.vue.dispose();
		}
	}
	/**
	 * Mettre à jour la table des locations
	 * @throws DAOException
	 */
	private void valider() throws DAOException {
		this.vueMesLocations.getControleurMesLocations().Update();
		this.vue.dispose();
	}
	/**
	 * Ajouter le document dans la base de données
	 * @param document
	 * @throws DAOException
	 */
	private void ajouterDocumentDAO(DocumentLocation document) throws DAOException {
		if (document!=null) {
			DocumentLocationDAO dao = new DocumentLocationDAO();
			dao.ajouterDocument(document,this.idBien,this.idLocataire,this.dateDebut);
		}
	}
	/**
	 * Initialiser la fenêtre d'enregistrer un document
	 */
	private void initialiserFenetreEnregistrerUnDocument() {
		VueEnregistrerDocumentsLocation frame;
		frame = new VueEnregistrerDocumentsLocation(
				this.vue.getLoc().getDate_debut(),this.vue.getIdBien(),this.vue.getIdLocataire(),this.vue,this);
		frame.setVisible(true);
	}
	/**
	 * Modifier le document d'état des lieux
	 * @param doc
	 */
	public void setDocEtat(DocumentLocation doc) {
		this.docEtat = doc;
		this.vue.afficherNomFichier(this.docEtat.getFileName(), this.vue.getEtatDesLieux());
	}
	/**
	 * Modifier le document de la caution
	 * @param docCaution
	 */
	public void setDocCaution(DocumentLocation docCaution) {
		this.docCaution = docCaution;
		this.vue.afficherNomFichier(this.docCaution.getFileName(), this.vue.getCaution());
	}
	/**
	 * Obtenir le document en cours
	 * @return
	 */
	public String getDocumentEnCours() {
		return this.documentEnCours.getDescription();
	}

	
}
