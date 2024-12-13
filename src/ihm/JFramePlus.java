package ihm;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JFramePlus extends JFrame {
	
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
	
	public void setSizeMulti(int i) {
		List<Component> comps = getAllComponents(this);
	    for (Component c : comps) {
	        if (c.getFont() != null) {
	        	c.setFont(c.getFont().deriveFont((float) (c.getFont().getSize() + i)));
	        }
	        if (c instanceof JButton) {
	        	c.setPreferredSize(new Dimension((int) c.getPreferredSize().getWidth() + i*2, (int) c.getPreferredSize().getHeight() + i*2));
	        }
	        if (c instanceof JFramePlus) {
	        	c.setBounds(new Rectangle(100,100,(int)c.getBounds().getWidth() + i*500,(int) c.getBounds().getHeight() + i*500));
	        }
	    }
	}

}
