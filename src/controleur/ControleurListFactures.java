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
import classes.Garage;
import classes.Immeuble;
import classes.Logement;
import classes.Maison;
import ihm.VueAjouterFacture;
import ihm.VueListFactures;
import ihm.VueMesImmeubles;
import modeleDAO.BienDAO;
import modeleDAO.DAOException;
import modeleDAO.FactureDAO;
import modeleDAO.ImmeubleDAO;

public class ControleurListFactures implements ActionListener{
	
	private VueListFactures vue;
	private List<Facture> factures;
	
	public ControleurListFactures(VueListFactures vue) {
		try {
			this.vue = vue;
			this.factures = new LinkedList<>();
			
			FactureDAO factureDAO = new FactureDAO();
			ResultSet rs = factureDAO.getAllFactures();
			while(rs.next()) {
                this.factures.add(new Facture(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getFloat(7), rs.getFloat(8), rs.getString(9)));
            }
		} catch (DAOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Update() throws DAOException {
		try {
			this.factures = new LinkedList<>();
			
			FactureDAO factureDAO = new FactureDAO();
			ResultSet rs = factureDAO.getAllFactures();
			while(rs.next()) {
                this.factures.add(new Facture(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getFloat(7), rs.getFloat(8), rs.getString(9)));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.vue.buildTable(this);
    }
	
	public List<Facture> getFactures() {
		return this.factures;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Ajouter") {
			//ouvrir ajouterImmeuble
				try {
					VueAjouterFacture frame = new VueAjouterFacture(this.vue);
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		} else if (b.getText() == "Supprimer") {
			FactureDAO facture = new FactureDAO();
			try {
				facture.supprimerFacture(this.factures.get(this.vue.getLigneChoisi()).getNumero());
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
			try {
				this.Update();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
