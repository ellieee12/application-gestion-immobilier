package sae3a01;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import classes.Batiment;
import classes.Garage;
import classes.Immeuble;
import classes.Logement;
import classes.Maison;
import modeleDAO.BienDAO;
import modeleDAO.ImmeubleDAO;

public class ControleurMesImmeubles implements ActionListener {
	
	private static ControleurAjouterImmeuble controleur;
	
	private VueMesImmeubles vue;
	private List<String> Id;
	private List<String> type;
	private List<String> adresse;
	private List<String> cp;
	private List<String> ville;
	private List<Date> date;
	
	private ControleurMesImmeubles(VueMesImmeubles vue) {
		try {
			this.vue = vue;
			this.Id = new LinkedList<>();
			this.type = new LinkedList<>();
			this.adresse = new LinkedList<>();
			this.cp = new LinkedList<>();
			this.ville = new LinkedList<>();
			this.date = new LinkedList<>();
			
			ImmeubleDAO immeuble = new ImmeubleDAO();
			ResultSet rs = immeuble.getAllImmeubles();
			while(rs.next()) {
				Id.add(rs.getString(1));
				if (rs.getString(2) == "M") {
					type.add(Maison.class);
				} else {
					type.add(Batiment.class);
				}
				adresse.add(rs.getInt(3));
				surfaceHabitable.add(rs.getFloat(4));
				nombrePiece.add(rs.getInt(5));
				date.add(rs.getDate(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Ajouter") {
			//fermer cette page et ouvrir ajouterImmeuble
			this.vue.dispose();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VueAjouterImmeuble frame = new VueAjouterImmeuble();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else if (b.getText() == "Supprimer") {
			
		}
	}

}
