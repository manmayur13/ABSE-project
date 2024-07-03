package com.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static String path="";
	static{
		File file = new File("");
		path = file.getAbsolutePath();
	}
	public static Connection getConnection()

	{
		Connection con = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attributebased", "root", "sql@123");
			return con;
		}
		catch (Exception e) 
		{
			System.out.println("Exception is " + e);

		}
		return con;
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub  
		
		

	}

}
