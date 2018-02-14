package fr.tonybloc.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.modele.Regate;
import fr.tonybloc.outils.*;

/**
 * 
 * @author Tony
 *
 */
public class FormulaireCreationRegate implements ActionListener {

	//JPanel panelCreationRegate;
	JTextField tfNomRegate;
	JNumberField tfDistance;
	JDatePickerImpl dpDate;
	JButton btnCreation;
	
	private RegateDAO regateManager = DAOFactory.getRegateDAO();
	private final static Pattern DISTANCE_FORMAT = Pattern.compile("^[0-9]+.{0,1}[0-9]{0,2}$");
	
	
	
	public FormulaireCreationRegate ( JTextField tfNomRegate, JNumberField tfDistance, JDatePickerImpl dpDate, JButton btnCreation ) {
		
		//panelCreationRegate = new JPanel();
		this.tfNomRegate = tfNomRegate;
		this.tfDistance = tfDistance;
		this.dpDate = dpDate;
		this.btnCreation = btnCreation;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == this.btnCreation) {
			
			Matcher m = DISTANCE_FORMAT.matcher(this.tfDistance.getText());
			Boolean verifDistance = m.find();
			
			
			if(verifDistance && !Outils.estVide(this.tfNomRegate) && !Outils.estVide(this.tfDistance) && !Outils.estVide(dpDate) ) {
				
				String libeller = this.tfNomRegate.getText();
				
				Double distance = Double.parseDouble(this.tfDistance.getText());
				Date date_depart = (Date) this.dpDate.getModel().getValue();
				
				System.out.println("Distance OK !");
				System.out.println("libeller: " + libeller);
				System.out.println("distance: " + distance);
				//System.out.println("date_depart: " + DATE_FORMAT.format(date_depart));
				System.out.println("date est vide: " + Outils.estVide(dpDate));
				
				this.regateManager.create(new Regate(libeller, distance, date_depart, false));
				
			}else {
				System.out.println("Erreur !");
			}
		}else {
			System.out.println("source non trouvé !");

		}
	}	
}
