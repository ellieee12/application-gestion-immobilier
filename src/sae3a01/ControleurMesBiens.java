package sae3a01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControleurMesBiens implements ActionListener {

	private static ControleurMesBiens controleurMesBiens = new ControleurMesBiens();
	
	private ControleurMesBiens(){
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton  b = (JButton) e.getSource();
		if(true) {
			
		}
		
	}

	public static ControleurMesBiens getControlleur() {
		return controleurMesBiens;
	}
}
