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
package fr.lordrski.mvc;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import fr.lordrski.dao.SecurIDDao;
import fr.lordrski.entity.security.SecurID;
import fr.lordrski.util.DaoProvider;

/**
 * Filtre pour la sécurité des requêtes.
 */
@Provider
@Authenticated
public class AuthenticationFilter implements ContainerRequestFilter, ContainerResponseFilter {
	
	private static final long NEXT_EXPIRATION_MILLIS = 600000L;
	private static final String CLIENT_TOKEN_KEY = "authenticationToken";
	
	private SecurIDDao securIdDao = DaoProvider.getDao(SecurID.class);

	/* (non-Javadoc)
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		UUID tokenId = null;
		SecurID securId = null;
		final String token = requestContext.getHeaderString(CLIENT_TOKEN_KEY);
		final ZonedDateTime zonedDateTime = ZonedDateTime.now();
		final Instant instantNow = zonedDateTime.toInstant();
		try {
			tokenId = UUID.fromString(token);
			securId = securIdDao.findByTokenId(tokenId);
			if (securId == null) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}
			else if (securId.getExpirationDate().toInstant().isBefore(instantNow)) {
				tokenId = UUID.randomUUID();
				securId.setTokenId(tokenId);
				securId.setExpirationDate(Date.from(instantNow.plusMillis(NEXT_EXPIRATION_MILLIS)));
				securIdDao.update(securId);
			}
			requestContext.getHeaders().add(CLIENT_TOKEN_KEY, tokenId.toString());
		} catch(IllegalArgumentException e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		} catch(SQLException e) {
			requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.container.ContainerResponseFilter#filter(javax.ws.rs.container.ContainerRequestContext, javax.ws.rs.container.ContainerResponseContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add(CLIENT_TOKEN_KEY, requestContext.getHeaderString(CLIENT_TOKEN_KEY));
	}

}
