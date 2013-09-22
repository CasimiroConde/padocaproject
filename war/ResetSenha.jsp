<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Bootstrap -->
<link href="/css/bootstrap.css" rel="stylesheet" media="screen">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Projeto Padoca - Reset Senha</title>
</head>
<body>
<%String message = (String) request.getAttribute("message");
String email = (String) request.getParameter("email_Usuario");	
String token = (String) request.getParameter("token");	
%>
<% if(session.getAttribute("UserName")== null){%>

<table class="centerForm">
	<td>
		<div class="hero-unit">
				
			
			<form action="Executa_Reset" method="post" class="form-horizontal">	
				<legend> Reset Senha </legend>
				<%if(message != null)
					{
						out.println("<div class='alert alert-error'> ");
						out.println(message + "</div>");
					}else
					{
						out.println("<div>  </div>");
					}
				%>
				<div><input type="hidden" name="token" value="<%=token%>"></div>
			 	<div><input type="hidden" name="email_Usuario" value="<%=email%>"></div>
				<div class="control-group">
	      			<label class="control-label" for="inputSenha1">Nova Senha: </label>
	      				<div class="controls">
	       					<input type="password" name="senha_Usuario1"  class="input" placeholder="Nova Senha""/>      
 						</div>
 				</div>
 				<div class="control-group">
	      			<label class="control-label" for="inputSenha2">Repita Senha: </label>
	      				<div class="controls">
	       					<input type="password" name="senha_Usuario2"  class="input" placeholder="Repita Nova Senha""/>      
 						</div>
 				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-primary">Cadastrar</button>
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