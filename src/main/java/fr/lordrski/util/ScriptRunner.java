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
package fr.lordrski.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Outil basé sur la classe com.ibatis.common.jdbc.ScriptRunner du projet iBATIS Apache.
 * Il permet d'exécuter des scripts SQL.
 */
public class ScriptRunner {

	private static final String DEFAULT_DELIMITER = ";";
	
	private final Logger log = Logger.getLogger("SCRIPT RUNNER");

	private Connection connection;

	private boolean stopOnError;
	private boolean autoCommit;

	private String delimiter = DEFAULT_DELIMITER;
	private boolean fullLineDelimiter = false;

	/**
	 * Default constructor
	 */
	public ScriptRunner(Connection connection, boolean autoCommit, boolean stopOnError) {
		this.connection = connection;
		this.autoCommit = autoCommit;
		this.stopOnError = stopOnError;
	}

	public void setDelimiter(String delimiter, boolean fullLineDelimiter) {
		this.delimiter = delimiter;
		this.fullLineDelimiter = fullLineDelimiter;
	}

	/**
	 * Exécute un script SQL
	 * 
	 * @param reader the source of the script
	 */
	public void runScript(Reader reader) throws IOException, SQLException {
		try {
			boolean originalAutoCommit = connection.getAutoCommit();
			try {
				if (originalAutoCommit != this.autoCommit) {
					connection.setAutoCommit(this.autoCommit);
				}
				runScript(connection, reader);
			} finally {
				connection.setAutoCommit(originalAutoCommit);
			}
		} catch (IOException | SQLException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error running script.  Cause: " + e, e);
		}
	}

	/**
	 * Exécute un script SQL en utilisant la connexion passé en paramètre
	 * 
	 * @param con la connexion utilisée pour l'exécution du script
	 * @param reader la source du script
	 * @throws SQLException si une erreur SQL se produit
	 * @throws IOException si une erreur se produit pendant la lecture du Reader
	 */
	private void runScript(Connection con, Reader reader) throws IOException, SQLException {
		StringBuffer command = null;
		try {
			LineNumberReader lineReader = new LineNumberReader(reader);
			String line;
			String trimmedLine;
			Statement statement;
			command = new StringBuffer();
			
			while ((line = lineReader.readLine()) != null) {
				trimmedLine = line.trim();
				
				if (trimmedLine.length() < 1 || trimmedLine.startsWith("//") || trimmedLine.startsWith("--")) {
					continue;
				}
				
				if (!fullLineDelimiter && trimmedLine.endsWith(delimiter) || fullLineDelimiter && trimmedLine.equals(delimiter)) {
					command.append(line.substring(0, line.lastIndexOf(delimiter))).append(" ");
					statement = con.createStatement();

					info(command);

					if (stopOnError) {
						statement.execute(command.toString());
					} else {
						try {
							statement.execute(command.toString());
						} catch (SQLException e) {
							e.fillInStackTrace();
							error("Error executing: " + command);
							error(e);
						}
					}

					if (autoCommit && !con.getAutoCommit()) {
						con.commit();
					}
					
					command.setLength(0);
					try {
						statement.close();
					} catch (Exception e) {}
					Thread.yield();
				} else {
					command.append(line).append(" ");
				}
			}
			if (!autoCommit) {
				con.commit();
			}
		} catch (SQLException | IOException e) {
			e.fillInStackTrace();
			error("Error executing: " + command);
			error(e);
			throw e;
		} finally {
			con.rollback();
		}
	}
	
	private void info(Object o) {
		log.info(o.toString());
	}
	
	private void error(Object o) {
		log.severe(o.toString());
	}
}
