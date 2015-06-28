package fr.lordrski.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Tool to run database scripts based on com.ibatis.common.jdbc.ScriptRunner class
 * from the iBATIS Apache project.
 * 
 * @author Edouard CATTEZ (la7production)
 */
public class ScriptRunner {

	private static final String DEFAULT_DELIMITER = ";";
	private static final String LOG_FILENAME = "script.log";
	
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
				this.log.addHandler(new FileHandler(LOG_FILENAME));
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
					} catch (Exception e) {
						// Ignore to workaround a bug in Jakarta DBCP
					}
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
