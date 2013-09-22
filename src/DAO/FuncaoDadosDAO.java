package DAO;

import java.util.StringTokenizer;

import simplemvc.datastore.AbstractDAO;
import simplemvc.datastore.HashField;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import funcaoDados.FuncaoDados;
import funcaoDados.RegistroDados;

public class FuncaoDadosDAO extends AbstractDAO<FuncaoDados> 
{
	public static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
	public FuncaoDadosDAO() 
	{
		super("Funcao_Dados");
	}

	@Override
	public FuncaoDados load(Entity e) 
	{
		FuncaoDados fd = new FuncaoDados();
		fd.setNome(getStringProperty(e, "Nome"));
		fd.setTipo(getStringProperty(e, "tipo"));
		fd.setIdProjeto(getLongProperty(e, "Projeto"));
		HashField hf = getHashFieldProperty(e, "rets");
		
		for (String nome : hf.getNames())
		{
			RegistroDados ret = new RegistroDados();
			ret.setNome(nome);
			
			String dets = hf.getString(nome);
			StringTokenizer st = new StringTokenizer(dets, ";");
			
			while(st.hasMoreTokens())
				ret.adicionaDado(st.nextToken());
			
			fd.adicionaRegistro(ret);
		}
		
		fd.setId(e.getKey().getId());	
		return fd;
	}

	@Override
	public void save(FuncaoDados t, Entity e) 
	{
		e.setProperty("Nome", t.getNome());
		e.setProperty("tipo", t.getTipo());
		e.setProperty("Projeto", t.getIdProjeto());
		
		HashField hf = new HashField();
		
		for (RegistroDados ret : t.getRegistros())
		{
			String dets = createDETList(ret);	
			hf.addField(ret.getNome(), dets);
		}
		
		e.setProperty("rets", hf.toString());
		ds.put(e);	
	}

	private String createDETList(RegistroDados ret) 
	{
		String dets = "";
		
		if (ret.contaDados() > 0)
			dets += ret.pegaDadoIndice(0);
		
		for (int i = 1; i < ret.contaDados(); i++)
			dets += ';' + ret.pegaDadoIndice(i);

		return dets;
	}
}