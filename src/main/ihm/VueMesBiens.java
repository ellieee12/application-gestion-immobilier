package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controleur.ControleurMesBiens;
import modele.Bien;
import modele.Logement;
import modele.DAOException;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VueMesBiens extends JFramePlus {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel t;
	private JButton valider;
	private JButton supprimer;
	private String idImmeuble;
	private ControleurMesBiens controleurMesBiens;

	/**
	 * Create the frame.
	 * @throws DAOException 
	 */
	public VueMesBiens(VueMesImmeubles vue,String idImmeuble) throws DAOException {
		super(vue);
		this.idImmeuble=idImmeuble;
		controleurMesBiens = new ControleurMesBiens(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel PanelInformations = new JPanel();
		contentPane.add(PanelInformations, BorderLayout.NORTH);
		PanelInformations.setLayout(new BorderLayout(0, 5));
		
		JLabel lblNewLabel = new JLabel("Immeuble "+this.idImmeuble);
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
		
		JLabel lblNewLabel_1 = new JLabel("Adresse : "+controleurMesBiens.getAdresse());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		PanelAdresse.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel PanelCP = new JPanel();
		PanelInformationsBatiment.add(PanelCP);
		PanelCP.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Code Postal : "+controleurMesBiens.getCp());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		PanelCP.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel PanelVille = new JPanel();
		PanelInformationsBatiment.add(PanelVille);
		PanelVille.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Ville : "+controleurMesBiens.getVille());
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		PanelVille.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel PanelBouton = new JPanel();
		contentPane.add(PanelBouton, BorderLayout.EAST);
		PanelBouton.setLayout(new GridLayout(2, 1, 0, 5));
		
		JPanel PanelBoutonValider = new JPanel();
		PanelBouton.add(PanelBoutonValider);
		PanelBoutonValider.setLayout(new BorderLayout(0, 0));
		
		this.valider = new JButton("Ajouter");
		this.valider.addActionListener(controleurMesBiens);
		PanelBoutonValider.add(valider, BorderLayout.SOUTH);
		
		
		JPanel PanelBoutonSupprimer = new JPanel();
		PanelBouton.add(PanelBoutonSupprimer);
		PanelBoutonSupprimer.setLayout(new BorderLayout(0, 0));
		
		this.supprimer = new JButton("Supprimer");
		this.supprimer.addActionListener(controleurMesBiens);
		PanelBoutonSupprimer.add(supprimer, BorderLayout.NORTH);
		
		this.t = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id bien", "Type", "Num\u00E9ro \u00E9tage", "Surface habitable", "Nb pi\u00E8ces", "Entretien parties communes","Date d'acquisition"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, Integer.class, Float.class, Integer.class, Float.class, Date.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				};
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};

		buildTable(controleurMesBiens);
		this.table = new JTable(t);
		table.getTableHeader().setReorderingAllowed(false);
		this.table.setModel(this.t);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(this.table);
		
		this.setSizeMulti();
	}

	public void buildTable(ControleurMesBiens controleur) {
		this.t.setRowCount(0);
		for (Bien b : controleur.getBien()) {
			this.t.addRow(new Object[] {b.getId_bien(),
					b.getClass().getSimpleName(),
					b.getNum_etage().isPresent()?b.getNum_etage().getAsInt():null,
					b.isLogement()?((Logement) b).getSurface_habitable():null,
					b.isLogement()?((Logement) b).getNb_pieces():null,
					b.getEntretienPartieCommune(),
					b.getDate_acquisition()} );
		}
	}

	public int getLigneChoisi() {
		return this.table.getSelectedRow();
	}
	

	public String getIdImmeuble() {
		return idImmeuble;
	}
	
	public ControleurMesBiens getControleurMesBiens() {
		return this.controleurMesBiens;
	}

}

