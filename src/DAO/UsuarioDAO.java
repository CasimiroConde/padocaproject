package DAO;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import padocaproject.Usuario;
import simplemvc.datastore.AbstractDAO;

public class UsuarioDAO extends AbstractDAO<Usuario>{

	public static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	public UsuarioDAO() 
	{ 
		super("Usuario");
		}
	
	@Override
	public Usuario load(Entity e)
	{
		Usuario U = new Usuario();
		U.setNome((String) e.getProperty("Nome"));
		U.setSobreNome((String) e.getProperty("Sobrenome"));
		U.setEmail((String) e.getProperty("email"));
		U.setSenha((String) e.getProperty("senha"));
		U.setId(e.getKey().getId());
		U.setTempoEmpresa(getIntProperty(e, "tempoEmpresa", 0));
		U.setForcaResetSenha(getBooleanProperty(e, "forcaTrocaSenha", false));
		
		
		return U;
	}


	@Override
	public void save(Usuario t, Entity e)
	{
		e.setProperty("Nome", t.getNome());
		e.setProperty("Sobrenome", t.getSobreNome());
		e.setProperty("email", t.getEmail());
		e.setProperty("senha", t.getSenha());
		e.setProperty("tempoEmpresa", t.getTempoEmpresa());
		e.setProperty("forcaTrocaSenha", t.getForcaResetSenha());
		ds.put(e);
	}


}
