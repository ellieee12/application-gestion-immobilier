package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.prompt.BuddySupport;

import controleur.ControleurMesLocations;
import controleur.ControleurRegularisation;

public class VueRegularisation extends JFramePlus {

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
	private JTextField champNouvelleProvision;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Date date = Date.valueOf("2021-12-17");
					VueRegularisation frame = new VueRegularisation(new ControleurMesLocations(null), "11",date);
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
	public VueRegularisation(ControleurMesLocations controleurMesLocations, String id_bien, Date date_debut) {
		
		
		ControleurRegularisation controleur = new ControleurRegularisation(this,controleurMesLocations,id_bien,date_debut);
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
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelHaut.add(lblTitre, BorderLayout.NORTH);
		
		JPanel panelChamps = new JPanel();
		panelHaut.add(panelChamps, BorderLayout.SOUTH);
		panelChamps.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblIndexEau = new JLabel("Nouvel index eau");
		panelChamps.add(lblIndexEau);
		
		champEau = new JTextField();
		BuddySupport.addRight(new JLabel("m³"), champEau);
		panelChamps.add(champEau);
		champEau.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nouvel index électricité");
		panelChamps.add(lblNewLabel);
		
		champElec = new JTextField();
		BuddySupport.addRight(new JLabel("Kw/h"), champElec);
		panelChamps.add(champElec);
		champElec.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Montant ordures ménagères");
		panelChamps.add(lblNewLabel_1);
		
		champOrdure = new JTextField();
		panelChamps.add(champOrdure);
		champOrdure.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panelChamps.add(panel_1);
		
		JPanel panel = new JPanel();
		panelChamps.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Valider");
		panel.add(btnNewButton, BorderLayout.EAST);
		btnNewButton.addActionListener(controleur);
		
		JPanel panelTotaux = new JPanel();
		contentPane.add(panelTotaux, BorderLayout.CENTER);
		panelTotaux.setLayout(new GridLayout(0, 2, 0, 10));
		
		JLabel lblNewLabel_2 = new JLabel("Consommation d'eau : ");
		panelTotaux.add(lblNewLabel_2);
		
		this.lblEau = new JLabel("€");
		panelTotaux.add(lblEau);
		
		JLabel lblNewLabel_3 = new JLabel("Consommation d'électricité : ");
		panelTotaux.add(lblNewLabel_3);
		
		this.lblElec = new JLabel("€");
		panelTotaux.add(lblElec);
		
		JLabel lblNewLabel_4 = new JLabel("Montant entretien parties communes :");
		panelTotaux.add(lblNewLabel_4);
		
		this.lblEntretien = new JLabel("€");
		panelTotaux.add(lblEntretien);
		
		JLabel lblNewLabel_5 = new JLabel("Montant ordures ménagères :");
		panelTotaux.add(lblNewLabel_5);
		
		this.lblOrdures = new JLabel("€");
		panelTotaux.add(lblOrdures);
		
		JLabel lblNewLabel_6 = new JLabel("Total : ");
		panelTotaux.add(lblNewLabel_6);
		
		this.lblTotal = new JLabel("€");
		panelTotaux.add(lblTotal);
		
		JLabel lblNewLabel_7 = new JLabel("Provision sur charges");
		panelTotaux.add(lblNewLabel_7);
		
		this.lblProvision = new JLabel("€");
		panelTotaux.add(lblProvision);
		
		JLabel lblNewLabel_8 = new JLabel("Reste à charge :");
		panelTotaux.add(lblNewLabel_8);
		
		this.lblReste = new JLabel("€");
		panelTotaux.add(lblReste);
		
		JPanel panelNouvelleProvision = new JPanel();
		contentPane.add(panelNouvelleProvision, BorderLayout.SOUTH);
		panelNouvelleProvision.setLayout(new GridLayout(2, 2, 10, 10));
		
		JLabel lblNewLabel_9 = new JLabel("Nouvelle provision sur charges :");
		lblNewLabel_9.setToolTipText("laisser vide si inchangé");
		panelNouvelleProvision.add(lblNewLabel_9);
		
		champNouvelleProvision = new JTextField();
		panelNouvelleProvision.add(champNouvelleProvision);
		champNouvelleProvision.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panelNouvelleProvision.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelNouvelleProvision.add(panel_3);
		
		JButton btnNewButton_1 = new JButton("Confirmer");
		panel_3.add(btnNewButton_1);
		btnNewButton_1.addActionListener(controleur);
		
		this.setSizeMulti();
	}

	public String getChampEau() {
		return champEau.getText();
	}
	
	public String getChampElec() {
		return champElec.getText();
	}
	
	public void setChampEau(int valeur) {
		champEau.setText(String.valueOf(valeur));;
	}
	
	public void setChampElec(int valeur) {
		champElec.setText(String.valueOf(valeur));
	}
	
	public String getChampNouvelleProvision() {
		return champNouvelleProvision.getText();
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
