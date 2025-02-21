package ihm;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import controleur.ControleurFramePlus;

public abstract class JFramePlus extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JFramePlus(JFramePlus vue) {
		ControleurFramePlus controleur = new ControleurFramePlus(vue);
		this.addWindowListener(controleur);
	}
	
	public JFramePlus() {}
	
	public JFramePlus(JPanelPlus vue) {
		ControleurFramePlus controleur = new ControleurFramePlus(vue);
		this.addWindowListener(controleur);
	}
	
	static final int i = 3;
	
	public void setLogo() {
		try {
			this.setIconImage(new ImageIcon(getClass().getResource("/images/logo.png")).getImage());
		}catch(Exception e){
		   System.out.println("Application icon not found");
		}
	}
	
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
	
	public void setSizeMulti() {
		List<Component> comps = getAllComponents(this);
	    for (Component c : comps) {
	        if (c.getFont() != null) {
	        	c.setFont(c.getFont().deriveFont((float) (c.getFont().getSize() + i)));
	        	if (c instanceof JLabel) {
	        		if (((JLabel) c).getText().toLowerCase().startsWith("<html>")) {
	        			((JLabel) c).setText(((JLabel) c).getText().replace("3",String.valueOf((float)3+i/2)));
	        		}
	        	}
	        }
	        if (c instanceof JPanel) {
	        	if (((JPanel)c).getBorder() instanceof javax.swing.border.TitledBorder) {
	        		((javax.swing.border.TitledBorder) ((JPanel)c).getBorder()).setTitleFont(c.getFont().deriveFont((float) (c.getFont().getSize() + i)));
	        	}
	        }
	        if (c instanceof JButton) {
	        	c.setPreferredSize(new Dimension((int) c.getPreferredSize().getWidth() + i*2, (int) c.getPreferredSize().getHeight() + i*2));
	        }
	        if (c instanceof JTable) {
	        	((JTable) c).setRowHeight(((JTable) c).getRowHeight() + i);
	        	((JTable) c).getTableHeader().setFont(c.getFont().deriveFont((float) (c.getFont().getSize() + i/2)));
	        }
	        if (c instanceof Container) {
	        	this.setBounds(new Rectangle(100,100,(int)this.getBounds().getWidth() + i,(int) this.getBounds().getHeight() + i));
	        }
	    }
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
