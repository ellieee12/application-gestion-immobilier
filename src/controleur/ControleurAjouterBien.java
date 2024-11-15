package controleur;

import java.Bien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import sae3a01.VueAjouterBien;

public class ControleurAjouterBien implements ActionListener {
	
	private static ControleurAjouterBien controleur;
	private VueAjouterBien vue;
	
	//Constructeur du controleur
	private ControleurAjouterBien (VueAjouterBien vue) {}
	
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			String s = ((JButton) e.getSource()).getText();

			if (s == "Annuler") {
				this.vue.dispose();
			} else if (s == "Valider") {
				
			}
		} else {
			JComboBox ComboBoxselected = (JComboBox) e.getSource();
			String optionSelected = (String) ComboBoxselected.getSelectedItem();
			
			if (optionSelected == "Garage") {
				
				this.vue.desactiverChamps();
			}else {
				this.vue.activerChamps();
			}
		}
	}
	
	public static synchronized ControleurAjouterBien getControleurAjouterBien (VueAjouterBien vue) {
		if (controleur == null) {
			controleur = new ControleurAjouterBien(vue);
		}
		return controleur;
	}
	
	
	
	
}