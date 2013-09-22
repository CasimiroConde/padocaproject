package padocaproject;

import java.util.List;

import simplemvc.datastore.DataObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;



public class Usuario implements DataObject {
	
	private long id;
	private String nome;
	private String sobreNome;
	private String email;
	private String senha;
	private int tempoEmpresa;
	private boolean ativo;
	private boolean forcaResetSenha;
	private boolean deveTrocarSenha;
	
	public static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
	public Usuario(Long iD, String nome, String sobrenome, String email, String senha,
			List<Projeto> projetos) 
	{
		super();
		id = iD;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.sobreNome = sobrenome;
	}

	public Usuario() 
	{

	}
	
	public String getNome() 
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getSenha() 
	{
		return senha;
	}

	public void setSenha(String senha) 
	{
		this.senha = senha;
	}

	@Override
	public Long getId() 
	{
		return id;
	}

	@Override
	public void setId(Long id) 
	{
		this.id = id;
		
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public int getTempoEmpresa() {
		return tempoEmpresa;
	}

	public void setTempoEmpresa(int tempoEmpresa) {
		this.tempoEmpresa = tempoEmpresa;
	}

	public boolean isAtivo() {
		return ativo;
	}
	
	public boolean getAtivo()
	{
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isForcaResetSenha() {
		return forcaResetSenha;
	}
	
	public boolean getForcaResetSenha()
	{
		return forcaResetSenha;
	}

	public void setForcaResetSenha(boolean forcaResetSenha) {
		this.forcaResetSenha = forcaResetSenha;
	}

	public boolean isDeveTrocarSenha() {
		return deveTrocarSenha;
	}

	public boolean getDeveTrocarSenha()
	{
		return deveTrocarSenha;
	}
	
	public void setDeveTrocarSenha(boolean deveTrocarSenha) {
		this.deveTrocarSenha = deveTrocarSenha;
	}
	
	
}
