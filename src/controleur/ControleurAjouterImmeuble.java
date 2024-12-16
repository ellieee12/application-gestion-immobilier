package controleur;

import modele.DAOException;
import modele.ImmeubleDAO;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueAjouterImmeuble;
import ihm.VueMesImmeubles;
import modele.Batiment;
import modele.Maison;

public class ControleurAjouterImmeuble implements ActionListener {
	
	private static ControleurAjouterImmeuble controleur;
	
	private VueMesImmeubles vueImmeubles;
	private VueAjouterImmeuble vue;
	private ImmeubleDAO dao;
	
	
	public ControleurAjouterImmeuble(VueAjouterImmeuble vue, VueMesImmeubles vueImmeubles) {
		this.vueImmeubles=vueImmeubles;
		this.vue=vue;
		this.dao = new ImmeubleDAO();
	}
	
	private boolean verifImmeubleExiste() throws HeadlessException, DAOException {
		if (this.dao.immeubleExiste(this.vue.getId())) {
			JOptionPane.showMessageDialog(this.vue, 
					"Cet immeuble existe déjà","Attention", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
	
	private boolean verifComplet() {
		if (!this.vue.isComplet()) {
			JOptionPane.showMessageDialog(this.vue, 
					"Champs obligatoires non remplis","Attention", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	private boolean allVerifs() throws HeadlessException, DAOException {
		return verifComplet() && !verifImmeubleExiste();
	}
	
	// ouvre mes immeubles et ferme cette page
	private void valider() throws DAOException {
		this.vueImmeubles.getControleurMesImmeubles().Update();
		this.vue.dispose();
	}
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			//ferme cette page
			this.vue.dispose();
		} else if (b.getText() == "Valider") {
			try {
				if (allVerifs()) {
					if (this.vue.getTypeImmeuble()=="Maison") {
						Maison maison = new Maison(this.vue.getId(), this.vue.getAdresse(),
								this.vue.getCP(),this.vue.getVille(),this.vue.getPeriodeConstruction());
						this.dao.ajouterImmeuble(maison);
					} else {
						Batiment batiment = new Batiment(this.vue.getId(), this.vue.getAdresse(),this.vue.getCP(),
								this.vue.getVille(),this.vue.getPeriodeConstruction());
						this.dao.ajouterImmeuble(batiment);
					}
					valider();
				}
			} catch (HeadlessException |  DAOException e1) {
				e1.printStackTrace();
			}
		}
	}


}
