package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControleurRegularisation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class VueRegularisation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField champEau;
	private JTextField champElec;
	private JTextField champOrdure;
	private JLabel lblEau;
	private JLabel lblElec;
	private JLabel lblEntretien;
	private JLabel lblOrdures;
	private JLabel lblTotal;
	private JLabel lblProvision;
	private JLabel lblReste;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueRegularisation frame = new VueRegularisation("1111",5F);
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
	public VueRegularisation(String id_bien, float provision) {
		
		
		ControleurRegularisation controleur = new ControleurRegularisation(this,id_bien, provision);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		champEau = new JTextField();
		champEau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelChamps.add(champEau);
		champEau.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nouvel index électricité");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelChamps.add(lblNewLabel);
		
		champElec = new JTextField();
		champElec.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelChamps.add(champElec);
		champElec.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Montant ordures ménagères");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelChamps.add(lblNewLabel_1);
		
		champOrdure = new JTextField();
		champOrdure.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelChamps.add(champOrdure);
		champOrdure.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panelChamps.add(panel_1);
		
		JPanel panel = new JPanel();
		panelChamps.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(btnNewButton, BorderLayout.EAST);
		btnNewButton.addActionListener(controleur);
		
		JPanel panelTotaux = new JPanel();
		contentPane.add(panelTotaux, BorderLayout.CENTER);
		panelTotaux.setLayout(new GridLayout(0, 2, 0, 10));
		
		JLabel lblNewLabel_2 = new JLabel("Consommation d'eau : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_2);
		
		this.lblEau = new JLabel("€");
		lblEau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblEau);
		
		JLabel lblNewLabel_3 = new JLabel("Consommation d'électricité : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_3);
		
		this.lblElec = new JLabel("€");
		lblElec.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblElec);
		
		JLabel lblNewLabel_4 = new JLabel("Montant entretien parties communes :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_4);
		
		this.lblEntretien = new JLabel("€");
		lblEntretien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblEntretien);
		
		JLabel lblNewLabel_5 = new JLabel("Montant ordures ménagères :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_5);
		
		this.lblOrdures = new JLabel("€");
		lblOrdures.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblOrdures);
		
		JLabel lblNewLabel_6 = new JLabel("Total : ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_6);
		
		this.lblTotal = new JLabel("€");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblTotal);
		
		JLabel lblNewLabel_7 = new JLabel("Provision sur charges");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_7);
		
		this.lblProvision = new JLabel("€");
		lblProvision.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblProvision);
		
		JLabel lblNewLabel_8 = new JLabel("Reste à charge :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblNewLabel_8);
		
		this.lblReste = new JLabel("€");
		lblReste.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTotaux.add(lblReste);
		
		JPanel panelNouvelleProvision = new JPanel();
		contentPane.add(panelNouvelleProvision, BorderLayout.SOUTH);
		panelNouvelleProvision.setLayout(new GridLayout(2, 2, 10, 10));
		
		JLabel lblNewLabel_9 = new JLabel("Nouvelle provision sur charges :");
		lblNewLabel_9.setToolTipText("laisser vide si inchangé");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelNouvelleProvision.add(lblNewLabel_9);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelNouvelleProvision.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panelNouvelleProvision.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelNouvelleProvision.add(panel_3);
		
		JButton btnNewButton_1 = new JButton("Confirmer");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(btnNewButton_1);
	}

	public String getChampEau() {
		return champEau.getText();
	}
	
	public String getChampElec() {
		return champElec.getText();
	}
	
	public String getChampOrdure() {
		return champOrdure.getText();
	}
	
	public void afficherMontantEau(float montant) {
		this.lblEau.setText(String.valueOf(montant)+" €");
	}
	
	public void afficherMontantElec(float montant) {
		this.lblElec.setText(String.valueOf(montant)+" €");
	}
	
	public void afficherEntretien(float montant) {
		this.lblEntretien.setText(String.valueOf(montant)+" €");
	}
	
	public void afficherMontantOrdure(float montant) {
		this.lblOrdures.setText(String.valueOf(montant)+" €");
	}
	
	public void afficherTotal(float montant) {
		this.lblTotal.setText(String.valueOf(montant)+" €");
	}
	
	public void afficherProvision(float montant) {
		this.lblProvision.setText(String.valueOf(montant)+" €");
	}
	
	public void afficherReste(float montant) {
		this.lblReste.setText(String.valueOf(montant)+" €");
	}
}
