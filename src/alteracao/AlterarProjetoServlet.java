package alteracao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import DAO.DAOFactory;

import padocaproject.Projeto;




@SuppressWarnings("serial")
public class AlterarProjetoServlet extends HttpServlet {
	
	String message = " ";	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	                throws IOException, ServletException {
		
		Projeto antigo = DAOFactory.getProjetoDAO().get(Long.valueOf(req.getParameter("id_Proj")));
		
		//Teste de cadastro
   	 	if(ValidarCampos(req)){
   	 	
			antigo.setNome(req.getParameter("novo_Nome_Projeto"));
			
         		 
			DAOFactory.getProjetoDAO().put(antigo); 
			
			resp.sendRedirect("/padocaproject.jsp");
	    	 
   	 	} else 
   	 	{
	   	 	req.setAttribute("message_Alteracao", message);
			req.getRequestDispatcher("/AlterarProjeto.jsp?id=" + antigo.getId()).forward(req, resp);
	    	
	    } 
	       
		 
		 
	}
	
	public boolean ValidarCampos(HttpServletRequest req){
		
		//Testa Campos Vazios
		if(req.getParameter("novo_Nome_Projeto").isEmpty())
		{ 
			message = "Campos Vazio";
			return false;
		} 
		
		Projeto proj = DAOFactory.getProjetoDAO().get(Long.valueOf(req.getParameter("id_Proj")));
		if(proj == null)
		{
			message = "Projeto não existente";
			return false;
		}
			return true;
	}	 
		 
}	