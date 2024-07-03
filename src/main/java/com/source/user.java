package com.source;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DbConnection;

/**
 * Servlet implementation class user
 */
@WebServlet("/user")
public class user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con = null;
	PreparedStatement ps;
	ResultSet rs;

	public void init(ServletConfig config) throws ServletException {
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
		String email= request.getParameter("email");
		String pwd = request.getParameter("pwd");
		HttpSession session = request.getSession();
		
		PreparedStatement ps1;
		try {
			ps1 = con.prepareStatement("select * from user where email='"+ email + "' AND pwd='" + pwd + "' ");
			ResultSet rs = ps1.executeQuery();
			String fname="",lname="";
			if (rs.next()) {
				String status = rs.getString("status");
				if(status.equals("1")){
					
				
				GlobalFunction gf=new GlobalFunction();
				fname=gf.getEUserFname(email);
				lname=gf.getEUserLname(email);
				String privileges=gf.getCurrentUPrv(rs.getString("u_id"));
				System.out.println("C user prv "+privileges);
				session.setAttribute("privileges", privileges);
				session.setAttribute("fname", fname);
				session.setAttribute("lname", lname);
				session.setAttribute("email", email);
				session.setAttribute("userid", rs.getString("u_id"));
				System.out.println("Login Done");
				response.sendRedirect("home.jsp?login=done");
				}else
				{
					response.sendRedirect("user.jsp?activate");
				}

			} else {
				System.out.println("Login fail");
				response.sendRedirect("user.jsp?login=fail");
			}
		} catch (SQLException e) {
			System.out.println("Exception Occure :- " + e);
		}

	}

}
