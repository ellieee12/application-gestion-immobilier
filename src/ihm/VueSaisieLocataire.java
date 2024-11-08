package ihm;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VueSaisieLocataire extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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

	/**
	 * Create the frame.
	 */
	public VueSaisieLocataire() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		textField = new JTextField();
		panel_4.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		textField_1 = new JTextField();
		panel_5.add(textField_1, BorderLayout.NORTH);
		textField_1.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		textField_2 = new JTextField();
		panel_7.add(textField_2, BorderLayout.NORTH);
		textField_2.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		textField_3 = new JTextField();
		panel_8.add(textField_3, BorderLayout.NORTH);
		textField_3.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		textField_4 = new JTextField();
		panel_9.add(textField_4, BorderLayout.NORTH);
		textField_4.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		panel_1.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		textField_5 = new JTextField();
		panel_13.add(textField_5, BorderLayout.NORTH);
		textField_5.setColumns(10);
		
		JPanel panel_14 = new JPanel();
		contentPane.add(panel_14, BorderLayout.SOUTH);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Créer");
		btnNewButton.setFont(new Font("Roboto", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_14.add(btnNewButton, BorderLayout.EAST);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_14.add(btnNewButton_1, BorderLayout.WEST);
	}

}
