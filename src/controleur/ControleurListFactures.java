package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import classes.Batiment;
import classes.Bien;
import classes.Facture;
import classes.Immeuble;
import classes.Maison;
import ihm.VueListFactures;
import ihm.VueMesImmeubles;
import modeleDAO.DAOException;
import modeleDAO.ImmeubleDAO;

public class ControleurListFactures implements ActionListener{
	
	private VueListFactures vue;
	private List<Facture> factures;
	
	public ControleurListFactures(VueListFactures vue) {
		try {
			this.vue = vue;
			this.factures = new LinkedList<>();
			
			ImmeubleDAO immeubleDAO = new ImmeubleDAO();
			ResultSet rs = immeubleDAO.getAllImmeubles();
			while(rs.next()) {
                if (rs.getString(2).equals("M")) {
                    this.immeuble.add(new Maison(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                } else {
                    this.immeuble.add(new Batiment( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
                }
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Facture> getFactures() {
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Ajouter") {
			//ouvrir ajouterImmeuble
				try {
					VueAjouterImmeuble frame = new VueAjouterImmeuble(this.vue);
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		} else if (b.getText() == "Supprimer") {
			
			this.Update();
		}
	}

}
