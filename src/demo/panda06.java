package demo;

//STEP 1. Import required packages
import java.sql.*;

public class panda06 {

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

			// STEP 4: Set auto commit as false.
			conn.setAutoCommit(false);

			// STEP 5: Execute a query to create statment with
			// required arguments for RS example.
			System.out.println("Creating statement...");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// STEP 6: INSERT a row into Employees table
			System.out.println("Inserting one row....");
			String SQL = "INSERT INTO Employees VALUES (106, 20, 'Rita', 'Tez')";
			stmt.executeUpdate(SQL);

			// STEP 7: INSERT one more row into Employees table
			SQL = "INSERT INTO Employees VALUES (107, 22, 'Sita', 'Singh')";
			stmt.executeUpdate(SQL);

			// STEP 8: Commit data here.
			System.out.println("Commiting data here....");
			conn.commit();

			// STEP 9: Now list all the available records.
			String sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("List result set for reference....");
			printRs(rs);

			// STEP 10: Clean-up environment
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
				;
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end main

	public static void printRs(ResultSet rs) throws SQLException {
		// Ensure we start with first row
		rs.beforeFirst();
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("id");
			int age = rs.getInt("age");
			String first = rs.getString("first");
			String last = rs.getString("last");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", Age: " + age);
			System.out.print(", First: " + first);
			System.out.println(", Last: " + last);
		}
		System.out.println();
	}// end printRs(
}// end panda06
