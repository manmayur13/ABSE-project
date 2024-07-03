<!DOCTYPE html>
<%@page import="com.source.GlobalFunction"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.util.DbConnection"%>
<%@page import="java.sql.Connection"%>
<html>
  <head>
  <noscript>
			<link rel="stylesheet" href="css/skel-noscript.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
    <title>ABD</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
   
    <link href='http://fonts.googleapis.com/css?family=Duru+Sans|Actor' rel='stylesheet' type='text/css'>
    
   
    <link href="css/bootshape.css" rel="stylesheet">

     </head>
  <body>
  <%
		if (request.getParameter("upload") != null) {
			out.print("<script>alert('File Upload Successfully')</script>");
		}
if (request.getParameter("dedup") != null) {
	out.print("<script>alert('File Duplicated')</script>");
}
	%>
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
            <li><a href="home.jsp">Home</a></li>
            <li><a href="graph.jsp">Graph</a></li>
             <li><a href="index.jsp?logout">Logout</a></li>
            </ul>
        </nav>
      </div>
    </div>
    
    <div class="jumbotron">
      <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
     
        <ol class="carousel-indicators">
          <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-example-generic" data-slide-to="1"></li>
          <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>
       
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
       
        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
      </div>
    </div>
    <div id="featured">
			<div class="container">
				<div class="row">
					<div class="12u">
						<section>
							
							<div align="center">
							
							<font style="color: black; font-size: 20px">
				</font> <br /> <br /> <br />
			<%
			Connection con = DbConnection.getConnection();
			String enctime="",uploadtime="";
			PreparedStatement ps = con.prepareStatement("select * from graph2 where fileid=? and userid=?");
			System.out.print("userid");
			ps.setString(1, request.getParameter("file_id"));
			ps.setString(2, session.getAttribute("userid").toString());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				enctime = rs.getString("dectime");
				uploadtime = rs.getString("downloadtime");
				//System.out.println(enctime);
				//System.out.println(uploadtime);
				
			}
			
			%>
	
<div id="chartContainer" style="height: 400px; width: 700px;"></div>


<script type="text/javascript">
		window.onload = function () {
			var chart = new CanvasJS.Chart("chartContainer", {
				title: {
					text: "Download Graph"
				},
				axisY: {
					title: "Time in ms"
				},
				data: [{
					type: "column",
					dataPoints: [
						{ y: <%=enctime%>, label: "Decryption Time" },
						{ y: <%=uploadtime%>, label: "Download Time" },
						
					]
				}]
			});
			chart.render();
		}
		
	</script>
	<script src="canvasjs.min.js"></script>



	<br></br>
<br></br>
							</div>
							
							
							
								
						</section>
					</div>		
				</div>
			</div>
		</div>
    <!-- Thumbnails -->
       <!-- Content -->
   

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootshape.js"></script>
  </body>
</html>