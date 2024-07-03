package com.source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.util.DbConnection;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	final String UPLOAD_DIRECTORY = DbConnection.path;//"D:/upload";

	public void init(ServletConfig config) throws ServletException {
		try {
			con = DbConnection.getConnection();

		} catch (Exception e) {

			System.out.println("Exception in DB" + e);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); Date
		 * dateobj = new Date(); String c_date = df.format(dateobj);
		 * 
		 * HttpSession session = request.getSession(); String c_user =
		 * session.getAttribute("email").toString();
		 * 
		 * String fkeyword = request.getParameter("fkey"); String skeyword =
		 * request.getParameter("skey"); String tkeyword =
		 * request.getParameter("tkey");
		 * 
		 * System.out.println("Fkey is " + fkeyword);
		 * System.out.println("Skey is " + skeyword);
		 * System.out.println("Tkey is " + tkeyword);
		 * 
		 * String opr1 = request.getParameter("opr1"); String opr2 =
		 * request.getParameter("opr2");
		 * 
		 * String ldate = request.getParameter("ldate"); String prv1 = "", prv2
		 * = "", prv3 = "";
		 * 
		 * String[] results = request.getParameterValues("s1_t1"); for (int i
		 * =0; i < results.length; i++) { if (i == 0) { prv1 = results[i]; } if
		 * (i == 1) { prv2 = results[i]; } if (i == 2) { prv3 = results[i]; } }
		 */
		if (ServletFileUpload.isMultipartContent(request)) 
		{
			try 
			{
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				System.out.println("ABCD");
				String FileName = "";
				String Userid = "";
				String SecKey = "";
				String FileExtention = "";
				String FileData = "";
				long FileSize = 0;

				for (FileItem item1 : multiparts) 
				{
					if (!item1.isFormField()) 
					{
						System.out.println("4");
						String name = new File(item1.getName()).getName();
						item1.write(new File(UPLOAD_DIRECTORY + File.separator+ name));
						FileName = item1.getName();
						FileExtention = item1.getContentType();
						FileSize = item1.getSize();
					}
				}

				String prv1 = "", prv2 = "", prv3 = "", fkeyword = "", skeyword = "", tkeyword = "", ldate = "", sdate = "";
				String opr1 = "", opr2 = "";
				String[] result;

				for (FileItem item : multiparts) 
				{
					if ((item.getFieldName()).equals("sdate")) 
					{
						sdate = (String) item.getString();
					}
				}
				for (FileItem item : multiparts) 
				{
					if ((item.getFieldName()).equals("ldate")) 
					{
						ldate = (String) item.getString();
					}
				}
				for (FileItem item : multiparts) 
				{
					if ((item.getFieldName()).equals("fkey")) 
					{
						fkeyword = (String) item.getString();
					}
				}
				for (FileItem item : multiparts) 
				{
					if ((item.getFieldName()).equals("skey")) {
						skeyword = (String) item.getString();
					}
				}
				for (FileItem item : multiparts) 
				{
					if ((item.getFieldName()).equals("tkey")) {
						tkeyword = (String) item.getString();
					}
				}
				for (FileItem item : multiparts) 
				{
					if ((item.getFieldName()).equals("opr1")) {
						opr1 = (String) item.getString();
					}
				}
				for (FileItem item : multiparts) 
				{
					if ((item.getFieldName()).equals("opr2")) 
					{
						opr2 = (String) item.getString();
					}
				}
				for (FileItem item : multiparts) 
				{
					if ((item.getFieldName()).equals("prv1")) {
						prv1 = (String) item.getString();
					}
				}
				for (FileItem item : multiparts) 
				{
					if ((item.getFieldName()).equals("prv2")) 
					{
						prv2 = (String) item.getString();
					}
				}
				for (FileItem item : multiparts) 
				{
					if ((item.getFieldName()).equals("prv3")) 
					{
						prv3 = (String) item.getString();
					}
				}
				
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				Date dateobj = new Date();
				String c_date = df.format(dateobj);
				
				HttpSession session=request.getSession();
				String email=session.getAttribute("email").toString();
				System.out.println("c_user Mail " + email);
				System.out.println("Ldate " + ldate);
				System.out.println("fkeyword " + fkeyword);
				System.out.println("skeyword " + skeyword);
				System.out.println("tkeyword " + tkeyword);
				System.out.println("opr1 " + opr1);
				System.out.println("opr2 " + opr2);
				System.out.println("prv1 " + prv1);
				System.out.println("prv2 " + prv2);
				System.out.println("prv3 " + prv3);
				System.out.println("FileName " + FileName);
				System.out.println("File Extension " + FileExtention);
				System.out.println("File Size " + FileSize);
				
				
				ArrayList<String> prvA=new ArrayList<String>();
				ArrayList<String> prvO=new ArrayList<String>();
				
				if(opr1.equals("AND"))
				{
					prvA.add(prv1);
					prvA.add(prv2);
					if(opr2.equals("AND"))
					{
						prvA.add(prv3);
					}
					if(opr2.equals("OR"))
					{
						prvO.add(prv3);
					}
				}
				else if(opr1.equals("OR"))
				{
					prvO.add(prv1);
					prvO.add(prv2);
					if(opr2.equals("OR"))
					{
						prvO.add(prv3);
					}
					else if(opr2.equals("AND")) 
					{
						prvO.clear();
						prvO.add(prv1);
						prvA.add(prv2);
						prvA.add(prv3);
					}
				}
				
				
				int prvO_length=prvO.size();
				System.out.println("prvO "+prvO_length);
				
				int prvA_length=prvA.size();
				System.out.println("prvA "+prvA_length);
				
				
				Userid = session.getAttribute("userid").toString();

				BufferedReader br = new BufferedReader(new FileReader(UPLOAD_DIRECTORY + "/" + FileName));
				try {
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();

					while (line != null) {
						sb.append(line);
						sb.append(System.lineSeparator());
						line = br.readLine();
					}
					String plainTextData = sb.toString();
					//String encValue=AESAlgorithm.encrypt(plainTextData,"mySalt");
					//System.out.println("Enc Value is \n"+encValue);
					
					String digest = MD5.digest(plainTextData);
					
									System.out.println("-================ "+digest);
					try {
						PreparedStatement pp = con.prepareStatement("select * from files where digest=?");
						pp.setString(1, digest);
						ResultSet rr = pp.executeQuery();
						if(!rr.next())
						{
							String encValue=AESAlgorithm.encrypt(plainTextData,"mySalt");
							System.out.println(encValue);
							//PrintWriter writer = new PrintWriter("D:\\upload\\enc_"+FileName, "UTF-8");
							PrintWriter writer = new PrintWriter(DbConnection.path+"\\enc_"+FileName, "UTF-8");
                            writer.println(encValue);
                            writer.close();
						/**/
						 PreparedStatement ps =con.prepareStatement("INSERT INTO `files` (`file_id`, `uplaod_by`, `file_name`, `upload_date`, `last_date`, `prv1`, `opr1`, `prv2`, `opr2`, `prv3`, `userid`, `digest`) VALUES (NULL, '"+email+"','"+FileName+"','"+sdate+"','"+ldate+"','"+prv1+"','"+opr1+"','"+prv2+"','"+opr2+"','"+prv3+"','"+Userid+"','"+digest+"')");
						 int rs=ps.executeUpdate();
						 if(rs>0)
						 {
							
							 System.out.println("Data Insert Done ");
								PreparedStatement psm =con.prepareStatement("SELECT MAX(file_id) FROM `files`"); 
							 	ResultSet rsm=psm.executeQuery();
							 	String max_file_id="";
							 	if(rsm.next())
							 	{
							 		max_file_id=rsm.getString(1);
							 	}
							 	 Graph1(max_file_id, Userid, ldate);
							 	//----------------------------Set Priviledges-----------------------------------------------------------------------------------
							 	System.out.println("size prvA is "+prvA.size());
								 System.out.println("size prvO is "+prvO.size());
								 String comboPrv="";
								 String single="";
								 
								 if(prvA.size()==3)
								 {
									 prv1=prvA.get(0);
									 prv2=prvA.get(1);
									 prv3=prvA.get(2);
									 comboPrv=prv1+" AND "+prv2+" AND "+prv3;
									 
								 }
								 if(prvA.size()==2)
								 {
									 prv1=prvA.get(0);
									 prv2=prvA.get(1);
									 comboPrv=prv1+" AND "+prv2;
									 
								 }
								 if(prvA.size()==1)
								 {
									 prv1=prvA.get(0);
									 single=prv1;
								 }
								 
								 
								 if(prvO.size()==2)
								 {
									 prv2=prvO.get(0);
									 prv3=prvO.get(1);
									 //comboPrv=comboPrv+" OR "+prv2+" AND "+prv3;
									 System.out.println("================================fffffffffffffffffffffffffffffffffffff");
									
									 PreparedStatement pec1=con.prepareStatement("INSERT INTO `fileaccess` (`file_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+prv2+"')");
									 int rec1=pec1.executeUpdate();
									 if(rec1>0)
									 {
										 System.out.println("Data Insertt Done pec1 ");
									 }
									 
									 PreparedStatement pec2=con.prepareStatement("INSERT INTO `fileaccess` (`file_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+prv3+"')");
									 int rec2=pec2.executeUpdate();
									 if(rec2>0)
									 {
										 System.out.println("Data Insertt Done pec2 ");
									 }
								 
								 }
								 if(prvO.size()==1)
								 {
									 prv3=prvO.get(0);
									 single=prv3;
								 }
								 
								 if(prvO.size()==3)
								 {
									 prv1=prvO.get(0);
									 prv2=prvO.get(1);
									 prv3=prvO.get(2);
									 try
									 {
										 PreparedStatement pec1=con.prepareStatement("INSERT INTO `fileaccess` (`file_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+prv1+"')");
										 int rec1=pec1.executeUpdate();
										 if(rec1>0)
										 {
											 System.out.println("Data Insertt Done pec1 ");
										 }
										 
										 PreparedStatement pec2=con.prepareStatement("INSERT INTO `fileaccess` (`file_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+prv2+"')");
										 int rec2=pec2.executeUpdate();
										 if(rec2>0)
										 {
											 System.out.println("Data Insertt Done pec2 ");
										 }
										 
										 PreparedStatement pec3=con.prepareStatement("INSERT INTO `fileaccess` (`file_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+prv3+"')");
										 int rec3=pec3.executeUpdate();
										 if(rec3>0)
										 {
											 System.out.println("Data Insertt Done pec3 ");
										 }
										 
									 }catch(Exception ec0)
									 {
										 System.out.println("Exception in ec0 "+ec0);
									 }
								 }
								 else
								 {
									try{
										
										
										System.out.println("Combo "+comboPrv);
										System.out.println("single "+single);
										if(!comboPrv.equals(""))
										{
											PreparedStatement pec1=con.prepareStatement("INSERT INTO `fileaccess` (`file_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+comboPrv+"')");
											int rec1=pec1.executeUpdate();
											if(rec1>0)
											{
												System.out.println("Data Insertt Done pec11 ");
											}
										}
										if(!single.equals(""))
										{
											PreparedStatement pec2=con.prepareStatement("INSERT INTO `fileaccess` (`file_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+single+"')");
											int rec2=pec2.executeUpdate();
											if(rec2>0)
											{
												System.out.println("Data Insertt Done pec22 ");
											}
										}
									}
									catch(Exception ec1)
									{
										System.out.println("Exception in ec1 "+ec1);
									}
								 }
								 
								 System.out.println("size prvO is "+comboPrv);
							 	//----------------------------End Priviledge-----------------------------------------------------------------------------------
							
							 
						 }
						 else{
							 System.out.println("Data");
						 }
						}
						else
						{
							System.out.println("--------------------------------------");
							String ref_ptr = rr.getString("file_id");
							
							PreparedStatement dedup = con.prepareStatement("insert into dedup_data(ref_ptr,userid,filename,upload_date,last_date,prv1,opr1,prv2,opr2,prv3) values(?,?,?,?,?,?,?,?,?,?)");
							dedup.setString(1, ref_ptr);
							dedup.setString(2, Userid);
							dedup.setString(3, FileName);
							dedup.setString(4, sdate);
							dedup.setString(5, ldate);
							dedup.setString(6, prv1);
							dedup.setString(7, opr1);
							dedup.setString(8, prv2);
							dedup.setString(9, opr2);
							dedup.setString(10, prv3);
							int rsd = dedup.executeUpdate();
							if(rsd>0)
							{
								PreparedStatement psm =con.prepareStatement("SELECT MAX(id) FROM `dedup_data`"); 
							 	ResultSet rsm=psm.executeQuery();
							 	String max_file_id="";
							 	if(rsm.next())
							 	{
							 		max_file_id=rsm.getString(1);
							 	}
							 	Graph1(max_file_id, Userid, ldate);
							 	//----------------------------Set Priviledges-----------------------------------------------------------------------------------
							 	System.out.println("size prvA is "+prvA.size());
								 System.out.println("size prvO is "+prvO.size());
								 String comboPrv="";
								 String single="";
								 
								 if(prvA.size()==3)
								 {
									 prv1=prvA.get(0);
									 prv2=prvA.get(1);
									 prv3=prvA.get(2);
									 comboPrv=prv1+" AND "+prv2+" AND "+prv3;
									 
								 }
								 if(prvA.size()==2)
								 {
									 prv1=prvA.get(0);
									 prv2=prvA.get(1);
									 comboPrv=prv1+" AND "+prv2;
								 }
								 if(prvA.size()==1)
								 {
									 prv1=prvA.get(0);
									 single=prv1;
								 }
								 
								 
								 if(prvO.size()==2)
								 {
									 prv2=prvO.get(0);
									 prv3=prvO.get(1);
									 comboPrv=comboPrv+" OR "+prv2+" AND "+prv3;
								 }
								 if(prvO.size()==1)
								 {
									 prv3=prvO.get(0);
									 single=prv3;
								 }
								 
								 if(prvO.size()==3)
								 {
									 prv1=prvO.get(0);
									 prv2=prvO.get(1);
									 prv3=prvO.get(2);
									 try
									 {
										 PreparedStatement pec1=con.prepareStatement("INSERT INTO `fileaccess` (`dedup_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+prv1+"')");
										 int rec1=pec1.executeUpdate();
										 if(rec1>0)
										 {
											 System.out.println("Data Insertt Done pec1 ");
										 }
										 
										 PreparedStatement pec2=con.prepareStatement("INSERT INTO `fileaccess` (`dedup_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+prv2+"')");
										 int rec2=pec2.executeUpdate();
										 if(rec2>0)
										 {
											 System.out.println("Data Insertt Done pec2 ");
										 }
										 
										 PreparedStatement pec3=con.prepareStatement("INSERT INTO `fileaccess` (`dedup_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+prv3+"')");
										 int rec3=pec3.executeUpdate();
										 if(rec3>0)
										 {
											 System.out.println("Data Insertt Done pec3 ");
										 }
										 
									 }catch(Exception ec0)
									 {
										 System.out.println("Exception in ec0 "+ec0);
									 }
								 }
								 else
								 {
									try{
										
										
										System.out.println("Combo "+comboPrv);
										System.out.println("single "+single);
										if(!comboPrv.equals(""))
										{
											PreparedStatement pec1=con.prepareStatement("INSERT INTO `fileaccess` (`dedup_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+comboPrv+"')");
											int rec1=pec1.executeUpdate();
											if(rec1>0)
											{
												System.out.println("Data Insertt Done pec11 ");
											}
										}
										if(!single.equals(""))
										{
											PreparedStatement pec2=con.prepareStatement("INSERT INTO `fileaccess` (`dedup_id`, `upload_by`, `privileges`) VALUES ('"+max_file_id+"', '"+Userid+"', '"+single+"')");
											int rec2=pec2.executeUpdate();
											if(rec2>0)
											{
												System.out.println("Data Insertt Done pec22 ");
											}
										}
									}
									catch(Exception ec1)
									{
										System.out.println("Exception in ec1 "+ec1);
									}
								 }
								 
								 System.out.println("size prvO is "+comboPrv);
							 	//----------------------------End Priviledge-----------------------------------------------------------------------------------
							
							}
							response.sendRedirect("uploadfile.jsp?dedup");
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					br.close();

				} catch (Exception e) {
					System.out.println("Exception is " + e);
					e.printStackTrace();

				}

				System.out.println("Connection created ");
				System.out.println("upload File Successfully ");
				response.sendRedirect("uploadfile.jsp?upload=done");
				

			}

			catch (Exception ex) {
				System.out.println("Exception e" + ex);
				ex.printStackTrace();
			}
		}

		else {
			System.out.println("Condition False");
			response.sendRedirect("uploadfile.jsp?fail=uplaod");
		}
	}
	
	
	private int graph()
	{
		
			int val=new Random().nextInt((400 - 100) + 1) + 100;
			return val;
		
	}
	private int graph2()
	{
		
			int val=new Random().nextInt((600 - 400) + 1) + 400;
			return val;
		
	}
public void Graph1(String file_id,String userid,String lsdate)
{
	try {
		PreparedStatement ps=con.prepareStatement("select * from graph1 where fileid='"+file_id+"' and userid='"+userid+"'");
		ResultSet rs=ps.executeQuery();
		if(!rs.next())
		{

	        Statement st = con.createStatement();
	         String sqlss = "INSERT INTO graph1(fileid,enctime,uploadtime,userid,lsdate) VALUE("+file_id+","+graph()+","+graph2()+",'"+userid+"','"+lsdate+"')";
	         //System.out.println("enr========="+sqlss);
	         st.executeUpdate(sqlss);
	         
		}
    	 
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}

