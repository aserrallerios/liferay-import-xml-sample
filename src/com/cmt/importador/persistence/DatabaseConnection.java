package com.cmt.importador.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.cmt.importador.ImportadorIntranet;

/**
 * PTLDatabaseConnection.java
 * 
 * @brief Clase de soporte para gestionar las conexiones JDBC de la
 *        aplicaci�n.
 */
public class DatabaseConnection {

	private static Connection con;

	/**
	 * Este m�todo recupera una conexion de B.D a partir del nombre JNDI con
	 * el que se mapea en el sistema.
	 * 
	 * @return Objeto de tipo java.sql.Connection.
	 * @throws SQLException
	 * @throws PTLException
	 *             En caso de ocurrir alg�n error.
	 */
	public static Connection getConnection() throws SQLException {

		if (con != null) {
			return con;
		} else {
			java.sql.DriverManager
					.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = java.sql.DriverManager.getConnection(
					ImportadorIntranet.CONFIGURATION.getString("jdbc.conex"),
					ImportadorIntranet.CONFIGURATION.getString("jdbc.user"),
					ImportadorIntranet.CONFIGURATION.getString("jdbc.pass"));
			return con;
		}
	}

}