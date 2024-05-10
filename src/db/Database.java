package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {
	private static Connection con;
	private static Statement st;

	public static Connection getConnection() {
		if (con == null) {
			try {
				Properties props = loadProps();
				con = DriverManager.getConnection(props.getProperty("url"), props);
			} catch (SQLException e) {
				throw new DatabaseException("Cannot instance a connection: " + e.getMessage());
			}
		}
		return con;
	}

	public static Statement createStatement() {
		try {
			if (st != null && !st.isClosed())
				throw new DatabaseException("Statement is open!");
			st = con.createStatement();
		} catch (SQLException e) {
			throw new DatabaseException("Cannot get statement: " + e.getMessage());
		}
		return st;
	}

	public static void closeStatement() {
		try {
			st.close();
		} catch (SQLException e) {
			throw new DatabaseException("Cannot close statement: " + e.getMessage());
		}
	}

	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			throw new DatabaseException("Cannot close connection: " + e.getMessage());
		}
	}

	public static void closeAll() {
		closeConnection();
		closeStatement();
	}

	private static Properties loadProps() {
		try (FileInputStream fs = new FileInputStream("db.props")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (FileNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
