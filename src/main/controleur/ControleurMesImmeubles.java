package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import ihm.VueAjouterImmeuble;
import ihm.VueMesImmeubles;
import modele.Immeuble;
import ihm.VueMesBiens;
import modele.DAOException;
import modele.ImmeubleDAO;

public class ControleurMesImmeubles extends MouseAdapter implements ActionListener {
	
	private VueMesImmeubles vue;
	private List<Immeuble> immeuble;
	/**
	 * Constructeur de ControleurMesImmeubles
	 * @param vue
	 * @throws DAOException
	 */
	public ControleurMesImmeubles(VueMesImmeubles vue) throws DAOException {
		this.vue = vue;
		this.immeuble = new LinkedList<Immeuble>();
		
		ImmeubleDAO immeubleDAO = new ImmeubleDAO();
		this.immeuble = immeubleDAO.getAllImmeubles();
	}
	/**
	 * Retourner la liste des immeubles
	 * @return
	 */
	public List<Immeuble> getImmeuble() {
		return immeuble;
	}
	/**
	 * Mettre à jour la liste des immeubles
	 * @throws DAOException
	 */
	public void Update() throws DAOException {
        this.immeuble = new LinkedList<>();
        ImmeubleDAO immeuble = new ImmeubleDAO();
        this.immeuble = immeuble.getAllImmeubles();
        this.vue.buildTable(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Ajouter") {
			//ouvrir ajouterImmeuble
				try {
					VueAjouterImmeuble frame = new VueAjouterImmeuble(this.vue);

					frame.setVisible(true);
					this.vue.updateVue();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		} else if (b.getText() == "Supprimer") {
			String[] options = {"Supprimer","Annuler"};
			JOptionPane pane = new JOptionPane();
			if (this.vue.getLigneChoisi() == -1) {
				JOptionPane.showMessageDialog(this.vue, 
						"Veuillez sélectionner un Immeuble avant de supprimer","Attention", JOptionPane.WARNING_MESSAGE);
			} else {
				@SuppressWarnings("static-access")
				int resultat=pane.showOptionDialog(this.vue, 
						"Tous les biens associés à cet immeuble seront également supprimés.",
						"Attention", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
				
				if (resultat==JOptionPane.YES_OPTION) {
					ImmeubleDAO immeuble = new ImmeubleDAO();
					try {
							immeuble.supprimerImmeuble(this.immeuble.get(this.vue.getLigneChoisi()).getId_immeuble());
							this.Update();
					} catch (DAOException e1) {
						e1.printStackTrace();
					}
				}
			}			
		}
	}
	
		
	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table =(JTable) e.getSource();
		if (table.isEnabled() && e.getClickCount() == 2 && table.getSelectedRow() != -1) {
			VueMesBiens frame = null;
			try {
				frame = new VueMesBiens(this.vue, immeuble.get(table.getSelectedRow()).getId_immeuble());
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.vue.updateVue();
			frame.setVisible(true);
		}
	}
}