<%@page import="funcaoTransacional.*"%>
<%@page import="funcaoDados.FuncaoDados"%>
<%@include file="header.jsp"%>

<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/forms.js"></script>

<%
	Long id_proj = Long.valueOf (request.getParameter ("id_proj"));
 	Long id_FT = Long.valueOf (request.getParameter ("id_FT"));
 	FuncoesTransacionais ft = DAOFactory.getFuncaoTransacionalDAO().get(id_FT);
 	Iterable<Funcao> results_ftr = ft.getFuncao();
 	List<FuncaoDados> results_fd = DAOFactory.getFuncaoDadosDAO().list(DAOFactory.getFuncaoDadosDAO().exactFilter("Projeto", FilterOperator.EQUAL, id_proj));	
 	String fdNome = "";
 	String message_erro = (String) request.getAttribute("messageError");
	
	//List<String> results_det1 = (List<String>) request.getAttribute("dets");
	%>
 <script>
 function check_Det(option){
	var id = option.selectedOptions[0].getAttribute("fdid");
	ajaxCall("/recuperaDet", "ret="+id, true, function(data) {
		var s = "";
		$(data).each(function() { s += "<option value='" + this + "'>" + this + "</option>" });
		$("#cbDET").html(s);
	});
}
		
 </script>
  
<table id="center_Funcao">
   	<tr>
    	<td>
	  		<ul class="breadcrumb">
	 			<li>Projeto: <a href="/Show_Projeto_Info.jsp?id=<%=id_proj%>"><%out.println(DAOFactory.getProjetoDAO().get(id_proj).getNome());%></a> <span class="divider">/</span></li>
				<li class="active">Função Transacional:<a href="/Show_FT_Info.jsp?id_proj=<%=id_proj%>&id_FT=<%=id_FT%>"><%out.println(DAOFactory.getFuncaoTransacionalDAO().get(id_FT).getNome());%></a></li>
			</ul>
		</td>
	</tr>
</table>

<table id="center_Funcao">
	<tr>
		<td>
			<form action="Cadastro_FT_Atributos" method="post">
						<fieldset>
							<legend>Cadastrar Atributos:</legend>
							 <div>
							 <%
	 							if(message_erro!= null){
	 							out.println("<div class='alert alert-error'>");
	  							out.println(message_erro +"</div>");}
							 %>
							 </div>
							<div id="Nome_Atributo_FT" >Nome FD: <select  id="select1"  name="nome_FD" onclick="check_Det(this)">
																			    <%
																					for(FuncaoDados fd1 : results_fd){
																						fdNome = fd1.getNome();
																					    out.println("<option value='" +fd1.getNome() + "' fdid='" + fd1.getId() + "'>" + fd1.getNome() + "</option>");}
																				%> 									   					
			 		   											</select>
			 		   		<div id="Nome_Atributo_DET" >Nome DET: <select id="cbDET"  name="nome_DET" >	
			 		   											</select>
			 		   		</div>		   		
			 		   		<div><input type="hidden" name="tipo" value="FTR"></div>
			 		   		<div><input type="hidden" name="IDFT" value="<%=id_FT%>"></div>
			 		   		<div><input type="hidden" name="IDProj" value="<%=id_proj%>"></div>
							<div class="controls">
			       				<button type="submit" class="btn btn-primary">Cadastrar</button>
	    					</div>
						</fieldset>
					</form> 
			</td>
		</tr>

		<tr>
			<td>
	 				<%
	      				if(!results_ftr.iterator().hasNext()){
	      			 %>
	      				  <div class="alert alert-info"> Você ainda não possui nenhuma função de dados cadastrada.</div>
		      
	          
	    			<%}else{%> 
	      			 <table  class="table table-hover">
	      			 	<tr>
                           
		                    <th>Nome FTR</th>
		                    <th>Nº DET</th>
		                    <th>Nome DET</th>
                	
            			</tr> 
           				 <tr>
				             <%int contaFtr = 0;
				             	for(Funcao ftr1 : results_ftr){ 
				                    out.println("<tr>");
				                    out.println("<td id='tabelaInformacaoNome'>");
				                    out.println("<a href ='Remover_FT_Atributos?ftr=" + ftr1.getNome()+ "&id_Ftr="+  contaFtr++ + "&id_FT=" + id_FT +"&tipo=FTR '>");
				                    out.println("<img src='IMG/DeleteRed.gif' alt='Deletar'/></a> "	+ ftr1.getNome() +"</td>");
				                    out.println("<td>" + ftr1.contaDados() +"</td>");
				                    out.println("<td>");%><%out.println("<table>");
				                    						int nDados = ftr1.contaDados();
				                    						for(int i = 0 ; i < nDados ; i++){
				                    								out.println("<tr>");
				                    								out.println("<td>" + ftr1.pegaDadoIndice(i)+ "</td>");
				                    								out.println("<td><a href ='Remover_FT_Atributos?ftr=" + ftr1.getNome() +"&id_FT=" +  id_FT + "&det=" + i + "&tipo=DET '>");
				                    								out.println("<img src='IMG/DeleteRed.gif' alt='Deletar'/> </a> </td>");
				                    								out.println("</tr>");
				                    						}
				                    							out.println("</table>");%><%	
				                    out.println("</td>");
				                    out.println("</tr>");
				                    }
				             %>
	      			 	
	      				</tr>
	                  </table>
	                   <%}%>
			</td>
			
</tr>
</table>

<%@include file="footer.jsp"%>