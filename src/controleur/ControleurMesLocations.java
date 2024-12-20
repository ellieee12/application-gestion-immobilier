package controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import ihm.VueAjouterLocation;
import ihm.VueMesLocations;
import modele.DAOException;
import modele.Location;
import modele.LocationDAO;

public class ControleurMesLocations /*extends MouseAdapter*/ implements ActionListener {

	private VueMesLocations vue;
	private List<Location> location;
	
	public ControleurMesLocations(VueMesLocations vue) throws DAOException {
			this.vue = vue;
			this.location = new LinkedList<Location>();
			
			LocationDAO locationDAO = new LocationDAO();
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
		} else if (b.getText().equals("Supprimer")){
			String[] options = {"Supprimer", "Annuler"};
			JOptionPane pane = new JOptionPane();
			@SuppressWarnings("static-access")
			int resultat=pane.showOptionDialog(this.vue, 
					"Tout les documents associés à cette location vont êtres supprimés.",
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
	
	

}
