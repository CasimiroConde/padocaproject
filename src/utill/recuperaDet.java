package utill;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funcaoDados.FuncaoDados;

import json.JSONArray;
import json.JSONObject;
import DAO.DAOFactory;

@SuppressWarnings("serial")
public class recuperaDet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException 
            {	
				long idFD = Long.valueOf(req.getParameter("ret"));
				FuncaoDados fd1 = DAOFactory.getFuncaoDadosDAO().get(idFD);
				
				JSONArray result = new JSONArray();
				
				for(int i = 0 ; i <fd1.contaRegistros() ; i++ ){
					for(int j = 0 ; j < fd1.pegaRegistroIndice(i).contaDados(); j++){
					  result.add(fd1.pegaRegistroIndice(i).pegaDadoIndice(j));	
					}		
				} 
				
				JSONObject resultado = new JSONObject();
				resultado.add("Result", "OK");
				resultado.add("data", result);
				resp.getWriter().print(resultado.toString());
			}
}
