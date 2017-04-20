package ru.aim.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.aim.dbmodels.DBManager;
import ru.aim.modelmanagers.WebAimGroupManager;
import ru.aim.modelmanagers.WebAimManager;
import ru.aim.modelmanagers.WebGroupManager;
import ru.aim.models.WebAim;
import ru.aim.models.WebCollector;
import ru.aim.models.WebGroup;
import ru.aim.utilities.AimManagerUtils;


/**
 * Servlet implementation class SignoutGroup
 */
@WebServlet("/Protected/signoutGroup.do")
public class SignoutGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignoutGroup() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int userId = Integer.parseInt(request.getParameter("userId"));
		String[] groups = request.getParameterValues("groups[]");
		String baseURL = getServletContext().getInitParameter("baseURL");
		
		try
		{
			if (getServletContext().getAttribute("AimDBManager") != null)
			{
				DBManager dbm = (DBManager)getServletContext().getAttribute("AimDBManager");
				WebGroupManager wgm = new WebGroupManager();
				
				if ( AimManagerUtils.arrayNullOrEmpty(groups) ) {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Protected/Cabinet.jsp");
					rd.forward(request, response);
					return;
				} else {
					for(String groupId: groups) { 
						boolean result = wgm.signoutUserFromGroup(dbm, userId, Integer.parseInt(groupId));
						 if(!result) throw new Exception("Cannot erase User from Group");
					}
				}
		
				String congrats = "Поздравляю, вы вышли!";
				request.setAttribute("success", congrats);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Protected/updateSessionData.do");
				rd.forward(request, response);
				return;
			}
			else
			{
				new Exception ("No database connection.");
				response.sendRedirect(baseURL + "/errorHandler.jsp");
			}
		}
		catch (Exception ex)
		{
			//log it.
			response.sendRedirect(baseURL + "/errorHandler.jsp");
		}
	}

}
