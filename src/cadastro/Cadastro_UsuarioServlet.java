package cadastro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import DAO.DAOFactory;

import padocaproject.Usuario;
import utill.Crypto;




@SuppressWarnings("serial")
public class Cadastro_UsuarioServlet extends HttpServlet {
	
	static final int  MINIMO_SENHA =6; 
	String message = " ";	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	                throws IOException, ServletException {
		
		//Teste de cadastro
   	 	if(ValidarCampos(req)){
			 Usuario user = new Usuario();
			 user.setNome(req.getParameter("nome_Usuario"));
			 user.setEmail(req.getParameter("email"));
			 user.setSenha(Crypto.hash(req.getParameter("senha")));
			 user.setSobreNome(req.getParameter("Sobrenome_Usuario"));
	                   	
			 DAOFactory.getUsuarioDAO().put(user); 
			 resp.sendRedirect("/Login_Index.jsp");
	    	 
   	 	} else 
   	 	{
   	 		req.setAttribute("nome_Usuario", req.getParameter("nome_Usuario"));
   	 		req.setAttribute("Sobrenome_Usuario", req.getParameter("Sobrenome_Usuario"));
   	 		req.setAttribute("email", req.getParameter("email"));
	   	 	req.setAttribute("message", message);
			req.getRequestDispatcher("/Usuario.jsp").forward(req, resp);
	    	
	    } 
	       
		 
		 
	}
	
	public boolean ValidarCampos(HttpServletRequest req){
		
		//Testa Campos Vazios
		if(req.getParameter("nome_Usuario").isEmpty() || req.getParameter("Sobrenome_Usuario").isEmpty() || req.getParameter("email").isEmpty() || req.getParameter("senha").isEmpty() || req.getParameter("confirmSenha").isEmpty())
		{ 
			message = "Campos Vazio";
			return false;
		} // Testa se tenha tem menos de 6 caracteres
		else if (req.getParameter("senha").length() < MINIMO_SENHA)
		{
			message = "Senha deve possuir mais de " + MINIMO_SENHA + " Caracteres";
			return false;
		}
		else if(!req.getParameter("senha").equals(req.getParameter("confirmSenha")))
		{
			message = "Senhas digitadas são diferentes";
			return false;
		}
		else // Testa se já existe um usuário com o mesmo login.
		{
			List<Usuario> users = new ArrayList<Usuario>();
			
			users = DAOFactory.getUsuarioDAO().list();
			
			for(Usuario u1 : users){
				if(req.getParameter("email").equals(u1.getEmail())){
					message = "Login já existente";
					return false;
				}
			}
		}
			return true;
	}	 
		 
}	