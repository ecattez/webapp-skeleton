package fr.lordrski.entity.fictables;

import java.util.List;

/**
 * Une Fictable est une table fictive qui peut être créé et géré par le client.
 * Au lieu d'enregistrer cette table en base de données, on l'enregistre au format json.
 */
public class Fictable implements Cloneable {
	
	/**
	 * Méthode permettant de normaliser le nom du fichier passé en paramètre, associé à une fictable
	 * @param fileName le nom du fichier à normaliser
	 * @return le nom du fichier normalisé
	 */
	public static String normalize(String fileName) {
		fileName = fileName.trim().replaceAll(" ", "_");
		return fileName.endsWith(".json") ? fileName : fileName + ".json";
	}
	
	private String fileName;
	private String displayName;
	private List<FictableInfos> infos;
	private List<FictableEntry> entries;
	
	public Fictable() {}
	
	public Fictable(String fileName, String displayName) {
		this(fileName, displayName, null, null);
	}
	
	public Fictable(String fileName, String displayName, List<FictableInfos> infos, List<FictableEntry> entries) {
		this.fileName = fileName;
		this.displayName = displayName;
		this.infos = infos;
		this.entries = entries;
	}
	
	/**
	 * Récupère le nom du fichier associé à la fictable
	 * @return le nom du fichier de la fictable
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Saisi le nom du fichier associé à la fictable
	 * @param fileName le nouveau nom du fichier de la fictable
	 */
	public void setFileName(String fileName) {
		this.fileName = normalize(fileName);
	}
	
	/**
	 * Récupère le nom de la fictable
	 * @return le nom de la fictable
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * Saisi le nom de la fictable
	 * @param displayName le nouveau nom de la fictable
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * Récupère les informations des colonnes de la fictable
	 * @return les informations des colonnes de la fictable
	 */
	public List<FictableInfos> getInfos() {
		return infos;
	}
	
	/**
	 * Saisi les informations des colonnes de la fictable
	 * @param infos les nouvelles informations des colonnes de la fictable
	 */
	public void setInfos(List<FictableInfos> infos) {
		this.infos = infos;
	}
	
	/**
	 * Récupère les lignes de la fictable
	 * @return les entrées de la fictable
	 */
	public List<FictableEntry> getEntries() {
		return entries;
	}
	
	/**
	 * Saisi les lignes de la fictable
	 * @param entries les nouvelles entrées de la fictable
	 */
	public void setEntries(List<FictableEntry> entries) {
		this.entries = entries;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Fictable) && fileName.equals(((Fictable)o).fileName);
	}
	
	@Override
	public int hashCode() {
		return fileName == null ? 0 : fileName.hashCode();
	}
	
	@Override
	public String toString() {
		return "Fictable [name=" + fileName + ", displayName=" + displayName + ", infos=" + infos + ", entries=" + entries + "]";
	}
	
	@Override
	public Fictable clone() {
		return new Fictable(fileName, displayName, infos, entries);
	}

}