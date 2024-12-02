package controleur;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import classes.Batiment;
import classes.Immeuble;
import classes.Location;
import classes.Maison;
import ihm.VueAjouterImmeuble;
import ihm.VueAjouterLocation;
import ihm.VueMesImmeubles;
import ihm.VueMesLocations;
import ihm.VueMesBiens;
import modeleDAO.ImmeubleDAO;
import modeleDAO.LocationDAO;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ControleurMesLocations /*extends MouseAdapter*/ implements ActionListener {

	private VueMesLocations vue;
	private List<Location> location;
	
	public ControleurMesLocations(VueMesLocations vue) {
		try {
			this.vue = vue;
			this.location = new LinkedList<Location>();
			
			LocationDAO locationDAO = new LocationDAO();
			ResultSet rs = locationDAO.getAllLocations();
			
			while(rs.next()) {
				location.add(new Location(rs.getDate(2), Location.isColocationByInt(rs.getInt(4)), rs.getInt(3), rs.getFloat(6), rs.getFloat(5),
						rs.getFloat(7), rs.getDate(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Location> getLocation(){
		return location;
	}
	
	public void Update() {
		try {
            this.location = new LinkedList<>();
            
            LocationDAO location = new LocationDAO();
            ResultSet rs = location.getAllLocations();
            while(rs.next()) {
				this.location.add(new Location(rs.getDate(2), Location.isColocationByInt(rs.getInt(4)), rs.getInt(3), rs.getFloat(6), rs.getFloat(5),
						rs.getFloat(7), rs.getDate(8)));
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.vue.buildTable(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		
		if (b.getText().equals("Ajouter")) {
			//Ouvrir ajouterLocation
			try {
				VueAjouterLocation frame = new VueAjouterLocation();
				frame.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (b.getText().equals("Supprimer")){
			String[] options = {"Supprimer", "Annuler"};
			JOptionPane pane = new JOptionPane();
			@SuppressWarnings("static-access")
			int resultat=pane.showOptionDialog(this.vue, 
					"Tout les documents associés à cette location vont êtres supprimés.",
					"Attention", 
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
			if (resultat == JOptionPane.YES_OPTION) {
				LocationDAO location = new LocationDAO();
				location.supprimerLocation(this.location.get(this.vue.getLigneChoisi()).getIdBien(),this.location.get(this.vue.getLigneChoisi()).getDate_debut());
			}
			this.Update();
		}
		
	}
	/*
	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table = (Table) e.getSource();
		if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						//la frame qu'on voudra afficher 
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			});
		}
	}
	*/
	
	

}
