package remocao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funcaoDados.FuncaoDados;
import funcaoDados.RegistroDados;

import DAO.DAOFactory;

@SuppressWarnings("serial")
public class Remover_FD_AtributosServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
    {
		String ret = req.getParameter ("ret");
		long idFd = Integer.valueOf(req.getParameter("id_FD"));
		
		
		String tipo = req.getParameter ("tipo");
		FuncaoDados fd = DAOFactory.getFuncaoDadosDAO().get(idFd);
		
		
		if(tipo.equals("RET"))
		{
			int idRet = Integer.valueOf(req.getParameter("id_Ret"));
			fd.removeRegistro(idRet);
			DAOFactory.getFuncaoDadosDAO().put(fd);
		}
		else
		{
			// Remove o DET de um RET em um Funcao de Dados
			int det = Integer.valueOf(req.getParameter ("det"));
			RegistroDados retFd = fd.pegaRegistroNome(ret);
			retFd.removeDado(det);
			
			DAOFactory.getFuncaoDadosDAO().put(fd);
		}
		
		resp.sendRedirect("/Show_FD_Info.jsp?id_proj="+fd.getIdProjeto()+"&id_FD=" +idFd);
		
		
    }
		
}
