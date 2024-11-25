package controleur;

import modeleDAO.ImmeubleDAO;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueAjouterImmeuble;
import ihm.VueMesImmeubles;

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
	
	private boolean verifImmeubleExiste() {
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
	
	private boolean allVerifs() {
		return verifComplet() && !verifImmeubleExiste();
	}
	
	// ouvre mes immeubles et ferme cette page
	private void valider() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VueMesImmeubles frame = new VueMesImmeubles();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
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
			if (allVerifs()) {
				if (this.vue.getTypeImmeuble()=="Maison") {
					this.dao.ajouterImmeuble(this.vue.getId(), this.vue.getAdresse(),
							this.vue.getCP(),this.vue.getVille(),this.vue.getPeriodeConstruction(), "M");
					valider();
				} else {
					this.dao.ajouterImmeuble(this.vue.getId(), this.vue.getAdresse(),this.vue.getCP(),
							this.vue.getVille(),this.vue.getPeriodeConstruction(), "B");
					valider();
				}
			}
		}
	}
	
//	public static synchronized ControleurAjouterImmeuble getControleur() {
//		if (controleur == null) {
//			controleur = new ControleurAjouterImmeuble();
//		}
//		return controleur;
//	}

}
