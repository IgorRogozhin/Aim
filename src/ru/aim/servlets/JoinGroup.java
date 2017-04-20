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
import ru.aim.modelmanagers.WebGroupManager;
import ru.aim.models.WebGroup;


/**
 * Servlet implementation class JoinGroup
 */
@WebServlet("/Protected/joinGroup.do")
public class JoinGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinGroup() {
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
		String name = request.getParameter("nameOfGroup");
		String password = request.getParameter("pwd");		
		
		String baseURL = getServletContext().getInitParameter("baseURL");
		
		try
		{
			
			if (getServletContext().getAttribute("AimDBManager") != null)
			{
				DBManager dbm = (DBManager)getServletContext().getAttribute("AimDBManager");
				WebGroupManager wgm = new WebGroupManager();
				WebGroup group = null;
				
				//whether group exist
				group = wgm.getWebGroupByName(dbm, name);
				if(group == null) {
					request.setAttribute("wrongInput", name);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Protected/joinGroup.jsp");
					rd.forward(request, response);
					return;
				}
				//whether password is correct
				if( !(group.getPassword().equals(password)) ) {
					request.setAttribute("wrongInput", "СуперСекретныйПароль");
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Protected/joinGroup.jsp");
					rd.forward(request, response);
					return;
				}
				//whether WebUser already joined or not
				group = wgm.getWebGroupByUserIdAndGroup(dbm, userId, name);
				if ( !(group == null) ) {
					request.setAttribute("alreadyIn", name);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Protected/joinGroup.jsp");
					rd.forward(request, response);
					return;
				}
				group = wgm.joinGroup(dbm, userId, name, password);
				
				ArrayList<WebGroup> groupsOfUser = wgm.getGroupsByUser(dbm, userId);
				HttpSession s = request.getSession();
				s.setAttribute("groupData", groupsOfUser);
				
				String congrats = String.format("Поздравляю, вы присоединились к \"%s\"!", name);
				request.setAttribute("success", congrats);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Protected/updateSessionData.do");
				rd.forward(request, response);
				return;
			}
			else
			{
				throw new Exception ("No database connection.");
			}
		}
		catch (Exception ex)
		{
			//log it.
			response.sendRedirect(baseURL + "/errorHandler.jsp");
		}
	}

}
