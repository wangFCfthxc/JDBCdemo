package demo;

//STEP 1. Import required packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class panda01 {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final static String URL = "jdbc:mysql://localhost/";
	final static String DATABASE = "emp";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(URL + DATABASE, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			String sql = "UPDATE Employees set age=? WHERE id=?";
			stmt = conn.prepareStatement(sql);

			// Bind values into the parameters.
			stmt.setInt(1, 35); // This would set age
			stmt.setInt(2, 102); // This would set ID

			// Let us update age of the record with ID = 102;
			int rows = stmt.executeUpdate();
			System.out.println("Rows impacted : " + rows);

			// Let us select all the records and display them.
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
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
			// STEP 6: Clean-up environment
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
				se.printStackTrace();
			} // end finally try
		}//end try
		System.out.println("Goodbye!");
	}// end main
}// end panda01
