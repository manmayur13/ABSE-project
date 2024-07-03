<!DOCTYPE html>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.util.DbConnection"%>
<%@page import="java.sql.Connection"%>
<html>
  <head>
    <title>ABD</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
    <!--Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Duru+Sans|Actor' rel='stylesheet' type='text/css'>
    
    <!--Bootshape-->
    <link href="css/bootshape.css" rel="stylesheet">

    
  </head>
  <body>
    <!-- Navigation bar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#"><span class="green">Attribute Based</span> System</a>
        </div>
        <nav role="navigation" class="collapse navbar-collapse navbar-right">
          <ul class="navbar-nav nav">
            <li class="active"><a href="cloudhome.jsp">Home</a></li>
            <li><a href="#" >Admins </a></li>
             <li><a href="#" >Users</a></li>
             
            <li><a href="index.jsp?logout">Logout</a></li>
            </ul>
        </nav>
      </div>
    </div><!-- End Navigation bar -->

    <!-- Slide gallery -->
    <div class="jumbotron">
      <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
          <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-example-generic" data-slide-to="1"></li>
          <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>
        <!-- Wrapper for slides -->
        <div class="carousel-inner">
          <div class="item active">
            <img src="img/carousel1.jpg" style="height:400px; width:1520px;" alt="">
            <div class="carousel-caption">
            </div>
          </div>
          <div class="item">
            <img src="img/carousel2.jpg" style="height:400px; width:1520px;" alt="">
            <div class="carousel-caption">
            </div>
          </div>
          <div class="item">
            <img src="img/carousel3.jpg" style="height:400px; width:1520px;" alt="">
            <div class="carousel-caption">
            </div>
          </div>
        </div>
        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
      </div>
    </div><!-- End Slide gallery -->
    <h3 class="text-center"><marquee> Cloud HOME</marquee></h3>
    <!-- Thumbnails -->
       <!-- Content -->
   <h3 class="text-center"><marquee>Active Admins</marquee></h3>
   <table align="center" border="1">
							<tr align="center" style="font-weight: bold;">
							<td>Srno</td>
							<td>First Name</td>
							<td>Last Name</td>
							<td>Gender</td>
							<td>Email</td>
							<td>Mobile</td>
							
							</tr>
							<%
							int srno=1;
							Connection con = DbConnection.getConnection();
							PreparedStatement ps = con.prepareStatement("select * from admin where status=1");
							ResultSet rs = ps.executeQuery();
							while(rs.next())
							{
								%>
								<tr align="center">
								<td><%=srno++ %></td>
								<td><%=rs.getString("fname") %></td>
								<td><%=rs.getString("lname") %></td>
								<td><%=rs.getString("gender") %></td>
								<td><%=rs.getString("email") %></td>
								<td><%=rs.getString("mobno") %></td>
								</tr>
								<%
								
							}
							%>
							</table>
							
							
   
   
   
   <h3 class="text-center"><marquee>Active Users</marquee></h3>
  		<table align="center" border="1">
							<tr align="center" style="font-weight: bold;">
							<td>Srno</td>
							<td>First Name</td>
							<td>Last Name</td>
							<td>Gender</td>
							<td>Email</td>
							<td>Mobile</td>
							</tr>
							<%
							int srno1=1;
							Connection con1 = DbConnection.getConnection();
							PreparedStatement ps1 = con.prepareStatement("select * from user where status=1");
							ResultSet rs1 = ps1.executeQuery();
							while(rs1.next())
							{
								%>
								<tr align="center">
								<td><%=srno1++ %></td>
								<td><%=rs1.getString("fname") %></td>
								<td><%=rs1.getString("lname") %></td>
								<td><%=rs1.getString("gender") %></td>
								<td><%=rs1.getString("email") %></td>
								<td><%=rs1.getString("mobno") %></td>
								</tr>
								<%
								
							}
							%>
							</table>
							
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootshape.js"></script>
  </body>
</html>