package ihm;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controleur.ControleurSaisieLocataire;

public class VueSaisieLocataire extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldTel;
	private JTextField textFieldMail;
	private JTextField textField_4;
	private JTextField textFieldDateDeNaissance;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueSaisieLocataire frame = new VueSaisieLocataire();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getNom() {
		return textFieldNom.getText();
	}
	public String getPrenom() {
		return textFieldPrenom.getText();
	}
	
	public String getTel() {
		return textFieldTel.getText();
	}
	
	public String getMail() {
		return textFieldMail.getText();
	}
	
	public Date getDateDeNaissance () {
		try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        sdf.setLenient(false);
	        java.util.Date parsedDate = sdf.parse(textFieldDateDeNaissance.getText());
	        return new Date(parsedDate.getTime());
	    } catch (Exception e) {
	        return null; // Retourne null en cas d'erreur
	    }
	}
	
	public String getId() {
		return textFieldId.getText();
	}
	
	public boolean isComplet() {
		return !this.getNom().isEmpty() && !this.getPrenom().isEmpty() && !this.getTel().isEmpty()
				&& !this.getMail().isEmpty() && !this.getId().isEmpty();
	}

	/**
	 * Create the frame.
	 */
	public VueSaisieLocataire() {
		
		ControleurSaisieLocataire controleur = ControleurSaisieLocataire.getControleur();
		controleur.initialiserControleur(this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 10));
		
		JLabel lblNewLabel = new JLabel("Locataire");
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 30));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Nom :");
		lblNewLabel_1.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_2.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Prenom :");
		lblNewLabel_2.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_3.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Téléphone :");
		lblNewLabel_3.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_6.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		panel.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Mail :");
		lblNewLabel_4.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_10.add(lblNewLabel_4, BorderLayout.NORTH);
		
		JPanel panel_11 = new JPanel();
		panel.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Date de naissance :");
		lblNewLabel_5.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_11.add(lblNewLabel_5, BorderLayout.NORTH);
		
		JPanel panel_12 = new JPanel();
		panel.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Identifiant :");
		lblNewLabel_6.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_12.add(lblNewLabel_6, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		textFieldNom = new JTextField();
		panel_4.add(textFieldNom, BorderLayout.NORTH);
		textFieldNom.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		textFieldPrenom = new JTextField();
		panel_5.add(textFieldPrenom, BorderLayout.NORTH);
		textFieldPrenom.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		textFieldTel = new JTextField();
		panel_7.add(textFieldTel, BorderLayout.NORTH);
		textFieldTel.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		textFieldMail = new JTextField();
		panel_8.add(textFieldMail, BorderLayout.NORTH);
		textFieldMail.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		textField_4 = new JTextField();
		try {
			textFieldDateDeNaissance = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		panel_9.add(textFieldDateDeNaissance, BorderLayout.NORTH);
		textFieldDateDeNaissance.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		panel_1.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		textFieldId = new JTextField();
		panel_13.add(textFieldId, BorderLayout.NORTH);
		textFieldId.setColumns(10);
		
		JPanel panel_14 = new JPanel();
		contentPane.add(panel_14, BorderLayout.SOUTH);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Créer");
		btnNewButton.setFont(new Font("Roboto", Font.PLAIN, 11));
		btnNewButton.addActionListener(controleur);
		panel_14.add(btnNewButton, BorderLayout.EAST);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.setFont(new Font("Roboto", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(controleur);
		panel_14.add(btnNewButton_1, BorderLayout.WEST);
	}

}
