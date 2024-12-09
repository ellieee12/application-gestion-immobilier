package controleur;

import modeleDAO.BienDAO;
import modeleDAO.DAOException;
import modeleDAO.ImmeubleDAO;

import java.awt.HeadlessException;
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
import classes.Logement;
import ihm.VueAjouterBien;
import ihm.VueMesBiens;

public class ControleurAjouterBien implements ActionListener {
	
	private VueMesBiens vueBiens;
	private VueAjouterBien vue;
	private BienDAO dao;
	private List<String> Immeubles;
	
	//Constructeur du controleur
	public ControleurAjouterBien (VueAjouterBien vue, VueMesBiens vueBiens) {
		try {
			this.vue = vue;
			this.vueBiens=vueBiens;
			this.dao = new BienDAO();
			this.Immeubles = new LinkedList<>();
			
			ImmeubleDAO Immeuble = new ImmeubleDAO();
			ResultSet rs = Immeuble.getImmeublesPourAjouterBien();
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

	private void ajouterGarage() throws DAOException {
		Garage g = new Garage (this.vue.getChampsDateAcquisition(),this.vue.getChampsIdBien(),this.vue.getEntretienPartieCommune());
		this.dao.ajouterBien(g,this.vue.getSelectedImmeuble());
	}

	private void ajouterLogement() throws DAOException {
		Logement l = new Logement(this.vue.getChampsDateAcquisition(),this.vue.getChampsIdBien(),this.vue.getChampsNumeroEtage(),this.vue.getChampsNombreDePiece(), this.vue.getChampsSurfaceHabitable(),this.vue.getEntretienPartieCommune());
		this.dao.ajouterBien(l,this.vue.getSelectedImmeuble());
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

	private boolean verificationBienExiste() throws DAOException {
		return this.dao.bienExiste(this.vue.getChampsIdBien());
	}
	
//	public static synchronized ControleurAjouterBien getControleurAjouterBien (VueAjouterBien vue) {
//		if (controleur == null) {
//			controleur = new ControleurAjouterBien(vue);
//		}
//		return controleur;
//	}

	public List<String> getImmeubles() {
		return Immeubles;
	}
	
	
	
	
}