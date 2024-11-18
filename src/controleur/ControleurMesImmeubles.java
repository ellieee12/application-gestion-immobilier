package controleur;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;

import classes.Batiment;
import classes.Immeuble;
import classes.Maison;
import ihm.VueAjouterImmeuble;
import ihm.VueMesImmeubles;
import ihm.VueMesBiens;
import modeleDAO.ImmeubleDAO;

public class ControleurMesImmeubles extends MouseAdapter implements ActionListener {
	
	private static ControleurMesImmeubles controleur;
	
	private VueMesImmeubles vue;
	private List<Immeuble> immeuble;
	
	private ControleurMesImmeubles(VueMesImmeubles vue) {
		try {
			this.vue = vue;
			this.immeuble = new LinkedList<Immeuble>();
			
			ImmeubleDAO immeubleDAO = new ImmeubleDAO();
			ResultSet rs = immeubleDAO.getAllImmeubles();
			while(rs.next()) {
				if (rs.getString(6).equals("M")) {
					immeuble.add(new Maison(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(1),rs.getString(5)));
				} else {
					immeuble.add(new Batiment(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(1),rs.getString(5)));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ControleurMesImmeubles getControleur(VueMesImmeubles vue) {
		if (controleur == null) {
			controleur = new ControleurMesImmeubles(vue);
		}
		return controleur;
		
	}
	
	public List<Immeuble> getImmeuble() {
		return immeuble;
	}

	public void Update() {
		controleur = null;
		getControleur(this.vue);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Ajouter") {
			//fermer cette page et ouvrir ajouterImmeuble
			this.vue.dispose();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VueAjouterImmeuble frame = new VueAjouterImmeuble();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else if (b.getText() == "Supprimer") {
			ImmeubleDAO immeuble = new ImmeubleDAO();
			int rs = immeuble.supprimerImmeuble(this.immeuble.get(this.vue.getLigneChoisi()).getId_immeuble());
			this.Update();
		}
	}
	
		
	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table =(JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);
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
