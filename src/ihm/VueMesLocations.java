package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classes.Immeuble;
import classes.Location;
import controleur.ControleurMesImmeubles;
import controleur.ControleurMesLocations;

public class VueMesLocations extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel t;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueMesLocations frame = new VueMesLocations();
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
	public VueMesLocations() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Mes Locations  ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel PanelBouton = new JPanel();
		contentPane.add(PanelBouton, BorderLayout.EAST);
		PanelBouton.setLayout(new GridLayout(2, 1, 0, 10));
		
		JPanel PanelBoutonNouveau = new JPanel();
		PanelBouton.add(PanelBoutonNouveau);
		PanelBoutonNouveau.setLayout(new BorderLayout(0, 0));
		
		JButton AjouterImmeuble = new JButton("Ajouter");
		PanelBoutonNouveau.add(AjouterImmeuble, BorderLayout.SOUTH);
		
		JPanel PanelBoutonSupprimer = new JPanel();
		PanelBouton.add(PanelBoutonSupprimer);
		PanelBoutonSupprimer.setLayout(new BorderLayout(0, 0));
		
		JButton SupprimerImeuble = new JButton("Supprimer");
		PanelBoutonSupprimer.add(SupprimerImeuble, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		this.t = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id Immeuble", "Type", "Adresse", "CP", "Ville", "Période Construction"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				};
				boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			//this.buildTable(controleurMesImmeubles);
			this.table = new JTable(this.t);
			this.table.setModel(this.t);
			
			scrollPane.setViewportView(table);
			
			//this.table.addMouseListener(controleurMesImmeubles);
	}
	
	public void buildTable(ControleurMesLocations controleur) {
		this.t.setRowCount(0);
		for (Location l : controleur.getLocation()) {
			t.addRow(new Object [] {l.getDate_debut(), l.getColocation(), l.getNb_mois(), l.getLoyer_TTC(), l.getProvision_chargement_TTC(),
					l.getCaution_TTC(), l.getDate_derniere_reg(), l.isLoyer_paye(), l.getMontant_reel_paye()});
		}
	}
	/*
	public int getLigneChoisi() {
		return this.table.getSelectedRow();
	}
	*/
	

}
