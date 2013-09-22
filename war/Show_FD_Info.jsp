<%@page import="funcaoDados.FuncaoDados"%>
<%@page import="funcaoDados.RegistroDados"%>
<%@include file="header.jsp"%>
<%
	Long id_proj = Long.valueOf (request.getParameter ("id_proj"));
  	Long id_FD = Long.valueOf (request.getParameter ("id_FD"));
  	FuncaoDados fd = DAOFactory.getFuncaoDadosDAO().get(id_FD);
  	Iterable<RegistroDados> results_ret = fd.getRegistros();
  	String message_erro = (String) request.getAttribute("messageError");
  	String nomeAtributo = "";
  	if(request.getAttribute("nomeAtributo")!=null){
		  nomeAtributo = (String) request.getAttribute("nomeAtributo");
 	 }
%>

 <script type="text/javascript">
 var x = 0;
 function hide(x){
	 if(x == 0) {
         document.getElementById("Nome_Atributo_FD").style.visibility="hidden";
     }
     if(x == 1) {
         document.getElementById("Nome_Atributo_FD").style.visibility="visible";
     }
 }
 </script>
<table id="center_Funcao">
   	<tr>
    	<td>
	  		<ul class="breadcrumb">
	 			<li>Projeto: <a href="/Show_Projeto_Info.jsp?id=<%=id_proj%>"><%out.println(DAOFactory.getProjetoDAO().get(id_proj).getNome());%></a> <span class="divider">/</span></li>
				<li class="active">Função de Dados:<a href="/Show_FD_Info.jsp?id_proj=<%=id_proj%>&id_FD=<%=id_FD%>"><%out.println(DAOFactory.getFuncaoDadosDAO().get(id_FD).getNome());%></a></li>
			</ul>
		</td>
	</tr>
</table>

<table id="center_Funcao">

	<tr>
   	 <td>
    	<form action="Cadastro_FD_Atributos" method="post" class="form-horizontal">
			<fieldset>
				<legend>Cadastrar Atributos:</legend>
					<div>
						 <%
 							if(message_erro!= null){
 							out.println("<div class='alert alert-error'>");
  							out.println(message_erro +"</div>");}
						 %>
					</div>
					<div class="control-group">
		      			<label class="control-label" for="inputNome">Nome: </label>
		      				<div class="controls">
		       					<input type="text" name="nome_Atributo" value="<%=nomeAtributo%>" class="input" placeholder="Nome da Função de Dados"/>      
	 						</div>
	 				</div>
	 				<div class="control-group">
		      			<label class="control-label" for="inputTipo">Tipo: </label>
		      				<div class="controls">
		       					<input type="radio" onclick="hide(0)" name="tipo" value="RET" checked="checked"/> RET 
		       					<input type="radio" onclick="hide(1)" name="tipo" value="DET"/> DET      
	 						</div>
	 				</div>
	 				<div  id="Nome_Atributo_FD" class="control-group">	 			
			      			<label class="control-label" for="inputNome_RET"> Nome RET: </label>
			      				<div  class="controls">
			       					<select  name="nome_Ret">
										<%
											for(RegistroDados ret1 : results_ret){
												out.println("<option value='" + ret1.getNome() + "'>" + ret1.getNome() + "</option>");}
										%>			 									   					
	 		   						</select>
	 		   					</div>
	 				</div>	
		 				   		
	 		   		<div><input type="hidden" name="IDFD" value="<%=id_FD%>"></div>
	 		   		<div><input type="hidden" name="IDProj" value="<%=id_proj%>"></div>
					<div class="control-group">
	   					<div class="controls">
			       			<button type="submit" class="btn btn-primary">Cadastrar</button>
	    				</div>
	 				</div>
				</fieldset>
			</form> 
	</td>
   </tr>
   <tr>
   <td>
   <%
   	if(!results_ret.iterator().hasNext()){
   %>
   		<div class="alert alert-info">Favor cadastre o Primeiro Ret dessa Função de dados.</div>
   		
   	<%
   		   		}else{
   		   	%>
   		<table class="table table-hover">
   			<tr>
                           
                    <th>Nome RET</th>
                    <th>Nº DET</th>
                    <th>Nome DET</th>
                	
            </tr> 
            <tr>
             <%int contaRet = 0;
             	for(RegistroDados ret1 : results_ret){ 
                    out.println("<tr>");
                    out.println("<td id='tabelaInformacaoNome'>");
                    out.println("<a href ='Remover_FD_Atributos?ret=" + ret1.getNome()+ "&id_Ret="+  contaRet++ + "&id_FD=" + id_FD +"&tipo=RET '>");
                    out.println("<img src='IMG/DeleteRed.gif' alt='Deletar'/></a> "	+ ret1.getNome() +"</td>");
                    out.println("<td>" + ret1.contaDados() +"</td>");
                    out.println("<td>");%><%out.println("<table>");
                    						int nDados = ret1.contaDados();
                    						for(int i = 0 ; i < nDados ; i++){
                    								out.println("<tr>");
                    								out.println("<td>" + ret1.pegaDadoIndice(i)+ "</td>");
                    								out.println("<td><a href ='Remover_FD_Atributos?ret=" + ret1.getNome() +"&id_FD=" +  id_FD + "&det=" + i + "&tipo=DET '>");
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

<script type="text/javascript">
	hide(0);
</script>

<%@include file="footer.jsp"%>