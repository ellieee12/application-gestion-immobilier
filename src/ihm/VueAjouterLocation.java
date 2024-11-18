package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class VueAjouterLocation extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBail;
	private JTextField textFieldDateDebutLocation;
	private JTextField textFieldNombreMoisPrevus;
	private JTextField textFieldLoyerLocataire;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueAjouterLocation frame = new VueAjouterLocation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VueAjouterLocation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		JLabel LabelAjouterLocation = new JLabel("Ajouter une Location");
		LabelAjouterLocation.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(LabelAjouterLocation, BorderLayout.NORTH);
		
		JPanel panelLibellé = new JPanel();
		contentPane.add(panelLibellé, BorderLayout.WEST);
		panelLibellé.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panelLibellé.add(panel);
		panel.setLayout(new BorderLayout(5, 0));
		
		JLabel lblNewLabel = new JLabel("Locataire : ");
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panelLibellé.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Etat des Lieux : ");
		panel_1.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panelLibellé.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Colocation : ");
		panel_2.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panelLibellé.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Loyer Payé : ");
		panel_3.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panelLibellé.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Bail : ");
		panel_4.add(lblNewLabel_4, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panelLibellé.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Date Début Location :");
		panel_5.add(lblNewLabel_5, BorderLayout.NORTH);
		
		JPanel panel_6 = new JPanel();
		panelLibellé.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Nombre de Mois prévus :");
		panel_6.add(lblNewLabel_6, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		panelLibellé.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("Loyer du Locataire : ");
		panel_7.add(lblNewLabel_7, BorderLayout.NORTH);
		
		JPanel panel_8 = new JPanel();
		panelLibellé.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_8 = new JLabel("Provisions sur charges : ");
		panel_8.add(lblNewLabel_8, BorderLayout.NORTH);
		
		JPanel panel_9 = new JPanel();
		panelLibellé.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_9 = new JLabel("Caution : ");
		panel_9.add(lblNewLabel_9, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		panelLibellé.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_10 = new JLabel("Date dernière régularisation : ");
		panel_10.add(lblNewLabel_10, BorderLayout.NORTH);
		
		JPanel panel_11 = new JPanel();
		panelLibellé.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_11 = new JLabel("Montant réel payé : ");
		panel_11.add(lblNewLabel_11, BorderLayout.NORTH);
		
		JPanel panelChamps = new JPanel();
		contentPane.add(panelChamps, BorderLayout.CENTER);
		panelChamps.setLayout(new GridLayout(13, 1, 0, 0));
		
		JPanel panel_12 = new JPanel();
		panelChamps.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JComboBox comboBoxLocataire = new JComboBox();
		panel_12.add(comboBoxLocataire, BorderLayout.NORTH);
		
		JPanel panel_13 = new JPanel();
		panelChamps.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JComboBox comboBoxEtatsDesLieux = new JComboBox();
		comboBoxEtatsDesLieux.setModel(new DefaultComboBoxModel(new String[] {"Tres mauvais", "Mauvais", "Moyen", "Bon", "Tres Bon"}));
		panel_13.add(comboBoxEtatsDesLieux, BorderLayout.NORTH);
		
		JPanel panel_14 = new JPanel();
		panelChamps.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JCheckBox chckbxColocation = new JCheckBox("");
		panel_14.add(chckbxColocation, BorderLayout.WEST);
		
		JPanel panel_15 = new JPanel();
		panelChamps.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JCheckBox chckbxLoyerPaye = new JCheckBox("");
		panel_15.add(chckbxLoyerPaye, BorderLayout.WEST);
		
		JPanel panel_16 = new JPanel();
		panelChamps.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		textFieldBail = new JTextField();
		panel_16.add(textFieldBail, BorderLayout.NORTH);
		textFieldBail.setColumns(10);
		
		JPanel panel_17 = new JPanel();
		panelChamps.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		textFieldDateDebutLocation = new JTextField();
		panel_17.add(textFieldDateDebutLocation, BorderLayout.NORTH);
		textFieldDateDebutLocation.setColumns(10);
		
		JPanel panel_18 = new JPanel();
		panelChamps.add(panel_18);
		panel_18.setLayout(new BorderLayout(0, 0));
		
		textFieldNombreMoisPrevus = new JTextField();
		panel_18.add(textFieldNombreMoisPrevus, BorderLayout.NORTH);
		textFieldNombreMoisPrevus.setColumns(10);
		
		JPanel panel_19 = new JPanel();
		panelChamps.add(panel_19);
		panel_19.setLayout(new BorderLayout(0, 0));
		
		textFieldLoyerLocataire = new JTextField();
		textFieldLoyerLocataire.setColumns(10);
		panel_19.add(textFieldLoyerLocataire, BorderLayout.NORTH);
		
		JPanel panel_20 = new JPanel();
		panelChamps.add(panel_20);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panelChamps.add(panel_21);
		panel_21.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_22 = new JPanel();
		panelChamps.add(panel_22);
		panel_22.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_23 = new JPanel();
		panelChamps.add(panel_23);
		panel_23.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_24 = new JPanel();
		panelChamps.add(panel_24);
		panel_24.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBoutons = new JPanel();
		contentPane.add(panelBoutons, BorderLayout.SOUTH);
	}

}
