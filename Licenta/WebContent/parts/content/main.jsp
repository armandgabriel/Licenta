<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">



<link href="../stylesheet/loginCSS.css" rel="stylesheet">



<title>Insert title here</title>

<link href="../stylesheet/loginCSS.css" rel="Stylesheet" >

  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
</title>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">SortManagement</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li><a href="#">View History</a></li>
      <li><a href="#">Action</a></li>
      <li><a href="#">Timer Action</a></li>
    </ul>
  </div>
</nav>
<%
Cookie[] cookies = request.getCookies();
String userName = null;
int age = 0;
if(cookies !=null)
{
	for(Cookie cookie : cookies)
	{
		if(cookie.getName().equals("user") && cookie.getValue().equals(request.getParameter("name"))) 
		{	
			userName = cookie.getValue();
			age = cookie.getMaxAge();
			break;
		}
	} 
}
%>
<p style="position:absolute;left=50%;">Welcome <%=userName %>[<%=age %>]</p>
	<div class="container">
  <h2>Basic Table</h2>
  <p>The .table class adds basic styling (light padding and only horizontal dividers) to a table:</p>            
 
 <a href="/Licenta/parts/content/logoutServlet.jsp">Logout</a>
</div>
</body>
</html>