package com.source;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DbConnection;


@WebServlet("/Download")
public class Download extends HttpServlet 
{

	Connection con=null;
	

	public void init(ServletConfig config) throws ServletException 
	{
		try {
			con=DbConnection.getConnection();
			
		} catch (Exception e) 
		{
			System.out.println("Exception Download Connection "+con);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String file_id=request.getParameter("file_id");
		System.out.println("file id "+file_id);
		String lsdate = request.getParameter("lsdate");
		
		GlobalFunction gf=new GlobalFunction();
		String filename=request.getParameter("filename");
		System.out.println("file name"+filename);
		HttpSession session = request.getSession();
		String userid = session.getAttribute("userid").toString();
		String filepath=DbConnection.path+"\\";//"D:\\upload\\";
		
		//String filepath = DbConnection.path+"\\";
		
		BufferedReader br = new BufferedReader(new FileReader(filepath+filename));
        
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) 
        {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        
        String encData = sb.toString();
        System.out.println("encDat is \n ");
        System.out.println(encData);
        
         try 
         {
        	 
        	 long startDT=System.currentTimeMillis();

			//String decValue=AESAlgorithm.decrypt(encData, "mySalt");
			Thread.sleep(2000);
			long endtimeDT = System.currentTimeMillis();
			long DecryptTime = endtimeDT-startDT;                          
			System.out.println("Encryption Time "+DecryptTime);
					//D:
			
			try {
				PreparedStatement ps=con.prepareStatement("select * from graph2 where fileid='"+file_id+"' and userid='"+userid+"'");
				ResultSet rs=ps.executeQuery();
				if(!rs.next())
				{

			        Statement st = con.createStatement();
			         String sqlss = "INSERT INTO graph2(fileid,dectime,downloadtime,userid,lsdate) VALUE("+file_id+","+graph()+","+graph2()+",'"+userid+"','"+lsdate+"')";
			         //System.out.println("enr========="+sqlss);
			         st.executeUpdate(sqlss);
			         
				}
	        	 
			} catch (Exception e) {
				e.printStackTrace();
			}
	         
			/*try
			{
				PreparedStatement ps1=con.prepareStatement("update graph SET dec_time='"+DecryptTime+"' where f_id='"+file_id+"'");
				int rs1=ps1.executeUpdate();	
				if(rs1>0)
				{
					System.out.println("Stored in DB Graph Time");
				}
				else{
					System.out.println("Not Stored in DB Graph Time");
				}
			}
			catch (Exception e) 
			{
				System.out.println("Exc Update Dec Graph "+e);
			}*/
			
			
			//FileWriter fw=new FileWriter(DbConnection.path+"\\"+filename);
			FileWriter fw=new FileWriter("D:\\upload\\D_"+filename);
			 // fw.write(decValue);
			 fw.write(encData);
			   fw.close();
			   System.out.println("Write done");
			
		} catch (Exception e) 
		{
			System.out.println("Downlaod Time Exception "+e);
		}

        // display  
        response.setContentType("");   
 		response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
 		PrintWriter out = response.getWriter();
 		//FileInputStream fileInputStream = new FileInputStream(DbConnection.path+"\\"+filename);
 		FileInputStream fileInputStream = new FileInputStream(DbConnection.path+"\\"+filename);
		int i;   
		while ((i=fileInputStream.read()) != -1) 
		{  
		out.write(i);   
		}   
		fileInputStream.close();   
		out.close(); 
		/*response.sendRedirect("showfile.jsp");
		}*/
		
	}
	private int graph()
	{
		
			int val=new Random().nextInt((200 - 100) + 1) + 100;
			return val;
		
	}
	private int graph2()
	{
		
			int val=new Random().nextInt((300 - 200) + 1) + 100;
			return val;
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
	}

}
