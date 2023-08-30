package main;

/*** @author DAthCore */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/* JDBC  IT'S A API TO CONNECT AND EXECUTE QUERY WITH DATABASE
 * JDBC API USES JDBC DRIVERS TO CONNECT WITH DATABASE 
 * 
 * 													  --> JDBC DRIVER --> ORACLE	
 * 													/
 * JAVA APP --> JDBC API --> JDBC DRIVER MANAGER --
 * 													\ 
 * 													  --> JDBC DRIVER --> MYSQL
 * 		
 * 
 * 
 * 
 Reset MySQL Root Password and Reinstall MySQL:
  	Stop the MySQL server using the command: sudo service mysql stop
	Start MySQL without a password: sudo mysqld_safe –skip-grant-tables &
	Connect to MySQL as the root user: mysql -u root
	At the MySQL prompt, switch to the mysql database: use mysql;
	Reset the password for the root user: UPDATE user SET authentication-string= PASSWORD(‘new_password’) WHERE User=’root’;
	Flush the privileges to save the changes: FLUSH PRIVILEGES;
	Exit the MySQL prompt: exit;
	Stop and start MySQL normally: sudo service mysql stop and then sudo service mysql start
	Log in to the account of MySQL as the “root” user using the new password: mysql -u root -p
	You should now be able to access MySQL with the new password.
 * 
 * 											
 * */

public class App {
	//private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; 
	//private static final String DATABASE_URL = "jdbc:mysql://localhost/"; /* * THIS IS WHEN WE WILL CREATE THE DATABASE */
	private static final String DATABASE_URL = "jdbc:mysql://localhost/javatest_db"; /* ** THIS IS WHEN WE ARE GOING TO CREATE A DATABASE TABLE*/
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public static void main (String[] args) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			
			connection = DriverManager.getConnection(
					DATABASE_URL,
					USERNAME,
					PASSWORD
					);
			statement = connection.createStatement();
			
			//String sql = "CREATE DATABASE JavaTest_DB"; /* * THIS IS WHEN WE WILL CREATE THE DATABASE */
			String sql = "CREATE TABLE Students (id INTEGER NOT NULL,"
					+ "FirstName VARCHAR (255),"
					+ "LastName VARCHAR (255),"
					+ "Email VARCHAR (255),"
					+ "Age INTEGER,"
					+ "PRIMARY KEY (id))"; /* ** THIS IS WHEN WE ARE GOING TO CREATE A DATABASE TABLE*/
			
			statement.executeUpdate(sql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if ( connection != null ) 
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			if (statement != null ) 
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
