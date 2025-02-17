package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueMesLocataires;
import ihm.VueSaisieLocataire;
import modele.Locataire;
import modele.DAOException;
import modele.LocataireDAO;

public class ControleurSaisieLocataire implements ActionListener {
	
	private VueSaisieLocataire vue;
	private VueMesLocataires vueLocataires;
	private LocataireDAO dao;
	
	public ControleurSaisieLocataire(VueSaisieLocataire vue, VueMesLocataires vueLocataires) {
		this.vue = vue;
		this.vueLocataires = vueLocataires;
		this.dao = new LocataireDAO();
	}
	
	private boolean verificationLocataireExiste(){
		try {
			if (this.dao.locataireExists(this.vue.getId())) {
				JOptionPane.showMessageDialog(this.vue, "Ce locataire existe déjà ou cet identifiant à déjà été utilisé",
						"Attention", JOptionPane.WARNING_MESSAGE);
				return true;
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	private boolean verificationComplet() {
//		if (!this.vue.isComplet()) {
//			JOptionPane.showMessageDialog(this.vue, "Champs obligatoires non remplis et/ou date de naissance invalide",
//					"Attention", JOptionPane.WARNING_MESSAGE);
//			return false;
//		}
//		return true;
//	}
	
//	private boolean allVerif(){
//		return verificationComplet() && !verificationLocataireExiste();
//	}
	
	private void afficherMsgErreurMail() {
		try {
			if ( vue.getMail()==null) {
				this.afficherMessageErreur("Mail non rempli");
			}
		}catch(IllegalArgumentException iae) {
			this.afficherMessageErreur("Format du mail incorrect (Ex : nom@gmail.com)");
		}
	}

	private boolean verifierMail() {
		try {
			return vue.getMail()!=null;
		}catch(IllegalArgumentException iae) {
			return false;
		}
	}
	
	private boolean verifierDateNaissance() { 
		try {
			return vue.getDateDeNaissance()!=null;
		}catch(IllegalArgumentException iae) {
			return false;
		}
	}
	private void afficherMsgErreurDateNaissaice() {
		try {
			if (vue.getDateDeNaissance()==null) {
				this.afficherMessageErreur("Date de naissance non remplie");
			}
		}catch(IllegalArgumentException e) {
			this.afficherMessageErreur("Format de la date de naissance incorrect");
		}
	}
	private boolean verifierChampsEtAfficherMsgErreur() {
		if (vue.getNom().isEmpty()) {
			this.afficherMessageErreur("Nom non rempli");
		}else if (vue.getPrenom().isEmpty()) {
			this.afficherMessageErreur("Prenom non rempli");
		}else if (vue.getTel()==null) {
			this.afficherMessageErreur("Numéro de téléphone non rempli");
		}else if (!this.verifierMail()) {
			this.afficherMsgErreurMail();
		}else if(!this.verifierDateNaissance()) {
			this.afficherMsgErreurDateNaissaice();
		}else if(vue.getId().isEmpty()) {
			this.afficherMessageErreur("Identifiant locataire non rempli");
		}else {
			return true;
		}
		return false;
	}
	
	public void afficherMessageErreur(String msg) {
		JOptionPane.showMessageDialog(this.vue, msg,"Attention", JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Annuler")) {
			this.vue.dispose();
		} else if (b.getText().equals("Ajouter")) {
			if (this.verifierChampsEtAfficherMsgErreur()) {
				if (!this.verificationLocataireExiste()) {
					Locataire loc = new Locataire(this.vue.getNom(), this.vue.getPrenom(), this.vue.getTel(), this.vue.getMail(),this.vue.getId(), this.vue.getDateDeNaissance());
					try {
						this.dao.ajouterLocataire(loc);
						this.vueLocataires.getControleurMesLocataires().Update();
					} catch (DAOException e1) {
						e1.printStackTrace();
					}
					//ferme cette page et ouvre le Menu
					this.vue.dispose();
				}
			}
		}
	}
	
	

}
