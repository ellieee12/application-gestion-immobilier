package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.jdesktop.swingx.prompt.BuddySupport;

import controleur.ControleurAjouterLocation;
import modele.DAOException;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class VueAjouterLocation extends JFramePlus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField textFieldDateDebutLocation;
	private JFormattedTextField textFieldNombreMoisPrevus;
	private JFormattedTextField textFieldLoyerLocataire;
	private JComboBox<String> comboBoxLocataire;
	private JComboBox<String> comboBoxBiens;
	private JCheckBox chckbxColocation;
	private JFormattedTextField formattedProvisionCharges;
	private JFormattedTextField formattedCaution;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VueAjouterLocation frame = new VueAjouterLocation();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public boolean isComplet() {
		return !this.getSelectedBien().isEmpty() && this.getDateDebutLocation() != null &&
				 this.getNbMoisPrevus() != null && this.getLoyer() != null && this.getProvisionsCharges() != null &&
				 this.getCaution() != null;
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public VueAjouterLocation(VueMesLocations vueMesLocations) throws SQLException, DAOException {
		super(vueMesLocations);
		ControleurAjouterLocation controleur = new ControleurAjouterLocation(this, vueMesLocations);
		//NumberFormatter currencyFormatter = generateCurrencyFormatter();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		NumberFormatter formatter = createNumberformatter();	
		
		JLabel LabelAjouterLocation = new JLabel("Ajouter une Location");
		LabelAjouterLocation.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(LabelAjouterLocation, BorderLayout.NORTH);
		
		JPanel panelLibellé = new JPanel();
		contentPane.add(panelLibellé, BorderLayout.WEST);
		panelLibellé.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_L_Locataire = new JPanel();
		panelLibellé.add(panel_L_Locataire);
		panel_L_Locataire.setLayout(new BorderLayout(5, 0));
		
		JLabel lblNewLabel = new JLabel("Locataire : ");
		panel_L_Locataire.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_l_bien = new JPanel();
		panelLibellé.add(panel_l_bien);
		panel_l_bien.setLayout(new BorderLayout(0, 0));
		
		JLabel lbl_Bien = new JLabel("Bien :");
		panel_l_bien.add(lbl_Bien, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panelLibellé.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Colocation : ");
		panel_2.add(lblNewLabel_2, BorderLayout.NORTH);
		
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
		
		JLabel lblNewLabel_8 = new JLabel("Provision sur charges : ");
		panel_8.add(lblNewLabel_8, BorderLayout.NORTH);
		
		JPanel panel_9 = new JPanel();
		panelLibellé.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_9 = new JLabel("Caution : ");
		panel_9.add(lblNewLabel_9, BorderLayout.NORTH);
		
		JPanel panelChamps = new JPanel();
		contentPane.add(panelChamps, BorderLayout.CENTER);
		panelChamps.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_c_Locataire = new JPanel();
		panelChamps.add(panel_c_Locataire);
		panel_c_Locataire.setLayout(new BorderLayout(0, 0));
		
		initialiserComboBoxLocataires(controleur);
		panel_c_Locataire.add(comboBoxLocataire, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panelChamps.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		initialiserComboBoxBiens(controleur);
		
		
		
		
		
		
//		String type = controleur.getNameImmeubles().get(comboBox_Immeuble.getSelectedItem());
//		if (type.equals("M")) {
//			initialiserComboBoxMaison();
//		} else {
//			initialiserComboBoxBatiment();
//		}
		panel.add(comboBoxBiens, BorderLayout.NORTH);
		
		JPanel panel_c_colocation = new JPanel();
		panelChamps.add(panel_c_colocation);
		panel_c_colocation.setLayout(new BorderLayout(0, 0));
		
		chckbxColocation = new JCheckBox("");
		panel_c_colocation.add(chckbxColocation, BorderLayout.NORTH);
		
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
		
		textFieldNombreMoisPrevus = new JFormattedTextField(formatter);
		panel_c_nb_mois.add(textFieldNombreMoisPrevus, BorderLayout.NORTH);
		textFieldNombreMoisPrevus.setColumns(10);
		
		JPanel panel_c_loyer = new JPanel();
		panelChamps.add(panel_c_loyer);
		panel_c_loyer.setLayout(new BorderLayout(0, 0));
		
		textFieldLoyerLocataire = new JFormattedTextField(formatter);
		textFieldLoyerLocataire.setValue(0.0);
		BuddySupport.addRight(new JLabel("€"), textFieldLoyerLocataire);
		textFieldLoyerLocataire.setColumns(10);
		panel_c_loyer.add(textFieldLoyerLocataire, BorderLayout.NORTH);
		
		JPanel panel_c_provisions = new JPanel();
		panelChamps.add(panel_c_provisions);
		panel_c_provisions.setLayout(new BorderLayout(0, 0));
		
		formattedProvisionCharges = new JFormattedTextField(formatter);
		formattedProvisionCharges.setValue(0.0);
		BuddySupport.addRight(new JLabel("€"), formattedProvisionCharges);
		panel_c_provisions.add(formattedProvisionCharges, BorderLayout.NORTH);
		
		JPanel panel__c_caution = new JPanel();
		panelChamps.add(panel__c_caution);
		panel__c_caution.setLayout(new BorderLayout(0, 0));

		formattedCaution = new JFormattedTextField(formatter);
		formattedCaution.setValue(0.0);
		BuddySupport.addRight(new JLabel("€"), formattedCaution);
		panel__c_caution.add(formattedCaution, BorderLayout.NORTH);
		
		JPanel PanelBoutons = new JPanel();
		contentPane.add(PanelBoutons, BorderLayout.SOUTH);
		
		JButton ButtonAnnuler = new JButton("Annuler");
		PanelBoutons.add(ButtonAnnuler);
		
		JButton ButtonValider = new JButton("Valider");
		PanelBoutons.add(ButtonValider);
		
		ButtonAnnuler.addActionListener(controleur);
		ButtonValider.addActionListener(controleur);
		
		this.setSizeMulti();
	}

	private NumberFormatter createNumberformatter() {
		NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        format.setGroupingUsed(false);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
		return formatter;
	}

	private void initialiserComboBoxBiens(ControleurAjouterLocation controleur) {
		comboBoxBiens = new JComboBox<String>();
		for (String s : controleur.getBiens()) {
			comboBoxBiens.addItem(s);
		}
	}

	private void initialiserComboBoxLocataires(ControleurAjouterLocation controleur) {
		comboBoxLocataire = new JComboBox<String>();
		for (String s : controleur.getLocataires()) {
			comboBoxLocataire.addItem(s);
		}
	}
	
	public String getSelectedBien() {
		return this.comboBoxBiens.getSelectedItem().toString();
	}
	
	public String getSelectedLocataire() {
		return this.comboBoxLocataire.getSelectedItem().toString();
	}
	
	public String isColocation() {
		if (this.chckbxColocation.isSelected()) {
			return "Oui";
		}
		return "Non";
	}

	public Date getDateDebutLocation() {
		try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        sdf.setLenient(false);
	        java.util.Date parsedDate = sdf.parse(this.textFieldDateDebutLocation.getText());
	        return new Date(parsedDate.getTime());
	    } catch (Exception e) {
	        return null; 
	    }
	}
	
	public Integer getNbMoisPrevus() {
		if(textFieldNombreMoisPrevus.getText().equals("")) {
			return null;
		}
		return Integer.valueOf(this.textFieldNombreMoisPrevus.getText());

	}
	
	public Float getLoyer() {
		System.out.println(textFieldNombreMoisPrevus.getText());
		if(textFieldLoyerLocataire.getText().equals("0")) {
			return null;
		}
		return Float.valueOf(this.textFieldLoyerLocataire.getText());
	}
	
	public Float getProvisionsCharges() {
		if(formattedProvisionCharges.getText().equals("0")) {
			return null;
		}
		return Float.valueOf(this.formattedProvisionCharges.getText());
	}
	
	public Float getCaution() {
		if(formattedCaution.getText().equals("0")) {
			return null;
		}
		return Float.valueOf(this.formattedCaution.getText());
	}	
}
