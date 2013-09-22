package authentication;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utill.Conversion;

/**
 * Servi�o de autentica��o do sistema
 * 
 * @author Marcio Barros
 */
public class AuthenticationService
{
	/**
	 * Chave de acesso ao usu�rio logado na mem�ria de requisi��o
	 */
    private static final String USER_KEY = "userid";
    
    /**
     * Classe de acesso ao servi�o de autentica��o na mem�ria de requisi��o
     */
    private static final String SERVICE_KEY = "authservice";

    /**
     * Indica o usu�rio logado no sistema, salvando um cookie com seu identificador
     */
    public static void setCurrentUser(HttpServletRequest request, HttpServletResponse response, IUser user)
    {    
        setCurrentUser(request, user);
        Cookie cookie = new Cookie(USER_KEY, Integer.toString(user.getId()));
        cookie.setPath("/");
    	response.addCookie(cookie);
    }
    
    /**
     * Indica o usu�rio logado no sistema, sem salvar seu identificador
     */
    public static void setCurrentUser(HttpServletRequest request, IUser user)
    {    
    	request.setAttribute(USER_KEY, user);
    }
    
    /**
     * Retorna o usu�rio logado
     */
    public static IUser getUser(ServletRequest request)
    {    
    	return (IUser) request.getAttribute(USER_KEY);
    }
    
    /**
     * Indica o servi�o de acesso a usu�rios que ser� usado na aplica��o
     */
    public static void setCurrentUserService(ServletRequest request, IUserService service)
    {    
    	request.setAttribute(SERVICE_KEY, service);
    }
    
    /**
     * Retorna o servi�o de acesso a usu�rios
     */
    public static IUserService getUserService(ServletRequest request)
    {    
    	return (IUserService) request.getAttribute(SERVICE_KEY);
    }

    /**
     * Retorna o identificador do usu�rio logado no sistema por seu cookie
     */
	public static long getUserCookie (HttpServletRequest request)
	{
		//System.out.println("Searching for cookie ...");
		Cookie[] cookies = request.getCookies();
		
		if (cookies == null)
			return -1;
		
		for (Cookie c : cookies)
			if (c.getName().compareTo(USER_KEY) == 0)
			{
				//System.out.println("Cookie found (" + c.getPath() + ", " + c.getDomain() + ")");
				return Conversion.safeParseLong(c.getValue(), -1);
			}
		
        return -1;
	}

	/**
	 * Invalida o usu�rio logado no sistema
	 */
	public static void invalidateCurrentUser(HttpServletRequest request, HttpServletResponse response) 
	{
        Cookie cookie = new Cookie(USER_KEY, null);
        cookie.setPath("/");
		response.addCookie(cookie);
		request.removeAttribute(USER_KEY);
	}
}