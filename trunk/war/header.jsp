<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="padocaproject.Usuario" %>
<%@ page import="padocaproject.Projeto" %>
<%@ page import="DAO.DAOFactory" %>
<%@ page import= "com.google.appengine.api.datastore.Query.FilterOperator"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  

<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
<style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
</style>
    
<%
	response.setHeader ("Cache-Control", "no-cache");
	response.setHeader ("Pragma", "no-cache");
	response.setDateHeader ("Expires", 0); 
%>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Padoca Project</title>
</head>
<body>

<% if(session.getAttribute("UserName")== null){
%>
	<jsp:forward page="Login_Index.jsp"/>
<%} 
String messageProjeto = (String) request.getAttribute("message");
String nomeProjeto = "";
if (request.getAttribute("nomeProjeto")!=null){
	nomeProjeto = (String) request.getAttribute("nomeProjeto");
}
%>
	
	<table id="tabelaSuperior" >
		<tr>
			<td>
				<div class="navbar navbar-inverse navbar-fixed-top">
					<div class="navbar-inner">
						<div class="container-fluid">
							<a class="brand" href="padocaproject.jsp">PADOCA</a>
							<form class="navbar-form pull-left" action="Cadastro_Projeto" method="post">
								<input id="nome_Projeto"  type="text" class="span2" name="nome_Projeto" value="<%=nomeProjeto%>"/>
 								 <button type="submit" class="btn">Cadastrar Projeto</button>
 								 <%if(messageProjeto != null)
									{
										out.println("<span class='label label-important'> ");
										out.println(messageProjeto + "</span>");
									}
 								 %>
							</form>
							
								<p class="navbar-text pull-right">
									Bem vindo, <%out.println(session.getAttribute("UserName"));%>  |
									<a href="AlterarUsuario.jsp" class="navbar-link "> Editar Cadastro</a> |   
									<a href="LogoutUser" class="navbar-link "> Logout</a>
								</p>
							
						</div>
					</div>					
				</div>
			</td>
		</tr>
	</table>
	
	
	<table class="tabelaExterna">
	<tr>
	  <td class="colunaLateral">
			<ul class="nav nav-pills nav-stacked">
			  <li class="nav-header">Menu</li>
			  <li><a href="Show_Usuario">Usuários</a></li>
			</ul>			
	  </td>

	  <td class="colunaCentral">