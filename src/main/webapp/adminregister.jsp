<!DOCTYPE html>
<!--
TEMPLATE
Name: Zoo Planet
Version: 1.0
Created: 23 January 2014

AUTHOR
Design and code by: http://www.bootshape.com
Free stock photos by: http://www.bootshape.com

Read full license: http://www.bootshape.com/license/

CREDITS
Background: http://subtlepatterns.com/ (extra_clean_paper)
Fonts: http://www.google.com/fonts (Actor, Duru_Sans)

SUPPORT
E-mail: bootshape.com@gmail.com
Contact: http://www.bootshape.com/contact/
-->
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
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
            <li class="active"><a href="index.jsp">Home</a></li>
            <li class="dropdown">
              <a data-toggle="dropdown" href="#" class="dropdown-toggle">User <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="user.jsp">Login</a></li>
                <li><a href="register.jsp">Registration</a></li>
                </ul>
            </li>
           <li class="dropdown">
              <a data-toggle="dropdown" href="#" class="dropdown-toggle">ADmin <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="admin.jsp">Login</a></li>
                <li><a href="adminregister.jsp">Registration</a></li>
                </ul>
            </li>
            <li><a href="ca.jsp">CA</a></li>
            <li><a href="cloud.jsp">CLOUD</a></li>
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
    <h3 class="text-center"><marquee> Registration</marquee></h3>
    <!-- Thumbnails -->
       <!-- Content -->
       <div align="center">
		<div>
			<br />  <br /> <br />
	<form action="adregister" method="post">
				<table border="0" cellpadding="5" cellspacing="5">
					<tr>
						<td>Full Name</td>
						<td><input type="text" style="width: 170px; height: 30px"
							name="fname" required="required"/></td>
						<td><input type="text" style="width: 170px; height: 30px"
							name="mname" required="required"/></td>
						<td><input type="text" style="width: 170px; height: 30px"
							name="lname" required="required"/></td>
					</tr>
					<tr>
						<td>Gender</td>
						<td><input type="radio" name="gender" value="Male" />Male&nbsp;&nbsp;<input
							type="radio" name="gender" value="Female" />Female</td>
						<td>Date of Birth</td>
						<td><input type="date" style="width: 170px; height: 30px"
							name="dob" required="required"/></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Reg. Date</td>
						<%
							DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
							Date dateobj = new Date();
							String c_date = df.format(dateobj);
							System.out.println("C Date " + c_date);
						%>

						<td><input type="text" name="reg_date" value="<%=c_date%>"
							style="width: 170px; height: 30px" readonly="readonly" /></td>
						<td>Mobile No.</td>
						<td><input type="text" name="mobno" maxlength="10" 
							style="width: 170px; height: 30px"  required="required"/></td>

					</tr>
					<tr>
						<td>Email ID</td>
						<td><input type="email" name="email"
							style="width: 170px; height: 30px"  required="required"/></td>
						<td>Password</td>
						<td><input type="password" name="pwd" 
							style="width: 170px; height: 30px"  required="required"/></td>


					</tr>
					<tr>

					</tr>

					

					<tr>
						<td></td>
						<td><input type="submit" value="Registration"
							style="width: 130px; height: 35px" /></td>
						<td><input type="reset" value="Reset"
							style="width: 130px; height: 35px" /></td>
						<td></td>
					</tr>

				</table>
			</form>

			<br />

		</div>
	</div>
   

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootshape.js"></script>
  </body>
</html>