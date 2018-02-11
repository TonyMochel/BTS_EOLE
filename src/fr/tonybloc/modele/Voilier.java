package fr.tonybloc.modele;

/**
 * Object Voilier
 * @author Tony
 *
 */
public class Voilier {
	
	/**
	 * Identifiant du voilier
	 */
	private int id;
	/**
	 * Catégorie du voilier
	 */
	private int categorie;
	/**
	 * Nom du voilier
	 */
	private String nomVoilier;
	/**
	 * Nom du skippeur (Capitaine)
	 */
	private String nomSkippeur;
	/**
	 * Rating du voilier
	 */
	private int rating;
	
	
	/**
	 * Crée une instance de la classe 'Voilier' : vide
	 */
	public Voilier() {}
	/**
	 * Crée une instance de la classe 'Voilier'
	 * @param categorie
	 * @param nomVoilier
	 * @param nomSkippeur
	 * @param rating
	 */
	public Voilier(int categorie, String nomVoilier, String nomSkippeur, int rating) {
		this.id = 0;
		this.categorie = categorie;
		this.nomVoilier = nomVoilier;
		this.nomSkippeur = nomSkippeur;
		this.rating = rating;
	}
	/**
	 * Crée une instance de la classe 'Voilier'
	 * @param id
	 * @param categorie
	 * @param nomVoilier
	 * @param nomSkippeur
	 * @param rating
	 */
	public Voilier(int id, int categorie, String nomVoilier, String nomSkippeur, int rating) {
		this.id = id;
		this.categorie = categorie;
		this.nomVoilier = nomVoilier;
		this.nomSkippeur = nomSkippeur;
		this.rating = rating;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	public String getNomVoilier() {
		return nomVoilier;
	}

	public void setNomVoilier(String nomVoilier) {
		this.nomVoilier = nomVoilier;
	}

	public String getNomSkippeur() {
		return nomSkippeur;
	}

	public void setNomSkippeur(String nomSkippeur) {
		this.nomSkippeur = nomSkippeur;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Description de l'objet Voilier
	 */
	@Override
	public String toString() {
		return "{ "
				+ "'id' => " + this.id + ", "
				+ "'categorie' => " + this.categorie + ", "
				+ "'nomVoilier' => " + this.nomVoilier + ", "
				+ "'nomSkippeur' => " + this.nomSkippeur + ", "
				+ "'rating' => " + this.rating
				+ "}";
		
	}
}
