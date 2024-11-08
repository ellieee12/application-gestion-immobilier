package sae3a01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VueMesBiens extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueMesBiens frame = new VueMesBiens();
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
	public VueMesBiens(/*String NomImmeuble*/) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel PanelInformations = new JPanel();
		contentPane.add(PanelInformations, BorderLayout.NORTH);
		PanelInformations.setLayout(new BorderLayout(0, 5));
		
		JLabel lblNewLabel = new JLabel("Immeuble A"/*NomImmeuble*/);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		PanelInformations.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel PanelInformationsBatiment = new JPanel();
		PanelInformationsBatiment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		PanelInformationsBatiment.setBorder(new TitledBorder(null, "Informations du batiment", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		PanelInformations.add(PanelInformationsBatiment, BorderLayout.SOUTH);
		PanelInformationsBatiment.setLayout(new GridLayout(3, 1, 0, 3));
		
		JPanel PanelAdresse = new JPanel();
		PanelInformationsBatiment.add(PanelAdresse);
		PanelAdresse.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Adresse");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		PanelAdresse.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel PanelCP = new JPanel();
		PanelInformationsBatiment.add(PanelCP);
		PanelCP.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Code Postal");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		PanelCP.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel PanelVille = new JPanel();
		PanelInformationsBatiment.add(PanelVille);
		PanelVille.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Ville");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		PanelVille.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel PanelBouton = new JPanel();
		contentPane.add(PanelBouton, BorderLayout.EAST);
		PanelBouton.setLayout(new GridLayout(2, 1, 0, 5));
		
		JPanel PanelBoutonValider = new JPanel();
		PanelBouton.add(PanelBoutonValider);
		PanelBoutonValider.setLayout(new BorderLayout(0, 0));
		
		JButton Valider = new JButton("Ajouter");
		PanelBoutonValider.add(Valider, BorderLayout.SOUTH);
		
		
		JPanel PanelBoutonSupprimer = new JPanel();
		PanelBouton.add(PanelBoutonSupprimer);
		PanelBoutonSupprimer.setLayout(new BorderLayout(0, 0));
		
		JButton Supprimer = new JButton("Supprimer");
		PanelBoutonSupprimer.add(Supprimer, BorderLayout.NORTH);
		
		
		
		DefaultTableModel t = new DefaultTableModel(new Object[] {"e", "u"}, 0);
		JTable table = new JTable(t);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id bien", "Num\u00E9ro \u00E9tage", "Surface habitable"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
	}

}
