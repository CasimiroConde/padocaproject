package DAO;

import java.util.StringTokenizer;

import simplemvc.datastore.AbstractDAO;
import simplemvc.datastore.HashField;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import funcaoTransacional.Funcao;
import funcaoTransacional.FuncoesTransacionais;

public class FuncaoTransacionalDAO extends AbstractDAO<FuncoesTransacionais> 
{

	public static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	public FuncaoTransacionalDAO() 
	{
		super("Funcao_Transacional");
	}

	@Override
	public FuncoesTransacionais load(Entity e) 
	{
		
		FuncoesTransacionais ft = new FuncoesTransacionais();
		ft.setNome(getStringProperty(e, "Nome"));
		ft.setTipo(getStringProperty(e, "tipo"));
		ft.setProjeto( getLongProperty(e, "Projeto"));
		
		HashField hf = getHashFieldProperty(e, "ftrs");
		
		for (String nome : hf.getNames())
		{
			Funcao ftr = new Funcao();
			ftr.setNome(nome);
			
			String dets = hf.getString(nome);
			StringTokenizer st = new StringTokenizer(dets, ";");
			
			while(st.hasMoreTokens())
				ftr.adicionaDado(st.nextToken());
			
			ft.adicionaFuncao(ftr);
		}
		
		ft.setId(e.getKey().getId());	
		return ft;
	}

	@Override
	public void save(FuncoesTransacionais t, Entity e) {
		e.setProperty("Nome", t.getNome());
		e.setProperty("tipo", t.getTipo());
		e.setProperty("Projeto", t.getIdProjeto());
		
		HashField hf = new HashField();
		
		for (Funcao ftr : t.getFuncao())
		{
			String dets = createDETList(ftr);	
			hf.addField(ftr.getNome(), dets);
		}
		
		e.setProperty("ftrs", hf.toString());
		ds.put(e);
		
	}
	
	private String createDETList(Funcao ftr) 
	{
		String dets = "";
		
		if (ftr.contaDados() > 0)
			dets += ftr.pegaDadoIndice(0);
		
		for (int i = 1; i < ftr.contaDados(); i++)
			dets += ';' + ftr.pegaDadoIndice(i);

		return dets;
	}
		
}
