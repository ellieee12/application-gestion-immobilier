package ihm;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controleur.ControleurSaisieLocataire;
import java.awt.FlowLayout;

public class VueSaisieLocataire extends JFramePlus {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JFormattedTextField textFieldTel;
	private JTextField textFieldMail;
	private JTextField textFieldDateDeNaissance;
	private JTextField textFieldId;
	private JPanel contentPane;
	/**
	 * Retourner le nom saisi
	 * @return String
	 */
	public String getNom() {
		return textFieldNom.getText();
	}
	/**
	 * Retourner le prénom saisi
	 * @return String
	 */
	public String getPrenom() {
		return textFieldPrenom.getText();
	}
	/**
	 * Retourner le numéro de téléphone saisi
	 * @return String
	 */
	public String getTel() {
		if (textFieldTel.getText().equals("  -  -  -  -  ")) {
	        return null; // Retourne null en cas d'erreur
	    } else {
			return textFieldTel.getText();
	    }
		
	}
	/**
	 * Retourner le mail saisi
	 * @return String
	 * @throws IllegalArgumentException
	 */
	public String getMail() throws IllegalArgumentException{
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		// Compile the regex
        Pattern p = Pattern.compile(emailRegex);
        // Check if email matches the pattern
        if (textFieldMail.getText().isEmpty()) {
        	return null;
        }else if (!p.matcher(textFieldMail.getText()).matches()) {
        	throw new IllegalArgumentException("Format email incorrect");
        }else {
        	return textFieldMail.getText();
        }
	}
	/**
	 * Retourner la date de naissance
	 * @return Date
	 * @throws IllegalArgumentException
	 */
	public Date getDateDeNaissance () throws IllegalArgumentException{
		try {
			if (textFieldDateDeNaissance.getText().equals("  /  /    ")) {
	        	return null;
			}
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        sdf.setLenient(false);
	        java.util.Date parsedDate = sdf.parse(textFieldDateDeNaissance.getText());
	        return new Date(parsedDate.getTime());
	    } catch (Exception e) {
	        throw new IllegalArgumentException("Format date incorrect"); // Retourne null en cas d'erreur
	    }
	}
	/**
	 * Retourner l'identifiant de locataire saisi
	 * @return String
	 */
	public String getId() {
		return textFieldId.getText();
	}

	/**
	 * Create the frame.
	 */ 	
	public VueSaisieLocataire(VueMesLocataires vue) {
		super(vue);
		setTitle("Saisie Locataire");
		ControleurSaisieLocataire controleur = new ControleurSaisieLocataire(this, vue);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 10));
		
		JLabel lblNewLabel = new JLabel("Locataire");
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 30));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panelLibellés = new JPanel();
		contentPane.add(panelLibellés, BorderLayout.WEST);
		panelLibellés.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panelLibellés.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("<html><font size='3' color=black>Nom</font> <font size='3'color=red>*</font></html>");
		lblNewLabel_1.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_2.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panelLibellés.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("<html><font size='3' color=black>Prenom</font> <font size='3'color=red>*</font></html>");
		lblNewLabel_2.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_3.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panel_6 = new JPanel();
		panelLibellés.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("<html><font size='3' color=black>Téléphone</font> <font size='3'color=red>*</font></html>");
		lblNewLabel_3.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_6.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		panelLibellés.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("<html><font size='3' color=black>Mail</font><font size='3'color=red>*</font></html>");
		lblNewLabel_4.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_10.add(lblNewLabel_4, BorderLayout.NORTH);
		
		JPanel panel_11 = new JPanel();
		panelLibellés.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("<html><font size='3' color=black></font>Date de naissance<font size='3'color=red>*</font></html>");
		lblNewLabel_5.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_11.add(lblNewLabel_5, BorderLayout.NORTH);
		
		JPanel panel_12 = new JPanel();
		panelLibellés.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("<html><font size='3' color=black>Identifiant</font> <font size='3'color=red>*</font></html>");
		lblNewLabel_6.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_12.add(lblNewLabel_6, BorderLayout.NORTH);
		
		JPanel panelChamps = new JPanel();
		this.add(panelChamps, BorderLayout.CENTER);
		panelChamps.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panelChamps.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		textFieldNom = new JTextField();
		panel_4.add(textFieldNom, BorderLayout.NORTH);
		textFieldNom.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panelChamps.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		textFieldPrenom = new JTextField();
		panel_5.add(textFieldPrenom, BorderLayout.NORTH);
		textFieldPrenom.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panelChamps.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		try {
			textFieldTel = new JFormattedTextField(new MaskFormatter("##-##-##-##-##"));
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		panel_7.add(textFieldTel, BorderLayout.NORTH);
		textFieldTel.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panelChamps.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		textFieldMail = new JTextField();
		panel_8.add(textFieldMail, BorderLayout.NORTH);
		textFieldMail.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panelChamps.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		new JTextField();
		try {
			textFieldDateDeNaissance = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		panel_9.add(textFieldDateDeNaissance, BorderLayout.NORTH);
		textFieldDateDeNaissance.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		panelChamps.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		textFieldId = new JTextField();
		panel_13.add(textFieldId, BorderLayout.NORTH);
		textFieldId.setColumns(10);
		
		JPanel panelBoutons = new JPanel();
		contentPane.add(panelBoutons, BorderLayout.SOUTH);
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.setFont(new Font("Roboto", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(controleur);
		panelBoutons.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setFont(new Font("Roboto", Font.PLAIN, 11));
		btnNewButton.addActionListener(controleur);
		panelBoutons.add(btnNewButton);
		
		this.setSizeMulti();
		this.setLogo();
	}

}
