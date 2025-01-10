package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controleur.ControleurDeclaration;

public class VueDeclaration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblRevenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueDeclaration frame = new VueDeclaration();
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
	public VueDeclaration() {
		
		ControleurDeclaration controleur = new ControleurDeclaration(this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelHaut = new JPanel();
		contentPane.add(panelHaut, BorderLayout.NORTH);
		panelHaut.setLayout(new BorderLayout(0, 20));
		
		JLabel lblTitre = new JLabel("Déclaration fiscale");
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 24));
		panelHaut.add(lblTitre, BorderLayout.NORTH);
		
		JPanel panelRevenus = new JPanel();
		panelHaut.add(panelRevenus, BorderLayout.WEST);
		GridLayout gl_panelRevenus = new GridLayout(0, 2);
		gl_panelRevenus.setHgap(10);
		panelRevenus.setLayout(gl_panelRevenus);
		
		JButton btnRevenu = new JButton("Calculer le revenu");
		panelRevenus.add(btnRevenu, BorderLayout.WEST);
		btnRevenu.addActionListener(controleur);
		
		this.lblRevenu = new JLabel("Revenu :");
		panelRevenus.add(lblRevenu, BorderLayout.CENTER);
		
		JLabel lblRegime = new JLabel("");
		lblRegime.setHorizontalAlignment(SwingConstants.CENTER);
		panelHaut.add(lblRegime, BorderLayout.CENTER);
		
		JPanel panelRegime = new JPanel();
		panelHaut.add(panelRegime, BorderLayout.SOUTH);
		
		JButton btnRegimeReel = new JButton("Régime Réel");
		btnRegimeReel.setToolTipText("Déduction des charges réelles");
		panelRegime.add(btnRegimeReel);
		btnRegimeReel.setVisible(false);
		
		JButton btnMicroFoncier = new JButton("Micro-foncier");
		btnMicroFoncier.setToolTipText("Abattement forfaitaire de 30%");
		panelRegime.add(btnMicroFoncier);
		btnMicroFoncier.setVisible(false);
		
		JPanel panelImprimer = new JPanel();
		contentPane.add(panelImprimer, BorderLayout.SOUTH);
		
		JButton btnImprimer = new JButton("Imprimer");
		panelImprimer.add(btnImprimer);
		
		JTextArea textAreaDeclaration = new JTextArea();
		contentPane.add(textAreaDeclaration, BorderLayout.CENTER);
		
	}
	
	public void afficherRevenus(float montant) {
		this.lblRevenu.setText("Revenus : "+String.valueOf(montant)+" €");
	}

}
