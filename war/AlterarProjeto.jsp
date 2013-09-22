<%@include file="header.jsp"%>
<%
	Long id_proj = Long.valueOf(request.getParameter("id"));
	Projeto proj= DAOFactory.getProjetoDAO().get(id_proj);
	
  	String message = (String) request.getAttribute("message_Alteracao");
%>

<table id="center_Alterar">

	<tr>
   	 <td>
    	<form action="Alterar_Projeto" method="post" class="form-horizontal">
			<fieldset>
				<legend>Alterar Cadastro:</legend>
					<div>
						 <%
 							if(message!= null){
 							out.println("<div class='alert alert-error'>");
  							out.println(message +"</div>");}
						 %>
					</div>
					<div class="control-group">
		      			<label class="control-label" for="inputNome">Nome: </label>
		      				<div class="controls">
		       					<input type="text" name="novo_Nome_Projeto" value="<%=proj.getNome()%>" class="input" placeholder="Nome do Projeto"/>      
	 						</div>
	 				</div>
	 				<div><input type="hidden" name="id_Proj" value="<%=proj.getId()%>"></div>
					<div class="control-group">
	   					<div class="controls">
			       			<button type="submit" class="btn btn-primary">Alterar</button>
	    				</div>
	 				</div>
				</fieldset>
			</form> 
	</td>
   </tr>
 
  
  </table>


<%@include file="footer.jsp"%>