package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;

import ihm.VueSoldeDeToutCompte;
import modele.BienDAO;
import modele.Compteur;
import modele.Releve;
import modele.Compteur.typeCompteur;
import modele.CompteurDAO;
import modele.DAOException;
import modele.ImmeubleDAO;
import modele.LocationDAO;
import modele.ReleveDAO;

public class ControleurSoldeDeToutCompte implements ActionListener {

	private final float PRIX_EAU = 2.86F;
	private final float PRIX_ELEC = 0.2F;
	private CompteurDAO dao;
	private VueSoldeDeToutCompte vue;
	private Compteur compteurEau;
	private Compteur compteurElec;
	private String idcompteurEau;
	private String idcompteurElec;
	private String id_bien;
	private Date date_debut;
	private int annee;
	private int index;
	private Releve releveEau;
	private Releve releveElec;
	private Integer newIndexEau;
	private Integer newIndexElec;
	private float montantOrdures;
	private float provisionSurCharges;
	private LocationDAO daoL;
	private ReleveDAO daoR;
	private BienDAO daoB;
	private ImmeubleDAO daoI;
	
	public ControleurSoldeDeToutCompte(VueSoldeDeToutCompte vue, String id_bien, Date date_debut) {
		this.vue = vue;
		this.id_bien = id_bien;
		this.date_debut = date_debut;
		this.dao = new CompteurDAO();
		this.daoL = new LocationDAO();
		this.daoR = new ReleveDAO();
		this.daoB = new BienDAO();
		this.daoI = new ImmeubleDAO();
		try {
			this.provisionSurCharges = daoL.getProvisionFromLocation(id_bien, date_debut);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		this.annee = LocalDate.now().getYear();

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
				} catch (DAOException e1) {
					e1.printStackTrace();
				}

				//créer le compteur
				this.compteurEau = new Compteur(typeCompteur.EAU, this.PRIX_EAU);
				//récupérer l'index du relevé
				try {
					this.index = this.daoR.getReleveFromIdCompteur(this.idcompteurEau,this.annee-1);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				//créer le relevé
				this.releveEau = new Releve(this.annee, this.index);

				//electricite
				//récupérer l'id du compteur
				try {
					this.idcompteurElec = this.dao.getCompteurFromOneBienSelonType(this.id_bien, typeCompteur.ELECTRICITE);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				//créer le compteur
				this.compteurElec = new Compteur(typeCompteur.ELECTRICITE, this.PRIX_ELEC);
				//récupérer l'index du relevé
				try {
					this.index = this.daoR.getReleveFromIdCompteur(this.idcompteurElec,this.annee-1);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				//créer le relevé
				this.releveElec = new Releve(this.annee, this.index);
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
					montantEntretien = this.daoB.getEntretienFromIdBien(id_bien);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				float montantTotal = montantEau+montantElec+montantOrdures+montantEntretien;
				float montantProvision = this.provisionSurCharges*(13-LocalDate.now().getMonthValue());
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
//			if (!this.vue.getChampNouvelleProvision().isEmpty()) {
//				try {
//					this.dao.setNouvelleProvision(id_bien, date_debut,Float.valueOf(this.vue.getChampNouvelleProvision()));
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//			// créer nouveau releve dans la bd
//			try {
//				this.dao.ajouterReleve(annee, Integer.valueOf(this.vue.getChampEau()), idcompteurEau);
//				this.dao.ajouterReleve(annee, Integer.valueOf(this.vue.getChampElec()), idcompteurElec);
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
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
			typeImmeuble = this.daoI.getTypeImmeubleFromIdBien(id_bien);	// on récupère le type d'immeuble
		} catch (DAOException e) {
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