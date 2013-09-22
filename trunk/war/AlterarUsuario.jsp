<%@include file="header.jsp"%>
<%
	Long id_user = (Long) session.getAttribute("ID");
	Usuario user = DAOFactory.getUsuarioDAO().get(id_user);
	
  	String message = (String) request.getAttribute("message_Alteracao");
%>

<table id="center_Alterar">

	<tr>
   	 <td>
    	<form action="Alterar_Usuario" method="post" class="form-horizontal">
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
		       					<input type="text" name="novo_Nome_Usuario" value="<%=user.getNome()%>" class="input" placeholder="Nome do Usuário"/>      
	 						</div>
	 				</div>
	 				<div class="control-group">
		      			<label class="control-label" for="inputNome">Sobrenome: </label>
		      				<div class="controls">
		       					<input type="text" name="novo_SobreNome_Usuario" value="<%=user.getSobreNome()%>" class="input" placeholder="Sobrenome do Usuário"/>      
	 						</div>
	 				</div>
	 				<div><input type="hidden" name="id_Usuario" value="<%=user.getId()%>"></div>
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