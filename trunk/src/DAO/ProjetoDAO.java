package DAO;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import padocaproject.Projeto;
import simplemvc.datastore.AbstractDAO;

public class ProjetoDAO extends AbstractDAO<Projeto> {

	
	public static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	public ProjetoDAO() 
	{ 
		super("Projeto");
		}

	@Override
	public Projeto load(Entity e) 
	{
		Projeto p = new Projeto();
		
		p.setNome((String) e.getProperty("Nome"));
		p.setUsuario( (Long) e.getProperty("Usuario"));
		p.setId(e.getKey().getId());	
		return p;
		
	}

	@Override
	public void save(Projeto t, Entity e) 
	{
		e.setProperty("Nome", t.getNome());
		e.setProperty("Usuario", t.getUsuario());
		ds.put(e);
	}
	
}
