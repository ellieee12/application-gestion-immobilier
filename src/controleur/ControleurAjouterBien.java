package controleur;

import classes.Bien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import sae3a01.VueAjouterBien;

public class ControleurAjouterBien implements ActionListener {
	
	private static ControleurAjouterBien controleur;
	private VueAjouterBien vue;
	
	public void initialiserControleur(VueAjouterBien vue) {
		this.vue = vue;
	}
	
	//Constructeur du controleur
	private ControleurAjouterBien () {}
	
	public void actionPerformed (ActionEvent e) {
		
		JComboBox ComboBoxselected = (JComboBox) e.getSource();
		String optionSelected = (String) ComboBoxselected.getSelectedItem();
		
		if (e.getSource() instanceof JButton) {
			String s = ((JButton) e.getSource()).getText();

			if (s == "Annuler") {
				this.vue.dispose();
			} else if (s == "Valider") {
			}
		} else {
			if (optionSelected == "Garage") {
				this.vue.desactiverChamps();
			}else {
				this.vue.activerChamps();
			}
		}
	}
	
	public static synchronized ControleurAjouterBien getControleurAjouterBien () {
		if (controleur == null) {
			controleur = new ControleurAjouterBien();
		}
		return controleur;
	}
	
	
	
	
}