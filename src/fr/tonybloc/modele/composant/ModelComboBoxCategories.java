package fr.tonybloc.modele.composant;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import fr.tonybloc.dao.DAOFactory;
import fr.tonybloc.dao.implement.CategorieDAO;
import fr.tonybloc.modele.Categorie;
import fr.tonybloc.modele.Regate;

public class ModelComboBoxCategories extends AbstractListModel implements ComboBoxModel {
	private CategorieDAO categorieManager;
	private List<Categorie> categories;
	
	private Categorie CategorieSelectionner = null;
	
	public ModelComboBoxCategories() {
		super();
		
		this.categorieManager = DAOFactory.getCategorieDAO();
		this.categories = categorieManager.findAll();
		
	}

	@Override
	public Categorie getElementAt(int index) {
		return categories.get(index);
	}

	@Override
	public int getSize() {
		return categories.size();
	}

	@Override
	public Object getSelectedItem() {
		return CategorieSelectionner;
	}

	@Override
	public void setSelectedItem(Object item) {
		this.CategorieSelectionner = (Categorie) item;
		
	}
}
