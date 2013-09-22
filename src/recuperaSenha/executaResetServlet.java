package recuperaSenha;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import padocaproject.Usuario;
import utill.Crypto;
import DAO.DAOFactory;

@SuppressWarnings("serial")
public class executaResetServlet extends HttpServlet{
	
	String message = " ";	
	static final int  MINIMO_SENHA =6; 
	private static final int VALIDATE_TOKEN_SENHA = 72;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
		

	//Teste de cadastro
		if(ValidarCampos(req)){
				Usuario user = DAOFactory.getUsuarioDAO().get("email", (String) req.getParameter("email_Usuario"));
				
				user.setSenha(Crypto.hash(req.getParameter("senha_Usuario1")));
				user.setDeveTrocarSenha(false);
				user.setForcaResetSenha(false);
				
				DAOFactory.getUsuarioDAO().put(user);	
				resp.sendRedirect("/padocaproject.jsp");
		 
		} else 
		{
			req.setAttribute("email", req.getParameter("email_Usuario"));
		 	req.setAttribute("message", message);
		 	req.getRequestDispatcher("/ResetSenha.jsp").forward(req, resp);
		
		} 
   
 
 
	}
	
public boolean ValidarCampos(HttpServletRequest req){
		
		//Testa Email Vazios
		if(req.getParameter("email_Usuario").isEmpty())
		{ 
			message = "Email Não Encontrado";
			return false;
		}
		
		//Testa Token Vazios
		if(req.getParameter("token").isEmpty()){
			message = "Token Não Encontrado";
			return false;
		}
		
		//Testa Campos Senha Vazios
		if(req.getParameter("senha_Usuario1").isEmpty() || req.getParameter("senha_Usuario2").isEmpty()){
			message = "Campos Vazios";
			return false;
		}
		
		//Testa Diferença entre Senhas 
		if(!req.getParameter("senha_Usuario1").equals(req.getParameter("senha_Usuario2"))){
			message = "Senhas estão Diferentes";
			return false;
		}
		
		// Testa se tenha tem menos de 6 caracteres
		if (req.getParameter("senha_Usuario1").length() < MINIMO_SENHA)
		{
			message = "Senha deve possuir mais de " + MINIMO_SENHA + " Caracteres";
			return false;
		}
		
		Usuario user = DAOFactory.getUsuarioDAO().get("email", (String) req.getParameter("email_Usuario"));
		
		if(user == null){
			message = "Usuário não encontrado";
			return false;
		}
		
		boolean valido = DAOFactory.getTokenSenhaUsuarioDAO().verificaTokenTrocaSenha(user.getId(), req.getParameter("token"), VALIDATE_TOKEN_SENHA);
			
		if(!valido){
			message = "Token não é válido";
			return false;
		}
		
		
			return true;
	}
	
}
