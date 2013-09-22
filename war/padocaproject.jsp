<%@include file="header.jsp"%>

<h1 id="center">Projeto Padoca </h1>
<br>
<table id="center" class="table table-hover">
  <%
  	Long ID = (Long) session.getAttribute("ID");
      List <Projeto> results = DAOFactory.getProjetoDAO().list(DAOFactory.getProjetoDAO().exactFilter("Usuario", FilterOperator.EQUAL, ID));
  		
  	if(results.isEmpty()){
  %>  
	<tr>
		<td>
			<div class="alert alert-info"> Bem Vindo a nossa ferramenta de Contagem de Pontos de Função
			<br> Favor cadastre seu primeiro projeto.</div> 
		</td>
    </tr>
           
    <%} 
	else
	{ %> 
	
	
	 <!-- column headers-->
            <tr>
                           
                    <th>Projeto</th>
                	<th>Esforço em PF</th>
                	<th>Informações Adicionais</th>
                	
            </tr> 
            <!-- column data -->
            <%  
                 for( Projeto p1 : results){ 
            	    out.println("<tr>");
            		out.println("<td id='tabelaInformacaoNomeProjeto'>");
            		out.println("<a href ='Remover_Projeto?id="+p1.getId()+"'>");
            		out.println("<img src='IMG/DeleteRed.gif' alt='Deletar'/></a> ");
            		out.println("<a href ='exportXML?id="+p1.getId()+"' target='_blank'>");
            		out.println("<img src='IMG/document_export.gif' alt='Exportar'/></a>");
            		out.println("<a href ='AlterarProjeto.jsp?id="+p1.getId()+"'>");
            		out.println("<img src='IMG/editar.gif' alt='Alterar'/></a> "+ p1.getNome() +"</td>");
            		out.println("<td>" + p1.Calcular_PF() +"</td>");
            		out.println("<td> <a href ='Show_Projeto_Info.jsp?id="+p1.getId()+"'>");
            		out.println("Informações do Projeto</a> </td>");
            		out.println("</tr>");
            		
            }
	
} %>
            
        </table>
<%@include file="footer.jsp"%>