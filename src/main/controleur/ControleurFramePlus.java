package controleur;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ihm.JFramePlus;
import ihm.JPanelPlus;

public class ControleurFramePlus extends WindowAdapter {
	
	private Container vue;
	/**
	 * Constructeur ControleurFramePlus
	 * @param vue
	 */
	public ControleurFramePlus(JFramePlus vue) {
		this.vue = vue;
	}
	/**
	 * Constructeur ControleurFramePlus
	 * @param vue
	 */
	public ControleurFramePlus(JPanelPlus vue) {
		this.vue = vue;
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		if (this.vue instanceof JFramePlus) {
			((JFramePlus) vue).updateVue();
		} else {
			((JPanelPlus) vue).updateVue();
		}
	}
	
}
