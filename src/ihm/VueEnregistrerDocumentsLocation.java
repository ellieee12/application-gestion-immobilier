package ihm;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControleurEnregistrerDocument;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.File;

import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.ComponentOrientation;

public class VueEnregistrerDocumentsLocation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNomFichier;
	private JTextArea textAreaDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueEnregistrerDocumentsLocation frame = new VueEnregistrerDocumentsLocation();
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
	public VueEnregistrerDocumentsLocation() {
		ControleurEnregistrerDocument controleur = new ControleurEnregistrerDocument(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel LabelEnregDoc = new JLabel("Enregistrer un Document");
		LabelEnregDoc.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(LabelEnregDoc, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new BorderLayout(0, 1));
		
		JLabel lblDescription = new JLabel("Description :  ");
		panel_4.add(lblDescription, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		textAreaDescription = new JTextArea();
		panel_5.add(textAreaDescription, BorderLayout.CENTER);
		
		JPanel panel_14 = new JPanel();
		panel.add(panel_14, BorderLayout.SOUTH);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(controleur);
		btnEnregistrer.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_14.add(btnEnregistrer, BorderLayout.EAST);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(controleur);
		btnAnnuler.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_14.add(btnAnnuler, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.WEST);
		
		JButton btnChoisirFichier = new JButton("Choisir un fichier");
		btnChoisirFichier.addActionListener(controleur);
		btnChoisirFichier.setVerticalTextPosition(SwingConstants.TOP);
		btnChoisirFichier.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(btnChoisirFichier);
		
		this.lblNomFichier = new JLabel("");
		this.lblNomFichier.setVisible(false);
		panel_3.add(this.lblNomFichier);
		
	}
	
	public void afficherNomFichier(String nomFichier) {
		this.lblNomFichier.setText(nomFichier);
		this.lblNomFichier.setVisible(true);
	}
	
	public String getDescription() {
		return this.textAreaDescription.getText();
	}

}
