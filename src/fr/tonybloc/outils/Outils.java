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
}
