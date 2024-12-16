package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
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

public class VueMesImmeubles extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel t;
	private ControleurMesImmeubles controleurMesImmeubles;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueMesImmeubles frame = new VueMesImmeubles();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws DAOException 
	 */
	public VueMesImmeubles() throws DAOException {
		controleurMesImmeubles = new ControleurMesImmeubles(this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Mes Immeubles ");
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
		AjouterImmeuble.addActionListener(controleurMesImmeubles);
		
		JPanel PanelBoutonSupprimer = new JPanel();
		PanelBouton.add(PanelBoutonSupprimer);
		PanelBoutonSupprimer.setLayout(new BorderLayout(0, 0));
		
		JButton SupprimerImeuble = new JButton("Supprimer");
		PanelBoutonSupprimer.add(SupprimerImeuble, BorderLayout.NORTH);
		SupprimerImeuble.addActionListener(controleurMesImmeubles);
		
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
		this.buildTable(controleurMesImmeubles);
		this.table = new JTable(this.t);
		table.getTableHeader().setReorderingAllowed(false);
		this.table.setModel(this.t);
		
		scrollPane.setViewportView(table);
		
		this.table.addMouseListener(controleurMesImmeubles);
	}
	
	public void buildTable(ControleurMesImmeubles controleur) {
		this.t.setRowCount(0);
		for (Immeuble i : controleur.getImmeuble()) {
			t.addRow(new Object [] {i.getId_immeuble(),i.getClass().getSimpleName(), i.getAdresse(),i.getCp(),i.getVille(),i.getPeriode_construction()});
		}
	}
	
	public int getLigneChoisi() {
		return this.table.getSelectedRow();
	}

	public ControleurMesImmeubles getControleurMesImmeubles() {
		return this.controleurMesImmeubles;
	}
}
