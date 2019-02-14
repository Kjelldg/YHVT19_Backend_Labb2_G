package com.denti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

	static private PreparedStatement preparedStatement;

	Connection dbConnection;

	// Used for the MySQL JDBC.
	protected String dbHost = "localhost";
	protected String dbPort = "3306";
	protected String dbUser = "root";
	protected String dbPass = "MySQL1243!";
	protected String dbName = "database_labb2";
	protected String jdbc = "jdbc:mysql://";
	protected String jdbcURL = ":3306/database_labb2?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

	// protected String jdbcURL =
	// ":3306/database_labb2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT";

	/*
	 * This method writes to the database using the input from the params in the
	 * URL.
	 */
	public void writeToSQL_URL(String inputName, String inputProfession) throws SQLException, ClassNotFoundException {

		String connectionString = jdbc + dbHost + jdbcURL;
		Class.forName("com.mysql.cj.jdbc.Driver");
		dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

		String query = "INSERT INTO users(name, profession)" + "VALUES(?,?)";

		preparedStatement = dbConnection.prepareStatement(query);

		preparedStatement.setString(1, inputName);
		preparedStatement.setString(2, inputProfession);
		preparedStatement.executeUpdate();

	}

	/*
	 * This method retrieves the users from the database and sends them to the user.
	 */
	public List<User> retrieveFromSQL() throws SQLException, ClassNotFoundException {

		ArrayList<User> usersFromSQL = new ArrayList<>();

		String connectionString = jdbc + dbHost + jdbcURL;
		Class.forName("com.mysql.cj.jdbc.Driver");
		dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

		String query = "SELECT * FROM users";

		ResultSet queryResultSet = preparedStatement.executeQuery(query);

		while (queryResultSet.next()) {
			int id = queryResultSet.getInt(1);
			String name = queryResultSet.getString(2);
			String profession = queryResultSet.getString(3);
			usersFromSQL.add(new User(id, name, profession));
		}

		return usersFromSQL;

	}

}