package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import ihm.VueMesLocataires;
import ihm.VueSaisieLocataire;
import modele.DAOException;
import modele.Locataire;
import modele.LocataireDAO;

public class ControleurMesLocataires implements ActionListener {

	private VueMesLocataires vue;
	private List<Locataire> locataires;
	/**
	 * Constructeur ControleurMesLocataires
	 * @param vue
	 */
	public ControleurMesLocataires(VueMesLocataires vue) {
		try {
			this.vue = vue;
			this.locataires = new LinkedList<>();
			
			LocataireDAO locationDAO = new LocataireDAO();
			this.locataires= locationDAO.getAllLocataires();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Retourner la liste des locataires
	 * @return List
	 */
	public List<Locataire> getLocataires(){
		return locataires;
	}
	/**
	 * Mettre à jour la liste des locataires
	 */
	public void Update() {
		try {
            this.locataires = new LinkedList<>();
            LocataireDAO location = new LocataireDAO();
			this.locataires = location.getAllLocataires();
        } catch (DAOException e) {
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
				VueSaisieLocataire frame = new VueSaisieLocataire(this.vue);
				this.vue.updateVue();
				frame.setVisible(true);
				this.Update();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (b.getText().equals("Supprimer")){
			String[] options = {"Supprimer", "Annuler"};
			JOptionPane pane = new JOptionPane();
			if (this.vue.getLigneChoisi() == -1) {
				JOptionPane.showMessageDialog(this.vue, 
						"Veuillez sélectionner une Locataire avant de supprimer","Attention", JOptionPane.WARNING_MESSAGE);
			} else {
				@SuppressWarnings("static-access")
				int resultat=pane.showOptionDialog(this.vue, 
						"Tout les documents associés à ce locataire vont êtres supprimés.",
						"Attention", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
				if (resultat == JOptionPane.YES_OPTION) {
					LocataireDAO locataire = new LocataireDAO();
					try {
						locataire.supprimerLocataire(this.locataires.get(this.vue.getLigneChoisi()).getId_locataire());
						this.Update();
					} catch (DAOException e1) {
						e1.printStackTrace();
					}
				}
				this.Update();
			}
		}
		
	}
}
