package controleur;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import ihm.VueAjouterBien;
import ihm.VueMesBiens;
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
import modele.ReleveDAO;

public class ControleurAjouterBien implements ActionListener {
	
	private VueMesBiens vueBiens;
	private VueAjouterBien vue;
	private BienDAO dao;
	private CompteurDAO daoC;
	private ReleveDAO daoR;
	private Map<String, String> NameImmeubles;
	private final float PRIX_EAU = 2.86F;
	private final float PRIX_ELEC = 0.2F;
	
	//Constructeur du controleur
	public ControleurAjouterBien (VueAjouterBien vue, VueMesBiens vueBiens) throws DAOException {
		try {
			this.vue = vue;
			this.vueBiens=vueBiens;
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
	
	public void actionPerformed (ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			String s = ((JButton) e.getSource()).getText();

			if (s == "Annuler") {
				this.vue.dispose();
			} else if (s == "Valider") {
				//vérifier si l'identifiant existe dans la base de données
				try {
					if (verificationBienExiste()) {
						JOptionPane.showMessageDialog(this.vue, "Ce bien existe déjà et/ou l'identifiant a déjà été utilisé","Attention", JOptionPane.WARNING_MESSAGE);
					}else if(!verificationChampIDBien()) {
						JOptionPane.showMessageDialog(this.vue, "Veuillez remplir tout les champs","Attention", JOptionPane.WARNING_MESSAGE);
					}else if(!verificationChampsDateAcquisition()) {
						try {
							throw new ParseException("Veuillez remplir tout les champs",0);
						}catch(ParseException pEx) {
							JOptionPane.showMessageDialog(this.vue, "Champs obligatoires non remplis","Attention", JOptionPane.WARNING_MESSAGE);
						}
					}else if (isLogement()) {
						if (champsLogementNonRemplis()){
							JOptionPane.showMessageDialog(this.vue, "Champs obligatoires non remplis et/ou date d'acquisition invalide","Attention", JOptionPane.WARNING_MESSAGE);
						}else {
							 ajouterLogement();
							 this.vueBiens.getControleurMesBiens().Update();
							 this.vue.dispose();
							
						}
					}else { /*if (isImmeubleMaison()) {
						JOptionPane.showMessageDialog(this.vue, "Une maison ne peut contenir qu'un bien de type logement","Attention", JOptionPane.WARNING_MESSAGE);
					} else {*/
						ajouterGarage();
						this.vueBiens.getControleurMesBiens().Update();
						this.vue.dispose();
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (DAOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
				
		} else {
			@SuppressWarnings("rawtypes")
			JComboBox ComboBoxselected = (JComboBox) e.getSource();
			if (ComboBoxselected==this.vue.getComboBox()) {
				String optionSelected = (String) ComboBoxselected.getSelectedItem();
				if (optionSelected == "Garage") {
					this.vue.desactiverChamps();
				}else {
					this.vue.activerChamps();
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
	
	private void ajouterGarage() throws DAOException {
		Garage g = new Garage (this.vue.getChampsDateAcquisition(),this.vue.getChampsIdBien(),this.vue.getEntretienPartieCommune());
		this.dao.ajouterBien(g,this.vue.getSelectedImmeuble());
	}

//	private void ajouterLogement() throws  SQLException, DAOException {
//		Logement l = new Logement(this.vue.getChampsDateAcquisition(),this.vue.getChampsIdBien(),this.vue.getChampsNumeroEtage(),this.vue.getChampsNombreDePiece(), this.vue.getChampsSurfaceHabitable(),this.vue.getEntretienPartieCommune());
//		this.dao.ajouterBien(l,this.vue.getSelectedImmeuble());
//		ajouterCompteursEtReleves(l);
//	}
	
	private void ajouterLogement() throws SQLException, DAOException, InterruptedException {
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


	private void ajouterReleves(Logement l) throws DAOException, SQLException {
		String id_eau = this.daoC.getCompteurFromOneBienSelonType(l.getId_bien(), typeCompteur.EAU);
		this.daoR.ajouterReleve(this.vue.getChampsEau(),Integer.valueOf(this.vue.getChampsDateAcquisition().toString().substring(0, 4)), id_eau);
		String id_elec = this.daoC.getCompteurFromOneBienSelonType(l.getId_bien(), typeCompteur.ELECTRICITE);
		this.daoR.ajouterReleve(this.vue.getChampsElectricite(),Integer.valueOf(this.vue.getChampsDateAcquisition().toString().substring(0, 4)), id_elec);
	}
	
	private boolean champsLogementNonRemplis() {
		return this.vue.getChampsNombreDePiece()==null || this.vue.getChampsNumeroEtage()==null || 
				this.vue.getChampsSurfaceHabitable()==null || this.vue.getChampsEau() == null ||
				this.vue.getChampsElectricite() == null;
	}

	private boolean isLogement() {
		return this.vue.getComboBoxTypeBien().equals("L");
	}

	private boolean verificationChampsDateAcquisition() {
		return this.vue.getChampsDateAcquisition()!=null;
	}

	private boolean verificationChampIDBien() {
		return this.vue.getChampsIdBien()!=null && !this.vue.getChampsIdBien().isEmpty();
	}

	private boolean verificationBienExiste() throws DAOException {
		return this.dao.bienExiste(this.vue.getChampsIdBien());
	}


	public Map<String, String> getNameImmeubles() {
		return NameImmeubles;
	}	
}