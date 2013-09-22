package alteracao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import funcaoDados.FuncaoDados;
import funcaoDados.RegistroDados;

import DAO.DAOFactory;




@SuppressWarnings("serial")
public class AlterarFuncaoDadosServlet extends HttpServlet {
	
	String message = " ";	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	                throws IOException, ServletException {
		
		FuncaoDados antigo = DAOFactory.getFuncaoDadosDAO().get(Long.valueOf(req.getParameter("id_FD")));
		
		//Teste de cadastro
   	 	if(ValidarCampos(req)){
   	 		
   	 		RegistroDados registro = antigo.pegaRegistroNome(antigo.getNome());
   	 		registro.setNome(req.getParameter("novo_Nome_FD"));
   	 		
   	 		antigo.removeRegistro(0);
   	 		
   	 		antigo.adicionaRegistro(registro);
			antigo.setNome(req.getParameter("novo_Nome_FD"));
			
         		 
			DAOFactory.getFuncaoDadosDAO().put(antigo); 
			
			resp.sendRedirect("/Show_Projeto_Info.jsp?id="+ antigo.getIdProjeto());
	    	 
   	 	} else 
   	 	{
	   	 	req.setAttribute("message_Alteracao", message);
			req.getRequestDispatcher("/AlterarFuncaoDados.jsp?id=" + antigo.getId()).forward(req, resp);
	    	
	    } 
	       
		 
		 
	}
	
	public boolean ValidarCampos(HttpServletRequest req){
		
		//Testa Campos Vazios
		if(req.getParameter("novo_Nome_FD").isEmpty())
		{ 
			message = "Campos Vazio";
			return false;
		} 
		
		FuncaoDados fd = DAOFactory.getFuncaoDadosDAO().get(Long.valueOf(req.getParameter("id_FD")));
		if(fd == null)
		{
			message = "Projeto não existente";
			return false;
		}
			return true;
	}	 
		 
}	