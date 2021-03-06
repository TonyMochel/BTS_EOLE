package fr.tonybloc.modele.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * Classe de pérsonalisation.
 * Pérsonalise le format de la date du datepicker
 * @author Tony
 *
 */
@SuppressWarnings("serial")
public class DateLabelFormatter extends AbstractFormatter {
 
	/** Pattern de la date */
    private String datePattern = "yyyy-MM-dd";	
    /** Format de la date */
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
    
    
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }
 
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
 
}
