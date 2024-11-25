package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controleur.ControleurAjouterImmeuble;

public class VueAjouterImmeuble extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_adresse;
	private JTextField textField_code_postal;
	private JTextField textField_ville;
	private JTextField textField_id;
	private JComboBox<String> comboBox;
	private JFormattedTextField formattedTextField_date;

	
	public String getTypeImmeuble() {
		return comboBox.getSelectedItem().toString();
	}
	
	public String getAdresse() {
		return textField_adresse.getText();
	}
	
	public String getCP() {
		return textField_code_postal.getText();
	}
	
	public String getVille() {
		return textField_ville.getText();
	}
	
	public String getId() {
		return textField_id.getText();
	}

	public String getPeriodeConstruction() {
		return this.formattedTextField_date.getText();
	}
	
	public boolean isComplet() {
		return !this.getAdresse().isEmpty() && !this.getTypeImmeuble().isEmpty()
				&& !this.getCP().isEmpty() && !this.getVille().isEmpty() && !this.getId().isEmpty();
	}

	/**
	 * Create the frame.
	 */
	public VueAjouterImmeuble(VueMesImmeubles vueImmeubles) {
		ControleurAjouterImmeuble controleur = new ControleurAjouterImmeuble(this,vueImmeubles);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		JLabel lblNewLabel = new JLabel("Ajout d'un immeuble");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel PanelChamps = new JPanel();
		contentPane.add(PanelChamps, BorderLayout.CENTER);
		PanelChamps.setLayout(new GridLayout(0, 1, 0, 5));
		
		JPanel panel_2 = new JPanel();
		PanelChamps.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Maison", "Batiment"}));
		panel_2.add(comboBox, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		PanelChamps.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textField_adresse = new JTextField();
		panel_1.add(textField_adresse, BorderLayout.NORTH);
		textField_adresse.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		PanelChamps.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		textField_code_postal = new JTextField();
		panel_3.add(textField_code_postal, BorderLayout.NORTH);
		textField_code_postal.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		PanelChamps.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		textField_ville = new JTextField();
		panel_5.add(textField_ville, BorderLayout.NORTH);
		textField_ville.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		PanelChamps.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		textField_id = new JTextField();
		panel_6.add(textField_id, BorderLayout.NORTH);
		textField_id.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		PanelChamps.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		this.formattedTextField_date = new JFormattedTextField();
		panel_4.add(formattedTextField_date, BorderLayout.NORTH);
		
		JPanel PanelBouton = new JPanel();
		contentPane.add(PanelBouton, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.addActionListener(controleur);
		PanelBouton.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(controleur);
		PanelBouton.add(btnNewButton);
		
		JPanel PanelLibellé = new JPanel();
		contentPane.add(PanelLibellé, BorderLayout.WEST);
		PanelLibellé.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_7 = new JPanel();
		PanelLibellé.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Type d'immeuble         ");
		panel_7.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel panel_8 = new JPanel();
		PanelLibellé.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Adresse");
		panel_8.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_9 = new JPanel();
		PanelLibellé.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Code Postal");
		panel_9.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		PanelLibellé.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2_1 = new JLabel("Ville");
		panel_10.add(lblNewLabel_2_1, BorderLayout.NORTH);
		
		JPanel panel_11 = new JPanel();
		PanelLibellé.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Id Immeuble");
		panel_11.add(lblNewLabel_5, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		PanelLibellé.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Periode Construction");
		panel.add(lblNewLabel_4, BorderLayout.NORTH);
	}
	
}
