package controleur;

import classes.Bien;
import classes.Garage;
import classes.Logement;
import ihm.VueAjouterBien;
import ihm.VueMesBiens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import modeleDAO.BienDAO;

public class ControleurMesBiens implements ActionListener {

	private VueMesBiens vue;
	private List<Bien> bien;
	
	public ControleurMesBiens(VueMesBiens vue) {
		try {
			this.vue = vue;
			this.bien = new LinkedList<>();
			
			BienDAO bien = new BienDAO();
			ResultSet rs = bien.getAllBiens();
			while(rs.next()) {
				if (rs.getString(2).equals("L")) {
					this.bien.add(new Logement(rs.getDate(6), rs.getString(1), rs.getInt(3), rs.getInt(5), rs.getFloat(4)));
				} else {
					this.bien.add(new Garage(rs.getDate(6), rs.getString(1)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Update() {
		try {
			this.bien = new LinkedList<>();
			
			BienDAO bien = new BienDAO();
			ResultSet rs = bien.getAllBiens();
			while(rs.next()) {
				if (rs.getString(2).equals("L")) {
					this.bien.add(new Logement(rs.getDate(6), rs.getString(1), rs.getInt(3), rs.getInt(5), rs.getFloat(4)));
				} else {
					this.bien.add(new Garage(rs.getDate(6), rs.getString(1)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.vue.buildTable(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton  b = (JButton) e.getSource();	
		if (b.getText() == "Ajouter") {
			try {
				VueAjouterBien frame = new VueAjouterBien();
				frame.setVisible(true);
			} catch (Exception error) {
				error.printStackTrace();
			}
		} else if (b.getText() == "Supprimer") {
			BienDAO bien = new BienDAO();
			int rs = bien.supprimerBien(this.bien.get(this.vue.getLigneChoisi()).getId_bien());
			this.Update();
		}
		
	}

	public List<Bien> getBien() {
		return this.bien;
	}
}
