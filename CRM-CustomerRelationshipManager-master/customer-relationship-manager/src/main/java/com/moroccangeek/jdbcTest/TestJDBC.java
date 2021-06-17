package com.moroccangeek.jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
		String user = "springstudent";
		String pass = "springstudent";
		
		try {
			
			System.out.println("Connection to database" + jdbcUrl);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);
			if(myConn != null)
				System.out.println("Connection successful");
			else
				System.out.println("Connection failed");
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}

}
