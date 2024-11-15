package sae3a01;

import java.Batiment;
import java.Maison;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControleurAjouterImmeuble implements ActionListener {
	
	private VueAjouterImmeuble vue;
	private ModeleMesImmeubles modele;

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			new VueMesImmeubles();
			this.vue.setVisible(false);
		} else if (b.getText() == "Valider") {
			//ouvrir mes immeubles et fermer cette page
			new VueMesImmeubles();
			this.vue.setVisible(false);
			// créer une instance de Immeuble
			if (this.vue.getTypeImmeuble()=="Maison") {
				this.modele.getList().add(new Maison(this.vue.getAdresse(), this.vue.getCP(), this.vue.getVille(),"random"));
			} else {
				this.modele.getList().add(new Batiment(this.vue.getAdresse(), this.vue.getCP(), this.vue.getVille(),"random"));
			}
		}
	}

}
