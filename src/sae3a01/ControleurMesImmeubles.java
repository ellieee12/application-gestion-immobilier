package sae3a01;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import classes.Immeuble;

public class ControleurMesImmeubles implements ActionListener {
	
	private static ControleurAjouterImmeuble controleur;
	
	private VueMesImmeubles vue;
	private ModeleMesImmeubles modele;
	private Immeuble immeuble;
	
	private ControleurMesImmeubles() {}
	
	public void initialiserControleur(ModeleMesImmeubles modele, VueMesImmeubles vue) {
		this.modele=modele;
		this.vue=vue;
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
			
		}
	}

}
