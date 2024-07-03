package com.source;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DbConnection;

/**
 * Servlet implementation class admin
 */
@WebServlet("/ca")
public class ca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		try{
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("update admin set status=1 where a_id=?");
			ps.setString(1, id);
			ps.executeUpdate();
			response.sendRedirect("adactivation.jsp?act");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username= request.getParameter("username");
		String pwd = request.getParameter("password");
		
		if(username.equals("ca") && pwd.equals("ca"))
		{
			response.sendRedirect("cahome.jsp");
		}
		else
		{
			response.sendRedirect("ca.jsp?fail");
		}
	}

}
