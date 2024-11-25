package controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ihm.VueLogin;
import ihm.VueMenu;
import ihm.VueSignUp;
import modele.ModeleLoginSignUp;
public class ControleurLogInSignUp extends MouseAdapter implements ActionListener{
	
	private static ControleurLogInSignUp controleur;
	
	private enum Etat{
		LOGIN,SIGNUP
	}
	private VueLogin vueLogin;
	private VueSignUp vueSignUp;
	private ModeleLoginSignUp modele;
	private Etat etat;

	//VueLogin vueLogin,VueSignUp vueSignUp
	private ControleurLogInSignUp() {}
	
	public void initialiserControleur() {
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
						VueMenu frame = new VueMenu();
						frame.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(vueSignUp, "Login incorrecte","Erreur",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			break;
			
		case SIGNUP:
			if (s.equals("Sign Up")) {
				System.out.println("Try");
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
	
	public static synchronized ControleurLogInSignUp getControleur() {
		if (controleur == null) {
			controleur = new ControleurLogInSignUp();
		}
		return controleur;
	}
	
	public void setVueLogin(VueLogin vueLogin) {
		this.vueLogin=vueLogin;
	}
	
	public void setVueSignUp(VueSignUp vueSignUp) {
		this.vueSignUp=vueSignUp;
	}

}
