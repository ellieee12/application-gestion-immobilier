import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ControleurLogInSignUp extends MouseAdapter implements ActionListener {

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
				if (this.modele.mdpCorrecte(this.vueLogin.getUsername().getText(), this.vueLogin.getMDP().getPassword())) {
					vueLogin.setVisible(false);
				}
			}
			break;
			
		case SIGNUP:
			if (s.equals("Sign Up")) {
				//String username = this.vueSignUp.get
				//if (this.modele.compteExiste())
				JOptionPane.showMessageDialog(vueLogin, "Compte enregistré","Information",JOptionPane.INFORMATION_MESSAGE);
				vueLogin.setVisible(true);
				vueSignUp.setVisible(false);
				this.etat=Etat.LOGIN;
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
			
			break;
		}
		
	} 


}
