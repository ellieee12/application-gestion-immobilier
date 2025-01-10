package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;

import ihm.VueDeclaration;
import modele.BienDAO;
import modele.DAOException;

public class ControleurDeclaration implements ActionListener {
	
	private VueDeclaration vue;
	private float revenus;
	
	public ControleurDeclaration(VueDeclaration vue) {
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e)  {
		JButton b = (JButton) e.getSource();
		int annee = this.getAnneeEnCours();
		if (b.getText() == "Calculer le revenu") {
			/*
			try {
				this.revenus = this.calculSomme12mois(annee)
						+this.calculLoyersTermine(annee)
						+this.getLoyersCommence(annee);
			} catch (DAOException e1) {
				e1.printStackTrace();
			}*/
			this.revenus = 1000F;
			this.vue.afficherRevenus(this.revenus);
		}
	}

	private float calculSomme12mois(int annee) {
		BienDAO dao = new BienDAO();
		try {
			return dao.getSommeLoyers12Mois(annee)*12;	//on multiplie par le nb de mois
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		return -1.0F;
	}
	
	private float calculLoyersTermine(int annee) throws DAOException {
		BienDAO dao = new BienDAO();
		List<List<Object>> l = dao.getLoyersTermine(annee);
		float resFinal = 0.0F;
		for (int i=0;i<l.size();i++) {
			float res = 0.0F;
			float loyer = (float) l.get(i).get(0);
			int anneeDebut = (int) l.get(i).get(1);
			int moisDebut = (int) l.get(i).get(2);
			int moisFin = (int) l.get(i).get(3);
			if (anneeDebut < annee -1) {
				res = loyer*moisFin;
			} else {
				res = loyer*(moisFin-moisDebut+1);
			}
			resFinal+=res;
		}
		return resFinal;
	}
	
	private float getLoyersCommence(int annee) throws DAOException {
		BienDAO dao = new BienDAO();
		List<List<Object>> l = dao.getLoyersCommence(annee);
		float resFinal = 0.0F;
		for (int i=0;i<l.size();i++) {
			float res = 0.0F;
			float loyer = (float) l.get(i).get(0);
			int moisDebut = (int) l.get(i).get(1);
			res = loyer*(12-moisDebut+1);
			resFinal+=res;
		}
		return resFinal;
	}
	
	private int getAnneeEnCours() {
		return Integer.valueOf(new Date(Calendar.getInstance()
				.getTime().getTime()).toString().substring(0, 4));
	}
}
