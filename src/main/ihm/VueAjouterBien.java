package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.jdesktop.swingx.prompt.BuddySupport;

import controleur.ControleurAjouterBien;
import modele.DAOException;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class VueAjouterBien extends JFramePlus {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIdBien;
	private JTextField textFieldNumeroEtage;
	private JTextField textFieldNombreDePieces;
	private JFormattedTextField textFieldSurfaceHabitable;
	private JFormattedTextField formattedTextField;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_Immeuble;
	private JTextField textFieldEntretien;
	private JFormattedTextField textFieldEau;
	private JFormattedTextField textFieldElectricite;
	private JLabel lblTypeBien;
	private JLabel lblIdentifiantBien;
	private JLabel lblEntretien;
	private JLabel lblDateAcquisition;
	private JLabel lblNumEtage;
	private JLabel lblNbPieces;
	private JLabel lblSurfaceHabitable;
	private JLabel lblIdImmeuble;
	private JLabel lblIndexEau;
	private JLabel lblIndexElec;

	/**
	 * Create the frame.
	 * @throws DAOException 
	 */
	public VueAjouterBien(VueMesBiens vueBiens) throws DAOException {
		//mise en place du controleur
		ControleurAjouterBien controleur = new ControleurAjouterBien(this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		NumberFormatter formatter = createNumberformatter();
		
		JLabel lblAjouterBien = new JLabel("Ajouter Bien");
		lblAjouterBien.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblAjouterBien, BorderLayout.NORTH);
		
		JPanel PanelLibellé = new JPanel();
		contentPane.add(PanelLibellé, BorderLayout.WEST);
		PanelLibellé.setLayout(new GridLayout(0, 1, 0, 3));
		
		JPanel panel_9 = new JPanel();
		PanelLibellé.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		lblTypeBien = new JLabel("<html><font size='3' color=black>Type Bien</font><font size='3'color=red>*</font></html>");
		panel_9.add(lblTypeBien, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		PanelLibellé.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		lblIdentifiantBien = new JLabel("<html><font size='3' color=black>Identifiant du Bien</font><font size='3'color=red>*</font></html>");
		lblIdentifiantBien.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_5.add(lblIdentifiantBien, BorderLayout.NORTH);
		
		JPanel panel_12_1 = new JPanel();
		PanelLibellé.add(panel_12_1);
		panel_12_1.setLayout(new BorderLayout(0, 0));
		
		lblEntretien = new JLabel("<html><font size='3' color=black>Entretien Partie Commune</font><font size='3'color=red>*</font></html>");
		panel_12_1.add(lblEntretien, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		PanelLibellé.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		lblDateAcquisition = new JLabel("<html><font size='3' color=black>Date Acquisition</font><font size='3'color=red>*</font></html>");
		panel_4.add(lblDateAcquisition, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		PanelLibellé.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		lblNumEtage = new JLabel("<html><font size='3' color=black>Numéro Etage</font><font size='3'color=red>*</font></html>");
		panel_3.add(lblNumEtage, BorderLayout.NORTH);
		
		JPanel panel_6 = new JPanel();
		PanelLibellé.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		lblNbPieces = new JLabel("<html><font size='3' color=black>Nombre de pièces</font><font size='3'color=red>*</font></html>");
		panel_6.add(lblNbPieces, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		PanelLibellé.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		lblSurfaceHabitable = new JLabel("<html><font size='3' color=black>Surface habitable</font><font size='3'color=red>*</font></html>");
		panel_10.add(lblSurfaceHabitable, BorderLayout.NORTH);
		
		JPanel panel_12 = new JPanel();
		PanelLibellé.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		lblIdImmeuble = new JLabel("<html><font size='3' color=black>Immeuble</font><font size='3'color=red>*</font></html>");
		panel_12.add(lblIdImmeuble, BorderLayout.NORTH);
		
		JPanel panel_15 = new JPanel();
		PanelLibellé.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		lblIndexEau = new JLabel("<html><font size='3' color=black>Index Eau</font><font size='3'color=red>*</font></html>");
		panel_15.add(lblIndexEau, BorderLayout.NORTH);
		
		JPanel panel_14 = new JPanel();
		PanelLibellé.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		lblIndexElec = new JLabel("<html><font size='3' color=black>Index Électricité</font><font size='3'color=red>*</font></html>");
		panel_14.add(lblIndexElec, BorderLayout.NORTH);
		
		JPanel PanelChamps = new JPanel();
		contentPane.add(PanelChamps, BorderLayout.CENTER);
		PanelChamps.setLayout(new GridLayout(0, 1, 0, 3));
		
		JPanel panel = new JPanel();
		PanelChamps.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		PanelChamps.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));	
		
		textFieldIdBien = new JTextField();
		textFieldIdBien.setColumns(10);
		panel_2.add(textFieldIdBien, BorderLayout.NORTH);
		
		JPanel panel_13_1 = new JPanel();
		PanelChamps.add(panel_13_1);
		panel_13_1.setLayout(new BorderLayout(0, 0));
		
		textFieldEntretien = new JFormattedTextField(formatter);
		BuddySupport.addRight(new JLabel("€"), textFieldEntretien);
		textFieldEntretien.setColumns(10);
		panel_13_1.add(textFieldEntretien, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		PanelChamps.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		formattedTextField = new JFormattedTextField();
		try {
			formattedTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		panel_1.add(formattedTextField, BorderLayout.NORTH);
		
		
		JPanel panel_7 = new JPanel();
		PanelChamps.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		textFieldNumeroEtage = new JFormattedTextField(formatter);
		panel_7.add(textFieldNumeroEtage, BorderLayout.NORTH);
		textFieldNumeroEtage.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		PanelChamps.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		textFieldNombreDePieces = new JFormattedTextField(formatter);
		panel_8.add(textFieldNombreDePieces, BorderLayout.NORTH);
		textFieldNombreDePieces.setColumns(10);
		
		JPanel panel_11 = new JPanel();
		PanelChamps.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		textFieldSurfaceHabitable = new JFormattedTextField(formatter);
		textFieldSurfaceHabitable.setValue(0.0f);
		BuddySupport.addRight(new JLabel("m²"), textFieldSurfaceHabitable);
		panel_11.add(textFieldSurfaceHabitable, BorderLayout.NORTH);
		textFieldSurfaceHabitable.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		PanelChamps.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		//comboBox Immeuble
		comboBox_Immeuble = new JComboBox<String>();
		@SuppressWarnings("unused")
		String selected ="";
		for (Map.Entry<String, String> s : controleur.getNameImmeubles().entrySet()) {
			if (s.getKey().equals(vueBiens.getIdImmeuble())) {
				selected=vueBiens.getIdImmeuble();
			}
			comboBox_Immeuble.addItem(s.getKey());
		}
		comboBox_Immeuble.setSelectedItem(vueBiens.getIdImmeuble());
		
		
		panel_13.add(comboBox_Immeuble, BorderLayout.NORTH);
		
		comboBox = new JComboBox<>();
		
		String type = controleur.getNameImmeubles().get(comboBox_Immeuble.getSelectedItem());
		if (type.equals("M")) {
			initialiserComboBoxMaison();
		} else {
			initialiserComboBoxBatiment();
		}
		panel.add(comboBox, BorderLayout.NORTH);
		
		JPanel panel_16 = new JPanel();
		PanelChamps.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		textFieldEau = new JFormattedTextField(formatter);
		BuddySupport.addRight(new JLabel("m³"), textFieldEau);
		panel_16.add(textFieldEau, BorderLayout.NORTH);
		textFieldEau.setColumns(10);
		
		JPanel panel_17 = new JPanel();
		PanelChamps.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		textFieldElectricite = new JFormattedTextField(formatter);
		BuddySupport.addRight(new JLabel("kWh"), textFieldElectricite);
		panel_17.add(textFieldElectricite, BorderLayout.NORTH);
		textFieldElectricite.setColumns(10);
		
		JPanel PanelBoutons = new JPanel();
		contentPane.add(PanelBoutons, BorderLayout.SOUTH);
		
		JButton ButtonAnnuler = new JButton("Annuler");
		PanelBoutons.add(ButtonAnnuler);
		
		JButton ButtonValider = new JButton("Valider");
		PanelBoutons.add(ButtonValider);
		
		//action du controleur
		comboBox.addActionListener(controleur);
		comboBox_Immeuble.addActionListener(controleur);
		ButtonAnnuler.addActionListener(controleur);
		ButtonValider.addActionListener(controleur);
		
		this.setSizeMulti();
		this.setLogo();
	}

	/**
	 * Initialiser la liste de types de biens disponibles dans le cas d'un bâtiment
	 */
	public void initialiserComboBoxBatiment() {
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Logement", "Garage"}));
	}

	/**
	 * Initialiser la liste de types de biens disponibles dans le cas d'une maison
	 */
	public void initialiserComboBoxMaison() {
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Logement"}));
	}

	/**
	 * Retourner l'immeuble séléctionné
	 * @return String identifiant immeuble
	 */
	public String getSelectedImmeuble() {
		return comboBox_Immeuble.getSelectedItem().toString();
	}
	
	/**
	 * Desactiver les champs non concernés par un garage
	 */
	public void desactiverChamps() {
		textFieldNombreDePieces.setEnabled(false);
		textFieldNumeroEtage.setEnabled(false);
		textFieldSurfaceHabitable.setEnabled(false);
	}
	/**
	 * Desactivier la libelle obligatoire des champs non concernés par un garage
	 */
	public void champsNonObligatoiresGarage() {
		this.lblNumEtage.setText("<html><font size='3' color=black>Numéro Etage</font></html>");
		this.lblNbPieces.setText("<html><font size='3' color=black>Nombre de pièces</font></html>");
		this.lblSurfaceHabitable.setText("<html><font size='3' color=black>Surface habitable</font></html>");
	}
	/**
	 * Desactivier la libelle obligatoire des champs non concernés par un garage
	 */
	public void champsObligatoireLogement() {
		this.lblSurfaceHabitable.setText("<html><font size='3' color=black>Surface habitable</font><font size='3'color=red>*</font></html>");
		this.lblNumEtage.setText("<html><font size='3' color=black>Numéro Etage</font><font size='3'color=red>*</font></html>");
		this.lblNbPieces.setText("<html><font size='3' color=black>Nombre de pièces</font><font size='3'color=red>*</font></html>");
	}

	/**
	 * Activer  la libelle obligatoire des champs concernés par un logement
	 */
	public void activerChamps() {
		textFieldNombreDePieces.setEnabled(true);
		textFieldNumeroEtage.setEnabled(true);
		textFieldSurfaceHabitable.setEnabled(true);
	}
	
	/**
	 * Retourner l'identifiant du bien saisi
	 * @return String
	 */
	public String getChampsIdBien () {
		return String.valueOf(textFieldIdBien.getText());
	}
	
	/**
	 * Retourner la date d'acquisition saisie
	 * @return Date
	 * @throws IllegalArgumentException
	 */
	public Date getChampsDateAcquisition () throws IllegalArgumentException{
		String date = formattedTextField.getText();
		if (date.equals("  /  /    ")) {
        	throw new IllegalArgumentException("Date vide");
		}
		try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        sdf.setLenient(false);
	        
	        java.util.Date parsedDate = sdf.parse(date);
	        System.out.println(date);
	        return new Date(parsedDate.getTime());
	    } catch (Exception e) {
	        return null; // Retourne null en cas d'erreur
	    }
	}
	
	/**
	 * Retourner le numéro d'étage saisi
	 * @return Integer
	 */
	public Integer getChampsNumeroEtage () {
		if(textFieldNumeroEtage.getText().equals("")) {
			return null;
		}
		return Integer.valueOf(textFieldNumeroEtage.getText());
	}
	
	/**
	 * Retourner le nombre de pièces saisi
	 * @return Integer
	 */
	public Integer getChampsNombreDePiece () {
		if(textFieldNombreDePieces.getText().equals("")) {
			return null;
		}
		return Integer.valueOf(textFieldNombreDePieces.getText());
	}
	/**
	 * Retourner la surface habitable saisie
	 * @return Float
	 */
	public Float getChampsSurfaceHabitable () {
		if(textFieldSurfaceHabitable.getText().equals("")) {
			return null;
		}
		return Float.valueOf(textFieldSurfaceHabitable.getText());
	}
	/**
	 * Retourner le type du bien saisi
	 * @return String
	 */
	public String getComboBoxTypeBien () {
		if (((String) comboBox.getSelectedItem()).equals("Logement")) {
			return "L";
		}else {
			return "G";
		}

	}
	/**
	 * Retourner l'entretien partie commune saisi
	 * @return Float
	 */
	public Float getEntretienPartieCommune() {
		if (this.textFieldEntretien.getText().equals("")) {
			return null;
		}
		return Float.valueOf(this.textFieldEntretien.getText());
	}
	/**
	 * Retourner l'index d'eau saisi
	 * @return Integer
	 */
	public Integer getChampsEau() {
		if(textFieldEau.getText().equals("")) {
			return null;
		}
		return Integer.valueOf(textFieldEau.getText());
	}
	/**
	 * Retourner l'index d'éléctricité saisi
	 * @return Integer
	 */
	public Integer getChampsElectricite() {
		if(textFieldElectricite.getText().equals("")) {
			return null;
		}
		return Integer.valueOf(textFieldElectricite.getText());
	}
	/**
	 * Créer et formatter un champs
	 * @return NumberFormatter
	 */
	private NumberFormatter createNumberformatter() {
		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
		return formatter;
	}
	/**
	 * Retourner un combo box
	 * @return JComboBox
	 */
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	/**
	 * Retourner un combo box immeuble
	 * @return JComboBox
	 */
	public JComboBox<String> getComboBox_Immeuble() {
		return comboBox_Immeuble;
	}
}


