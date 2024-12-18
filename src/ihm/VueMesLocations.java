package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controleur.ControleurMesLocations;
import modele.Location;

public class VueMesLocations extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel t;
	private ControleurMesLocations controleurMesLocations;
	
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
		controleurMesLocations = new ControleurMesLocations(this);
		
		setBounds(100, 100, 662, 359);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Mes Locations  ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel PanelBouton = new JPanel();
		this.add(PanelBouton, BorderLayout.EAST);
		PanelBouton.setLayout(new GridLayout(2, 1, 0, 10));
		
		JPanel PanelBoutonNouveau = new JPanel();
		PanelBouton.add(PanelBoutonNouveau);
		PanelBoutonNouveau.setLayout(new BorderLayout(0, 0));
		
		JButton AjouterLocation = new JButton("Ajouter");
		PanelBoutonNouveau.add(AjouterLocation, BorderLayout.SOUTH);
		AjouterLocation.addActionListener(controleurMesLocations);
		
		JPanel PanelBoutonSupprimer = new JPanel();
		PanelBouton.add(PanelBoutonSupprimer);
		PanelBoutonSupprimer.setLayout(new BorderLayout(0, 0));
		
		JButton SupprimerLocation = new JButton("Supprimer");
		PanelBoutonSupprimer.add(SupprimerLocation, BorderLayout.NORTH);
		SupprimerLocation.addActionListener(controleurMesLocations);
		
		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.t = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id Bien", "Date début location", "Colocation", "Nombre de mois", "Loyer TTC", "Provisions chargement TTC",
						"Caution TTC", "Loyer payé"
					
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Date.class, Integer.class, Integer.class, Float.class, Float.class,
					Float.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				};
				boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
				
			};
			
			this.buildTable(controleurMesLocations);
			this.table = new JTable(this.t);
			table.getTableHeader().setReorderingAllowed(false);
			this.table.setModel(this.t);
			
			scrollPane.setViewportView(table);
			
			//this.table.addMouseListener(controleurMesLocations);
	}
	
	public void buildTable(ControleurMesLocations controleur) {
		this.t.setRowCount(0);
		for (Location l : controleur.getLocation()) {
			t.addRow(new Object[]{
				    l.getIdBien(), l.getDate_debut(), l.isColocation(), l.getNb_mois(),
				    l.getLoyer_TTC(), l.getProvision_chargement_TTC(), l.getCaution_TTC(),
				    "1er janvier 2024", 1 
				});
		}
	}
	
	public int getLigneChoisi() {
		return this.table.getSelectedRow();
	}
	
	public ControleurMesLocations getControleurMesLocations() {
		return this.controleurMesLocations;
	}
	

}
