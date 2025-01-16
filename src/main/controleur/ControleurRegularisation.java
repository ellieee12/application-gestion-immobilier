package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.JButton;

import ihm.VueRegularisation;
import modele.BienDAO;
import modele.Compteur;
import modele.Compteur.typeCompteur;
import modele.CompteurDAO;
import modele.DAOException;
import modele.ImmeubleDAO;
import modele.LocationDAO;
import modele.Releve;
import modele.ReleveDAO;

public class ControleurRegularisation implements ActionListener {

	private final float PRIX_EAU = 2.86F;
	private final float PRIX_ELEC = 0.2F;
	
	private CompteurDAO compteurDAO;
	private ImmeubleDAO immeubleDAO;
	private ReleveDAO releveDAO;
	private BienDAO bienDAO;
	private LocationDAO locationDAO;
	
	private VueRegularisation vue;
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
	
	public ControleurRegularisation(VueRegularisation vue, ControleurMesLocations controleurMesLocations, String id_bien, Date date_debut) {
		this.vue = vue;
		this.controleurMesLocations = controleurMesLocations;
		this.id_bien = id_bien;
		this.date_debut = date_debut;
		
		this.compteurDAO = new CompteurDAO();
		this.immeubleDAO = new ImmeubleDAO();
		this.releveDAO = new ReleveDAO();
		this.bienDAO = new BienDAO();
		this.locationDAO = new LocationDAO();
		try {
			this.provisionSurCharges = this.locationDAO.getProvisionFromLocation(id_bien, date_debut);
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
				try {
					// eau
					//récupérer l'id du compteur
					this.idcompteurEau = this.compteurDAO.getCompteurFromOneBienSelonType(this.id_bien, typeCompteur.EAU);
					//créer le compteur
					this.compteurEau = new Compteur(typeCompteur.EAU, this.PRIX_EAU);
					//récupérer l'index du relevé
					this.index = this.releveDAO.getReleveFromIdCompteur(this.idcompteurEau,this.annee-1);
					//créer le relevé
					this.releveEau = new Releve(this.annee, this.index);
					//electricite
					//récupérer l'id du compteur
					this.idcompteurElec = this.compteurDAO.getCompteurFromOneBienSelonType(this.id_bien, typeCompteur.ELECTRICITE);
					//créer le compteur
					this.compteurElec = new Compteur(typeCompteur.ELECTRICITE, this.PRIX_ELEC);
					//récupérer l'index du relevé
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
				float montantEau = 0f;
				float montantElec = 0f;
				float montantEntretien = 0f;
				try {
					montantEau = this.montantEau(ConsoEau);
					montantElec = this.montantElec(ConsoElec);
					montantEntretien = this.bienDAO.getEntretienFromIdBien(id_bien);
				} catch (DAOException e1) {
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
					this.locationDAO.setNouvelleProvision(id_bien, date_debut,Float.valueOf(this.vue.getChampNouvelleProvision()));
					this.locationDAO.setDateRegularisation(id_bien, date_debut, new Date(Calendar.getInstance().getTime().getTime()));
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
	public float montantEau(int conso) throws DAOException {
		String typeImmeuble="";
		try { 
			typeImmeuble = this.immeubleDAO.getTypeImmeubleFromIdBien(id_bien);	// on récupère le type d'immeuble
		} catch (DAOException e) {
			e.printStackTrace();
		}
		if (typeImmeuble.equals("")) {
			System.out.println("Erreur");
			return 0F;
		} else if (typeImmeuble.equals("M")) {
			return this.compteurEau.calculerMontantEauMaison(conso);	//le prix est différent si c'est une maison ou un batiment
		} else {
			return this.compteurEau.calculerMontantEauBatiment(conso);
		}
	}
	
	//calcul le montant d'électricité à partir de la consommation
	public float montantElec (int conso) {
		return (float) this.compteurElec.calculerMontantElec(conso);
	}
}