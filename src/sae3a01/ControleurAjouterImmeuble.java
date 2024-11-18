package sae3a01;

import classes.Batiment;
import classes.Maison;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ControleurAjouterImmeuble implements ActionListener {
	
	private static ControleurAjouterImmeuble controleur;
	
	private VueAjouterImmeuble vue;
	private ModeleMesImmeubles modele;
	
	private ControleurAjouterImmeuble() {}
	
	public void initialiserControleur(VueAjouterImmeuble vue) {
		this.modele = new ModeleMesImmeubles();
		this.vue=vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			//ferme cette page
			this.vue.dispose();
		} else if (b.getText() == "Valider") {
			// créer une instance de Immeuble
			if (vue.isComplet()) {
				if (!this.modele.immeubleExiste(this.vue.getAdresse(),this.vue.getCP(), this.vue.getVille())) {
					if (this.vue.getTypeImmeuble()=="Maison") {
						this.modele.getList().add(new Maison(this.vue.getAdresse(),
								this.vue.getCP(), this.vue.getVille(),"random"));
						if (!this.vue.getPeriodeConstruction().isEmpty()) {
							this.modele.getDernierImmeuble().setPeriode_construction(this.vue.getPeriodeConstruction());;
						}
						//ferme cette page et ouvre mesImmeubles
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									VueMesImmeubles frame = new VueMesImmeubles();
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						this.vue.dispose();
					} else {
						this.modele.getList().add(new Batiment(this.vue.getAdresse(),
								this.vue.getCP(), this.vue.getVille(),"random"));
						if (!this.vue.getPeriodeConstruction().isEmpty()) {
							this.modele.getDernierImmeuble().setPeriode_construction(this.vue.getPeriodeConstruction());;
						}
						//ferme cette page et ouvre mesImmeubles
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									VueMesImmeubles frame = new VueMesImmeubles();
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						this.vue.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(this.vue, "Cet immeuble existe déjà","Attention", JOptionPane.WARNING_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(this.vue, "Champs obligatoires non remplis","Attention", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public static synchronized ControleurAjouterImmeuble getControleur() {
		if (controleur == null) {
			controleur = new ControleurAjouterImmeuble();
		}
		return controleur;
	}

}
