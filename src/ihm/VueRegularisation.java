package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VueRegularisation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueRegularisation frame = new VueRegularisation();
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
	public VueRegularisation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(20, 20));
		
		JPanel panelHaut = new JPanel();
		contentPane.add(panelHaut, BorderLayout.NORTH);
		panelHaut.setLayout(new BorderLayout(10, 10));
		
		JLabel lblTitre = new JLabel("Régularisation des charges");
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 26));
		panelHaut.add(lblTitre, BorderLayout.NORTH);
		
		JPanel panelChamps = new JPanel();
		panelHaut.add(panelChamps, BorderLayout.SOUTH);
		panelChamps.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblIndexEau = new JLabel("Nouvel index eau");
		lblIndexEau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelChamps.add(lblIndexEau);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelChamps.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nouvel index électricité");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelChamps.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelChamps.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Montant ordures ménagères");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelChamps.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelChamps.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panelChamps.add(panel_1);
		
		JPanel panel = new JPanel();
		panelChamps.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(btnNewButton, BorderLayout.EAST);
		
		JPanel panelTotaux = new JPanel();
		contentPane.add(panelTotaux, BorderLayout.CENTER);
		panelTotaux.setLayout(new GridLayout(0, 2, 0, 10));
		
		JLabel lblNewLabel_2 = new JLabel("Consommation d'eau : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_2);
		
		JLabel lblNewLabel_9 = new JLabel("€");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_9);
		
		JLabel lblNewLabel_3 = new JLabel("Consommation d'électricité : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_3);
		
		JLabel lblNewLabel_10 = new JLabel("€");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_10);
		
		JLabel lblNewLabel_4 = new JLabel("Montant entretien parties communes :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_4);
		
		JLabel lblNewLabel_11 = new JLabel("€");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_11);
		
		JLabel lblNewLabel_5 = new JLabel("Montant ordures ménagères :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_5);
		
		JLabel lblNewLabel_12 = new JLabel("€");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_12);
		
		JLabel lblNewLabel_6 = new JLabel("Total : ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_6);
		
		JLabel lblNewLabel_13 = new JLabel("€");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_13);
		
		JLabel lblNewLabel_7 = new JLabel("Provision sur charges");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_7);
		
		JLabel lblNewLabel_14 = new JLabel("€");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_14);
		
		JLabel lblNewLabel_8 = new JLabel("Reste à charge :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_8);
		
		JLabel lblNewLabel_15 = new JLabel("€");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_15);
	}

}
