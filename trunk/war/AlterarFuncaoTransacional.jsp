<%@page import="funcaoTransacional.FuncoesTransacionais"%>
<%@include file="header.jsp"%>
<%
	Long id_ft = Long.valueOf(request.getParameter("id_FT"));
	FuncoesTransacionais ft= DAOFactory.getFuncaoTransacionalDAO().get(id_ft);
	
  	String message = (String) request.getAttribute("message_Alteracao");
%>

<table id="center_Alterar">

	<tr>
   	 <td>
    	<form action="Alterar_Funcao_Transacional" method="post" class="form-horizontal">
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
		       					<input type="text" name="novo_Nome_FT" value="<%=ft.getNome()%>" class="input" placeholder="Nome da Funcao Transacional"/>      
	 						</div>
	 				</div>
	 				<div><input type="hidden" name="id_FT" value="<%=ft.getId()%>"></div>
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