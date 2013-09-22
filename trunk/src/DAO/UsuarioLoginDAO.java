package DAO;

import java.util.Date;
import java.util.List;

import padocaproject.Usuario;
import simplemvc.datastore.AbstractDAO;
import simplemvc.datastore.DataObject;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;

public class UsuarioLoginDAO extends AbstractDAO<LoginUsuario>
{
	protected UsuarioLoginDAO()
	{
		super("UsuarioLogin");
	}

	@Override
	protected LoginUsuario load(Entity e)
	{
		LoginUsuario login = new LoginUsuario();
		login.setId((Long)e.getKey().getId());
		login.setIdUsuario(getIntProperty(e, "idUsuario", -1));
		login.setToken(getBooleanProperty(e, "sucesso", false));
		login.setTimestamp(getDateProperty(e, "timestamp"));
		return login;
	}

	@Override
	protected void save(LoginUsuario login, Entity e)
	{
		e.setProperty("idUsuario", login.getIdUsuario());
		e.setProperty("sucesso", login.getSucesso());
		e.setProperty("timestamp", login.getTimestamp());
	}

	public void registraLoginFalha(Long long1)
	{
		this.put(new LoginUsuario(long1, false));

		List<LoginUsuario> logins = list(0, 3, exactFilter("idUsuario", FilterOperator.EQUAL, long1), "timestamp", SortDirection.DESCENDING);
		
		for (LoginUsuario login : logins)
			if (login.getSucesso())
				return;

		if (logins.size() == 3)
		{
			Usuario usuario = DAOFactory.getUsuarioDAO().get(long1);
			usuario.setForcaResetSenha(true);
			DAOFactory.getUsuarioDAO().put(usuario);
		}
	}

	public void registraLoginSucesso(Long long1)
	{
		this.put(new LoginUsuario(long1, true));

		Usuario usuario = DAOFactory.getUsuarioDAO().get(long1);
		usuario.setForcaResetSenha(false);
		DAOFactory.getUsuarioDAO().put(usuario);
	}

	public Date pegaDataUltimoLogin(int idUsuario)
	{
		List<LoginUsuario> logins = list(0, 1, exactFilter("idUsuario", FilterOperator.EQUAL, idUsuario), "timestamp", SortDirection.DESCENDING);
		return (logins.size() > 0) ? logins.get(0).getTimestamp() : null;
	}
}

class LoginUsuario implements DataObject
{
	private long id;
	private long idUsuario;
	private boolean sucesso;
	private Date timestamp;
	
	public LoginUsuario(long idUsuario, boolean sucesso)
	{
		this.id = -1;
		this.idUsuario = idUsuario;
		this.sucesso = sucesso;
		this.timestamp = new Date();
	}

	public LoginUsuario()
	{
		this((long) -1, false);
	}

	@Override
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public long getIdUsuario()
	{
		return idUsuario;
	}

	public void setIdUsuario(long id)
	{
		this.idUsuario = id;
	}

	public boolean getSucesso()
	{
		return sucesso;
	}

	public void setToken(boolean flag)
	{
		this.sucesso = flag;
	}

	public Date getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}

	
}