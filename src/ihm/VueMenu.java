package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControleurMenu;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class VueMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueMenu frame = new VueMenu();
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
	public VueMenu() {
		ControleurMenu controleur = ControleurMenu.getControleur(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabelTitre = new JLabel("Accueil");
		lblNewLabelTitre.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabelTitre, BorderLayout.NORTH);
		
		JPanel panelBoutonsPages = new JPanel();
		contentPane.add(panelBoutonsPages, BorderLayout.CENTER);
		
		JButton boutonMesImmeubles = new JButton("Mes Immeubles");
		panelBoutonsPages.add(boutonMesImmeubles);
		
		JButton boutonAjouterLocation = new JButton("Ajouter une Location");
		panelBoutonsPages.add(boutonAjouterLocation);
		
		JButton boutonAjouterLocataire = new JButton("Ajouter un Locataire");
		panelBoutonsPages.add(boutonAjouterLocataire);
		
		boutonMesImmeubles.addActionListener(controleur);
		boutonAjouterLocataire.addActionListener(controleur);
		boutonAjouterLocation.addActionListener(controleur);
	}

}
