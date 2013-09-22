package alteracao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import DAO.DAOFactory;

import padocaproject.Usuario;




@SuppressWarnings("serial")
public class AlterarUsuarioServlet extends HttpServlet {
	
	String message = " ";	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	                throws IOException, ServletException {
		
		//Teste de cadastro
   	 	if(ValidarCampos(req)){
   	 		Usuario antigo = DAOFactory.getUsuarioDAO().get(Long.valueOf(req.getParameter("id_Usuario")));
   	 	
			antigo.setNome(req.getParameter("novo_Nome_Usuario"));
			antigo.setSobreNome(req.getParameter("novo_SobreNome_Usuario"));
         		 
			DAOFactory.getUsuarioDAO().put(antigo); 
			
			HttpSession session = req.getSession();
			session.setAttribute("UserName", antigo.getNome());
			
			resp.sendRedirect("/padocaproject.jsp");
	    	 
   	 	} else 
   	 	{
	   	 	req.setAttribute("message_Alteracao", message);
			req.getRequestDispatcher("/AlterarUsuario.jsp").forward(req, resp);
	    	
	    } 
	       
		 
		 
	}
	
	public boolean ValidarCampos(HttpServletRequest req){
		
		//Testa Campos Vazios
		if(req.getParameter("novo_Nome_Usuario").isEmpty() || req.getParameter("novo_SobreNome_Usuario").isEmpty())
		{ 
			message = "Campos Vazio";
			return false;
		} 
		
		Usuario user = DAOFactory.getUsuarioDAO().get(Long.valueOf(req.getParameter("id_Usuario")));
		if(user == null)
		{
			message = "Usuário não existente";
			return false;
		}
			return true;
	}	 
		 
}	