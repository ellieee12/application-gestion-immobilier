package controleur;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueAjouterFacture;
import ihm.VueListFactures;
import modele.BienDAO;
import modele.DAOException;
import modele.Facture;
import modele.FactureDAO;

public class ControleurAjouterFacture implements ActionListener {
	
	private VueListFactures vueListFactures;
	private VueAjouterFacture vue;
	private FactureDAO factureDao;
	private BienDAO bienDAO;
	private List<String> biens;
	
	public ControleurAjouterFacture (VueAjouterFacture vue, VueListFactures vueListFactures) throws DAOException, SQLException {
		this.vue = vue;
		this.biens = new LinkedList<>();
		this.vueListFactures = vueListFactures;
		this.factureDao = new FactureDAO();
		this.bienDAO = new BienDAO();
		List<Bien> biensliste = this.bienDAO.getAllBiens();
		for(Bien b : biensliste) {
			this.biens.add(b.getId_bien());
		}
	}
	
	public void actionPerformed (ActionEvent e) {
		JButton b = (JButton) e.getSource();

		if (b.getText() == "Annuler") {
			this.vue.dispose();
		} else if (b.getText() == "Valider") {
			//vérifier si l'identifiant existe dans la base de données
			try {
				if (verificationFactureExiste()) {
					JOptionPane.showMessageDialog(this.vue, "Cette facture existe déjà","Attention", JOptionPane.WARNING_MESSAGE);
				}else if(!verificationChamps()) {
					JOptionPane.showMessageDialog(this.vue, "Veuillez remplir tout les champs","Attention", JOptionPane.WARNING_MESSAGE);
				}else {
					this.ajouterFacture();
					this.vueListFactures.getControleurMesBiens().Update();
					this.vue.dispose();
				}
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}				
		}
	}
	
	private boolean verificationFactureExiste() throws DAOException {
		return this.factureDao.FactureExiste(this.vue.getChampsNumero());
	}

	private boolean verificationChamps() {
		return this.vue.getChampsDateEmission() != null && this.vue.getChampsDateAcquisition() != null && this.vue.getChampsNumero() != null && this.vue.getChampsDesignation() != null && this.vue.getChampsMontant() != null && this.vue.getChampsNumeroDevis() != null && this.vue.getChampsMontantReelPaye() != null;
	}
	
	private void ajouterFacture() throws DAOException {
		Facture f = new Facture (this.vue.getChampsNumero(), this.vue.getChampsDateEmission(), this.vue.getChampsDateAcquisition(), this.vue.getChampsNumeroDevis(), this.vue.getChampsDesignation(), this.vue.getChampsMontantReelPaye(), this.vue.getChampsMontant(), this.vue.getChampsImputableLocataire(), this.vue.getSelectedBien());
		this.factureDao.ajouterFacture(f);
	}
	
	public List<String> getBiens(){
		return this.biens;
	}
}
