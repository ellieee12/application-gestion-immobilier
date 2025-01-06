package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;

import ihm.VueRegularisation;
import modele.Compteur;
import modele.Relevé;
import modele.Compteur.typeCompteur;
import modele.CompteurDAO;

public class ControleurRegularisation implements ActionListener {

	private final float PRIX_EAU = 2.86F;
	private final float PRIX_ELEC = 0.2F;
	private CompteurDAO dao;
	private VueRegularisation vue;
	private Compteur compteurEau;
	private Compteur compteurElec;
	private String idcompteurEau;
	private String idcompteurElec;
	private String id_bien;
	private Date date_debut;
	private int annee;
	private int index;
	private Relevé releveEau;
	private Relevé releveElec;
	private Integer newIndexEau;
	private Integer newIndexElec;
	private float montantOrdures;
	private float provisionSurCharges;
	
	public ControleurRegularisation(VueRegularisation vue, String id_bien, Date date_debut) {
		this.vue = vue;
		this.id_bien = id_bien;
		this.date_debut = date_debut;
		this.dao = new CompteurDAO();
		try {
			this.provisionSurCharges = dao.getProvisionFromLocation(id_bien, date_debut);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.annee = Integer.valueOf(new Date(Calendar.getInstance().getTime().getTime()).toString().substring(0, 4));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Valider") {
			if(this.isComplet()) {

				// eau
				//récupérer l'id du compteur
				try {
					this.idcompteurEau = this.dao.getCompteurFromOneBienSelonType(this.id_bien, typeCompteur.EAU);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				//créer le compteur
				this.compteurEau = new Compteur(typeCompteur.EAU, this.PRIX_EAU);
				//récupérer l'index du relevé
				try {
					this.index = this.dao.getReleveFromIdCompteur(this.idcompteurEau,this.annee-1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//créer le relevé
				this.releveEau = new Relevé(this.annee, this.index);

				//electricite
				//récupérer l'id du compteur
				try {
					this.idcompteurElec = this.dao.getCompteurFromOneBienSelonType(this.id_bien, typeCompteur.ELECTRICITE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//créer le compteur
				this.compteurElec = new Compteur(typeCompteur.ELECTRICITE, this.PRIX_ELEC);
				//récupérer l'index du relevé
				try {
					this.index = this.dao.getReleveFromIdCompteur(this.idcompteurElec,this.annee-1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//créer le relevé
				this.releveElec = new Relevé(this.annee, this.index);
				//récuperer les valeurs des champs
				this.newIndexEau = Integer.valueOf(this.vue.getChampEau());
				this.newIndexElec = Integer.valueOf(this.vue.getChampElec());
				this.montantOrdures = Float.valueOf(this.vue.getChampOrdure());
				int ConsoEau=0;
				int ConsoElec=0;
				//calcul des consommations
				ConsoEau = newIndexEau-releveEau.getIndexcomp();
				ConsoElec = newIndexElec-releveElec.getIndexcomp();
				//calcul des montants
				float montantEau = this.montantEau(ConsoEau);
				float montantElec = this.montantElec(ConsoElec);
				float montantEntretien = 0F;
				try {
					ResultSet rs = this.dao.getEntretienFromIdBien(id_bien);
					montantEntretien = rs.getFloat(1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				float montantTotal = montantEau+montantElec+montantOrdures+montantEntretien;
				float montantProvision = this.provisionSurCharges*12;
				float montantReste = montantTotal-montantProvision;
				this.vue.afficherMontantEau(montantEau);
				this.vue.afficherMontantElec(montantElec);
				this.vue.afficherEntretien(montantEntretien);
				this.vue.afficherMontantOrdure(montantOrdures);
				this.vue.afficherTotal(montantTotal);
				this.vue.afficherProvision(montantProvision);
				this.vue.afficherReste(montantReste);
			}
		} else if (b.getText()=="Confirmer") {
			if (!this.vue.getChampNouvelleProvision().isEmpty()) {
				try {
					this.dao.setNouvelleProvision(id_bien, date_debut,Float.valueOf(this.vue.getChampNouvelleProvision()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			// créer nouveau releve dans la bd
			try {
				this.dao.ajouterReleve(annee, Integer.valueOf(this.vue.getChampEau()), idcompteurEau);
				this.dao.ajouterReleve(annee, Integer.valueOf(this.vue.getChampElec()), idcompteurElec);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			this.vue.dispose();
		}
	}

	public boolean isComplet() {
		return !this.vue.getChampEau().isEmpty() && !this.vue.getChampElec().isEmpty() 
				&& !this.vue.getChampOrdure().isEmpty();
	}

	//calcul le montant d'eau à partir de la consommation
	public float montantEau(int conso) {
		String typeImmeuble="";
		try {
			ResultSet rs = this.dao.getTypeImmeubleFromIdBien(id_bien);	// on récupère le type d'immeuble
			typeImmeuble = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (typeImmeuble.equals("")) {
			System.out.println("Erreur");
			return 0F;
		} else if (typeImmeuble.equals("Maison")) {
			return (float) (conso*this.compteurEau.getPrix_abonnement() + 176.16);	//le prix est différent si c'est une maison ou un batiment
		} else {
			return (float) (conso*this.compteurEau.getPrix_abonnement() + 11.02);
		}
	}
	
	//calcul le montant d'électricité à partir de la consommation
	public float montantElec (int conso) {
		return (float) (conso*this.compteurElec.getPrix_abonnement());
	}
}