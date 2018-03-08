package fr.tonybloc.vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VueAccueil {
	private JPanel content;
	private JLabel lbTitre;
	private JLabel lbImgRegate;
	private final Font policeTitle = new Font("Arial", Font.PLAIN, 20);
	
	public VueAccueil() {
		this.content = new JPanel(new BorderLayout());
		this.content.setBorder(new EmptyBorder(20, 10, 20, 10));
		JPanel panelGroup = new JPanel(new BorderLayout());
		
		this.lbTitre = new JLabel("APPLICATION : GESTIONNAIRE DE REGATE", SwingConstants.CENTER);
		this.lbTitre.setFont(policeTitle);
		
		ImageIcon icCourse 	= new ImageIcon("./img/logo_eole.png");
		Image imCourse 		= icCourse.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
		icCourse 			= new ImageIcon(imCourse);
		JLabel lbIconAcceuil = new JLabel(icCourse);
		
		panelGroup.add(lbTitre, BorderLayout.NORTH);
		panelGroup.add(lbIconAcceuil, BorderLayout.CENTER);
		
		this.content.add(panelGroup, BorderLayout.CENTER);
	}

	public JPanel getContent() {
		return content;
	}

	public JLabel getLbTitre() {
		return lbTitre;
	}

	public JLabel getLbImgRegate() {
		return lbImgRegate;
	}
	
	
	
}
