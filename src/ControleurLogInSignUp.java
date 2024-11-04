import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ControleurLogInSignUp extends MouseAdapter implements ActionListener{

	private enum Etat{
		LOGIN,SIGNUP
	}
	private VueLogin vueLogin;
	private VueSignUp vueSignUp;
	private ModeleLoginSignUp modele;
	private Etat etat;

	public ControleurLogInSignUp(VueLogin vueLogin,VueSignUp vueSignUp) {
		this.vueLogin=vueLogin;
		this.vueSignUp=vueSignUp;
		this.etat=Etat.LOGIN;
		this.modele=new ModeleLoginSignUp();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = ((JButton)e.getSource()).getText();
		switch(this.etat) {
		case LOGIN:
			if (s.equals("Sign In")) {
				String username = this.vueLogin.getUsername().getText();
				String mdp = String.valueOf(this.vueLogin.getMDP().getPassword());
				if (!this.modele.compteExiste(username)) {
					JOptionPane.showMessageDialog(vueSignUp, "Compte n'existe pas","Erreur",JOptionPane.WARNING_MESSAGE);
				}else {
					if (this.modele.mdpCorrecte(username, mdp)){
						vueLogin.setVisible(false);
					}else {
						JOptionPane.showMessageDialog(vueSignUp, "Mot de passe incorrecte","Erreur",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			break;
			
		case SIGNUP:
			if (s.equals("Sign Up")) {
				String username = this.vueSignUp.getNouveauUsername();
				String mdp1=this.vueSignUp.getNouveauMDP();
				String mdp2=this.vueSignUp.getNouveauMDPConfirmation();
				if (this.modele.compteExiste(username)) {
					JOptionPane.showMessageDialog(vueSignUp, "Compte déjà existant","Erreur",JOptionPane.WARNING_MESSAGE);
				}else {
					if (!mdp1.equals(mdp2)) {
						JOptionPane.showMessageDialog(vueSignUp, "Les mots de passe saisis ne sont pas identiques.","Erreur",JOptionPane.WARNING_MESSAGE);
					}else {
						this.modele.addCompte(username,mdp1);
						JOptionPane.showMessageDialog(vueLogin, "Compte enregistré","Information",JOptionPane.INFORMATION_MESSAGE);
						vueLogin.setVisible(true);
						vueSignUp.setVisible(false);
						this.etat=Etat.LOGIN;
					}
				}	
			}
			break;
		}
	}	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		String s = ((JLabel)e.getSource()).getText();
		switch(this.etat) {
		case LOGIN:
			if (s.equals("Créer un compte")) {
				this.etat=Etat.SIGNUP;
				vueLogin.setVisible(false);
				vueSignUp.setVisible(true);
			}
			break;
			
		case SIGNUP:
			if (s.equals(" Connectez-vous!")) {
				this.etat=Etat.LOGIN;
				vueLogin.setVisible(true);
				vueSignUp.setVisible(false);
			}
			break;
		}
		
	}

}
