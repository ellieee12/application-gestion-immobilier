package ihm;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.jdesktop.swingx.JXLabel;

public class JFramePlus extends JFrame {
	
	static final int i = 4;
	
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

}
