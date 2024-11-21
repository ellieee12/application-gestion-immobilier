package controleur;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ihm.VueAjouterImmeuble;
import ihm.VueAjouterLocation;
import ihm.VueMenu;
import ihm.VueMesImmeubles;
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
				VueAjouterImmeuble frame = new VueAjouterImmeuble();
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
		}else {
			try {
				VueAjouterLocation frame = new VueAjouterLocation();
				frame.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}

