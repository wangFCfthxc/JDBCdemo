package demo;

//STEP 1. Import required packages
import java.sql.*;

public class panda03 {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost/";
	static final String DATABASE = "EMP";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(URL + DATABASE, USER, PASS);

			// STEP 4: Execute a query to create statment with
			// required arguments for RS example.
			System.out.println("Creating statement...");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			// Move cursor to the last row.
			System.out.println("Moving cursor to the last...");
			rs.last();

			// STEP 5: Extract data from result set
			System.out.println("Displaying record...");
			// Retrieve by column name
			int id = rs.getInt("id");
			int age = rs.getInt("age");
			String first = rs.getString("first");
			String last = rs.getString("last");

			// Display values
			System.out.println("ID: " + id + ", Age: " + age + ", First: " + first + ", Last: " + last);

			// Move cursor to the first row.
			System.out.println("Moving cursor to the first row...");
			rs.first();

			// STEP 6: Extract data from result set
			System.out.println("Displaying record...");
			// Retrieve by column name
			id = rs.getInt("id");
			age = rs.getInt("age");
			first = rs.getString("first");
			last = rs.getString("last");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", Age: " + age);
			System.out.print(", First: " + first);
			System.out.println(", Last: " + last);
			// Move cursor to the first row.

			System.out.println("Moving cursor to the next row...");
			rs.next();

			// STEP 7: Extract data from result set
			System.out.println("Displaying record...");
			id = rs.getInt("id");
			age = rs.getInt("age");
			first = rs.getString("first");
			last = rs.getString("last");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", Age: " + age);
			System.out.print(", First: " + first);
			System.out.println(", Last: " + last);

			// STEP 8: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			System.out.println(se.toString());
		} catch (Exception e) {
			// Handle errors for Class.forName
			System.out.println(e.toString());
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				System.out.println(se.toString());
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end main
}// end panda03
