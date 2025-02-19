package controleur;

import ihm.VueAjouterBien;
import ihm.VueMesBiens;
import modele.Bien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.BienDAO;
import modele.DAOException;
import modele.Immeuble;
import modele.ImmeubleDAO;

public class ControleurMesBiens implements ActionListener {

	private VueMesBiens vue;
	private List<Bien> bien;
	private String adresse, cp, ville;
	
	public ControleurMesBiens(VueMesBiens vue) throws DAOException {
		this.vue = vue;
		this.bien = new LinkedList<>();
		
		BienDAO bien = new BienDAO();
		ImmeubleDAO immeuble = new ImmeubleDAO();
		this.bien = bien.getBiensFromOneImmeuble(this.vue.getIdImmeuble());
		Immeuble i = immeuble.getInfoImmeuble(this.vue.getIdImmeuble());
		this.adresse = i.getAdresse();
		this.cp = i.getCp();
		this.ville = i.getVille();
	}
	
	public void Update() throws DAOException {
        this.bien = new LinkedList<>();
		BienDAO bien = new BienDAO();
		ImmeubleDAO immeuble = new ImmeubleDAO();
		System.out.println(this.vue.getIdImmeuble());
		this.bien = bien.getBiensFromOneImmeuble(this.vue.getIdImmeuble());
		Immeuble i = immeuble.getInfoImmeuble(this.vue.getIdImmeuble());
		this.adresse = i.getAdresse();
		this.cp = i.getCp();
		this.ville = i.getVille();
        this.vue.buildTable(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton  b = (JButton) e.getSource();	
		if (b.getText() == "Ajouter") {
			try {
				VueAjouterBien frame = new VueAjouterBien(this.vue);
				this.vue.updateVue();
				frame.setVisible(true);
			} catch (Exception error) {
				error.printStackTrace();
			}
		} else if (b.getText() == "Supprimer") {
			String[] options = {"Supprimer","Annuler"};
			JOptionPane pane = new JOptionPane();
			if (this.vue.getLigneChoisi() == -1) {
				JOptionPane.showMessageDialog(this.vue, 
						"Veuillez sélectionner un Bien avant de supprimer","Attention", JOptionPane.WARNING_MESSAGE);
			} else {
				@SuppressWarnings("static-access")
				int resultat=pane.showOptionDialog(this.vue, 
						"Tous les locations associées à ce bien seront également supprimées.",
						"Attention", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
				if (resultat==JOptionPane.YES_OPTION) {
					BienDAO bien = new BienDAO();
					try {
						bien.supprimerBien(this.bien.get(this.vue.getLigneChoisi()).getId_bien());
						this.Update();
					} catch (DAOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public String getAdresse() {
		return adresse;
	}

	public String getCp() {
		return cp;
	}

	public String getVille() {
		return ville;
	}

	public List<Bien> getBien() {
		return this.bien;
	}
}
