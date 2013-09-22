package remocao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOFactory;

@SuppressWarnings("serial")
public class Remover_Funcao_TransacionalServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
    {
		long id_FT = Long.valueOf (req.getParameter ("id_FT"));
		long id_proj = Long.valueOf (req.getParameter ("id_proj"));
		DAOFactory.getFuncaoTransacionalDAO().remove(id_FT);
		
		resp.sendRedirect("/Show_Projeto_Info.jsp?id="+ id_proj);
		
		
    }
}
