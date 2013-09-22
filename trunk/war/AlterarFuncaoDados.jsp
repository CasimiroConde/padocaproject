<%@page import="funcaoDados.FuncaoDados"%>
<%@include file="header.jsp"%>
<%
	Long id_fd = Long.valueOf(request.getParameter("id_FD"));
	FuncaoDados fd= DAOFactory.getFuncaoDadosDAO().get(id_fd);
	
  	String message = (String) request.getAttribute("message_Alteracao");
%>

<table id="center_Alterar">

	<tr>
   	 <td>
    	<form action="Alterar_Funcao_Dados" method="post" class="form-horizontal">
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
		       					<input type="text" name="novo_Nome_FD" value="<%=fd.getNome()%>" class="input" placeholder="Nome da Funcao de Dados"/>      
	 						</div>
	 				</div>
	 				<div><input type="hidden" name="id_FD" value="<%=fd.getId()%>"></div>
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