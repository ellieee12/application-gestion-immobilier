package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueAjouterLocation;
import modeleDAO.BienDAO;
import modeleDAO.LocataireDAO;
import modeleDAO.LocationDAO;

public class ControleurAjouterLocation implements ActionListener{
	
	private VueAjouterLocation vue;
	private BienDAO bienDAO;
	private LocationDAO locationDAO;
	private LocataireDAO locataireDAO;
	private List<String> locataires;
	private List<String> biens;
	
	private boolean verifComplet() {
		if (!this.vue.isComplet()) {
			JOptionPane.showMessageDialog(this.vue, 
					"Champs obligatoires non remplis","Attention", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public ControleurAjouterLocation(VueAjouterLocation vue) throws SQLException {
		this.vue=vue;
		this.bienDAO=new BienDAO();
		this.locationDAO=new LocationDAO();
		this.locataireDAO=new LocataireDAO();
		this.locataires=new LinkedList<>();
		this.biens=new LinkedList<>();
		
		ResultSet locatairesRS = this.locataireDAO.getAllLocataires();
		while (locatairesRS.next()) {
			this.locataires.add(locatairesRS.getString(2)+ " " + locatairesRS.getString(3));
		}
		ResultSet biensRS = this.bienDAO.getAllBiens();
		while (biensRS.next()) {
			this.biens.add(biensRS.getString(1));
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		if (bouton.getText().equals("Annuler")) {
			this.vue.dispose();
		} else if(bouton.getText().equals("Valider")) {
			if (verifComplet()) {
				//this.locationDAO.ajouterLocation(null, null, null);
			}
		}
	}
	
	public List<String> getLocataires(){
		return this.locataires;
	}
	
	public List<String> getBiens(){
		return this.biens;
	}

}
