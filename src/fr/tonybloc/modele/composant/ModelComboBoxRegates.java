package fr.tonybloc.modele.composant;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.RegateDAO;
import fr.tonybloc.modele.Regate;

public class ModelComboBoxRegates extends AbstractListModel implements ComboBoxModel {

	private RegateDAO regateManager;
	private List<Regate> regates;
	
	private Regate regateSelectionner = null;
	
	public final static boolean REGATE_CLOSURE = true;
	public final static boolean REGATE_NOT_CLOSURE = false;
	
	public ModelComboBoxRegates(boolean typeRegate ) {
		super();
		this.regateManager = DAOFactory.getRegateDAO();
		
		if(typeRegate == REGATE_CLOSURE) {
			this.regates = regateManager.findRegateClosure();
		}else if(typeRegate == REGATE_NOT_CLOSURE) {
			this.regates = regateManager.findRegateNotClosure();
		}else {
			this.regates = new ArrayList<Regate>();
		}
		
	}

	@Override
	public Regate getElementAt(int index) {
		return regates.get(index);
	}

	@Override
	public int getSize() {
		return regates.size();
	}

	@Override
	public Object getSelectedItem() {
		return regateSelectionner;
	}

	@Override
	public void setSelectedItem(Object item) {
		this.regateSelectionner = (Regate) item;
		
	}
}