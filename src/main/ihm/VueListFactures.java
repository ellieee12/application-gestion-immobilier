package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import controleur.ControleurListFactures;
import controleur.ControleurMesBiens;
import modele.Bien;
import modele.Facture;
import modele.Logement;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class VueListFactures extends JPanelPlus {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel t;
	private JTable table;
	private JButton ajouter;
	private JButton supprimer;
	private ControleurListFactures controleur;

	/**
	 * Create the frame.
	 */
	public VueListFactures() {
		this.controleur = new ControleurListFactures(this);
		setBounds(100, 100, 800, 300);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Factures");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.add(lblNewLabel, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.t = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Numero", "Date d'emission", "Date de paiement", "Numero de devis", "Designation", "Montant réel payé", "Montant", "Imputable locataire", "Id bien"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Date.class, Date.class, String.class, String.class, Float.class, Float.class, Float.class, String.class
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
			
		buildTable(this.controleur);
		this.table = new JTable(this.t);
		table.getTableHeader().setReorderingAllowed(false);
		this.table.setModel(this.t);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(2, 1, 0, 10));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		this.ajouter = new JButton("Ajouter");
		panel_1.add(this.ajouter, BorderLayout.SOUTH);
		this.ajouter.addActionListener(controleur);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		this.supprimer = new JButton("Supprimer");
		panel_2.add(this.supprimer, BorderLayout.NORTH);
		this.supprimer.addActionListener(controleur);
	}
	
	public void buildTable(ControleurListFactures controleur) {
		this.t.setRowCount(0);
		for (Facture f : controleur.getFactures()) {
			this.t.addRow(new Object[] {f.getNumero(), f.getDate_emission(), f.getDate_paiement(), f.getNumero_devis(), f.getDesignation(), f.getMontant_reel_paye(), f.getMontant(), f.getImputable_locataire(), f.getId_bien()} );
		}
	}
	
	public ControleurListFactures getControleurMesBiens() {
		return this.controleur;
	}
	
	public int getLigneChoisi() {
		return this.table.getSelectedRow();
	}

}
