package com.source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.util.DbConnection;

public class GlobalFunction {
	
	
	public String getFname(String email)
	{
		String fname = "";
		try 
		{
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user where u_id='"+ email+ "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				fname = rs.getString("fname");

			}
			return fname;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception " + e);

		}
		return fname;

	}

	public String getLname(String email) {
		String lname = "";
		try 
		{
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user where u_id='"+ email + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				lname = rs.getString("lname");

			}
			return lname;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception " + e);

		}
		return lname;
	}
	
	
	public String getEUserFname(String email)
	{
		String fname = "";
		try 
		{
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user where email='"+ email+ "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				fname = rs.getString("fname");

			}
			return fname;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception " + e);

		}
		return fname;

	}
	public String getEmail(String email)
	{
		String fname = "";
		try 
		{
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user where u_id='"+ email+ "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				fname = rs.getString("email");

			}
			return fname;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception " + e);

		}
		return fname;

	}

	public String getEUserLname(String email) {
		String lname = "";
		try 
		{
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user where email='"+ email + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				lname = rs.getString("lname");

			}
			return lname;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception " + e);

		}
		return lname;
	}
	public String getCurrentUPrv(String userid) 
	{
		String c_email=userid;
		String comb="";
		
		try{
			
			Connection con=DbConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user where u_id='"+c_email+"'");
			ResultSet rs=ps.executeQuery();
			
			 
			int i=0;
			String prv1="",prv2="",prv3="";
			ArrayList<String> aa=new ArrayList<String>();
			if(rs.next())
			{
				if((!rs.getString("prv1").isEmpty()))
				{
					prv1=rs.getString("prv1");
					aa.add(prv1);
				}
				if((!rs.getString("prv2").isEmpty()))
				{
					prv2=rs.getString("prv2");
					aa.add(prv2);
				}
				if((!rs.getString("prv3").isEmpty()))
				{
					prv3=rs.getString("prv3");
					aa.add(prv3);
				}
			
			}
			
			int ln=aa.size();
			System.out.println("ln  "+ln);
			if(ln==3)
			{
				comb=aa.get(0)+" AND "+aa.get(1)+" AND "+aa.get(2);
			}
			if(ln==2)
			{
				comb=aa.get(0)+" AND "+aa.get(1);
			}
			if(ln==1)
			{
				comb=aa.get(0);
			}
			
			System.out.println(" Combo is "+comb);
			
		}
		catch(Exception e)
		{
			System.out.println(" Exception "+e);
		}
		
		return comb;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
