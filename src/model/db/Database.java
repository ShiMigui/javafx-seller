package model.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {
	private static Connection con;

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

	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			throw new DatabaseException("Cannot close connection: " + e.getMessage());
		}
	}

	public static void close(Statement st, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (st != null && !st.isClosed()) {
				st.close();
			}
		} catch (SQLException e) {
			throw new DatabaseException("Cannot close: " + e.getMessage());
		}
	}
}
