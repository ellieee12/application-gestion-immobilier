package ihm;
import controleur.ControleurLogInSignUp;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Cursor;

public class VueSignUp extends JFramePlus {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomOuEmail;
	private ControleurLogInSignUp controleur;
	private JPasswordField passwordFieldMDP;
	private JPasswordField passwordFieldMDPConfirmer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueSignUp frame = new VueSignUp();
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
	public VueSignUp() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			Logger.getLogger(VueMenu.class.getName()).log(Level.SEVERE, null, ex);
		}
		this.controleur = ControleurLogInSignUp.getControleur();
		this.controleur.setVueSignUp(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Sign up");
		lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		panel_5.add(separator, BorderLayout.SOUTH);
		
		JPanel panel_6 = new JPanel();
		panel_6.setFont(new Font("Roboto", Font.PLAIN, 10));
		contentPane.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Nom d'utilisateur");
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
		panel_6.add(lblNewLabel, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		txtNomOuEmail = new JTextField();
		panel_3.add(txtNomOuEmail, BorderLayout.NORTH);
		txtNomOuEmail.setName("");
		txtNomOuEmail.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_7.setFont(new Font("Roboto", Font.PLAIN, 10));
		contentPane.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe");
		lblNewLabel_2.setFont(new Font("Roboto", Font.PLAIN, 15));
		panel_7.add(lblNewLabel_2, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		passwordFieldMDP = new JPasswordField();
		panel_2.add(passwordFieldMDP, BorderLayout.NORTH);
		
		JPanel panel_8 = new JPanel();
		contentPane.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Confirmer le mot de passe");
		lblNewLabel_3.setFont(new Font("Roboto", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_3, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		passwordFieldMDPConfirmer = new JPasswordField();
		panel_1.add(passwordFieldMDPConfirmer, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(controleur);
		btnSignUp.setFont(new Font("Roboto", Font.PLAIN, 10));
		panel.add(btnSignUp);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Vous avez déjà un compte?");
		lblNewLabel_4.setFont(new Font("Roboto", Font.PLAIN, 10));
		panel_4.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblConnection = new JLabel(" Connectez-vous!");
		lblConnection.addMouseListener(controleur);
		lblConnection.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblConnection.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConnection.setForeground(new Color(0, 0, 160));
		panel_4.add(lblConnection);
		
		this.setSizeMulti();
	}

	public String getNouveauMDP() {
		return String.valueOf(this.passwordFieldMDP.getPassword());
	}
	
	public String getNouveauMDPConfirmation() {
		return String.valueOf(this.passwordFieldMDPConfirmer.getPassword());
	}
	
	public String getNouveauUsername() {
		return this.txtNomOuEmail.getText();
	}
	
	
	

}
