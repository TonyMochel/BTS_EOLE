package fr.tonybloc.modele;

/**
 * Objet Categorie;
 * @author Tony
 *
 */
public class Categorie {
	
	/**
	 * Identifiant de la catégorie
	 */
	private int id;
	/**
	 * Libeller de la catégorie
	 */
	private String libeller;
	
	
	/**
	 * Crée une instance de la classe 'Catégorie' : vide
	 */
	public Categorie() {}
	
	/**
	 * Crée une instance de la classe 'Categorie'
	 * @param libeller
	 */
	public Categorie(String libeller) {
		this.id = 0;
		this.libeller = libeller;
	}
	/**
	 * Crée une instance de la classe 'Catégorie'
	 * @param id
	 * @param libeller
	 */
	public Categorie(int id, String libeller) {
		this.id = id;
		this.libeller = libeller;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibeller() {
		return libeller;
	}
	public void setLibeller(String libeller) {
		this.libeller = libeller;
	}
	
	/**
	 * Description de l'objet Voilier
	 */
	@Override
	public String toString() {
		return "{ "
				+ "'id' => " + this.id + ", "
				+ "'libeller' => " + this.libeller + ", "
				+ "}";
		
	}
	
}
