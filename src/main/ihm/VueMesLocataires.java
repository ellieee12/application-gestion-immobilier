package ihm;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controleur.ControleurMesLocataires;
import modele.Locataire;

public class VueMesLocataires extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel t;
	private ControleurMesLocataires controleur;
	/**
	 * Create the frame.
	 */
	public VueMesLocataires() {
		controleur = new ControleurMesLocataires(this);
		
		setBounds(100, 100, 662, 359);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Mes Locataires");
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
		AjouterLocation.addActionListener(controleur);
		
		JPanel PanelBoutonSupprimer = new JPanel();
		PanelBouton.add(PanelBoutonSupprimer);
		PanelBoutonSupprimer.setLayout(new BorderLayout(0, 0));
		
		JButton SupprimerLocation = new JButton("Supprimer");
		PanelBoutonSupprimer.add(SupprimerLocation, BorderLayout.NORTH);
		SupprimerLocation.addActionListener(controleur);
		
		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.t = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id Locataire", "Nom", "Prenom", "Téléphone", "Mail", "Date de naissance"
					
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, Date.class
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
			
			this.buildTable(controleur);
			this.table = new JTable(this.t);
			table.getTableHeader().setReorderingAllowed(false);
			this.table.setModel(this.t);
			
			scrollPane.setViewportView(table);
			
			//this.table.addMouseListener(controleurMesLocations);
	}
	
	public void buildTable(ControleurMesLocataires controleur) {
		this.t.setRowCount(0);
		for (Locataire l : controleur.getLocataires()) {
			t.addRow(new Object[]{
				    l.getId_locataire(), l.getNom(), l.getPrenom(), l.getTelephone(),
				    l.getMail(), l.getDate_naissance()
				});
		}
	}
	
	public int getLigneChoisi() {
		return this.table.getSelectedRow();
	}
	
	public ControleurMesLocataires getControleurMesLocataires() {
		return this.controleur;
	}
	

}
