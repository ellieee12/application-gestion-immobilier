package controleur;

import classes.Batiment;
import classes.Garage;
import classes.Logement;
import classes.Maison;
import ihm.VueAjouterBien;
import ihm.VueMesBiens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import modeleDAO.BienDAO;

public class ControleurMesBiens implements ActionListener {

	private static ControleurMesBiens controleurMesBiens;
	
	private VueMesBiens vue;
	private List<String> Id;
	private List<Class<?>> type;
	private List<Integer> numeroEtage;
	private List<Float> surfaceHabitable;
	private List<Integer> nombrePiece;
	private List<Date> date;
	
	private ControleurMesBiens(VueMesBiens vue) {
		try {
			this.vue = vue;
			this.Id = new LinkedList<>();
			this.type = new LinkedList<>();
			this.numeroEtage = new LinkedList<>();
			this.surfaceHabitable = new LinkedList<>();
			this.nombrePiece = new LinkedList<>();
			this.date = new LinkedList<>();
			
			BienDAO bien = new BienDAO();
			ResultSet rs = bien.getAllBiens();
			while(rs.next()) {
				Id.add(rs.getString(1));
				if (rs.getString(2) == "G") {
					type.add(Garage.class);
				} else {
					type.add(Logement.class);
				}
				numeroEtage.add(rs.getInt(3));
				surfaceHabitable.add(rs.getFloat(4));
				nombrePiece.add(rs.getInt(5));
				date.add(rs.getDate(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ControleurMesBiens getControleur(VueMesBiens vue) {
		if (controleurMesBiens == null) {
			controleurMesBiens = new ControleurMesBiens(vue);
		}
		return controleurMesBiens;
		
	}
	
	public void Update() {
		controleurMesBiens = null;
		getControleur(this.vue);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton  b = (JButton) e.getSource();	
		if (b.getText() == "Ajouter") {
			new VueAjouterBien();
		} else if (b.getText() == "Supprimer") {
			BienDAO bien = new BienDAO();
			int rs = bien.supprimerBien(this.Id.get(this.vue.getLigneChoisi()));
			this.Update();
		}
		
	}

	public List<String> getId() {
		return Id;
	}
	public List<Class<?>> getType() {
		return type;
	}
	public List<Integer> getNumeroEtage() {
		return numeroEtage;
	}
	public List<Float> getSurfaceHabitable() {
		return surfaceHabitable;
	}
	public List<Integer> getNombrePiece() {
		return nombrePiece;
	}
	public List<Date> getDate() {
		return date;
	}
}
