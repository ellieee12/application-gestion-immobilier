package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.VueAjouterFacture;
import ihm.VueListFactures;
import modele.Facture;
import modele.DAOException;
import modele.FactureDAO;

public class ControleurListFactures implements ActionListener{
	
	private VueListFactures vue;
	private List<Facture> factures;
	/**
	 * Constructeur ControleurListFactures
	 * @param vue
	 */
	public ControleurListFactures(VueListFactures vue) {
		try {
			this.vue = vue;
			this.factures = new LinkedList<>();
			
			FactureDAO factureDAO = new FactureDAO();
			 this.factures = factureDAO.getAllFactures();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Mettre à jour la fenêtre 
	 * @throws DAOException
	 */
	public void Update() throws DAOException {
		try {
			this.factures = new LinkedList<>();
			
			FactureDAO factureDAO = new FactureDAO();
			this.factures = factureDAO.getAllFactures();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		this.vue.buildTable(this);
    }
	/**
	 * Retourner la liste d es factures
	 * @return
	 */
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
					this.vue.updateVue();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		} else if (b.getText() == "Supprimer") {
			String[] options = {"Supprimer","Annuler"};
			JOptionPane pane = new JOptionPane();
			if (this.vue.getLigneChoisi() == -1) {
				JOptionPane.showMessageDialog(this.vue, 
						"Veuillez sélectionner une Facture avant de supprimer","Attention", JOptionPane.WARNING_MESSAGE);
			} else {
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
}
