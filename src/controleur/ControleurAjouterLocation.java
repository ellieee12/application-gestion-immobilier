package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import classes.Location;
import ihm.VueAjouterDocuments;
import ihm.VueAjouterLocation;
import ihm.VueMesLocations;
import modeleDAO.BienDAO;
import modeleDAO.DAOException;
import modeleDAO.LocataireDAO;
import modeleDAO.LocationDAO;

public class ControleurAjouterLocation implements ActionListener{
	
	private VueAjouterLocation vue;
	private VueMesLocations vueMesLocations;
	private BienDAO bienDAO;
	private LocationDAO locationDAO;
	private LocataireDAO locataireDAO;
	private List<String> locataires;
	private List<String> biens;
	private List<String> id_locataires;	
	
	//add a new method
	public String getIDLocataire(String nomPrenom) {
	        return this.id_locataires.get(this.locataires.indexOf(nomPrenom));
	}

	
	private boolean verifComplet() {
		if (!this.vue.isComplet()) {
			JOptionPane.showMessageDialog(this.vue, 
					"Champs obligatoires non remplis","Attention", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	private void valider() {
		this.vueMesLocations.getControleurMesLocations();
		this.vue.dispose();
	}
	
	private boolean verifLocationExiste() {
		if (this.locationDAO.locationExists(this.vue.getSelectedBien(),
				this.getIDLocataire(this.vue.getSelectedLocataire()),this.vue.getDateDebutLocation())) {
			JOptionPane.showMessageDialog(this.vue, 
					"Cet location existe déjà","Attention", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
	
	private boolean allVerifs() {
		return verifComplet() && !verifLocationExiste();
	}
	
	public ControleurAjouterLocation(VueAjouterLocation vue, VueMesLocations vueMesLocations) throws SQLException, DAOException {
		this.vue=vue;
		this.vueMesLocations = vueMesLocations;
		this.bienDAO=new BienDAO();
		this.locationDAO=new LocationDAO();
		this.locataireDAO=new LocataireDAO();
		this.locataires=new LinkedList<>();
		this.biens=new LinkedList<>();
		this.id_locataires=new LinkedList<>();
		ResultSet locatairesRS = this.locataireDAO.getAllLocataires();
		while (locatairesRS.next()) {
			this.locataires.add(locatairesRS.getString(2)+ " " + locatairesRS.getString(3));
			this.id_locataires.add(locatairesRS.getString(1));
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
		} else if (bouton.getText().equals("Valider")) {
			if (allVerifs()) {
				try {
					Location loc = new Location(this.vue.getDateDebutLocation(), this.vue.isColocation(),
							this.vue.getNbMoisPrevus(), this.vue.getLoyer(), this.vue.getProvisionsCharges(),
							this.vue.getCaution(), this.vue.getSelectedBien());
					this.locationDAO.ajouterLocation(this.vue.getSelectedBien(),
							this.getIDLocataire(this.vue.getSelectedLocataire()), loc);
					VueAjouterDocuments frame = new VueAjouterDocuments(loc,this.vue.getSelectedBien(),
							this.vue.getSelectedLocataire(), this.vueMesLocations);
					valider();
					frame.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
