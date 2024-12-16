package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.JXFormattedTextField;
import org.jdesktop.swingx.painter.GlossPainter;
import org.jdesktop.swingx.prompt.BuddySupport;

import controleur.ControleurAjouterFacture;
import controleur.ControleurAjouterLocation;
import modeleDAO.DAOException;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class VueAjouterFacture extends JFramePlus {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField textFieldDateEmission;
	private JFormattedTextField textFieldDatePaiement;
	private JTextField textFieldNumero;
	private JTextField textFieldDesignation;
	private JFormattedTextField textFieldMontant;
	private JTextField textFieldNumeroDevis;
	private JFormattedTextField textFieldMontantReelPaye;
	private JFormattedTextField textFieldImputableLocataire;
	private JComboBox<String> comboBoxBiens;
	private JButton annuler;
	private JButton valider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueAjouterFacture frame = new VueAjouterFacture(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public VueAjouterFacture(VueListFactures VueListFactures) throws DAOException, SQLException {
		ControleurAjouterFacture controleur = new ControleurAjouterFacture(this, VueListFactures);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		JLabel lblNewLabel = new JLabel("Ajouter Facture");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 3));
		
		JPanel panel_12 = new JPanel();
		panel.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Id bien");
		panel_12.add(lblNewLabel_6, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Date emission");
		panel_3.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Date paiement");
		panel_4.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Numero");
		panel_5.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Designation");
		panel_6.add(lblNewLabel_4, BorderLayout.NORTH);
		
		JPanel panel_11 = new JPanel();
		panel.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Montant");
		panel_11.add(lblNewLabel_5, BorderLayout.NORTH);
		
		JPanel panel_13 = new JPanel();
		panel.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("Numero devis");
		panel_13.add(lblNewLabel_7, BorderLayout.NORTH);
		
		JPanel panel_14 = new JPanel();
		panel.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_8 = new JLabel("Montant réel payé");
		panel_14.add(lblNewLabel_8, BorderLayout.NORTH);
		
		JPanel panel_15 = new JPanel();
		panel.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_9 = new JLabel("Imputable locataire");
		panel_15.add(lblNewLabel_9, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 3));
		
		JPanel panel_17 = new JPanel();
		panel_1.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		comboBoxBiens = new JComboBox();
		this.initialiserComboBoxBiens(controleur);
		panel_17.add(comboBoxBiens, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		textFieldDateEmission = new JFormattedTextField();
		try {
			textFieldDateEmission = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		panel_7.add(textFieldDateEmission, BorderLayout.NORTH);
		textFieldDateEmission.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		textFieldDatePaiement = new JFormattedTextField();
		try {
			textFieldDatePaiement = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		panel_8.add(textFieldDatePaiement, BorderLayout.NORTH);
		textFieldDatePaiement.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		textFieldNumero = new JTextField();
		panel_9.add(textFieldNumero, BorderLayout.NORTH);
		textFieldNumero.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		textFieldDesignation = new JTextField();
		panel_10.add(textFieldDesignation, BorderLayout.NORTH);
		textFieldDesignation.setColumns(10);
		
		JPanel panel_16 = new JPanel();
		panel_1.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		textFieldMontant = new JFormattedTextField();
		textFieldMontant.setValue(0.0);
		BuddySupport.addRight(new JLabel("€"), textFieldMontant);
		panel_16.add(textFieldMontant, BorderLayout.NORTH);
		textFieldMontant.setColumns(10);
		
		JPanel panel_18 = new JPanel();
		panel_1.add(panel_18);
		panel_18.setLayout(new BorderLayout(0, 0));
		
		textFieldNumeroDevis = new JTextField();
		panel_18.add(textFieldNumeroDevis, BorderLayout.NORTH);
		textFieldNumeroDevis.setColumns(10);
		
		JPanel panel_19 = new JPanel();
		panel_1.add(panel_19);
		panel_19.setLayout(new BorderLayout(0, 0));
		
		textFieldMontantReelPaye = new JFormattedTextField();
		textFieldMontantReelPaye.setValue(0.0f);
		BuddySupport.addRight(new JLabel("€"), textFieldMontantReelPaye);
		panel_19.add(textFieldMontantReelPaye, BorderLayout.NORTH);
		textFieldMontantReelPaye.setColumns(10);
		
		JPanel panel_20 = new JPanel();
		panel_1.add(panel_20);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		textFieldImputableLocataire = new JFormattedTextField();
		textFieldImputableLocataire.setValue(0.0f);
		BuddySupport.addRight(new JLabel("€"), textFieldImputableLocataire);
		panel_20.add(textFieldImputableLocataire, BorderLayout.NORTH);
		textFieldImputableLocataire.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		annuler = new JButton("Annuler");
		panel_2.add(annuler);
		this.annuler.addActionListener(controleur);
		
		valider = new JButton("Valider");
		panel_2.add(valider);
		this.valider.addActionListener(controleur);
		
		this.setSizeMulti(5);
	}
	
	private void initialiserComboBoxBiens(ControleurAjouterFacture controleur) {
		comboBoxBiens = new JComboBox<String>();
		for (String s : controleur.getBiens()) {
			comboBoxBiens.addItem(s);
		}
	}
	
	public String getSelectedBien() {
		return comboBoxBiens.getSelectedItem().toString();
	}
	
	public Date getChampsDateEmission() {
		try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        sdf.setLenient(false);
	        java.util.Date parsedDate = sdf.parse(textFieldDateEmission.getText());
	        return new Date(parsedDate.getTime());
	    } catch (Exception e) {
	        return null; // Retourne null en cas d'erreur
	    }
	}
	
	public Date getChampsDateAcquisition() {
		try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        sdf.setLenient(false);
	        java.util.Date parsedDate = sdf.parse(textFieldDatePaiement.getText());
	        return new Date(parsedDate.getTime());
	    } catch (Exception e) {
	        return null; // Retourne null en cas d'erreur
	    }
	}
	
	public String getChampsNumero() {
		if(textFieldNumero.getText().equals("")) {
			return null;
		}
		return String.valueOf(textFieldNumero.getText());
	}
	
	public String getChampsDesignation() {
		if(textFieldDesignation.getText().equals("")) {
			return null;
		}
		return String.valueOf(textFieldDesignation.getText());
	}
	
	public Float getChampsMontant() {
		if(textFieldMontant.getText().equals("0")) {
			return null;
		}
		return Float.valueOf(textFieldMontant.getText());
	}
	
	public String getChampsNumeroDevis() {
		if(textFieldNumeroDevis.getText().equals("")) {
			return null;
		}
		return String.valueOf(textFieldNumeroDevis.getText());
	}
	
	public Float getChampsMontantReelPaye() {
		if(textFieldMontantReelPaye.getText().equals("0")) {
			return null;
		}
		return Float.valueOf(textFieldMontantReelPaye.getText());
	}
	
	public Float getChampsImputableLocataire() {
		if(textFieldImputableLocataire.getText().equals("0")) {
			return null;
		}
		return Float.valueOf(textFieldImputableLocataire.getText());
	}

}
