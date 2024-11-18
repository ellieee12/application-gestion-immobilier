package sae3a01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControleurAjouterBien;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;

public class VueAjouterBien extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIdBien;
	private JTextField textFieldDateAcquisition;
	private JTextField textFieldNumeroEtage;
	private JTextField textFieldNombreDePieces;
	private JTextField textFieldSurfaceHabitable;
	private JComboBox<String> comboBox;

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
		
		JPanel panel_9 = new JPanel();
		PanelLibellé.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Type Bien");
		panel_9.add(lblNewLabel_6, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		PanelLibellé.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Identifiant du Bien");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_5.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		PanelLibellé.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Date Acquisition");
		panel_4.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		PanelLibellé.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Numéro Etage");
		panel_3.add(lblNewLabel_1, BorderLayout.NORTH);
		
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
		PanelChamps.setLayout(new GridLayout(0, 1, 0, 3));
		
		JPanel panel = new JPanel();
		PanelChamps.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Logement", "Garage"}));
		panel.add(comboBox, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		PanelChamps.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textFieldIdBien = new JTextField();
		panel_1.add(textFieldIdBien, BorderLayout.NORTH);
		textFieldIdBien.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		PanelChamps.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		textFieldDateAcquisition = new JTextField();
		panel_2.add(textFieldDateAcquisition, BorderLayout.NORTH);
		textFieldDateAcquisition.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		PanelChamps.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		textFieldNumeroEtage = new JTextField();
		panel_7.add(textFieldNumeroEtage, BorderLayout.NORTH);
		textFieldNumeroEtage.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		PanelChamps.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		textFieldNombreDePieces = new JTextField();
		panel_8.add(textFieldNombreDePieces, BorderLayout.NORTH);
		textFieldNombreDePieces.setColumns(10);
		
		JPanel panel_11 = new JPanel();
		PanelChamps.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		textFieldSurfaceHabitable = new JTextField();
		panel_11.add(textFieldSurfaceHabitable, BorderLayout.NORTH);
		textFieldSurfaceHabitable.setColumns(10);
		
		JPanel PanelBoutons = new JPanel();
		contentPane.add(PanelBoutons, BorderLayout.SOUTH);
		
		JButton ButtonAnnuler = new JButton("Annuler");
		PanelBoutons.add(ButtonAnnuler);
		
		JButton ButtonValider = new JButton("Valider");
		PanelBoutons.add(ButtonValider);
		
		//mise en place du controleur
		ControleurAjouterBien controleur = ControleurAjouterBien.getControleurAjouterBien();
		controleur.initialiserControleur(this);
		comboBox.addActionListener(controleur);
		ButtonAnnuler.addActionListener(controleur);
		ButtonValider.addActionListener(controleur);
	}
	
	//Methode pour désactiver les champs
	public void desactiverChamps() {
		textFieldNombreDePieces.setEnabled(false);
		textFieldNumeroEtage.setEnabled(false);
		textFieldSurfaceHabitable.setEnabled(false);
	}

	//Methode pour activer les champs
	public void activerChamps() {
		textFieldNombreDePieces.setEnabled(true);
		textFieldNumeroEtage.setEnabled(true);
		textFieldSurfaceHabitable.setEnabled(true);
	}
	
	public String getChampsIdBien () {
		return String.valueOf(textFieldIdBien.getText());
	}
	
	public Date getChampsDateAcquisition () {
		return Date.valueOf(textFieldDateAcquisition.getText());
	}
	
	public int getChampsNumeroEtage () {
		return Integer.valueOf(textFieldNumeroEtage.getText());
	}
	
	public int getChampsNombreDePiece () {
		return Integer.valueOf(textFieldIdBien.getText());
	}
	
	public float getChampsSurfaceHabitable () {
		return Float.valueOf(textFieldSurfaceHabitable.getText());
	}
	
	public String getComboBoxTypeBien () {
		return (String) comboBox.getSelectedItem();
	}
}
