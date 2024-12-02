package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

import classes.Compteur;
import classes.Relevé;
import ihm.VueRegularisation;

public class ControleurRegularisation implements ActionListener {
	
	private VueRegularisation vue;
	private Compteur compteurEau;
	private Compteur compteurElec;
	private Relevé releveEau;
	private Relevé releveElec;
	private Integer newIndexEau;
	private Integer newIndexElec;
	private float montantOrdures;
	
	public ControleurRegularisation(VueRegularisation vue) {
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(this.isComplet()) {
			if (b.getText() == "Valider") {
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
		Random random = new Random();
		if (random.nextInt(2)==0/*maison*/) {
			return (float) (conso*this.compteurEau.getPrix_abonnement() + 176.16);
		} else {
			return (float) (conso*this.compteurEau.getPrix_abonnement() + 11.02);
		}
	}
	
	public float montantElec (int conso) {
		return (float) (conso*this.compteurElec.getPrix_abonnement());
	}
}
