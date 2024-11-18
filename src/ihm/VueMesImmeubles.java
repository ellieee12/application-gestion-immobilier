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
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class VueMesImmeubles extends JFrame {

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
	 */
	public VueMesImmeubles() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		
		JPanel PanelBoutonSupprimer = new JPanel();
		PanelBouton.add(PanelBoutonSupprimer);
		PanelBoutonSupprimer.setLayout(new BorderLayout(0, 0));
		
		JButton SupprimerImeuble = new JButton("Supprimer");
		PanelBoutonSupprimer.add(SupprimerImeuble, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		DefaultTableModel t = new DefaultTableModel(new Object[] {"e", "u"}, 0);
		this.table = new JTable(t);
		this.table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id Immeuble", "Type", "Adresse", "CP", "Ville", "Période Construction"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Date.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		scrollPane.setViewportView(table);
	}
	
	public int getLigneChoisi() {
		return this.table.getSelectedRow();
	}

}
