package com.cmt.importador.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.cmt.importador.ImportadorIntranet;

/**
 * PTLManager.java
 * 
 * @brief Super clase de la que extienden el resto de managers de la aplicacion.
 */

public class Manager {

	protected static Connection con = null;
	private static Logger log = Logger.getLogger(Manager.class);
	static Statement smt = null;

	static Statement smtg = null;
	static Statement smtn = null;

	/**
	 * @brief Método que finaliza la conexión con la base de datos
	 * @throws PTLException
	 *             Si se produce un error al cerrar la conexión
	 */
	protected static void closeConnection() {
		// Cerramos la conexión con la BD
		if (con != null) {
			try {
				if (!con.isClosed()) {
					// Cerramos el PreparedStatement
					if (log.isDebugEnabled())
						log.debug("Cerrando conexión con la BD...");
					con.close();
				}
			} catch (SQLException se) {
				log.error("Se ha producido un SQL ERROR al intentar cerrar la conexión con BD.");
				log.error(se.getMessage());

			} catch (RuntimeException re) {
				log.error("Se ha producido un RUNTIME ERROR al intentar cerrar la conexión con BD.");
				log.error(re.getMessage());
			}
		}
	}

	/**
	 * Esta llamada cierra el objeto preparedStatement
	 * 
	 * @throws SQLException
	 * 
	 * @throws PTLException
	 */
	public static void closeStatement() throws SQLException {
		if (smt != null) {
			smt.close();
		}
		if (smtg != null) {
			smtg.close();
		}
		if (smtn != null) {
			smtn.close();
		}
		closeConnection();
	}

	public static ResultSet executeSelectGetArticles() {

		String q = null;
		try {
			con = getConnection();
			q = ImportadorIntranet.CONFIGURATION.getString("intranet.query");
			smt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (log.isDebugEnabled())
				log.debug("Ejecutando Select: " + q);
			ResultSet result = smt.executeQuery(q);
			return result;
		} catch (SQLException se) {
			log.error("Se ha producido un error al ejecutar la SELECT.");
			log.error("NOMBRE: " + q);
			log.error("SELECT: " + q);
			log.error(se.getMessage());

		}
		return null;
	}

	public static ResultSet executeSelectGetGlosario() {

		String q = null;
		try {
			con = getConnection();
			q = ImportadorIntranet.CONFIGURATION.getString("intranet.query.glosary");
			smtg = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (log.isDebugEnabled())
				log.debug("Ejecutando Select: " + q);
			ResultSet result = smtg.executeQuery(q);
			return result;
		} catch (SQLException se) {
			log.error("Se ha producido un error al ejecutar la SELECT.");
			log.error("NOMBRE: " + q);
			log.error("SELECT: " + q);
			log.error(se.getMessage());

		}
		return null;
	}

	public static ResultSet executeSelectGetNovedades() {

		String q = null;
		try {
			con = getConnection();
			q = ImportadorIntranet.CONFIGURATION.getString("intranet.query.novedades");
			smtn = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (log.isDebugEnabled())
				log.debug("Ejecutando Select: " + q);
			ResultSet result = smtn.executeQuery(q);
			return result;
		} catch (SQLException se) {
			log.error("Se ha producido un error al ejecutar la SELECT.");
			log.error("NOMBRE: " + q);
			log.error("SELECT: " + q);
			log.error(se.getMessage());

		}
		return null;
	}

	/**
	 * @param con
	 * @throws SQLException
	 * @brief Método que inicializa la conexión a la base de datos
	 * @throws PTLException
	 *             Si se produce un error al abrir la conexión
	 */
	protected static Connection getConnection() throws SQLException {
		con = DatabaseConnection.getConnection();
		return con;
	}
}