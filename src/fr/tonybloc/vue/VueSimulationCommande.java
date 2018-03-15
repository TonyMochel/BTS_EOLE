package fr.tonybloc.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.tonybloc.modele.composant.Chronometre;

/**
 * Vue paneau de controle de la simulation
 * @author Tony
 *
 */
public class VueSimulationCommande {

	private JPanel content;
	private JButton btnArreteChrono;
	private JButton btnDemarreChrono;
	private JButton btnRecommencer;
	private JButton btnCloturerRegate;
	
	private Chronometre chrono;
	private JLabel lbChrono;
	
	private final Dimension dim = new Dimension(150, 30);
	private final Font police = new Font("Arial", Font.PLAIN, 14);
	
	public VueSimulationCommande() {
		
		
		this.content = new JPanel(new BorderLayout());
		this.content.setBorder(new EmptyBorder(20, 10, 20, 10));
		
		this.btnCloturerRegate = new JButton("Clotuer la régate");
		this.btnArreteChrono = new JButton("Arreter");
		this.btnDemarreChrono = new JButton("Démarrer");
		this.btnRecommencer = new JButton("Recommencer");
		
		this.chrono = new Chronometre();
		this.lbChrono = this.chrono.getJLabel();
		
//		JLabel lbTitreChrono = new JLabel("CHRONOMETRE :");
		
		btnArreteChrono.setFont(police);
		btnDemarreChrono.setFont(police);
		btnRecommencer.setFont(police);
		btnCloturerRegate.setFont(police);
		lbChrono.setFont(new Font("Arial", Font.PLAIN, 46));
//		lbTitreChrono.setFont(police);
		
		
		JPanel panelGroupe = new JPanel(new GridLayout(0,2));
		JPanel panelChrono = new JPanel(new FlowLayout());
		JPanel panelCommande = new JPanel(new FlowLayout());
		
		panelCommande.add(btnDemarreChrono);
		panelCommande.add(btnArreteChrono);
		panelCommande.add(btnRecommencer);
		panelCommande.add(btnCloturerRegate);
//		panelChrono.add(lbTitreChrono);
		panelChrono.add(lbChrono);
		
		panelGroupe.add(panelChrono);
		panelGroupe.add(panelCommande);
		
		content.add(panelGroupe, BorderLayout.CENTER);		
		
	}

	public JPanel getContent() {
		return content;
	}

	public JButton getBtnArreteChrono() {
		return btnArreteChrono;
	}

	public JButton getBtnDemarreChrono() {
		return btnDemarreChrono;
	}

	public JButton getBtnRecommencer() {
		return btnRecommencer;
	}

	public JLabel getLbChrono() {
		return lbChrono;
	}
	public JButton getBtnCloture() {
		return btnCloturerRegate;
	}
	public Chronometre getChrono() {
		return chrono;
	}
	
	
	
}
