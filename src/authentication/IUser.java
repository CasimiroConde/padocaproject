package authentication;

/**
 * Classe que representa um usuário abstrato
 * 
 * @author Marcio Barros
 */
public interface IUser 
{
	/**
	 * Retorna o identificador do usuário
	 */
	public int getId();
	
	/**
	 * Retorna o nome do usuário
	 */
	public String getName();
	
	/**
	 * Retorna o e-mail do usuário
	 */
	public String getEmail();
	
	/**
	 * Retorna a senha do usuário
	 */
	public String getPassword();
	
	/**
	 * Verifica se o usuário tem um determinado nível de acesso no sistema
	 * 
	 * @param level		Nível de acesso desejado
	 */
	public boolean checkLevel(String level);
}