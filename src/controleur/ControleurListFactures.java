package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueAjouterFacture;
import ihm.VueListFactures;
import modele.Facture;
import modele.DAOException;
import modele.Facture;
import modele.FactureDAO;

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
			String[] options = {"Suppimer","Annuler"};
			JOptionPane pane = new JOptionPane();
			@SuppressWarnings("static-access")
			int resultat=pane.showOptionDialog(this.vue, 
					"Êtes-vous sûr de vouloir supprimer cette facture.",
					"Attention", 
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
			if (resultat==JOptionPane.YES_OPTION) {
				FactureDAO facture = new FactureDAO();
				try {
					facture.supprimerFacture(this.factures.get(this.vue.getLigneChoisi()).getNumero());
					this.Update();
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
