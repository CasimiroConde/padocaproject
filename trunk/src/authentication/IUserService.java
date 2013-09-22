package authentication;

/**
 * Classe abstrata dos servi�os que devem ser oferecidos para o usu�rio
 * 
 * @author Marcio Barros
 */
public interface IUserService 
{
	/**
	 * Retorna um usu�rio, dado seu identificador
	 * 
	 * @param id			Identificador do usu�rio desejado
	 */
	IUser getUserId(long id);
	
	/**
	 * Retorna um usu�rio, dado seu e-mail
	 * 
	 * @param email			E-mail do usu�rio desejado
	 */
	IUser getUserEmail(String email);
	
	/**
	 * Altera a senha do usu�rio corrente
	 * 
	 * @param usuario		Usu�rio logado no sistema
	 * @param password		Nova senha do usu�rio
	 */
	void changePassword(IUser usuario, String password);
}