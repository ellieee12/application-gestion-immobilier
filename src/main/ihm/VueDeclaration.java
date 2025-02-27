package ihm;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import controleur.ControleurDeclaration;

public class VueDeclaration extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblRevenu;
	private JButton btnRegimeReel;
	private JButton btnMicroFoncier;
	private JTextArea textAreaDeclaration;
	private JButton btnImprimer;

	/**
	 * Create the frame.
	 */
	public VueDeclaration() {
		ControleurDeclaration controleur = new ControleurDeclaration(this);
		
		setBounds(100, 100, 650, 600);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setLayout(new BorderLayout(0, 0));
		
		JPanel panelHaut = new JPanel();
		this.add(panelHaut, BorderLayout.NORTH);
		panelHaut.setLayout(new BorderLayout(0, 20));
		
		JLabel lblTitre = new JLabel("Déclaration fiscale");
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 18));
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
		
		this.btnRegimeReel = new JButton("Régime Réel");
		btnRegimeReel.setToolTipText("Déduction des charges réelles");
		panelRegime.add(btnRegimeReel);
		btnRegimeReel.setVisible(false);
		btnRegimeReel.addActionListener(controleur);
		
		this.btnMicroFoncier = new JButton("Micro-foncier");
		btnMicroFoncier.setToolTipText("Abattement forfaitaire de 30%");
		panelRegime.add(btnMicroFoncier);
		btnMicroFoncier.setVisible(false);
		btnMicroFoncier.addActionListener(controleur);
		
		JPanel panelImprimer = new JPanel();
		this.add(panelImprimer, BorderLayout.SOUTH);
		
		this.btnImprimer = new JButton("Imprimer");
		panelImprimer.add(btnImprimer);
		btnImprimer.addActionListener(controleur);
		btnImprimer.setEnabled(false);
		
		this.textAreaDeclaration = new JTextArea();
		textAreaDeclaration.setWrapStyleWord(true);
		textAreaDeclaration.setLineWrap(true);
		textAreaDeclaration.setEditable(false);
		this.add(textAreaDeclaration, BorderLayout.CENTER);
	}
	
	public void afficherRevenus(float montant) {
		this.btnMicroFoncier.setVisible(false);
		this.lblRevenu.setText("Revenus : "+String.valueOf(montant)+" €");
		if (montant < 15000) {	//on n'affiche le bouton micro-foncier que si le revenu est inférieur à 15 000€
			this.btnMicroFoncier.setVisible(true);
		}
		this.btnRegimeReel.setVisible(true);
	}

	/**
	 * on affiche les informations du formulaire 2044
	 * @param montant
	 */
	public void afficherTexteMicroFoncier(float montant) {
		String text = "Recettes brutes sans abattement 4BE : "+ montant +" €\r\n"
				+ "       dont recettes de source étrangère ouvrant droit à un crédit d’impôt égal à l’impôt français 4BK : 0 €\r\n"
				+ "\r\n"
				+ "Recettes nettes : "+ montant*0.7 +" €";
		this.textAreaDeclaration.setText(text);
		this.btnImprimer.setEnabled(true);
	}
	/**
	 * on affiche les informations du formulaire 2044
	 * @param revenus
	 * @param charges
	 */
	public void afficherTexteRegimeReel(float revenus, float charges) {
		float revenuNet = revenus - charges;
		String text = "";
		if (revenuNet > 0) {	//recette ou déficit en fonction du revenu
			text = "Recettes brutes : "+ revenus +" €\r\n"
					+ "Charges : "+ charges +" €\r\n"
					+ "\r\n"
					+ "Revenus fonciers imposables 4BA : "+ revenuNet +" €\r\n"
					+ "Déficit imputable sur les revenus fonciers 4BB : 0 €\r\n"
					+ "Déficit imputable sur le revenu global 4BC : 0 €\r\n"
					+ "Déficits antérieurs non encore imputés 4BD : 0 €\r\n";
		} else {
			text = "Recettes brutes : "+ revenus +" €\r\n"
					+ "Charges : "+ charges +" €\r\n"
					+ "\r\n"
					+ "Revenus fonciers imposables 4BA : 0 €\r\n"
					+ "Déficit imputable sur les revenus fonciers 4BB : 0 €\r\n"
					+ "Déficit imputable sur le revenu global 4BC : "+ -revenuNet +" €\r\n"
					+ "Déficits antérieurs non encore imputés 4BD : 0 €\r\n";
		}
		this.textAreaDeclaration.setText(text);
		this.btnImprimer.setEnabled(true);
	}
	
	public void imprimerTexte() {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName("Déclaration fiscale");

        // Définir la façon d'imprimer (on utilise ici un Printable)
        printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) { // Si c'est la page 1 ou plus, on arrête
                return Printable.NO_SUCH_PAGE;
            }

            // Récupérer la largeur de la page pour ajuster le line wrap
            double pageWidth = pageFormat.getImageableWidth();
            int x = 100; // Position horizontale
            int y = 100; // Position verticale (première ligne)

            // Obtenez la police utilisée pour le texte et les métriques de la police
            Font font = graphics.getFont();
            FontMetrics fontMetrics = graphics.getFontMetrics(font);

            // Calculer la largeur maximale de la ligne
            int lineWidth = (int) pageWidth - 2 * x; // Largeur de la page - marges

            // Récupérer le texte et le découper en lignes à partir du texte du JTextArea
            String text = textAreaDeclaration.getText();
            String[] lines = text.split("\n"); // Découper le texte en lignes (en tenant compte des sauts de ligne explicites)

            for (String line : lines) {
                StringBuilder currentLine = new StringBuilder();
                String[] words = line.split(" "); // Découper chaque ligne en mots

                for (String word : words) {
                    if (fontMetrics.stringWidth(currentLine.toString() + word) <= lineWidth) {
                        // Ajouter le mot à la ligne en cours
                        currentLine.append(word).append(" ");
                    } else {
                        // Si la ligne est trop longue, dessiner la ligne et recommencer une nouvelle ligne
                        graphics.drawString(currentLine.toString(), x, y);
                        y += fontMetrics.getHeight(); // Passage à la ligne suivante
                        currentLine = new StringBuilder(word + " "); // Nouveau mot sur une nouvelle ligne
                    }
                }

                // Dessiner la dernière ligne restée dans le StringBuilder
                if (currentLine.length() > 0) {
                    graphics.drawString(currentLine.toString(), x, y);
                    y += fontMetrics.getHeight(); // Passage à la ligne suivante
                }
            }

            return Printable.PAGE_EXISTS; // Cela signifie qu'il y a une page à imprimer
        });

        // Si l'imprimante est prête, on lance l'impression
        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
	}
}
