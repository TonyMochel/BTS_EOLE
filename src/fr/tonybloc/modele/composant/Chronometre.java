package fr.tonybloc.modele.composant;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class Chronometre {
	
	private int iHours = 0;			
	private int iMinutes = 0;			
	private int iSeconds = 0;				
	private static final int DELAIS = 1000;
	
	private JLabel lblChrono;
	
	private static Timer tTimer;			
	private static ActionListener alTimer;
	
	/**
	 * Contructeur de la classe
	 */
	public Chronometre(){
		lblChrono = new JLabel(
				( ( iHours < 10 ) 	? "0" + iHours : iHours )		+ ":" +
				( ( iMinutes < 10 ) ? "0" + iMinutes : iMinutes )	+ ":" +
				( ( iSeconds < 10 ) ? "0" + iSeconds : iSeconds )
		);
		
		alTimer = new ActionListener(){
			public void actionPerformed( ActionEvent eEvent ){
				
				iSeconds++;
				if ( iSeconds == 60 ){
					iSeconds = 0;
					iMinutes++;
				}
				if ( iMinutes == 60 ){
					iMinutes = 0;
					iHours++;
				}
				
				lblChrono.setText(						
						( ( iHours < 10 ) 	? "0" + iHours : iHours )		+ ":" +
						( ( iMinutes < 10 ) ? "0" + iMinutes : iMinutes )	+ ":" +
						( ( iSeconds < 10 ) ? "0" + iSeconds : iSeconds )
				);
			}
		};
		createChrono();
	}
	
	/**
	 * Methode executant tout les seconde (DELAI) l'evenement alTimer
	 */
	public void createChrono(){
		// Execution tout les seconde de l'evenement alTimer
		tTimer = new Timer(DELAIS, alTimer );
	}
	
	public JLabel getJLabel(){
		lblChrono.setFont( new Font("Arial", Font.PLAIN, 32) );
		return lblChrono;
	}
	
	public String getText(){
		return lblChrono.getText();
	}
	
	/**
	 * Arrete l'execution du Timer
	 */
	public void StopTimer(){
		tTimer.stop();
	}
	
	/**
	 * Démarre l'execution du Timer
	 */
	public void StartTimer(){
		tTimer.start();
	}
	
	/**
	 * Réinitialise le Timer
	 */
	public void ResetTimer(){
		if ( !tTimer.isRunning() )
			lblChrono.setText( "00:00:00" );
			this.iSeconds = 0;
			this.iMinutes = 0;
			this.iHours = 0;	
	}
}
