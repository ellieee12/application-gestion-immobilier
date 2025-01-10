package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JButton;

import ihm.VueDeclaration;
import modele.BienDAO;
import modele.DAOException;

public class ControleurDeclaration implements ActionListener {
	
	private VueDeclaration vue;

	@Override
	public void actionPerformed(ActionEvent e)  {
		JButton b = (JButton) e.getSource();
		int annee = Integer.valueOf(new Date(Calendar.getInstance()		//annee en cours
				.getTime().getTime()).toString().substring(0, 4));
		if (b.getText() == "Calculer le revenu") {
			BienDAO dao = new BienDAO();
			try {
				float sommmeLoyers12Mois = dao.getSommeLoyers12Mois(annee)*12;
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
