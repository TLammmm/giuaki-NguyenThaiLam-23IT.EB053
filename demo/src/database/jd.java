package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class jd {
	
	
	public static Connection getConnection() {
		Connection c=null;
		
		try {
			String url="jdbc:mysql://localhost:3306/lam";
			String username="root";
			String password="";	
			c=DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return c;
	}
	public static void closeConnection(Connection c) {
		try {
			if(c!=null) {
				c.close();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
