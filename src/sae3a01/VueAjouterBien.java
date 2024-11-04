package sae3a01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VueAjouterBien extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueAjouterBien frame = new VueAjouterBien();
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
	public VueAjouterBien() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		JLabel lblNewLabel = new JLabel("Ajouter Bien");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel PanelLibellé = new JPanel();
		contentPane.add(PanelLibellé, BorderLayout.WEST);
		PanelLibellé.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		PanelLibellé.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Numéro Etage");
		panel_3.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		PanelLibellé.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Date Acquisition");
		panel_4.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		PanelLibellé.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Identifiant du Bien");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel_5.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel panel_6 = new JPanel();
		PanelLibellé.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Nombre de pièces");
		panel_6.add(lblNewLabel_4, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		PanelLibellé.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Surface habitable");
		panel_10.add(lblNewLabel_5, BorderLayout.NORTH);
		
		JPanel PanelChamps = new JPanel();
		contentPane.add(PanelChamps, BorderLayout.CENTER);
		PanelChamps.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		PanelChamps.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		panel.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		PanelChamps.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, BorderLayout.NORTH);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		PanelChamps.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		textField_2 = new JTextField();
		panel_2.add(textField_2, BorderLayout.NORTH);
		textField_2.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		PanelChamps.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		textField_3 = new JTextField();
		panel_7.add(textField_3, BorderLayout.NORTH);
		textField_3.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		PanelChamps.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		textField_5 = new JTextField();
		panel_8.add(textField_5, BorderLayout.NORTH);
		textField_5.setColumns(10);
		
		JPanel PanelBoutons = new JPanel();
		contentPane.add(PanelBoutons, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Annuler");
		PanelBoutons.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Valider");
		PanelBoutons.add(btnNewButton_1);
	}

}
