package fr.lordrski.dao;

/**
 * Implémentation du pattern DAO
 * 
 * @author Edouard CATTEZ (la7production)
 *
 * @param <E> L'objet associé à une table en base de données
 * @param <PK> Le type de la clé primaire de la table
 */
public interface DAO<E, PK> {
	
	/**
	 * Trouve l'objet stocké en base de données via sa clé primaire
	 * @param pk la clé primaire
	 * @return l'objet en base de données
	 */
	public E find(PK pk);
	
	/**
	 * Insert l'objet en base de données
	 * @param e l'objet à insérer en base de données
	 * @return l'objet inséré en base de données
	 */
	public E insert(E e);
	
	/**
	 * Supprime l'objet en base de données
	 * @param e l'objet à supprimer de la base de données
	 * @return l'objet supprimé de la base de données
	 */
	public E delete(E e);
	
	/**
	 * Met à jour l'objet en base de données
	 * @param pk la clé primaire de l'objet à mettre à jour
	 * @param e l'objet à mettre à jour
	 * @return l'objet mis à jour en base de données
	 */
	public E update(PK pk, E e);

}
