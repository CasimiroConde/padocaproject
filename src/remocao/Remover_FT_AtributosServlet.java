package remocao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funcaoTransacional.Funcao;
import funcaoTransacional.FuncoesTransacionais;

import DAO.DAOFactory;

@SuppressWarnings("serial")
public class Remover_FT_AtributosServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
    {
		String ftr = req.getParameter ("ftr");
		long idFt = Integer.valueOf(req.getParameter("id_FT"));
		
		
		String tipo = req.getParameter ("tipo");
		FuncoesTransacionais ft = DAOFactory.getFuncaoTransacionalDAO().get(idFt);
		
		
		if(tipo.equals("FTR"))
		{
			int idFtr = Integer.valueOf(req.getParameter("id_Ftr"));
			ft.removeFuncao(idFtr);
			DAOFactory.getFuncaoTransacionalDAO().put(ft);
		}
		else
		{
			// Remove o DET de um FTR em um Funcao Transacional
			int det = Integer.valueOf(req.getParameter ("det"));
			Funcao ftrFt = ft.pegaFuncaoNome(ftr);
			ftrFt.removeDado(det);
			
			DAOFactory.getFuncaoTransacionalDAO().put(ft);
		}
		
		resp.sendRedirect("/Show_FT_Info.jsp?id_proj="+ft.getIdProjeto()+"&id_FT=" +idFt);
		
		
    }
		
}
