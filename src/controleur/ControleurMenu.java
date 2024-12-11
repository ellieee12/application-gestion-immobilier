package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ihm.VueAjouterLocation;
import ihm.VueListFactures;
import ihm.VueMenu;
import ihm.VueMesImmeubles;
import ihm.VueMesLocations;
import ihm.VueSaisieLocataire;

public class ControleurMenu implements ActionListener {
	
	private static ControleurMenu controleur;
	private VueMenu vue;
	
	private ControleurMenu(VueMenu vue) {
		this.vue = vue;
	}
	
	public static synchronized ControleurMenu getControleur(VueMenu vue) {
		if (controleur == null) {
			controleur = new ControleurMenu(vue);
		}
		return controleur;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Mes Immeubles") {
			try {
				VueMesImmeubles frame = new VueMesImmeubles();
				frame.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (b.getText() == "Ajouter un Locataire") {
			try {
				VueSaisieLocataire frame = new VueSaisieLocataire();
				frame.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (b.getText() == "Mes Factures") {
			try {
				VueListFactures frame = new VueListFactures();
				frame.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				VueMesLocations frame = new VueMesLocations();
				frame.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}

