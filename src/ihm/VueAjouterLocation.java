package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class VueAjouterLocation extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBail;
	private JFormattedTextField textFieldDateDebutLocation;
	private JFormattedTextField textFieldNombreMoisPrevus;
	private JFormattedTextField textFieldLoyerLocataire;

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
		NumberFormatter currencyFormatter = generateCurrencyFormatter();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		panelLibellé.setLayout(new GridLayout(12, 1, 0, 0));
		
		JPanel panel_L_Locataire = new JPanel();
		panelLibellé.add(panel_L_Locataire);
		panel_L_Locataire.setLayout(new BorderLayout(5, 0));
		
		JLabel lblNewLabel = new JLabel("Locataire : ");
		panel_L_Locataire.add(lblNewLabel, BorderLayout.NORTH);
		
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
		panelChamps.setLayout(new GridLayout(12, 1, 0, 0));
		
		JPanel panel_c_Locataire = new JPanel();
		panelChamps.add(panel_c_Locataire);
		panel_c_Locataire.setLayout(new BorderLayout(0, 0));
		
		JComboBox comboBoxLocataire = new JComboBox();
		panel_c_Locataire.add(comboBoxLocataire, BorderLayout.NORTH);
		
		JPanel panel_c_edl = new JPanel();
		panelChamps.add(panel_c_edl);
		panel_c_edl.setLayout(new BorderLayout(0, 0));
		JComboBox comboBoxEtatsDesLieux = new JComboBox();
		comboBoxEtatsDesLieux.setModel(new DefaultComboBoxModel(new String[] {"Tres mauvais", "Mauvais", "Moyen", "Bon", "Tres Bon"}));
		panel_c_edl.add(comboBoxEtatsDesLieux, BorderLayout.NORTH);
		
		JPanel panel_c_colocation = new JPanel();
		panelChamps.add(panel_c_colocation);
		panel_c_colocation.setLayout(new BorderLayout(0, 0));
		
		JCheckBox chckbxColocation = new JCheckBox("");
		panel_c_colocation.add(chckbxColocation, BorderLayout.WEST);
		
		JPanel panel_c_loyer_paye = new JPanel();
		panelChamps.add(panel_c_loyer_paye);
		panel_c_loyer_paye.setLayout(new BorderLayout(0, 0));
		
		JCheckBox chckbxLoyerPaye = new JCheckBox("");
		panel_c_loyer_paye.add(chckbxLoyerPaye, BorderLayout.WEST);
		
		JPanel panel_c_bail = new JPanel();
		panelChamps.add(panel_c_bail);
		panel_c_bail.setLayout(new BorderLayout(0, 0));
		
		textFieldBail = new JTextField();
		panel_c_bail.add(textFieldBail, BorderLayout.NORTH);
		textFieldBail.setColumns(10);
		
		JPanel panel_c_date_debut_location = new JPanel();
		panelChamps.add(panel_c_date_debut_location);
		panel_c_date_debut_location.setLayout(new BorderLayout(0, 0));
		
		textFieldDateDebutLocation = new JFormattedTextField();
		try {
			textFieldDateDebutLocation = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		panel_c_date_debut_location.add(textFieldDateDebutLocation, BorderLayout.NORTH);
		textFieldDateDebutLocation.setColumns(10);
		
		JPanel panel_c_nb_mois = new JPanel();
		panelChamps.add(panel_c_nb_mois);
		panel_c_nb_mois.setLayout(new BorderLayout(0, 0));
		
		textFieldNombreMoisPrevus = new JFormattedTextField();
		panel_c_nb_mois.add(textFieldNombreMoisPrevus, BorderLayout.NORTH);
		textFieldNombreMoisPrevus.setColumns(10);
		
		JPanel panel_c_loyer = new JPanel();
		panelChamps.add(panel_c_loyer);
		panel_c_loyer.setLayout(new BorderLayout(0, 0));
		
		textFieldLoyerLocataire = new JFormattedTextField(currencyFormatter);
		textFieldLoyerLocataire.setValue(0.0);
		textFieldLoyerLocataire.setColumns(10);
		
		panel_c_loyer.add(textFieldLoyerLocataire, BorderLayout.NORTH);
		
		JPanel panel_c_provisions = new JPanel();
		panelChamps.add(panel_c_provisions);
		panel_c_provisions.setLayout(new BorderLayout(0, 0));
		
		JFormattedTextField formattedProvisionCharges = new JFormattedTextField(currencyFormatter);
		formattedProvisionCharges.setValue(0.0);
		panel_c_provisions.add(formattedProvisionCharges, BorderLayout.NORTH);
		
		JPanel panel__c_caution = new JPanel();
		panelChamps.add(panel__c_caution);
		panel__c_caution.setLayout(new BorderLayout(0, 0));
		
		JFormattedTextField formattedCaution = new JFormattedTextField(currencyFormatter);
		formattedCaution.setValue(0.0);
		panel__c_caution.add(formattedCaution, BorderLayout.NORTH);
		
		JPanel panel_c_date_derniere_reg = new JPanel();
		panelChamps.add(panel_c_date_derniere_reg);
		panel_c_date_derniere_reg.setLayout(new BorderLayout(0, 0));
		
		JFormattedTextField formattedDateDerniereRegularisation = new JFormattedTextField();
		try {
			formattedDateDerniereRegularisation = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		panel_c_date_derniere_reg.add(formattedDateDerniereRegularisation, BorderLayout.NORTH);
		
		JPanel panel_c_montant_reel = new JPanel();
		panelChamps.add(panel_c_montant_reel);
		panel_c_montant_reel.setLayout(new BorderLayout(0, 0));
		
		JFormattedTextField formattedMontantReelPaye = new JFormattedTextField();
		panel_c_montant_reel.add(formattedMontantReelPaye, BorderLayout.NORTH);
		
		JPanel panelBoutons = new JPanel();
		contentPane.add(panelBoutons, BorderLayout.SOUTH);
	}

	private NumberFormatter generateCurrencyFormatter() {
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00€");
		decimalFormat.setDecimalSeparatorAlwaysShown(true);
		NumberFormatter numberFormatter= new NumberFormatter(decimalFormat);
		numberFormatter.setValueClass(Double.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0.0);
		return numberFormatter;
	}

}
