package controleur;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueAjouterDocuments;
import ihm.VueAjouterLocation;
import ihm.VueMesLocations;
import modele.Location;
import modele.Bien;
import modele.BienDAO;
import modele.DAOException;
import modele.LocataireDAO;
import modele.LocationDAO;

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
					"Champs obligatoires non remplis et/ou date d'acquisition invalide","Attention", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	
	private int verifcheckBoxColoc() {
		Location loc = this.locationDAO.getLocationById_Bien(this.vue.getSelectedBien());
		if (loc == null) {
			return 0;
		}
		if (loc.isColocation().equals("Oui") &&
				 this.vue.isColocation().equals("Non")){
			return 1;
		} else if (loc.isColocation().equals("Non")){
			return 2;
		} else {
			return 0;
		}
		
	}
	
	private void valider() {
		this.vueMesLocations.getControleurMesLocations();
		this.vue.dispose();
	}
	
	private boolean verifLocationExiste() throws HeadlessException, DAOException {
		if (this.locationDAO.locationExists(this.vue.getSelectedBien(),
				this.getIDLocataire(this.vue.getSelectedLocataire()),this.vue.getDateDebutLocation())) {
			JOptionPane.showMessageDialog(this.vue, 
					"Cet location existe déjà","Attention", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
	
	private boolean verifColocationChecked() {
		if (verifcheckBoxColoc() == 1) {
			JOptionPane.showMessageDialog(this.vue, 
					"Ce bien est déjà associé à une colocation. Vous ne pouvez ajouter qu'une autre colocation.",
					"Attention", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (verifcheckBoxColoc() == 2) {
			JOptionPane.showMessageDialog(this.vue, 
					"Vous ne pouvez pas ajouter de colocation ou de location à un bien qui possède déjà une location.",
					"Attention", JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (verifcheckBoxColoc() == 0){
			return true;
		}
		return false;
	}
	
	
	private boolean allVerifs() throws HeadlessException, DAOException {
		return verifComplet() && !verifLocationExiste() && verifColocationChecked();
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
		List<Bien> biensliste= this.bienDAO.getAllBiens();
		for(Bien b : biensliste) {
			this.biens.add(b.getId_bien());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		if (bouton.getText().equals("Annuler")) {
			this.vue.dispose();
		} else if (bouton.getText().equals("Valider")) {
			try {
				if (allVerifs()) {
					try {
						Location loc = new Location(this.vue.getDateDebutLocation(), this.vue.isColocation(),
								this.vue.getNbMoisPrevus(), this.vue.getLoyer(), this.vue.getProvisionsCharges(),
								this.vue.getCaution(), this.vue.getSelectedBien(), this.getIDLocataire(this.vue.getSelectedLocataire()), true);
						
						this.locationDAO.ajouterLocation(this.vue.getSelectedBien(), loc);
						
						VueAjouterDocuments frame = new VueAjouterDocuments(loc,this.vue.getSelectedBien(),
								this.vue.getSelectedLocataire(), this.vueMesLocations);
						valider();
						frame.setVisible(true);
					} catch (DAOException e1) {
						e1.printStackTrace();
					}
				}
			} catch (HeadlessException | DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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


