package padocaproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utill.Crypto;

import configuracao.Configuracao;

import DAO.DAOFactory;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
		
		String message = " ";
		
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException
            {
			
		//Teste no campo
		
	if(ValidarCampos(req)){
		
		 List<Usuario> lista_usuarios = new ArrayList<Usuario>();
		 
		 lista_usuarios = DAOFactory.getUsuarioDAO().list();
		 
		 String senha = req.getParameter("UserPassword");
		 
		 for (Usuario u1 : lista_usuarios)
		 {
			 if(u1.getEmail().equals(req.getParameter("UserLogin")))
			 {
				 System.out.println("Login Encontrado");
				 
				 String hashSenha = Configuracao.getAmbienteHomologacao() ? senha : Crypto.hash(senha);
				 if(u1.getSenha().compareTo(hashSenha) == 0)
				 {
					 System.out.println("Senha Encontrada");
					 HttpSession session = req.getSession();
					 session.setAttribute("UserName", u1.getNome());
					 session.setAttribute("ID", u1.getId());
					 DAOFactory.getLoginUsuarioDAO().registraLoginSucesso(u1.getId());
					 req.getRequestDispatcher("/padocaproject.jsp").forward(req, resp);		 
				 }
			 }
		 }
	}
	else
	{
		 req.setAttribute("UserLogin", req.getParameter("UserLogin"));	
		 req.setAttribute("message", message);
		 req.getRequestDispatcher("/Login_Index.jsp").forward(req, resp);
	}
		
		 
	}
	
	public boolean ValidarCampos(HttpServletRequest req)
	{
		String senha = req.getParameter("UserPassword");
		
		if(req.getParameter("UserLogin").isEmpty() || req.getParameter("UserPassword").isEmpty() )
		{
			message = "Campos Vazios";
			return false;
		}
		else // testa se login e senha existem na base
		{
			Usuario user = DAOFactory.getUsuarioDAO().get("email", req.getParameter("UserLogin"));
			
		
			
			//Buscando Usuario e Login correspondentes na base
			if(user!=null)
			{
				//Testa se o usuário deve resetar a senha
				if(user.getForcaResetSenha()){
					message = "Faça Reset de Senha.";
					return false;
				}
				
				String hashSenha = Configuracao.getAmbienteHomologacao() ? senha : Crypto.hash(senha);
				
				if(user.getSenha().compareTo(hashSenha) == 0)
				{
					return true;
				}
				else// Caso a senha o login retorna false e grava um erro
				{
					DAOFactory.getLoginUsuarioDAO().registraLoginFalha(user.getId());
					message = "Login ou Senha Incorretos.";
					return false;
				}
			}		
			message = "Login ou Senha Incorretos.";
			return false;
		}
		
	}
}


