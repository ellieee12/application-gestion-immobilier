package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controleur.ControleurMesLocations;
import modele.DAOException;
import modele.Location;

public class VueMesLocations extends JPanelPlus {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel t;
	private ControleurMesLocations controleurMesLocations;
	private JButton boutonRegu;
	private JButton boutonSolde;

	/**
	 * Create the frame.
	 * @throws DAOException 
	 */
	public VueMesLocations() throws DAOException {
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
		
		JPanel panel = new JPanel();
		PanelBoutonNouveau.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		this.boutonRegu = new JButton("<html><div style='text-align: center;'>Régularisation<br>des charges</div></html>");
		this.boutonRegu.setHorizontalAlignment(SwingConstants.CENTER); // Centre horizontalement
		this.boutonRegu.setVerticalAlignment(SwingConstants.CENTER);   // Centre verticalement
		panel.add(this.boutonRegu, BorderLayout.NORTH);
		this.boutonRegu.addActionListener(controleurMesLocations);
		
		this.boutonSolde = new JButton("<html><div style='text-align: center;'>Solde de<br>tout comptes</div></html>");
		panel.add(this.boutonSolde, BorderLayout.CENTER);
		this.boutonSolde.addActionListener(controleurMesLocations);
		
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
						"Caution TTC", "Date dernière régularisation", "Date Fin Location", "Id Locataire"	
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, Date.class, Integer.class, Integer.class, Float.class, Float.class,
					Float.class, Date.class, Date.class, String.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				};
				boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false, false, false, false, false
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
	}
	/**
	 * Mettre à jour la table des locations
	 * @param controleur
	 */
	public void buildTable(ControleurMesLocations controleur) {
		this.t.setRowCount(0);
		for (Location l : controleur.getLocation()) {
			t.addRow(new Object[]{
				    l.getIdBien(), l.getDate_debut(), l.isColocation(), l.getNb_mois(),
				    l.getLoyer_TTC(), l.getProvision_chargement_TTC(), l.getCaution_TTC(),
				    l.getDate_regularisation(), l.getDate_fin(), l.getIdLocataire()
			});
		}
	}
	/**
	 * Retourner la ligne séléctionnée
	 * @return int
	 */
	public int getLigneChoisi() {
		return this.table.getSelectedRow();
	}
	/**
	 * Retourner le controleur
	 * @return ControleurMesLocations
	 */
	public ControleurMesLocations getControleurMesLocations() {
		return this.controleurMesLocations;
	}
	/**
	 * Retourner le bouton regu?
	 * @return JButton
	 */
	public JButton getBoutonRegu() {
		return boutonRegu;
	}
	

}
