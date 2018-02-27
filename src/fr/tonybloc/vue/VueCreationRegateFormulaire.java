package fr.tonybloc.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.tonybloc.modele.date.DateLabelFormatter;
import fr.tonybloc.outils.CapitaliseDocumentFilter;
import fr.tonybloc.outils.JDoubleField;


public class VueCreationRegateFormulaire {

	private JPanel formulaire;
	
	private JTextField tfNomRegate;
	private JDoubleField tfDistance;
	private JDatePickerImpl datePicker;
	private JButton btnCreation;
	private JButton btnAnnuler;
	
	private final Dimension dim = new Dimension(150, 30);
	private final Font police = new Font("Arial", Font.PLAIN, 14);
	
	public VueCreationRegateFormulaire() {		
		
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
		this.formulaire.setBorder(new EmptyBorder(20, 10, 20, 10));
		
		// Label
		JLabel lbNomRegate = new JLabel("Nom Régate :");
		JLabel lbDistance = new JLabel("Distance :");
		JLabel lbDate = new JLabel("Date de départ :");
		
		// TextField & Buttons
		this.tfNomRegate = new JTextField();
		this.tfDistance	= new JDoubleField();
		this.btnCreation = new JButton("Créer");
		this.btnAnnuler = new JButton("Annuler");
		this.btnAnnuler.setVisible(false);
		
		DocumentFilter uppercaseFilter = new CapitaliseDocumentFilter();
		((AbstractDocument) this.tfNomRegate.getDocument()).setDocumentFilter(uppercaseFilter);
		
		
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
		this.btnCreation.setFont(police);
		this.btnAnnuler.setFont(police);
		
		// Panel de positionnement
		JPanel panelLabel = new JPanel(new GridLayout(3,0));
		JPanel panelTextField = new JPanel(new GridLayout(3,0));
		JPanel panelSouth = new JPanel(new BorderLayout());
		JPanel panelButtons = new JPanel(new FlowLayout());
		
		
		panelLabel.add(lbNomRegate);
		panelLabel.add(lbDistance);
		panelLabel.add(lbDate);		

		panelTextField.add(this.tfNomRegate);
		panelTextField.add(this.tfDistance);
		panelTextField.add(this.datePicker);
		
		panelButtons.add(btnAnnuler);
		panelButtons.add(btnCreation);
		
		panelSouth.add(panelButtons, BorderLayout.EAST);
		
		// Regroupement des Panels
		JPanel panelContent = new JPanel(new BorderLayout());
		panelContent.add(panelLabel, BorderLayout.WEST);
		panelContent.add(panelTextField, BorderLayout.CENTER);
		panelContent.add(panelSouth, BorderLayout.SOUTH);
		
		panelContent.setBorder(BorderFactory.createTitledBorder("Creation de régates"));
		
		formulaire.add(panelContent, BorderLayout.NORTH);
		
	}
	
	
	public JPanel getPanelFormulaire() {
		return formulaire;
	}
	public JTextField getTfNomRegate() {
		return tfNomRegate;
	}
	public JDoubleField getTfDistance() {
		return tfDistance;
	}
	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}
	public JButton getBtnCreation() {
		return btnCreation;
	}
	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}
	
}
