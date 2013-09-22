package remocao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOFactory;

@SuppressWarnings("serial")
public class Remover_Funcao_DadosServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
    {
		long id_FD = Long.valueOf (req.getParameter ("id_FD"));
		long id_proj = Long.valueOf (req.getParameter ("id_proj"));
		DAOFactory.getFuncaoDadosDAO().remove(id_FD);
		resp.sendRedirect("/Show_Projeto_Info.jsp?id="+ id_proj);
		
		
    }
}
