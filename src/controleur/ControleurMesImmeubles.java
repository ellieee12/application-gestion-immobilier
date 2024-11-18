package sae3a01;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import classes.Batiment;
import classes.Immeuble;
import classes.Maison;
import modeleDAO.ImmeubleDAO;

public class ControleurMesImmeubles implements ActionListener {
	
	private static ControleurMesImmeubles controleur;
	
	private VueMesImmeubles vue;
	private List<Immeuble> immeuble;
	
	private ControleurMesImmeubles(VueMesImmeubles vue) {
		try {
			this.vue = vue;
			this.immeuble = new LinkedList<Immeuble>();
			
			ImmeubleDAO immeubleDAO = new ImmeubleDAO();
			ResultSet rs = immeubleDAO.getAllImmeubles();
			while(rs.next()) {
				if (rs.getString(2).equals("M")) {
					immeuble.add(new Maison(rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
				} else {
					immeuble.add(new Batiment(rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ControleurMesImmeubles getControleur(VueMesImmeubles vue) {
		if (controleur == null) {
			controleur = new ControleurMesImmeubles(vue);
		}
		return controleur;
		
	}
	
	public void Update() {
		controleur = null;
		getControleur(this.vue);
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
			ImmeubleDAO immeuble = new ImmeubleDAO();
			int rs = immeuble.supprimerImmeuble(this.immeuble.get(this.vue.getLigneChoisi()).getId_immeuble());
			this.Update();
		}
	}
	

}
