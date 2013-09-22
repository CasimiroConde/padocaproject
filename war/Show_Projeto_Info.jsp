<%@page import="funcaoTransacional.FuncoesTransacionais"%>
<%@page import="funcaoDados.FuncaoDados"%>
<%@include file="header.jsp"%>
<%
	Long id_proj = Long.valueOf (request.getParameter ("id"));
	String messageFuncaoDados = (String) request.getAttribute("messageFuncaoDados");
	String messageFuncaoTransacional = (String) request.getAttribute("messageFuncaoTransacional");
	String nomeFT = "";
	String nomeFD = "";
	if(request.getAttribute("nomeFT")!=null){
		nomeFT = (String) request.getAttribute("nomeFT");
	}
	if(request.getAttribute("nomeFD")!=null){
		nomeFD = (String) request.getAttribute("nomeFD");
	}
%>
<table id="center">
  	<tr>
    	<td>
     		<ul class="breadcrumb">
	 			<li class="active">Projeto: <a href="/Show_Projeto_Info.jsp?id=<%=id_proj%>"><%out.println(DAOFactory.getProjetoDAO().get(id_proj).getNome());%></a></li>
			</ul>
   		</td>
  	</tr>
	<tr>
    	<td>
    		<form action="Cadastro_Funcao_Dados" method="post"  class="form-horizontal">
			<fieldset>
				<legend>Funcão de Dados:</legend>
					<div>
						 <%
 							if(messageFuncaoDados != null){
 							out.println("<div class='alert alert-error'>");
  							out.println(messageFuncaoDados +"</div>");}
						 %>
					</div>
					<div class="control-group">
		      			<label class="control-label" for="inputNome">Nome: </label>
		      				<div class="controls">
		       					<input type="text" name="nome_FD" value="<%=nomeFD%>"  class="input" placeholder="Nome da Função de Dados"/>      
	 						</div>
	 				</div>
	 				<div class="control-group">
		      			<label class="control-label" for="inputTipo">Tipo: </label>
		      				<div class="controls">
		       					<select  name="Tipo_FD">
 									<option value="ILF" >ILF</option>
  			    			 		<option value="EIF" >EIF</option>
 		   						</select>
 		   					</div>
	 				</div>
 		   			<div><input type="hidden" name="IDProj" value="<%=id_proj%>"></div>
 		   			<div class="control-group">
	   					<div class="controls">
			       			<button type="submit" class="btn btn-primary">Cadastrar</button>
	    				</div>
	 				</div>
			</fieldset>
		</form> 
	</td>
    <td>
    	<form action="Cadastro_Funcao_Transacional" method="post" class="form-horizontal">
			<fieldset>
				<legend>Funcão Transacional:</legend>
					<div>
						 <%
 							if(messageFuncaoTransacional != null){
 							out.println("<div class='alert alert-error'>");
  							out.println(messageFuncaoTransacional +"</div>");}
						 %>
					</div>
					<div class="control-group">
		      			<label class="control-label" for="inputNome">Nome: </label>
		      				<div class="controls">
		       					<input type="text" name="nome_FT" value="<%=nomeFT%>"  class="input" placeholder="Nome da Função Transacional"/>      
	 						</div>
	 				</div>
	 				<div class="control-group">
		      			<label class="control-label" for="inputTipo">Tipo: </label>
		      				<div class="controls">
		       					<select  name="Tipo_FT">
 									<option value="EI">EI</option>
  			    			 		<option value="EO">EO</option>
						     		<option value="EQ">EQ</option>
 		   						</select>
 		   					</div>
	 				</div>
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
		<table class="table table-hover">
		  <%
		  	List <FuncaoDados> results_fd = DAOFactory.getFuncaoDadosDAO().list(DAOFactory.getFuncaoDadosDAO().exactFilter("Projeto", FilterOperator.EQUAL, id_proj));
		            		
		            	if(results_fd.isEmpty()){
		  %>  
			
			<div class="alert alert-info"> Sem Função de Dados cadastrada.</div>
			      
		           
		    <%
			                     	} 
			                     	                     	                     	                     	                     	else
			                     	                     	                     	                     	                     	{
			                     %> 
			
			
			 <!-- column headers-->
		            <tr>
		                           
		                    <th>Nome</th>
		                    <th>Tipo</th>
		                    <th>PF</th>
		                	<th>RET</th>
		                	<th>DET</th>
		                	<th>Mais</th>	
		                	
		            </tr> 
		            <!-- column data -->
		            <%
		            	for( FuncaoDados fd1 : results_fd){ 
		                                    		int quantidadeDados = 0; 
		                                            out.println("<tr>");
		                                            out.println("<td id='tabelaInformacaoNome'>");
		                                            out.println("<a href ='Remover_Funcao_Dados?id_proj="+ id_proj + "&id_FD=" + fd1.getId() +"'>");
		                                            out.println("<img src='IMG/DeleteRed.gif' alt='Deletar'/></a> ");
		                                            out.println("<a href ='AlterarFuncaoDados.jsp?id_FD=" + fd1.getId() +"'>");
		                                    		out.println("<img src='IMG/editar.gif' alt='Alterar'/></a> "+ fd1.getNome() +"</td>");
		                                            out.println("<td>" + fd1.getTipo() +"</td>");
		                                            out.println("<td>" + fd1.calculaPontosFuncao() +"</td>");
		                                            out.println("<td>" + fd1.contaRegistros() +"</td>");
		                                                          		
		                                            for(int i = 0; i < fd1.contaRegistros(); i++){
		                            					quantidadeDados += fd1.pegaRegistroIndice(i).contaDados();
		                                            }
		                                            out.println("<td>" + quantidadeDados +"</td>");
		                                            out.println("<td> <a href ='Show_FD_Info.jsp?id_proj="+ id_proj + "&id_FD=" + fd1.getId() +"'>");
		                                            out.println("Info FD</a> </td>");
		                                            out.println("</tr>");
		                                                                                         }
		                                                                        	
		                                                                        }
		            %>
		 </table>
    </td>
    <td>
		<table class="table table-hover">
		  <%
		  	List <FuncoesTransacionais> results_ft = DAOFactory.getFuncaoTransacionalDAO().list(DAOFactory.getFuncaoTransacionalDAO().exactFilter("Projeto", FilterOperator.EQUAL, id_proj));
		  		
		  	if(results_ft.isEmpty()){
		  %>  
			
			<div class="alert alert-info"> Sem Função Transacional cadastrada.</div>
			      
		           
		    <%
			                     	} 
			                     	else
			                     	{
			                     %> 
			
			
			 <!-- column headers-->
		            <tr>
		                           
		                    <th>Nome</th>
		                    <th>Tipo</th>
		                    <th>PF</th>
		                	<th>FTR</th>
		                	<th>DET</th>
		                	<th>Mais</th>
		                	
		            </tr> 
		            <!-- column data -->
		            <%
		            	for( FuncoesTransacionais ft1 : results_ft){ 
		            											int quantidadeDados = 1;  // Inicia com 1 pelos det de Mensagem
		                                                	   	out.println("<tr>");
		                                                	   	out.println("<td id='tabelaInformacaoNome'>");
		                                                	    out.println("<a href ='Remover_Funcao_Transacional?id_proj="+ id_proj + "&id_FT=" + ft1.getId() +"'>");
		                                                		out.println("<img src='IMG/DeleteRed.gif' alt='Deletar'/></a>");
		                                                		out.println("<a href ='AlterarFuncaoTransacional.jsp?id_FT=" + ft1.getId() +"'>");
		    		                                    		out.println("<img src='IMG/editar.gif' alt='Alterar'/></a> "+ ft1.getNome() +"</td>");
		                                                		out.println("<td>" + ft1.getTipo() +"</td>");
		                                                		out.println("<td>" + ft1.calculaPontosFuncao() +"</td>");
		                                                		out.println("<td>" + ft1.contaFuncao() +"</td>");
		                                                		for(int i = 0; i < ft1.contaFuncao(); i++){
		    		                            					quantidadeDados += ft1.pegaFuncaoIndice(i).contaDados();
		    		                                            }
		                                                		out.println("<td>" + quantidadeDados +"</td>"); 
		                                                		out.println("<td> <a href ='Show_FT_Info.jsp?id_proj="+ id_proj + "&id_FT="+ ft1.getId()+ "'>");
		                                                		out.println("Info FT</a> </td>");
		                                                		out.println("</tr>");
		                                                     }
		                                    	
		                                    }
		            %>
		
		</table>
	</td>
  </tr>
</table>



<%@include file="footer.jsp"%>