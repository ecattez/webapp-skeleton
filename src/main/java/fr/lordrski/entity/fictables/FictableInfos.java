package fr.lordrski.entity.fictables;

/**
 * FictableInfos représente les informations d'une colonne d'une {@link fr.lordrski.entity.fictables.Fictable}.
 */
public class FictableInfos {
	
	private String name;
	private String type;
	private int length;
	
	public FictableInfos() {}
	
	public FictableInfos(String name, String type, int length) {
		this.name = name;
		this.type = type;
		this.length = length;
	}
	
	/**
	 * Récupère le nom de la colonne
	 * @return le nom de la colonne
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Saisi le nom de la colonne
	 * @param name le nouveau nom de la colonne
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Récupère le type (int, string...) de la colonne
	 * @return le type de la colonne
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Saisi le type (int, string...) de la colonne
	 * @param type le nouveau type de la colonne
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Récupère la taille (nb de caractères) de la colonne
	 * @return la taille de la colonne
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Saisi la taille (nb de caractères) de la colonne
	 * @param length la nouvelle taille de la colonne
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Fictable) && name.equals(((FictableInfos)o).name);
	}
	
	@Override
	public int hashCode() {
		return name == null ? 0 : name.hashCode();
	}
	
	@Override
	public String toString() {
		return "[" + name + ", " + type + ", " + length + "]";
	}

}
