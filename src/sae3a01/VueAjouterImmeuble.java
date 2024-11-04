package sae3a01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;
import javax.swing.JFormattedTextField;
import java.awt.Font;

public class VueAjouterImmeuble extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueAjouterImmeuble frame = new VueAjouterImmeuble();
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
	public VueAjouterImmeuble() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		PanelChamps.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		PanelChamps.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Maison", "Batiment"}));
		panel_2.add(comboBox, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		PanelChamps.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, BorderLayout.NORTH);
		textField_1.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		PanelChamps.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		textField_2 = new JTextField();
		panel_3.add(textField_2, BorderLayout.NORTH);
		textField_2.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		PanelChamps.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		panel_5.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		PanelChamps.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		panel_4.add(formattedTextField, BorderLayout.NORTH);
		
		JPanel PanelBouton = new JPanel();
		contentPane.add(PanelBouton, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		PanelBouton.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Valider");
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
		
		JPanel panel = new JPanel();
		PanelLibellé.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Periode Construction");
		panel.add(lblNewLabel_4, BorderLayout.NORTH);
	}
}
