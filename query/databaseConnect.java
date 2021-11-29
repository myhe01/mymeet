/*
 * This class establishes a connection with the database
 */

import java.sql.*;

public class databaseConnect {
	public String dbUrl = ""; // database URL
    public String dbName = "root"; // name of database
    public String dbPassword = ""; // password to database (often left either blank or root)
    public String dbDriver = ""; // LDBC "com.mysql.jdbc.Driver

    public void loadDriver(String dbDriver)
    {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection()
    {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbName, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
