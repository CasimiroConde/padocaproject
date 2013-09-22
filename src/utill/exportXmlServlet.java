package utill;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.appengine.api.datastore.Query.FilterOperator;

import funcaoDados.FuncaoDados;
import funcaoDados.RegistroDados;
import funcaoTransacional.Funcao;
import funcaoTransacional.FuncoesTransacionais;

import padocaproject.Projeto;
import DAO.DAOFactory;

@SuppressWarnings("serial")
public class exportXmlServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
    {
			
		try {
			
			long idProj = Integer.valueOf(req.getParameter("id"));
			
			Projeto proj =DAOFactory.getProjetoDAO().get(idProj);
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elemento Projeto
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Projeto");
			doc.appendChild(rootElement);
			rootElement.setAttribute("Nome", proj.getNome());
			rootElement.setAttribute("id", proj.getId().toString());
	 
			// staff elements Funcoes de Dados
			List<FuncaoDados> listFd = DAOFactory.getFuncaoDadosDAO().list(DAOFactory.getFuncaoDadosDAO().exactFilter("Projeto", FilterOperator.EQUAL, proj.getId()));
			
			for(FuncaoDados fd1 : listFd){
				
				//Indica a Funcao de Dados	
				Element staff = doc.createElement("Funcao_de_Dados");
				rootElement.appendChild(staff);
				staff.setAttribute("Nome", fd1.getNome());
				staff.setAttribute("Tipo", fd1.getTipo());
				staff.setAttribute("id", fd1.getId().toString());
				
				//Impressão dos atributos de cada RET de uma Funcao de Dados
				for(int i = 0; i < fd1.contaRegistros(); i++){
					Element ret = doc.createElement("ret");
					RegistroDados ret1 = fd1.pegaRegistroIndice(i);
					staff.appendChild(ret);
					ret.setAttribute("Nome",ret1.getNome().toString());
						
					//Impressão de cada DET de um RET
					for(int j = 0; j < ret1.contaDados(); j++){
						Element det = doc.createElement("det");
						String det1 = ret1.pegaDadoIndice(j);
						det.appendChild(doc.createTextNode(det1));
						ret.appendChild(det);
					}
				}
			}
			
			// staff elements Funcoes Transacional
			List<FuncoesTransacionais> listFt = DAOFactory.getFuncaoTransacionalDAO().list(DAOFactory.getFuncaoTransacionalDAO().exactFilter("Projeto", FilterOperator.EQUAL, proj.getId()));
			
			for(FuncoesTransacionais ft1 : listFt){
				
				//Indica a Funcao de Dados	
				Element staff = doc.createElement("Funcao_Transacionais");
				rootElement.appendChild(staff);
				staff.setAttribute("Nome", ft1.getNome());
				staff.setAttribute("Tipo", ft1.getTipo());
				staff.setAttribute("id", ft1.getId().toString());
				
				//Impressão dos atributos de cada RET de uma Funcao de Dados
				for(int i = 0; i < ft1.contaFuncao(); i++){
					Element ftr = doc.createElement("ftr");
					Funcao ftr1 = ft1.pegaFuncaoIndice(i);
					staff.appendChild(ftr);
					ftr.setAttribute("Nome",ftr1.getNome().toString());
					
						
					//Impressão de cada DET de um RET
					for(int j = 0; j < ftr1.contaDados(); j++){
						Element det = doc.createElement("det");
						String det1 = ftr1.pegaDadoIndice(j);
						det.appendChild(doc.createTextNode(det1));
						ftr.appendChild(det);
					}
				}
			}
	 
			// escrevendo em uma nova tela o XML do projeto
			resp.setContentType("text/xml");
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(resp.getWriter());
	 
			
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			
	
	 
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
	}
}
