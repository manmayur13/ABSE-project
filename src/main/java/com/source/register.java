package com.source;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DbConnection;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement ps;
	ResultSet rs;

	
	public void init(ServletConfig config) throws ServletException 
	{
		try {

			con = DbConnection.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname=request.getParameter("fname");
		String mname=request.getParameter("mname");
		String lname=request.getParameter("lname");
		String dob=request.getParameter("dob");
		String gender=request.getParameter("gender");
		String privileges=request.getParameter("privileges");
		String email=request.getParameter("email");
		String mobno=request.getParameter("mobno");
		
		
		
		String fprv="",sprv="",tprv="";
		
		String[] results = request.getParameterValues("chk");
		
		
		for (int i = 0; i < results.length; i++) 
		{
			
			if(i==0)
			{
				fprv=results[i];
			}
			if(i==1)
			{
				sprv=results[i];
			}
			if(i==2)
			{
				tprv=results[i];
			}
		}
		
		System.out.println("1 "+fprv);
		System.out.println("2 "+sprv);
		System.out.println("3 "+tprv);
		

		
		String pwd=request.getParameter("pwd");
		String reg_date=request.getParameter("reg_date");
		
		
		
		try
		{
			PreparedStatement ps1 = con.prepareStatement("select * from user where email='"+email+"'");
			ResultSet rs =ps1.executeQuery();
			if(!rs.next())
			{
				ps=con.prepareStatement("INSERT INTO `user` (`u_id`, `fname`, `mname`, `lname`, `gender`, `dob`, `prv1`,`prv2`,`prv3`, `reg_date`, `email`,`mobno`, `pwd`) VALUES (NULL, '"+fname+"','"+mname+"', '"+lname+"', '"+gender+"','"+dob+"', '"+fprv+"','"+sprv+"','"+tprv+"', '"+reg_date+"', '"+email+"','"+mobno+"', '"+pwd+"');");
						
				int result=ps.executeUpdate();
				if(result>0)
				{
					System.out.println("Registration Successfully");
					response.sendRedirect("user.jsp?reg=done");
				}
				else 
				{
					System.out.println("Registration Fail");
					response.sendRedirect("register.jsp?reg=fail");
				}
			}
			else{
				System.out.println("Allready Registration Done");
				response.sendRedirect("register.jsp?already=done");
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception Occure:- "+e);
		}
		

	
	}

}
