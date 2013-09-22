package alteracao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import funcaoDados.FuncaoDados;
import funcaoTransacional.FuncoesTransacionais;

import DAO.DAOFactory;




@SuppressWarnings("serial")
public class AlterarFuncaoTransacionalServlet extends HttpServlet {
	
	String message = " ";	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	                throws IOException, ServletException {
		
		FuncoesTransacionais antigo = DAOFactory.getFuncaoTransacionalDAO().get(Long.valueOf(req.getParameter("id_FT")));
		
		//Teste de cadastro
   	 	if(ValidarCampos(req)){
   	 	
			antigo.setNome(req.getParameter("novo_Nome_FT"));
			
         		 
			DAOFactory.getFuncaoTransacionalDAO().put(antigo); 
			
			resp.sendRedirect("/Show_Projeto_Info.jsp?id="+ antigo.getIdProjeto());
	    	 
   	 	} else 
   	 	{
	   	 	req.setAttribute("message_Alteracao", message);
			req.getRequestDispatcher("/AlterarFuncaoTransacional.jsp?id=" + antigo.getId()).forward(req, resp);
	    	
	    } 
	       
		 
		 
	}
	
	public boolean ValidarCampos(HttpServletRequest req){
		
		//Testa Campos Vazios
		if(req.getParameter("novo_Nome_FT").isEmpty())
		{ 
			message = "Campos Vazio";
			return false;
		} 
		
		FuncoesTransacionais ft = DAOFactory.getFuncaoTransacionalDAO().get(Long.valueOf(req.getParameter("id_FT")));
		if(ft == null)
		{
			message = "Função Transacional não existente";
			return false;
		}
			return true;
	}	 
		 
}	