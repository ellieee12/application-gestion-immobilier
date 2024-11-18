package controleur;

import modeleDAO.BienDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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
				//vérifier si l'identifiant existe dans la base de données
				verificationBienExiste();
				verificationChampIDBien();
				verificationChampsDateAcquisition();
				if (this.vue.getComboBoxTypeBien().equals("L")) {
					if (this.vue.getChampsNombreDePiece()==null || this.vue.getChampsNumeroEtage()==null || 
							this.vue.getChampsSurfaceHabitable()==null){
						JOptionPane.showMessageDialog(this.vue, "Champs obligatoires non remplis","Attention", JOptionPane.WARNING_MESSAGE);
					}else{
						int i = this.dao.ajouterBien(this.vue.getChampsNumeroEtage(), this.vue.getChampsDateAcquisition(),
								this.vue.getChampsIdBien(), this.vue.getChampsNumeroEtage(), this.vue.getChampsSurfaceHabitable(),
								"1", this.vue.getComboBoxTypeBien());
						System.out.println(i + " lignes ajoutées");
					}
				}else {
					int i = this.dao.ajouterBien(null, this.vue.getChampsDateAcquisition(),this.vue.getChampsIdBien(), null, null, 
							"1", this.vue.getComboBoxTypeBien());
					System.out.println(i + " lignes ajoutées");
				}
				
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

	private void verificationChampsDateAcquisition() {
		try {
			if (this.vue.getChampsDateAcquisition()==null) {
				throw new ParseException("Format du date d'acquisition invalide",0);
			}
		}catch(ParseException pEx) {
			JOptionPane.showMessageDialog(this.vue, "Le format du date d'acquisition est incorrecte","Attention", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void verificationChampIDBien() {
		if (this.vue.getChampsIdBien()==null) {
			JOptionPane.showMessageDialog(this.vue, "Veillez saisir l'identifiant du bien","Attention", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void verificationBienExiste() {
		if (this.dao.bienExiste(this.vue.getChampsIdBien())) {
			JOptionPane.showMessageDialog(this.vue, "Ce bien existe déjà","Attention", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static synchronized ControleurAjouterBien getControleurAjouterBien () {
		if (controleur == null) {
			controleur = new ControleurAjouterBien();
		}
		return controleur;
	}
	
	
	
	
}