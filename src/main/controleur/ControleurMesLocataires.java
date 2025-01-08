package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public ControleurMesLocataires(VueMesLocataires vue) {
		try {
			this.vue = vue;
			this.locataires = new LinkedList<>();
			
			LocataireDAO locationDAO = new LocataireDAO();
			ResultSet rs;
			rs = locationDAO.getAllLocataires();
			while(rs.next()) {
				locataires.add(new Locataire(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(1), rs.getDate(6)));
			}
		} catch (SQLException|DAOException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Locataire> getLocataires(){
		return locataires;
	}
	
	public void Update() {
		try {
            this.locataires = new LinkedList<>();
            LocataireDAO location = new LocataireDAO();
            ResultSet rs;
			rs = location.getAllLocataires();
            while(rs.next()) {
            	locataires.add(new Locataire(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(1), rs.getDate(6)));
    		}
        } catch (SQLException|DAOException e) {
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
				frame.setVisible(true);
				this.Update();
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
			if (resultat == JOptionPane.YES_OPTION) {
				LocataireDAO location = new LocataireDAO();
			}
			this.Update();
		}
		
	}
	/*
	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table = (Table) e.getSource();
		if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						//la frame qu'on voudra afficher 
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			});
		}
	}
	*/	

}
