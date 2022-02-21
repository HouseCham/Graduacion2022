package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	public DatabaseConnection(){
        
    }
	
	public static Connection conectar() {
		try {
			String url = "jdbc:mysql://remotemysql.com:3306/l8AegdMAQ2";
			String user = "l8AegdMAQ2";
			String password = "FJDZedNViq";
			
			Connection cn = DriverManager.getConnection(url, user, password);
			return cn;
			
		} catch(Exception e) {
			System.out.println("Error: " + e);
		}
		return (null);
	}
}
