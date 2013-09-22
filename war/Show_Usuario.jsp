<%@include file="header.jsp"%>
 <h1 id="center">Usuários Cadastrados</h1>
 
 
     <table id="center" class="table table-hover">
     
       
            <!-- column headers-->
            <tr>
                           
                    <th>Nome</th>
                	<th>Sobrenome</th>
                	<th>E-mail</th>
            
            </tr> 
            <!-- column data -->
            <% List <Usuario> results = (List<Usuario>) request.getAttribute("entity");
                for( Usuario u1 : results){ 
            	    out.println("<tr>");
            		out.println("<td>" + u1.getNome() +"</td>");
            		out.println("<td>" + u1.getSobreNome() +"</td>");
            		out.println("<td>" + u1.getEmail() +"</td>");
            		out.println("</tr>");
            }
            	%>
            
        </table> 


<%@include file="footer.jsp"%>