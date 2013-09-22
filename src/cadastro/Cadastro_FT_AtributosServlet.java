package cadastro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funcaoTransacional.Funcao;
import funcaoTransacional.FuncoesTransacionais;

import DAO.DAOFactory;

@SuppressWarnings("serial")
public class Cadastro_FT_AtributosServlet extends HttpServlet {

	String message = "";
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException  {
		
	long idFuncaoTransacional = Long.valueOf(req.getParameter("IDFT"));
	FuncoesTransacionais ft = DAOFactory.getFuncaoTransacionalDAO().get(idFuncaoTransacional);
	String tipo = req.getParameter("tipo");	
	String ftr = req.getParameter("nome_FD");
	String det = req.getParameter("nome_DET");
	
	if(ValidarCampos(req, ft, tipo)){
		if(ft.pegaFuncaoNome(ftr) != null){
			Funcao ftr1 = ft.pegaFuncaoNome(ftr);
			ftr1.adicionaDado(det);
			
			DAOFactory.getFuncaoTransacionalDAO().put(ft);
		}
		else
		{
			Funcao ftr1 = new Funcao();
			ftr1.setNome(ftr);
			ftr1.adicionaDado(det);
			
			ft.adicionaFuncao(ftr1);
			DAOFactory.getFuncaoTransacionalDAO().put(ft);
			
		}
		resp.sendRedirect("/Show_FT_Info.jsp?id_proj="+ft.getIdProjeto()+"&id_FT=" +idFuncaoTransacional);
	}
	else
	{
		req.setAttribute("messageError", message);
		req.getRequestDispatcher("/Show_FT_Info.jsp?id_proj="+ft.getIdProjeto()+"&id_FT=" +idFuncaoTransacional).forward(req, resp);
	}	

}
	
public boolean ValidarCampos(HttpServletRequest req, FuncoesTransacionais ft, String tipo){
	
	String nome_DET = req.getParameter("nome_DET");
	String nome_FD = req.getParameter("nome_FD");
	
	if(nome_FD == null){
		message = "Favor informar o FD";
		return false;
	}
	
	if(nome_DET == null){
		message = "Favor informar o DET";
		return false;
	}
	
	Funcao ftr1 = ft.pegaFuncaoNome(nome_FD);
	
	if(ftr1 != null){
		int nDados = ftr1.contaDados();
		
		for(int i = 0; i<nDados ; i++){
			if(nome_DET.equals(ftr1.pegaDadoIndice(i))){
				message = "DET já cadastrado";
				return false;
			}
			
		}
	}
	
return true;
}
		
	
}
