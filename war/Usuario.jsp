<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Bootstrap -->
<link href="/css/bootstrap.css" rel="stylesheet" media="screen">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Projeto Padoca - Usuário</title>
</head>
<body>
<%String message = (String) request.getAttribute("message");
String nomeUsuario = "";
String SobrenomeUsuario = "";
String email = "";
if(request.getAttribute("nome_Usuario") != null)
{
 	nomeUsuario = (String) request.getAttribute("nome_Usuario");
}
if(request.getAttribute("Sobrenome_Usuario") != null)
{
 	SobrenomeUsuario = (String) request.getAttribute("Sobrenome_Usuario");
}
if(request.getAttribute("email") != null){
	email = (String) request.getAttribute("email");	
}
%>
<% if(session.getAttribute("UserName")== null){%>

<table class="centerForm">
	<td>
		<div class="hero-unit">
			
		
			
			<form action="Cadastro_Usuario" method="post" class="form-horizontal">	
				<legend> Cadastro de Usuário </legend>
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
	      			<label class="control-label" for="inputNome">Nome: </label>
	      				<div class="controls">
	       					<input type="text" name="nome_Usuario"  class="input" placeholder="Nome" value="<%=nomeUsuario%>"/>      
 						</div>
 				</div>
 				<div class="control-group">
	      			<label class="control-label" for="inputNome">Sobrenome: </label>
	      				<div class="controls">
	       					<input type="text" name="Sobrenome_Usuario"  class="input" placeholder="Sobrenome" value="<%=SobrenomeUsuario%>"/>      
 						</div>
 				</div>
 				<div class="control-group">
	      			<label class="control-label" for="inputNome">Email: </label>
	      				<div class="controls">
	       					<input type="text" name="email"  class="input" placeholder="Email" value="<%=email%>"/>     
 						</div>
 				</div>
 				<div class="control-group">
	      			<label class="control-label" for="inputNome">Senha: </label>
	      				<div class="controls">
	       					<input type ="password" name="senha" class="input" placeholder="Senha"/>     
 						</div>
 				</div>
 				<div class="control-group">
	      			<label class="control-label" for="inputNome">Confirmação de Senha: </label>
	      				<div class="controls">
	       					<input type ="password" name="confirmSenha" class="input" placeholder=" Confirmação de Senha"/>     
 						</div>
 				</div>
 				<div class="control-group">
   					<div class="controls">
   						<button type="submit" class="btn btn-primary">Entrar</button>
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