<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
</head>
<body>

<%
String userName = null;
int age = 0;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) 
	{	userName = cookie.getValue();
		cookie.setMaxAge(0);
		age = cookie.getMaxAge();
		cookie.setPath("/");
		cookie.setValue("");
		cookie.setComment("EXPIRING COOKIE at " + System.currentTimeMillis());
		response.addCookie(cookie);
	}
	
}
cookies[1] = null;
System.out.println("cookies size: " + cookies.length);
}
if(userName == null) response.sendRedirect("login.html");
%>
<h3>Bye <%=userName %>[<%=age %>], Logout successful.</h3>
<br>
<form action="/Licenta/Welcome" method="GET">
<input type="submit" value="Logout" >
</form>
</body>
</html>