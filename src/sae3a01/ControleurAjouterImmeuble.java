package sae3a01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControleurAjouterImmeuble implements ActionListener {
	
	private VueAjouterImmeuble vue;

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			
		} else if (b.getText() == "Valider") {
			//ouvrir mes immeubles
			new VueMesImmeubles();
			// créer une instance de Immeuble
			if (this.vue.getTypeImmeuble()=="Maison") {
				new 
			} else {
				
			}
			this.vue.getAdresse();
		}
	}

}
