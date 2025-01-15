package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

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
	private CompteurDAO compteurDAO;
	private VueSoldeDeToutCompte vue;
	private ControleurMesLocations controleurMesLocations;
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
	private LocationDAO locationDAO;
	private ReleveDAO releveDAO;
	private BienDAO bienDAO;
	private ImmeubleDAO immeubleDAO;
	
	public ControleurSoldeDeToutCompte(VueSoldeDeToutCompte vue, ControleurMesLocations controleurMesLocations, String id_bien, Date date_debut) {
		this.vue = vue;
		this.controleurMesLocations = controleurMesLocations;
		this.id_bien = id_bien;
		this.date_debut = date_debut;
		
		this.compteurDAO = new CompteurDAO();
		this.locationDAO = new LocationDAO();
		this.releveDAO = new ReleveDAO();
		this.bienDAO = new BienDAO();
		this.immeubleDAO = new ImmeubleDAO();
		try {
			this.provisionSurCharges = locationDAO.getProvisionFromLocation(id_bien, date_debut);
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
					this.idcompteurEau = this.compteurDAO.getCompteurFromOneBienSelonType(this.id_bien, typeCompteur.EAU);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}

				//créer le compteur
				this.compteurEau = new Compteur(typeCompteur.EAU, this.PRIX_EAU);
				//récupérer l'index du relevé
				try {
					this.index = this.releveDAO.getReleveFromIdCompteur(this.idcompteurEau,this.annee-1);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				//créer le relevé
				this.releveEau = new Releve(this.annee, this.index);

				//electricite
				//récupérer l'id du compteur
				try {
					this.idcompteurElec = this.compteurDAO.getCompteurFromOneBienSelonType(this.id_bien, typeCompteur.ELECTRICITE);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				//créer le compteur
				this.compteurElec = new Compteur(typeCompteur.ELECTRICITE, this.PRIX_ELEC);
				//récupérer l'index du relevé
				try {
					this.index = this.releveDAO.getReleveFromIdCompteur(this.idcompteurElec,this.annee-1);
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
					montantEntretien = this.bienDAO.getEntretienFromIdBien(id_bien);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				float montantTotal = montantEau+montantElec+montantOrdures+montantEntretien;
				float montantProvision = 0;
				try {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.YEAR, -1);
					if (c.getTime().compareTo(this.locationDAO.getLocationById_Bien(id_bien).getDate_regularisation() == null?date_debut:locationDAO.getLocationById_Bien(id_bien).getDate_regularisation()) > 0) {
						c.setTimeInMillis(c.getTime().getTime() - (this.locationDAO.getLocationById_Bien(id_bien).getDate_regularisation() == null?date_debut.getTime():locationDAO.getLocationById_Bien(id_bien).getDate_regularisation().getTime()));
						montantProvision = this.provisionSurCharges*(c.get(Calendar.MONTH));
					} else {
						montantProvision = this.provisionSurCharges*12;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
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
					this.locationDAO.setNouvelleProvision(id_bien, date_debut,Float.valueOf(this.vue.getChampNouvelleProvision()));
					this.locationDAO.setDateFin(id_bien, date_debut, new Date(Calendar.getInstance().getTime().getTime()));
					// créer nouveau releve dans la bd
					this.releveDAO.ajouterReleve(new Releve(annee, Integer.valueOf(this.vue.getChampEau())), idcompteurEau);
					this.releveDAO.ajouterReleve(new Releve(annee, Integer.valueOf(this.vue.getChampElec())), idcompteurElec);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
			}
			try {
				this.controleurMesLocations.Update();
			} catch (DAOException e1) {
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
			typeImmeuble = this.immeubleDAO.getTypeImmeubleFromIdBien(id_bien);	// on récupère le type d'immeuble
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