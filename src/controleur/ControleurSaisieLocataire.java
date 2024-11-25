package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueSaisieLocataire;
import modeleDAO.LocataireDAO;

public class ControleurSaisieLocataire implements ActionListener {
	
	private static ControleurSaisieLocataire controleur;
	
	private VueSaisieLocataire vue;
	private LocataireDAO dao;
	
	private ControleurSaisieLocataire() {}
	
	public void initialiserControleur(VueSaisieLocataire vue) {
		this.vue=vue;
		this.dao = new LocataireDAO();
	}
	
	public static synchronized ControleurSaisieLocataire getControleur() {
		if (controleur == null) {
			controleur = new ControleurSaisieLocataire();
		}
		return controleur;
	}
	
	private boolean verificationLocataireExiste() {
		if (this.dao.locataireExists(this.vue.getId())) {
			JOptionPane.showMessageDialog(this.vue, "Ce locataire existe déjà",
					"Attention", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
	
	private boolean verificationComplet() {
		if (!this.vue.isComplet()) {
			JOptionPane.showMessageDialog(this.vue, "Champs obligatoires non remplis",
					"Attention", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	private boolean allVerif() {
		return verificationComplet() && !verificationLocataireExiste();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			this.vue.dispose();
		} else if (b.getText() == "Ajouter") {
			if (allVerif()) {
				this.dao.ajouterLocataire(this.vue.getId(), this.vue.getNom(),
						this.vue.getPrenom(),this.vue.getTel(),this.vue.getMail(),this.vue.getDateDeNaissance());
				//ferme cette page et ouvre le Menu
				this.vue.dispose();
				// FAIRE LE MENU !!!
			}
		}
	}

}
