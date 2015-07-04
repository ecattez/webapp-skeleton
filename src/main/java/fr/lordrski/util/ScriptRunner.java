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
 * Tool to run database scripts based on com.ibatis.common.jdbc.ScriptRunner class
 * from the iBATIS Apache project.
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
	 * Runs an SQL script (read in using the Reader parameter)
	 * 
	 * @param reader
	 *            - the source of the script
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
	 * Runs an SQL script (read in using the Reader parameter) using the
	 * connection passed in
	 * 
	 * @param conn
	 *            - the connection to use for the script
	 * @param reader
	 *            - the source of the script
	 * @throws SQLException
	 *             if any SQL errors occur
	 * @throws IOException
	 *             if there is an error reading from the Reader
	 */
	private void runScript(Connection conn, Reader reader) throws IOException, SQLException {
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
					statement = conn.createStatement();

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

					if (autoCommit && !conn.getAutoCommit()) {
						conn.commit();
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
				conn.commit();
			}
		} catch (SQLException | IOException e) {
			e.fillInStackTrace();
			error("Error executing: " + command);
			error(e);
			throw e;
		} finally {
			conn.rollback();
		}
	}
	
	private void info(Object o) {
		log.info(o.toString());
	}
	
	private void error(Object o) {
		log.severe(o.toString());
	}
}
