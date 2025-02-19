package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueAjouterFacture;
import ihm.VueListFactures;
import modele.Bien;
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
	
	public ControleurAjouterFacture (VueAjouterFacture vue, VueListFactures vueListFactures) throws DAOException{
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
		if (b.getText().equals("Annuler")) {
			this.vue.dispose();
		} else if (b.getText().equals("Valider")) {
			//vérifier si l'identifiant existe dans la base de données
			try {
				if (this.verificationChamps()) {
					if (verificationFactureExiste()) {
						JOptionPane.showMessageDialog(this.vue, "Cette facture existe déjà","Attention", JOptionPane.WARNING_MESSAGE);
					}else if(!verificationChamps()) {
						JOptionPane.showMessageDialog(this.vue, "Champs obligatoires non remplis et/ou Champs Dates invalides","Attention", JOptionPane.WARNING_MESSAGE);
					}else {
						this.ajouterFacture();
						this.vueListFactures.getControleurMesBiens().Update();
						this.vue.dispose();
					}
				}	
			} catch (DAOException e1) {
				e1.printStackTrace();
			}				
		}
	}
	/**
	 * Vérifier tous les champs 
	 * @return boolean
	 */
	private boolean verificationChamps() {
		if (!this.verificationDateEmission()) {
			this.affichageErreurDateEmission();
		}else if(!this.verificationDatePaiement()){
			this.affichageErreurDatePaiement();
		}else if (this.vue.getChampsNumero()==null){
			this.afficherMessageErreur("Numéro de facture non rempli");
		}else if(this.vue.getChampsDesignation()==null){
			this.afficherMessageErreur("Désignation de facture non rempli");
		}else if(this.vue.getChampsMontant()==null) {
			this.afficherMessageErreur("Format de montant incorrect");
		}else if(this.vue.getChampsNumeroDevis()==null){
			this.afficherMessageErreur("Numéro devis non rempli");
		}else if(this.vue.getChampsMontantReelPaye()==null) {
			this.afficherMessageErreur("Format de montant réel payé incorrect");
		}else if (this.vue.getChampsImputableLocataire()==null) {
			this.afficherMessageErreur("Format de l'imputable locataire incorrect");
		}else {
			return true;
		}
		
		return false;
	}
	/**
	 * Vérifier le format et la validité de la date d'émission
	 * @return boolean
	 */
	private boolean verificationDateEmission() {
		try {
			return this.vue.getChampsDateEmission()!=null;
		}catch(IllegalArgumentException e) {
			return false;
		}
	}
	/**
	 * Afficher la message correspondante à l'erreur de la saisie de la date d'émission
	 */
	private void affichageErreurDateEmission() {
		try {
			if (this.vue.getChampsDateEmission()==null) {
				this.afficherMessageErreur("Date d'émission de facture non remplie");
			}
		}catch(IllegalArgumentException e) {
			this.afficherMessageErreur("Le format de la date d'émission incorrect");
		}
	}
	/**
	 * Vérifier le format et la validité de la date de paiement
	 * @return boolean
	 */
	private boolean verificationDatePaiement() {
		try {
			return this.vue.getChampsDatePaiement()!=null;
		}catch(IllegalArgumentException e) {
			return false;
		}
	}
	/**
	 * Afficher la message correspondante à l'erreur de la saisie de la date de paiement
	 */
	private void affichageErreurDatePaiement() {
		try {
			if (this.vue.getChampsDatePaiement()==null) {
				this.afficherMessageErreur("Date d'acquisition de facture non remplie");
			}
		}catch(IllegalArgumentException e) {
			this.afficherMessageErreur("Le format de la date de paiement incorrect");
		}
	}
	/**
	 * Vérifier si la facture existe dans la base de données
	 * @return boolean
	 * @throws DAOException
	 */
	private boolean verificationFactureExiste() throws DAOException {
		return this.factureDao.FactureExiste(this.vue.getChampsNumero());
	}
	/**
	 * Ajouter la facture dans la base de données
	 * @throws DAOException
	 */
	private void ajouterFacture() throws DAOException {
		Facture f = new Facture (this.vue.getChampsNumero(), this.vue.getChampsDateEmission(), this.vue.getChampsDatePaiement(), this.vue.getChampsNumeroDevis(), this.vue.getChampsDesignation(), this.vue.getChampsMontantReelPaye(), this.vue.getChampsMontant(), this.vue.getChampsImputableLocataire(), this.vue.getSelectedBien());
		this.factureDao.ajouterFacture(f);
	}
	
	public List<String> getBiens(){
		return this.biens;
	}
	
	/**
	 * Afficher une message d'erreur dans une JOptionPan avec l'option de WARNING_MESSAGE 
	 * @param msg message d'erreur à afficher
	 */
	public void afficherMessageErreur(String msg) {
		JOptionPane.showMessageDialog(this.vue, msg,"Attention", JOptionPane.WARNING_MESSAGE);
	}
}
