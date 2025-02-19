package controleur;

import modele.DAOException;
import modele.ImmeubleDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueAjouterImmeuble;
import ihm.VueMesImmeubles;
import modele.Batiment;
import modele.Maison;

public class ControleurAjouterImmeuble extends WindowAdapter implements ActionListener  {
		
	private VueMesImmeubles vueImmeubles;
	private VueAjouterImmeuble vue;
	private ImmeubleDAO dao;
	
	/**
	 * Constructeur de ControleurAjouterImmeuble
	 * @param vue
	 * @param vueImmeubles
	 */
	public ControleurAjouterImmeuble(VueAjouterImmeuble vue, VueMesImmeubles vueImmeubles) {
		this.vueImmeubles=vueImmeubles;
		this.vue=vue;
		this.dao = new ImmeubleDAO();
	}
	/**
	 * Vérification si l'immeuble avec un identifiant donné existe dans la base de données
	 * @return
	 * @throws DAOException
	 */
	private boolean verifImmeubleExiste() throws DAOException {
		return this.dao.immeubleExiste(this.vue.getId());
	}
	/**
	 * Vérification des champs
	 * @return
	 */
	private boolean verifComplet() {
		if (this.vue.getAdresse().equals("")) {
			this.afficherMessageErreur("Adresse non remplie");
		}else if (this.vue.getCP()==null) {
			this.afficherMessageErreur("Code postal non rempli");
		}else if (this.vue.getVille().equals("")) {
			this.afficherMessageErreur("Ville non remplie");
		}else if (this.vue.getId().equals("")) {
			this.afficherMessageErreur("Identifiant non rempli");
		}else {
			return true;
		}
		return false;
	}

	/**
	 * Ouvrir la page des immeubles et fermer cette page
	 * @throws DAOException
	 */
	private void valider() throws DAOException {
		this.vueImmeubles.getControleurMesImmeubles().Update();
		this.vue.dispose();
	}
	
	/**
	 * Afficher une message d'erreur dans une JOptionPan avec l'option de WARNING_MESSAGE 
	 * @param msg message d'erreur à afficher
	 */
	public void afficherMessageErreur(String msg) {
		JOptionPane.showMessageDialog(this.vue, msg,"Attention", JOptionPane.WARNING_MESSAGE);
	}
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			//ferme cette page
			this.vue.dispose();
		} else if (b.getText() == "Valider") {
			try {
				if (this.verifComplet()) {
					if (this.verifImmeubleExiste()) {
						this.afficherMessageErreur("L'immeuble avec cet identifiant existe déjà");
					}else {
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
				}
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
