package fr.tonybloc.outils;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;
 
/**
 * Classe JDoubleField : Permet de limiter la saisie d'un champs (au caractères : Nombres entier)
 * @author Tony
 *
 */
public class JNumberField extends JTextField
{
	/**
	 * Crée une instance de la classe 'JNumberField'
	 */
    public JNumberField(){
        super();
    }
 
    /**
     * Crée une instance de la classe 'JNumberField'
     * @param string : string
     */
    public JNumberField(String string)
    {
        super(string);
    }
 
    /**
     * Limite la saisise des caractère
     */
    protected void processComponentKeyEvent(KeyEvent e)
    {
        int id = e.getID();
        switch(id)
        {
                case KeyEvent.KEY_TYPED:
                case KeyEvent.KEY_PRESSED:
                case KeyEvent.KEY_RELEASED:
                	if (allowKey(e))
                    {
                	    super.processComponentKeyEvent(e);
                    }                    
                    else
                    {
                    	e.consume();
                    }
                    break;
 
        }
    }
    
    /**
     * Defini si le caractère saisie est permis
     * @param event : clic
     * @return
     */
    protected boolean allowKey(KeyEvent event)
    {
        int keyCode = event.getKeyCode();
        char keyChar = event.getKeyChar();
        if (keyChar == KeyEvent.VK_0            ||
            keyChar == KeyEvent.VK_1            ||
            keyChar == KeyEvent.VK_2            ||
            keyChar == KeyEvent.VK_3            ||
            keyChar == KeyEvent.VK_4            ||
            keyChar == KeyEvent.VK_5            ||
            keyChar == KeyEvent.VK_6            ||
            keyChar == KeyEvent.VK_7            ||
            keyChar == KeyEvent.VK_8            ||
            keyChar == KeyEvent.VK_9            ||
            keyChar == KeyEvent.VK_BACK_SPACE   ||
            keyCode == KeyEvent.VK_TAB          ||
            keyCode == KeyEvent.VK_RIGHT        ||
            keyCode == KeyEvent.VK_LEFT         ||
            keyCode == KeyEvent.VK_DELETE       ||
            keyCode == KeyEvent.VK_HOME         ||
            keyCode == KeyEvent.VK_END          ||
            keyCode == KeyEvent.VK_ENTER        ||
            keyCode == KeyEvent.VK_ESCAPE       ||
            keyCode == KeyEvent.VK_SHIFT)
        {
        	
            return true;
        }
        else
        {
            return false;
        }
    }
}

