package controleur;

import modeleDAO.BienDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import ihm.VueAjouterBien;

public class ControleurAjouterBien implements ActionListener {
	
	private static ControleurAjouterBien controleur;
	private VueAjouterBien vue;
	private BienDAO dao;
	
	public void initialiserControleur(VueAjouterBien vue) {
		this.vue = vue;
		this.dao = new BienDAO();
	}
	
	//Constructeur du controleur
	private ControleurAjouterBien () {}
	
	public void actionPerformed (ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			String s = ((JButton) e.getSource()).getText();

			if (s == "Annuler") {
				this.vue.dispose();
			} else if (s == "Valider") {
				int i = this.dao.ajouterBien(this.vue.getChampsNumeroEtage(), this.vue.getChampsDateAcquisition(),
						this.vue.getChampsIdBien(), this.vue.getChampsNumeroEtage(), this.vue.getChampsSurfaceHabitable(),
						"1", this.vue.getComboBoxTypeBien());
				System.out.println(i + " lignes ajoutées");
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
	
	public static synchronized ControleurAjouterBien getControleurAjouterBien () {
		if (controleur == null) {
			controleur = new ControleurAjouterBien();
		}
		return controleur;
	}
	
	
	
	
}