package recuperaSenha;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import configuracao.Configuracao;


import padocaproject.Usuario;
import services.GerenciadorEmail;
import utill.Crypto;
import DAO.DAOFactory;

@SuppressWarnings("serial")
public class recuperaSenhaServlet extends HttpServlet {
	
	String message = " ";	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

	//Teste de cadastro
		if(ValidarCampos(req)){
		
		Usuario usuario = DAOFactory.getUsuarioDAO().get("email", req.getParameter("email_Usuario"));
			
		String token = geraTokenTrocaSenha();
		DAOFactory.getTokenSenhaUsuarioDAO().armazenaTokenTrocaSenha(usuario.getId(), token);
	
		String url = Configuracao.getHostname() + "/Prepara_Reset?token=" + token + "&email_Usuario=" + usuario.getEmail();
		String corpo = "<p>Você está recebendo este e-mail porque pediu a reinicializa\u00E7\u00E3o da senha de acesso ao ";
		corpo += "sistema PADOCA. Clique <a href='" + url + "'>aqui</a> para acessar a página de troca de senha.</p>";
		boolean envioOK = GerenciadorEmail.getInstance().envia(usuario.getNome(), usuario.getEmail(), "Reinicializacao de senha de acesso ao sistema PADOCA", corpo);
		
		if(!envioOK){
			req.setAttribute("message", "Ocorreu um Erro ao Enviar sua senha");
		 	req.getRequestDispatcher("/RecuperaSenha.jsp").forward(req, resp);
		}
			
		resp.sendRedirect("/Login_Index.jsp");
		 
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
			message = "Campos Vazio";
			return false;
		}
		
		Usuario user = DAOFactory.getUsuarioDAO().get("email", (String) req.getParameter("email_Usuario"));
		
		if(user == null){
			message = "Email não encontrado";
			return false;
		}
				
		
		
			return true;
	}	 
	
	private String geraTokenTrocaSenha()
	{
		StringBuilder sb = new StringBuilder();
		Random r = Crypto.createSecureRandom();

		for (int i = 0; i < 256; i++)
		{
			char c = (char) ('A' + r.nextInt(26));
			sb.append(c);
		}

		return sb.toString();
	}

}
