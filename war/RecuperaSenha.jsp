<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Bootstrap -->
<link href="/css/bootstrap.css" rel="stylesheet" media="screen">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Projeto Padoca - Recupera Senha</title>
</head>
<body>
<%String message = (String) request.getAttribute("message");
String email = "";
if(request.getAttribute("email") != null){
	email = (String) request.getAttribute("email");	
}
%>
<% if(session.getAttribute("UserName")== null){%>

<table class="centerForm">
	<td>
		<div class="hero-unit">
				
			
			<form action="Recupera_Senha" method="post" class="form-horizontal">	
				<legend> Recuperar Senha </legend>
				<%if(message != null)
					{
						out.println("<div class='alert alert-error'> ");
						out.println(message + "</div>");
					}else
					{
						out.println("<div>  </div>");
					}
				%>
				<div class="control-group">
	      			<label class="control-label" for="inputEmail">Email: </label>
	      				<div class="controls">
	       					<input type="text" name="email_Usuario"  class="input" placeholder="Email" value="<%=email%>"/>      
 						</div>
 				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-primary">Enviar</button>
    						<label class="btn btn-link">
    						<a href="Login_Index.jsp"> Voltar</a>
    						</label>
    				</div>
       			</div>				
			</form>
		</div>
	</td>
</table>	
	
<%	
}else{
%>

<jsp:forward page="Show_Usuario.jsp"/>

<%
}
%>

</body>
</html>