package fr.tonybloc.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.tonybloc.modele.date.DateLabelFormatter;
import fr.tonybloc.outils.JNumberField;


public class VueCreationRegateFormulaire {

	JPanel formulaire;
	
	JTextField tfNomRegate;
	JNumberField tfDistance;
	JDatePickerImpl datePicker;
	JButton btnCreationRegate;
	
	Dimension dim;
	Font police;
	
	public VueCreationRegateFormulaire() {		
		
		this.dim 		= new Dimension(150, 30);
		this.police 	= new Font("Arial", Font.PLAIN, 14);
		//this.content 	= new JPanel(new BorderLayout(100, 20));
		
		initFormulaire();
		//JPanel panelListRegate = initPanelListRegate();
		/*
		content.add(panelListRegate, BorderLayout.CENTER);
		content.add(panelFormulaire, BorderLayout.WEST);
		content.add(new JPanel(), BorderLayout.EAST);
		content.add(new JPanel(), BorderLayout.NORTH);
		content.add(new JPanel(), BorderLayout.SOUTH);
		*/
	}

	public void initFormulaire() {
		
		// Formulaire de création : Régate
		this.formulaire 	= new JPanel(new BorderLayout());
		Border border = BorderFactory.createTitledBorder("Creation de Régates ");
		formulaire.setBorder(border);
		
		// Label
		JLabel lbNomRegate = new JLabel("Nom Régate :");
		JLabel lbDistance = new JLabel("Distance :");
		JLabel lbDate = new JLabel("Date de départ :");
		
		// TextField
		this.tfNomRegate = new JTextField();
		this.tfDistance	= new JNumberField();
		this.btnCreationRegate = new JButton("Créer");
		
		// DatePicker
		UtilDateModel model	= new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
		this.datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		
		lbNomRegate.setLabelFor(this.tfNomRegate);
		lbDistance.setLabelFor(this.tfDistance);
		
		
		// Personalisation		
		lbNomRegate.setPreferredSize(dim);
		lbDistance.setPreferredSize(dim);
		lbDate.setPreferredSize(dim);
		
		lbNomRegate.setFont(police);
		lbDistance.setFont(police);
		lbDate.setFont(police);
		this.tfNomRegate.setFont(police);
		this.tfDistance.setFont(police);
		this.datePicker.setFont(police);
		this.btnCreationRegate.setFont(police);
		
		// Panel de positionnement
		JPanel panelLabel = new JPanel(new GridLayout(3,0));
		JPanel panelTextField = new JPanel(new GridLayout(3,0));
		JPanel panelSouth = new JPanel(new BorderLayout());
		
		
		panelLabel.add(lbNomRegate);
		panelLabel.add(lbDistance);
		panelLabel.add(lbDate);		

		panelTextField.add(this.tfNomRegate);
		panelTextField.add(this.tfDistance);
		panelTextField.add(this.datePicker);
		
		panelSouth.add(btnCreationRegate, BorderLayout.EAST);
		
		// Regroupement des Panels
		JPanel panelContent = new JPanel(new BorderLayout());
		panelContent.add(panelLabel, BorderLayout.WEST);
		panelContent.add(panelTextField, BorderLayout.CENTER);
		panelContent.add(panelSouth, BorderLayout.SOUTH);
		
		
		formulaire.add(panelContent, BorderLayout.NORTH);
		
	}
	
	
	public JPanel getPanelFormulaire() {
		return formulaire;
	}

	public JTextField getTfNomRegate() {
		return tfNomRegate;
	}

	public JNumberField getTfDistance() {
		return tfDistance;
	}
	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}
	public JButton getBtnCreationRegate() {
		return btnCreationRegate;
	}
	
}
