package controleur;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueSaisieLocataire;
import modele.Locataire;
import modele.DAOException;
import modele.LocataireDAO;

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
	
	private boolean verificationLocataireExiste() throws SQLException {
		try {
			if (this.dao.locataireExists(this.vue.getId())) {
				JOptionPane.showMessageDialog(this.vue, "Ce locataire existe déjà",
						"Attention", JOptionPane.WARNING_MESSAGE);
				return true;
			}
		} catch (HeadlessException | DAOException e) {
			e.printStackTrace();
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
	
	private boolean allVerif() throws SQLException {
		return verificationComplet() && !verificationLocataireExiste();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			this.vue.dispose();
		} else if (b.getText() == "Ajouter") {
			try {
				if (allVerif()) {
					Locataire loc = new Locataire(this.vue.getNom(), this.vue.getPrenom(), this.vue.getTel(), this.vue.getMail(),this.vue.getId(), this.vue.getDateDeNaissance());
					try {
						this.dao.ajouterLocataire(loc);
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//ferme cette page et ouvre le Menu
					this.vue.dispose();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
