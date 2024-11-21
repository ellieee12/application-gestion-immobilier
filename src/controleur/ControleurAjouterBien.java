package controleur;

import modeleDAO.BienDAO;
import modeleDAO.ImmeubleDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import classes.Garage;
import classes.Immeuble;
import classes.Logement;
import ihm.VueAjouterBien;

public class ControleurAjouterBien implements ActionListener {
	
	private static ControleurAjouterBien controleur;
	private VueAjouterBien vue;
	private BienDAO dao;
	private List<String> Immeubles;
	
	//Constructeur du controleur
	private ControleurAjouterBien (VueAjouterBien vue) {
		try {
			this.vue = vue;
			this.dao = new BienDAO();
			this.Immeubles = new LinkedList<>();
			
			ImmeubleDAO Immeuble = new ImmeubleDAO();
			ResultSet rs = Immeuble.getImmeubles();
			while(rs.next()) {
				this.Immeubles.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed (ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			String s = ((JButton) e.getSource()).getText();

			if (s == "Annuler") {
				this.vue.dispose();
			} else if (s == "Valider") {
				//vérifier si l'identifiant existe dans la base de données
				if (verificationBienExiste()) {
					JOptionPane.showMessageDialog(this.vue, "Ce bien existe déjà","Attention", JOptionPane.WARNING_MESSAGE);
				}else if(!verificationChampIDBien()) {
					JOptionPane.showMessageDialog(this.vue, "Veillez saisir l'identifiant du bien","Attention", JOptionPane.WARNING_MESSAGE);
				}else if(!verificationChampsDateAcquisition()) {
					try {
						throw new ParseException("Format du date d'acquisition invalide",0);
					}catch(ParseException pEx) {
						JOptionPane.showMessageDialog(this.vue, "Le format du date d'acquisition est incorrecte","Attention", JOptionPane.WARNING_MESSAGE);
					}
				}else if (isLogement()) {
					if (champsLogementNonRemplis()){
						JOptionPane.showMessageDialog(this.vue, "Champs obligatoires non remplis","Attention", JOptionPane.WARNING_MESSAGE);
					}else{
						ajouterLogement();
					}
				}else {
					ajouterGarage();
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

	private void ajouterGarage() {
		int i = this.dao.ajouterBien(null, this.vue.getChampsDateAcquisition(),this.vue.getChampsIdBien(), null, null, 
				"1", this.vue.getComboBoxTypeBien());
		System.out.println(i + " lignes ajoutées");
	}

	private void ajouterLogement() {
		int i = this.dao.ajouterBien(this.vue.getChampsNumeroEtage(), this.vue.getChampsDateAcquisition(),
				this.vue.getChampsIdBien(), this.vue.getChampsNumeroEtage(), this.vue.getChampsSurfaceHabitable(),
				this.vue.getSelectedImmeuble(), this.vue.getComboBoxTypeBien());
		System.out.println(i + " lignes ajoutées");
	}

	private boolean champsLogementNonRemplis() {
		return this.vue.getChampsNombreDePiece()==null || this.vue.getChampsNumeroEtage()==null || 
				this.vue.getChampsSurfaceHabitable()==null;
	}

	private boolean isLogement() {
		return this.vue.getComboBoxTypeBien().equals("L");
	}

	private boolean verificationChampsDateAcquisition() {
		return this.vue.getChampsDateAcquisition()!=null;
	}

	private boolean verificationChampIDBien() {
		return this.vue.getChampsIdBien()!=null && !this.vue.getChampsIdBien().isEmpty();
	}

	private boolean verificationBienExiste() {
		return this.dao.bienExiste(this.vue.getChampsIdBien());
	}
	
	public static synchronized ControleurAjouterBien getControleurAjouterBien (VueAjouterBien vue) {
		if (controleur == null) {
			controleur = new ControleurAjouterBien(vue);
		}
		return controleur;
	}

	public List<String> getImmeubles() {
		return Immeubles;
	}
	
	
	
	
}