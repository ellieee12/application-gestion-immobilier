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
import javax.swing.JOptionPane;

import modeleDAO.BienDAO;
import modeleDAO.DAOException;
import modeleDAO.ImmeubleDAO;

public class ControleurMesBiens implements ActionListener {

	private VueMesBiens vue;
	private List<Bien> bien;
	private String adresse, cp, ville;
	
	public ControleurMesBiens(VueMesBiens vue) {
		try {
			this.vue = vue;
			this.bien = new LinkedList<>();
			
			BienDAO bien = new BienDAO();
			ImmeubleDAO immeuble = new ImmeubleDAO();
			ResultSet rs = bien.getBiensFromOneImmeuble(this.vue.getIdImmeuble());
			ResultSet rs2 = immeuble.getInfoImmeuble(this.vue.getIdImmeuble());
			while(rs2.next()) {
				this.adresse = rs2.getString(1);
				this.cp = rs2.getString(2);
				this.ville = rs2.getString(3);
			}
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
            ImmeubleDAO immeuble = new ImmeubleDAO();
            ResultSet rs = bien.getBiensFromOneImmeuble(this.vue.getIdImmeuble());
			ResultSet rs2 = immeuble.getInfoImmeuble(this.vue.getIdImmeuble());
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
				VueAjouterBien frame = new VueAjouterBien(this.vue);
				frame.setVisible(true);
			} catch (Exception error) {
				error.printStackTrace();
			}
		} else if (b.getText() == "Supprimer") {
			String[] options = {"Suppimer","Annuler"};
			JOptionPane pane = new JOptionPane();
			@SuppressWarnings("static-access")
			int resultat=pane.showOptionDialog(this.vue, 
					"Tous les locations associées à ce bien seront également supprimées.",
					"Attention", 
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
			if (resultat==JOptionPane.YES_OPTION) {
				BienDAO bien = new BienDAO();
				try {
					bien.supprimerBien(this.bien.get(this.vue.getLigneChoisi()).getId_bien());
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				this.Update();
			}
		}
		
	}
	
	public String getAdresse() {
		return adresse;
	}

	public String getCp() {
		return cp;
	}

	public String getVille() {
		return ville;
	}

	public List<Bien> getBien() {
		return this.bien;
	}
}
