package ihm;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controleur.ControleurMesImmeubles;
import modele.Immeuble;
import modele.DAOException;

public class VueMesImmeubles extends JPanelPlus {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel t;
	private ControleurMesImmeubles controleurMesImmeubles;
	private JButton supprimerImmeuble;
	private JButton ajouterImmeuble;

	/**
	 * Create the frame.
	 * @throws DAOException 
	 */
	public VueMesImmeubles() throws DAOException {
		controleurMesImmeubles = new ControleurMesImmeubles(this);
		setBounds(100, 100, 750, 300);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Mes Immeubles ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel PanelBouton = new JPanel();
		this.add(PanelBouton, BorderLayout.EAST);
		PanelBouton.setLayout(new GridLayout(2, 1, 0, 10));
		
		JPanel PanelBoutonNouveau = new JPanel();
		PanelBouton.add(PanelBoutonNouveau);
		PanelBoutonNouveau.setLayout(new BorderLayout(0, 0));
		
		ajouterImmeuble = new JButton("Ajouter");
		PanelBoutonNouveau.add(ajouterImmeuble, BorderLayout.SOUTH);
		ajouterImmeuble.addActionListener(controleurMesImmeubles);
		
		JPanel PanelBoutonSupprimer = new JPanel();
		PanelBouton.add(PanelBoutonSupprimer);
		PanelBoutonSupprimer.setLayout(new BorderLayout(0, 0));
		
		supprimerImmeuble = new JButton("Supprimer");
		PanelBoutonSupprimer.add(supprimerImmeuble, BorderLayout.NORTH);
		supprimerImmeuble.addActionListener(controleurMesImmeubles);
		
		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.t = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id Immeuble", "Type", "Adresse", "CP", "Ville", "Période Construction"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
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
		this.buildTable(controleurMesImmeubles);
		this.table = new JTable(this.t);
		table.getTableHeader().setReorderingAllowed(false);
		this.table.setModel(this.t);
		scrollPane.setViewportView(table);
		
		this.table.addMouseListener(controleurMesImmeubles);
	}
	/**
	 * Mettre à jour la table des immeubles
	 * @param controleur
	 */
	public void buildTable(ControleurMesImmeubles controleur) {
		this.t.setRowCount(0);
		for (Immeuble i : controleur.getImmeuble()) {
			t.addRow(new Object [] {i.getId_immeuble(),i.getClass().getSimpleName(), i.getAdresse(),i.getCp(),i.getVille(),i.getPeriode_construction()});
		}
	}
	/**
	 * Retourner l'indice de la ligne séléctionnée
	 * @return
	 */
	public int getLigneChoisi() {
		return this.table.getSelectedRow();
	}
	/**
	 * Retourner l'identifiant de l'immeuble sélectionné
	 * @return
	 */
	public String getIdImmeubleChoisi() {
		return (String) this.table.getModel().getValueAt(this.getLigneChoisi(), 0);
	}
	/**
	 * Retourner le controleur des immeubles
	 * @return
	 */
	public ControleurMesImmeubles getControleurMesImmeubles() {
		return this.controleurMesImmeubles;
	}
	
}





