package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ihm.VueSaisieLocataire;

public class ControleurSaisieLocataire implements ActionListener {
	
	private static ControleurSaisieLocataire controleur;
	
	private VueSaisieLocataire vue;
	private ControleurSaisieLocataire() {}
	
	public static synchronized ControleurSaisieLocataire getControleur() {
		if (controleur == null) {
			controleur = new ControleurSaisieLocataire();
		}
		return controleur;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			this.vue.dispose();
		} else if (b.getText() == "Créer") {
			
		}

	}

}
