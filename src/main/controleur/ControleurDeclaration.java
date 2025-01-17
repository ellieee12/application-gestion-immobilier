package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

import ihm.VueDeclaration;
import modele.DAOException;
import modele.FactureDAO;
import modele.LocationDAO;

public class ControleurDeclaration implements ActionListener {
	
	private VueDeclaration vue;
	private float revenus;
	private float charges;
	
	public ControleurDeclaration(VueDeclaration vue) {
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e)  {
		JButton b = (JButton) e.getSource();
		int annee = this.getAnneeEnCours()-1;
		if (b.getText() == "Calculer le revenu") {
			try {
				this.revenus = this.calculSomme12mois(annee)
						+this.calculLoyersTermine(annee)
						+this.getLoyersCommence(annee);
				System.out.println(annee);
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
			this.vue.afficherRevenus(this.revenus);
		}
		if (b.getText() == "Micro-foncier") {
			this.vue.afficherTexteMicroFoncier(revenus);
		}
		if (b.getText() == "Régime Réel") {
			JPanel panel = new JPanel();
			NumberFormatter formatter = createNumberformatter();
	        panel.add(new JLabel("<html>Veuillez entrer le montant des charges  <br>de l'année autre que les travaux</html>"));
	        JFormattedTextField textField = new JFormattedTextField(formatter);
	        textField.setColumns(10);
	        textField.setValue(0);
	        panel.add(textField);
			int option = JOptionPane.showConfirmDialog(this.vue, panel, "Charges", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				float autresCharges = Float.valueOf(textField.getText());
				try {
					this.charges = this.calculCharges(annee, autresCharges);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				this.vue.afficherTexteRegimeReel(this.revenus, this.charges);
	        }
		}
		if (b.getText() == "Imprimer") {
			this.vue.imprimerTexte();
		}
	}

	private float calculSomme12mois(int annee) {
		LocationDAO dao = new LocationDAO();
		try {
			return dao.getSommeLoyers12Mois(annee);	//on multiplie par le nb de mois
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		return -1.0F;
	}
	
	private float calculLoyersTermine(int annee) throws DAOException {
		LocationDAO dao = new LocationDAO();
		List<List<Object>> l = dao.getLoyersTermine(annee);
		float resFinal = 0.0F;
		for (int i=0;i<l.size();i++) {
			float res = 0.0F;
			float loyer = (float) l.get(i).get(0);
			int anneeDebut = (int) l.get(i).get(1);
			int moisDebut = (int) l.get(i).get(2);
			int moisFin = (int) l.get(i).get(3);
			if (anneeDebut < annee) {
				res = loyer*moisFin;
			} else {
				res = loyer*(moisFin-moisDebut+1);
			}
			resFinal+=res;
		}
		return resFinal;
	}
	
	private float getLoyersCommence(int annee) throws DAOException {
		LocationDAO dao = new LocationDAO();
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
	
	private float calculCharges(int annee, float autresCharges) throws DAOException {
		FactureDAO dao = new FactureDAO();
		return dao.getMontantTravaux(annee)+autresCharges;
	}
	
	private NumberFormatter createNumberformatter() {
		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
		return formatter;
	}
}
