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
package fr.lordrski.dao.impl;

import java.sql.SQLException;
import java.util.UUID;

import com.j256.ormlite.support.ConnectionSource;

import fr.lordrski.dao.SecurIDDao;
import fr.lordrski.entity.security.SecurID;

/**
 * Implémentation JDBC de l'interface SecurIDDao.
 */
public class SecurIDDaoImpl extends AbstractDaoImpl<SecurID, UUID> implements SecurIDDao {

	public SecurIDDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, SecurID.class);
	}

	@Override
	public SecurID findByTokenId(UUID tokenId) throws SQLException {
		return this.queryBuilder().where().eq("token_id", tokenId).queryForFirst();
	}
	
}
