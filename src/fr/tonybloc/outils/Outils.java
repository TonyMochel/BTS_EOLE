package fr.tonybloc.outils;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

import org.jdatepicker.impl.JDatePickerImpl;

import fr.tonybloc.modele.Categorie;
import fr.tonybloc.modele.Regate;

/**
 * Methode tiers (Boite à outils)
 * @author Tony
 *
 */
public class Outils {
	
	/**
	 * Vérifie si un composant (JTextComponent) est vide
	 * @param composant : JTextComponent a annalyser
	 * @return boolean
	 */
	public static boolean estVide(JTextComponent composant)
	{
		 return ( composant.getText().equals("") ) ? true : false;
	}
	/**
	 * Vérifie si un composant (JDatePickerImpl) est vide
	 * @param composant : JDaatePicker a annalyser
	 * @return boolean
	 */
	public static boolean estVide(JDatePickerImpl composant) {
		return ( composant.getModel().getValue().equals("") ) ? true : false;
	}
	/**
	 * Convertie une date en Calendar
	 * @param date : date à convertir
	 * @return Calendar
	 */
	public static Calendar dateToCalendar(Date date){ 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	/**
	 * Initialise un placeholder sur un JTextField
	 * @param obj : TextField cible
	 * @param text : texte du placeholder
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
	 * @param pSeconde : nombre de seconde à convertir
	 * @return String
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
	 * @param date : date à convertire
	 * @return int
	 */
	@SuppressWarnings("deprecation")
	public static int convertHTStoS(Date date) {
		return ( date.getSeconds() + (date.getMinutes()*60) + (date.getHours()*60*60) );
	}
	
	/**
	 * Convertie une JTable en fichier excel
	 * @param tableCategorie1 : JTable participant de la categorie A
	 * @param tableCategorie2 : JTable participant de la categorie B
	 * @param tableCategorie3 : JTable participant de la categorie C
	 * @param tableCategorie4 : JTable participant de la categorie D
	 * @param regate : Régate courrente
	 * @param file : fichier d'export (résultat)
	 */
	public static void toExcel(JTable tableCategorie1, JTable tableCategorie2, JTable tableCategorie3, JTable tableCategorie4, Regate regate, File file){
	    try{
	        TableModel modelCategorie1 = tableCategorie1.getModel();
	        TableModel modelCategorie2 = tableCategorie2.getModel();
	        TableModel modelCategorie3 = tableCategorie3.getModel();
	        TableModel modelCategorie4 = tableCategorie4.getModel();
	        
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        
	        // En-tête :
	        bw.write("CLASSEMENT DE LA COURSE : " + regate.getIntituler() + " DU " + regate.getDate_depart_string() + "\r\n");
			bw.write("\r\n");
			bw.write("\r\n");
			
	        // Categorie 1 :
			bw.write("Classement : CATEGORIE 1" + "\r\n");
			ecritLeClassementDeLaCategorie(modelCategorie1,bw);
	        bw.write("\r\n");
			bw.write("Classement : CATEGORIE 2" + "\r\n");
	        ecritLeClassementDeLaCategorie(modelCategorie2,bw);
	        bw.write("\r\n");
			bw.write("Classement : CATEGORIE 3" + "\r\n");
			ecritLeClassementDeLaCategorie(modelCategorie3,bw);
	        bw.write("\r\n");
			bw.write("Classement : CATEGORIE 4" + "\r\n");
	        ecritLeClassementDeLaCategorie(modelCategorie4,bw);
	        
	        bw.close();

	    }catch(IOException e){ System.out.println(e); }
	}
	
	/**
	 * Ecrit le classement des participants d'une categorie
	 * @param modelCategorie : Model de la JTable categorie
	 * @param bw : BufferWriter
	 * @throws IOException 
	 */
	private static void ecritLeClassementDeLaCategorie(TableModel modelCategorie, BufferedWriter bw) throws IOException {
		
	    for(int indexCol = 0; indexCol < modelCategorie.getColumnCount(); indexCol++){
	        bw.write(modelCategorie.getColumnName(indexCol) + ";");
	    }
	    bw.write("\r\n");
	
	    for(int rowIndex = 0; rowIndex < modelCategorie.getRowCount(); rowIndex++) {
	        for(int colIndex = 0; colIndex < modelCategorie.getColumnCount(); colIndex++) {
	            bw.write(modelCategorie.getValueAt(rowIndex,colIndex).toString() + ";");
	        }
	        bw.write("\r\n");
	    }
	    bw.write("\r\n");
	}
}
