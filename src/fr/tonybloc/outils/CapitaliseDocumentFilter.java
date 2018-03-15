package fr.tonybloc.outils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
/**
 * Classe permettent d'appliqué un placeholder à un TextField
 * @author Tony
 *
 */
public class CapitaliseDocumentFilter extends DocumentFilter {
	
	/**
	 * Crée une instance de la class 'CapitaliseDocumentFilter'
	 */
	public CapitaliseDocumentFilter() {
		super();
	}
	
	/**
	 * Définie le comportement du placeholder
	 */
	public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
			AttributeSet attr) throws BadLocationException {
		
		if(offset == 0  && text.length() > 1) {
			fb.insertString(offset, text.substring(0, 1).toUpperCase() + text.substring(1), attr);	
		}else if(offset == 0 && text.length() <= 1) {
			fb.insertString(offset, text.toUpperCase(), attr);
		}
		else {
			fb.insertString(offset, text.toLowerCase(), attr);
		}
		
	}
	/**
	 * Défini le comportement de la saisie
	 */
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, 
			AttributeSet attrs) throws BadLocationException {

		if(offset == 0 && text.length() > 1) {
			System.out.println("Offset : " + offset + "Text : " + text);
			fb.replace(offset, length, text.substring(0, 1).toUpperCase() + text.substring(1), attrs);	
		}else if(offset == 0 && text.length() <= 1) {
			fb.replace(offset, length, text.toUpperCase(), attrs);
		}
		else {
			fb.replace(offset, length, text.toLowerCase(), attrs);
		}
	}
	
}
