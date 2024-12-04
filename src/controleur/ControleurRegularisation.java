package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;

import com.mysql.jdbc.ResultSet;

import classes.Compteur;
import classes.Compteur.typeCompteur;
import classes.Relevé;
import ihm.VueRegularisation;
import modeleDAO.CompteurDAO;

public class ControleurRegularisation implements ActionListener {

	private final float PRIX_EAU = 2.86F;
	private final float PRIX_ELEC = 0.2F;
	private CompteurDAO dao;
	private VueRegularisation vue;
	private Compteur compteurEau;
	private Compteur compteurElec;
	private String idcompteur;
	private String id_bien;
	private int annee;
	private int index;
	private Relevé releveEau;
	private Relevé releveElec;
	private Integer newIndexEau;
	private Integer newIndexElec;
	private float montantOrdures;
	
	public ControleurRegularisation(VueRegularisation vue, String id_bien) {
		this.vue = vue;
		this.id_bien = id_bien;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(this.isComplet()) {
			if (b.getText() == "Valider") {
				
				// eau
				try {
					ResultSet rs = (ResultSet) this.dao.getCompteurFromOneBienSelonType(this.id_bien, typeCompteur.EAU);
					this.idcompteur = rs.getString(1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				this.compteurEau = new Compteur(this.idcompteur, typeCompteur.EAU, this.PRIX_EAU);
				try {
					ResultSet rs = (ResultSet) this.dao.getReleveFromIdCompteur(this.idcompteur);
					this.annee = rs.getInt(1);
					this.index = rs.getInt(2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				this.releveEau = new Relevé(this.annee, this.index);
				
				//electricite
				try {
					ResultSet rs = (ResultSet) this.dao.getCompteurFromOneBienSelonType(this.id_bien, typeCompteur.ELECTRICITE);
					this.idcompteur = rs.getString(1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				this.compteurElec = new Compteur(this.idcompteur, typeCompteur.ELECTRICITE, this.PRIX_ELEC);
				
				try {
					ResultSet rs = (ResultSet) this.dao.getReleveFromIdCompteur(this.idcompteur);
					this.annee = rs.getInt(1);
					this.index = rs.getInt(2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				this.releveElec = new Relevé(this.annee, this.index);
				
				this.newIndexEau = Integer.valueOf(this.vue.getChampEau());
				this.newIndexElec = Integer.valueOf(this.vue.getChampElec());
				this.montantOrdures = Float.valueOf(this.vue.getChampEau());
				int ConsoEau=0;
				int ConsoElec=0;
				ConsoEau = newIndexEau-releveEau.getIndexcomp();
				ConsoElec = newIndexElec-releveElec.getIndexcomp();
				float montantEau = this.montantEau(ConsoEau);
				float montantElec = this.montantElec(ConsoElec);
				//float montantEntretien = this.bien.getEntretien;
				float montantTotal = montantEau+montantElec+montantOrdures/*montantEntretien*/;
				//float montantProvision = this.location.getProvision;
				float montantReste = montantTotal/*-montantProvision*/;
				this.vue.afficherMontantEau(montantEau);
				this.vue.afficherMontantElec(montantElec);
				this.vue.afficherEntretien(0/*montantEntretien*/);
				this.vue.afficherMontantOrdure(montantOrdures);
				this.vue.afficherTotal(montantTotal);
				this.vue.afficherProvision(0/*montantProvision*/);
				this.vue.afficherReste(montantReste);
			}
		}
	}
	
	public boolean isComplet() {
		return !this.vue.getChampEau().isEmpty() && !this.vue.getChampElec().isEmpty() 
				&& !this.vue.getChampOrdure().isEmpty();
	}
	
	public float montantEau(int conso) {
		String typeImmeuble="";
		try {
			ResultSet rs = (ResultSet) this.dao.getTypeImmeubleFromIdBien(id_bien);
			typeImmeuble = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (typeImmeuble.equals("")) {
			System.out.println("Erreur");
			return 0F;
		} else if (typeImmeuble.equals("Maison")) {
			return (float) (conso*this.compteurEau.getPrix_abonnement() + 176.16);
		} else {
			return (float) (conso*this.compteurEau.getPrix_abonnement() + 11.02);
		}
	}
	
	public float montantElec (int conso) {
		return (float) (conso*this.compteurElec.getPrix_abonnement());
	}
}
