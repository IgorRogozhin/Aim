package ru.aim.modelmanagers;

import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.aim.models.WebUser;

/**
 * Manages Cookie's object life-cycle
 *
 */
public class CookieManager implements Serializable{
	private static final long serialVersionUID = 1L;
	private final String credentials_uid = "credentials_uid";
	private final String credentials_pwd = "credentials_pwd";
	private final int cookieLife = 3600*24*7;
	
	/**
	 * Creates Cookie object with lifetime=7days 
	 * and add Cookie to response
	 * @param res
	 * @param wu
	 * @return boolean
	 */
	public boolean AddCredentials(HttpServletResponse res, WebUser wu)
	{
		//we also want to store the information in a cookie
		//for reuse later:
		try
		{
			Cookie uidCook = new Cookie(credentials_uid,wu.getUserId());
			uidCook.setMaxAge(cookieLife);  //7 days
			res.addCookie(uidCook);
			Cookie pwdCook = new Cookie(credentials_pwd,wu.getPassword());
			uidCook.setMaxAge(cookieLife);  //7 days
			res.addCookie(pwdCook);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Clear the credential cookies
	 * @param req the HttpServletRequest
	 * @param res the HttpServletResponse
	 * @return true on success else false
	 */
	public boolean ClearCredentials(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			Cookie[] MyCookies = req.getCookies();
			if (MyCookies != null && MyCookies.length > 0)
			{
				for (Cookie c : MyCookies)
				{
					if (c.getName().toLowerCase().contains("credentials"))
					{
						//expire the cookie
						c.setMaxAge(0);
						res.addCookie(c);
					}
				}
			}
		}
		catch (Exception ex)
		{
			return false;
		}
		return true;
	}
}
