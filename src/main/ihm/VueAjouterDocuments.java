package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controleur.ControleurAjouterDocuments;
import modele.Location;

public class VueAjouterDocuments extends JFramePlus {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel etatDesLieux;
	public JLabel getEtatDesLieux() {
		return etatDesLieux;
	}

	public void setEtatDesLieux(JLabel etatDesLieux) {
		this.etatDesLieux = etatDesLieux;
	}

	private JLabel caution;
	private JButton bEtatDesLieux;
	private JButton bCaution;
	private Location loc;
	private Date dateDebut;
	private String idBien;
	private String idLocataire;
	private ControleurAjouterDocuments controleur;

	/**
	 * Create the frame.
	 */
	public VueAjouterDocuments(Location loc, String idBien, String idLocataire, VueMesLocations vueMesLocations) {
		this.loc= loc;
		this.dateDebut=loc.getDate_debut();
		this.idBien=idBien;
		this.idLocataire=idLocataire;
		
		this.controleur = new ControleurAjouterDocuments(this,dateDebut,idBien,idLocataire, vueMesLocations);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 20));
		
		JLabel lblNewLabel = new JLabel("Documents location");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panelLibellé = new JPanel();
		contentPane.add(panelLibellé, BorderLayout.WEST);
		panelLibellé.setLayout(new GridLayout(0, 1, 5, 0));
		
		JPanel panel = new JPanel();
		panelLibellé.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Etat des lieux");
		panel.add(lblNewLabel_3, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panelLibellé.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Acte de caution");
		panel_1.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JPanel panelBoutons = new JPanel();
		contentPane.add(panelBoutons, BorderLayout.SOUTH);
		panelBoutons.setLayout(new BorderLayout(0, 0));
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(controleur);
		panelBoutons.add(btnEnregistrer, BorderLayout.EAST);
		
		JPanel panelChamps = new JPanel();
		panelChamps.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelChamps.setBackground(new Color(240, 240, 240));
		contentPane.add(panelChamps, BorderLayout.CENTER);
		panelChamps.setLayout(new GridLayout(0, 1, 5, 0));
		
		JPanel panel_4 = new JPanel();
		panelChamps.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		this.etatDesLieux = new JLabel("");
		panel_4.add(this.etatDesLieux, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8, BorderLayout.EAST);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));
		
		this.bEtatDesLieux = new JButton("Choisir");
		panel_8.add(this.bEtatDesLieux);
		bEtatDesLieux.addActionListener(controleur);
		
		JPanel panel_5 = new JPanel();
		panelChamps.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		this.caution = new JLabel("");
		panel_5.add(this.caution, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		panel_5.add(panel_9, BorderLayout.EAST);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));
		
		this.bCaution = new JButton("Choisir");
		panel_9.add(this.bCaution);
		bCaution.addActionListener(controleur);
		
		this.setSizeMulti();
		this.setLogo();
	}
	
	public JLabel getCaution() {
		return this.caution;
	}

	public ControleurAjouterDocuments getControleur() {
		return controleur;
	}

	public JButton getbEtatDesLieux() {
		return bEtatDesLieux;
	}

	public JButton getbCaution() {
		return bCaution;
	}

	public Location getLoc() {
		return loc;
	}

	public String getIdBien() {
		return idBien;
	}

	public String getIdLocataire() {
		return idLocataire;
	}

	public void afficherNomFichier(String nomFichier, JLabel label) {
		label.setText(nomFichier);
	}
	
}
