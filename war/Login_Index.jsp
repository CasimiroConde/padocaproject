<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Projeto Padoca</title>
</head>
<body>
<%
String message = (String) request.getAttribute("message");
String login = "";		  
if (request.getAttribute("UserLogin")!=null){
	 login = (String) request.getAttribute("UserLogin");  
  }
%>
<% if(session.getAttribute("UserName")== null){%>
	
<table class="centerForm">
<td>
	<div class="hero-unit">
		<div id="imgLogo"> <img src ="/IMG/PadocaLogo.png" alt="Padoca"/></div>
		
		
		<%if(message != null)
		{
			out.println("<div class='alert alert-error'> ");
			out.println(message + "</div>");
		}else
		{
			out.println("<div>  </div>");
		}
		%>
	
		
		
		
		<form action="LoginUser" method="post" class="form-horizontal">
	      	<div class="control-group">
		      	<label class="control-label" for="inputEmail">Email</label>
		      		<div class="controls">
		       			<input type="text" name="UserLogin"  id="inputEmail" placeholder="Email" value="<%=login%>"/>       
	 				</div>
	 		</div>
	     	<div class="control-group">	
	       		<label class="control-label" for="inputPassword">Senha</label>
	       			<div class="controls">
	       			 	<input type="password" name="UserPassword" id="inputPassword" placeholder="Password"/>
	         		</div>
	 		</div>
			<div class="control-group">
	   			<div class="controls">
	       			<button type="submit" class="btn btn-primary">Entrar</button>
	       			<label class="btn btn-link">
	       			<a href="Usuario.jsp"> Fazer Cadastro</a>
	       			</label>
	   				 <div> <label  class="btn btn-link"> <a href="RecuperaSenha.jsp">Esqueci minha senha! </a></label></div>
	    		</div>
	 		</div>	
		</form>
	</div>
	
	</td>
</table>
<%	
}else{
%>
<jsp:forward page="padocaproject.jsp"/>
<%
}
%>  

</body>
</html>