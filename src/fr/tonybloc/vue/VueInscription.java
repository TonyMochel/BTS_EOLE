package fr.tonybloc.vue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.Border;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.*;
import fr.tonybloc.modele.*;

/**
 * Formulaire : création d'un voilier 
 * @author Tony
 *
 */
public class VueInscription{
	
	private VueInscriptionFormulaire vueFormulaire;
	private VueInscriptionListe vueListe;
	
	private JPanel content;
	
	/**
	 * Crée une instance de la classe 'VueCreationVoilier'
	 */
	public VueInscription() {
		this.vueListe = new VueInscriptionListe();
		this.vueFormulaire = new VueInscriptionFormulaire();
		
		
		this.content = new JPanel(new BorderLayout());
		
		content.add(vueFormulaire.getPanelFormulaire(), BorderLayout.WEST);
		content.add(vueListe.getPanelListParticipant(), BorderLayout.CENTER);
	
	}
	
	public VueInscriptionFormulaire getVueFormulaire() {
		return this.vueFormulaire;
	}
	public VueInscriptionListe getVueListe() {
		return this.vueListe;
	}
	public JPanel getContent() {
		return this.content;
	}
	
	
}
