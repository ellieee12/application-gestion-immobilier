package controleur;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import modele.Locataire;
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
	
	/**
	 * Retourner l'identifiant du locataire
	 * @param nomPrenom
	 * @return
	 */
	public String getIDLocataire(String nomPrenom) {
	        return this.id_locataires.get(this.locataires.indexOf(nomPrenom));
	}
	/**
	 * Vérifier les champs
	 * @return
	 */
	private boolean verifComplet() {
		if (!this.vue.isComplet()) {
			JOptionPane.showMessageDialog(this.vue, 
					"Champs obligatoires non remplis et/ou date d'acquisition invalide","Attention", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	/**
	 * Vérification du checkbox colocation
	 * @return
	 * @throws DAOException
	 */
	private int verifcheckBoxColoc() throws DAOException {
		Location loc = this.locationDAO.getLocationById_Bien(this.vue.getSelectedBien());
		if (loc == null) {
			return 0;
		}
		
		boolean bienSelectionnéEnColocation = "Oui".equals(loc.isColocation());
		boolean bienEnColocation = "Oui".equals(this.vue.isColocation());
		
		
		if (bienSelectionnéEnColocation && !bienEnColocation){
			return 1;
		} else if (!bienSelectionnéEnColocation && !bienEnColocation){
			return 2;
		}
		
		return 0;
		
	}
	/**
	 * Mettre à jour la liste des locations
	 */
	private void valider() {
		this.vueMesLocations.getControleurMesLocations();
		this.vue.dispose();
	}
	/**
	 * Vérifier si la location existe dans la base de données
	 * @return
	 * @throws HeadlessException
	 * @throws DAOException
	 */
	private boolean verifLocationExiste() throws HeadlessException, DAOException {
		if (this.locationDAO.locationExists(this.vue.getSelectedBien(),
				this.getIDLocataire(this.vue.getSelectedLocataire()),this.vue.getDateDebutLocation())) {
			JOptionPane.showMessageDialog(this.vue, 
					"Cette location existe déjà","Attention", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
	/**
	 * Vérification si la colocation est active
	 * @return
	 * @throws DAOException
	 */
	private boolean verifColocationChecked() throws DAOException {
		
		int statutColocation = verifcheckBoxColoc();
		
		switch (statutColocation) {
			case 1: 
				JOptionPane.showMessageDialog(
					this.vue, 
					"Ce bien est déjà associé à une colocation. Vous ne pouvez ajouter qu'une autre colocation.",
					"Attention", JOptionPane.WARNING_MESSAGE
				);
				
				return false;	
				
			case 2:
	            JOptionPane.showMessageDialog(
	                this.vue,
	                "Vous ne pouvez pas ajouter de colocation ou de location à un bien qui possède déjà une location.",
	                "Attention",
	                JOptionPane.WARNING_MESSAGE
	            );
	            
	            return false;
	        
	        default:
	        	return true;
		}
	}
	/**
	 * Vérifier tous les champs
	 * @return
	 * @throws HeadlessException
	 * @throws DAOException
	 */
	private boolean allVerifs() throws HeadlessException, DAOException {
		return verifComplet() && !verifLocationExiste() && verifColocationChecked();
	}
	/**
	 * Constructeur de ControleurAjouterLocation
	 * @param vue
	 * @param vueMesLocations
	 * @throws SQLException
	 * @throws DAOException
	 */
	public ControleurAjouterLocation(VueAjouterLocation vue, VueMesLocations vueMesLocations) throws SQLException, DAOException {
		this.vue=vue;
		this.vueMesLocations = vueMesLocations;
		this.bienDAO=new BienDAO();
		this.locationDAO=new LocationDAO();
		this.locataireDAO=new LocataireDAO();
		this.locataires=new LinkedList<>();
		this.biens=new LinkedList<>();
		this.id_locataires=new LinkedList<>();
		List<Locataire> liste = this.locataireDAO.getAllLocataires();
		
		for (Locataire l : liste) {
			this.locataires.add(l.getNom()+ " " + l.getPrenom());
			this.id_locataires.add(l.getId_locataire());
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
								this.vue.getCaution(), this.vue.getSelectedBien(), this.getIDLocataire(this.vue.getSelectedLocataire()), null, null);
						
						this.locationDAO.ajouterLocation(this.vue.getSelectedBien(), loc);
						
						VueAjouterDocuments frame = new VueAjouterDocuments(loc,this.vue.getSelectedBien(),
								this.id_locataires.get(this.locataires.indexOf(this.vue.getSelectedLocataire())), this.vueMesLocations);
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
	/**
	 * Retourner la liste des locataires
	 * @return
	 */
	public List<String> getLocataires(){
		return this.locataires;
	}
	/**
	 * Retourner la liste des biens
	 * @return
	 */
	public List<String> getBiens(){
		return this.biens;
	}
}


