package ihm;
import controleur.ControleurLogInSignUp;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.FlowLayout;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

public class VueLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueLogin frame = new VueLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static List<Component> getAllComponents(final Container c) {
	    Component[] comps = c.getComponents();
	    List<Component> compList = new ArrayList<Component>();
	    for (Component comp : comps) {
	        compList.add(comp);
	        if (comp instanceof Container)
	            compList.addAll(getAllComponents((Container) comp));
	    }
	    return compList;
	}
	
	public static void setSizeMulti(List<Component> comps, int i) {
	    for (Component c : comps) {
	        if (c.getFont() != null) {
	        	c.setFont(c.getFont().deriveFont((float) (c.getFont().getSize() + i)));
	        }
	        if (c instanceof JButton) {
	        	c.setPreferredSize(new Dimension((int) c.getPreferredSize().getWidth() + i*2, (int) c.getPreferredSize().getHeight() + i*2));
	        }
	        if (c instanceof Container) {
	        	c.setBounds(new Rectangle(100,100,(int)c.getBounds().getWidth() + i*500,(int) c.getBounds().getHeight() + i*500));
	        }
	    }
	}
	
	public VueLogin() {
		@SuppressWarnings("unused")
		VueSignUp vueSignUp=new VueSignUp();
		ControleurLogInSignUp controleur = ControleurLogInSignUp.getControleur();
		controleur.setVueLogin(this);
		controleur.initialiserControleur();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblSignIn = new JLabel("Sign In");
		lblSignIn.setHorizontalTextPosition(SwingConstants.LEADING);
		lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignIn.setFont(new Font("Roboto", Font.BOLD, 30));
		contentPane.add(lblSignIn);
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		panel_6.add(separator, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblUsername = new JLabel("Nom d'utilisateur");
		panel_2.add(lblUsername, BorderLayout.SOUTH);
		lblUsername.setFont(new Font("Roboto", Font.PLAIN, 15));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		textFieldUsername = new JTextField();
		panel_3.add(textFieldUsername, BorderLayout.NORTH);
		textFieldUsername.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMdp = new JLabel("Mot de passe");
		panel_4.add(lblMdp, BorderLayout.SOUTH);
		lblMdp.setFont(new Font("Roboto", Font.PLAIN, 15));
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		passwordField = new JPasswordField();
		panel_5.add(passwordField, BorderLayout.NORTH);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe oublié?");
		panel_5.add(lblMotDePasse, BorderLayout.CENTER);
		lblMotDePasse.setFont(new Font("Roboto", Font.PLAIN, 10));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setPreferredSize(new Dimension(89, 21));
		btnSignIn.setMaximumSize(new Dimension(30, 21));
		btnSignIn.addActionListener(controleur);
		panel_1.add(btnSignIn);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblCreerCompte_1 = new JLabel("Pas de compte? ");
		lblCreerCompte_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreerCompte_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblCreerCompte_1);
		
		JLabel lblCreerCompte_2 = new JLabel("Créer un compte");
		lblCreerCompte_2.setForeground(new Color(0, 0, 160));
		lblCreerCompte_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCreerCompte_2.addMouseListener(controleur);
		lblCreerCompte_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblCreerCompte_2);
		
		setSizeMulti(getAllComponents(this), 10);
	}
	
	public JTextField getUsername() {
		return this.textFieldUsername;
	}
	
	public JPasswordField getMDP() {
		return this.passwordField;
	}

}