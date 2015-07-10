/**
 * This file is part of webapp-skeleton.
 *
 * webapp-skeleton is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * webapp-skeleton is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with webapp-skeleton.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
package fr.lordrski.dao;

import java.io.Serializable;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.Transaction;


/**
 * Implémentation du pattern DAO.
 *
 * @param <PK> Le type de la clé primaire de la table
 * @param <E> L'objet associé à une table en base de données
 */
public interface DAO<PK extends Serializable, E> {
	
	/**
	 * Trouve l'objet stocké en base de données via sa clé primaire
	 * 
	 * @param pk la clé primaire
	 * @return l'objet en base de données
	 */
	public E find(PK pk);
	
	/**
	 * Trouve tous les objets stockés en base de données
	 * 
	 * @return tous les objets de la table voulue
	 */
	public List<E> getAll();
	
	/**
	 * Insert l'objet en base de données
	 * 
	 * @param e l'objet à insérer en base de données
	 * @return l'objet inséré en base de données
	 */
	@Transaction
	public int insert(@BindBean E e);
	
	/**
	 * Supprime l'objet en base de données
	 * 
	 * @param e l'objet à supprimer de la base de données
	 * @return l'objet supprimé de la base de données
	 */
	@Transaction
	public int delete(PK pk);
	
	/**
	 * Met à jour l'objet en base de données
	 * 
	 * @param pk la clé primaire de l'objet à mettre à jour
	 * @param e l'objet à mettre à jour
	 * @return l'objet mis à jour en base de données
	 */
	@Transaction
	public int update(@BindBean E e);
	
	/**
	 * Détecte si un objet est présent en base de données
	 * 
	 * @param pk la clé primaire de l'objet à trouver
	 * @return vrai si l'objet a été trouvé
	 */
	public boolean exists(PK pk);
	
	/**
	 * Compte le nombre d'objets présents en base de données
	 * 
	 * @return le nombre d'objets dans la table voulue
	 */
	public int count();
	
	/**
	 * Utilisé pour fermer la connexion à la base de données
	 */
	public void close();

}
