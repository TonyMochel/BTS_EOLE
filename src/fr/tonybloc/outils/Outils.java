package fr.tonybloc.outils;

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
}
