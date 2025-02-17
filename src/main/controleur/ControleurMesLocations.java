package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import ihm.VueAjouterLocation;
import ihm.VueMesLocations;
import ihm.VueRegularisation;
import ihm.VueSoldeDeToutCompte;
import modele.DAOException;
import modele.Location;
import modele.LocationDAO;

public class ControleurMesLocations /*extends MouseAdapter*/ implements ActionListener {

	private VueMesLocations vue;
	private List<Location> location;
	private LocationDAO locationDAO;
	
	public ControleurMesLocations(VueMesLocations vue) throws DAOException {
			this.vue = vue;
			this.location = new LinkedList<Location>();
			
			locationDAO = new LocationDAO();
			this.location = locationDAO.getAllLocations();	
	}
	
	public List<Location> getLocation(){
		return location;
	}
	
	public void Update() throws DAOException {
		this.location = new LinkedList<>();
		LocationDAO location = new LocationDAO();
		this.location = location.getAllLocations();
        this.vue.buildTable(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		
		if (b.getText().equals("Ajouter")) {
			//Ouvrir ajouterLocation
			try {
				VueAjouterLocation frame = new VueAjouterLocation(this.vue);
				frame.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		// on vérifie qu'une ligne est selectionnée
		} else if (this.vue.getLigneChoisi() != -1){
			// on vérifie si la location est terminée
			if (this.location.get(this.vue.getLigneChoisi()).getDate_fin() != null) {
				JOptionPane.showMessageDialog(this.vue, 
						"La location selectionnée est terminée","Attention", JOptionPane.WARNING_MESSAGE);
			} else {
				if (b.getText().equals("<html><div style='text-align: center;'>Régularisation<br>des charges</div></html>")){
					try {
						if (LocalDate.now().minusYears(1).isAfter(LocalDate.parse((this.location.get(this.vue.getLigneChoisi()).getDate_regularisation() == null?this.location.get(this.vue.getLigneChoisi()).getDate_debut():this.location.get(this.vue.getLigneChoisi()).getDate_regularisation()).toString()))) {
							VueRegularisation frame = new VueRegularisation(this, this.location.get(this.vue.getLigneChoisi()).getIdBien(), this.location.get(this.vue.getLigneChoisi()).getDate_debut());
							frame.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(this.vue, 
									"Cela ne fait pas 1 ans depuis le commencement de la location ou de la dernière régularisation","Attention", JOptionPane.WARNING_MESSAGE);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (b.getText().equals("<html><div style='text-align: center;'>Solde de<br>tout comptes</div></html>")){
					try {
						VueSoldeDeToutCompte frame = new VueSoldeDeToutCompte(this, this.location.get(this.vue.getLigneChoisi()).getIdBien(), this.location.get(this.vue.getLigneChoisi()).getDate_debut());
						frame.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (b.getText().equals("Supprimer")){
					System.out.println("yes");
					String[] options = {"Supprimer", "Annuler"};
					JOptionPane pane = new JOptionPane();
					@SuppressWarnings("static-access")
					int resultat=pane.showOptionDialog(this.vue, 
							"Tout les documents associés à cette Location vont êtres supprimés.",
							"Attention", 
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[1]);	
					try {
						if (resultat == JOptionPane.YES_OPTION) {
							LocationDAO location = new LocationDAO();
							location.supprimerLocation(this.location.get(this.vue.getLigneChoisi()).getIdBien(),this.location.get(this.vue.getLigneChoisi()).getDate_debut());
						}
						this.Update();
					} catch (DAOException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(this.vue, 
					"Veuillez sélectionner une Location avant de faire une action","Attention", JOptionPane.WARNING_MESSAGE);
		}
	}

}
