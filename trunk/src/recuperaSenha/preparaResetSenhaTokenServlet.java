package recuperaSenha;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import padocaproject.Usuario;
import DAO.DAOFactory;

@SuppressWarnings("serial")
public class preparaResetSenhaTokenServlet extends HttpServlet{
	
	String message = " ";	
	private static final int VALIDATE_TOKEN_SENHA = 72;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
		

	//Teste de cadastro
		if(ValidarCampos(req)){
				String token = req.getParameter("token");
				String email = req.getParameter("email_Usuario");
				
				
				resp.sendRedirect("/ResetSenha.jsp?token=" + token + "&email_Usuario=" + email);
		 
		} else 
		{
			req.setAttribute("email", req.getParameter("email_Usuario"));
		 	req.setAttribute("message", message);
		 	req.getRequestDispatcher("/RecuperaSenha.jsp").forward(req, resp);
		
		} 
   
 
 
	}
	
public boolean ValidarCampos(HttpServletRequest req){
		
		//Testa Campos Vazios
		if(req.getParameter("email_Usuario").isEmpty())
		{ 
			message = "Email Não Encontrado";
			return false;
		}
		
		if(req.getParameter("token").isEmpty()){
			message = "Token Não Encontrado";
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
