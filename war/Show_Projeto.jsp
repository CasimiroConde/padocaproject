<%@include file="header.jsp"%>

<h1>Projetos Cadastrados</h1>
 

 <table border="1">
     
       
            <!-- column headers-->
            <tr>
                           
                    <th>Nome</th>
                	<th>Usu�rio</th>
                	<th>Esfor�o em PF</th>
                	<th>Informa��es Adicionais</th>
                	
            </tr> 
            <!-- column data -->
            <% List <Projeto> results = (List<Projeto>) request.getAttribute("entity");
                for( Projeto p1 : results){ 
            	    out.println("<tr>");
            		out.println("<td>" + p1.getNome() +"</td>");
            		out.println("<td>" + p1.getUsuario() +"</td>");
            		out.println("<td>" + p1.getPontos_Funcao() +"</td>");
            		out.println("<td> <a href ='Show_Projeto_Info.jsp?session.setAttribute<%p1.geti'> Informa��es Projetos</a> </td>");
            		out.println("</tr>");
            }
            	%>
            
        </table> 
        
<%@include file="footer.jsp"%>