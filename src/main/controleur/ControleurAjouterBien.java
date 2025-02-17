package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import ihm.VueAjouterBien;
import modele.BienDAO;
import modele.Compteur;
import modele.Compteur.typeCompteur;
import modele.CompteurDAO;
import modele.DAOException;
import modele.Garage;
import modele.ImmeubleDAO;
import modele.Immeuble;
import modele.Logement;
import modele.Maison;
import modele.Releve;
import modele.ReleveDAO;

public class ControleurAjouterBien implements ActionListener {
	
	private VueAjouterBien vue;
	private BienDAO dao;
	private CompteurDAO daoC;
	private ReleveDAO daoR;
	private Map<String, String> NameImmeubles;
	private final float PRIX_EAU = 2.86F;
	private final float PRIX_ELEC = 0.2F;
	
	/**
	 * Constructeur ControleurAjouterBien
	 * @param vue
	 * @throws DAOException
	 */
	public ControleurAjouterBien (VueAjouterBien vue) throws DAOException {
		try {
			this.vue = vue;
			this.dao = new BienDAO();
			this.daoC = new CompteurDAO();
			this.daoR = new ReleveDAO();
			this.NameImmeubles = new HashMap<>();
			
			ImmeubleDAO Immeuble = new ImmeubleDAO();
			List<Immeuble> liste = Immeuble.getImmeublesPourAjouterBien();
			for (Immeuble i : liste) {
				if (i instanceof Maison) {
					this.NameImmeubles.put(i.getId_immeuble(), "M");
				}else {
					this.NameImmeubles.put(i.getId_immeuble(), "B");
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Actions à effectué lors d'un clique d'un bouton
	 */
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			String s = ((JButton) e.getSource()).getText();
			if (s.equals("Annuler")) {
				this.vue.dispose();
			} else if (s.equals("Valider")) {
				try {
					this.addBien();
				} catch (DAOException de1) {
					de1.printStackTrace();
				}
			}
				
		} else {
			@SuppressWarnings("rawtypes")
			JComboBox ComboBoxselected = (JComboBox) e.getSource();
			if (ComboBoxselected==this.vue.getComboBox()) {
				String optionSelected = (String) ComboBoxselected.getSelectedItem();
				if (optionSelected == "Garage") {
					this.vue.desactiverChamps();
					this.vue.champsNonObligatoiresGarage();
				}else {
					this.vue.activerChamps();
					this.vue.champsObligatoireLogement();
				}
			}else {
				String optionSelected = (String) ComboBoxselected.getSelectedItem();
				String typeImmeuble = this.NameImmeubles.get(optionSelected);
				if (typeImmeuble.equals("M")) {
					this.vue.initialiserComboBoxMaison();
				} else {
					this.vue.initialiserComboBoxBatiment();
				}
				this.vue.activerChamps();
			}
		}
	}

	/**
	 * Vérifier et ajouter le bien dans la base de données
	 * @throws DAOException
	 */
	private void addBien() throws DAOException {
		if (!verificationChampIDBien()) { //vérifier si l'identifiant est vide ou null
			this.afficherMessageErreur("Identifiant bien non rempli");
		}else if (verificationBienExiste()) { //vérifier si l'identifiant existe dans la base de données
			this.afficherMessageErreur("Ce bien avec cet identifiant existe déjà");
		}else if (this.verificationEtAffichageErreurChampsDateAcquisition()) { //vérifier et affichage de message d'erreurs du champs de date d'acquisition
			//ajouter le bien dans la base de données
			if (this.isLogement()) {
				this.ajouterLogement();
			}else {
				this.ajouterGarage();
			}
		}
	}
	
	/**
	 * Ajouter des informations sur un garage dans la base de donnees
	 * @throws DAOException
	 */
	private void ajouterGarage() throws DAOException {
		if (this.verificationChampsGarage()) {
			Garage g = new Garage (this.vue.getChampsDateAcquisition(),this.vue.getChampsIdBien(),this.vue.getEntretienPartieCommune());
			this.dao.ajouterBien(g,this.vue.getSelectedImmeuble());
		}
		
	}
	
	/**
	 * Ajouter des informations sur un logement dans la base de donnees
	 * @throws DAOException
	 */
	private void ajouterLogement() throws DAOException{
		// Verification des champs des informations liées au logement
		if (this.verificationChampsLogement()) {
			// Création du logement
		    Logement l = new Logement(
		        this.vue.getChampsDateAcquisition(),
		        this.vue.getChampsIdBien(),
		        this.vue.getChampsNumeroEtage(),
		        this.vue.getChampsNombreDePiece(),
		        this.vue.getChampsSurfaceHabitable(),
		        this.vue.getEntretienPartieCommune()
		    );

		    // Création des compteurs associés
		    List<Compteur> compteurs = new ArrayList<>();
		    compteurs.add(new Compteur(typeCompteur.EAU,PRIX_EAU ));
		    compteurs.add(new Compteur(typeCompteur.ELECTRICITE, PRIX_ELEC));

		    // Appel à la méthode DAO
		    this.dao.ajouterBienEtCompteurs(l, this.vue.getSelectedImmeuble(), compteurs);
		    ajouterReleves(l);
		}
	}

	/**
	 * Ajouter un releve dans la base de données
	 * @param l classe Logement contenant ses informations
	 * @throws DAOException
	 */
	private void ajouterReleves(Logement l) throws DAOException{
		String id_eau = this.daoC.getCompteurFromOneBienSelonType(l.getId_bien(), typeCompteur.EAU);
		Releve releveEau = new Releve(this.vue.getChampsEau(),Integer.valueOf(this.vue.getChampsDateAcquisition().toString().substring(0, 4)));
		this.daoR.ajouterReleve(releveEau, id_eau);
		String id_elec = this.daoC.getCompteurFromOneBienSelonType(l.getId_bien(), typeCompteur.ELECTRICITE);
		Releve releveElec = new Releve(this.vue.getChampsElectricite(),Integer.valueOf(this.vue.getChampsDateAcquisition().toString().substring(0, 4)));
		this.daoR.ajouterReleve(releveElec, id_elec);
	}
	
	/**
	 * Verification des chammps logement et affichage de message en cas d'erreur
	 * @return boolean
	 */
	private boolean verificationChampsLogement() {
		if(this.vue.getEntretienPartieCommune()==null) {
			this.afficherMessageErreur("Entretien partie commune non rempli");
		}else if (this.vue.getChampsNombreDePiece()==null) {
			this.afficherMessageErreur("Le nombre de pièce non rempli");
		}else if (this.vue.getChampsNumeroEtage()==null) {
			this.afficherMessageErreur("Le numéro d'étage non rempli");
		}else if(this.vue.getChampsSurfaceHabitable()==null) {
			this.afficherMessageErreur("La surface habitable non rempli");
		}else if(this.vue.getChampsEau() == null) {
			this.afficherMessageErreur("L'index eau non rempli");
		}else if(this.vue.getChampsElectricite() == null) {
			this.afficherMessageErreur("L'index éléctricité non rempli");
		}else if(this.vue.getChampsSurfaceHabitable()==0) {
			this.afficherMessageErreur("La surface habitable null");
		} else {
			return true;
		}
		return false;
	}

	/**
	 * Verification des champs garage et affichage de message en cas d'erreur
	 * @return boolean
	 */
	private boolean verificationChampsGarage() {
		if(this.vue.getEntretienPartieCommune()==null) {
			this.afficherMessageErreur("Entretien partie commune non rempli");
		}else if(this.vue.getChampsEau() == null) {
			this.afficherMessageErreur("L'index eau non rempli");
		}else if(this.vue.getChampsElectricite() == null) {
			this.afficherMessageErreur("L'index éléctricité non rempli");		
		}else {
			return true;
		}
		return false;
	}
	
	/**
	 * Vérification si le bien ajouté est un logement
	 * @return boolean
	 */
	private boolean isLogement() {
		return this.vue.getComboBoxTypeBien().equals("L");
	}

	/**
	 * Vérification du champs date d'acquisition et affichage de message d'erreur correspondante dans le cas d'échec
	 */
	private boolean verificationEtAffichageErreurChampsDateAcquisition() {
		try {
			if (this.vue.getChampsDateAcquisition()==null) {
				this.afficherMessageErreur("Le format de la date d'acquisition est incorrect");
			}
			return !(this.vue.getChampsDateAcquisition()==null);
		}catch(IllegalArgumentException iae) {
			this.afficherMessageErreur("La date d'acquisition non remplie");
			return false;
		}
		
	}

	/**
	 * Vérifier si le champ de l'identifiant est vide ou null
	 * @return boolean
	 */
	private boolean verificationChampIDBien() {
		return this.vue.getChampsIdBien()!=null && !this.vue.getChampsIdBien().isEmpty();
	}

	/**
	 * Vérifier si un bien avec l'identifiant donné existe dans la base de données
	 * @return boolean
	 * @throws DAOException
	 */
	private boolean verificationBienExiste() throws DAOException {
		return this.dao.bienExiste(this.vue.getChampsIdBien());
	}

	/**
	 * Retourner le map contenant les identifiants des immeubles avec leur type
	 * @return Map
	 */
	public Map<String, String> getNameImmeubles() {
		return NameImmeubles;
	}	
	
	/**
	 * Afficher une message d'erreur dans une JOptionPan avec l'option de WARNING_MESSAGE 
	 * @param msg message d'erreur à afficher
	 */
	public void afficherMessageErreur(String msg) {
		JOptionPane.showMessageDialog(this.vue, msg,"Attention", JOptionPane.WARNING_MESSAGE);
	}
}