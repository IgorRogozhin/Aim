package ru.aim.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.aim.dbmodels.DBManager;
import ru.aim.modelmanagers.CookieManager;
import ru.aim.modelmanagers.WebUserManager;
import ru.aim.models.WebUser;

/**
 * Servlet implementation class LoginUser
 */
@WebServlet("/registerUser.do")
public class RegistrationUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationUser() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession s = request.getSession();
		WebUser wu = (WebUser)s.getAttribute("authorized_user");
		WebUserManager wum = new WebUserManager();
		
		String baseURL = request.getServletContext().getInitParameter("baseURL");
		String loginError = baseURL + "/loginError.jsp";
		String login = "";
		String pwd = "";
		String destination = "";
		
		//checks whether user logged in system or not, if not then
		if (!wum.userIsValid(wu))
		{
			//get parameters and validate not empty:
			if (request.getParameter("uid") != null)
			{
				login = request.getParameter("uid");
			}
			if (request.getParameter("pwd") != null)
			{
				pwd = request.getParameter("pwd");
			}
			if (!wum.validateParams(login, pwd))
			{
				//bad data
				response.sendRedirect(loginError);
			}
			//"AimDBManager" has been set in DBManagerSetup with ServletContextListener;
			//use models to get matching user
			if (getServletContext().getAttribute("AimDBManager") != null)
			{
				
				DBManager dbm = (DBManager)getServletContext().getAttribute("AimDBManager");
				wu = wum.setWebUserByCredentials(dbm, login, pwd);
				
				if (wu == null)
				{
					s.setAttribute("wrongName", login);
					response.sendRedirect(baseURL + "/registration.jsp");
					return;
				}
				s.setAttribute("authorized_user", wu);
				
				//add cookie if user wants to
				if (request.getParameter("rememberMe") != null)
				{
					String rememberMe = request.getParameter("rememberMe");
					if (rememberMe.equalsIgnoreCase("ON"))
					{
						CookieManager cmgr = new CookieManager();
						cmgr.AddCredentials(response, wu);
					}
				}
			}
		}
		
		//direct to destination
		if (wum.userIsValid(wu)) {
			 destination = (baseURL + "/Protected/Cabinet.jsp");
		} else {
			destination = (baseURL + "/registration.jsp");
		}
		
		response.sendRedirect(destination);
	}

}
