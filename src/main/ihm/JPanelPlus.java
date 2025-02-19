package ihm;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import controleur.ControleurFramePlus;

public class JPanelPlus extends JPanel {
	
	private static List<Component> getAllComponents(final Container c) {
	    Component[] comps = c.getComponents();
	    List<Component> compList = new ArrayList<Component>();
	    for (Component comp : comps) {
	        compList.add(comp);
	        if (comp instanceof Container)
	            compList.addAll(getAllComponents((Container) comp));
	    }
	    return compList;
	}
	
	/**
	 * Active ou désactive les boutons et le tableau sur la vue au moment où on change de page
	 */
	public void updateVue() {
		List<Component> comps = getAllComponents(this);
		for (Component c : comps) {
	        if (c instanceof JButton) {
	        	if (c.isEnabled()) {
	    			c.setEnabled(false);
	    		}else {
	    			c.setEnabled(true);
	    		}
	        } else if (c instanceof JTable) {
	        	if (c.isEnabled()) {
	        		c.setEnabled(false);
	        	} else {
	        		c.setEnabled(true);
	        	}
	        }
		}

	}


}
