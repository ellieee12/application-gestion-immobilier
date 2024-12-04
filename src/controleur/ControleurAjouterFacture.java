package controleur;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import ihm.VueAjouterBien;
import ihm.VueAjouterFacture;
import ihm.VueListFactures;
import ihm.VueMesBiens;
import modeleDAO.BienDAO;
import modeleDAO.DAOException;
import modeleDAO.FactureDAO;
import modeleDAO.ImmeubleDAO;

public class ControleurAjouterFacture {
	
	private VueListFactures VueListFactures;
	private VueAjouterFacture vue;
	private FactureDAO dao;
	
	public ControleurAjouterFacture (VueAjouterFacture vue, VueListFactures VueListFactures) {
		this.vue = vue;
		this.VueListFactures = VueListFactures;
		this.dao = new FactureDAO();
	}
	
	public void actionPerformed (ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			String s = ((JButton) e.getSource()).getText();

			if (s == "Annuler") {
				this.vue.dispose();
			} else if (s == "Valider") {
				//vérifier si l'identifiant existe dans la base de données
				try {
					if (verificationBienExiste()) {
						JOptionPane.showMessageDialog(this.vue, "Ce bien existe déjà","Attention", JOptionPane.WARNING_MESSAGE);
					}else if(!verificationChampIDBien()) {
						JOptionPane.showMessageDialog(this.vue, "Veuillez remplir tout les champs","Attention", JOptionPane.WARNING_MESSAGE);
					}else if(!verificationChampsDateAcquisition()) {
						try {
							throw new ParseException("Veuillez remplir tout les champs",0);
						}catch(ParseException pEx) {
							JOptionPane.showMessageDialog(this.vue, "Veuillez remplir tout les champs","Attention", JOptionPane.WARNING_MESSAGE);
						}
					}else if (isLogement()) {
						if (champsLogementNonRemplis()){
							JOptionPane.showMessageDialog(this.vue, "Veuillez remplir tout les champs","Attention", JOptionPane.WARNING_MESSAGE);
						}else{
							 ajouterLogement();
							 this.vueBiens.getControleurMesBiens().Update();
							 this.vue.dispose();
							
						}
					}else {
						ajouterGarage();
						this.vueBiens.getControleurMesBiens().Update();
						this.vue.dispose();
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (DAOException e1) {
					e1.printStackTrace();
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
}
