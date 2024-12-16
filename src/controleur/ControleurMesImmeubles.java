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

import ihm.VueAjouterImmeuble;
import ihm.VueMesImmeubles;
import modele.Batiment;
import modele.Immeuble;
import modele.Maison;
import ihm.VueMesBiens;
import modele.DAOException;
import modele.ImmeubleDAO;

public class ControleurMesImmeubles extends MouseAdapter implements ActionListener {
	
	private VueMesImmeubles vue;
	private List<Immeuble> immeuble;
	
	public ControleurMesImmeubles(VueMesImmeubles vue) throws DAOException {
		try {
			this.vue = vue;
			this.immeuble = new LinkedList<Immeuble>();
			
			ImmeubleDAO immeubleDAO = new ImmeubleDAO();
			ResultSet rs = immeubleDAO.getAllImmeubles();
			while(rs.next()) {
                if (rs.getString(6).equals("M")) {
                    this.immeuble.add(new Maison(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                } else {
                    this.immeuble.add(new Batiment( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
                }
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	
//	public static synchronized ControleurMesImmeubles getControleur(VueMesImmeubles vue) {
//		if (controleur == null) {
//			controleur = new ControleurMesImmeubles(vue);
//		}
//		return controleur;
//		
//	}
	
	public List<Immeuble> getImmeuble() {
		return immeuble;
	}

	public void Update() throws DAOException {
        try {
            this.immeuble = new LinkedList<>();
            
            ImmeubleDAO immeuble = new ImmeubleDAO();
            ResultSet rs = immeuble.getAllImmeubles();
            while(rs.next()) {
                if (rs.getString(6).equals("M")) {
                    this.immeuble.add(new Maison(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                } else {
                    this.immeuble.add(new Batiment( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		} else if (b.getText() == "Supprimer") {
			String[] options = {"Suppimer","Annuler"};
			JOptionPane pane = new JOptionPane();
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
	
		
	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table =(JTable) e.getSource();
        	if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
        		EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VueMesBiens frame = new VueMesBiens(immeuble.get(table.getSelectedRow()).getId_immeuble());
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
        	}
	}	

}
