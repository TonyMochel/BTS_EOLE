package fr.tonybloc.outils;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.jdatepicker.impl.JDatePickerImpl;

public class Outils {
	
	/**
	 * Vérifie si un composant (JTextComponent) est vide
	 * @param composant
	 * @return boolean
	 */
	public static boolean estVide(JTextComponent composant)
	{
		 return ( composant.getText().equals("") ) ? true : false;
	}
	/**
	 * Vérifie si un composant (JDatePickerImpl) est vide
	 * @param composant
	 * @return
	 */
	public static boolean estVide(JDatePickerImpl composant) {
		return ( composant.getModel().getValue().equals("") ) ? true : false;
	}
	/**
	 * Convertie une date en Calendar
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date){ 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	/**
	 * Initialise un placeholder sur un JTextField
	 * @param obj
	 * @param text
	 */
	public static void placeholder(JTextField obj, String text) {
		obj.setText(text);
		obj.addFocusListener( new FocusListener() {			
			@Override
			public void focusLost(FocusEvent e) {
				if(obj.getText().equals("")) {
					obj.setText(text);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(obj.getText().equals(text)) {
					obj.setText("");
					//obj.setForeground(Color.BLACK);
				}
			}
		});
	}
	/**
	 * Convertie un nombre de seconde en HH:MM:SS
	 * @param pSeconde
	 * @return
	 */
	public static String convertHMS(int pSeconde) {
		int heure = (int) pSeconde/3600;
		int minute = (int) ((pSeconde % 3600) / 60);
		int seconde = (int) ((pSeconde % 3600) % 60);
		
		String res = 	( ( heure < 10 ) 	? "0" + heure : heure )		+ ":" +
				( ( minute < 10 ) ? "0" + minute : minute )	+ ":" +
				( ( seconde < 10 ) ? "0" + seconde : seconde );
		
		return res;
	}
	
	
	/**
	 * Convertie une date (HH:MM:SS) en seconde
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int convertHTStoS(Date date) {
		return ( date.getSeconds() + (date.getMinutes()*60) + (date.getHours()*60*60) );
	}
}
