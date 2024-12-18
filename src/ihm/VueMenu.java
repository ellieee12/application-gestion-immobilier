package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controleur.ControleurMenu;
import modele.DAOException;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class VueMenu extends JFramePlus {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueMenu frame = new VueMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VueMenu() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			Logger.getLogger(VueMenu.class.getName()).log(Level.SEVERE, null, ex);
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		ControleurMenu controleur = ControleurMenu.getControleur(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		try {
			tabbedPane.addTab("Mes Immeubles", null, new VueMesImmeubles(), null);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabbedPane.addTab("Mes Factures", null, new VueListFactures(), null);
		tabbedPane.addTab("Mes Locations", null, new VueMesLocations(), null);
		tabbedPane.addTab("Ajouter un Locataire", null, new VueSaisieLocataire(), null);
		
		this.setSizeMulti(5);
	}

}
